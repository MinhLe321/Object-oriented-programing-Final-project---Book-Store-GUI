package BookStoreGUI;
import java.io.*;
import java.util.*;

public class BookLoader {

    public static List<Book> loadBooks(String fileName) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {

                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                // Validate format
                if (parts.length != 3) continue;

                String title = parts[0].trim();
                String author = parts[1].trim();
                double price;

                try {
                    price = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException e) {
                    continue; // skip invalid price
                }

                books.add(new Book(title, author, price));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return books;
    }
}