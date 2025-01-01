import java.util.Scanner;

class MaximumFinder {
    public static int getMaxValue(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}

class MaxValueFromSets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of sets: ");
        int setsCount = getValidInt(sc);

        int[][] allValues = new int[setsCount][];
        int[] maxValues = new int[setsCount];

        for (int i = 0; i < setsCount; i++) {
            System.out.print("Enter the number of values in set " + (i + 1) + ": ");
            int valuesCount = getValidInt(sc);

            int[] values = new int[valuesCount];
            System.out.print("Enter values for set " + (i + 1) + ": ");
            for (int j = 0; j < valuesCount; j++) {
                values[j] = getValidInt(sc);
            }

            allValues[i] = values;
            maxValues[i] = MaximumFinder.getMaxValue(values);
        }

        for (int i = 0; i < setsCount; i++) {
            System.out.print("Values in set " + (i + 1) + ": {");
            int[] values = allValues[i];
            for (int j = 0; j < values.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(values[j]);
            }
            System.out.println("}");

            System.out.println("Maximum value in set " + (i + 1) + ": " + maxValues[i]);
        }

        sc.close();
    }

    private static int getValidInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.print("Invalid input! Please enter a valid integer: ");
                sc.nextLine();
            }
        }
    }
}
