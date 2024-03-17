package com.szchoiceway.eventcenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.provider.Settings;

public class CarletterInterface {
    public static final String ALLOW_AUTO_BOOT_ANDROID_AUTO = "allowAutoBootAndroidAuto";
    public static final String ALLOW_AUTO_BOOT_CARPLAY = "allowAutoBootCarplay";
    public static final String ALLOW_AUTO_START_CARPLAY_BY_BT = "allowAutoStartCarplayByBT";
    public static String CLASS_NAME = EventUtils.LETTER_CARPLAY_MODE_CLASS_NAME;
    public static final String CL_CARLETTER_CONNECT_MODE = "carletter_connect_mode";
    public static final String CL_CARLETTER_CONNECT_STATE = "carletter_connect_state";
    public static final String CL_CARLETTER_MEDIA_STATE = "carletter_media_state";
    public static final String CL_CARLETTER_NAVI_STATE = "carletter_navi_state";
    public static final String CL_CARLETTER_RECORDER_STATE = "carletter_recorder_state";
    public static final String CL_CARLETTER_RUNNING_STATE = "carletter_running_state";
    public static final String CL_CARLETTER_TELEPHONE_STATE = "carletter_telephone_state";
    public static final String CL_LINK_CONTROL = "carletter_link_control";
    public static final String CL_NIGHT_MODE = "carletter_night_mode";
    public static final String CL_RESERVE_STATE = "carletter_reserve_state";
    public static String FRAGMENT_TYPE_TAG = "FragmentTypeTag";
    public static final int KEYCODE_BACK = 289;
    public static final int KEYCODE_DOWN = 261;
    public static final int KEYCODE_ENTER = 288;
    public static final int KEYCODE_HOME = 290;
    public static final int KEYCODE_KNOB_LEFT = 256;
    public static final int KEYCODE_KNOB_RIGHT = 257;
    public static final int KEYCODE_LEFT = 258;
    public static final int KEYCODE_MAP = 593;
    public static final int KEYCODE_MEDIA_NEXT = 339;
    public static final int KEYCODE_MEDIA_PAUSE = 337;
    public static final int KEYCODE_MEDIA_PLAY = 336;
    public static final int KEYCODE_MEDIA_PREV = 340;
    public static final int KEYCODE_MEDIA_TOGGLE_PLAY_PAUSE = 338;
    public static final int KEYCODE_MUSIC = 594;
    public static final int KEYCODE_PHONE = 595;
    public static final int KEYCODE_RIGHT = 259;
    public static final int KEYCODE_TEL_ACCEPT = 512;
    public static final int KEYCODE_TEL_MUTE_MIC = 515;
    public static final int KEYCODE_TEL_REJECT = 513;
    public static final int KEYCODE_TEL_TOGGLE_ACCEPT_REJECT = 514;
    public static final int KEYCODE_UNKNOWN = 0;
    public static final int KEYCODE_UP = 260;
    public static final int KEYCODE_VOICE = 592;
    public static String PACKAGE_NAME = EventUtils.LETTER_CARPLAY_MODE_PACKAGE_NAME;
    public static CarletterInterface mInstance = new CarletterInterface();
    private int mConnectMode = 0;
    private int mConnectState = 0;
    private int mMediaState = 0;
    private int mRecorderState = 0;
    private int mRunningState = 0;

    public interface CarletterConnectMode {
        public static final int AIRPLAY = 1;
        public static final int ANDROID_AUTO = 3;
        public static final int ANDROID_LINK = 4;
        public static final int CARLIFE = 5;
        public static final int CARPLAY = 2;
        public static final int DLNA = 6;
        public static final int HICAR = 7;
        public static final int UNKNOWN = 0;
    }

    public interface CarletterConnectState {
        public static final int CONNECTED = 1;
        public static final int DISCONNECTED = 0;
    }

    public interface CarletterMediaState {
        public static final int START = 1;
        public static final int STOP = 0;
    }

    public interface CarletterNaviState {
        public static final int START = 1;
        public static final int STOP = 0;
    }

