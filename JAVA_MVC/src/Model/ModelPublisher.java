package Model;

public class ModelPublisher {
    private int id_publisher;
    private String name_publisher;

    public int getId_publisher() {
        return id_publisher;
    }

    public void setId_publisher(int id_publisher) {
        this.id_publisher = id_publisher;
    }

    public String getName_publisher() {
        return name_publisher;
    }

    public void setName_publisher(String name_publisher) {
        this.name_publisher = name_publisher;
    }

    public ModelPublisher() {
    }

    public ModelPublisher(int id_publisher, String name_publisher) {
        this.id_publisher = id_publisher;
        this.name_publisher = name_publisher;
    }
}
