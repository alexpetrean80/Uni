package domain.baseEntities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Purchase extends StorableEntity<Long, Purchase> implements XMLHandler{

    Long clientId;
    Long droidId;
    double totalPrice;

    /**
     *
     * @param clientId
     * @param totalPrice
     * @param droidId
     */
    public Purchase(Long clientId, double totalPrice, Long droidId) {
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.droidId = droidId;
    }

    /**
     *
     * @param e - Element object that is transformed into a Purchase type
     * @return Purchase - the resulting Purchase
     */
    @Override
    public Purchase fromNode(Element e){
        Long id = Long.parseLong(e.getElementsByTagName("id").item(0).getTextContent());
        Long clientId = Long.parseLong(e.getElementsByTagName("clientId").item(0).getTextContent());
        Long droidId = Long.parseLong(e.getElementsByTagName("droidId").item(0).getTextContent());


        Purchase purchase = new Purchase(id, clientId, droidId);
        purchase.setId(id);
        return purchase;
    }


    /**
     *
     * @param document - Document object that is transformed into an XML Element object
     * @return Element - XML object that will be printed in the xml file
     */
    @Override
    public Element intoNode(Document document) {
        Element root = document.createElement("purchase");

        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(this.getId().toString()));
        root.appendChild(idElement);

        Element clientIdElement = document.createElement("clientId");
        clientIdElement.appendChild(document.createTextNode(this.getClientId().toString()));
        root.appendChild(clientIdElement);

        Element purcheaseIdElement = document.createElement("droidId");
        purcheaseIdElement.appendChild(document.createTextNode(this.getDroidId().toString()));
        root.appendChild(purcheaseIdElement);

        return root;
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
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.totalPrice, totalPrice) == 0 && clientId.equals(purchase.clientId);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Purchease{" +
                "purchaseId=" + clientId +
                ", totalPrice=" + totalPrice +
                '}';
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(clientId, totalPrice);
    }

    /**
     *
     * @return
     */
    public Long getPurchaseId() {
        return clientId;
    }

    /**
     *
     * @return
     */
    public Long getDroidId(){
        return this.droidId;
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
     * @param purchaseId
     */
    public void setPurchaseId(Long purchaseId) {
        this.setId(purchaseId);
    }

    /**
     *
     * @return
     */
    public Long getClientId(){
        return this.clientId;
    }

    /**
     *
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public PreparedStatement getPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, Long.parseLong(this.getId().toString()));
        preparedStatement.setLong(2, Long.parseLong(this.clientId.toString()));
        preparedStatement.setLong(3, Long.parseLong(this.droidId.toString()));
        preparedStatement.setDouble(4, this.totalPrice);

        return preparedStatement;
    }

    @Override
    public String getSqlInsertCommand() {
        return "insert into \"DroidDealership\".\"Purchase\"  (id, clientID, droidID, totalPrice) values (" + this.getId() + "," +
                "'" + this.clientId + "'" + "," + "'" + this.droidId + "'" + "," +  ")";
    }

    @Override
    public String getSqlDeleteCommand() {
        return "delete from \"DroidDealership\".\"Purchase\"  where id = ?";
    }

    @Override
    public String getSqlUpdateCommand() {
        return "update \"DroidDealership\".\"Purchase\" set clientID=?, droidID = ?, totalPrice = ? where id=?";
    }

    @Override
    public Purchase getFromResultSet(ResultSet resultSet) throws SQLException {
        Long purchaseID = (long) resultSet.getInt("id");
        Long clientID = (long) resultSet.getInt("clientID");
        Long droidID = (long) resultSet.getInt("droidID");
        double totalPrice = (double) resultSet.getDouble("totalPrice");

        Purchase purchase = new Purchase(clientID, totalPrice, droidID);
        purchase.setId(purchaseID);
        return purchase;
    }
}
