package BookStoreGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class BookStoreGUITable extends JFrame {

    private JTextField searchField;
    private JComboBox<String> searchType;
    private JTable table;
    private DefaultTableModel tableModel;

    private java.util.List<Book> catalog;
    private java.util.List<Book> searchResults;
    private java.util.List<Book> cart;

    private String booksCatalog = "C:\\Users\\jeamp\\cse1325_Spring\\BookStoreGUI\\Books.txt";

    public BookStoreGUITable() {
        setTitle("Book Store");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        catalog = BookLoader.loadBooks(booksCatalog);
        searchResults = new ArrayList<>();
        cart = new ArrayList<>();

        //TOP PANEL
        JPanel topPanel = new JPanel();

        searchField = new JTextField(20);

        searchType = new JComboBox<>(
                new String[]{"Title/Author", "Price", "Price Range"}
        );

        JButton searchButton = new JButton("Search");

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchType);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        //TABLE SETUP
        tableModel = new DefaultTableModel(
                new String[]{"Title", "Author", "Price"}, 0
        );

        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true); // 🔥 enables click-to-sort

        add(new JScrollPane(table), BorderLayout.CENTER);

        //BOTTOM PANEL
        JPanel bottomPanel = new JPanel();
        JButton viewCartButton = new JButton("View Cart");
        bottomPanel.add(viewCartButton);

        JButton addToCartButton = new JButton("Add to Cart");
        JButton checkoutButton = new JButton("Checkout");

        bottomPanel.add(addToCartButton);
        bottomPanel.add(checkoutButton);

        add(bottomPanel, BorderLayout.SOUTH);

        //ACTIONS
        viewCartButton.addActionListener(e -> viewCart());
        searchButton.addActionListener(e -> searchBooks());
        addToCartButton.addActionListener(e -> addToCart());
        checkoutButton.addActionListener(e -> checkout());

        setVisible(true);
    }

    //SEARCH LOGIC
    private void searchBooks() {
        String input = searchField.getText().trim();
        String type = searchType.getSelectedItem().toString();

        if (input.isEmpty()) {
            clearTable();
            return;
        }

        searchResults.clear();

        try {

            // 🔹 TITLE / AUTHOR
            if (type.equals("Title/Author")) {
                String lower = input.toLowerCase();

                for (Book book : catalog) {
                    if (book.getTitle().toLowerCase().contains(lower) ||
                            book.getAuthor().toLowerCase().contains(lower)) {
                        searchResults.add(book);
                    }
                }
            }

            // 🔹 EXACT PRICE
            else if (type.equals("Price")) {
                double price = Double.parseDouble(input);

                for (Book book : catalog) {
                    if (book.getPrice() == price) {
                        searchResults.add(book);
                    }
                }
            }

            // 🔹 PRICE RANGE
            else if (type.equals("Price Range")) {
                String[] parts = input.split("-");

                if (parts.length != 2) return;

                double min = Double.parseDouble(parts[0].trim());
                double max = Double.parseDouble(parts[1].trim());

                for (Book book : catalog) {
                    if (book.getPrice() >= min && book.getPrice() <= max) {
                        searchResults.add(book);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
            return;
        }

        displayResults();
    }

    private void viewCart() {

        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(700, 400);
        cartFrame.setLayout(new BorderLayout());

        DefaultTableModel cartModel = new DefaultTableModel(
                new String[]{"Title", "Author", "Price"}, 0
        );

        JTable cartTable = new JTable(cartModel);
        cartTable.setAutoCreateRowSorter(true);

        //Load cart items into table
        for (Book book : cart) {
            cartModel.addRow(new Object[]{
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPrice()
            });
        }

        //Total label
        JLabel totalLabel = new JLabel();
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Helper to update total
        Runnable updateTotal = () -> {
            double total = 0;
            for (Book b : cart) {
                total += b.getPrice();
            }
            totalLabel.setText("Total: $" + String.format("%.2f", total));
        };

        updateTotal.run();

        //Remove Button
        JButton removeButton = new JButton("Remove Selected");

        removeButton.addActionListener(e -> {

            int selectedRow = cartTable.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(cartFrame, "Select a book to remove.");
                return;
            }

            // Handle sorting index conversion
            int modelRow = cartTable.convertRowIndexToModel(selectedRow);

            // Remove from actual cart list
            cart.remove(modelRow);

            // Remove from table
            cartModel.removeRow(modelRow);

            updateTotal.run();
        });

        //Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(removeButton);

        cartFrame.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        cartFrame.add(bottomPanel, BorderLayout.NORTH);
        cartFrame.add(totalLabel, BorderLayout.SOUTH);

        cartFrame.setVisible(true);
    }



    // DISPLAY RESULTS IN TABLE
    private void displayResults() {
        clearTable();

        for (Book book : searchResults) {
            tableModel.addRow(new Object[]{
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPrice()
            });
        }
        if (searchResults.isEmpty()) {
            clearTable();

            tableModel.addRow(new Object[]{
                    "No results found for your search", "", ""
            });

            return;
        }
    }

    // CLEAR TABLE
    private void clearTable() {
        tableModel.setRowCount(0);
    }

    // ADD TO CART (click row)
    private void addToCart() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a book first.");
            return;
        }

        // Convert row index if sorted
        int modelRow = table.convertRowIndexToModel(selectedRow);

        Book selected = searchResults.get(modelRow);
        cart.add(selected);

        JOptionPane.showMessageDialog(this,
                selected.getTitle() + " added to cart.");
    }

    // CHECKOUT
    private void checkout() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty.");
            return;
        }

        double total = 0;
        StringBuilder receipt = new StringBuilder("Checkout:\n\n");

        for (Book book : cart) {
            receipt.append(book).append("\n");
            total += book.getPrice();
        }

        receipt.append("\nTotal: $").append(String.format("%.2f", total));

        JOptionPane.showMessageDialog(this, receipt.toString());

        cart.clear();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookStoreGUITable::new);
    }
}