package BookStoreGUI;

import java.util.ArrayList;
import java.util.List;

public class BookStore implements Searchable{
    private List<Book> books;

    public BookStore() {
        books = new ArrayList<>();
    }

    // This method search for books based on the title.
    public List<Book> searchByTitle(String title){
         List<Book> abooks = new ArrayList<>();
        if(title == null || title.trim().isEmpty()){
            return null;
        }
        title = title.trim();
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                abooks.add(book);
            }
        }
        return abooks;
    }

    // This method searches for books based on given author name.
     public List<Book> searchByAuthor(String author){
        List<Book> abooks = new ArrayList<>();
        if(author == null || author.trim().isEmpty()){
            return abooks;
        }
         author = author.trim();

        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getAuthor().toLowerCase().contains(author.toLowerCase())){
                 abooks.add(book);
            }
        }

        return abooks;
    }
    // This method allows the User to search by the Price of the book.
    public List<Book> searchByPriceRange(double min, double max){
        List<Book> results = new ArrayList<>();

        for(Book book : books){
            double p = book.getPrice();

            if(p >= min && p <= max){
                results.add(book);
            }
        }

        return results;
    }

    // This book adds a book back to the Store
    public void addBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book cannot be null");
        }
        books.add(book);
    }

    // This method checks to make sure a book is available
    public boolean isAvailable(String title){
        if(title == null || title.trim().isEmpty()){
            return false;
        }
        title = title.trim();
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getTitle().equalsIgnoreCase(title)){
                return true;
            }
        }
        return false;
    }

}
