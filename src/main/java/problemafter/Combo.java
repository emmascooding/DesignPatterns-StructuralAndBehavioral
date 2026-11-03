package problemafter;

import java.util.ArrayList;

public class Combo implements MenuItem{
    private final String name;
    private final ArrayList<MenuItem> combo = new ArrayList<>();
    public Combo(String name){
        this.name = name;
    }
    public Combo add(MenuItem item){
        combo.add(item);
        return this;
    }
    public String getItem(){
        return this.name;
    }
    public double getPrice(){
        double total = 0;
        for (MenuItem item : combo){
            total += item.getPrice();
        }
        return total;
    }
}
