import java.util.ArrayList;
import java.util.List;

public class Player {
    //info where is a player
    private Room currentRoom;
    private int maximalWeightToCarry;

    // a list of items carried by the player
    private List<Item> carriedItems;



    public Player(int maximalWeightToCarry)
    {
        carriedItems = new ArrayList<>();
        this.maximalWeightToCarry = maximalWeightToCarry;
       //this.currentRoom = currentRoom;
        //i will implement it later
    }

    //setter method
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    //getter method
    public Room getCurrentRoom() {
        return currentRoom;
    }

    //this item must be available in the current room
    public void pickUpItem(Item item)
    {
        if(currentRoom.getItems().contains(item))
            carriedItems.add(item);
    }

   public void dropItem(Item item)
   {
       carriedItems.remove(item);
    }

    //player can carry items

    /**
     * prints out carried items && their total weight
     */
    public void items()
    {
        int totalWeightOfItems=0;
        for(Item item: carriedItems)
        {
            System.out.println("item\t");
            totalWeightOfItems += item.getWeight();
        }
        System.out.println("Total weight of items: " + totalWeightOfItems);
    }

    public void eatCookie()
    {
    maximalWeightToCarry+= 20;

    //how can I write it better??
    currentRoom.setThereCookie(false);
    }

}
