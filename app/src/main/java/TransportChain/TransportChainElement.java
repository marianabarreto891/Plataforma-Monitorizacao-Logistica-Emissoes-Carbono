package TransportChain;

public abstract class TransportChainElement {
    private String elementId;
    private String chainId;
    private String geography;
    private int year;
    private double emission;

    public TransportChainElement(String elementId, String chainId, String geography, int year) {
        this.elementId = elementId;
        this.chainId = chainId;
        this.geography = geography;
        this.year = year;
    }

    public String getElementId() {
        return elementId;
    }

    public String getChainId() {
        return chainId;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getEmission() {
        return this.emission;
    }

    public void setEmission(double emission) { this.emission = emission; }

}