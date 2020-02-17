package businessLayer;

public class BaseProduct implements MenuItem,Cloneable{
    private int page;
    private float price;
    private String name;
    private int quantity;

    public BaseProduct(int page, float price, String name, int quantity) {
        this.page = page;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }
    @Override
    public int hashCode(){
        return page+(int)price+name.hashCode()+quantity;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof BaseProduct){
            if(page==((BaseProduct) obj).page && price==((BaseProduct) obj).price && name.equals(((BaseProduct) obj).name) && quantity==((BaseProduct) obj).quantity){
                return true;
            }
            return false;
        }
        return false;
    }
    public float computePrice(){
        return price;
    }
    public String toString(){
        return "page : "+page+", price : "+price+", name : "+name+", quantity : "+quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String[] getComposedBy() {
        String[] itself=new String[]{"itself"};
        return itself;
    }

    public int getPage() {
        return page;
    }
    public Object clone()  {
        BaseProduct bp=new BaseProduct(this.page,this.price,this.name,this.quantity);
        return bp;
    }
}
