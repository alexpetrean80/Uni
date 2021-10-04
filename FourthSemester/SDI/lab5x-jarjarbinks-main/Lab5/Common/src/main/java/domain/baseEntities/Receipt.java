package domain.baseEntities;

import java.util.Objects;

public class Receipt extends BaseEntity<Long> {

    private Long purchaseID;
    double totalPrice;

    /**
     * @return
     */
    public Long getPurchaseID() {
        return purchaseID;
    }

    /**
     * @param purchaseID
     */
    public void setPurchaseID(Long purchaseID) {
        this.purchaseID = purchaseID;
    }

    /**
     * @return
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    /**
     * @param purchaseID
     * @param totalPrice
     */
    public Receipt(Long purchaseID, double totalPrice) {
        this.purchaseID = purchaseID;
        this.totalPrice = totalPrice;
    }

    public Receipt() {
        this.purchaseID = 0L;
        this.totalPrice = 0L;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Double.compare(receipt.totalPrice, totalPrice) == 0 && purchaseID.equals(receipt.purchaseID);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(purchaseID, totalPrice);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Receipt{" +
                "purchaseID=" + purchaseID +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