    public interface CarletterRecorderState {
        public static final int START = 1;
        public static final int STOP = 0;
    }

    public interface CarletterRunningState {
        public static final int BACKGROUND = 1;
        public static final int FOREGROUND = 0;
    }

    public interface CarletterTelephoneState {
        public static final int ALERT = 1;
        public static final int OFF = 0;
        public static final int TALKING = 2;
    }

    public interface FragmentType {
        public static final int AIRPLAY = 1;
        public static final int ANDROID_AUTO = 3;
        public static final int ANDROID_LINK = 4;
        public static final int CARLIFE = 5;
        public static final int CARPLAY = 2;
        public static final int DLNA = 6;
        public static final int HICAR = 7;
    }

    private CarletterInterface() {
    }

    public static CarletterInterface getInstance() {
        return mInstance;
    }

    public void startCarLetter(Context context) {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME));
    }

    public void startCarplay(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
        intent.putExtra(FRAGMENT_TYPE_TAG, 2);
        context.startActivity(intent);
    }

    public void startCarlife(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
        intent.putExtra(FRAGMENT_TYPE_TAG, 5);
        context.startActivity(intent);
    }

    public void startAndroidAuto(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
        intent.putExtra(FRAGMENT_TYPE_TAG, 3);
        context.startActivity(intent);
    }

    public void startAirplay(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
        intent.putExtra(FRAGMENT_TYPE_TAG, 1);
        context.startActivity(intent);
    }

    public void startDLNA(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME));
        intent.putExtra(FRAGMENT_TYPE_TAG, 6);
        context.startActivity(intent);
    }

    public void linkControl(Context context, int i) {
        Settings.System.putInt(context.getContentResolver(), CL_LINK_CONTROL, i);
        context.getContentResolver().notifyChange(Settings.System.getUriFor(CL_LINK_CONTROL), (ContentObserver) null);
    }

    public void allowStartToForeground(Context context, int i) {
        Settings.System.putInt(context.getContentResolver(), ALLOW_AUTO_START_CARPLAY_BY_BT, i);
        context.getContentResolver().notifyChange(Settings.System.getUriFor(ALLOW_AUTO_START_CARPLAY_BY_BT), (ContentObserver) null);
    }

    public boolean isAllowAutoStartCarplayByBT(Context context) {
        return Settings.System.getInt(context.getContentResolver(), ALLOW_AUTO_START_CARPLAY_BY_BT, 0) < 1;
    }

    public void allowAutoBootAndroidAuto(Context context, int i) {
        Settings.System.putInt(context.getContentResolver(), ALLOW_AUTO_BOOT_ANDROID_AUTO, i);
        context.getContentResolver().notifyChange(Settings.System.getUriFor(ALLOW_AUTO_BOOT_ANDROID_AUTO), (ContentObserver) null);
    }

    public void allowAutoBootCarplay(Context context, int i) {
        Settings.System.putInt(context.getContentResolver(), ALLOW_AUTO_BOOT_CARPLAY, i);
        context.getContentResolver().notifyChange(Settings.System.getUriFor(ALLOW_AUTO_BOOT_CARPLAY), (ContentObserver) null);
    }

    public boolean isAllowAutoBootCarplay(Context context) {
        return Settings.System.getInt(context.getContentResolver(), ALLOW_AUTO_BOOT_CARPLAY, 0) < 1;
    }

    public void sendReverseState(Context context, int i) {
        Settings.System.putInt(context.getContentResolver(), CL_RESERVE_STATE, i);
        context.getContentResolver().notifyChange(Settings.System.getUriFor(CL_RESERVE_STATE), (ContentObserver) null);
    }

    public boolean isNightMode(Context context) {
        return Settings.System.getInt(context.getContentResolver(), CL_NIGHT_MODE, 0) == 1;
    }

    public void sendNightMode(Context context, int i) {
        Settings.System.putInt(context.getContentResolver(), CL_NIGHT_MODE, i);
        context.getContentResolver().notifyChange(Settings.System.getUriFor(CL_NIGHT_MODE), (ContentObserver) null);
    }
}
