package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private String regNo;
    private Status status;
    private List<CourseEnrollment> enrolledCourses;
    private LocalDate enrollmentDate;

    public enum Status { ACTIVE, INACTIVE }

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.status = Status.ACTIVE;
        this.enrolledCourses = new ArrayList<>();
        this.enrollmentDate = LocalDate.now();
    }

    public String getRegNo() { return regNo; }
    public Status getStatus() { return status; }
    public List<CourseEnrollment> getEnrolledCourses() { return enrolledCourses; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }

    public void deactivate() { this.status = Status.INACTIVE; }
    public void enrollCourse(CourseEnrollment ce) { enrolledCourses.add(ce); }
    public void unenrollCourse(CourseEnrollment ce) { enrolledCourses.remove(ce); }
    public String getProfile() {
        return String.format("Student Profile: %s\nRegNo: %s\nEmail: %s\nStatus: %s\nEnrolled: %d courses", fullName, regNo, email, status, enrolledCourses.size());
    }
    public String toString() {
        return getProfile();
    }
}
