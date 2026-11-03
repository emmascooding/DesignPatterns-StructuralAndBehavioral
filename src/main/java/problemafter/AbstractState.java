package problemafter;

public abstract class AbstractState implements OrderState{
    public void pay(Order order){
        refuse("pay");
    }
    public void startPreparing(Order order){
        refuse("prepare");
    }
    public void deliver(Order order){
        refuse("deliver");
    }
    public void cancel(Order order){
        refuse("cancel");
    }
    private void refuse(String action){
        System.out.println("Cannot " + action + " an order that is "+ name());
    }
}
