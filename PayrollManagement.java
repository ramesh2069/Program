import java.util.Scanner;

interface NetPayCalculator {
    void calculateGrossPay();
    void calculateTax();
    void calculateNetPay();
}

class EmployeeDetails {
    int employeeId;
    float basicSalary;
    String employeeName, joiningDate, jobTitle, panNumber, department;

    void setDetails(int id, String name, String date, String title, String pan, String dept, float salary) {
        this.employeeId = id;
        this.employeeName = name;
        this.joiningDate = date;
        this.jobTitle = title;
        this.panNumber = pan;
        this.department = dept;
        this.basicSalary = salary;
    }
}

class Payroll extends EmployeeDetails implements NetPayCalculator {
    float grossSalary, taxableAmount, netSalary;

    Payroll(int id, String name, String date, String title, String pan, String dept, float salary) {
        setDetails(id, name, date, title, pan, dept, salary);
    }


    public void calculateNetPay() {
        netSalary = grossSalary - taxableAmount;
    }


    public void calculateGrossPay() {
        float da = 0.5f * basicSalary; // Dearness Allowance
        float hra = 0.2f * basicSalary; // House Rent Allowance
        grossSalary = basicSalary + da + hra;
    }


    public void calculateTax() {
        if (grossSalary >= 100000 && grossSalary < 200000) {
            taxableAmount = 0.20f * grossSalary;
        } else if (grossSalary > 300000) {
            taxableAmount = 0.30f * grossSalary;
        } else {
            taxableAmount = 0;
        }
    }

    public void displayEmployeeDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + employeeName);
        System.out.println("Joining Date: " + joiningDate);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("PAN Number: " + panNumber);
        System.out.println("Department: " + department);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Taxable Amount: " + taxableAmount);
        System.out.println("Net Salary: " + netSalary);
        System.out.println();
    }
}

public class PayrollManagement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of employees: ");
        int numberOfEmployees = input.nextInt();
        input.nextLine(); // Consume newline

        Payroll[] employeeList = new Payroll[numberOfEmployees];

        for (int i = 0; i < numberOfEmployees; i++) {
            System.out.println("Enter details for employee " + (i + 1) + ":");
            System.out.print("ID: ");
            int id = input.nextInt();
            input.nextLine(); // Consume newline
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Date of Joining (dd/mm/yyyy): ");
            String date = input.nextLine();
            System.out.print("Designation: ");
            String title = input.nextLine();
            System.out.print("PAN Number: ");
            String pan = input.nextLine();
            System.out.print("Department: ");
            String dept = input.nextLine();
            System.out.print("Basic Salary: ");
            float salary = input.nextFloat();
            input.nextLine(); // Consume newline

            employeeList[i] = new Payroll(id, name, date, title, pan, dept, salary);
        }

        System.out.println("\nEmployee Details:\n");
        for (Payroll employee : employeeList) {
            employee.calculateGrossPay();
            employee.calculateTax();
            employee.calculateNetPay();
            employee.displayEmployeeDetails();
        }

        input.close();
    }
}
