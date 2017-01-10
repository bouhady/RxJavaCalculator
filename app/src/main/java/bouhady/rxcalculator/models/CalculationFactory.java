package bouhady.rxcalculator.models;

import static bouhady.rxcalculator.models.CalculationTypes.DIVISION;
import static bouhady.rxcalculator.models.CalculationTypes.MINUS;
import static bouhady.rxcalculator.models.CalculationTypes.MULTIPLE;
import static bouhady.rxcalculator.models.CalculationTypes.PLUS;

/**
 * Created by Yaniv Bouhadana on 09/01/2017.
 */

public class CalculationFactory {
    public static Calculation getCalculation(@CalculationTypes.CalculationType int calcType){
        Calculation result = null;
        switch (calcType){
            case PLUS :
                result = new PlusCalculation();
            break;
            case MINUS :
                result =  new MinusCalculation();
            break;
            case MULTIPLE :
                result =  new MultipleCalculation();
            break;
            case DIVISION:
                result =  new DivisionCalculation();
        }
        
        return result;
    }
}
