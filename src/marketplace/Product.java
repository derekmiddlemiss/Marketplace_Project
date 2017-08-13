package marketplace;

public class Product {

    private String identifier;
    private Double price;
    private String description;

    public Product( String identifier, Double price, String description ){
        this.identifier = identifier;
        this.price = price;
        this.description = description;
    }

    public String getIdentifier(){
        return this.identifier;
    }

    public Double getPrice(){
        return this.price;
    }

    public String getDescription(){
        return this.description;
    }

}
