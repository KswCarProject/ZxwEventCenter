package com.szchoiceway.eventcenter;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.Log;
import android.widget.Toast;
import com.example.mylibrary.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public class EventUtils {
    public static final int ACC_CHANGE_EVENT = 4106;
    public static final String ACTION_ACC_SLEEP_STATUS_EVT = "com.szchoiceway.eventcenter.EventUtils.ACTION_ACC_SLEEP_STATUS_EVT";
    public static final String ACTION_ACC_SLEEP_STATUS_EXTRA = "ACC_Status";
    public static final String ACTION_CARPLAY_DISABLE_AP = "com.zjinnova.zlink.action.DISABLE_AP";
    public static final String ACTION_CARPLAY_ENABLE_AP = "com.zjinnova.zlink.action.ENABLE_AP";
    public static final String ACTION_CARPLAY_TELEPHONE_STATUS_DATA = "EventUtils.ACTION_CARPLAY_TELEPHONE_STATUS_DATA";
    public static final String ACTION_CARPLAY_TELEPHONE_STATUS_EVENT = "com.szchoiceway.eventcenter.EventUtils.ACTION_CARPLAY_TELEPHONE_STATUS_EVENT";
    public static final String ACTION_CHANGE_MODE_EVENT = "com.szchoiceway.eventcenter.EventUtils.ACTION_CHANGE_MODE_EVENT";
    public static final String ACTION_DISMISS_SPLITSCREEN = "dismissSplitScreen";
    public static final String ACTION_FORCE_CAMERA_CLOSE = "com.szchoiceway.eventcenter.force_camera_close";
    public static final String ACTION_HIDE_VOICE_WND = "com.choiceway.eventcenter.ZXW.TXZ.ACTION_HIDE_VOICE_WND";
    public static final String ACTION_ICON_DISPLAY = "com.szchoiceway.settings.ICON_DISPLAY";
    public static final String ACTION_MCU_CMD_EVENT = "com.szchoiceway.eventcenter.EventUtils.ACTION_MCU_CMD_EVENT";
    public static final String ACTION_MODE_DATA = "EventUtils.ACTION_MODE_DATA";
    public static final String ACTION_NAVI_START_PLAY_SOUND = "com.mxnavi.broadcast.startplaysound";
    public static final String ACTION_NAVI_STOP_PLAY_SOUND = "com.mxnavi.broadcast.stopplaysound";
    public static final String ACTION_REFRESH_PHONE_SIGNAL = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL";
    public static final String ACTION_REFRESH_PHONE_SIGNAL2 = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL2";
    public static final String ACTION_REFRESH_PHONE_SIGNAL_DATA_LEVEL = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL_DATA_LEVEL";
    public static final String ACTION_REFRESH_PHONE_SIGNAL_DATA_STATE = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL_DATA_STATE";
    public static final String ACTION_REFRESH_PHONE_SIGNAL_DATA_TYPE = "com.choiceway.eventcenter.EventUtils.ACTION_REFRESH_PHONE_SIGNAL_DATA_TYPE";
    public static final String ACTION_SETTINGS_FACTORY_RESET = "com.android.settings.action.FactoryReset";
    public static final String ACTION_SHOW_TASK_LIST = "com.szchoiceway.eventcenter.EventUtils.ACTION_SHOW_TASK_LIST";
    public static final String ACTION_SHOW_VOICE_WND = "com.choiceway.eventcenter.ZXW.TXZ.ACTION_SHOW_VOICE_WND";
    public static final String ACTION_SPLITSCREEN = "com.szchoiceway.systemui.Splitscreen";
    public static final String ACTION_START_CHW_TALK = "com.szchoiceway.ACTION_START_CHW_TALK";
    public static final String ACTION_START_TALK = "com.unisound.intent.action.START_TALK";
    public static final String ACTION_SWITCH_ORIGINACAR = "com.szchoiceway.eventcenter.EventUtils.ACTION_SWITCH_ORIGINACAR";
    public static final String ACTION_SWITCH_SPLITSCREEN = "com.szchoiceway.systemui.switchSplitscreen";
    public static final String ACTION_SWITCH_USBDVRMODE = "com.szchoiceway.ACTION_SWITCH_USBDVRMODE";
    public static final String ACTION_SWITCH_USBDVRMODE_EXTRA = "com.szchoiceway.ACTION_SWITCH_USBDVRMODE_EXTRA";
    public static final String ACTION_UIMODE_CHANGED = "com.szchoiceway.uiModeNightChanged";
    public static final String ACTION_UNISOUND_VOICE_END = "com.unisound.intent.action.ACTION_UNISOUND_VOICE_END";
    public static final String ACTION_UNISOUND_VOICE_START = "com.unisound.intent.action.ACTION_UNISOUND_VOICE_START";
    public static final byte ADDR_CMD_DVR = 29;
    public static final String ANXIAO_MODE_PACKAGE_NAME = "com.atelectronic.atavm3d";
    public static final String APKINSTALL_MODE_PACKAGE_NAME = "com.szchoiceway.apkinstall";
    public static final String AUX_MODE_CLASS_NAME = "com.szchoiceway.navigation.AUXActivity";
    public static final String AUX_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final String BACKCAR_SERVICE_NAME = "com.szchoiceway.backcarevent.BackcarService";
    public static final String BROADCAST_KSW_DVD_MUSIC_SWITCH_VIDEO_VIEW = "com.choiceway.eventcenter.EventUtils.BROADCAST_KSW_DVD_MUSIC_SWITCH_VIDEO_VIEW";
    public static final String BROADCAST_KSW_DVD_MUSIC_SWITCH_VIDEO_VIEW_DATA = "com.choiceway.eventcenter.EventUtils.BROADCAST_KSW_DVD_MUSIC_SWITCH_VIDEO_VIEW_DATA";
    public static final String BROADCAST_VALID_MODE_EVT = "com.szchoiceway.eventcenter.EventUtils.BROADCAST_VALID_MODE_EVT";
    public static final String BTMUSIC_MODE_CLASS_NAME = "com.szchoiceway.btsuite.BTMusicActivity";
    public static final String BT_CallRecordKey = "CallRecordPage";
    public static final String BT_DialPageKey = "DialPage";
    public static final String BT_MODE_CLASS_NAME = "com.szchoiceway.btsuite.BTMainActivity";
    public static final String BT_MODE_PACKAGE_NAME = "com.szchoiceway.btsuite";
    public static final String BT_MODE_SERVICE_NAME = "com.szchoiceway.btsuite.BTService";
    public static final String BT_MusicPageKey = "BTMusic";
    public static final String BT_PhoneBookPageKey = "PhoneBookPage";
    public static final String BT_SetPageKey = "SetPage";
    public static final String BT_TransmitSetPageKey = "TransmitSetPage";
    public static final int CAMERA_OWNER_AUX = 1;
    public static final int CAMERA_OWNER_BACKCAR = 0;
    public static final int CAMERA_OWNER_CAR_MONITOR = 2;
    public static final int CAMERA_OWNER_DVD = 4;
    public static final int CAMERA_OWNER_DVR = 3;
    public static final int CAMERA_OWNER_INVALID = -1;
    public static final int CAMERA_OWNER_TV = 5;
    public static final String CAN_SHOW_AIR_INFO_EVT = "com.choiceway.eventcenter.CanUtils.CAN_SHOW_AIR_INFO_EVT";
    public static final String CARLINK_MODE_CLASS_NAME = "com.ucarhu.carlink.UCarDemoActivity";
    public static final String CARLINK_MODE_PACKAGE_NAME = "com.ucarhu.carlink";
    public static final int CARTYPE_Camery_HI = 37;
    public static final int CARTYPE_Camery_LOW = 36;
    public static final String CAR_AIR_DATA = "EventUtils.CAR_AIR_DATA";
    public static final String CAR_CAN_DATA = "com.szchoiceway.eventcenter.CAR_CAN_DATA";
    public static final String CHEKU_BOTTOM_KEY = "com.szchoiceway.eventcenter.EventUtils.CHEKU_BOTTOM_KEY";
    public static final int CHEKU_BOTTOM_KEY_1 = 1;
    public static final int CHEKU_BOTTOM_KEY_10 = 10;
    public static final int CHEKU_BOTTOM_KEY_2 = 2;
    public static final int CHEKU_BOTTOM_KEY_3 = 3;
    public static final int CHEKU_BOTTOM_KEY_4 = 4;
    public static final int CHEKU_BOTTOM_KEY_5 = 5;
    public static final int CHEKU_BOTTOM_KEY_6 = 6;
    public static final int CHEKU_BOTTOM_KEY_7 = 7;
    public static final int CHEKU_BOTTOM_KEY_8 = 8;
    public static final int CHEKU_BOTTOM_KEY_9 = 9;
    public static final String CHEKU_BOTTOM_KEY_DATA = "com.szchoiceway.eventcenter.EventUtils.CHEKU_BOTTOM_KEY_DATA";
    public static final String CHEKU_BOTTOM_KEY_DATA_PARKDOWN = "com.szchoiceway.eventcenter.EventUtils.CHEKU_BOTTOM_KEY_DATA_PARKDOWN";
    public static final String CHEKU_BOTTOM_KEY_PARK = "com.szchoiceway.eventcenter.EventUtils.CHEKU_BOTTOM_KEY_PARK";
    public static final String CHEKU_BOTTOM_KEY_PARK_DATA = "com.szchoiceway.eventcenter.EventUtils.CHEKU_BOTTOM_KEY_PARK_DATA";
    public static final String CHEKU_WEATHER_INIT_REFRESH_WEATHER = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_INIT_REFRESH_WEATHER";
    public static final String CHEKU_WEATHER_IPC = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC";
    public static final String CHEKU_WEATHER_IPC_CityName = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_CityName";
    public static final String CHEKU_WEATHER_IPC_UpdateInfor = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_UpdateInfor";
    public static final String CHEKU_WEATHER_IPC_WeatherDay = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherDay";
    public static final String CHEKU_WEATHER_IPC_WeatherIcon = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherIcon";
    public static final String CHEKU_WEATHER_IPC_WeatherInfor = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherInfor";
    public static final String CHEKU_WEATHER_IPC_WeatherTemp = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherTemp";
    public static final String CHEKU_WEATHER_IPC_WeatherTitle = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherTitle";
    public static final String CHEKU_WEATHER_SETCITYCODE = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_SETCITYCODE";
    public static final byte CMD_8836_TO_ARM_CHEKU = -90;
    public static final byte CMD_8902_BACKCAR_SET = 110;
    public static final byte CMD_ARM_SEND_0x49 = 73;
    public static final byte CMD_ARM_SEND_PASSWORD = 30;
    public static final byte CMD_ARM_SYS_RTC_TIME = 19;
    public static final byte CMD_ARM_TO_8836_CHEKU = 48;
    public static final byte CMD_BACK_RADAR = 32;
    public static final byte CMD_BAL_FAD_VAL = 122;
    public static final byte CMD_BEEP = 6;
    public static final byte CMD_BMT_VAL = 118;
    public static final byte CMD_BREAK_STATE = 28;
    public static final byte CMD_BT_POWER = 24;
    public static final byte CMD_BT_STATE = 11;
    public static final byte CMD_CAMRY_CAN_BAIC_INFO = 36;
    public static final byte CMD_CAMRY_CAN_CURRENT_TRIP = 34;
    public static final byte CMD_CAMRY_CAN_HISTORY_TRIP = 35;
    public static final byte CMD_CAMRY_CAN_MIN_TRIP = 39;
    public static final byte CMD_CAMRY_CAN_TPMS_STATUS = 37;
    public static final byte CMD_CAMRY_CAN_TRIP_INFO = 33;
    public static final byte CMD_CAMRY_CAN_VEHICLE_SETTINGS = 38;
    public static final byte CMD_CANBUS_ADDRESS = 13;
    public static final byte CMD_CANBUS_ALL_ADD = -91;
    public static final byte CMD_CAN_AIR = -96;
    public static final byte CMD_CAR_AIR = -95;
    public static final byte CMD_CAR_INFO = -76;
    public static final byte CMD_CMMB_POWER = 22;
    public static final byte CMD_CRV_SIYU_COMPASS_STATUS = -46;
    public static final byte CMD_CRV_SIYU_MEDIA_STATUS = 33;
    public static final byte CMD_CRV_SIYU_TIME_STATUS = -47;
    public static final byte CMD_DISC_AUTO_IN = -126;
    public static final byte CMD_DSP_TYTE = 119;
    public static final byte CMD_DVD_FLIE_TYPE = 18;
    public static final byte CMD_DVD_TYPE = 33;
    public static final byte CMD_FACTORY_SET = 15;
    public static final byte CMD_FM_EVENT = 115;
    public static final byte CMD_FM_KEY = 2;
    public static final byte CMD_FREQ_SEL = Byte.MIN_VALUE;
    public static final byte CMD_GPS_SOUND_TYPE = 20;
    public static final byte CMD_HEAD_RADAR = 35;
    public static final byte CMD_KEY_AD = 126;
    public static final byte CMD_KEY_EVENT = 114;
    public static final byte CMD_LEAVE_CAMERAS = 67;
    public static final byte CMD_LOUND = 123;
    public static final byte CMD_MAIN_VOL = 121;
    public static final byte CMD_MCU_CONTROL_BLACK_SCREEN = -124;
    public static final byte CMD_MCU_INIT = 124;
    public static final byte CMD_MCU_SEND_PASSWORD = -123;
    public static final byte CMD_MCU_UPDATA = -75;
    public static final byte CMD_MCU_UPGRADE = 14;
    public static final byte CMD_MENU_MOVE_DIR = 52;
    public static final byte CMD_MENU_TOUCH_POS = 51;
    public static final byte CMD_MODE = 1;
    public static final byte CMD_MODE_ASK = 112;
    public static final byte CMD_MODE_POWERON = -127;
    public static final byte CMD_MUTE = 120;
    public static final byte CMD_NOTIFY_MCU_GPSMODE = 39;
    public static final byte CMD_PLAY_STATE = 25;
    public static final byte CMD_RADIO_ONOFF = 125;
    public static final byte CMD_RESET_MCU = 59;
    public static final byte CMD_SEND_4GRESET = 73;
    public static final byte CMD_SEND_8825_VALUE = 35;
    public static final byte CMD_SEND_APP_3Dh_INFO = -114;
    public static final byte CMD_SEND_AROUND_VALUE = 47;
    public static final byte CMD_SEND_AUDIO_VALUE = 34;
    public static final byte CMD_SEND_AUDIO_VALUE_0x40 = 64;
    public static final byte CMD_SEND_BAUD_RATE = 69;
    public static final byte CMD_SEND_BLACK_SCREEN = 31;
    public static final byte CMD_SEND_DIM = 36;
    public static final byte CMD_SEND_GPS_VOL = 38;
    public static final byte CMD_SEND_INFOR = 64;
    public static final byte CMD_SEND_INFO_2 = 16;
    public static final byte CMD_SEND_INTO_UPGRADE = 70;
    public static final byte CMD_SEND_KEY_MUTE = 17;
    public static final byte CMD_SEND_MCU_RESET = 59;
    public static final byte CMD_SEND_NAV_SND_STATE = 63;
    public static final byte CMD_SEND_NOTIFY_HAS_RADAR_SIGNAL = 41;
    public static final byte CMD_SEND_PWM_VALUE = 46;
    public static final byte CMD_SEND_RADA = 40;
    public static final byte CMD_SEND_STUDY_KEY_FLAG = -120;
    public static final byte CMD_SEND_TPMS_DATA = 37;
    public static final byte CMD_SET_BMT_VAL = 23;
    public static final byte CMD_SET_FM_FREQ = 12;
    public static final byte CMD_SET_MUTE = 10;
    public static final byte CMD_SRC_ARMUI = 49;
    public static final byte CMD_SWITCH_CAR_SCREEN = -79;
    public static final byte CMD_SW_VOL = 21;
    public static final byte CMD_SYS_EQ = 9;
    public static final byte CMD_SYS_EVENT = 113;
    public static final byte CMD_SYS_KEY = 8;
    public static final byte CMD_SYS_RTC_TIME = -125;
    public static final byte CMD_SYS_SET = 5;
    public static final byte CMD_TOUCH_BTN_KEY = 27;
    public static final byte CMD_TOUCH_POS = 4;
    public static final byte CMD_TOUCH_XY = -78;
    public static final byte CMD_TV_EVENT = 117;
    public static final byte CMD_TV_KEY = 3;
    public static final byte CMD_UPGRADE_ACK = Byte.MAX_VALUE;
    public static final byte CMD_VALID_INFO_CHANGE = 66;
    public static final byte CMD_VIDEO_FORMAT = 26;
    public static final byte CMD_WHEEL = 7;
    public static final byte CMD_WHEEL_STATE = 116;
    public static final String CMMB_MODE_CLASS_NAME = "com.szchoiceway.tvplayer.MainActivity";
    public static final String CMMB_MODE_PACKAGE_NAME = "com.szchoiceway.tvplayer";
    public static final String DASHBOARD_MODE_CLASS_NAME = "com.szchoiceway.ksw_dashboard.MainActivity";
    public static final String DASHBOARD_MODE_PACKAGE_NAME = "com.szchoiceway.ksw_dashboard";
    public static final String DDBOX_KEY_NEXT = "com.glsx.ddbox.audiofocus.playnext";
    public static final String DDBOX_KEY_PLAY_PAUSE = "com.glsx.ddbox.audiofocus.play_pause";
    public static final String DDBOX_KEY_PREV = "com.glsx.ddbox.audiofocus.playpre";
    public static final String DVD_KSW_MODE_CLASS_NAME = "com.szchoiceway.ksw_dvd.MainActivity";
    public static final String DVD_KSW_MODE_PACKAGE_NAME = "com.szchoiceway.ksw_dvd";
    public static final String DVD_MODE_CLASS_NAME = "com.szchoiceway.dvdplayer.MainActivity";
    public static final String DVD_MODE_PACKAGE_NAME = "com.szchoiceway.dvdplayer";
    public static final String DVR_MODE_CLASS_NAME = "com.szchoiceway.dvrplayer.DvrmainActivity";
    public static final String DVR_MODE_PACKAGE_NAME = "com.szchoiceway.dvrplayer";
    public static final String ESTRONGS_MODE_CLASS_NAME = "com.estrongs.android.pop.view.FileExplorerActivity";
    public static final String ESTRONGS_MODE_PACKAGE_NAME = "com.estrongs.android.pop";
    public static final String ESUPER_MODE_CLASS_NAME = "com.frames.filemanager.module.activity.FirstActivity";
    public static final String ESUPER_MODE_PACKAGE_NAME = "com.es.file.explorer.manager";
    public static final int EVENT_BACKCAR_END = 4100;
    public static final int EVENT_BACKCAR_START = 4099;
    public static final int EVENT_BT_EVENT = 4102;
    public static final int EVENT_CAMERA_STATUS = 4107;
    public static final int EVENT_CAN_GATHER_END = 4110;
    public static final String EVENT_DISCONNECT_BT = "EventUtils.EVENT_DISCONNECT_BT";
    public static final int EVENT_KEY_EVENT = 4098;
    public static final int EVENT_MODE_CHANGE = 4097;
    public static final int EVENT_POWER = 4101;
    public static final int EVENT_START = 4096;
    public static final String EVT_BACKCAR_NAME = "BACKCAR_STATE";
    public static final int EVT_END_BACKCAR = 502;
    public static final int EVT_START_BACKCAR = 501;
    public static final String EXPLORER_MODE_CLASS_NAME = "com.google.android.apps.chrome.Main";
    public static final String EXPLORER_MODE_PACKAGE_NAME = "com.android.chrome";
    public static final String EXTRA_SHOW_VOLUME_BAR = "EXTRA_VOLUME";
    public static final String FATSET_MODE_PACKAGE_NAME = "com.szchoiceway.fatset";
    public static final byte FM_BAND_NUM = 1;
    public static final byte FM_CURR_FREQ = 3;
    public static final byte FM_FREQ_LIST = 4;
    public static final byte FM_PS_NAME = 6;
    public static final byte FM_PTY_TYPE = 5;
    public static final byte FM_STATE = 0;
    public static final byte FM_TUNER_NUM = 2;
    public static final String GOOGLE_MAP_MODE_CLASS_NAME = "com.google.android.maps.MapsActivity";
    public static final String GOOGLE_MAP_MODE_PACKAGE_NAME = "com.google.android.apps.maps";
    public static final int HANDLER_ACC_DISCONNECTED = 255;
    public static final int HANDLER_ACC_SLEEP_LAST_MODE = 290;
    public static final int HANDLER_AUTO_RUN_GPS = 240;
    public static final int HANDLER_AVM_MODE_START = 257;
    public static final int HANDLER_AVM_MODE_START_LEFT_RIGHT_BACK = 265;
    public static final int HANDLER_AVM_MODE_START_LEFT_RIGHT_BACK_END = 266;
    public static final int HANDLER_BACKCAR_END = 251;
    public static final int HANDLER_BACKCAR_END_CHEKU = 277;
    public static final int HANDLER_BACKCAR_END_KSW = 271;
    public static final int HANDLER_BACKCAR_END_REMOVE_CAMERA_PIP_DELAY = 2570;
    public static final int HANDLER_BACKCAR_START = 250;
    public static final int HANDLER_BACKCAR_START_CHEKU = 274;
    public static final int HANDLER_BACKCAR_START_KSW = 270;
    public static final int HANDLER_BACKLIGHT_DELAY_KSW = 272;
    public static final int HANDLER_BACKLIGHT_END_KSW = 279;
    public static final int HANDLER_BACKLIGHT_START_KSW = 276;
    public static final int HANDLER_BREAK_EVT = 254;
    public static final int HANDLER_BT_NORMAL_HOME_DELAY_KSW = 273;
    public static final int HANDLER_CAN_KEY_EVENT = 249;
    public static final int HANDLER_CUR_TOPACTIVITY_REFRESH = 2578;
    public static final int HANDLER_DAB_TIMER = 2572;
    public static final int HANDLER_DELAY_0X15_EVENT = 3003;
    public static final int HANDLER_DELAY_0X15_EVENT2 = 3008;
    public static final int HANDLER_DELAY_CHECK_OK_NOTIFY_MCU_UPGRADE = 2576;
    public static final int HANDLER_DELAY_EXIT_MEDIA_CHANNAL = 2577;
    public static final int HANDLER_DELAY_LAND = 2567;
    public static final int HANDLER_DELAY_MOUSE_APP = 2581;
    public static final int HANDLER_DELAY_MOUSE_HIDE = 2579;
    public static final int HANDLER_DELAY_MOUSE_SHOW = 2580;
    public static final int HANDLER_DELAY_OS_VIDEO_CHENNAL_BORUIZONGHENG = 2582;
    public static final int HANDLER_DELAY_TOUCH_LONG_CHEKU = 2583;
    public static final int HANDLER_DVD_BACKCAR_DELAY = 2569;
    public static final int HANDLER_DVD_CONNECTED_EVT = 247;
    public static final int HANDLER_DVD_DISCONNECTED_EVT = 248;
    public static final int HANDLER_EXECUTE_LAST_GPS_MDOE = 261;
    public static final int HANDLER_EXECUTE_LAST_MDOE = 256;
    public static final int HANDLER_HEART_BEAT_TIMER = 2574;
    public static final int HANDLER_INIT_EVENT_STATE = 1246;
    public static final int HANDLER_INIT_SCREEN_ORIEN = 1244;
    public static final int HANDLER_KEYCODE_BACK_EVENT = 262;
    public static final int HANDLER_LAMP_EVT = 253;
    public static final int HANDLER_LANGUAGE_CHANGE_SEND_TO_OS_DATA = 10001;
    public static final int HANDLER_LEFT_CAMERA_END = 264;
    public static final int HANDLER_LEFT_CAMERA_START = 263;
    public static final int HANDLER_LONG_TOUCH_CALIBRATE = 2573;
    public static final int HANDLER_MAISILUO_ACC_OFF_ACK = 267;
    public static final int HANDLER_MENU_PRESS_TOUCH_POS = 2568;
    public static final int HANDLER_MENU_TOUCH_POS = 252;
    public static final int HANDLER_ORIGINAL_END_KSW = 278;
    public static final int HANDLER_ORIGINAL_START_KSW = 275;
    public static final int HANDLER_OS_BACKCAR_END = 237;
    public static final int HANDLER_QUIT = 256;
    public static final int HANDLER_REPREAD_SEND_MAISILUO_MCUUPGRADE = 268;
    public static final int HANDLER_RESPONSE_KEY = 280;
    public static final int HANDLER_RIGHT_CAMERA_END = 260;
    public static final int HANDLER_RIGHT_CAMERA_START = 259;
    public static final int HANDLER_RUN_LAST_MODE = 232;
    public static final int HANDLER_SD_CONNECTED_EVT = 245;
    public static final int HANDLER_SD_DISCONNECTED_EVT = 246;
    public static final int HANDLER_SEND_HEARTBEAT_EVENT = 267;
    public static final int HANDLER_SEND_SYS_TIME_TO_CAN = 258;
    public static final int HANDLER_SEND_TOUCH_POS_ZHTY_LONG = 269;
    public static final int HANDLER_START_GPS_LOC_LISTENER = 2571;
    public static final int HANDLER_START_MODE_SERVICE = 462849;
    public static final String HBCP_EVT_HSHF_GET_STATUS = "com.szchoiceway.btsuite.HBCP_EVT_HSHF_GET_STATUS";
    public static final String HBCP_EVT_HSHF_STATUS = "com.szchoiceway.btsuite.HBCP_EVT_HSHF_STATUS";
    public static final int HBCP_STATUS_HSHF_ACTIVE_CALL = 6;
    public static final int HBCP_STATUS_HSHF_CONNECTED = 3;
    public static final int HBCP_STATUS_HSHF_CONNECTING = 2;
    public static final int HBCP_STATUS_HSHF_INCOMING_CALL = 5;
    public static final int HBCP_STATUS_HSHF_INITIALISING = 0;
    public static final int HBCP_STATUS_HSHF_OUTGOING_CALL = 4;
    public static final int HBCP_STATUS_HSHF_READY = 1;
    public static final String HICAR_MODE_CLASS_NAME = "com.huawei.hicar.app.HiCarActivity";
    public static final String HICAR_MODE_PACKAGE_NAME = "com.huawei.hicar";
    public static final String HIDE_EXPAND_ICON = "android.intent.statusbar.HIDE_EXPAND_ICON";
    public static final int HIDE_VOL_DIALOG = 8;
    public static final String INTENT_EXTRA_INT_KEYNAME = "com.szchoiceway.btsuite.DATA_INT";
    public static final String INTENT_EXTRA_STR_KEYNAME = "com.szchoiceway.btsuite.DATA_STR";
    public static final byte KESAIWEI_1280X480_0XA1_0X10_DATA = 96;
    public static final byte KESAIWEI_1280X480_AIR_TEMP = 95;
    public static final byte KESAIWEI_1280X480_BELT_BRAKE = 91;
    public static final byte KESAIWEI_1280X480_CALLBACK_GPS_FOCUS = 92;
    public static final byte KESAIWEI_1280X480_CALLBACK_STATE = 90;
    public static final byte KESAIWEI_1280X480_DOOR_INFO = 93;
    public static final String KESAIWEI_EQ_MODE_SELECT = "COM.KESAIWEI_EQ_MODE_SELECT";
    public static final String KESAIWEI_EQ_USER_HIGHT = "COM.KESAIWEI_EQ_USER_HIGHT";
    public static final String KESAIWEI_EQ_USER_LOW = "COM.KESAIWEI_EQ_USER_LOW";
    public static final String KESAIWEI_EQ_USER_MID = "COM.KESAIWEI_EQ_USER_MID";
    public static final String KEY_AUDIO_SETTING_0X40 = "com.choiceway.KEY_AUDIO_SETTING_0X40";
    public static final String KEY_BAL_SETTINGS = "COM.SZCHOICEWAY_BAL_SETTINGS";
    public static final String KEY_BASS_FREQ_SETTINGS = "COM.SZCHOICEWAY_BASS_FREQ_SETTINGS";
    public static final String KEY_BASS_SETTINGS = "COM.SZCHOICEWAY_BASS_SETTINGS";
    public static final String KEY_BRIGHTNESS_SETTINGS = "COM.SZCHOICEWAY_BRIGHTNESS_SETTINGS";
    public static final String KEY_BackcarFullview = "Set_BackcarFullview";
    public static final String KEY_BackcarRadar = "Set_BackcarRadar";
    public static final String KEY_BackcarTrack = "Set_BackcarTrack";
    public static final String KEY_CHECKASSISTIVE_TOUCH_SETTINGS = "COM.SZCHOICEWAY_CHECKASSISTIVE_TOUCH_SETTINGS";
    public static final String KEY_EQ_MODE_SETTINGS = "COM.SZCHOICEWAY_EQ_MODE_SETTINGS";
    public static final String KEY_FAD_SETTINGS = "COM.SZCHOICEWAY_FAD_SETTINGS";
    public static final String KEY_KEYDOWNSND_SETTINGS = "COM.SZCHOICEWAY_KEYDOWNSND_SETTINGS";
    public static final String KEY_KSW_VOL_VAL_00 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_00";
    public static final String KEY_KSW_VOL_VAL_01 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_01";
    public static final String KEY_KSW_VOL_VAL_02 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_02";
    public static final String KEY_KSW_VOL_VAL_03 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_03";
    public static final String KEY_KSW_VOL_VAL_04 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_04";
    public static final String KEY_KSW_VOL_VAL_05 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_05";
    public static final String KEY_LOGO_SETTINGS = "COM.SZCHOICEWAY_LOGO_SETTINGS";
    public static final String KEY_LiShengDVD = "Set_LiShengDVD";
    public static final String KEY_LiShengType = "Set_LiShengType";
    public static final String KEY_MID_FREQ_SETTINGS = "COM.SZCHOICEWAY_MID_FREQ_SETTINGS";
    public static final String KEY_MID_SETTINGS = "COM.SZCHOICEWAY_MID_SETTINGS";
    public static final String KEY_NBRIGHTNESS_SETTINGS = "COM.SZCHOICEWAY_NBRIGHTNESS_SETTINGS";
    public static final String KEY_NaiAoShiDVD = "Set_NaiAoShiDVD";
    public static final String KEY_NaiAoShiType = "Set_NaiAoShiType";
    public static final String KEY_OrgBackcar = "Set_OrgBackcar";
    public static final String KEY_RADIO_ZONE_SETTINGS = "COM.SZCHOICEWAY_RADIO_ZONE_SETTINGS";
    public static final String KEY_STANDBY_SETTINGS = "COM.SZCHOICEWAY_STANDBY_SETTINGS";
    public static final String KEY_SWITCH_MIRROR = "COM.KEY_SWITCH_MIRROR";
    public static final String KEY_SYS_LAST_GPS_MODE = "KEY_SYS_LAST_GPS_MODE";
    public static final String KEY_SYS_LAST_MODE = "KEY_SYS_LAST_MODE_SAVE";
    public static final String KEY_SYS_LAST_MUSIC_NAVI_MODE = "KEY_SYS_LAST_MUSIC_NAVI_MODE";
    public static final String KEY_SYS_LAST_QUICK_ACCESS_1_MODE = "KEY_SYS_LAST_QUICK_ACCESS_1_MODE";
    public static final String KEY_SYS_LAST_QUICK_ACCESS_2_MODE = "KEY_SYS_LAST_QUICK_ACCESS_2_MODE";
    public static final String KEY_TRE_FREQ_SETTINGS = "COM.SZCHOICEWAY_TRE_FREQ_SETTINGS";
    public static final String KEY_TRE_SETTINGS = "COM.SZCHOICEWAY_TRE_SETTINGS";
    public static final String KEY_XINGSHUO_CAR_STYLE = "Set_Xingshuo_car_sytle";
    public static final String KEY_XINGSHUO_PARA_STYLE = "KEY_XINGSHUO_PARA_STYLE";
    public static final String KSW_BROADCASE_DVD_VIDEO_TO_LIST = "com.choiceway.eventcenter.EventUtils.KSW_BROADCASE_DVD_VIDEO_TO_LIST";
    public static final int KSW_EASYCONNSTATE_ACQUIRE = 3;
    public static final int KSW_EASYCONNSTATE_OPEN = 1;
    public static final int KSW_EASYCONNSTATE_QUIT = 5;
    public static final int KSW_EASYCONNSTATE_RELEASE = 4;
    public static final int KSW_EASYCONNSTATE_RESUME = 2;
    public static final int KSW_HANDLER_360_SERVICE_XYQ_CHECK = 3014;
    public static final int KSW_HANDLER_BT_SERVICE_CHECK = 3013;
    public static final int KSW_HANDLER_CHECK_CONFIGURATION = 3002;
    public static final int KSW_HANDLER_CHECK_COPY_MEDIA_MANUAL = 3019;
    public static final int KSW_HANDLER_CHECK_MCU_UPGRADE_SERVICE_ALIVE = 3021;
    public static final int KSW_HANDLER_CHECK_MCU_VERSION = 3010;
    public static final int KSW_HANDLER_CHECK_NAVI_ALIVE = 3018;
    public static final int KSW_HANDLER_CHECK_SPLIT_SCREEN_A12 = 3020;
    public static final int KSW_HANDLER_CHECK_XYQ_INIT = 3022;
    public static final int KSW_HANDLER_CLOCK_CONTROL = 3009;
    public static final int KSW_HANDLER_DELAY_AUX_MODE = 3033;
    public static final int KSW_HANDLER_DISABLE_GOOGLE_APKS = 3015;
    public static final int KSW_HANDLER_END_BACKCAR = 3006;
    public static final int KSW_HANDLER_END_BT_PHONE = 3005;
    public static final int KSW_HANDLER_GET_IMEI = 3000;
    public static final int KSW_HANDLER_HAVE_GPS_SOUND = 2586;
    public static final int KSW_HANDLER_INTERFACE_STATE = 2591;
    public static final int KSW_HANDLER_LAUNCHER_INIT_CHECK = 3012;
    public static final int KSW_HANDLER_NOT_GPS_SOUND = 2587;
    public static final int KSW_HANDLER_OPEN_SERIALPORT = 3007;
    public static final int KSW_HANDLER_POWER_ON = 2584;
    public static final int KSW_HANDLER_POWER_ON_CHECK = 3011;
    public static final int KSW_HANDLER_REBOOT = 3016;
    public static final int KSW_HANDLER_SD_USB_CARTYPE_XML_UPDATE = 2585;
    public static final int KSW_HANDLER_SD_USB_FACTORY_XML_UPDATE = 2588;
    public static final int KSW_HANDLER_SD_USB_LOGO_UPDATE = 2589;
    public static final int KSW_HANDLER_SD_USB_MAP_APK_LIST_UPDATE = 2590;
    public static final int KSW_HANDLER_SET_CURRENT_CARPLAY = 3017;
    public static final int KSW_HANDLER_SET_DHW_CID = 3034;
    public static final String KSW_MCU_SEND_ARM_DVD_EVENT = "com.choiceway.eventcenter.EventUtils.KSW_MCU_SEND_ARM_DVD_EVENT";
    public static final String KSW_MCU_SEND_ARM_DVD_EVENT_DATA = "com.choiceway.eventcenter.EventUtils.KSW_MCU_SEND_ARM_DVD_EVENT_DATA";
    public static final String KSW_OLD_BMW_X1_ORIGINAL_CLASS = "com.szchoiceway.ksw_old_bmw_x1_original.MainActivity";
    public static final String KSW_OLD_BMW_X1_ORIGINAL_PACKAGE = "com.szchoiceway.ksw_old_bmw_x1_original";
    public static final String KSW_RETURN_MODE_REQUEST = "com.szchoiceway.KSW_RETURN_MODE_REQUEST";
    public static final String KSW_RETURN_MODE_REQUEST_DATA = "com.szchoiceway.KSW_RETURN_MODE_REQUEST_DATA";
    public static final String KSW_STOP_BTMUSIC_SWITCH = "com.choiceway.eventcenter.KSW_STOP_BTMUSIC_SWITCH";
    public static final String KSW_STOP_BTMUSIC_SWITCH_PHONELINK = "com.choiceway.eventcenter.KSW_STOP_BTMUSIC_SWITCH_PHONELINK";
    public static final String KSW_UPDATE_VOL_SET_VIEW = "com.choiceway.eventcenter.EventUtils.KSW_UPDATE_VOL_SET_VIEW";
    public static final String KSW_ZXW_ACC_CONTROL_BEFORE = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_ACC_CONTROL_BEFORE";
    public static final String KSW_ZXW_ACC_CONTROL_BEFORE_DATA = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_ACC_CONTROL_BEFORE_DATA";
    public static final String KSW_ZXW_BT_CONNECED_SHOW_VIEW = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW";
    public static final String KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA";
    public static final String KSW_ZXW_MCUUPGRADE_MCU_ARM_DATA = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_MCUUPGRADE_MCU_ARM_DATA";
    public static final String KSW_ZXW_MCUUPGRADE_MCU_ARM_EVT = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_MCUUPGRADE_MCU_ARM_EVT";
    public static final String KSW_ZXW_VIDEO_BAN_PARK = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_VIDEO_BAN_PARK";
    public static final String KSW_ZXW_VIDEO_BAN_PARK_EXTRA_BAN = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_VIDEO_BAN_PARK_EXTRA_BAN";
    public static final String KSW_ZXW_VIDEO_BAN_PARK_EXTRA_PARK = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_VIDEO_BAN_PARK_EXTRA_PARK";
    public static final String KeyAutoAnswer = "BT_AutoAnswer";
    public static final String LETTER_AIRPLAY_MODE_CLASS_NAME = "com.carletter.car.ui.AirplayMediaActivity";
    public static final String LETTER_AUTO_MODE_CLASS_NAME = "com.carletter.car.ui.H264MediaActivity";
    public static final int LETTER_CARPLAY_MODE_AIRPLAY = 1;
    public static final int LETTER_CARPLAY_MODE_ANDROID_AUTO = 3;
    public static final int LETTER_CARPLAY_MODE_ANDROID_LINK = 4;
    public static final int LETTER_CARPLAY_MODE_CARLIFE = 5;
    public static final int LETTER_CARPLAY_MODE_CARPLAY = 2;
    public static final String LETTER_CARPLAY_MODE_CLASS_NAME = "com.carletter.car.ui.CarletterActivity";
    public static final int LETTER_CARPLAY_MODE_DLNA = 6;
    public static final int LETTER_CARPLAY_MODE_HICAR = 7;
    public static final String LETTER_CARPLAY_MODE_PACKAGE_NAME = "com.carletter.car";
    public static final int LETTER_CARPLAY_MODE_UNKNOWN = 0;
    public static final String LETTER_DLNA_MODE_CLASS_NAME = "com.carletter.car.ui.StreamMediaActivity";
    public static final String LIDIAN_MODE_PACKAGE_NAME = "com.fibocom.multicamera";
    public static final String LIDIAN_MODE_SERVICE_NAME = "com.megaview.avm.service.AvmService";
    public static final String MAIRUIWEI_BACKCARCONTROL_DATA = "EventUtils.MAIRUIWEI_BACKCARCONTROL_DATA";
    public static final String MAIRUIWEI_BACKCARRADAR_DATA = "EventUtils.MAIRUIWEI_BACKCARRADAR_DATA";
    public static final String MAIRUIWEI_BACKCARTRACK_DATA = "EventUtils.MAIRUIWEI_BACKCARTRACK_DATA";
    public static final String MAIRUIWEI_CAMERASELECT_DATA = "EventUtils.MAIRUIWEI_CAMERASELECT_DATA";
    public static final String MAIRUIWEI_DTVCHANNEL_DATA = "EventUtils.MAIRUIWEI_DTVCHANNEL_DATA";
    public static final String MAISILUO_ZXW_SHOW_ALL_APPS = "com.choiceway.eventcenter.EventUtils.MAISILUO_ZXW_SHOW_ALL_APPS";
    public static final String MCU_3DH_INFO = "com.szchoiceway.eventcenter.EventUtils.MCU_3DH_INFO";
    public static final String MCU_CAR_CAN_RADAR_INFO = "com.szchoiceway.eventcenter.MCU_CAR_CAN_RADAR_INFO";
    public static final String MCU_CMD_DATA = "EventUtils.MCU_CMD_DATA";
    public static final byte MCU_KEY1_12 = 109;
    public static final byte MCU_KEY1_14 = 110;
    public static final byte MCU_KEY1_2 = 103;
    public static final byte MCU_KEY1_3 = 104;
    public static final byte MCU_KEY1_4 = 105;
    public static final byte MCU_KEY1_5 = 108;
    public static final byte MCU_KEY2_1 = 106;
    public static final byte MCU_KEY2_10 = 107;
    public static final byte MCU_KEY_AB = 86;
    public static final byte MCU_KEY_AC = 91;
    public static final byte MCU_KEY_AIR_MODE = 106;
    public static final byte MCU_KEY_AIR_SW = 95;
    public static final byte MCU_KEY_AMS = 12;
    public static final byte MCU_KEY_ANGLE = 31;
    public static final byte MCU_KEY_APPLIST = -7;
    public static final byte MCU_KEY_APS = 13;
    public static final byte MCU_KEY_AUTO = 94;
    public static final byte MCU_KEY_BEEP = -15;
    public static final byte MCU_KEY_BLACK = 21;
    public static final byte MCU_KEY_BLOW_FONT = 92;
    public static final byte MCU_KEY_BLOW_REVERSE = 93;
    public static final byte MCU_KEY_BND = 14;
    public static final byte MCU_KEY_BT = 59;
    public static final byte MCU_KEY_CALIBRATE = -17;
    public static final byte MCU_KEY_CAR_MEDIA = 115;
    public static final byte MCU_KEY_CLEAR = 83;
    public static final byte MCU_KEY_CLOCK = 73;
    public static final byte MCU_KEY_CLOSE_SCREEN = -5;
    public static final byte MCU_KEY_CMMB = 62;
    public static final byte MCU_KEY_DIM = -10;
    public static final byte MCU_KEY_DISP = 10;
    public static final byte MCU_KEY_DISPLAY = 32;
    public static final byte MCU_KEY_DOWN = 26;
    public static final byte MCU_KEY_DUAL = 88;
    public static final byte MCU_KEY_DVD = 57;
    public static final byte MCU_KEY_DVD_MENU = 84;
    public static final byte MCU_KEY_EJECT = 15;
    public static final byte MCU_KEY_ENTER = 28;
    public static final byte MCU_KEY_EQ = 51;
    public static final int MCU_KEY_EXIT = 511;
    public static final byte MCU_KEY_EXIT_AUX = 118;
    public static final byte MCU_KEY_FAN_ADD = 99;
    public static final byte MCU_KEY_FAN_SUB = 98;
    public static final byte MCU_KEY_FF = 7;
    public static final byte MCU_KEY_FORCE_EJECT = 56;
    public static final byte MCU_KEY_F_CAM = 102;
    public static final byte MCU_KEY_GOTO = 47;
    public static final byte MCU_KEY_HANGUP = 22;
    public static final byte MCU_KEY_IDLE = -8;
    public static final String MCU_KEY_INFOR_ACTION = "com.szchoiceway.eventcenter.EventUtils.MCU_KEY_INFOR";
    public static final byte MCU_KEY_LEFT = 24;
    public static final byte MCU_KEY_LEFT_RIGHT_BACK_CAM_LONG = 117;
    public static final byte MCU_KEY_LEFT_TEMP_ADD = 100;
    public static final byte MCU_KEY_LEFT_TEMP_SUB = 101;
    public static final byte MCU_KEY_LOCDX = 48;
    public static final byte MCU_KEY_LOUDNESS = 82;
    public static final byte MCU_KEY_L_R = 50;
    public static final byte MCU_KEY_MAX_AC = 90;
    public static final byte MCU_KEY_MENU = 9;
    public static final byte MCU_KEY_MIDDLE_CONTROL = 114;
    public static final byte MCU_KEY_MODE = 16;
    public static final byte MCU_KEY_MOVIE = -13;
    public static final byte MCU_KEY_MP5 = 60;
    public static final byte MCU_KEY_MUSIC = -14;
    public static final byte MCU_KEY_MUTE = 17;
    public static final byte MCU_KEY_NAV = 55;
    public static final byte MCU_KEY_NEXT = 2;
    public static final byte MCU_KEY_NONE = 0;
    public static final byte MCU_KEY_NP = -12;
    public static final byte MCU_KEY_NUM0 = 36;
    public static final byte MCU_KEY_NUM1 = 37;
    public static final byte MCU_KEY_NUM10 = 46;
    public static final byte MCU_KEY_NUM1_LONG = 64;
    public static final byte MCU_KEY_NUM2 = 38;
    public static final byte MCU_KEY_NUM2_LONG = 65;
    public static final byte MCU_KEY_NUM3 = 39;
    public static final byte MCU_KEY_NUM3_LONG = 66;
    public static final byte MCU_KEY_NUM4 = 40;
    public static final byte MCU_KEY_NUM4_LONG = 67;
    public static final byte MCU_KEY_NUM5 = 41;
    public static final byte MCU_KEY_NUM5_LONG = 68;
    public static final byte MCU_KEY_NUM6 = 42;
    public static final byte MCU_KEY_NUM6_LONG = 69;
    public static final byte MCU_KEY_NUM7 = 43;
    public static final byte MCU_KEY_NUM8 = 44;
    public static final byte MCU_KEY_NUM9 = 45;
    public static final byte MCU_KEY_PHONELINK = -6;
    public static final byte MCU_KEY_PIP = -16;
    public static final byte MCU_KEY_PLAY = 4;
    public static final byte MCU_KEY_PLAYPAUSE = 6;
    public static final byte MCU_KEY_POWER = 1;
    public static final byte MCU_KEY_PREV = 3;
    public static final byte MCU_KEY_PROG = 72;
    public static final byte MCU_KEY_RADIO = 54;
    public static final byte MCU_KEY_RADIO_NEXT = 70;
    public static final byte MCU_KEY_RADIO_PREV = 71;
    public static final byte MCU_KEY_RANDOM = 30;
    public static final byte MCU_KEY_REP = 11;
    public static final byte MCU_KEY_REPEAT = 29;
    public static final byte MCU_KEY_RETURN = 85;
    public static final byte MCU_KEY_RF = 8;
    public static final byte MCU_KEY_RIGHT = 27;
    public static final byte MCU_KEY_RIGHT_CAM = 112;
    public static final byte MCU_KEY_RIGHT_CAM_LONG = 113;
    public static final byte MCU_KEY_RIGHT_TEMP_ADD = 96;
    public static final byte MCU_KEY_RIGHT_TEMP_SUB = 97;
    public static final byte MCU_KEY_SEARCH = 87;
    public static final byte MCU_KEY_SEL = 58;
    public static final byte MCU_KEY_SETUP = 20;
    public static final byte MCU_KEY_SLOW = 53;
    public static final byte MCU_KEY_SOUNDLANG = 34;
    public static final byte MCU_KEY_STANDBY = -9;
    public static final byte MCU_KEY_STMONO = 49;
    public static final byte MCU_KEY_STOP = 5;
    public static final byte MCU_KEY_SUBT = 33;
    public static final byte MCU_KEY_SYS_ESC = 78;
    public static final byte MCU_KEY_SYS_HOME = 76;
    public static final byte MCU_KEY_SYS_MENU = 77;
    public static final byte MCU_KEY_SYS_WINCE = 79;
    public static final byte MCU_KEY_TAB = 89;
    public static final byte MCU_KEY_TALK = 23;
    public static final byte MCU_KEY_TFT_CLOSE = 75;
    public static final byte MCU_KEY_TFT_LONG_CLOSE = 81;
    public static final byte MCU_KEY_TFT_LONG_OPEN = 80;
    public static final byte MCU_KEY_TFT_OPEN = 74;
    public static final byte MCU_KEY_TITLE = 52;
    public static final byte MCU_KEY_TOUCH = 63;
    public static final byte MCU_KEY_TOUCH_CAL = -11;
    public static final byte MCU_KEY_TV = 61;
    public static final byte MCU_KEY_UP = 25;
    public static final String MCU_KEY_VALUE = "EventUtils.MCU_KEY_VALUE";
    public static final byte MCU_KEY_VOL_ADD = 18;
    public static final byte MCU_KEY_VOL_SHOW = -24;
    public static final byte MCU_KEY_VOL_SUB = 19;
    public static final byte MCU_KEY_WAKE_UP = -57;
    public static final byte MCU_KEY_ZOOM = 35;
    public static final String MCU_MSG_ACC_DISCONNECTED_EVT = "com.choiceway.eventcenter.EventUtils.MCU_MSG_ACC_DISCONNECTED_EVT";
    public static final String MCU_MSG_BACKCAR_END_EVT = "com.choiceway.eventcenter.EventUtils.MCU_MSG_BACKCAR_END";
    public static final String MCU_MSG_BACKCAR_START_EVT = "com.choiceway.eventcenter.EventUtils.MCU_MSG_BACKCAR_START";
    public static final String MCU_MSG_BACKCAR_SWITCH_AUDIO_EVT_KSW = "com.choiceway.eventcenter.EventUtils.MCU_MSG_BACKCAR_SWITCH_AUDIO_EVT_KSW";
    public static final String MCU_MSG_BACKCAR_SWITCH_AUDIO_EVT_KSW_DATA = "com.choiceway.eventcenter.EventUtils.MCU_MSG_BACKCAR_SWITCH_AUDIO_EVT_KSW_DATA";
    public static final String MCU_MSG_BAL_FAD = "com.choiceway.eventcenter.EventUtils.MCU_MSG_BAL_FAD";
    public static final String MCU_MSG_BRAKE_EVT = "com.choiceway.eventcenter.EventUtils.MCU_MSG_BRAKE_EVT";
    public static final String MCU_MSG_CAN_ALL_INFO = "com.choiceway.eventcenter.EventUtils.MCU_MSG_CAN_ALL_INFO";
    public static final String MCU_MSG_CAR_AIR = "com.choiceway.eventcenter.EventUtils.CMD_CAR_AIR";
    public static final String MCU_MSG_CAR_INFO = "com.choiceway.eventcenter.EventUtils.MCU_MSG_CAR_INFO";
    public static final String MCU_MSG_EQ_MODE = "com.choiceway.eventcenter.EventUtils.MCU_MSG_EQ_MODE";
    public static final String MCU_MSG_FINISH_EVT = "com.choiceway.eventcenter.EventUtils.MCU_MSG_FINISH_EVT";
    public static final String MCU_MSG_INIT_EVT = "com.choiceway.eventcenter.EventUtils.MCU_MSG_INIT_EVT";
    public static final String MCU_MSG_LOUD_EVT = "com.choiceway.eventcenter.EventUtils.MCU_MSG_LOUD_EVT";
    public static final String MCU_MSG_MAIL_VOL = "com.choiceway.eventcenter.EventUtils.MCU_MSG_MAIL_VOL";
    public static final String MCU_MSG_MAIL_VOL_VAL = "com.choiceway.eventcenter.EventUtils.MCU_MSG_MAIL_VOL_VAL";
    public static final String MCU_MSG_MUTE_STATE = "com.choiceway.eventcenter.EventUtils.MCU_MSG_MUTE_STATE";
    public static final String MCU_MSG_SWITCH_CAR_SCREEN = "com.choiceway.eventcenter.EventUtils.MCU_MSG_SWITCH_CAR_SCREEN";
    public static final String MCU_MSG_WHEEL_INFO = "com.choiceway.eventcenter.EventUtils.MCU_MSG_WHEEL_INFO";
    public static final byte MCU_SEND_8825_VALUE = -122;
    public static final String MOVIE_MODE_CLASS_NAME = "com.szchoiceway.videoplayer.MainActivity";
    public static final String MOVIE_MODE_PACKAGE_NAME = "com.szchoiceway.videoplayer";
    public static final String MSG_EVENT_BACKCAR = "com.szchoiceway.backcarevent.BACKCAR";
    public static final String MSG_EVENT_TEST = "MSG_EVENT_TEST_TEST";
    public static final String MSN_ZXW_IN_ORIGINAL_DATA = "com.choiceway.eventcenter.EventUtils.MSN_ZXW_IN_ORIGINAL_DATA";
    public static final String MSN_ZXW_IN_ORIGINAL_EVT = "com.choiceway.eventcenter.EventUtils.MSN_ZXW_IN_ORIGINAL_EVT";
    public static final String MUSIC_MODE_CLASS_NAME = "com.szchoiceway.musicplayer.MainActivity";
    public static final String MUSIC_MODE_PACKAGE_NAME = "com.szchoiceway.musicplayer";
    public static final String MUSIC_NAVI_MODE_CLASS_NAME = "com.szchoiceway.navigation.MusicSetActivity";
    public static final String MUSIC_NAVI_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final String NAVIGATION_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final String NAV_MODE_CLASS_NAME = "com.szchoiceway.navigation.MainActivity";
    public static final String NAV_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final String NAV_SET_GOTO_PAGE = "com.szchoiceway.NaviSettings.GotoPage";
    public static final String NOTIFY_WORKSPACE_PLAY_STRACK = "com.choiceway.eventcenter.EventUtils.NOTIFY_WORKSPACE_PLAY_STRACK";
    public static final String PERMISSION_CHOICEWAY_BROADCAST = "com.szchoiceway.permission.broadcast";
    public static final String PHONEAPP_MODE_CLASS_NAME = "net.easyconn.WelcomeActivity";
    public static final String PHONEAPP_MODE_CLASS_NAME2 = "net.easyconn.ui.Zxw11MainActivity";
    public static final String PHONEAPP_MODE_PACKAGE_NAME = "net.easyconn";
    public static final String PHONELINK_MODE_CLASS_NAME = "com.szchoiceway.phonelink.MainActivity";
    public static final String PHONELINK_MODE_PACKAGE_NAME = "com.szchoiceway.phonelink";
    public static final String PLAY_STRACK_DATA = "EventUtils.PLAY_STRACK_DATA";
    public static final String QUICK_ACCESS_1_NAVI_MODE_CLASS_NAME = "com.szchoiceway.navigation.Quick_Access_1_Activity";
    public static final String QUICK_ACCESS_1_NAVI_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final String QUICK_ACCESS_2_NAVI_MODE_CLASS_NAME = "com.szchoiceway.navigation.Quick_Access_2_Activity";
    public static final String QUICK_ACCESS_2_NAVI_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final String QUICK_ACCESS_3_NAVI_MODE_CLASS_NAME = "com.szchoiceway.navigation.Quick_Access_3_Activity";
    public static final String QUICK_ACCESS_3_NAVI_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final String RADIO_DSP_MODE_CLASS_NAME = "com.szchoiceway.dsp.radio.MainActivity";
    public static final String RADIO_DSP_MODE_PACKAGE_NAME = "com.szchoiceway.dsp.radio";
    public static final String RADIO_MODE_CLASS_NAME = "com.szchoiceway.radio.MainActivity";
    public static final String RADIO_MODE_PACKAGE_NAME = "com.szchoiceway.radio";
    public static final String REC_AUTONAVI_STANDARD = "AUTONAVI_STANDARD_BROADCAST_SEND";
    public static final String SD_MODE_CLASS_NAME = "com.szchoiceway.dvdplayer.SDMainActivity";
    public static final String SET_MODE_CLASS_NAME = "com.szchoiceway.settings.MainActivity";
    public static final String SET_MODE_PACKAGE_NAME = "com.szchoiceway.settings";
    public static final int SHOW_VOL_DIALOG = 7;
    public static final String STEER_WHEEL_INFOR = "com.choiceway.eventcenter.EventUtils.STEER_WHEEL_INFOR";
    public static final String STEER_WHEEL_INFOR_LPARAM = "EventUtils.STEER_WHEEL_INFOR_LPARAM";
    public static final String STEER_WHEEL_INFOR_WPARAM = "EventUtils.STEER_WHEEL_INFOR_WPARAM";
    public static final String STEER_WHEEL_STATUS = "com.choiceway.eventcenter.EventUtils.STEER_WHEEL_STATUS";
    public static final String STEER_WHEEL_STUDY_STATUS = "EventUtils.STEER_WHEEL_STUDY_STATUS";
    public static final String SWITCH_CAR_SCREEN_DATA = "EventUtils.SWITCH_CAR_SCREEN_DATA";
    public static final int SYS_BAL_ADD = 8;
    public static final int SYS_BAL_SUB = 9;
    public static final int SYS_BASS_ADD = 2;
    public static final int SYS_BASS_FREQ_ADD = 17;
    public static final int SYS_BASS_FREQ_SUB = 18;
    public static final int SYS_BASS_SUB = 3;
    public static final byte SYS_BEEP_ONOFF = 2;
    public static final int SYS_EJECT = 15;
    public static final int SYS_FAD_ADD = 10;
    public static final int SYS_FAD_SUB = 11;
    public static final byte SYS_FM_ZONE = 1;
    public static final int SYS_FORCE_EJECT = 16;
    public static final byte SYS_GPS_SND = 4;
    public static final int SYS_INIT_AUDIO = 14;
    public static final int SYS_LOUD = 13;
    public static final int SYS_MID_ADD = 4;
    public static final int SYS_MID_FREQ_ADD = 19;
    public static final int SYS_MID_FREQ_SUB = 20;
    public static final int SYS_MID_SUB = 5;
    public static final int SYS_MUTE = 12;
    public static final byte SYS_PTY_NUM = 3;
    public static final byte SYS_RDS_ONFF = 0;
    public static final byte SYS_SYS_VOL = 5;
    public static final int SYS_TREB_FREQ_ADD = 21;
    public static final int SYS_TREB_FREQ_SUB = 22;
    public static final int SYS_TRE_ADD = 6;
    public static final int SYS_TRE_SUB = 7;
    public static final int SYS_VOL_ADD = 0;
    public static final int SYS_VOL_SUB = 1;
    public static final String TAG = "EventUtils";
    public static final String TEST_BROADCAST_KSW_ORIGINAL_QUIT = "test_broadcast_ksw_original_quit";
    public static final String TMC_UAER_DEV_PATH = "/dev/ttyS2";
    public static final int TMC_UART_DEV_SPEED = 38400;
    public static final byte TV_AMS = 9;
    public static final byte TV_BAND = 0;
    public static final byte TV_CHANNEL = 1;
    public static final byte TV_CHECK_VALID = 3;
    public static final byte TV_FORMAT = 6;
    public static final byte TV_FREQ = 2;
    public static final byte TV_FREQ_ADD = 2;
    public static final byte TV_FREQ_DEC = 3;
    public static final byte TV_FREQ_STEP = 1;
    public static final String TV_MODE_CLASS_NAME = "com.szchoiceway.navigation.CMMBActivity";
    public static final String TV_MODE_PACKAGE_NAME = "com.szchoiceway.navigation";
    public static final byte TV_NEXT = 7;
    public static final byte TV_NONE = 0;
    public static final byte TV_NOTIFY_OPENCAMERA = 4;
    public static final byte TV_PREV = 8;
    public static final byte TV_SEEK_DOWN = 5;
    public static final byte TV_SEEK_UP = 4;
    public static final byte TV_SIGNAL_INVALID = 10;
    public static final byte TV_SIGNAL_VALID = 11;
    public static final byte TV_SUB_FORMAT = 12;
    public static final String TXZ_TRIGGERRECORD_BUTTON_KEYWORDS = "com.txznet.triggerrecordbuttonKeywords";
    public static final String TXZ_TRIGGERRECORD_BUTTON_KEYWORDS2 = "txz.intent.action.smartwakeup.triggerRecordButton";
    public static final String UART_DEV_PATH = "/dev/ttyS0";
    public static final String UART_DEV_PATH_AC8257 = "/dev/ttyS1";
    public static final String UART_DEV_PATH_GT = "/dev/ttyHS1";
    public static final String UART_DEV_PATH_RK3188 = "/dev/ttyS0";
    public static final String UART_DEV_PATH_RKPX3 = "/dev/ttyS0";
    public static final String UART_DEV_PATH_RKPX6 = "/dev/ttyS3";
    public static final int UART_DEV_SPEED = 115200;
    public static final int UART_DEV_SPEED1 = 460800;
    public static final String USB_DVR_MODE_CLASS_NAME = "com.szchoiceway.usbdvrplayer.UsbDvrActivity";
    public static final String USB_DVR_MODE_PACKAGE_NAME = "com.szchoiceway.usbdvrplayer";
    public static final String VALID_MODE_INFOR_CHANGE = "com.szchoiceway.eventcenter.EventUtils.VALID_MODE_INFOR_CHANGE";
    public static final String VW_KEY_DATA = "EventUtils.VW_KEY_DATA";
    public static final String WEATHER_MODE_CLASS_NAME = "com.txznet.weather.MainActivity";
    public static final String WEATHER_MODE_PACKAGE_NAME = "com.txznet.weather";
    public static final int WHAT_AUTO_RUNNING_USB_DVR_CRASH_SERVICE = 300;
    public static final int WHAT_HIDE_MEMU_CONTENT = 2593;
    public static final int WHAT_HIDE_MEMU_ROOT = 2594;
    public static final int WHAT_SET_CARPLAY_PHONE_STATUS_SYS_EVT = 3004;
    public static final int WHAT_START_CEPSERVICE = 2592;
    public static final int WSK_BANK = 4;
    public static final int WSK_CLEAR = 115;
    public static final int WSK_ENTER = 112;
    public static final int WSK_HANDUP = 6;
    public static final int WSK_MODE = 0;
    public static final int WSK_MUTE = 5;
    public static final int WSK_NEXT = 1;
    public static final int WSK_OK = 114;
    public static final int WSK_PREV = 2;
    public static final int WSK_PWOFF = 3;
    public static final int WSK_QUIT = 113;
    public static final int WSK_TURNON = 7;
    public static final int WSK_VOLDEC = 9;
    public static final int WSK_VOLINC = 8;
    public static final String XYQ_MODE_CLASS_NAME = "com.ivicar.modules.main.view.MainActivity";
    public static final String XYQ_MODE_PACKAGE_NAME = "com.ivicar.avm";
    public static final String XYQ_MODE_SERVICE_NAME = "com.szchoiceway.AvmService";
    public static final String ZHTY_EXIT_DIANTAIZHIJIA = "com.choiceway.eventcenter.ZHTY_EXIT_DIANTAIZHIJIA";
    public static final String ZHTY_EXIT_DIANTAIZHIJIA_DATA = "com.choiceway.eventcenter.ZHTY_EXIT_DIANTAIZHIJIA_DATA";
    public static final String ZHTY_KEY_BT_VOL = "COM.SZCHOICEWAY_ZHTY_KEY_BT_VOL";
    public static final String ZHTY_KEY_GPS_VOL = "COM.SZCHOICEWAY_ZHTY_KEY_GPS_VOL";
    public static final String ZHTY_KEY_MEDIA_VOL = "COM.SZCHOICEWAY_ZHTY_KEY_MEDIA_VOL";
    public static final String ZHTY_MCU_SWITCH_GPS_CHANNAL = "com.choiceway.eventcenter.ZHTY_MCU_SWITCH_GPS_CHANNAL";
    public static final String ZHTY_MCU_SWITCH_GPS_CHANNAL_DATA = "com.choiceway.eventcenter.ZHTY_MCU_SWITCH_GPS_CHANNAL_DATA";
    public static final String ZHTY_MCU_SWITCH_MEDIA_CHANNAL = "com.choiceway.eventcenter.ZHTY_MCU_SWITCH_MEDIA_CHANNAL";
    public static final String ZHTY_MCU_SWITCH_MEDIA_CHANNAL_DATA = "com.choiceway.eventcenter.ZHTY_MCU_SWITCH_MEDIA_CHANNAL_DATA";
    public static final String ZHTY_PHONELINK_UNMUTE = "com.choiceway.eventcenter.ZHTY_PHONELINK_UNMUTE";
    public static final String ZHTY_PHONELINK_UNMUTE_DATA = "com.choiceway.eventcenter.ZHTY_PHONELINK_UNMUTE_DATA";
    public static final String ZHTY_SEND_BT_VOL_BOOLEAN = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_BT_VOL_BOOLEAN";
    public static final String ZHTY_SEND_BT_VOL_DATA = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_BT_VOL_DATA";
    public static final String ZHTY_SEND_GPS_VOL_BOOLEAN = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_GPS_VOL_BOOLEAN";
    public static final String ZHTY_SEND_GPS_VOL_DATA = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_GPS_VOL_DATA";
    public static final String ZHTY_SEND_MEDIA_BT_GPS_VOL = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_MEDIA_BT_GPS_VOL";
    public static final String ZHTY_SEND_MEDIA_VOL_BOOLEAN = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_MEDIA_VOL_BOOLEAN";
    public static final String ZHTY_SEND_MEDIA_VOL_DATA = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_MEDIA_VOL_DATA";
    public static final String ZHTY_SHOW_OR_HIDE_TXZ_WIN = "com.choiceway.eventcenter.ZHTY_SHOW_OR_HIDE_TXZ_WIN";
    public static final String ZHTY_SHOW_OR_HIDE_TXZ_WIN_DATA = "com.choiceway.eventcenter.ZHTY_SHOW_OR_HIDE_TXZ_WIN_DATA";
    public static final String ZLINK_ANDROID_AUTO_MODE_CLASS_NAME = "com.zjinnova.android.zlink.features.launcher.AutoActivity";
    public static final String ZLINK_CARPLAY_MODE_CLASS_NAME = "com.zjinnova.android.zlink.features.main.MainActivity";
    public static final int ZLINK_CONNECT_MODE_ANDROID_AUTO = 1;
    public static final int ZLINK_CONNECT_MODE_CARLINK = 2;
    public static final int ZLINK_CONNECT_MODE_CARPLAY = 0;
    public static final int ZLINK_CONNECT_MODE_DLNA = 4;
    public static final int ZLINK_CONNECT_MODE_HICAR = 5;
    public static final int ZLINK_CONNECT_MODE_MIRROR_AIRPLAY = 3;
    public static final int ZLINK_CONNECT_MODE_NULL = -1;
    public static final String ZLINK_DLNA_MODE_CLASS_NAME = "com.zjinnova.android.zlink.features.dlna.DlnaActivity";
    public static final String ZLINK_MIRROR_MODE_CLASS_NAME = "com.zjinnova.android.zlink.features.launcher.MirrorActivity";
    public static final String ZLINK_MODE_PACKAGE_NAME = "com.zjinnova.zlink";
    public static final String ZXW_ACTION_BT_PHONE_CALL_INTERFACE = "ZXW_ACTION_BT_PHONE_CALL_INTERFACE";
    public static final String ZXW_ACTION_BT_PHONE_CALL_INTERFACE_EXTRA = "ZXW_ACTION_BT_PHONE_CALL_INTERFACE_EXTRA";
    public static final String ZXW_ACTION_BT_SERVICE_ON_DESTROY = "ZXW_ACTION_BT_SERVICE_ON_DESTROY";
    public static final String ZXW_ACTION_CLOSE_EQUALIZER = "ZXW_ACTION_CLOSE_EQUALIZER";
    public static final String ZXW_ACTION_CONTROL_SPLIT_SCREEN_EXTRA = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_CONTROL_SPLIT_SCREEN_EXTRA";
    public static final String ZXW_ACTION_CONTROL_SPLIT_SCREEN_KEY = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_CONTROL_SPLIT_SCREEN_KEY";
    public static final String ZXW_ACTION_DELAY_AUX = "ZXW_ACTION_DELAY_AUX";
    public static final String ZXW_ACTION_DELAY_AUX_DATA = "ZXW_ACTION_DELAY_AUX_DATA";
    public static final String ZXW_ACTION_DOUBLE_FLASH = "com.szchoiceway.action.DOUBLE_FLASH";
    public static final String ZXW_ACTION_ENTER_REVERSE_IMAGE = "com.szchoiceway.action.ENTER_REVERSE_IMAGE";
    public static final String ZXW_ACTION_ENTER_THIRD_MEDIA = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_ENTER_THIRD_MEDIA";
    public static final String ZXW_ACTION_ENTER_THIRD_MEDIA_EXTRA = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_ENTER_THIRD_MEDIA_EXTRA";
    public static final String ZXW_ACTION_EXIT_AUX = "ZXW_ACTION_EXIT_AUX";
    public static final String ZXW_ACTION_EXIT_REVERSE_IMAGE = "com.szchoiceway.action.EXIT_REVERSE_IMAGE";
    public static final String ZXW_ACTION_IMPORT_CONFIGURATION = "ZXW_ACTION_IMPORT_CONFIGURATION";
    public static final String ZXW_ACTION_KSW_END_MCU_LOGCAT = "EventUtils.ZXW_ACTION_KSW_END_MCU_LOGCAT";
    public static final String ZXW_ACTION_KSW_SHOW_SOFT_KEYBOARD = "ZXW_ACTION_KSW_SHOW_SOFT_KEYBOARD";
    public static final String ZXW_ACTION_KSW_THEME_CHANGE = "ZXW_ACTION_KSW_THEME_CHANGE";
    public static final String ZXW_ACTION_KSW_UPDRADE_FAST_CHARGING_STATUS = "ZXW_ACTION_KSW_UPDRADE_FAST_CHARGING_STATUS";
    public static final String ZXW_ACTION_KSW_VIDEOPLAYER_FULL_SCREEN = "com.szchoiceway.eventcenter.ZXW_ACTION_KSW_VIDEOPLAYER_FULL_SCREEN";
    public static final String ZXW_ACTION_LETTER_BROADCAST = "com.carletter.link";
    public static final String ZXW_ACTION_LETTER_PHONE_OFF = "ZXW_ACTION_LETTER_PHONE_OFF";
    public static final String ZXW_ACTION_LIDIAN_DIALOG_STATUS = "com.megaview.avm.window_status";
    public static final String ZXW_ACTION_LIDIAN_DIALOG_STATUS_EXTRA = "app_status";
    public static final String ZXW_ACTION_LIGHT_EXTRA = "state";
    public static final String ZXW_ACTION_MCU_CAR_CAN_RADAR_INFO = "com.szchoiceway.eventcenter.EventUtils.MCU_CAR_CAN_RADAR_INFO";
    public static final String ZXW_ACTION_MCU_CAR_CAN_RADAR_INFO_EXTRA = "EventUtils.CAR_CAN_DATA";
    public static final String ZXW_ACTION_REBOOT_SYS_REBOOT = "ZXW_ACTION_REBOOT_SYS_REBOOT";
    public static final String ZXW_ACTION_REFRESH_AFTER_LOCALE_CHANGED = "ZXW_ACTION_REFRESH_AFTER_ACTION_LOCALE_CHANGED";
    public static final String ZXW_ACTION_REFRESH_MUSIC_COVER = "ZXW_ACTION_REFRESH_MUSIC_COVER";
    public static final String ZXW_ACTION_REFRESH_SMALL_CLOCK_INDEX = "ZXW_ACTION_REFRESH_SMALL_CLOCK_INDEX";
    public static final String ZXW_ACTION_REMOVE_BLACK_SCREEN = "EventUtils.ZXW_ACTION_REMOVE_BLACK_SCREEN";
    public static final String ZXW_ACTION_REMOVE_BLACK_SCREEN_EXTRA = "EventUtils.ZXW_ACTION_REMOVE_BLACK_SCREEN_EXTRA";
    public static final String ZXW_ACTION_REQUEST_BT_STATUS = "ZXW_ACTION_REQUEST_BT_STATUS";
    public static final String ZXW_ACTION_RESTART_ZXWMEDIA = "ZXW_ACTION_RESTART_ZXWMEDIA";
    public static final String ZXW_ACTION_SEND_BACKCAR_END_TO_ZLINK = "com.zjinnova.zlink.action.BACKCAR_STOP";
    public static final String ZXW_ACTION_SEND_BACKCAR_START_TO_ZLINK = "com.zjinnova.zlink.action.BACKCAR_START";
    public static final String ZXW_ACTION_SEND_THIRD_MEDIA_SOURCE = "ZXW_ACTION_SEND_THIRD_MEDIA_SOURCE";
    public static final String ZXW_ACTION_SET_RESOLUTION_BY_DENSITY = "ZXW_ACTION_SET_RESOLUTION_BY_DENSITY";
    public static final String ZXW_ACTION_SET_RESOLUTION_BY_DENSITY_EXTRA = "ZXW_ACTION_SET_RESOLUTION_BY_DENSITY_EXTRA";
    public static final String ZXW_ACTION_SHOW_BLACK_SCREEN = "EventUtils.ZXW_ACTION_SHOW_BLACK_SCREEN";
    public static final String ZXW_ACTION_START_EQ = "ZXW_ACTION_START_EQ";
    public static final String ZXW_ACTION_SYS_BRIGHTNESS_SETTINGS = "com.szchoiceway.action.brightness_change";
    public static final String ZXW_ACTION_SYS_BRIGHTNESS_SETTINGS_EXTRA = "com.szchoiceway.action.brightness_change_extra";
    public static final String ZXW_ACTION_SYS_DSP_VOL_EVT = "com.choiceway.action.dsp_vol";
    public static final String ZXW_ACTION_SYS_DSP_VOL_EVT_EXTRA = "com.choiceway.action.dsp_vol_extra";
    public static final String ZXW_ACTION_SYS_DSP_VOL_EVT_EXTRA_ADD = "dsp_vol_add";
    public static final String ZXW_ACTION_SYS_DSP_VOL_EVT_EXTRA_MUTE = "dsp_vol_mute";
    public static final String ZXW_ACTION_SYS_DSP_VOL_EVT_EXTRA_SUB = "dsp_vol_sub";
    public static final String ZXW_ACTION_SYS_DSP_VOL_VALUE_EVT = "com.choiceway.action.dsp_vol_value";
    public static final String ZXW_ACTION_SYS_DSP_VOL_VALUE_EVT_EXTRA = "com.choiceway.action.dsp_vol_value_extra";
    public static final String ZXW_ACTION_SYS_MODE_CHANGE_EVT = "com.szchoiceway.action.mode_change";
    public static final String ZXW_ACTION_SYS_MODE_CHANGE_EXTRA_EVT = "com.szchoiceway.action.mode_change_extra";
    public static final String ZXW_ACTION_SYS_MODE_TITLE_CHANGE_EVT = "com.szchoiceway.action.mode_title_change";
    public static final String ZXW_ACTION_SYS_MODE_TITLE_CHANGE_EXTRA_EVT = "com.szchoiceway.action.mode_title_change_extra";
    public static final String ZXW_ACTION_SYS_MODE_TITLE_CHANGE_EXTRA_SCREEN_EVT = "com.szchoiceway.action.mode_title_change_screen_extra";
    public static final String ZXW_ACTION_SYS_UPDATE_CITY_NAME = "ZXW_ACTION_SYS_UPDATE_CITY_NAME";
    public static final String ZXW_ACTION_TOUCH_CALIBRATION = "com.szchoiceway.eventcenter.touch_calibration";
    public static final String ZXW_ACTION_TURN_LEFT = "com.szchoiceway.action.TURN_LEFT";
    public static final String ZXW_ACTION_TURN_RIGHT = "com.szchoiceway.action.TURN_RIGHT";
    public static final String ZXW_ACTION_TXZ_VOICE__BROADCAST = "com.szchoiceway.ACTION_TXZ_VOICE__BROADCAST";
    public static final String ZXW_ACTION_TXZ_VOICE__BROADCAST_KEY = "com.szchoiceway.ACTION_TXZ_VOICE__BROADCAST_KEY";
    public static final String ZXW_ACTION_TXZ_VOICE__BROADCAST_VALUE = "com.szchoiceway.ACTION_TXZ_VOICE__BROADCAST_VALUE";
    public static final String ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_RECEIVE = "ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_RECEIVE";
    public static final String ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_SEND = "ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_SEND";
    public static final String ZXW_ACTION_UPDATE_CONFIGURATION = "ZXW_ACTION_UPDATE_CONFIGURATION";
    public static final String ZXW_ACTION_UPDATE_CONFIGURATION_FAILED = "ZXW_ACTION_UPDATE_CONFIGURATION_FAILED";
    public static final String ZXW_ACTION_UPDATE_CONFIGURATION_FAILED_VERSION = "ZXW_ACTION_UPDATE_CONFIGURATION_FAILED_VERSION";
    public static final String ZXW_ACTION_UPDATE_CONFIGURATION_FILE_ERROR = "ZXW_ACTION_UPDATE_CONFIGURATION_FILE_ERROR";
    public static final String ZXW_ACTION_UPDATE_CONFIGURATION_FILE_NOT_EXIT = "ZXW_ACTION_UPDATE_CONFIGURATION_FILE_NOT_EXIT";
    public static final String ZXW_ACTION_UPDATE_CONFIGURATION_LCD_FILE_ERROR = "ZXW_ACTION_UPDATE_CONFIGURATION_LCD_FILE_ERROR";
    public static final String ZXW_ACTION_UPDATE_CONFIGURATION_SUCCED = "ZXW_ACTION_UPDATE_CONFIGURATION_SUCCED";
    public static final String ZXW_ACTION_UPDATE_GPS_TIME = "EventUtils.ZXW_ACTION_UPDATE_GPS_TIME";
    public static final String ZXW_ACTION_UPDATE_TIME_BY_GPS = "ZXW_ACTION_UPDATE_TIME_BY_GPS";
    public static final String ZXW_ACTION_UPDATE_TIME_BY_GPS_DATA = "ZXW_ACTION_UPDATE_TIME_BY_GPS_DATA";
    public static final String ZXW_ACTION_VOLUME_ADD = "ZXW_ACTION_VOLUME_ADD";
    public static final String ZXW_ACTION_VOLUME_REDUCE = "ZXW_ACTION_VOLUME_REDUCE";
    public static final String ZXW_ACTION_XYQ_DIALOG_STATUS_EXTRA = "com.ivicar.avm.action.APP_STATE";
    public static final String ZXW_ARRAY_BYTE_DATA_EVT = "com.choiceway.eventcenter.EventUtils.ARRAY_BYTE_DATA";
    public static final String ZXW_ARRAY_BYTE_EXTRA_DATA_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_ARRAY_BYTE_EXTRA_DATA_EVT";
    public static final String ZXW_AUX_MODE_CLASS_NAME = "com.szchoiceway.auxplayer.MainActivity";
    public static final String ZXW_AUX_MODE_PACKAGE_NAME = "com.szchoiceway.auxplayer";
    public static final String ZXW_AVM_LEFT_RIGHT_BACK = "com.szchoiceway.eventcenter.ZXW_AVM_LEFT_RIGHT_BACK";
    public static final String ZXW_BORUIZONGHENG_BACK_HIDE_VOL = "com.choiceway.eventcenter.EventUtils.ZXW_BORUIZONGHENG_BACK_HIDE_VOL";
    public static final String ZXW_CAN_ACCORD_DOOR_INFO = "com.szchoiceway.eventcenter.EventUtils.ACCORD_DOOR_INFO";
    public static final String ZXW_CAN_ACCORD_DOOR_INFO_EXTRA = "EventUtils.CAR_DOOR_DATA";
    public static final String ZXW_CAN_KEY_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT";
    public static final String ZXW_CAN_KEY_EVT_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA";
    public static final String ZXW_CAN_MAIN_OTHER_VER = "com.szchoiceway.can.ZXW_CAN_MAIN_OTHER_VER";
    public static final String ZXW_CAN_MAIN_OTHER_VER_DATA0 = "com.szchoiceway.can.ZXW_CAN_MAIN_OTHER_VER_DATA0";
    public static final String ZXW_CAN_START_UP_APPS = "com.szchoiceway.eventcenter.EventUtils.ZXW_CAN_START_UP_APPS";
    public static final String ZXW_CAN_VER_CHANGE = "com.szchoiceway.can.ZXW_CAN_VER_CHANGE";
    public static final String ZXW_CAN_WHEEL_TRACK_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT";
    public static final String ZXW_CAN_WHEEL_TRACK_EVT_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT_EXTRA";
    public static final String ZXW_DATABASE_CAN_FILENAME = "com.szchoiceway.eventcenter.can";
    public static final String ZXW_DATABASE_CARTYPE_FILENAME = "com.szchoiceway.eventcenter.carType";
    public static final String ZXW_DATABASE_DVR_LIST_FILENAME = "com.szchoiceway.eventcenter.dvr";
    public static final String ZXW_DATABASE_NAVI_LIST_FILENAME = "com.szchoiceway.eventcenter.navigation";
    public static final String ZXW_DATABASE_SCREEN_DENSITY_CODE = "com.szchoiceway.eventcenter.screen_density_code";
    public static final String ZXW_DATABASE_UI_FILENAME = "com.szchoiceway.eventcenter.ui";
    public static final String ZXW_EVENT_MCUUPGRADE_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_EVENT_MCUUPGRADE_DATA";
    public static final String ZXW_EVENT_MCUUPGRADE_DATA_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_EVENT_MCUUPGRADE_DATA_EVT";
    public static final String ZXW_FRONT_CAMERA_CLASS_NAME = "com.szchoiceway.auxplayer.MainActivity";
    public static final String ZXW_FRONT_CAMERA_MODE_PACKAGE_NAME = "com.szchoiceway.auxplayer";
    public static final String ZXW_KEY_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_KEY_EVT";
    public static final String ZXW_KEY_EVT_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_KEY_EVT_EXTRA";
    public static final String ZXW_KILL_ALL_APK_XINGSHUO = "com.szchoiceway.eventcenter.EventUtils.ZXW_KILL_ALL_APK_XINGSHUO";
    public static final String ZXW_KSW_SEND_MODE_0X67 = "ZXW_KSW_SEND_MODE_0X67";
    public static final String ZXW_KSW_SEND_MODE_0X67_EXTRA = "ZXW_KSW_SEND_MODE_0X67_EXTRA";
    public static final String ZXW_LAUNCHER_ACTION_IN_RECENT = "com.android.launcher3.IN_RECENT";
    public static final String ZXW_LAUNCHER_ACTION_KILL_PROGRESS = "ZXW_LAUNCHER_ACTION_KILL_PROGRESS";
    public static final String ZXW_LAUNCHER_ACTION_SPLIT_SCREEN = "com.android.launcher3.SPLIT_SCREEN";
    public static final String ZXW_MCU_MCUUPGRADE_ADDR_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_MCUUPGRADE_ADDR_DATA";
    public static final String ZXW_MCU_MCUUPGRADE_VER_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_MCUUPGRADE_VER_DATA";
    public static final String ZXW_MCU_NOTIFY_8902_MCUUPGRADE_ADDR_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_NOTIFY_8902_MCUUPGRADE_ADDR_EVT";
    public static final String ZXW_MCU_NOTIFY_8902_MCUUPGRADE_VER_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_NOTIFY_8902_MCUUPGRADE_VER_EVT";
    public static final String ZXW_MCU_NOTIFY_ZHTY_MCUUPGRADE_ADDR_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_NOTIFY_ZHTY_MCUUPGRADE_ADDR_DATA";
    public static final String ZXW_MCU_NOTIFY_ZHTY_MCUUPGRADE_ADDR_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_NOTIFY_ZHTY_MCUUPGRADE_ADDR_EVT";
    public static final String ZXW_MCU_NOTIFY_ZHTY_MCUUPGRADE_LEN_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_NOTIFY_ZHTY_MCUUPGRADE_LEN_EVT";
    public static final String ZXW_MCU_UPGRADE_ACK_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_UPGRADE_ACK_EVT";
    public static final String ZXW_MCU_UPGRADE_ACK_EVT_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_UPGRADE_ACK_EVT_EXTRA";
    public static final String ZXW_MCU_UPGRADE_FAIL = "com.choiceway.eventcenter.EventUtils.ZXW_MCU_UPGRADE_FAIL";
    public static final int ZXW_ORIGINAL_MCU_KEY_DOWN = 4;
    public static final int ZXW_ORIGINAL_MCU_KEY_ENTER = 5;
    public static final int ZXW_ORIGINAL_MCU_KEY_ENTER_DOWN = 6;
    public static final String ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA";
    public static final String ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT";
    public static final String ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT2 = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT2";
    public static final String ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_THIRD_PARTY_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_THIRD_PARTY_DATA";
    public static final int ZXW_ORIGINAL_MCU_KEY_LEFT = 1;
    public static final int ZXW_ORIGINAL_MCU_KEY_LEFT_HANDED = 7;
    public static final int ZXW_ORIGINAL_MCU_KEY_MOUSE_DOWN = 4;
    public static final int ZXW_ORIGINAL_MCU_KEY_MOUSE_ENTER = 5;
    public static final int ZXW_ORIGINAL_MCU_KEY_MOUSE_LEFT = 1;
    public static final String ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_DATA";
    public static final String ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_EVT";
    public static final int ZXW_ORIGINAL_MCU_KEY_MOUSE_RIGHT = 2;
    public static final int ZXW_ORIGINAL_MCU_KEY_MOUSE_UP = 3;
    public static final int ZXW_ORIGINAL_MCU_KEY_RIGHT = 2;
    public static final int ZXW_ORIGINAL_MCU_KEY_RIGHT_HANDED = 8;
    public static final int ZXW_ORIGINAL_MCU_KEY_UP = 3;
    public static final String ZXW_RADIO_INFO_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_RADIO_INFO_EVT";
    public static final String ZXW_SENDBROADCAST8902MOD = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD";
    public static final String ZXW_SENDBROADCAST8902MOD_DATA0 = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_DATA0";
    public static final String ZXW_SENDBROADCAST8902MOD_DATA1 = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_DATA1";
    public static final String ZXW_SENDBROADCAST8902MOD_EXTRA = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_EXTRA";
    public static final String ZXW_SENDBROADCAST8902MOD_ShunShiSuDu = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_ShunShiSuDu";
    public static final String ZXW_SENDBROADCAST8902MOD_SpeedUnit = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_SpeedUnit";
    public static final String ZXW_SENDBROADCAST8902MOD_anquandai = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_anquandai";
    public static final String ZXW_SENDBROADCAST8902MOD_fadongjizhuansu = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_fadongjizhuansu";
    public static final String ZXW_SENDBROADCAST8902MOD_huanjinwendu = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_huanjinwendu";
    public static final String ZXW_SENDBROADCAST8902MOD_shousha = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_shousha";
    public static final String ZXW_SENDBROADCAST8902MOD_xushilicheng = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_xushilicheng";
    public static final String ZXW_SENDBROADCAST8902MOD_youLiang = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_youLiang";
    public static final String ZXW_SENDBROADCAST8902MOD_youhao = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_youhao";
    public static final String ZXW_SET_TOUCH_TYPE = "ZXW_SET_TOUCH_TYPE";
    public static final String ZXW_SET_TOUCH_TYPE_EXTRA = "ZXW_SET_TOUCH_TYPE_EXTRA";
    public static final String ZXW_SET_VALIDE_MODE_INFO = "com.szchoiceway.musicplayer.ZXW_SET_VALIDE_MODE_INFO";
    public static final String ZXW_SYS_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_EXTRA";
    public static final String ZXW_SYS_KEYBOARD_HIDE = "com.choiceway.keyboard.ZXW_SYS_KEYBOARD_HIDE";
    public static final String ZXW_SYS_KEYBOARD_SHOW = "com.choiceway.keyboard.ZXW_SYS_KEYBOARD_SHOW";
    public static final String ZXW_SYS_KEY_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_KEY";
    public static final String ZXW_SYS_VOL_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_VOL_EVT";
    public static final String ZXW_TEST_ACTION_SET_DENSITY = "ZXW_TEST_ACTION_SET_DENSITY";
    public static final String ZXW_TEST_ACTION_SET_DENSITY_EXTRA = "ZXW_TEST_ACTION_SET_DENSITY_EXTRA";
    public static final String ZXW_TXZ_VOICE_ICON_CONTROL = "com.choiceway.eventcenter.EventUtils.ZXW_TXZ_VOICE_ICON_CONTROL";
    public static final String ZXW_XINGSHUO_SOUND_VOL = "com.choiceway.eventcenter.EventUtils.ZXW_XINGSHUO_SOUND_VOL";
    public static final String ZXW_XINGSHUO_SOUND_VOL_DATA = "com.choiceway.eventcenter.EventUtils.ZXW_XINGSHUO_SOUND_VOL_DATA";
    public static final String ZXW_ZHTY_CHECK_FAIL_NOTIFY_EXIT_MCU_UPGRADE = "com.choiceway.eventcenter.EventUtils.ZXW_ZHTY_CHECK_FAIL_NOTIFY_EXIT_MCU_UPGRADE";
    public static final String ZXW_ZHTY_CHECK_OK_NOTIFY_MCU_UPGRADE = "com.choiceway.eventcenter.EventUtils.ZXW_ZHTY_CHECK_OK_NOTIFY_MCU_UPGRADE";
    private static String autoUpgradeFileName = "mcu_update_auto.bin";
    public static final int eRZY_AUSTRALIA = 5;
    public static final int eRZY_BRAZIL = 6;
    public static final int eRZY_CHINA = 0;
    public static final int eRZY_JAPAN = 4;
    public static final int eRZY_MAX = 7;
    public static final int eRZY_RUSSIA = 3;
    public static final int eRZY_USA1 = 1;
    public static final int eRZY_USA2 = 2;
    public static final int eSWSS_NONE = 0;
    public static final int eSWSS_PRESS_KEY = 2;
    public static final int eSWSS_SELECT_KEY = 1;
    public static final int eSWSS_STUDY_ERROR = 4;
    public static final int eSWSS_STUDY_OK = 3;
    public static final byte eTK_DVR_DOWN = 1;
    public static final byte eTK_DVR_FOMAT = 3;
    public static final byte eTK_DVR_MENU = 4;
    public static final byte eTK_DVR_PLAY = 2;
    public static final byte eTK_DVR_UP = 0;
    private static Toast mTip;
    /* access modifiers changed from: private */
    public static final Map<Integer, eSrcMode> mValueList = new HashMap();
    private static String upgradeFileName = "mcu_update.bin";

    public static int BIT_OFF(int i, int i2) {
        return i & (~(1 << i2));
    }

    public static int BIT_ON(int i, int i2) {
        return i | (1 << i2);
    }

    public static String getDecoderResolution(byte b) {
        return b != -40 ? b != 90 ? b != 109 ? "1920x720" : "800x480" : "1280x480" : "1440x540";
    }

    public enum eSrcMode {
        SRC_NONE(0),
        SRC_RADIO(1),
        SRC_DVD(2),
        SRC_USB(3),
        SRC_CARD(4),
        SRC_IPOD(5),
        SRC_BT(6),
        SRC_BTMUSIC(7),
        SRC_CMMB(8),
        SRC_TV(9),
        SRC_MOVIE(10),
        SRC_MUSIC(11),
        SRC_EBOOK(12),
        SRC_IMAGE(13),
        SRC_ANDROID(14),
        SRC_VMCD(15),
        SRC_NETWORK(16),
        SRC_CARMEDIA(17),
        SRC_CAR_BT(18),
        SRC_DVR(19),
        SRC_MOBILE_APP(20),
        SRC_DSP_RADIO(21),
        SRC_ATSL_AIRCONSOLE(30),
        SRC_PHONELINK(31),
        SRC_CARPLAY(32),
        SRC_AIRPLAY(33),
        SRC_TXZ_WEBCHAT(37),
        SRC_TXZ_MUSIC(38),
        SRC_TXZ_WEATHER(39),
        SRC_AUX(40),
        SRC_BACKCAR(41),
        SRC_GPS(42),
        SRC_HOME(43),
        SRC_REHOME(44),
        SRC_COMPASS(45),
        SRC_STANDBY(46),
        SRC_EQ(47),
        SRC_BACKLIGHT_SET(48),
        SRC_SETUP(49),
        SRC_FRONT_CAMERA(50),
        SRC_BCAM(51),
        SRC_LEFT_CAMERA(52),
        SRC_DVR2(53),
        SRC_RIGHT_CAMERA(54),
        SRC_THIRD_APP(55),
        SRC_MCU_VERSION(80),
        SRC_TFT_VERSION(81),
        SRC_NULL(99),
        SRC_POWERON(100),
        SRC_POWEROFF(101),
        SRC_IDLE_MODE(103),
        SRC_IDLE_REST(104),
        SRC_MUSIC_NAVI(105),
        SRC_PHONE_APP(106),
        SRC_QUICK_ACCESS_1(107),
        SRC_QUICK_ACCESS_2(108),
        SRC_Original_TO_ARM(109),
        SRC_BT_ONLY(157),
        SRC_ES_FILE_EXPLORER(156),
        SRC_APP_LIST(154),
        SRC_EXPLORER(153),
        SRC_FATSET(200),
        SRC_FEEDBACK(201),
        SRC_SETTINGS_NAVIGATION(202),
        SRC_SETTINGS_LANGUAGE(203),
        SRC_SETTINGS_DATATIMER(204),
        SRC_SETTINGS_VOLUME(205),
        SRC_SETTINGS_EQ(206),
        SRC_SETTINGS_SYSTEM(207),
        SRC_SETTINGS_FACTORY(208),
        SRC_SETTINGS_SYSTEM_INFO(209);
        
        private int value;

        private eSrcMode(int i) {
            this.value = i;
            EventUtils.mValueList.put(Integer.valueOf(i), this);
        }

        public byte getValue() {
            return (byte) (this.value & 255);
        }

        public int getIntValue() {
            return this.value;
        }

        public static eSrcMode valueOf(int i) {
            eSrcMode esrcmode = (eSrcMode) EventUtils.mValueList.get(Integer.valueOf(i));
            return esrcmode == null ? SRC_NONE : esrcmode;
        }

        public String toString() {
            return Integer.toString(this.value);
        }
    }

    public static String getMcuComDevicePath() {
        Log.i("TAG", "getMcuComDevicePath: MODEL = " + Build.MODEL);
        if (Build.MODEL.equals("rk3188")) {
            return "/dev/ttyS0";
        }
        if (Build.MODEL.startsWith("rk3399")) {
            return UART_DEV_PATH_RKPX6;
        }
        if (Build.MODEL.equals("px7_zxw")) {
            return UART_DEV_PATH_AC8257;
        }
        if (Build.MODEL.equals("sc138 for arm64")) {
            return UART_DEV_PATH_GT;
        }
        boolean equals = Build.MODEL.equals("GT6");
        return UART_DEV_PATH_GT;
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(BuildConfig.FLAVOR);
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            sb.append("0x");
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString + " ");
        }
        return sb.toString();
    }

    public static String bytesToHexString2(byte[] bArr) {
        StringBuilder sb = new StringBuilder(BuildConfig.FLAVOR);
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (i == 0) {
                sb.append("0x");
            }
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static void sendBroadcast(Context context, String str, byte[] bArr) {
        if (context != null && bArr != null) {
            Intent intent = new Intent(str);
            intent.addFlags(32);
            intent.putExtra(CAR_AIR_DATA, bArr);
            context.sendBroadcast(intent);
        }
    }

    public static void sendBroadcast(Context context, String str, String str2) {
        if (context != null) {
            Intent intent = new Intent(str);
            intent.putExtra(PLAY_STRACK_DATA, str2);
            context.sendBroadcast(intent);
        }
    }

    public static void sendBTBroadcast(Context context, String str, int i) {
        if (context != null) {
            Intent intent = new Intent(str);
            intent.putExtra(MCU_KEY_VALUE, i);
            Log.d(TAG, "sendBTBroadcast MCU_KEY_VALUE = " + i);
            context.sendBroadcast(intent);
        }
    }

    public static void sendSysBroadcast(Context context, String str, int i) {
        if (context != null) {
            Intent intent = new Intent(str);
            intent.putExtra(ZXW_SYS_EXTRA, i);
            Log.d(TAG, "sendSysBroadcast ZXW_SYS_EXTRA = " + i);
            context.sendBroadcast(intent);
        }
    }

    public static void sendBroadcastExtra(Context context, String str, int i) {
        if (context != null) {
            Intent intent = new Intent(str);
            intent.putExtra(ZXW_ARRAY_BYTE_EXTRA_DATA_EVT, i);
            context.sendBroadcast(intent);
        }
    }

    public static void sendBroadcastToDsp(Context context, String str, String str2) {
        if (context != null) {
            Intent intent = new Intent(str);
            intent.putExtra(ZXW_ACTION_SYS_DSP_VOL_EVT_EXTRA, str2);
            context.sendBroadcast(intent);
        }
    }

    public static void sendBroadcastControlSplitScreen(Context context, String str, int i) {
        if (context != null) {
            Log.i("TAG", "sendBroadcastControlSplitScreen: ");
            Intent intent = new Intent(str);
            intent.putExtra(ZXW_ACTION_CONTROL_SPLIT_SCREEN_EXTRA, i);
            context.sendBroadcast(intent);
        }
    }

    public static boolean isActivityRuning(Context context, Class<?> cls) {
        return cls.getName().equals(((ActivityManager) context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName());
    }

    public static boolean startActivityIfNotRuning(Context context, Class<?> cls) {
        boolean z = true;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() <= 0) {
            Intent intent = new Intent(context, cls);
            intent.addFlags(268435456);
            try {
                context.startActivity(intent);
                return false;
            } catch (Exception unused) {
                return false;
            }
        } else {
            if (!cls.getName().equals(runningTasks.get(0).topActivity.getClassName())) {
                Intent intent2 = new Intent(context, cls);
                intent2.addFlags(268435456);
                try {
                    context.startActivity(intent2);
                } catch (Exception unused2) {
                }
                z = false;
            }
            return z;
        }
    }

    public static void startActivityIfNotRuning(Context context, String str, String str2) {
        Log.d(TAG, "packageName = " + str + ", className = " + str2);
        if (context != null && str != null && str2 != null) {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks == null || runningTasks.size() <= 0) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setComponent(new ComponentName(str, str2));
                intent.setFlags(270532608);
                context.startActivity(intent);
                return;
            }
            ComponentName componentName = runningTasks.get(0).topActivity;
            if (!componentName.getClassName().equals(str2) || !componentName.getPackageName().equals(str)) {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setComponent(new ComponentName(str, str2));
                intent2.setFlags(270532608);
                try {
                    context.startActivity(intent2);
                } catch (Exception unused) {
                }
            }
        }
    }

    public static boolean startActivityIfNotRuning2(Context context, String str, String str2) {
        if (context == null || str == null || str2 == null) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() <= 0) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setComponent(new ComponentName(str, str2));
            intent.setFlags(270532608);
            context.startActivity(intent);
        } else {
            ComponentName componentName = runningTasks.get(0).topActivity;
            if (componentName.getClassName().equals(str2) && componentName.getPackageName().equals(str)) {
                return false;
            }
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setComponent(new ComponentName(str, str2));
            intent2.setFlags(270532608);
            try {
                context.startActivity(intent2);
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    public static void startZlink(EventService eventService) {
        String str;
        int zlinkLinkMode = eventService.getZlinkLinkMode();
        String str2 = ZLINK_MODE_PACKAGE_NAME;
        if (zlinkLinkMode != 2) {
            str = zlinkLinkMode != 4 ? ZLINK_CARPLAY_MODE_CLASS_NAME : ZLINK_DLNA_MODE_CLASS_NAME;
        } else {
            str2 = CARLINK_MODE_PACKAGE_NAME;
            str = CARLINK_MODE_CLASS_NAME;
        }
        startActivityIfNotRuning(eventService, str2, str);
    }

    public static void startLetter(EventService eventService) {
        String str;
        int letterConnectMode = eventService.getLetterConnectMode();
        Log.d(TAG, "startLetter mode = " + letterConnectMode);
        if (letterConnectMode != 1) {
            if (letterConnectMode == 2 || letterConnectMode == 3) {
                str = LETTER_AUTO_MODE_CLASS_NAME;
                startActivityIfNotRuning(eventService, LETTER_CARPLAY_MODE_PACKAGE_NAME, str);
            } else if (letterConnectMode != 4) {
                str = letterConnectMode != 6 ? LETTER_CARPLAY_MODE_CLASS_NAME : LETTER_DLNA_MODE_CLASS_NAME;
                startActivityIfNotRuning(eventService, LETTER_CARPLAY_MODE_PACKAGE_NAME, str);
            }
        }
        str = LETTER_AIRPLAY_MODE_CLASS_NAME;
        startActivityIfNotRuning(eventService, LETTER_CARPLAY_MODE_PACKAGE_NAME, str);
    }

    public static void setWifiMode(Context context, boolean z) {
        ((WifiManager) context.getSystemService("wifi")).setWifiEnabled(z);
    }

    public static void killProcess(String str) {
        try {
            if (Build.VERSION.SDK_INT <= 23) {
                Runtime.getRuntime().exec("su \n");
            }
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("am force-stop " + str + " \n");
            Log.i(TAG, "closeApplication " + str + " by aios-adapter!!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "killProcess: e = " + e.toString());
        }
    }

    public static String CurTopPackage(Context context) {
        if (context == null) {
            return BuildConfig.FLAVOR;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (runningTasks.size() > 0) {
            return runningTasks.get(0).topActivity.getPackageName();
        }
        return null;
    }

    public static boolean getInstallStatus(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        new ArrayList();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        boolean z = false;
        for (int i = 0; i < installedPackages.size(); i++) {
            if (str.equals(installedPackages.get(i).packageName)) {
                z = true;
            }
        }
        return z;
    }

    public static String getTopPackageName(Context context) {
        String str;
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks == null || runningTasks.size() <= 0) {
                str = BuildConfig.FLAVOR;
            } else {
                ComponentName componentName = runningTasks.get(0).topActivity;
                str = componentName.getPackageName();
                componentName.getClassName();
            }
            if (Build.VERSION.SDK_INT <= 29) {
                return str;
            }
            ActivityTaskManager.RootTaskInfo rootTaskInfo = null;
            for (ActivityTaskManager.RootTaskInfo next : getAllRootTaskInfo()) {
                if (next.topActivityInfo != null && next.topActivityInfo.packageName.equals(str) && next.displayId == 0) {
                    return str;
                }
                if (next.displayId == 0 && next.topActivityInfo != null && rootTaskInfo == null) {
                    rootTaskInfo = next;
                }
            }
            if (rootTaskInfo != null) {
                String str2 = rootTaskInfo.topActivityInfo.packageName;
                String str3 = rootTaskInfo.topActivityInfo.name;
                return str2;
            }
            return BuildConfig.FLAVOR;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ActivityTaskManager.RootTaskInfo> getAllRootTaskInfo() throws RemoteException {
        return ActivityManager.getService().getAllRootTaskInfos();
    }

    public static String getTopActivityName(Context context) {
        String str;
        String str2;
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks == null || runningTasks.size() <= 0) {
                str = BuildConfig.FLAVOR;
                str2 = str;
            } else {
                ComponentName componentName = runningTasks.get(0).topActivity;
                str2 = componentName.getPackageName();
                str = componentName.getClassName();
            }
            if (Build.VERSION.SDK_INT <= 29) {
                return str2;
            }
            ActivityTaskManager.RootTaskInfo rootTaskInfo = null;
            for (ActivityTaskManager.RootTaskInfo next : getAllRootTaskInfo()) {
                if (next.topActivityInfo != null && next.topActivityInfo.packageName.equals(str2) && next.displayId == 0) {
                    return str;
                }
                if (next.displayId == 0 && next.topActivityInfo != null && rootTaskInfo == null) {
                    rootTaskInfo = next;
                }
            }
            if (rootTaskInfo != null) {
                String str3 = rootTaskInfo.topActivityInfo.packageName;
                return rootTaskInfo.topActivityInfo.name;
            }
            return BuildConfig.FLAVOR;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTopPackageNameUnfiltered(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        String packageName = (runningTasks == null || runningTasks.size() <= 0) ? BuildConfig.FLAVOR : runningTasks.get(0).topActivity.getPackageName();
        Log.i(TAG, "getTopPackageNameUnfiltered: topPackageName = " + packageName);
        return packageName;
    }

    public static String getTopActivityNameUnfiltered(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return (runningTasks == null || runningTasks.size() <= 0) ? BuildConfig.FLAVOR : runningTasks.get(0).topActivity.getClassName();
    }

    public static void runCmdAtRootMode(String str, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            SystemProperties.set("sys.apk_path", str);
            SystemProperties.set("ctl.start", "install_apk");
            String str2 = str;
            while (str2.equals(str)) {
                str2 = SystemProperties.get("sys.apk_path");
                try {
                    Thread.sleep((long) i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        try {
            try {
                Runtime.getRuntime().exec(str).waitFor();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static String getPinYin(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.setLength(0);
        char[] charArray = str.toCharArray();
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] > 128) {
                try {
                    stringBuffer.append(PinyinHelper.toHanyuPinyinStringArray(charArray[i], hanyuPinyinOutputFormat)[0]);
                    stringBuffer.append(" ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                stringBuffer.append(charArray[i]);
            }
        }
        return stringBuffer.toString();
    }

    public static void saveCanDataToExternal(byte[] bArr, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str), true);
            fileOutputStream.write(bArr);
            fileOutputStream.write("\r\n".getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e("TAG", "saveCanDataToExternal: an error occured while writing file...", e);
        }
    }

    public static void sendBroadcastCanKeyExtra(Context context, String str, int i) {
        if (context != null) {
            Intent intent = new Intent(str);
            intent.putExtra(ZXW_CAN_KEY_EVT_EXTRA, i);
            Log.i("TAG", "---iExtraData---" + i);
            context.sendBroadcast(intent);
        }
    }

    public static void sendBroadcastIDriver(Context context, int i) {
        Intent intent = new Intent(ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT);
        if (((EventService) context).getBTStatus() > 3) {
            intent = new Intent(ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT2);
        }
        intent.putExtra(ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA, i);
        context.sendBroadcast(intent);
    }

    public static void sendBroadcastIDriver2(Context context, int i) {
        Intent intent = new Intent(ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT2);
        intent.putExtra(ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA, i);
        context.sendBroadcast(intent);
    }

    public static void showTipString(Context context, int i) {
        showTipString(context, context.getString(i));
    }

    public static void showTipString(Context context, String str) {
        hideTipString();
        Toast makeText = Toast.makeText(context, str, 0);
        mTip = makeText;
        makeText.setGravity(17, 0, 60);
        mTip.show();
    }

    public static void hideTipString() {
        Toast toast = mTip;
        if (toast != null) {
            toast.cancel();
            mTip = null;
        }
    }

    public static void startVoice(Context context) {
        EventService eventService;
        boolean z = false;
        if (context instanceof EventService) {
            eventService = (EventService) context;
            z = eventService.mSysProviderOpt.getRecordBoolean(SysProviderOpt.SYS_GOOGLE_VOICE_SWITCH, false);
        } else {
            eventService = null;
        }
        Log.i(TAG, "startVoice: bGoogleVoiceSwicth = " + z);
        if (z) {
            Intent intent = new Intent("android.speech.action.WEB_SEARCH");
            intent.addFlags(268435456);
            context.startActivity(intent);
            return;
        }
        String recordValue = eventService.mSysProviderOpt.getRecordValue(SysProviderOpt.VOICE_PACKAGENAME, BuildConfig.FLAVOR);
        eventService.mSysProviderOpt.getRecordValue(SysProviderOpt.VOICE_ACTIVITYNAME, BuildConfig.FLAVOR);
        Log.i(TAG, "startVoice: voicePackageName = " + recordValue);
        if ("com.zxwtxz.sdkdemo".equals(recordValue) || "com.txznet.smartadapter".equals(recordValue)) {
            context.sendBroadcastAsUser(new Intent(TXZ_TRIGGERRECORD_BUTTON_KEYWORDS), UserHandle.ALL);
            context.sendBroadcastAsUser(new Intent(TXZ_TRIGGERRECORD_BUTTON_KEYWORDS2), UserHandle.ALL);
        } else if ("com.google.android.googlequicksearchbox".equals(recordValue)) {
            Intent intent2 = new Intent("android.speech.action.WEB_SEARCH");
            intent2.addFlags(268435456);
            context.startActivity(intent2);
        } else {
            eventService.sendKeyDownUpSync(219);
        }
    }

    public static void saveIcCheck(String str) {
        boolean z = false;
        String str2 = BuildConfig.FLAVOR;
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                if (Build.VERSION.SDK_INT > 19) {
                    str2 = "/storage/usb_storage0/ic_checked/";
                    File file = new File(str2);
                    if (!file.exists()) {
                        z = file.mkdirs();
                        if (!z) {
                            str2 = "/storage/usb_storage1/ic_checked/";
                            File file2 = new File(str2);
                            if (!file2.exists()) {
                                z = file2.mkdirs();
                            }
                        }
                    }
                    z = true;
                }
                if (z) {
                    FileOutputStream fileOutputStream = new FileOutputStream(str2 + "ic_checked.txt");
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.close();
                    return;
                }
                Log.i("TAG", "saveIcCheck: mkdirs = false");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("TAG", "saveIcCheck: e = " + e.toString());
        }
    }

    public static String getBasebandVer() {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{"gsm.version.baseband", BuildConfig.FLAVOR});
            PrintStream printStream = System.out;
            printStream.println("getBasebandVer: result = " + ((String) invoke));
            return (String) invoke;
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public static boolean isServiceRunning(String str, Context context) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(100)) {
            if (str.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAppRunning(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(100);
        if (runningTasks.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
            if (runningTaskInfo.baseActivity.getPackageName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getInMultiWindowMode() {
        try {
            List allRootTaskInfos = ActivityTaskManager.getService().getAllRootTaskInfos();
            ActivityTaskManager.RootTaskInfo rootTaskInfo = null;
            ActivityTaskManager.RootTaskInfo rootTaskInfo2 = null;
            for (int i = 0; i < allRootTaskInfos.size(); i++) {
                ActivityTaskManager.RootTaskInfo rootTaskInfo3 = (ActivityTaskManager.RootTaskInfo) allRootTaskInfos.get(i);
                if (rootTaskInfo3.visible) {
                    if (rootTaskInfo3.getConfiguration().windowConfiguration.getWindowingMode() == 3) {
                        rootTaskInfo = rootTaskInfo3;
                    } else if (rootTaskInfo3.getConfiguration().windowConfiguration.getWindowingMode() == 4) {
                        rootTaskInfo2 = rootTaskInfo3;
                    }
                }
            }
            if (rootTaskInfo == null || rootTaskInfo2 == null) {
                return false;
            }
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isServiceAlive(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(10000);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (str.equals(runningServices.get(i).service.getClassName().toString())) {
                return true;
            }
        }
        return false;
    }

    public static String[] getAllPath(Context context) {
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        String[] strArr = null;
        try {
            Method method = StorageManager.class.getMethod("getVolumePaths", new Class[0]);
            method.setAccessible(true);
            try {
                strArr = (String[]) method.invoke(storageManager, new Object[0]);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < strArr.length; i++) {
                Log.d(TAG, "path----> " + strArr[i] + "\n");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return strArr;
    }

    public static String getMcuLogPath(Context context) {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        List volumes = ((StorageManager) context.getSystemService(StorageManager.class)).getVolumes();
        Log.d(TAG, "localPath = " + absolutePath);
        Iterator it = volumes.iterator();
        String str = BuildConfig.FLAVOR;
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                break;
            }
            VolumeInfo volumeInfo = (VolumeInfo) it.next();
            if (volumeInfo.getType() == 0 && volumeInfo.isMountedReadable()) {
                Log.d(TAG, "Volume path:" + volumeInfo.getPath());
                File file = new File(volumeInfo.getPath().toString());
                if (file.exists() && file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    int length = listFiles.length;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        File file2 = listFiles[i];
                        if (file2.isDirectory() && "mlog".equalsIgnoreCase(file2.getName())) {
                            str = file2.getPath();
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        Log.d(TAG, "savePath0 = " + str);
        if (BuildConfig.FLAVOR.equals(str)) {
            File file3 = new File(absolutePath + "/mlog");
            if (!file3.exists() || !file3.isDirectory()) {
                file3.mkdir();
            }
            str = file3.getPath();
        }
        Log.d(TAG, "savePath1 = " + str);
        File[] listFiles2 = new File(str).listFiles();
        if (listFiles2.length == 10) {
            Arrays.sort(listFiles2, $$Lambda$EventUtils$2imDI2VY0v4GLNfZIwz3GXM0.INSTANCE);
            boolean delete = listFiles2[0].delete();
            Log.d(TAG, "delete = " + delete + ", childFile size = " + listFiles2.length);
        }
        return str;
    }

    static /* synthetic */ int lambda$getMcuLogPath$0(File file, File file2) {
        return file2.lastModified() > file.lastModified() ? -1 : 1;
    }

    public static boolean isMcuUpgradeFileExist(Context context) {
        if (new File("/data/local/mcu_update.bin").exists()) {
            return true;
        }
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        for (String str : getAllPath(context)) {
            if (!str.equals(absolutePath)) {
                for (File file : new File(str).listFiles()) {
                    file.getAbsolutePath();
                    file.getName();
                    if (file.getName().toLowerCase().equals("mcu_update.bin")) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public static boolean isMcuAutoUpgradeFileExist(Context context) {
        if (new File("/data/local/mcu_update_auto.bin").exists()) {
            return true;
        }
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        for (String str : getAllPath(context)) {
            if (!str.equals(absolutePath)) {
                for (File file : new File(str).listFiles()) {
                    file.getAbsolutePath();
                    file.getName();
                    if (file.getName().toLowerCase().equals("mcu_update_auto.bin")) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public static class MyDate {
        public static String getFileName() {
            return new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date(System.currentTimeMillis()));
        }

        public static String getDateEN() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        }
    }

    public static void sendCarPlayPhoneStatusBroadcastEvt(Context context, int i) {
        if (context != null) {
            Intent intent = new Intent(ACTION_CARPLAY_TELEPHONE_STATUS_EVENT);
            intent.putExtra(ACTION_CARPLAY_TELEPHONE_STATUS_DATA, i);
            context.sendBroadcastAsUser(intent, UserHandle.ALL);
        }
    }

    public static void enterBtWithExtra(Context context, String str) {
        Intent intent = new Intent("android.intent.action.MAIN");
        Bundle bundle = new Bundle();
        bundle.putString("GotoPageNum", str);
        intent.putExtras(bundle);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(BT_MODE_PACKAGE_NAME, BT_MODE_CLASS_NAME));
        intent.setFlags(270532608);
        context.startActivity(intent);
    }

    public static int pxToDp(Context context, int i) {
        String recordValue = SysProviderOpt.getInstance(context).getRecordValue(SysProviderOpt.KSW_UI_RESOLUTION, "1920x720");
        return (!"1920x720".equalsIgnoreCase(recordValue) && "1280x480".equalsIgnoreCase(recordValue)) ? (int) (((double) i) / 1.5d) : i;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public static void deleteLocalMcUFile(Context context) {
        boolean recordBoolean = SysProviderOpt.getInstance(context).getRecordBoolean(SysProviderOpt.KSW_HAS_DELETE_MCU_UPGRADE_FILE, false);
        Log.d(TAG, "hasDelete = " + recordBoolean);
        File file = new File("/data/local/" + upgradeFileName);
        File file2 = new File("/data/local/" + autoUpgradeFileName);
        if (file.exists() || file2.exists()) {
            if (file.exists()) {
                file.delete();
            }
            if (file2.exists()) {
                file2.delete();
            }
        }
    }

    public static void showTXZIcon(Context context, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("showTXZIcon show = ");
        sb.append(z);
        sb.append(", m_iBTStatus = ");
        EventService eventService = (EventService) context;
        sb.append(eventService.getBTStatus());
        Log.d(TAG, sb.toString());
        if (z) {
            sendCarPlayPhoneStatusBroadcastEvt(context, 0);
            context.sendBroadcast(new Intent(ACTION_SHOW_VOICE_WND));
            Intent intent = new Intent("com.txznet.smartadapter.recv");
            intent.putExtra("floatview_control", 1);
            context.sendBroadcast(intent);
        } else {
            sendCarPlayPhoneStatusBroadcastEvt(context, 1);
            context.sendBroadcast(new Intent(ACTION_HIDE_VOICE_WND));
            Intent intent2 = new Intent("com.txznet.smartadapter.recv");
            intent2.putExtra("floatview_control", 2);
            context.sendBroadcast(intent2);
        }
        eventService.mShowTxzIcon = z;
    }

    public static void sendZlinkKeyCode(Context context, int i) {
        Log.d(TAG, "sendZlinkKeyCode keyCode = " + i);
        Intent intent = new Intent();
        intent.setAction(ZLINK_MODE_PACKAGE_NAME);
        intent.setPackage(ZLINK_MODE_PACKAGE_NAME);
        intent.putExtra("command", "REQ_SPEC_FUNC_CMD");
        intent.putExtra("specFuncCode", i);
        context.sendBroadcast(intent);
    }

    public static boolean isNaviKing(String str) {
        return "com.kingwaytek.naviking.telemetrics".equals(str) || "com.kingwaytek.naviking3d".equals(str) || "com.kingwaytek".equals(str) || "com.kingwaytek.naviking3d.google.std".equals(str);
    }
}
