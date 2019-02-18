package dto;

public class Commodity implements Identifiable {

    private String name;
    private int price;
    private int id;

    public Commodity(String name, int price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Commodity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
