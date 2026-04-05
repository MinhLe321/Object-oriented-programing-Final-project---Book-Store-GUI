package BookStoreGUI;

import java.util.List;

public interface searchable {
    public Book searchByTitle(String title);
    public List<Book> searchByAuthor(String author);
}