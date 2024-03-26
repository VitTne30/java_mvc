package Model;

public class ModelBook {

    private String name, author, publisher, category;
    private int id, number, price;

    public ModelBook(String name, String author, int id, String category, int number, int price, String publisher) {
        this.name = name;
        this.author = author;
        this.id = id;
        this.category = category;
        this.number = number;
        this.price = price;
        this.publisher = publisher;
    }

    public ModelBook() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
