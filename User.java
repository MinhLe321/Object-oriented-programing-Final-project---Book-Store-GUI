package BookStoreGUI;

import java.util.List;

public class User {
    private String name;
    private ShoppingCart cart;

    public User(){
        this.name = "Unknown";
        cart = new ShoppingCart();
    }

    public User(String name){
        if(name == null || name.trim().isEmpty()){
            this.name = "Unknown";
        }else{
            this.name = name;
        }
        cart = new ShoppingCart();
    }

    // Class Accessors
    public List<Book> getCartItems() {
        return cart.getCartItems();
    }

    public double checkout(){
        return cart.checkout();
    }

    public String getName() {
        return name;
    }

    // Class Mutators
    public void addToCart(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book cannot be null");
        }
        cart.addBook(book);
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            this.name = "Unknown";
        }else{
            this.name = name;
        }
    }
    
    public void removeBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book cannot be null");
        }
        cart.removeBook(book);
    }

    

}
