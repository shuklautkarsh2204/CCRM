package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class EnrollmentService {
    private final StudentService studentService;
    private final CourseService courseService;
    private final int MAX_CREDITS = 30;

    public EnrollmentService(StudentService ss, CourseService cs) {
        this.studentService = ss;
        this.courseService = cs;
    }

    public void enrollStudent(String studentId, String courseCode) throws MaxCreditLimitExceededException {
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseCode);
        if (student == null || course == null)
            throw new IllegalArgumentException("Student or Course not found");
        int totalCredits = student.getEnrolledCourses().stream().mapToInt(e -> e.getCourse().getCredits()).sum();
        if (totalCredits + course.getCredits() > MAX_CREDITS)
            throw new MaxCreditLimitExceededException("Max credits exceeded");
        CourseEnrollment ce = new CourseEnrollment(course);
        student.enrollCourse(ce);
    }

    public void unenrollStudent(String studentId, String courseCode) {
        Student student = studentService.getStudent(studentId);
        if (student == null)
            throw new IllegalArgumentException("Student not found");
        student.getEnrolledCourses().removeIf(e -> e.getCourse().getCode().equals(courseCode));
    }

    public void recordMarks(String studentId, String courseCode, int marks, Grade grade) {
        Student student = studentService.getStudent(studentId);
        if (student == null)
            throw new IllegalArgumentException("Student not found");
        for (CourseEnrollment ce : student.getEnrolledCourses()) {
            if (ce.getCourse().getCode().equals(courseCode)) {
                ce.setMarks(marks);
                ce.setGrade(grade);
            }
        }
    }
}
