package domain.baseEntities;

/**
 *
 * @param <ID>
 */
public class BaseEntity<ID> {
    private ID id;

    /**
     *
     * @return ID - returns the ID of the entity
     */
    public ID getId(){
        return this.id;
    }

    /**
     *
     * @param id - sets a new ID for the entity
     */
    public void setId(ID id){
        this.id = id;
    }

    /**
     *
     * @return String - toString() of the entity
     */
    @Override
    public String toString() {
        return "BaseEntity{"+
                "id = " + id +
                "}";
    }
}
