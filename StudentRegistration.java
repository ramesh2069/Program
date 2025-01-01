import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class InvalidNameException extends Exception {
    public InvalidNameException(String message) {
        super(message);
    }
}

class Student {
    private int rollNumber;
    private String studentName;
    private int studentAge;
    private String courseName;

    public Student(int rollNumber, String studentName, int studentAge, String courseName) throws InvalidAgeException, InvalidNameException {
        if (studentAge < 14 || studentAge > 21) {
            throw new InvalidAgeException("Age must be between 14 and 21.");
        }
        if (!studentName.matches("[a-zA-Z]+")) {
            throw new InvalidNameException("Name must contain only alphabets.");
        }
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.courseName = courseName;
    }

    public void displayDetails() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Name: " + studentName);
        System.out.println("Age: " + studentAge);
        System.out.println("Course: " + courseName);
    }
}

public class StudentRegistration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Student Registration System");
        while (true) {
            try {
                System.out.print("Enter Roll Number: ");
                int rollNumber = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String studentName = sc.nextLine();

                System.out.print("Enter Age: ");
                int studentAge = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Course: ");
                String courseName = sc.nextLine();

                Student student = new Student(rollNumber, studentName, studentAge, courseName);
                System.out.println("Student registered successfully!");
                student.displayDetails();
            } catch (InvalidAgeException | InvalidNameException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine(); // Clear buffer
            }

            System.out.print("Do you want to register another student? (yes/no): ");
            if (!sc.nextLine().trim().equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Thank you for using the Student Registration System!");
        sc.close();
    }
}
