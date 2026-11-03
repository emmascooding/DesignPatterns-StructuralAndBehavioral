package problemafter;

public interface OrderState {
    String name();
    void pay(Order order);
    void startPreparing(Order order);
    void deliver(Order order);
    void cancel(Order order);
}
