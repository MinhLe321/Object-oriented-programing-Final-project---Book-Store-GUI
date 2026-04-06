package BookStoreGUI;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Book> cartItems;

    public ShoppingCart(){
        cartItems = new ArrayList<>();
    }

    // This method adds a book to the shopping cart List
    public void addBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book cannot be null");
        }
        cartItems.add(book);
    }

    // This method removes a book from the shopping cart list
    public void removeBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book cannot be null");
        }
        cartItems.remove(book);
    }

    // Class Accessors
    public double getTotal(){
        double total = 0;
        for(int i = 0; i < cartItems.size(); i++){
            Book book = cartItems.get(i);
            total += book.getPrice();
        }
        return total;
    }

    public List<Book> getCartItems() {
        return cartItems;
    }

    // This method handles checkout logic
    public double checkout(){
        double total = getTotal();
        cartItems.clear();
        return total;
    }
    
}
