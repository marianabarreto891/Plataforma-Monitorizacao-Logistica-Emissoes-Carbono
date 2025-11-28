package TransportChain;

public class HubOperation extends TransportChainElement {
    private double consumoEnergia;
    private double fatorEmissao;

    public HubOperation(String elementId, String chainId, String geography, double fatorEmissao, double consumoEnergia) {
        super(elementId, chainId, geography);
        this.consumoEnergia = consumoEnergia;
        this.fatorEmissao = fatorEmissao;
    }

    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    public double getFatorEmissao() {
        return fatorEmissao;
    }

    public void setConsumoEnergia(double consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public void setFatorEmissao(double fatorEmissao) {
        this.fatorEmissao = fatorEmissao;
    }

    @Override
    public String getElementId() {
        return super.getElementId();
    }

    @Override
    public String listarAtributos() {
        StringBuilder sb = new StringBuilder();
        sb.append("ElementId: ").append(getElementId())
                .append(", ConsumoEnergia: ").append(getConsumoEnergia())
                .append(", FatorEmissao: ").append(getFatorEmissao());
        return sb.toString();
    }
}