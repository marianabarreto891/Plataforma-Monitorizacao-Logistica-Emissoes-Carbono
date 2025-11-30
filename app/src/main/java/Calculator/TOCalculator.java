package Calculator;

public class TOCalculator implements ITOCalculations {

    private double ttw;
    private double wtt;
    private double wtw;

    private double calculateTTW(double consumptionEi, double efTTW) {
        return consumptionEi * efTTW;
    }

    private double calculateWTT(double consumptionEi, double efWTT) {
        return consumptionEi * efWTT;
    }

    @Override
    public double calculateWTW(double consumptionEi, double efTTW, double efWTT) {

        if (consumptionEi < 0 || consumptionEi > 10000000) {
            return -1;
        }

        if (efTTW < 0 || efTTW > 1000) {
            return -1;
        }

        if (efWTT < 0 || efWTT > 1000) {
            return -1;
        }

        this.ttw = calculateTTW(consumptionEi, efTTW);
        this.wtt = calculateWTT(consumptionEi, efWTT);

        this.wtw = this.ttw + this.wtt;

        return this.wtw;
    }
}
