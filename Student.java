// edu/ccrm/domain/Person.java
package edu.ccrm.domain;
import java.time.LocalDate;

public abstract class Person {
    private String id;
    private String fullName;
    private String email;
    private boolean isActive;
    protected LocalDate createdOn;

    public Person(String id, String fullName, String email) { /* ... */ } // implement

    public abstract void printProfile();
    public String toString() { /* ... */ return ""; }
}

// edu/ccrm/domain/Student.java
package edu.ccrm.domain;

import java.util.*;
import java.time.LocalDate;

public class Student extends Person {
    private String regNo;
    private List<Enrollment> enrollments;
    private LocalDate deactivatedOn;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrollments = new ArrayList<>();
    }

    @Override
    public void printProfile() { System.out.println(this); }
}
