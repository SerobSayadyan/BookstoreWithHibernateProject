package org.example.commandLineInterface;

import org.example.dataAccesObjects.CustomerDao;
import org.example.model.Customer;
import org.example.util.Validation;

import java.util.List;
import java.util.Scanner;


/**
 * Customer Command Line Interface
 */
public class CustomersCLI {

    private static final CustomerDao customerDao = new CustomerDao();

    static void updateCustomerInformation() {
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Scanner scanner = new Scanner(System.in);

        if (!allCustomers.isEmpty()) {
            while (true) {
                printCustomerList(allCustomers);
                System.out.println("Choose the Customer by id");
                try {
                    int id = scanner.nextInt();
                    Customer byId = customerDao.getById(id);
                    updatingDetailsOfCustomer(byId);
                    scanner.close();
                    return;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Currently there are no customers in DB");
        }

    }

    static void printCustomerList(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }


    private static void updatingDetailsOfCustomer(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        if (customer != null) {
            System.out.println("Do you want to change 'name' of the customer \n(write - YES or 1 to accept and anything other to decline)");
            String operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "yes", "1" -> {
                    System.out.print("Please write the new name: ");
                    customer.setName(scanner.nextLine());
                    System.out.println();
                }
                default -> System.out.println("the name is not changed ('" + customer.getName() + "')");
            }
            System.out.println("Do you want to change 'email' of the customer \n(write - YES or 1 to accept and anything other to decline)");
            operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "yes", "1" -> {
                    do {
                        System.out.print("Please write the new email: ");
                        String email = scanner.nextLine();
                        System.out.println();
                        if (Validation.isEmailValid(email)) {
                            customer.setEmail(email);
                            break;
                        } else {
                            System.out.println("The Email is not valid. Please try again!");
                        }
                    } while (true);
                }
                default -> System.out.println("the email is not changed ('" + customer.getEmail() + "')");
            }
            System.out.println("Do you want to change 'phone' of the customer \n(write - YES or 1 to accept and anything other to decline");
            operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "yes", "1" -> {
                    do {
                        System.out.print("Please write the new phone: ");
                        String phone = scanner.nextLine();
                        System.out.println();
                        if (Validation.isPhoneNumberValid(phone)) {
                            customer.setPhone(phone);
                            break;
                        } else {
                            System.out.println("The Phone number is not valid. Please try again!");
                        }
                    } while (true);
                }
                default -> System.out.println("the phone is not changed ('" + customer.getPhone() + "')");
            }
            customerDao.update(customer);
        } else {
            System.out.println("No Such Customer in DB");
        }
        scanner.close();
    }

}
