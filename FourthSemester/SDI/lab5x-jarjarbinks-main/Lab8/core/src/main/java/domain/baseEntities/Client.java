package domain.baseEntities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@javax.persistence.Entity
public class Client extends BaseEntity<Long> {


    private String name;
    private String address;
    private String phoneNumber;

//    @OneToMany(mappedBy = "Droid",cascade = {CascadeType.ALL})
//    Set<Purchase> purchaseSet;

    /**
     * @param name        - full name of the client
     * @param address     - delivery address of the client
     * @param phoneNumber - contact phone number of the client
     */
    public Client(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Client(Long id, String name, String address, String phoneNumber) {
        this.setId(id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Client(){
        this.name = "";
        this.address = "";
        this.phoneNumber = "";
    }

    /**
     * @return the name of a client
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address of a client
     */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone number of a client
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
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
     * @return int - hashCode of the droid entity
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, address, phoneNumber);
    }

    /**
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


}
