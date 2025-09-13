package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person {
    protected String id;
    protected String fullName;
    protected String email;
    protected LocalDate createdDate;
    protected LocalDate updatedDate;

    public Person(String id, String fullName, String email) {
        assert id != null : "ID must not be null";
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getUpdatedDate() { return updatedDate; }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        this.updatedDate = LocalDate.now();
    }
    public void setEmail(String email) {
        this.email = email;
        this.updatedDate = LocalDate.now();
    }

    public abstract String getProfile();

    public String toString() {
        return String.format("%s: %s (%s)", getClass().getSimpleName(), fullName, email);
    }
}
