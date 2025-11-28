package TransportChain;

public class TransportOperation extends TransportChainElement {
    private double distanciaKm;
    private double cargaTotalCapacidade;
    private double fatorCarga;
    private double emptyReturn;
    private double consumoMedioE;
    private double fatorEmissaoTTW;
    private double fatorEmissaoWTT;


    public TransportOperation(String elementId, String chainId, String geography, double distanciaKm, double cargaTotalCapacidade, double fatorCarga, double emptyReturn, double consumoMedioE, double efTTW, double efWTT) {

        super(elementId, chainId, geography);

        this.distanciaKm = distanciaKm;
        this.cargaTotalCapacidade = cargaTotalCapacidade;
        this.fatorCarga = fatorCarga;
        this.emptyReturn = emptyReturn;
        this.consumoMedioE = consumoMedioE;
        this.fatorEmissaoTTW = efTTW;
        this.fatorEmissaoWTT = efWTT;

    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public double getCargaTotalCapacidade() {
        return cargaTotalCapacidade;
    }

    public void setCargaTotalCapacidade(double cargaTotalCapacidade) {
        this.cargaTotalCapacidade = cargaTotalCapacidade;
    }

    public double getFatorCarga() {
        return fatorCarga;
    }

    public void setFatorCarga(double fatorCarga) {
        this.fatorCarga = fatorCarga;
    }

    public double getEmptyReturn() {
        return emptyReturn;
    }

    public void setEmptyReturn(double emptyReturn) {
        this.emptyReturn = emptyReturn;
    }

    public double getConsumoMedioE() {
        return consumoMedioE;
    }

    public void setConsumoMedioE(double consumoMedioE) {
        this.consumoMedioE = consumoMedioE;
    }

    public double getFatorEmissaoTTW() {
        return fatorEmissaoTTW;
    }

    public void setFatorEmissaoTTW(double fatorEmissaoTTW) {
        this.fatorEmissaoTTW = fatorEmissaoTTW;
    }

    public double getFatorEmissaoWTT() {
        return fatorEmissaoWTT;
    }

    public void setFatorEmissaoWTT(double fatorEmissaoWTT) {
        this.fatorEmissaoWTT = fatorEmissaoWTT;
    }

    @Override
    public String listarAtributos() {
        StringBuilder sb = new StringBuilder();
        sb.append("ElementId: ").append(getElementId())
                .append(", DistanciaKm: ").append(getDistanciaKm())
                .append(", CargaTotal: ").append(getCargaTotalCapacidade())
                .append(", FatorCarga: ").append(getFatorCarga())
                .append(", EmptyReturn: ").append(getEmptyReturn())
                .append(", ConsumoMedioE: ").append(getConsumoMedioE())
                .append(", FatorEmissaoTTW: ").append(getFatorEmissaoTTW())
                .append(", FatorEmissaoWTT: ").append(getFatorEmissaoWTT())
                .append(", Emissao: ").append(getEmissao());
        return sb.toString();
    }
}