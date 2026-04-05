package BookStoreGUI;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(){
        this.title = "Unknown";
        this.author = "Unknown";
        this.price = 0.0;
    }

    public Book(String title, String author, double price){
        if(title == null || title.trim().isEmpty()){
            this.title = "Unknown";
        }else{
            this.title = title;
        }
        if(author == null || author.trim().isEmpty()){
            this.author = "Unknown";
        }else{
           this.author = author; 
        }

        if(price < 0){
            this.price = 0.0;
        }else{
             this.price = price;
        }
       
    }

    // Class Accessors
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    // Class Mutators
    public void setTitle(String title) {
         if(title == null || title.equals(" ") || title.isEmpty()){
            this.title = "Unknown";
        }else{
            this.title = title;
        }
    }

    public void setAuthor(String author) {
       if(author == null || author.equals(" ") || author.isEmpty()){
            this.author = "Unknown";
        }else{
           this.author = author; 
        }
    }

    public void setPrice(double price) {
        if(price < 0){
            this.price = 0.0;
            return;
        }else{
             this.price = price;
        }
    }

    public String toString(){
         return "Title: " + title + " | Author: " + author + " | Price: $" + price;
    }
}
