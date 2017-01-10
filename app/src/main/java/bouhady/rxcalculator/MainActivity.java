package bouhady.rxcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import bouhady.rxcalculator.controllers.CalculatorController;
import bouhady.rxcalculator.models.CalculationArguments;
import bouhady.rxcalculator.models.CalculationTypes;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "[RxJavaCalculator]";

    @BindView(R.id.a_plus) EditText aPlusEditText;
    @BindView(R.id.b_plus) EditText bPlusEditText;
    @BindView(R.id.result_plus) TextView resultPlusTextView;

    @BindView(R.id.a_minus) EditText aMinusEditText;
    @BindView(R.id.b_minus) EditText bMinusEditText;
    @BindView(R.id.result_minus) TextView resultMinusTextView;

    @BindView(R.id.a_multiple) EditText aMultipleEditText;
    @BindView(R.id.b_multiple) EditText bMultipleEditText;
    @BindView(R.id.result_multiple) TextView resultMultipleTextView;

    @BindView(R.id.a_division) EditText aDivisionEditText;
    @BindView(R.id.b_division) EditText bDivisionEditText;
    @BindView(R.id.result_division) TextView resultDivisionTextView;

    private Observable _aPlusChangeObservable;
    private Observable _bPlusChangeObservable;
    private Observable _aMinusChangeObservable;
    private Observable _bMinusChangeObservable;
    private Observable _aMultipleChangeObservable;
    private Observable _bMultipleChangeObservable;
    private Observable _aDivisionChangeObservable;
    private Observable _bDivisionChangeObservable;

    private Observable<CalculationArguments> plusCalculationObservable;
    private Observable<CalculationArguments> minusCalculationObservable;
    private Observable<CalculationArguments> multipleCalculationObservable;
    private Observable<CalculationArguments> divisionCalculationObservable;

    private Subscriber<Double> _resultPlusObserver;
    private Subscriber<Double> _resultMinusObserver;
    private Subscriber<Double> _resultMultipleObserver;
    private Subscriber<Double> _resultDivisionObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createEditTextsObservables();
        createTextViewSubscribers();
        combineEditTexts();

        CalculatorController calculatorController = new CalculatorController()
                .addCalculation(plusCalculationObservable, _resultPlusObserver , CalculationTypes.PLUS)
                .addCalculation(minusCalculationObservable, _resultMinusObserver, CalculationTypes.MINUS)
                .addCalculation(multipleCalculationObservable, _resultMultipleObserver, CalculationTypes.MULTIPLE)
                .addCalculation(divisionCalculationObservable, _resultDivisionObserver, CalculationTypes.DIVISION);
    }

    Observable getEditTextObservable(final EditText editText) {
        Observable editTextObservable = RxTextView
                .textChanges(editText)
                .skip(1);

        return editTextObservable.filter(new Func1<CharSequence,Boolean>(){
            @Override
            public Boolean call(CharSequence charSequence) {
                boolean aValid = !(charSequence.length() == 0);
                if (!aValid)
                    editText.setError("Invalid!");
                return aValid;
            }
        });
    }

    /**
     * create all the EditTexts Observables
     */
    void createEditTextsObservables(){
        _aPlusChangeObservable = getEditTextObservable(aPlusEditText);
        _bPlusChangeObservable = getEditTextObservable(bPlusEditText);
        _aMinusChangeObservable = getEditTextObservable(aMinusEditText);
        _bMinusChangeObservable = getEditTextObservable(bMinusEditText);
        _aMultipleChangeObservable = getEditTextObservable(aMultipleEditText);
        _bMultipleChangeObservable = getEditTextObservable(bMultipleEditText);
        _aDivisionChangeObservable = getEditTextObservable(aDivisionEditText);
        _bDivisionChangeObservable = getEditTextObservable(bDivisionEditText);

    }

    /**
     * Create an Observable for calculation using two editTexts Observables and filter nulls in order
     * to prevent "OnErrors"
     * @param aInput
     * @param bInput
     * @return the combined Observable
     */
    Observable<CalculationArguments> createCalculationObservable(Observable aInput, Observable bInput){
        return Observable.combineLatest(aInput,bInput,new CombineInputs())
                .filter(new Func1<CalculationArguments,Boolean>() {
                    @Override
                    public Boolean call(CalculationArguments data) {
                        return (data!=null);
                    }
                });
    }

    /**
     * Combine each couple of arguments EditTexts observables into single CalculationArgument observable
     */
    void combineEditTexts(){
        plusCalculationObservable = createCalculationObservable(_aPlusChangeObservable,_bPlusChangeObservable);
        minusCalculationObservable = createCalculationObservable(_aMinusChangeObservable,_bMinusChangeObservable);
        multipleCalculationObservable = createCalculationObservable(_aMultipleChangeObservable,_bMultipleChangeObservable);
        divisionCalculationObservable = createCalculationObservable(_aDivisionChangeObservable,_bDivisionChangeObservable);

    }


    /**
     * An implementation of a generic Subscriber to react and update a single TextView
     * @param resultTextView The TextView to update
     * @return the Subscriber itself
     */
    Subscriber<Double> getTextViewSubscriber(final TextView resultTextView){
        return new Subscriber<Double>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Double aDouble) {
                resultTextView.setText(String.format("%4.4g", aDouble));
            }
        };
    }

    /**
     * create all the TextViewSubscribers for all the results
     */
    void createTextViewSubscribers(){
        _resultPlusObserver = getTextViewSubscriber(resultPlusTextView);
        _resultMinusObserver = getTextViewSubscriber(resultMinusTextView);
        _resultMultipleObserver = getTextViewSubscriber(resultMultipleTextView);
        _resultDivisionObserver = getTextViewSubscriber(resultDivisionTextView);
    }



}
