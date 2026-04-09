package BookStoreGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BookStoreGUI extends JFrame {

    private BookStore store;
    private User user;

    private JTextField searchField;
    private JTextArea resultsArea;
    private JTextArea cartArea;

    private JButton searchTitleBtn;
    private JButton searchAuthorBtn;
    private JButton addToCartBtn;
    private JButton viewCartBtn;
    private JButton checkoutBtn;

    private List<Book> lastSearchResults;

    public BookStoreGUI() {
        store = new BookStore();
        user = new User("Guest");

        // Sample Data (IMPORTANT for testing)
        store.addBook(new Book("Java Basics", "John Doe", 19.99));
        store.addBook(new Book("Data Structures", "Jane Smith", 29.99));
        store.addBook(new Book("Algorithms", "John Doe", 39.99));

        setTitle("Book Store");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel (Search)
        JPanel topPanel = new JPanel();
        searchField = new JTextField(20);

        searchTitleBtn = new JButton("Search by Title");
        searchAuthorBtn = new JButton("Search by Author");

        topPanel.add(searchField);
        topPanel.add(searchTitleBtn);
        topPanel.add(searchAuthorBtn);

        add(topPanel, BorderLayout.NORTH);

        // Center (Results)
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        // Bottom Panel (Actions)
        JPanel bottomPanel = new JPanel();

        addToCartBtn = new JButton("Add to Cart");
        viewCartBtn = new JButton("View Cart");
        checkoutBtn = new JButton("Checkout");

        bottomPanel.add(addToCartBtn);
        bottomPanel.add(viewCartBtn);
        bottomPanel.add(checkoutBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Side Panel (Cart Display)
        cartArea = new JTextArea(10, 15);
        cartArea.setEditable(false);
        add(new JScrollPane(cartArea), BorderLayout.EAST);

        // Button Actions
        setupActions();
    }

    private void setupActions() {

        // Search by Title
        searchTitleBtn.addActionListener(e -> {
            String input = searchField.getText();
            lastSearchResults = store.searchByTitle(input);

            displayResults(lastSearchResults);
        });

        // Search by Author
        searchAuthorBtn.addActionListener(e -> {
            String input = searchField.getText();
            lastSearchResults = store.searchByAuthor(input);

            displayResults(lastSearchResults);
        });

        // Add to Cart (adds FIRST result for simplicity)
        addToCartBtn.addActionListener(e -> {
            if (lastSearchResults == null || lastSearchResults.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No book to add.");
                return;
            }

            Book selected = lastSearchResults.get(0);
            user.addToCart(selected);

            JOptionPane.showMessageDialog(this, "Added to cart: " + selected.getTitle());
        });

        // View Cart
        viewCartBtn.addActionListener(e -> {
            List<Book> cartItems = user.getCartItems();

            StringBuilder sb = new StringBuilder();
            for (Book b : cartItems) {
                sb.append(b.toString()).append("\n");
            }

            cartArea.setText(sb.toString());
        });

        // Checkout
        checkoutBtn.addActionListener(e -> {
            double total = user.checkout();

            JOptionPane.showMessageDialog(this, "Total: $" + total);
            cartArea.setText("");
        });
    }

    private void displayResults(List<Book> books) {
        if (books == null || books.isEmpty()) {
            resultsArea.setText("No results found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Book b : books) {
            sb.append(b.toString()).append("\n");
        }

        resultsArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BookStoreGUI().setVisible(true);
        });
    }
}