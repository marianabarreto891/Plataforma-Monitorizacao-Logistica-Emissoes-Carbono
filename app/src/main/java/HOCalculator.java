public class HOCalculator implements IHOCalculator {

    @Override
    public double calculateEmissonsHO(double EF, double Ei) {
        if (EF < 0 || Ei < 0) {
            return -1;
        }
        if (EF == 0 || Ei == 0) {
            return -1;
        }

        if(EF>1000 || Ei>1000){
             return -1;
        }
        
        return EF * Ei;
    }
}