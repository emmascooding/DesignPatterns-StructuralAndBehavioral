package problemafter;

public class NewState extends AbstractState{
    public void pay(Order order){
        order.setState(new PaidState());
    }
    public void cancel(Order order){
        order.setState(new CancelledState());
    }
    public String name(){
        return "NEW";
    }
}
