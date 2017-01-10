package bouhady.rxcalculator.models;

/**
 * Created by Yaniv Bouhadana on 09/01/2017.
 */

public abstract class Calculation {
     protected CalculationArguments arguments;


    public Calculation(){
    }

    public Calculation setArguments(CalculationArguments arguments) {
        this.arguments = arguments;
        return this;
    }

    public abstract Double getProduct();

}
