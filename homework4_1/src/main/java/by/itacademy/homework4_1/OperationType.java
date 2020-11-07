package by.itacademy.homework4_1;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class OperationType {
    public static final int CHANGE = 1;
    public static final int REMOVE = 2;

    public OperationType(@Operation int operation) {
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CHANGE, REMOVE})
    public @interface Operation {

    }
}
