package edu.ccrm.io;

import edu.ccrm.domain.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ImportExportService {
    public List<Student> importStudents(Path filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.skip(1).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    students.add(new Student(parts[0], parts[1], parts[2], parts[3]));
                }
            });
        }
        return students;
    }

    public void exportStudents(List<Student> students, Path filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,regNo,fullName,email");
        for (Student s : students) {
            lines.add(String.join(",", s.getId(), s.getRegNo(), s.getFullName(), s.getEmail()));
        }
        Files.write(filePath, lines);
    }

    // Similar methods for courses, enrollments, etc.
}
