package problembefore;

import java.util.*;
public class Order {
    private final String customer;
    private final List<String> names = new ArrayList<>();
    private final List<Double> prices = new ArrayList<>();
    // NEW, PAID, PREPARING, DELIVERED or CANCELLED
    private String status = "NEW";
    private final SmsSender sms = new SmsSender();
    private final KitchenScreen kitchen = new KitchenScreen();

    public Order(String customer) { this.customer = customer; }

    public void addDish(String name, double price, boolean cheese, boolean spicy) {
        if (cheese) { name += " + cheese"; price += 5; }
        if (spicy) { name += " + spicy"; price += 2; }
        names.add(name);
        prices.add(price);
    }
    public void addCombo(String name, String[] dishes, double[] dishPrices) {
        double sum = 0;
        for (double p : dishPrices) sum += p;
        names.add(name + " [" + String.join(", ", dishes) + "]");
        prices.add(sum * 0.9);
    }
    public double total() {
        double t = 0;
        for (double p : prices) t += p;
        return t;
    }
    public void print() {
        for (int i = 0; i < names.size(); i++)
            System.out.printf("%s: %.2f MAD%n",
                    names.get(i), prices.get(i));
        System.out.printf("Total: %.2f MAD%n", total());
    }
    public void pay() {
        if (status.equals("NEW")) {
            status = "PAID";
            sms.send(customer, "order PAID");
            kitchen.show("new order for " + customer);
        } else {
            System.out.println("Cannot pay an order that is " + status);
        }
    }
    public void startPreparing() {
        if (status.equals("PAID")) {
            status = "PREPARING";
            sms.send(customer, "order PREPARING");
        } else {
            System.out.println("Cannot prepare an order that is " + status);
        }
    }
    public void deliver() {
        if (status.equals("PREPARING")) {
            status = "DELIVERED";
            sms.send(customer, "order DELIVERED");
        } else {
            System.out.println("Cannot deliver an order that is " + status);
        }
    }
    public void cancel() {
        if (status.equals("NEW") || status.equals("PAID")) {
            status = "CANCELLED";
            sms.send(customer, "order CANCELLED");
        } else {
            System.out.println("Cannot cancel an order that is " + status);
        }
    }
}
