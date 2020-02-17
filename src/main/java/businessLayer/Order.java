package businessLayer;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private int orderID;
    private Date date;
    private String stringDate ;
    private int table;

    public Order(int orderID, int table) {
        this.orderID = orderID;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.date=date;
        this.stringDate = dateFormat.format(date);
        this.table = table;
    }

    public int getOrderID(){
        return orderID;
    }
    public int getTable(){
        return table;
    }
    public Date getDate(){
        return (Date)date.clone();
    }
    @Override
    public int hashCode(){
        int hashValue=0;
            for(Field field : this.getClass().getDeclaredFields()){
                field.setAccessible(true);
                hashValue+=field.hashCode();
            }
        return hashValue;
    }
    public String toString(){
        return "Order : "+orderID+", date : "+date+", table : "+table;
    }

    public String getStringDate() {
        return stringDate;
    }
}
