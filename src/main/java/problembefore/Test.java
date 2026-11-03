package problembefore;

public class Test {
    public static void main(String[] args) {
        Order order = new Order("SAFI Amal");
        order.addDish("Tacos", 45, true, true);
        order.addCombo("Student menu", new String[] {"Burger + cheese", "Fries", "Soda"}, new double[] {45, 15, 10});
        order.print();
        order.pay();
        order.startPreparing();
        order.cancel();
        order.deliver();
        order.pay();
    }
}

