package domain.baseEntities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Client extends StorableEntity<Long, Client> implements XMLHandler{

    private final String name;
    private final String address;
    private final String phoneNumber;


    /**
     *
     * @param e - Element object that is transformed into a Client type
     * @return Client - the resulting Client
     */
    @Override
    public Client fromNode(Element e){
        Long id = Long.parseLong(e.getElementsByTagName("id").item(0).getTextContent());
        String name = e.getElementsByTagName("name").item(0).getTextContent();
        String address = e.getElementsByTagName("address").item(0).getTextContent();
        String phoneNumber = e.getElementsByTagName("phoneNumber").item(0).getTextContent();


        Client client = new Client(name, address, phoneNumber);
        client.setId(id);
        return client;

    }


    /**
     *
     * @param document - Document object that is transformed into an XML Element object
     * @return Element - XML object that will be printed in the xml file
     */
    @Override
    public Element intoNode(Document document) {
        Element root = document.createElement("client");

        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(this.getId().toString()));
        root.appendChild(idElement);

        Element nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(name));
        root.appendChild(nameElement);

        Element addressElement = document.createElement("address");
        addressElement.appendChild(document.createTextNode(address));
        root.appendChild(addressElement);

        Element phoneNumber = document.createElement("phoneNumber");
        phoneNumber.appendChild(document.createTextNode(String.valueOf(phoneNumber)));
        root.appendChild(phoneNumber);

        return root;
    }

    /**
     *
     * @param name - full name of the client
     * @param address - delivery address of the client
     * @param phoneNumber - contact phone number of the client
     */
    public Client(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return the name of a client
     */
    public String getName() { return name; }

    /**
     *
     * @return the address of a client
     */
    public String getAddress() { return address; }

    /**
     *
     * @return the phone number of a client
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     *
     * @param o - an object
     * @return - true if the clients are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(address, client.address) && Objects.equals(phoneNumber, client.phoneNumber);
    }

    /**
     *
     * @return int - hashCode of the droid entity
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, address, phoneNumber);
    }

    /**
     *
     * @return String - toString() of the Client
     */
    @Override
    public String toString() {
        return "Client{" +
                "name=" + name +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                '}';
    }


    @Override
    public PreparedStatement getPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, Long.parseLong(this.getId().toString()));
        preparedStatement.setString(2, this.name);
        preparedStatement.setString(3, this.address);
        preparedStatement.setString(4, this.phoneNumber);

        return preparedStatement;
    }

    @Override
    public String getSqlInsertCommand() {
        return "insert into \"DroidDealership\".\"Client\"  (id, name, address, phoneNumber) values (" + this.getId() + "," +
                "'" + this.name + "'" + "," + "'" + this.address + "'" + "," + this.phoneNumber + ")";
    }

    @Override
    public String getSqlDeleteCommand() {
        return "delete from \"DroidDealership\".\"Client\"  where id = ?";
    }

    @Override
    public String getSqlUpdateCommand() {
        return "update \"DroidDealership\".\"Client\" set name=?, address = ?, phoneNumber = ? where id=?";
    }

    @Override
    public Client getFromResultSet(ResultSet resultSet) throws SQLException {
        Long clientID = (long) resultSet.getInt("id");
        String clientName = (String) resultSet.getString("name");
        String address = (String) resultSet.getString("address");
        String phoneNr = (String) resultSet.getString("phoneNumber");

        Client client = new Client(clientName, address, phoneNr);
        client.setId(clientID);
        return client;
    }


}
