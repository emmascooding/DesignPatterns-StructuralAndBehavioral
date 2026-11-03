package problemafter;

public class PreparingState extends AbstractState{
    public void deliver(Order order){
        order.setState(new DeliveredState());
    }
    public String name(){
        return "PREPARING";
    }
}
