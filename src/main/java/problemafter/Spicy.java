package problemafter;

public class Spicy extends ExtraDecorator{
    public Spicy(MenuItem item){
        super(item);
    }
    public String getItem(){
        return inner.getItem()+" + spicy sauce";
    }
    public double getPrice(){
        return inner.getPrice()+2.0;
    }
}
