package edu.ccrm.domain;

public class Course {
    private final String code;
    private String title;
    private int credits;
    private Instructor instructor;
    private Semester semester;
    private String department;
    private boolean active;

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
        this.department = builder.department;
        this.active = builder.active;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    public boolean isActive() { return active; }

    public void setTitle(String title) { this.title = title; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public void setSemester(Semester semester) { this.semester = semester; }
    public void setDepartment(String department) { this.department = department; }
    public void deactivate() { this.active = false; }

    public String toString() {
        return String.format("Course: %s - %s (%d credits) [%s]", code, title, credits, department);
    }

    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private Instructor instructor;
        private Semester semester;
        private String department;
        private boolean active = true;

        public Builder code(String code) { this.code = code; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder credits(int credits) { this.credits = credits; return this; }
        public Builder instructor(Instructor instructor) { this.instructor = instructor; return this; }
        public Builder semester(Semester semester) { this.semester = semester; return this; }
        public Builder department(String department) { this.department = department; return this; }
        public Builder active(boolean active) { this.active = active; return this; }
        public Course build() { return new Course(this); }
    }
}
