package bouhady.rxcalculator.models;

/**
 * Created by Yaniv Bouhadana on 09/01/2017.
 */

public class PlusCalculation extends Calculation {

    @Override
    public Double getProduct() {
        return (arguments.argumentA+arguments.argumentB);
    }
}
