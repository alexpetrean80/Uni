package domain.Keys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class ClientDroidPrimaryKey implements Serializable {
    Long clientID, droidID;

    ClientDroidPrimaryKey(){}

    public ClientDroidPrimaryKey(Long clientID, Long droidID){
        this.clientID = clientID;
        this.droidID = droidID;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public Long getDroidID() {
        return droidID;
    }

    public void setDroidID(Long droidID) {
        this.droidID = droidID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDroidPrimaryKey that = (ClientDroidPrimaryKey) o;
        return Objects.equals(clientID, that.clientID) && Objects.equals(droidID, that.droidID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientID, droidID);
    }

    @Override
    public String toString() {
        return "ClientDroidPrimaryKey{" +
                "clientID=" + clientID +
                ", droidID=" + droidID +
                '}';
    }
}
