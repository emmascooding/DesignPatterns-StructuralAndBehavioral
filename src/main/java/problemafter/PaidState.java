package problemafter;

public class PaidState extends AbstractState{
    public void startPreparing(Order order){
        order.setState(new PreparingState());
    }
    public void cancel(Order order){
        order.setState(new CancelledState());
    }
    public String name(){
        return "PAID";
    }
}
