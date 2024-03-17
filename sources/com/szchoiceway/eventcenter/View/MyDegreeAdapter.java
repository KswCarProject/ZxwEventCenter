package com.szchoiceway.eventcenter.View;

import android.graphics.Point;

public class MyDegreeAdapter {
    private static final double PI = 3.141592653589793d;

    enum _Quadrant {
        eQ_NONE,
        eQ_ONE,
        eQ_TWO,
        eQ_THREE,
        eQ_FOUR
    }

    public static _Quadrant GetQuadrant(Point point) {
        if (point.x == 0 || point.y == 0) {
            return _Quadrant.eQ_NONE;
        }
        if (point.x > 0) {
            if (point.y > 0) {
                return _Quadrant.eQ_ONE;
            }
            return _Quadrant.eQ_TWO;
        } else if (point.y < 0) {
            return _Quadrant.eQ_THREE;
        } else {
            return _Quadrant.eQ_FOUR;
        }
    }

    public static int GetRadianByPos(Point point) {
        return (int) (GetRadianByPosEx(point) * 57.29577951308232d);
    }

    private static double GetRadianByPosEx(Point point) {
        if (point.x == 0 && point.y == 0) {
            return 0.0d;
        }
        double asin = Math.asin(((double) point.x) / Math.sqrt((double) ((point.x * point.x) + (point.y * point.y))));
        int i = AnonymousClass1.$SwitchMap$com$szchoiceway$eventcenter$View$MyDegreeAdapter$_Quadrant[GetQuadrant(point).ordinal()];
        if (i != 1) {
            if (i == 2) {
                return asin;
            }
            if (i == 3 || i == 4) {
                return PI - asin;
            }
            if (i != 5) {
                return asin;
            }
            return asin + 6.283185307179586d;
        } else if (point.x == 0 && point.y == 0) {
            return 0.0d;
        } else {
            if (point.x == 0) {
                if (point.y > 0) {
                    return 0.0d;
                }
                return PI;
            } else if (point.y == 0) {
                return point.x > 0 ? 1.5707963267948966d : 4.71238899230957d;
            } else {
                return asin;
            }
        }
    }

    /* renamed from: com.szchoiceway.eventcenter.View.MyDegreeAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$szchoiceway$eventcenter$View$MyDegreeAdapter$_Quadrant;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.szchoiceway.eventcenter.View.MyDegreeAdapter$_Quadrant[] r0 = com.szchoiceway.eventcenter.View.MyDegreeAdapter._Quadrant.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$szchoiceway$eventcenter$View$MyDegreeAdapter$_Quadrant = r0
                com.szchoiceway.eventcenter.View.MyDegreeAdapter$_Quadrant r1 = com.szchoiceway.eventcenter.View.MyDegreeAdapter._Quadrant.eQ_NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$szchoiceway$eventcenter$View$MyDegreeAdapter$_Quadrant     // Catch:{ NoSuchFieldError -> 0x001d }
                com.szchoiceway.eventcenter.View.MyDegreeAdapter$_Quadrant r1 = com.szchoiceway.eventcenter.View.MyDegreeAdapter._Quadrant.eQ_ONE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$szchoiceway$eventcenter$View$MyDegreeAdapter$_Quadrant     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.szchoiceway.eventcenter.View.MyDegreeAdapter$_Quadrant r1 = com.szchoiceway.eventcenter.View.MyDegreeAdapter._Quadrant.eQ_TWO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$szchoiceway$eventcenter$View$MyDegreeAdapter$_Quadrant     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.szchoiceway.eventcenter.View.MyDegreeAdapter$_Quadrant r1 = com.szchoiceway.eventcenter.View.MyDegreeAdapter._Quadrant.eQ_THREE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$szchoiceway$eventcenter$View$MyDegreeAdapter$_Quadrant     // Catch:{ NoSuchFieldError -> 0x003e }
                com.szchoiceway.eventcenter.View.MyDegreeAdapter$_Quadrant r1 = com.szchoiceway.eventcenter.View.MyDegreeAdapter._Quadrant.eQ_FOUR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.View.MyDegreeAdapter.AnonymousClass1.<clinit>():void");
        }
    }
}
