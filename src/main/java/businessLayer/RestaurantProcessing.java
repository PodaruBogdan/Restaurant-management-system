package businessLayer;

import java.util.ArrayList;

public interface RestaurantProcessing {
    /**
     * Adds new item to the menu collection.
     * @pre item != null
     * @pre !menuContains(item)
     * @post @result {@literal <=>} !menuContains(item)
     * @param item
     * @return boolean
     */
    boolean createMenuItem(MenuItem item);
    /**
     * Removes the item from the menu collection.
     * @pre item!=null
     * @post menuContains(item) == false
     * @param item
     */
    void deleteMenuItem(MenuItem item);
    /**
     * Replace the item with newItem in the collection.
     * @pre item != null {@literal &&} newItem != null
     * @post menuContains(item) == false {@literal &&} menuContains(newItem)==true
     * @param item
     * @param newItem
     */
    void editMenuItem(MenuItem item,MenuItem newItem);
    /**
     * Creates and add new order to the current map of orders.
     * @pre o != null {@literal &&} list.size() {@literal >} 0
     * @post ordersContains(o,list) == true
     * @param o
     * @param list
     */
    void createOrder(Order o, ArrayList<MenuItem> list);
    /**
     * @pre list.size() {@literal >} 0
     * @post @nochange
     * @param list
     * @return float
     */
    float computeOrderTotal(ArrayList<MenuItem> list);
    /**
     * Generates a .txt file with information extracted from arguments.
     * @pre list.size() {@literal >} 0 {@literal &&} o != null {@literal &&} ordersContains(o,list) == true
     * @post @nochange
     * @param list
     */
    void generateBill(Order o, ArrayList<MenuItem> list);
    /**
     * Checks if the menu already contains the item to be added.
     * @pre item != null
     * @post @nochange
     * @param item
     * @return boolean
     */
    boolean menuContains(MenuItem item);
    /**
     * Checks if orders map already contains the map.entry {@literal <o,list>}.
     * @pre item != null {@literal &&} list.size() {@literal >} 0
     * @post @nochange
     * @param o
     * @param list
     * @return boolean
     */
    boolean ordersContains(Order o,ArrayList<MenuItem> list);
}
