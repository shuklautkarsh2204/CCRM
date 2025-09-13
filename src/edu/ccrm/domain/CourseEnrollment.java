package edu.ccrm.domain;

import java.time.LocalDate;

public class CourseEnrollment {
    private final Course course;
    private Grade grade;
    private LocalDate enrollmentDate;
    private int marks;

    public CourseEnrollment(Course course) {
        this.course = course;
        this.enrollmentDate = LocalDate.now();
    }

    public Course getCourse() { return course; }
    public Grade getGrade() { return grade; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public int getMarks() { return marks; }

    public void setMarks(int marks) { this.marks = marks; }
    public void setGrade(Grade grade) { this.grade = grade; }
    public String toString() {
        return String.format("%s: %d marks, Grade: %s", course.getCode(), marks, grade);
    }
}
