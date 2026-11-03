package problemafter;

public class Cheese extends ExtraDecorator{
    public Cheese(MenuItem item){
        super(item);
    }
    public String getItem(){
        return inner.getItem() + " + cheese";
    }
    public double getPrice(){
        return inner.getPrice()+5.0;
    }
}
