package threadcount.nugget;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Item {
    public String name;
    public double value;

    public Item(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public static ArrayList<Item> listItems(){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Piccola", 5.0d));
        items.add(new Item("Jumbo", 10.0d));
        return items;
    }
}
