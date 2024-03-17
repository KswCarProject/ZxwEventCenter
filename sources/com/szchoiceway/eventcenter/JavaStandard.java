package com.szchoiceway.eventcenter;

import com.szchoiceway.eventcenter.function.Function1;
import com.szchoiceway.eventcenter.function.Function1Void;

public class JavaStandard {
    public static boolean isInRange(double d, double d2, double d3) {
        return d >= d2 && d <= d3;
    }

    public static boolean isInRange(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    public static boolean isUntilRange(double d, double d2, double d3) {
        return d >= d2 && d < d3;
    }

    public static boolean isUntilRange(int i, int i2, int i3) {
        return i >= i2 && i < i3;
    }

    public static <T> T also(T t, Function1Void<T> function1Void) {
        function1Void.invoke(t);
        return t;
    }

    public static <T, R> R let(T t, Function1<T, R> function1) {
        return function1.invoke(t);
    }

    public static <T> T runIf(T t, Function1<T, Boolean> function1, Function1Void<T> function1Void) {
        if (function1.invoke(t).booleanValue()) {
            function1Void.invoke(t);
        }
        return t;
    }

    public static <T> void runIfNonNull(T t, Function1Void<T> function1Void) {
        runIf(t, new Function1<T, Boolean>() {
            public Boolean invoke(T t) {
                return Boolean.valueOf(t != null);
            }
        }, function1Void);
    }

    public static void repeat(int i, Function1Void<Integer> function1Void) {
        for (int i2 = 0; i2 < i; i2++) {
            function1Void.invoke(Integer.valueOf(i2));
        }
    }

    private JavaStandard() {
    }
}
