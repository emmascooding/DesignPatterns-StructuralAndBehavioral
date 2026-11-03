package problemafter;

public class Dish implements MenuItem{
    private final String name;
    private final double price;

    public Dish(String name, double price){
        this.name=name;
        this.price = price;
    }
    public String getItem(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
}
