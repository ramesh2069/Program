import java.util.Scanner;

abstract class Shape {
    abstract double calculateSurfaceArea();
    abstract double calculateVolume();
}

class Sphere extends Shape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double calculateVolume() {
        return (4 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
}

class Cube extends Shape {
    private double side;

    public Cube(double side) {
        this.side = side;
    }

    @Override
    public double calculateSurfaceArea() {
        return 6 * side * side;
    }

    @Override
    public double calculateVolume() {
        return Math.pow(side, 3);
    }
}

class RectangularPrism extends Shape {
    private double length, width, height;

    public RectangularPrism(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateSurfaceArea() {
        return 2 * (length * width + width * height + height * length);
    }

    @Override
    public double calculateVolume() {
        return length * width * height;
    }
}

class Cylinder extends Shape {
    private double radius, height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public double calculateSurfaceArea() {
        return 2 * Math.PI * radius * (radius + height);
    }

    @Override
    public double calculateVolume() {
        return Math.PI * radius * radius * height;
    }
}

public class ShapeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("Choose a shape:");
            System.out.println("1. Sphere");
            System.out.println("2. Cube");
            System.out.println("3. Rectangular Prism");
            System.out.println("4. Cylinder");
            System.out.print("Enter your choice: ");

            int choice = getValidInt(sc);
            Shape shape = null;

            switch (choice) {
                case 1:
                    System.out.print("Enter the radius: ");
                    double radius = getValidDouble(sc);
                    shape = new Sphere(radius);
                    break;
                case 2:
                    System.out.print("Enter the side length: ");
                    double side = getValidDouble(sc);
                    shape = new Cube(side);
                    break;
                case 3:
                    System.out.print("Enter the length: ");
                    double length = getValidDouble(sc);
                    System.out.print("Enter the width: ");
                    double width = getValidDouble(sc);
                    System.out.print("Enter the height: ");
                    double height = getValidDouble(sc);
                    shape = new RectangularPrism(length, width, height);
                    break;
                case 4:
                    System.out.print("Enter the radius: ");
                    radius = getValidDouble(sc);
                    System.out.print("Enter the height: ");
                    height = getValidDouble(sc);
                    shape = new Cylinder(radius, height);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            if (shape != null) {
                System.out.println("Surface Area: " + shape.calculateSurfaceArea());
                System.out.println("Volume: " + shape.calculateVolume());
            }

            System.out.print("Do you want to calculate another shape? (yes/no): ");
            String userChoice = sc.next();
            if (!userChoice.equalsIgnoreCase("yes")) {
                continueProgram = false;
            }
        }

        System.out.println("Program terminated.");
        sc.close();
    }

    private static int getValidInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.print("Invalid input! Enter a valid integer: ");
                sc.nextLine();
            }
        }
    }

    private static double getValidDouble(Scanner sc) {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.print("Invalid input! Enter a valid number: ");
                sc.nextLine();
            }
        }
    }
}
