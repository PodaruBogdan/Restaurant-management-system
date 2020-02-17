package businessLayer;

import java.util.ArrayList;
import java.util.Iterator;

public class CompositeProduct implements MenuItem,Cloneable{
    private ArrayList<MenuItem> components;
    private String name;
    private int quantity;
    public CompositeProduct(String name,int quantity){
        components=new ArrayList<MenuItem>();
        this.name=name;
        this.quantity=quantity;
    }
    public void addComponent(MenuItem item){
        components.add(item);
    }
    public float computePrice(){
        float total=0;
        for(Iterator<MenuItem> it=components.iterator();it.hasNext();){
            total+=it.next().computePrice();
        }
        return total;
    }
    public int hashCode(){
        int hashValue=0;
        for(Iterator<MenuItem> it=components.iterator();it.hasNext();){
            hashValue+=it.next().hashCode();
        }
        hashValue+=quantity;
        hashValue+=name.hashCode();
        return hashValue;
    }
    public boolean equals(Object obj) {
        if (obj instanceof CompositeProduct) {
            if(((CompositeProduct)obj).name.equals(this.name)){
                return true;
            }
        }
        return false;
    }

        public int getQuantity () {
            return quantity;
        }

        public String[] getComposedBy () {
            ArrayList<String> composedBy = new ArrayList<String>();
            for (Iterator<MenuItem> it = components.iterator(); it.hasNext(); ) {
                composedBy.add(it.next().getName());
            }
            String[] names = new String[composedBy.size()];
            return composedBy.toArray(names);
        }

        public String getName () {
            return name;
        }
        public ArrayList<MenuItem> getComponents () {
            return components;
        }
        public Object clone() {
            CompositeProduct cp=new CompositeProduct(this.name,this.quantity);
            ArrayList<MenuItem> componentsCopy=new ArrayList<MenuItem>();
            for(int i=0;i<components.size();i++){
                componentsCopy.add(components.get(i));
            }
            cp.components=componentsCopy;
            return cp;
        }
    }