package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        if (students.containsKey(student.getId())) throw new IllegalArgumentException("Duplicate student ID");
        students.put(student.getId(), student);
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students.values());
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public void updateStudent(String id, String fullName, String email) {
        Student s = students.get(id);
        if (s != null) {
            s.setFullName(fullName);
            s.setEmail(email);
        }
    }

    public void deactivateStudent(String id) {
        Student s = students.get(id);
        if (s != null) s.deactivate();
    }

    public List<Student> searchByName(String name) {
        return students.values().stream()
            .filter(s -> s.getFullName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
    }
}
