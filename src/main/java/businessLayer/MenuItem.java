package businessLayer;

import java.io.Serializable;

public interface MenuItem extends Serializable {
    float computePrice();
    int hashCode();
    boolean equals(Object obj);
    String getName();
    int getQuantity();
    String[] getComposedBy();
    Object clone();
}
