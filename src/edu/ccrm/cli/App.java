package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
        TranscriptService transcriptService = new TranscriptService();
        ImportExportService importExportService = new ImportExportService();
        BackupService backupService = new BackupService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n--- Campus Course & Records Manager (CCRM) ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enrollment & Grading");
            System.out.println("4. Import/Export Data");
            System.out.println("5. Backup & Show Backup Size");
            System.out.println("6. Reports");
            System.out.println("7. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: // Manage Students
                    System.out.println("1. Add Student");
                    System.out.println("2. List Students");
                    System.out.println("3. Print Transcript");
                    System.out.print("Select option: ");
                    int studentChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (studentChoice == 1) {
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter RegNo: ");
                        String regNo = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        Student student = new Student(id, regNo, name, email);
                        studentService.addStudent(student);
                        System.out.println("Student added.");
                    } else if (studentChoice == 2) {
                        for (Student s : studentService.listStudents()) {
                            System.out.println(s);
                        }
                    } else if (studentChoice == 3) {
                        System.out.print("Enter Student ID: ");
                        String id = scanner.nextLine();
                        Student s = studentService.getStudent(id);
                        if (s != null) {
                            System.out.println(transcriptService.generateTranscript(s));
                        } else {
                            System.out.println("Student not found.");
                        }
                    }
                    break;
                case 2: // Manage Courses
                    System.out.println("1. Add Course");
                    System.out.println("2. List Courses");
                    System.out.print("Select option: ");
                    int courseChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (courseChoice == 1) {
                        System.out.print("Enter Code: ");
                        String code = scanner.nextLine();
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Credits: ");
                        int credits = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Instructor Name: ");
                        String instructorName = scanner.nextLine();
                        System.out.print("Enter Instructor Email: ");
                        String instructorEmail = scanner.nextLine();
                        System.out.print("Enter Department: ");
                        String department = scanner.nextLine();
                        System.out.print("Enter Semester (SPRING/SUMMER/FALL): ");
                        String semesterStr = scanner.nextLine();
                        Semester semester = Semester.valueOf(semesterStr.toUpperCase());
                        Instructor instructor = new Instructor("I"+code, instructorName, instructorEmail, department);
                        Course course = new Course.Builder()
                            .code(code)
                            .title(title)
                            .credits(credits)
                            .instructor(instructor)
                            .semester(semester)
                            .department(department)
                            .build();
                        courseService.addCourse(course);
                        System.out.println("Course added.");
                    } else if (courseChoice == 2) {
                        for (Course c : courseService.listCourses()) {
                            System.out.println(c);
                        }
                    }
                    break;
                case 3: 
                    System.out.println("1. Enroll Student");
                    System.out.println("2. Unenroll Student");
                    System.out.println("3. Record Marks");
                    System.out.print("Select option: ");
                    int enrollChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (enrollChoice == 1) {
                        System.out.print("Enter Student ID: ");
                        String sid = scanner.nextLine();
                        System.out.print("Enter Course Code: ");
                        String ccode = scanner.nextLine();
                        try {
                            enrollmentService.enrollStudent(sid, ccode);
                            System.out.println("Enrolled successfully.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else if (enrollChoice == 2) {
                        System.out.print("Enter Student ID: ");
                        String sid = scanner.nextLine();
                        System.out.print("Enter Course Code: ");
                        String ccode = scanner.nextLine();
                        enrollmentService.unenrollStudent(sid, ccode);
                        System.out.println("Unenrolled successfully.");
                    } else if (enrollChoice == 3) {
                        System.out.print("Enter Student ID: ");
                        String sid = scanner.nextLine();
                        System.out.print("Enter Course Code: ");
                        String ccode = scanner.nextLine();
                        System.out.print("Enter Marks: ");
                        int marks = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Grade (S/A/B/C/D/E/F): ");
                        String gradeStr = scanner.nextLine();
                        Grade grade = Grade.valueOf(gradeStr.toUpperCase());
                        enrollmentService.recordMarks(sid, ccode, marks, grade);
                        System.out.println("Marks recorded.");
                    }
                    break;
                case 4: // Import/Export Data
                    System.out.println("1. Import Students");
                    System.out.println("2. Export Students");
                    System.out.print("Select option: ");
                    int ioChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (ioChoice == 1) {
                        System.out.print("Enter file path to import: ");
                        String path = scanner.nextLine();
                        try {
                            for (Student s : importExportService.importStudents(Paths.get(path))) {
                                studentService.addStudent(s);
                            }
                            System.out.println("Students imported.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else if (ioChoice == 2) {
                        System.out.print("Enter file path to export: ");
                        String path = scanner.nextLine();
                        try {
                            importExportService.exportStudents(studentService.listStudents(), Paths.get(path));
                            System.out.println("Students exported.");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;
                case 5: // Backup & Show Backup Size
                    System.out.print("Enter source directory: ");
                    String srcDir = scanner.nextLine();
                    System.out.print("Enter backup root directory: ");
                    String backupRoot = scanner.nextLine();
                    try {
                        java.nio.file.Path backupDir = backupService.backupData(Paths.get(srcDir), Paths.get(backupRoot));
                        long size = backupService.computeBackupSize(backupDir);
                        System.out.println("Backup completed to: " + backupDir);
                        System.out.println("Total backup size: " + size + " bytes");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 6: // Reports
                    System.out.println("1. Top Students by GPA");
                    System.out.println("2. GPA Distribution");
                    System.out.print("Select option: ");
                    int reportChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (reportChoice == 1) {
                        studentService.listStudents().stream()
                            .sorted((a, b) -> {
                                double gpaA = transcriptService.generateTranscript(a).contains("GPA:") ? Double.parseDouble(transcriptService.generateTranscript(a).split("GPA: ")[1]) : 0;
                                double gpaB = transcriptService.generateTranscript(b).contains("GPA:") ? Double.parseDouble(transcriptService.generateTranscript(b).split("GPA: ")[1]) : 0;
                                return Double.compare(gpaB, gpaA);
                            })
                            .limit(3)
                            .forEach(s -> System.out.println(s.getFullName() + " GPA: " + transcriptService.generateTranscript(s).split("GPA: ")[1]));
                    } else if (reportChoice == 2) {
                        studentService.listStudents().stream()
                            .map(s -> transcriptService.generateTranscript(s).split("GPA: ")[1])
                            .map(Double::parseDouble)
                            .collect(java.util.stream.Collectors.groupingBy(gpa -> Math.round(gpa), java.util.stream.Collectors.counting()))
                            .forEach((gpa, count) -> System.out.println("GPA: " + gpa + " Count: " + count));
                    }
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
        System.out.println("Thank you for using CCRM!");
    }
}
