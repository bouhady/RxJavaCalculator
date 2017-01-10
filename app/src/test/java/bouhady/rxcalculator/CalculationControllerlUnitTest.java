package bouhady.rxcalculator;

import org.junit.Test;

import java.util.Arrays;

import bouhady.rxcalculator.controllers.CalculatorController;
import bouhady.rxcalculator.models.CalculationArguments;
import bouhady.rxcalculator.models.CalculationFactory;
import bouhady.rxcalculator.models.CalculationTypes;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculationControllerlUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        assertEquals(4, 2 + 2);

        CalculationArguments calculationArguments = new CalculationArguments(2.4,2.5);

        testControllenrMechnism(new CalculationArguments(2.4,2.5));
        testControllenrMechnism(new CalculationArguments(1.2,2.3));
        testControllenrMechnism(new CalculationArguments(0.0,40000000000000.0));
        testControllenrMechnism(new CalculationArguments(0.0,0.0));
        testControllenrMechnism(new CalculationArguments(400000000000000.0,0.0));
        testControllenrMechnism(new CalculationArguments(2.4,2.5));
        testControllenrMechnism(null);
        CalculatorController calculatorController = new CalculatorController();
        Observable test = Observable.just(calculationArguments);
        TestSubscriber testSubscriber = new TestSubscriber();

        calculatorController.addCalculation(test,testSubscriber, CalculationTypes.DIVISION);
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(CalculationFactory.getCalculation(CalculationTypes.DIVISION).setArguments(calculationArguments).getProduct()));
    }

   private void testControllenrMechnism(CalculationArguments calculationArguments){
       Observable<CalculationArguments> inputData = Observable.just(calculationArguments);
       TestSubscriber testSubscriber = new TestSubscriber();
       inputData.subscribe(testSubscriber);
       testSubscriber.assertNoErrors();
       testSubscriber.assertReceivedOnNext(Arrays.asList(calculationArguments));
   }
}