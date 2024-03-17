package com.szchoiceway.eventcenter.util;

import android.hardware.input.InputManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.szchoiceway.eventcenter.EventUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InputTouchManage {
    public static final int INJECT_INPUT_EVENT_MODE_ASYNC = 0;
    public static final int INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH = 2;
    public static final int INJECT_INPUT_EVENT_MODE_WAIT_FOR_RESULT = 1;
    private static String TAG = "InputTouchManage";
    private static InputTouchManage inputManager;
    private static Method setActionButtonMethod;
    private static Method setDisplayIdMethod;
    private boolean bTouch1Down = false;
    private boolean bTouch2Down = false;
    private Method injectInputEventMethod;
    private long lastTouch2Down;
    private long lastTouchDown;
    public int mAction = 0;
    private long mLastTouch2Down;
    private long mLastTouchDown;
    private int mTouchCount1 = 0;
    private int mTouchCount2 = 0;
    private final Object manager;
    private final MotionEvent.PointerCoords[] pointerCoords = new MotionEvent.PointerCoords[10];
    private final MotionEvent.PointerProperties[] pointerProperties = new MotionEvent.PointerProperties[10];
    private int x1Old = 0;
    private int x2Old = 0;
    private float xOneEnd = 0.0f;
    private float xOneStart = 0.0f;
    private float xTwoEnd = 0.0f;
    private float xTwoStart = 0.0f;
    private int y1Old = 0;
    private int y2Old = 0;
    private float yOneEnd = 0.0f;
    private float yOneStart = 0.0f;
    private float yTwoEnd = 0.0f;
    private float yTwoStart = 0.0f;

    public static InputTouchManage getInputManager() {
        if (inputManager == null) {
            try {
                Class<?> inputManagerClass = getInputManagerClass();
                String str = TAG;
                Log.i(str, "getInputManager inputManagerClass: " + inputManagerClass);
                inputManager = new InputTouchManage(inputManagerClass.getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]));
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new AssertionError(e);
            }
        }
        return inputManager;
    }

    public static Class<?> getInputManagerClass() {
        try {
            return Class.forName("android.hardware.input.InputManagerGlobal");
        } catch (ClassNotFoundException unused) {
            return InputManager.class;
        }
    }

    public InputTouchManage(Object obj) {
        this.manager = obj;
        initPointers();
    }

    private void initPointers() {
        for (int i = 0; i < 10; i++) {
            MotionEvent.PointerProperties pointerProperties2 = new MotionEvent.PointerProperties();
            pointerProperties2.toolType = 1;
            MotionEvent.PointerCoords pointerCoords2 = new MotionEvent.PointerCoords();
            pointerCoords2.orientation = 0.0f;
            pointerCoords2.size = 0.0f;
            this.pointerProperties[i] = pointerProperties2;
            this.pointerCoords[i] = pointerCoords2;
        }
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: type inference failed for: r5v4 */
    public boolean injectTouch(int i, float f, float f2, int i2, float f3, float f4) {
        int i3;
        ? r5;
        int i4;
        String str;
        int i5;
        int i6;
        int i7 = i;
        float f5 = f;
        float f6 = f2;
        float f7 = f3;
        float f8 = f4;
        String str2 = TAG;
        Log.i(str2, "injectTouch touchState1: " + i7 + ", x1 = " + f5 + ", y1 = " + f6);
        String str3 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("injectTouch touchState2: ");
        int i8 = i2;
        sb.append(i8);
        sb.append(", x2 = ");
        sb.append(f7);
        sb.append(", y2 = ");
        sb.append(f8);
        Log.i(str3, sb.toString());
        long uptimeMillis = SystemClock.uptimeMillis();
        this.pointerProperties[0].toolType = 3;
        this.pointerProperties[1].toolType = 3;
        this.pointerProperties[0].id = 0;
        this.pointerProperties[1].id = 1;
        if (i7 == 1) {
            this.lastTouchDown = uptimeMillis;
            if (!this.bTouch1Down) {
                this.bTouch1Down = true;
                this.mAction = 0;
                this.x1Old = (int) f5;
                this.y1Old = (int) f6;
            }
            if (!(((float) this.x1Old) == f5 && ((float) this.y1Old) == f6)) {
                this.x1Old = (int) f5;
                this.y1Old = (int) f6;
                this.mAction = 2;
            }
        } else if (i7 == 0 && this.bTouch1Down) {
            this.mAction = 1;
            this.bTouch1Down = false;
            i8 = 0;
        }
        int i9 = (i7 == 0 || i8 == 0) ? 1 : 2;
        String str4 = TAG;
        Log.i(str4, "injectTouch pointerCount: " + i9);
        if (i8 == 1 && (i5 = (int) f7) != 65535 && (i6 = (int) f8) != 65535) {
            this.mLastTouch2Down = uptimeMillis;
            String str5 = TAG;
            Log.i(str5, "injectTouch bTouch2Down: " + this.bTouch2Down);
            if (!this.bTouch2Down) {
                this.bTouch2Down = true;
                this.x2Old = i5;
                this.y2Old = i6;
                this.mAction = 261;
            }
            if (((float) this.x2Old) != f7 || ((float) this.y2Old) != f8) {
                this.x2Old = i5;
                this.y2Old = i6;
                this.mAction = 2;
            }
        }
        if (i8 == 0 && this.bTouch2Down) {
            this.bTouch2Down = false;
            this.mAction = EventUtils.HANDLER_KEYCODE_BACK_EVENT;
        }
        String str6 = TAG;
        Log.i(str6, "injectTouch mAction: " + this.mAction);
        if (this.mAction == 0) {
            this.pointerCoords[0].x = f5;
            this.pointerCoords[0].y = f6;
            i3 = i9;
            r5 = 0;
            boolean injectEvent = injectEvent(MotionEvent.obtain(this.lastTouchDown, uptimeMillis, 0, i3, this.pointerProperties, this.pointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 4098, 0), 0);
            String str7 = TAG;
            Log.i(str7, "injectTouch ResultDown: " + injectEvent);
            if (!injectEvent) {
                return false;
            }
        } else {
            i3 = i9;
            r5 = 0;
        }
        if (this.mAction == 261) {
            if (i7 == 1) {
                this.pointerCoords[r5].x = f5;
                this.pointerCoords[r5].y = f6;
                boolean injectEvent2 = injectEvent(MotionEvent.obtain(this.lastTouchDown, uptimeMillis, 0, 1, this.pointerProperties, this.pointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 4098, 0), r5);
                String str8 = TAG;
                Log.i(str8, "injectTouch ResultDown1111: " + injectEvent2);
            }
            this.pointerCoords[1].x = f7;
            this.pointerCoords[1].y = f4;
            boolean injectEvent3 = injectEvent(MotionEvent.obtain(this.mLastTouch2Down, uptimeMillis, 261, i3, this.pointerProperties, this.pointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 4098, 0), r5);
            String str9 = TAG;
            Log.i(str9, "injectTouch ResultPOINTER_2_DOWN: " + injectEvent3);
            if (!injectEvent3) {
                return r5;
            }
        }
        if (this.mAction == 2) {
            this.pointerCoords[r5].x = f5;
            this.pointerCoords[r5].y = f6;
            i4 = i3;
            if (i4 == 2) {
                this.pointerCoords[1].x = f7;
                this.pointerCoords[1].y = f4;
            }
            boolean injectEvent4 = injectEvent(MotionEvent.obtain(this.lastTouchDown, uptimeMillis, 2, i4, this.pointerProperties, this.pointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 4098, 0), r5);
            String str10 = TAG;
            Log.i(str10, "injectTouch ResultMove: " + injectEvent4);
        } else {
            i4 = i3;
        }
        if (this.mAction == 262) {
            str = "injectTouch resultUp: ";
            boolean injectEvent5 = injectEvent(MotionEvent.obtain(this.mLastTouch2Down, uptimeMillis, EventUtils.HANDLER_KEYCODE_BACK_EVENT, 2, this.pointerProperties, this.pointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 4098, 0), r5);
            String str11 = TAG;
            Log.i(str11, "injectTouch resultUp1: " + injectEvent5);
            if (!injectEvent5) {
                return r5;
            }
            if (i7 == 0) {
                boolean injectEvent6 = injectEvent(MotionEvent.obtain(this.lastTouchDown, uptimeMillis, 1, i4, this.pointerProperties, this.pointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 4098, 0), r5);
                String str12 = TAG;
                Log.i(str12, str + injectEvent6);
                if (!injectEvent6) {
                    return r5;
                }
            }
        } else {
            str = "injectTouch resultUp: ";
        }
        if (this.mAction != 1) {
            return true;
        }
        boolean injectEvent7 = injectEvent(MotionEvent.obtain(this.lastTouchDown, uptimeMillis, 1, i4, this.pointerProperties, this.pointerCoords, 0, 0, 1.0f, 1.0f, 0, 0, 4098, 0), r5);
        String str13 = TAG;
        Log.i(str13, str + injectEvent7);
        if (!injectEvent7) {
            return r5;
        }
        return true;
    }

    public boolean injectEvent(InputEvent inputEvent, int i) {
        return injectInputEvent(inputEvent, i);
    }

    public boolean injectKeyEvent(int i, int i2, int i3, int i4, int i5) {
        long uptimeMillis = SystemClock.uptimeMillis();
        return injectEvent(new KeyEvent(uptimeMillis, uptimeMillis, i, i2, i3, i4, -1, 0, 0, 257), i5);
    }

    public boolean pressReleaseKeycode(int i, int i2) {
        return injectKeyEvent(0, i, 0, 0, i2) && injectKeyEvent(1, i, 0, 0, i2);
    }

    private Method getInjectInputEventMethod() throws NoSuchMethodException {
        if (this.injectInputEventMethod == null) {
            this.injectInputEventMethod = this.manager.getClass().getMethod("injectInputEvent", new Class[]{InputEvent.class, Integer.TYPE});
        }
        return this.injectInputEventMethod;
    }

    public boolean injectInputEvent(InputEvent inputEvent, int i) {
        try {
            return ((Boolean) getInjectInputEventMethod().invoke(this.manager, new Object[]{inputEvent, Integer.valueOf(i)})).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            String str = TAG;
            Log.e(str, "Could not invoke method e = " + e);
            return false;
        }
    }

    private static Method getSetDisplayIdMethod() throws NoSuchMethodException {
        if (setDisplayIdMethod == null) {
            setDisplayIdMethod = InputEvent.class.getMethod("setDisplayId", new Class[]{Integer.TYPE});
        }
        return setDisplayIdMethod;
    }

    public static boolean setDisplayId(InputEvent inputEvent, int i) {
        try {
            getSetDisplayIdMethod().invoke(inputEvent, new Object[]{Integer.valueOf(i)});
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            String str = TAG;
            Log.e(str, "Cannot associate a display id to the input event e = " + e);
            return false;
        }
    }

    private static Method getSetActionButtonMethod() throws NoSuchMethodException {
        if (setActionButtonMethod == null) {
            setActionButtonMethod = MotionEvent.class.getMethod("setActionButton", new Class[]{Integer.TYPE});
        }
        return setActionButtonMethod;
    }

    public static boolean setActionButton(MotionEvent motionEvent, int i) {
        try {
            getSetActionButtonMethod().invoke(motionEvent, new Object[]{Integer.valueOf(i)});
            return true;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            String str = TAG;
            Log.e(str, "Cannot set action button on MotionEvent e = " + e);
            return false;
        }
    }
}
