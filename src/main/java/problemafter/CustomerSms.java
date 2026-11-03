package problemafter;

public class CustomerSms implements OrderListener{
    public void statusChanged(Order order){
        System.out.println("[sms to " + order.getCustomer() + "] order "+ order.getStatus());
    }
}
