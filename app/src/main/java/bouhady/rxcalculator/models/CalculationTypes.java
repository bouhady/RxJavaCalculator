package bouhady.rxcalculator.models;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Yaniv Bouhadana on 09/01/2017.
 */
public class CalculationTypes{
    @Retention(SOURCE)
    @IntDef({PLUS,MINUS,MULTIPLE, DIVISION})
    public @interface CalculationType {}
    public static final int PLUS = 0 ;
    public static final int MINUS = 1 ;
    public static final int MULTIPLE = 2 ;
    public static final int DIVISION = 3 ;
}

