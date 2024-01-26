package org.example.commandLineInterface;

import org.example.dataAccesObjects.BookDao;
import org.example.model.Book;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


/**
 * Books Command Line Interface
 */
public class BooksCLI {

    private static final BookDao bookDao = new BookDao();

    static void updateBookDetails() {
        List<Book> allBooks = bookDao.getAllBooks();
        Scanner scanner = new Scanner(System.in);

        if (!allBooks.isEmpty()) {
            while (true) {
                printBookList(allBooks);
                System.out.println("Choose the Book by id");
                try {
                    int id = scanner.nextInt();
                    Book byId = bookDao.getById(id);
                    updatingDetailsOfBook(byId);
                    scanner.close();
                    return;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Currently there are no books in DB");
        }

    }

    static void listBooksByGenreOrAuthor() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("""
                    Would you like to list books by Genre or by Author
                    Write 'Author' or 1 - for author
                    Write 'Genre' or 2 - for genre
                    :
                    """);
            String operation = scanner.nextLine();
            switch (operation) {
                case "author", "1" -> {
                    listBooksByAuthor();
                    scanner.close();
                    return;
                }
                case "genre", "2" -> {
                    listBooksByGenre();
                    scanner.close();
                    return;
                }
                default -> System.out.println("WRONG OPERATION! try again");
            }

        } while (true);
    }

    static void printBookList(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void updatingDetailsOfBook(Book book) {
        Scanner scanner = new Scanner(System.in);

        if (book != null) {
            System.out.println("Do you want to change 'title' of the book \n(write - YES or 1 to accept and anything other to decline)");
            String operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "yes", "1" -> {
                    System.out.print("Please write the new title: ");
                    book.setTitle(scanner.nextLine());
                    System.out.println();
                }
                default -> System.out.println("the title is not changed ('" + book.getTitle() + "')");
            }
            System.out.println("Do you want to change 'author' of the book \n(write - YES or 1 to accept and anything other to decline)");
            operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "yes", "1" -> {
                    System.out.print("Please write the new author: ");
                    book.setAuthor(scanner.nextLine());
                    System.out.println();
                }
                default -> System.out.println("the author is not changed ('" + book.getAuthor() + "')");
            }
            System.out.println("Do you want to change 'genre' of the book \n(write - YES or 1 to accept and anything other to decline)");
            operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "yes", "1" -> {
                    System.out.print("Please write the new genre: ");
                    book.setGenre(scanner.nextLine());
                    System.out.println();
                }
                default -> System.out.println("the genre is not changed ('" + book.getGenre() + "')");
            }
            System.out.println("Do you want to change 'price' of the book \n(write - YES or 1 to accept and anything other to decline)");
            operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "yes", "1" -> {
                    System.out.print("Please write the new price(example 10.15 or 10): ");
                    try {
                        book.setPrice(BigDecimal.valueOf(scanner.nextDouble()));
                        System.out.println();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.out.println("the price is not changed");
                    }
                }
                default -> System.out.println("the price is not changed ('" + book.getPrice() + "')");

            }
            scanner.nextLine();
            System.out.println("Do you want to change 'quantity in stock' of the book \n(write - YES or 1 to accept and anything other to decline)");
            operation = scanner.nextLine().toLowerCase();
            switch (operation) {
                case "yes", "1" -> {
                    System.out.print("Please write the new 'quantity in stock'(example 10): ");
                    try {
                        book.setQuantityInStock(scanner.nextInt());
                        System.out.println();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.out.println("the 'quantity in stock' is not changed");
                    }
                }
                default -> System.out.println("the 'quantity in stock' is not changed ('" + book.getAuthor() + "')");
            }
            bookDao.update(book);
        } else {
            System.out.println("No Such Book in DB");
        }
        scanner.close();
    }

    private static void listBooksByGenre() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the name of Genre: ");
        String genre = scanner.nextLine();
        List<Book> byAuthor = bookDao.getByGenre(genre);
        if (byAuthor.isEmpty()) {
            System.out.println("No genre named '" + genre + "' have been found");
        } else {
            for (Book book : byAuthor) {
                System.out.println(book);
            }
        }
        scanner.close();
    }


    private static void listBooksByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the name of Author: ");
        String author = scanner.nextLine();
        List<Book> byAuthor = bookDao.getByAuthor(author);
        if (byAuthor.isEmpty()) {
            System.out.println("No author with name '" + author + "' have been found");
        } else {
            for (Book book : byAuthor) {
                System.out.println(book);
            }
        }
        scanner.close();
    }
}
