package BookStoreGUI;

import java.util.List;

public interface Searchable {
    public List<Book> searchByTitle(String title);
    public List<Book> searchByAuthor(String author);
    public List<Book> searchByPriceRange(double min, double max);
}