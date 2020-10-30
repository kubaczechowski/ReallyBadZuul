import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private String description;
    private int weight;
    private boolean canBePickedUp; // the default value is false

    public Item(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    //note that it returns a description of only one item
    public String getDescription() {
        return description;
    }
    public int getWeight() {
        return weight;
    }
    public String getName(){ return name;}




}
