// CreativeWebOrderSystem.java
// Author: Alex Ellis
// Module 5: Database integration using SQLite

import java.util.Scanner;
import java.util.logging.Logger;

public class CreativeWebOrderSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger = AppLogger.getLogger();

    public static void main(String[] args) {

        if (!DatabaseManager.initializeDatabase()) {
            System.out.println("Database initialization failed. Exiting.");
            return;
        }

        logger.info("Application started with database.");

        boolean running = true;

        while (running) {
            System.out.println("\nCreativeWeb Order System");
            System.out.println("1. Create Order");
            System.out.println("2. View Orders Report");
            System.out.println("3. Update Order Status");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter customer name: ");
                    String customer = scanner.nextLine();
                    System.out.print("Enter project type: ");
                    String project = scanner.nextLine();

                    if (DatabaseManager.insertOrder(customer, project, "Pending")) {
                        System.out.println("Order created successfully.");
                        logger.info("New order created: " + customer + ", " + project);
                    } else {
                        System.out.println("Error inserting order.");
                        logger.warning("Failed to insert order: " + customer + ", " + project);
                    }
                    break;

                case 2:
                    DatabaseManager.printOrdersReport();
                    logger.info("Orders report generated.");
                    break;

                case 3:
                    System.out.print("Enter Order ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new status: ");
                    String status = scanner.nextLine();

                    if (DatabaseManager.updateOrderStatus(id, status)) {
                        System.out.println("Order updated successfully.");
                        logger.info("Order status updated for ID: " + id);
                    } else {
                        System.out.println("Error updating order.");
                        logger.warning("Failed to update order ID: " + id);
                    }
                    break;

                case 4:
                    running = false;
                    logger.info("Application closed.");
                    System.out.println("Goodbye.");
                    break;

                default:
                    logger.warning("Invalid menu option selected.");
                    System.out.println("Invalid option.");
            }
        }
    }
}


