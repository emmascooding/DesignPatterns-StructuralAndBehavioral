package problembefore;

public class SmsSender {
    public void send(String customer, String message){
        System.out.println("[sms to "+ customer + "] " + message);
    }
}
