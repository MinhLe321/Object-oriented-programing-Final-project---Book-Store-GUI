package BookStoreGUI;

import java.util.ArrayList;
import java.util.List;

public class BookStore implements Searchable{
    private List<Book> books;

    public BookStore() {
        books = new ArrayList<>();
    }
    
    public List<Book> searchByTitle(String title){
         List<Book> abooks = new ArrayList<>();
        if(title == null || title.trim().isEmpty()){
            return null;
        }
        title = title.trim();
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getTitle().equalsIgnoreCase(title)){
                abooks.add(book);
            }
        }
        return abooks;
    }

     public List<Book> searchByAuthor(String author){
        List<Book> abooks = new ArrayList<>();
        if(author == null || author.trim().isEmpty()){
            return abooks;
        }
         author = author.trim();

        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getAuthor().equalsIgnoreCase(author)){
                 abooks.add(book);
            }
        }

        return abooks;
    }

    public void addBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book cannot be null");
        }
        books.add(book);
    }

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
