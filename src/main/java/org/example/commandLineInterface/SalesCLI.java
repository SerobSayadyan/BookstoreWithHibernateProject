package org.example.commandLineInterface;

import org.example.dataAccesObjects.BookDao;
import org.example.dataAccesObjects.CustomerDao;
import org.example.dataAccesObjects.SaleDao;
import org.example.model.Book;
import org.example.model.Customer;
import org.example.model.Sale;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class SalesCLI {

    private static final SaleDao salesDao = new SaleDao();
    private static final BookDao bookDao = new BookDao();
    private static final CustomerDao customerDao = new CustomerDao();

    static void processNewSale() {
        Scanner scanner = new Scanner(System.in);
        List<Customer> allCustomers = customerDao.getAllCustomers();
        List<Book> allBooks = bookDao.getAllBooks();
        if (allCustomers.isEmpty()) {
            System.out.println("Currently there are no customers, at first please add some customer");
            return;
        }
        if (allBooks.isEmpty()) {
            System.out.println("Currently there are no books in shop, at first please add some books");
            return;
        }

        Customer customer;
        Book book;
        Date date;

        CustomersCLI.printCustomerList(allCustomers);
        do {
            System.out.print("Please select the Customer by id: ");
            try {
                int id = scanner.nextInt();
                System.out.println();
                customer = customerDao.getById(id);
                if (customer == null) {
                    System.out.println("No such customer!!! Try again");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } while (true);

        BooksCLI.printBookList(allBooks);
        do {
            System.out.print("Please select the Book by id: ");
            try {
                int id = scanner.nextInt();
                System.out.println();
                book = bookDao.getById(id);
                if (book == null) {
                    System.out.println("No such book!!! Try again");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } while (true);

        date = new Date(new java.util.Date().getTime());

        Sale sale = Sale.builder()
                .book(book)
                .customer(customer)
                .dateOfSale(date)
                .totalPrice(book.getPrice())
                .build();

        salesDao.save(sale);
        System.out.println("new Sale: " + sale);
        scanner.close();

    }


    public static void viewCustomerPurchaseHistory() {
        List<Customer> customers = customerDao.getAllCustomers();
        Scanner scanner = new Scanner(System.in);
        Customer customer = null;
        if (!customers.isEmpty()) {
            while (true) {
                CustomersCLI.printCustomerList(customers);
                System.out.println("Choose the Customer by id");
                try {
                    int id = scanner.nextInt();
                    customer = customerDao.getById(id);
                    break;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Currently there are no customers in DB");
            return;
        }
        List<Sale> sales = salesDao.getByCustomer(customer);
        System.out.println("\nHere is the purchase history of customer - name: '" + customer.getName() +
                "' email: " + customer.getEmail());
        printSalesList(sales);
        scanner.close();
    }

    static void printSalesList(List<Sale> sales) {
        for (Sale sale : sales) {
            System.out.println(sale);
        }

    }
}
