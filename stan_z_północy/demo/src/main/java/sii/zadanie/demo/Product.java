package sii.zadanie.demo;

public class Product {

    private String name;
    private String description;
    private float price;
    private String currency;

    public Product() {
    }

    public Product(String name, String description, float price, String currency) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
    }


    public Product(String name, float price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public float getPrice() {
        return price;
    }


    public void setPrice(float price) {
        this.price = price;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
    return "Product{" +
           "name='" + name + '\'' +
           ", description='" + description + '\'' +
           ", currency='" + currency + '\'' +
           ", price=" + price +
           '}';
}
    

    
}
