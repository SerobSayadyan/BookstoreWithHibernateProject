package org.example.commandLineInterface;

import org.example.dataAccesObjects.CustomerDao;
import org.example.dataAccesObjects.SaleDao;

import java.util.Scanner;

public class MainCommandLineInterface {

    private MainCommandLineInterface() {}

    private static final CustomerDao customerDao = new CustomerDao();
    private static final SaleDao saleDao = new SaleDao();

    public static void start() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("""
                    1. Update Book Details
                    2. List Books by Genre or Author
                    3. Update Customer Information
                    4. View Customer's Purchase History
                    5. Process New Sale
                    0. Exit
                    """);

            System.out.print("\nEnter your choice: ");
            String operation = scanner.nextLine();
            System.out.println();

            switch (operation) {
                case "1" -> BooksCLI.updateBookDetails();
                case "2" -> BooksCLI.listBooksByGenreOrAuthor();
                case "3" -> CustomersCLI.updateCustomerInformation();
                case "4" -> SalesCLI.viewCustomerPurchaseHistory();
                case "5" -> SalesCLI.processNewSale();
                case "0" -> {
                    System.out.println("Exiting Application Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice ('" + operation + "' is not a valid operator). Please enter a valid option.");
            }
        }
    }



}
