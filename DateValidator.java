class InvalidDayException extends Exception {
    public InvalidDayException(String message) {
        super(message);
    }
}

class InvalidMonthException extends Exception {
    public InvalidMonthException(String message) {
        super(message);
    }
}

public class DateValidator {
    private int day;
    private int month;
    private int year;

    public DateValidator() {
        this.day = 1;
        this.month = 1;
        this.year = 2000;
    }

    public DateValidator(int day, int month, int year) throws InvalidDayException, InvalidMonthException {
        if (month < 1 || month > 12) {
            throw new InvalidMonthException("Invalid month: " + month);
        }
        if (day < 1 || day > getDaysInMonth(month, year)) {
            throw new InvalidDayException("Invalid day: " + day);
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private static int getDaysInMonth(int month, int year) {
        return switch (month) {
            case 4, 6, 9, 11 -> 30;
            case 2 -> (isLeapYear(year) ? 29 : 28);
            default -> 31;
        };
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please provide day, month, and year as command-line arguments.");
            return;
        }

        try {
            int day = Integer.parseInt(args[0]);
            int month = Integer.parseInt(args[1]);
            int year = Integer.parseInt(args[2]);

            DateValidator date = new DateValidator(day, month, year);
            System.out.println("Valid date: " + day + "/" + month + "/" + year);
        } catch (InvalidDayException | InvalidMonthException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid integers for day, month, and year.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
