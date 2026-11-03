package problemafter;

public class Test {
    public static void main(String[] args) {
        Order order = new Order("SAFI Amal");
        order.subscribe(new CustomerSms());
        order.subscribe(new KitchenScreen());

        order.add(new Spicy(new Cheese(new Dish("Tacos", 45))));
        order.add(new Combo("Student menu")
                .add(new Cheese(new Dish("Burger", 40)))
                .add(new Dish("Fries", 15))
                .add(new Dish("Soda", 10)));
        order.print();
        order.pay();
        order.startPreparing();
        order.cancel();
        order.deliver();
        order.pay();
    }
}
