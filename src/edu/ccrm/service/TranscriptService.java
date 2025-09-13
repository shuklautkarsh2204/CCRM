package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class TranscriptService {
    public String generateTranscript(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append("Transcript for ").append(student.getFullName()).append(" (RegNo: ").append(student.getRegNo()).append(")\n");
        double totalPoints = 0;
        int totalCredits = 0;
        for (CourseEnrollment ce : student.getEnrolledCourses()) {
            sb.append(ce.toString()).append("\n");
            if (ce.getGrade() != null) {
                totalPoints += ce.getGrade().getPoints() * ce.getCourse().getCredits();
                totalCredits += ce.getCourse().getCredits();
            }
        }
        double gpa = totalCredits == 0 ? 0 : totalPoints / totalCredits;
        sb.append(String.format("GPA: %.2f", gpa));
        return sb.toString();
    }
}
