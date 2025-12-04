package Calculator;

public class HOCalculator implements IHOCalculator {

    /**
     * Calculates the emissions for a Hub Operation based on emission factor and energy consumption.
     * @param EF The emission factor (must be strictly positive and less than or equal to 1000).
     * @param Ei The energy consumption (must be strictly positive and less than or equal to 1000).
     * @return The calculated emissions (EF * Ei), or -1 if the inputs are zero, negative, or exceed the limit.
     */
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