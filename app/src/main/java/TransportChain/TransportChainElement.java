package TransportChain;

public abstract class TransportChainElement {
    private String elementId;
    private String chainId;
    private String geography;
    private int year;
    private double emission;

    /**
     * Initializes the base element with identification and context data.
     * @param elementId The unique identifier for this element.
     * @param chainId The identifier of the transport chain this element belongs to.
     * @param geography The geographical location or scope of the element.
     * @param year The year associated with the operation.
     */
    public TransportChainElement(String elementId, String chainId, String geography, int year) {
        this.elementId = elementId;
        this.chainId = chainId;
        this.geography = geography;
        this.year = year;
    }

    /**
     * Retrieves the unique identifier of this element.
     * @return The element ID string.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * Retrieves the identifier of the transport chain this element belongs to.
     * @return The chain ID string.
     */
    public String getChainId() {
        return chainId;
    }

    /**
     * Retrieves the geographical location or scope of the element.
     * @return The geography string.
     */
    public String getGeography() {
        return geography;
    }

    /**
     * Updates the geographical location of the element.
     * @param geography The new geography string to set.
     */
    public void setGeography(String geography) {
        this.geography = geography;
    }

    /**
     * Retrieves the year associated with this operation.
     * @return The year as an integer.
     */
    public int getYear() {
        return year;
    }

    /**
     * Updates the year of the operation.
     * @param year The new year value to set.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Retrieves the calculated emission value for this element.
     * @return The emission value.
     */
    public double getEmission() {
        return this.emission;
    }

    /**
     * Sets the calculated emission value for this element.
     * @param emission The calculated emission value to store.
     */
    public void setEmission(double emission) { this.emission = emission; }

}