package businessLayer;

import dataLayer.FileWriter;
import dataLayer.RestaurantSerializator;

import javax.swing.*;
import java.util.*;

/**
 * @invariant isWellFormed()
 */
public class Restaurant extends Observable implements RestaurantProcessing {

    Map<Order, ArrayList<MenuItem>> orders;
    Set<MenuItem> menu;

    public Restaurant(){
        orders=new HashMap<Order,ArrayList<MenuItem>>();
        menu=(HashSet<MenuItem>) RestaurantSerializator.deserialize();
        assert isWellFormed();
    }
    /**
     * Adds new item to the menu collection.
     * @pre item != null
     * @pre !menuContains(item)
     * @post @result {@literal <=>} !menuContains(item)
     * @param item
     * @return boolean
     */
    public boolean createMenuItem(MenuItem item) {
        assert item!=null;
        assert !menuContains(item);
        assert isWellFormed();
        if(!menuContains(item)) {
            menu.add(item);
        }else {
            JOptionPane.showMessageDialog(null,"This item already exists!");
            assert isWellFormed();
            assert menuContains(item);
            return false;
        }
        assert isWellFormed();
        return true;
    }
    /**
     * Removes the item from the menu collection.
     * @pre item!=null
     * @post menuContains(item) == false
     * @param item
     */
    public void deleteMenuItem(MenuItem item) {
        assert item!=null;
        assert isWellFormed();
        menu.remove(item);
        assert isWellFormed();
        assert !menuContains(item);
    }
    /**
     * Replace the item with newItem in the collection.
     * @pre item != null {@literal &&} newItem != null
     * @post menuContains(item) == false {@literal &&} menuContains(newItem)==true
     * @param item
     * @param newItem
     */
    public void editMenuItem(MenuItem item,MenuItem newItem) {
        assert item!=null && newItem !=null;
        assert isWellFormed();
        deleteMenuItem(item);
        createMenuItem(newItem);
        assert isWellFormed();
        assert menuContains(item)==false && menuContains(newItem)==true;
    }
    /**
     * Creates and add new order to the current map of orders.
     * @pre o != null {@literal &&} list.size() {@literal >} 0
     * @post ordersContains(o,list) == true
     * @param o
     * @param list
     */
    public void createOrder(Order o,ArrayList<MenuItem> list) {
        assert o!=null && list.size()>0;
        assert isWellFormed();
        orders.put(o,list);
        for(int i=0;i<list.size();i++){
            if(list.get(i) instanceof CompositeProduct){
                this.setChanged();
                this.notifyObservers(list.get(i));
            }
        }
        assert isWellFormed();
        assert ordersContains(o,list);
    }
    /**
     * @pre list.size() {@literal >} 0
     * @post @nochange
     * @param list
     * @return float
     */
    public float computeOrderTotal(ArrayList<MenuItem> list) {
        assert list.size()>0;
        float total=0;
        for(int i=0;i<list.size();i++){
            total+=list.get(i).computePrice();
        }
        assert isWellFormed();
        return total;
    }
    /**
     * Generates a .txt file with information extracted from arguments.
     * @pre list.size() {@literal >} 0 {@literal &&} o != null {@literal &&} ordersContains(o,list) == true
     * @post @nochange
     * @param list
     */
    public void generateBill(Order o, ArrayList<MenuItem> list) {
        assert list.size()>0 && o!=null;
        String fileName="order.txt";
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("--Order ");
        stringBuilder.append(o.getOrderID()+"\n");
        stringBuilder.append("--Date : ");
        stringBuilder.append(o.getStringDate()+"\n");
        stringBuilder.append("--Table : ");
        stringBuilder.append(o.getTable()+"\n");
        stringBuilder.append("--Ordered : "+"\n");
        for(int i=0;i<list.size();i++){
            stringBuilder.append((i+1)+". "+list.get(i).getName()+"    price : "+list.get(i).computePrice()+"\n");
        }
        stringBuilder.append("\n--Total : ");
        stringBuilder.append(computeOrderTotal(list));
        FileWriter.writeText(fileName,stringBuilder.toString());
        assert isWellFormed();
    }
    /**
     * Checks if the menu already contains the item to be added.
     * @pre item != null
     * @post @nochange
     * @param item
     * @return boolean
     */
    public boolean menuContains(MenuItem item) {
        assert item!=null;
        if(item!=null && menu.contains(item)){
            assert isWellFormed();
            return true;
        }
        assert isWellFormed();
        return false;
    }
    /**
     * Checks if orders map already contains the map.entry {@literal <o,list>}.
     * @pre item != null {@literal &&} list.size() {@literal >} 0
     * @post @nochange
     * @param o
     * @param list
     * @return boolean
     */
    public boolean ordersContains(Order o,ArrayList<MenuItem> list){
        assert o!=null && list.size()>0;
        if(orders.containsKey(o) && orders.get(o)==list){
            assert isWellFormed();
            return true;
        }
        assert isWellFormed();
        return false;
    }

    public Set<MenuItem> getMenu() {
        return menu;
    }
    public Map<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    protected boolean isWellFormed(){
        if(menu==null || orders==null)
            return false;
        for(Iterator<MenuItem> it=menu.iterator();it.hasNext();){
            if(it.next()==null){
                return false;
            }
        }
        for(Map.Entry<Order,ArrayList<MenuItem>> entry:orders.entrySet()){
            if(entry.getValue()==null || entry.getKey()==null){
                return false;
            }
        }
        return true;
    }
}
