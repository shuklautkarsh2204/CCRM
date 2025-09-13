package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import java.util.*;
import java.util.stream.Collectors;

public class CourseService {
    private final Map<String, Course> courses = new HashMap<>();

    public void addCourse(Course course) {
        if (courses.containsKey(course.getCode())) throw new IllegalArgumentException("Duplicate course code");
        courses.put(course.getCode(), course);
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses.values());
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public void updateCourse(String code, String title, int credits) {
        Course c = courses.get(code);
        if (c != null) {
            c.setTitle(title);
            c.setCredits(credits);
        }
    }

    public void deactivateCourse(String code) {
        Course c = courses.get(code);
        if (c != null) c.deactivate();
    }

    public List<Course> searchByInstructor(Instructor instructor) {
        return courses.values().stream()
            .filter(c -> c.getInstructor() != null && c.getInstructor().equals(instructor))
            .collect(Collectors.toList());
    }

    public List<Course> filterByDepartment(String department) {
        return courses.values().stream()
            .filter(c -> c.getDepartment().equalsIgnoreCase(department))
            .collect(Collectors.toList());
    }

    public List<Course> filterBySemester(Semester semester) {
        return courses.values().stream()
            .filter(c -> c.getSemester() == semester)
            .collect(Collectors.toList());
    }
}
