// CreativeWebOrderSystem.java
// Author: Alex Ellis
// Module 3: Logging added for troubleshooting and alerts

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

// Class representing a customer order
class Order {
    private int orderId;
    private String customerName;
    private String projectType;
    private String status;

    public Order(int orderId, String customerName, String projectType) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.projectType = projectType;
        this.status = "Pending"; 
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getProjectType() { return projectType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Project Type: " + projectType);
        System.out.println("Status: " + status);
        System.out.println("----------------------");
    }
}

// Main application class
public class CreativeWebOrderSystem {

    private static ArrayList<Order> orders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Get logger
    private static Logger logger = AppLogger.getLogger();

    public static void main(String[] args) {
        boolean running = true;
        int nextOrderId = 1;

        logger.info("Application started.");

        while (running) {
            System.out.println("CreativeWeb Order System");
            System.out.println("1. Create Order");
            System.out.println("2. View Orders");
            System.out.println("3. Update Order Status");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter project type: ");
                    String project = scanner.nextLine();

                    Order newOrder = new Order(nextOrderId, name, project);
                    orders.add(newOrder);

                    logger.info("New order created with ID: " + nextOrderId);

                    System.out.println("Order created with ID: " + nextOrderId);
                    nextOrderId++;
                    break;

                case 2:
                    for (Order order : orders) {
                        order.displayOrder();
                    }
                    break;

                case 3:
                    System.out.print("Enter order ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Order orderToUpdate = null;
                    for (Order o : orders) {
                        if (o.getOrderId() == id) {
                            orderToUpdate = o;
                            break;
                        }
                    }

                    if (orderToUpdate != null) {
                        System.out.print("Enter new status: ");
                        String newStatus = scanner.nextLine();
                        orderToUpdate.setStatus(newStatus);
                        logger.info("Order " + id + " status updated to: " + newStatus);
                        System.out.println("Order updated successfully.");
                    } else {
                        logger.warning("Attempted update failed. Order ID not found: " + id);
                        System.out.println("Order ID not found.");
                    }
                    break;

                case 4:
                    running = false;
                    logger.info("Application closed by user.");
                    System.out.println("Exiting program.");
                    break;

                default:
                    logger.warning("User entered invalid menu option.");
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}

