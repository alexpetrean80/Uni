package domain.baseEntities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Receipt extends StorableEntity<Long, Receipt> implements XMLHandler{
    private Long purchaseID;
    double totalPrice;

    /**
     *
     * @param purchaseID
     * @param totalPrice
     */
    public Receipt(Long purchaseID, double totalPrice) {
        this.purchaseID = purchaseID;
        this.totalPrice = totalPrice;
    }

    /**
     *
     * @param e - Element object that is transformed into a Receipt type
     * @return Receipt - the resulting Purchase
     */
    @Override
    public Receipt fromNode(Element e){
        Long id = Long.parseLong(e.getElementsByTagName("id").item(0).getTextContent());
        Long purchaseID = Long.parseLong(e.getElementsByTagName("purchaseID").item(0).getTextContent());
        double totalPrice = Double.parseDouble(e.getElementsByTagName("totalPrice").item(0).getTextContent());


        Receipt receipt = new Receipt(purchaseID, totalPrice);
        receipt.setId(id);
        return receipt;
    }


    /**
     *
     * @param document - Document object that is transformed into an XML Element object
     * @return Element - XML object that will be printed in the xml file
     */
    @Override
    public Element intoNode(Document document) {
        Element root = document.createElement("receipt");

        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(this.getId().toString()));
        root.appendChild(idElement);

        Element purchaseIDElement = document.createElement("purchaseID");
        purchaseIDElement.appendChild(document.createTextNode(this.getPurchaseID().toString()));
        root.appendChild(purchaseIDElement);

        Element totalPriceElement = document.createElement("totalPrice");
        totalPriceElement.appendChild(document.createTextNode(String.valueOf(this.totalPrice)));
        root.appendChild(totalPriceElement);

        return root;
    }

    public Long getPurchaseID() {
        return purchaseID;
    }

    /**
     *
     * @param purchaseID
     */
    public void setPurchaseID(Long purchaseID) {
        this.purchaseID = purchaseID;
    }

    /**
     *
     * @return
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     *
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Double.compare(receipt.totalPrice, totalPrice) == 0 && Objects.equals(purchaseID, receipt.purchaseID);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(purchaseID, totalPrice);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Receipt{" +
                "purchaseID=" + purchaseID +
                ", totalPrice=" + totalPrice +
                '}';
    }


    //private Long purchaseID;
    //    double totalPrice;


    @Override
    public PreparedStatement getPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, Long.parseLong(this.getId().toString()));
        preparedStatement.setLong(2, this.purchaseID);
        preparedStatement.setDouble(3, this.totalPrice);
        return preparedStatement;
    }

    @Override
    public String getSqlInsertCommand() {
        return "insert into \"DroidDealership\".\"Receipt\"  (id, purchaseID, totalPrice) values (" + this.getId() + "," +
                "'" + this.purchaseID + "'" + "," + "'" + this.totalPrice + ")";
    }

    @Override
    public String getSqlDeleteCommand() {
        return "delete from \"DroidDealership\".\"Receipt\"  where id = ?";
    }

    @Override
    public String getSqlUpdateCommand() {
        return "update \"DroidDealership\".\"Receipt\" set purchaseID=?, totalPrice = ? where id=?";
    }


    @Override
    public Receipt getFromResultSet(ResultSet resultSet) throws SQLException {
        Long receiptID = (long) resultSet.getInt("id");
        Long purchaseID = (long) resultSet.getInt("purchaseID");
        double totalPrice = (double) resultSet.getDouble("totalPrice");


        Receipt receipt = new Receipt(purchaseID, totalPrice);
        receipt.setId(receiptID);
        return receipt;
    }
}
