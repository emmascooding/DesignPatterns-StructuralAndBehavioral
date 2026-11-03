package problemafter;
import java.util.*;
public class Order {
    private final String customer;
    private final List<MenuItem> items = new ArrayList<>();
    private final List<OrderListener> listeners = new ArrayList<>();
    private OrderState state = new NewState();

    public Order(String customer){
        this.customer = customer;
    }
    public String getCustomer(){
        return this.customer;
    }
    public String getStatus(){
        return this.state.name();
    }
    public void add(MenuItem item){
        items.add(item);
    }
    public void subscribe(OrderListener listener){
        listeners.add(listener);
    }
    public void unsubscribe(OrderListener listener){
        listeners.remove(listener);
    }
    public double total(){
        double t=0;
        for (MenuItem i : items){
            t += i.getPrice();
        }
        return t;
    }
    public void print(){
        for (MenuItem i : items){
            System.out.printf("%s: %.2f MAD%n", i.getItem(), i.getPrice());
        }
        System.out.printf("Total: %.2f MAD%n", total());
    }
    public void pay(){
        state.pay(this);
    }
    public void startPreparing(){
        state.startPreparing(this);
    }
    public void deliver(){
        state.deliver(this);
    }
    public void cancel(){
        state.cancel(this);
    }
    void setState(OrderState s) {
        state = s;
        for (OrderListener l : new ArrayList<>(listeners)) l.statusChanged(this);
    }
}
