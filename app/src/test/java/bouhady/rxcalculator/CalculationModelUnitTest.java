package bouhady.rxcalculator;

import org.junit.Test;

import bouhady.rxcalculator.models.CalculationArguments;
import bouhady.rxcalculator.models.CalculationFactory;
import bouhady.rxcalculator.models.CalculationTypes;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculationModelUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        assertEquals(4, 2 + 2);
        testAllOperation(2.3,1.2);
        testAllOperation(1.2,2.3);
        testAllOperation(40000000000000.0,1.2);
        testAllOperation(1.0,40000000000000.0);
        testAllOperation(40000000000000.0,40000000000000.0);
        testAllOperation(0.0,40000000000000.0);
        testAllOperation(0.0,0.0);
        testAllOperation(400000000000000.0,0.0);

    }

    private void testAllOperation(Double aVar,Double bVar){
        assertEquals(aVar+bVar, CalculationFactory.getCalculation(CalculationTypes.PLUS).setArguments(new CalculationArguments(aVar,bVar)).getProduct(),0.0);
        assertEquals(aVar-bVar, CalculationFactory.getCalculation(CalculationTypes.MINUS).setArguments(new CalculationArguments(aVar,bVar)).getProduct(),0.0);
        assertEquals(aVar*bVar, CalculationFactory.getCalculation(CalculationTypes.MULTIPLE).setArguments(new CalculationArguments(aVar,bVar)).getProduct(),0.0);
        assertEquals(aVar/bVar, CalculationFactory.getCalculation(CalculationTypes.DIVISION).setArguments(new CalculationArguments(aVar,bVar)).getProduct(),0.0);

    }
}