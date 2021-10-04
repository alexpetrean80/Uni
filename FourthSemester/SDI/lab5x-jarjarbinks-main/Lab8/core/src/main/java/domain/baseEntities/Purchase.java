package domain.baseEntities;

import domain.Keys.ClientDroidPrimaryKey;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
@javax.persistence.Entity
@Embeddable
public class Purchase extends BaseEntity<ClientDroidPrimaryKey>{
    public Purchase(){}

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    Client client;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    Droid droid;

    private double totalPrice;

    public Purchase(ClientDroidPrimaryKey cdpk, Client _client, Droid _droid, double _totalPrice ){
        this.setId(cdpk);
        this.client = _client;
        this.droid = _droid;
        this.totalPrice = _totalPrice;
    }

    public Long getClientID(){
        return this.getId().getClientID();
    }

    public Long getDroidID(){
        return this.getId().getDroidID();
    }

    public double getTotalPrice(){
        return this.totalPrice;
    }

    public void setTotalPrice(double _p){
        this.totalPrice = _p;
    }


    public boolean equals(Object obj) {
        return obj instanceof Purchase && this.getId().equals(((Purchase) obj).getId());
    }

    public Client getClient(){
        return this.client;
    }

    public Droid getDroid(){
        return this.droid;
    }

    public void setDroid(Droid d){
        this.droid = d;
    }

    public void setClient(Client c){
        this.client = c;
    }

    @Override
    public String toString() {
        return "JpaPurchase{" +
                "client=" + client +
                ", droid=" + droid +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
