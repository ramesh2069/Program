import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DataStructureOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose a data structure:");
        System.out.println("1. Stack");
        System.out.println("2. Queue");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = sc.nextInt();

        if (choice == 1) {
            handleStackOperations(sc);
        } else if (choice == 2) {
            handleQueueOperations(sc);
        } else {
            System.out.println("Invalid choice. Program will now exit.");
        }

        sc.close();
    }

    public static void handleStackOperations(Scanner sc) {
        Stack<Integer> stack = new Stack<>();
        int choice;

        System.out.println("Stack Operations:");
        System.out.println("1. Push an element");
        System.out.println("2. Pop an element");
        System.out.println("3. View the top element");
        System.out.println("4. Check if the stack is empty");
        System.out.println("5. Display all elements in the stack");
        System.out.println("0. Exit stack operations");

        do {
            System.out.print("Enter your choice for stack: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter a number to push: ");
                    int element = sc.nextInt();
                    stack.push(element);
                    System.out.println("Added: " + element);
                    break;
                case 2:
                    if (!stack.isEmpty()) {
                        System.out.println("Removed: " + stack.pop());
                    } else {
                        System.out.println("The stack is empty.");
                    }
                    break;
                case 3:
                    if (!stack.isEmpty()) {
                        System.out.println("Top element: " + stack.peek());
                    } else {
                        System.out.println("The stack is empty.");
                    }
                    break;
                case 4:
                    System.out.println(stack.isEmpty() ? "The stack is empty." : "The stack is not empty.");
                    break;
                case 5:
                    System.out.println("Stack contents: " + stack);
                    break;
                case 0:
                    System.out.println("Exiting stack operations.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    public static void handleQueueOperations(Scanner sc) {
        Queue<Integer> queue = new LinkedList<>();
        int choice;

        System.out.println("Queue Operations:");
        System.out.println("1. Add an element");
        System.out.println("2. Remove an element");
        System.out.println("3. View the front element");
        System.out.println("4. Check if the queue is empty");
        System.out.println("5. Display all elements in the queue");
        System.out.println("0. Exit queue operations");

        do {
            System.out.print("Enter your choice for queue: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter a number to add: ");
                    int element = sc.nextInt();
                    queue.offer(element);
                    System.out.println("Added: " + element);
                    break;
                case 2:
                    if (!queue.isEmpty()) {
                        System.out.println("Removed: " + queue.poll());
                    } else {
                        System.out.println("The queue is empty.");
                    }
                    break;
                case 3:
                    if (!queue.isEmpty()) {
                        System.out.println("Front element: " + queue.peek());
                    } else {
                        System.out.println("The queue is empty.");
                    }
                    break;
                case 4:
                    System.out.println(queue.isEmpty() ? "The queue is empty." : "The queue is not empty.");
                    break;
                case 5:
                    System.out.println("Queue contents: " + queue);
                    break;
                case 0:
                    System.out.println("Exiting queue operations.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }
}
