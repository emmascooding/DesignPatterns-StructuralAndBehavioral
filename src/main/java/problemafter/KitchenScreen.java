package problemafter;

public class KitchenScreen implements OrderListener{
    public void statusChanged(Order order){
        if (order.getStatus().equals("PAID")){
            System.out.println("[kitchen] new order for " + order.getCustomer());
        }
    }
}
