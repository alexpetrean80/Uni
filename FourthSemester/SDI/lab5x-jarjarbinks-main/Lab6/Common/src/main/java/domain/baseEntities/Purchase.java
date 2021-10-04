package domain.baseEntities;

import javax.persistence.Entity;
import java.io.Serializable;


@Entity
public class Purchase extends BaseEntity<Long>  {

    Long clientId;
    Long droidId;
    double totalPrice;

    /**
     * @param clientId
     * @param totalPrice
     * @param droidId
     */
    public Purchase(Long clientId, double totalPrice, Long droidId) {
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.droidId = droidId;
    }

    public Purchase(Long id, Long clientId, double totalPrice, Long droidId) {
        this.setId(id);
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.droidId = droidId;
    }

    public Purchase(){
        this.clientId = 0L;
        this.totalPrice = 0L;
        this.droidId = 0L;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getDroidId() {
        return droidId;
    }

    public void setDroidId(Long droidId) {
        this.droidId = droidId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.totalPrice, totalPrice) == 0 && clientId.equals(purchase.clientId);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Purchease{" +
                "purchaseId=" + clientId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}



