package TransportChain;

public abstract class TransportChainElement {
    private String elementId;
    private String chainId;
    private String geography;
    private double emissao;

    public TransportChainElement(String elementId, String chainId, String geography) {
        this.elementId = elementId;
        this.chainId = chainId;
        this.geography = geography;
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

    public double getEmissao() {
        return this.emissao;
    }

    public void setEmissao(double emissao) { this.emissao = emissao; }

    public abstract String listarAtributos();
}
