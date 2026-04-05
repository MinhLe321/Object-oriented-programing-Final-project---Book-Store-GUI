package BookStoreGUI;

import java.util.List;

public class BookStore  extends Book implements searchable{
    private List<Book> books;

    
    public Book searchByTitle(String title){
        if(title == null || title.equals(" ") || title.isEmpty()){
            return null;
        }
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }

     public List<Book> searchByAuthor(String author){
        List<Book> Ebooks = List.of();
        if( author== null || author.equals(" ") || author.isEmpty()){
            return null;
        }
        for(int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getAuthor().equals(author)){
                 Ebooks.add(book);
            }
        }

        return Ebooks;
    }
}
