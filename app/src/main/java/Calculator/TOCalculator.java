package Calculator;

public class TOCalculator implements TOCalculations {

    private double ttw;
    private double wtt;
    private double wtw;

    private double calcularTTW(double consumoEi, double efTTW) {
        return consumoEi * efTTW;
    }

    private double calcularWTT(double consumoEi, double efWTT) {
        return consumoEi * efWTT;
    }

    @Override
    public double calcularWtw(double consumoEi, double efTTW, double efWTT) {

        if (consumoEi < 0 || consumoEi > 10000) {
            return -1;
        }

        if (efTTW < 0 || efTTW > 1000) {
            return -1;
        }

        if (efWTT < 0 || efWTT > 1000) {
            return -1;
        }

        this.ttw = calcularTTW(consumoEi, efTTW);
        this.wtt = calcularWTT(consumoEi, efWTT);

        this.wtw = this.ttw + this.wtt;

        return this.wtw;
    }
}
