package bouhady.rxcalculator.controllers;

import java.util.HashMap;

import bouhady.rxcalculator.models.CalculationArguments;
import bouhady.rxcalculator.models.CalculationFactory;
import bouhady.rxcalculator.models.CalculationTypes;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 *
 * The main controller which receives Observable from inputs (CalculationArguments) and a Subscriber to the GUI TextView
 * , performs the calculation according the sign and return the result.
 * in addition it register the observable and subscriber into a HashMap for future optional unsubscribe
 * Created by Yaniv Bouhadana on 10/01/2017.
 */
public class CalculatorController {
    private HashMap<Observable<CalculationArguments>,Subscriber<Double>> observers;

    public CalculatorController(){
        observers = new HashMap<>();
    }

    public CalculatorController addCalculation(Observable<CalculationArguments> calculationObservable
            , Subscriber<Double> resultObserver
            , final @CalculationTypes.CalculationType int calculationType)
    {
        observers.put(calculationObservable, resultObserver);
        calculationObservable.map(new Func1<CalculationArguments, Double>() {
            @Override
            public Double call(CalculationArguments calculationArguments) {
                return CalculationFactory.getCalculation(calculationType).setArguments(calculationArguments).getProduct();
            }
        }).subscribe(resultObserver);
        return this;
    }
}
