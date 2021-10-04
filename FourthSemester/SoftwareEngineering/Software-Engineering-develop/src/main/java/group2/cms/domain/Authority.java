package group2.cms.domain;

public enum Authority {
    Default,
    Listener,
    Author,
    PCMember,
    CoChair,
    Chair;

    /**
     *
     * @param authority - authority object with the level selected e.g Authority.Author
     * @return - true if authority given is <= the authority from which the compare function is called
     */
    public boolean compareAuthority(Authority authority){
        return this.compareTo(authority) > 0;
    }
}
