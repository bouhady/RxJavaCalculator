package bouhady.rxcalculator;

import android.util.Log;

import bouhady.rxcalculator.models.CalculationArguments;
import rx.Subscriber;
import rx.functions.Func2;

import static android.text.TextUtils.isEmpty;

/**
 * Implementation of the Func2 class (method) for combining two EditTexts Obserables outputs (CharSequences)
 * and process it into a POJO (CalculationArguments)
 *
 * Created by Yaniv Bouhadana on 09/01/2017.
 */

public class CombineInputs implements Func2< CharSequence, CharSequence,CalculationArguments> {
    private final String TAG = "CombineInputs";

    private Subscriber<Double> resultObserver;


    @Override
    public CalculationArguments call(CharSequence aValue, CharSequence bValue) {
        boolean aValid = !isEmpty(aValue);
        if (!aValid) {
        }
        boolean bValid = !isEmpty(bValue);
        if (!bValid) {
        }

        CalculationArguments result;

        try {
            result = new CalculationArguments(Double.valueOf(aValue.toString()), Double.valueOf(bValue.toString()));
            Log.d(TAG, "result ok " + result);
        } catch (Exception e) {
            result = null;
            Log.d(TAG, "result FAIL");
        }
        return result;
    }
}
