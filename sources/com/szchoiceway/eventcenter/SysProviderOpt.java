package com.szchoiceway.eventcenter;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import java.util.ArrayList;

public class SysProviderOpt {
    public static final String ADO_LastLoopMode_KEY = "Ado_LastLoopMode";
    public static final String ADO_LastLrcMode_KEY = "Ado_LastLrcMode";
    public static final String ADO_LastPlayFilePath_KEY = "Ado_LastPlayFilePath";
    public static final String ADO_LastPlayFilePos_KEY = "Ado_LastPlayFilePos";
    public static final String ADO_LastPlayFileSize_KEY = "Ado_LastPlayFileSize";
    public static final String ADO_StorageType_KEY = "Ado_StorageType";
    public static final String AUDI_GT6_LEFT_CAR_ICON = "AUDI_GT6_LEFT_CAR_ICON";
    public static final String AUDI_GT6_RIGHT_WIDGET = "AUDI_GT6_RIGHT_WIDGET";
    public static final int BTTYPE_CHENGQIAN822 = 10;
    public static final int BTTYPE_FEIYITONG = 5;
    public static final int BTTYPE_IVT_BC5 = 6;
    public static final int BTTYPE_Qualcomm = 7;
    public static final int BTTYPE_SUDING_816 = 9;
    public static final int BTTYPE_SUDING_BC5 = 1;
    public static final int BTTYPE_SUDING_BC8 = 2;
    public static final int BTTYPE_WENQIANG_BC6 = 3;
    public static final int BTTYPE_WENQIANG_BC9 = 4;
    public static final int BTTYPE_WENQIANG_Qualcomm = 8;
    public static final String BT_AutoAnswer_KEY = "BT_AutoAnswer";
    public static final String CAN_DST_SET_KEY = "CAN_DST_SET_KEY";
    public static final String CHEKU_B_F_AMP_SETTINGS = "CHEKU_B_F_AMP_SETTINGS";
    public static final String CHEKU_L_R_AMP_SETTINGS = "CHEKU_L_F_AMP_SETTINGS";
    public static final String CHEKU_SHOW_RADAR_P = "CHEKU_SHOW_RADAR_P";
    private static final String CONTENT_NAME = "content://com.szchoiceway.eventcenter.SysVarProvider/SysVar";
    public static final String CPU_MAIN_FREQUENCY = "CPU_MAIN_FREQUENCY";
    public static final String FullscreenMode_KEY = "FullscreenMode";
    public static final String IMITAGE_ORIGINAL_CAL_STYLE_CLIENT = "IMITAGE_ORIGINAL_CAL_STYLE_CLIENT";
    public static final String IMITAGE_ORIGINAL_CAL_STYLE_INDEX = "IMITAGE_ORIGINAL_CAL_STYLE_INDEX";
    public static final String KESAIWEI_AMBIENT_LIGHT_B_VALUE = "KESAIWEI_AMBIENT_LIGHT_B_VALUE";
    public static final String KESAIWEI_AMBIENT_LIGHT_G_VALUE = "KESAIWEI_AMBIENT_LIGHT_G_VALUE";
    public static final String KESAIWEI_AMBIENT_LIGHT_R_VALUE = "KESAIWEI_AMBIENT_LIGHT_R_VALUE";
    public static final String KESAIWEI_BOTTOM_P_SWITCH = "KESAIWEI_RECORD_BT_INDEX";
    public static final String KESAIWEI_CHK_HAVE_DVD_MOVEMENT = "KESAIWEI_CHK_HAVE_DVD_MOVEMENT";
    public static final String KESAIWEI_CHK_HAVE_MP3_INTERFACE = "KESAIWEI_CHK_HAVE_MP3_INTERFACE";
    public static final String KESAIWEI_CUR_SELECT_POSITION = "KESAIWEI_CUR_SELECT_POSITION";
    public static final String KESAIWEI_FM_CUR_FREQ = "KESAIWEI_FM_CUR_FREQ";
    public static final String KESAIWEI_FM_FREQ_0 = "KESAIWEI_FM_FREQ_0";
    public static final String KESAIWEI_FM_FREQ_1 = "KESAIWEI_FM_FREQ_1";
    public static final String KESAIWEI_FM_FREQ_2 = "KESAIWEI_FM_FREQ_2";
    public static final String KESAIWEI_FM_FREQ_3 = "KESAIWEI_FM_FREQ_3";
    public static final String KESAIWEI_FM_FREQ_4 = "KESAIWEI_FM_FREQ_4";
    public static final String KESAIWEI_FM_FREQ_5 = "KESAIWEI_FM_FREQ_5";
    public static final String KESAIWEI_FM_ON = "KESAIWEI_FM_ON";
    public static final String KESAIWEI_HORN_SELECTION = "KESAIWEI_HORN_SELECTION";
    public static final String KESAIWEI_RECORD_AMPLIFIER = "KESAIWEI_RECORD_AMPLIFIER";
    public static final String KESAIWEI_RECORD_AUX_SWITCHING = "KESAIWEI_RECORD_AUX_SWITCHING";
    public static final String KESAIWEI_RECORD_BELT = "KESAIWEI_RECORD_BELT";
    public static final String KESAIWEI_RECORD_BT_CONNECT_MENU = "KESAIWEI_RECORD_BT_CONNECT_MENU";
    public static final String KESAIWEI_RECORD_BT_INDEX = "KESAIWEI_RECORD_BT_INDEX";
    public static final String KESAIWEI_RECORD_BT_OFF = "KESAIWEI_RECORD_BT_OFF";
    public static final String KESAIWEI_RECORD_CAR_TYPE = "KESAIWEI_RECORD_CAR_TYPE";
    public static final String KESAIWEI_RECORD_DVR = "KESAIWEI_RECORD_DVR";
    public static final String KESAIWEI_RECORD_PARK = "KESAIWEI_RECORD_PARK";
    public static final String KESAIWEI_SYS_BACKCAR_MIRROR = "KESAIWEI_SYS_BACKCAR_MIRROR";
    public static final String KESAIWEI_SYS_BACKCAR_PAUSE_PLAY = "KESAIWEI_SYS_BACKCAR_PAUSE_PLAY";
    public static final String KESAIWEI_SYS_CAMERA_SELECTION = "KESAIWEI_SYS_CAMERA_SELECTION";
    public static final String KESAIWEI_SYS_DVD_SELECTION = "KESAIWEI_SYS_DVD_SELECTION";
    public static final String KESAIWEI_SYS_FRONT_MIRROR = "KESAIWEI_SYS_FRONT_MIRROR";
    public static final String KESAIWEI_SYS_LANGUAGE = "KESAIWEI_SYS_LANGUAGE";
    public static final String KESAIWEI_SYS_MODE_SELECTION = "KESAIWEI_SYS_MODE_SELECTION";
    public static final String KESAIWEI_SYS_ORIGINAL_IS_24_HOUR = "KESAIWEI_SYS_IS_24_HOUR";
    public static final String KESAIWEI_SYS_RADAR = "KESAIWEI_SYS_RADAR";
    public static final String KESAIWEI_SYS_REVERSING_TRACK = "KESAIWEI_SYS_REVERSING_TRACK";
    public static final String KESAIWEI_SYS_SD_HOST = "KESAIWEI_SYS_SD_HOST";
    public static final String KESAIWEI_SYS_USER_TIME_TYPE = "KESAIWEI_SYS_USER_TIME_TYPE";
    public static final String KESAIWEI_SYS_VIDEO_DRIVING_BAN = "KESAIWEI_SYS_VIDEO_DRIVING_BAN";
    public static final String KEY_XINGSHUO_FORWARD_VIEW = "KEY_XINGSHUO_FORWARD_VIEW";
    public static final String KEY_XINGSHUO_FORWARD_VIEW_MIRROR = "KEY_XINGSHUO_FORWARD_VIEW_MIRROR";
    public static final String KEY_XINGSHUO_RIGHT_VIEW = "KEY_XINGSHUO_RIGHT_VIEW";
    public static final String KSE_TXZ_WARING_VALUE_OIL = "KSE_TXZ_WARING_VALUE_OIL";
    public static final String KSE_TXZ_WARING_VALUE_SPEED = "KSE_TXZ_WARING_VALUE_SPEED";
    public static final String KSW_360_CAMERA_TYPE_INDEX = "KSW_360_CAMERA_TYPE_INDEX";
    public static final String KSW_ACC_ON_FOCUS = "KSW_ACC_ON_FOCUS";
    public static final String KSW_ACTION_FACTORY_FLAG = "KSW_ACTION_FACTORY_FLAG";
    public static final String KSW_ACTION_IMPORT_CONFIG = "KSW_ACTION_IMPORT_CONFIG";
    public static final String KSW_AGREEMENT_SELECT_INDEX = "KSW_AGREEMENT_SELECT_INDEX";
    public static final String KSW_AHD_CAMERA_TYPE = "KSW_AHD_CAMERA_TYPE";
    public static final String KSW_AIRMATIC_STATUS = "KSW_AIRMATIC_STATUS";
    public static final String KSW_ANDROID_CAMERA_TYPE = "KSW_ANDROID_CAMERA_TYPE";
    public static final String KSW_APPS_ICON_SELECT_INDEX = "KSW_APPS_ICON_SELECT_INDEX";
    public static final String KSW_ARL_LEFT_SHOW_INDEX = "KSW_ARL_LEFT_SHOW_INDEX";
    public static final String KSW_ARL_RIGHT_SHOW_INDEX = "KSW_ARL_RIGHT_SHOW_INDEX";
    public static final String KSW_AUDIO_MAIN_SCROLL = "KSW_AUDIO_MAIN_SCROLL";
    public static final String KSW_AUTO_IMPORT_FACTORY_SETTINGS = "KSW_AUTO_IMPORT_FACTORY_SETTINGS";
    public static final String KSW_AUXILIARY_RADAR_STATUS = "KSW_AUXILIARY_RADAR_STATUS";
    public static final String KSW_AUX_ACTIVATION_FUNCTION_INDEX = "KSW_AUX_ACTIVATION_FUNCTION_INDEX";
    public static final String KSW_AUX_ITEM_POSITION = "KSW_AUX_ITEM_POSITION";
    public static final String KSW_AUX_ITEM_POSITION2 = "KSW_AUX_ITEM_POSITION2";
    public static final String KSW_BACKLIGHT_CONTROL_INDEX = "KSW_BACKLIGHT_CONTROL_INDEX";
    public static final String KSW_BENZ_THEME_BACKGROUND_INDEX = "KSW_BENZ_THEME_BACKGROUND_INDEX";
    public static final String KSW_BNEZ_AUX_ACTIVE = "KSW_BNEZ_AUX_ACTIVE";
    public static final String KSW_BOOT_360_CAMERA_INDEX = "KSW_BOOT_360_CAMERA_INDEX";
    public static final String KSW_BOOT_MODE_MEMORY_INDEX = "KSW_BOOT_MODE_MEMORY_INDEX";
    public static final String KSW_CCC_IDRIVE_TYPE = "KSW_CCC_IDRIVE_TYPE";
    public static final String KSW_CHK_VOICE_SWITCH = "KSW_CHK_VOICE_SWITCH";
    public static final String KSW_COCKBOARD_ASCENDING_STATUS = "KSW_COCKBOARD_ASCENDING_STATUS";
    public static final String KSW_COLLECT_CAN_DATA_SWITCH_INDEX = "KSW_COLLECT_CAN_DATA_SWITCH_INDEX";
    public static final String KSW_DASHBOARD_SELECT = "KSW_DASHBOARD_SELECT";
    public static final String KSW_DATA_CAR_MANUFACTURER_INDEX = "KSW_DATA_CAR_MANUFACTURER_INDEX";
    public static final String KSW_DATA_DECODER_V3 = "KSW_DATA_DECODER_V3";
    public static final String KSW_DATA_PRODUCT_INDEX = "KSW_DATA_PRODUCT_INDEX";
    public static final String KSW_DISTACNE_UNIT = "KSW_DISTACNE_UNIT";
    public static final String KSW_DO_NOT_INSTALL_CHK = "KSW_DO_NOT_INSTALL_CHK";
    public static final String KSW_DVR_APK_PACKAGENAME = "KSW_DVR_APK_PACKAGENAME";
    public static final String KSW_EVO_ID6_MAIN_INTERFACE_INDEX = "KSW_EVO_ID6_MAIN_INTERFACE_INDEX";
    public static final String KSW_EVO_MAIN_INTERFACE_INDEX = "KSW_EVO_MAIN_INTERFACE_INDEX";
    public static final String KSW_EVO_MAIN_INTERFACE_SELECT_ZOOM = "KSW_EVO_MAIN_INTERFACE_SELECT_ZOOM";
    public static final String KSW_EXTERNAL_INTERNAL_MIC_SELECTION = "KSW_EXTERNAL_INTERNAL_MIC_SELECTION";
    public static final String KSW_FACTORY_NO_DEBUG = "KSW_FACTORY_NO_DEBUG";
    public static final String KSW_FACTORY_SET_CLIENT = "KSW_FACTORY_SET_CLIENT";
    public static final String KSW_FACTORY_SET_CLIENT_ALS_TEST = "KSW_FACTORY_SET_CLIENT_ALS_TEST";
    public static final String KSW_FACTORY_SET_CLIENT_INDEX = "KSW_FACTORY_SET_CLIENT_INDEX";
    public static final String KSW_FACTORY_SET_PASSWORD = "KSW_FACTORY_SET_PASSWORD";
    public static final String KSW_FACTORY_VER = "KSW_FACTORY_VER";
    public static final String KSW_FAST_CHARGE = "KSW_FAST_CHARGE";
    public static final String KSW_FM_LAUNCH = "KSW_FM_LAUNCH";
    public static final String KSW_FOREIGN_BROWSER = "KSW_FOREIGN_BROWSER";
    public static final String KSW_HANDSET_AUTOMATIC_SET_INDEX = "KSW_HANDSET_AUTOMATIC_SET_INDEX";
    public static final String KSW_HAS_DELETE_MCU_UPGRADE_FILE = "KSW_HAS_DELETE_MCU_UPGRADE_FILE";
    public static final String KSW_HAS_ENABLE_360_APK = "KSW_HAS_ENABLE_360_APK";
    public static final String KSW_HAVE_AUX = "KSW_HAVE_AUX";
    public static final String KSW_HAVE_CARAUTO = "KSW_HAVE_CARAUTO";
    public static final String KSW_HAVE_DVD = "KSW_HAVE_DVD";
    public static final String KSW_HAVE_ECAR = "KSW_HAVE_ECAR";
    public static final String KSW_HAVE_EQ = "KSW_HAVE_EQ";
    public static final String KSW_HAVE_ESEXPLORER = "KSW_HAVE_ESEXPLORER";
    public static final String KSW_HAVE_FRONT_CAMERA = "KSW_HAVE_FRONT_CAMERA";
    public static final String KSW_HAVE_GPSAPP = "KSW_HAVE_GPSAPP";
    public static final String KSW_HAVE_HICAR = "KSW_HAVE_HICAR";
    public static final String KSW_HAVE_MANUAL = "KSW_HAVE_MANUAL";
    public static final String KSW_HAVE_TV = "KSW_HAVE_TV";
    public static final String KSW_HAVE_TXZ = "KSW_HAVE_TXZ";
    public static final String KSW_HAVE_WEATHER = "KSW_HAVE_WEATHER";
    public static final String KSW_INIT_ARM_UPGRADE = "KSW_INIT_ARM_UPGRADE";
    public static final String KSW_INIT_ARM_UPGRADE_GOOGLE_APP = "KSW_INIT_ARM_UPGRADE_GOOGLE_APP";
    public static final String KSW_INIT_LOGO = "KSW_INIT_LOGO";
    public static final String KSW_INIT_LOGO_ANIMOTION = "KSW_INIT_LOGO_ANIMOTION";
    public static final String KSW_INSTRUMENT_BACKLIGHT_VALUE = "KSW_INSTRUMENT_BACKLIGHT_VALUE";
    public static final String KSW_IS_9211_DEVICE = "KSW_IS_9211_DEVICE";
    public static final String KSW_JLR_MAIN_INTERFACE_INDEX = "KSW_JLR_MAIN_INTERFACE_INDEX";
    public static final String KSW_LANDROVER_HOST_INDEX = "KSW_LANDROVER_HOST_INDEX";
    public static final String KSW_LANDROVER_KEY_PANEL_LEFT_INDEX = "KSW_LANDROVER_KEY_PANEL_LEFT_INDEX";
    public static final String KSW_LANDROVER_KEY_PANEL_RIGHT_INDEX = "KSW_LANDROVER_KEY_PANEL_RIGHT_INDEX";
    public static final String KSW_LANDROVER_THEME_BACKGROUND_INDEX = "KSW_LANDROVER_THEME_BACKGROUND_INDEX";
    public static final String KSW_LANDRVOER_WHEEL_CONTROL_TYPE = "KSW_LANDRVOER_WHEEL_CONTROL_TYPE";
    public static final String KSW_LAUGUAGESELECT_INDEX = "KSW_LAUGUAGESELECT_INDEX";
    public static final String KSW_LEXUS_AIR_CONTROL = "KSW_LEXUS_AIR_CONTROL";
    public static final String KSW_LEXUS_ORIGIN_FM = "KSW_LEXUS_ORIGIN_FM";
    public static final String KSW_LOGO_TYPE_SELECT_INDEX = "KSW_LOGO_TYPE_SELECT_INDEX";
    public static final String KSW_MAP_KEY_FUNCTION_INDEX = "KSW_MAP_KEY_FUNCTION_INDEX";
    public static final String KSW_MODE_KEY_FUNCTION_INDEX = "KSW_MODE_KEY_FUNCTION_INDEX";
    public static final String KSW_OIL_UNIT = "KSW_OIL_UNIT";
    public static final String KSW_ORIGINAL_CAR_VIDEO_DISPLAY = "KSW_ORIGINAL_CAR_VIDEO_DISPLAY";
    public static final String KSW_ORIGINAL_INSTALL_MIC_SELECTION = "KSW_ORIGINAL_INSTALL_MIC_SELECTION";
    public static final String KSW_PHONE_KEY_FUNCTION_INDEX = "KSW_PHONE_KEY_FUNCTION_INDEX";
    public static final String KSW_RECORD_AUTO_TO_BT_MUSIC = "KSW_RECORD_AUTO_TO_BT_MUSIC";
    public static final String KSW_REVEERSING_MUTE_SELECT_INDEX = "KSW_REVEERSING_MUTE_SELECT_INDEX";
    public static final String KSW_REVEERSING_TYPE_SELECT_INDEX = "KSW_REVEERSING_TYPE_SELECT_INDEX";
    public static final String KSW_REVERSE_EXIT_TIME_CUSTOMIZE = "KSW_REVERSE_EXIT_TIME_CUSTOMIZE";
    public static final String KSW_REVERSE_EXIT_TIME_INDEX = "KSW_REVERSE_EXIT_TIME_INDEX";
    public static final String KSW_SCREEN_CAST_MS9120 = "KSW_SCREEN_CAST_MS9120";
    public static final String KSW_SELECTION_SMALL_CLOCK_INDEX = "KSW_SELECTION_SMALL_CLOCK_INDEX";
    public static final String KSW_SEND_TOUCH_DATA_CONTINUED = "KSW_SEND_TOUCH_DATA_CONTINUED";
    public static final String KSW_SHOW_AIR = "KSW_SHOW_AIR";
    public static final String KSW_SLEEP_TIME = "KSW_SLEEP_TIME";
    public static final String KSW_SPEED_TYPE_SELECTION = "KSW_SPEED_TYPE_SELECTION";
    public static final String KSW_SPLITTING_MACHINE_LVDS_MODE = "KSW_SPLITTING_MACHINE_LVDS_MODE";
    public static final String KSW_SUPPORT_DASHBOARD = "KSW_SUPPORT_DASHBOARD";
    public static final String KSW_SUPPORT_FACTORY_RESET = "KSW_SUPPORT_FACTORY_RESET";
    public static final String KSW_TEMP_UNIT = "KSW_TEMP_UNIT";
    public static final String KSW_TURN_SIGNAL_CONTROL = "KSW_TURN_SIGNAL_CONTROL";
    public static final String KSW_TXZ_WAKEUP = "KSW_TXZ_WAKEUP";
    public static final String KSW_TXZ_WAKEUP_Pinyin = "KSW_TXZ_WAKEUP_Pinyin";
    public static final String KSW_UI_RESOLUTION = "KSW_UI_RESOLUTION";
    public static final String KSW_USB_HOST_MODE = "KSW_USB_HOST_MODE";
    public static final String KSW_VOICE_KEY_FUNCTION_INDEX = "KSW_VOICE_KEY_FUNCTION_INDEX";
    public static final String KSW_WHELLTRACK_INDEX = "KSW_WHELLTRACK_INDEX";
    public static final String KSW_b_door_shang = "KSW_b_door_shang";
    public static final String KSW_b_door_xia = "KSW_b_door_xia";
    public static final String KSW_b_door_youshang = "KSW_b_door_youshang";
    public static final String KSW_b_door_youxia = "KSW_b_door_youxia";
    public static final String KSW_b_door_zuoshang = "KSW_b_door_zuoshang";
    public static final String KSW_b_door_zuoxia = "KSW_b_door_zuoxia";
    public static final String LAUNCHER_APPS_CUSTOMIZE_RESUM = "LAUNCHER_APPS_CUSTOMIZE_RESUM";
    public static final String LEFT_RADAR_8836_CHEKU = "LEFT_RADAR_8836_CHEKU";
    public static final String LEFT_TRACK_8836_CHEKU = "LEFT_TRACK_8836_CHEKU";
    public static final String MAIRUIWEI_BACKCAR_CONTROL = "MAIRUIWEI_BACKCAR_CONTROL";
    public static final String MAIRUIWEI_BACKCAR_RADAR = "MAIRUIWEI_BACKCAR_RADAR";
    public static final String MAIRUIWEI_BACKCAR_TRACK = "MAIRUIWEI_BACKCAR_TRACK";
    public static final String MAIRUIWEI_CAMERA_SELECT = "MAIRUIWEI_CAMERA_SELECT";
    public static final String MAIRUIWEI_DTV_CHANNEL_SELECT = "MAIRUIWEI_DTV_CHANNEL_SELECT";
    public static final String MAISILUO_LAUNCHER_APPS_CUSTOMIZE_RESUM = "MAISILUO_LAUNCHER_APPS_CUSTOMIZE_RESUM";
    public static final String MAISILUO_SYS_GOOGLEPLAY = "MAISILUO_SYS_GOOGLEPLAY";
    public static final String MCU_MSG_MUTE_STATE = "MCU_MSG_MUTE_STATE";
    public static final String MUSIC_ACTIVITYNAME = "Music_ActivityName";
    public static final String MUSIC_PACKAGENAME = "Music_PackageName";
    public static final String NAVI_ACTIVITYNAME = "Navi_ActivityName";
    public static final String NAVI_PACKAGENAME = "Navi_PackageName";
    public static final String NAV_ACTIVITYNAME = "NAV_ACTIVITYNAME";
    public static final String NAV_PACKAGENAME = "NAV_PACKAGENAME";
    public static final String Navi_FullscreenMode_KEY = "Navi_FullscreenMode";
    public static final String OUTPUT_VIDEO_FORMAT = "OUTPUT_VIDEO_FORMAT";
    public static final String RESOLUTION = "RESOLUTION";
    public static final String RIGHT_RADAR_8836_CHEKU = "RIGHT_RADAR_8836_CHEKU";
    public static final String RIGHT_TRACK_8836_CHEKU = "RIGHT_TRACK_8836_CHEKU";
    public static final String SET_AUTO_RUN_GPS_KEY = "Set_AutoRunGPS";
    public static final String SET_AUX_ALTERNATIVE_INDEX = "Set_Aux_Alternative_index";
    public static final String SET_BACKCAR_VIDEO = "Set_BackCar_Video";
    public static final String SET_BACK_TRACK_KEY = "Set_BackTrack";
    public static final String SET_BAL_FA_KEY = "Set_BalanaceFA";
    public static final String SET_BAL_LR_KEY = "Set_BalanaceLR";
    public static final String SET_BASS_FREQ_KEY = "Set_Bass_Freq";
    public static final String SET_BASS_KEY = "Set_Bass_Val";
    public static final String SET_BRAKE_DETECT_KEY = "Set_BrakeDetection";
    public static final String SET_CONSOLE_VERSION_INDEX = "Set_Console_Version_index";
    public static final String SET_Canbustype_KEY = "Set_Canbustype";
    public static final String SET_CarCanbusName_ID_KEY = "Set_CarCanbusName_ID";
    public static final String SET_Carstype_ID_KEY = "Set_Carstype_ID";
    public static final String SET_DAY_LIGHT_KEY = "Set_Day_Light";
    public static final String SET_DIM_LIGHT_KEY = "Set_DIM_Light";
    public static final String SET_DVD_MODE = "Set_Dvd_Mode";
    public static final String SET_EQMODE_KEY = "Set_Eq_Mode";
    public static final String SET_LOCAL_NAV_KEY = "Set_LocalNavi";
    public static final String SET_LOUDNESS_KEY = "Set_Loudness";
    public static final String SET_MIDDLE_FREQ_KEY = "Set_Middle_Freq";
    public static final String SET_MIDDLE_KEY = "Set_Middle_Val";
    public static final String SET_NIGHT_LIGHT_KEY = "Set_Night_Light";
    public static final String SET_NavClassName_KEY = "Set_NavClassName";
    public static final String SET_NavPackageName_KEY = "Set_NavPackageName";
    public static final String SET_NavSoundVolume_KEY = "Set_NavSoundVolume";
    public static final String SET_RIGHT_SIGN_DETECT_KEY = "Set_RightSignDetect";
    public static final String SET_SUBWOOFER_KEY = "Set_Subwoofer";
    public static final String SET_TOUCH_BEEP_KEY = "Set_TouchBeep";
    public static final String SET_TREBLE_FREQ_KEY = "Set_Treble_Freq";
    public static final String SET_TREBLE_KEY = "Set_Treble_Val";
    public static final String SET_USB_DVR_BACK_RUNNING = "Set_Usb_dvr_back_running";
    public static final String SET_USB_DVR_MODE = "Set_Usb_Dvr_Mode";
    public static final String SET_USER_BASS_KEY = "Set_User_Bass";
    public static final String SET_USER_MIDDLE_KEY = "Set_User_Middle";
    public static final String SET_USER_TREBLE_KEY = "Set_User_Treble";
    public static final String SET_USER_UI_TYPE = "Set_User_UI_Type";
    public static final String SET_USER_UI_TYPE_INDEX = "Set_User_UI_Type_index";
    public static final String STARTUP_ACTIVITYNAME = "Startup_ActivityName";
    public static final String STARTUP_PACKAGENAME = "Startup_PackageName";
    public static final String SYS_3D_DATA_RE_X = "Sys_3D_Data_Re_X";
    public static final String SYS_3D_DATA_RE_Y = "Sys_3D_Data_Re_Y";
    public static final String SYS_3D_DATA_RE_Z = "Sys_3D_Data_Re_Z";
    public static final String SYS_4G_ON_OFF_SET_VALUE_INDEX_KEY = "SYS_4G_ON_OFF_SET_VALUE_INDEX_KEY";
    public static final String SYS_8825_UI_NUM_KEY = "Sys_8825UINumber";
    public static final String SYS_APP_VERSION = "Sys_AppVersion";
    public static final String SYS_ASSISTIVE_TOUCH = "SYS_Assistive_Touch";
    public static final String SYS_AUTO_UPGRADE_MCU = "SYS_AUTO_UPGRADE_MCU";
    public static final String SYS_BACKCAR_6752_VIDEO_TYPE = "Sys_6752_Backcar_Video_Type";
    public static final String SYS_BACKCAR_AID = "Sys_Backcar_aid";
    public static final String SYS_BACKCAR_SND = "Sys_BackCar_Snd";
    public static final String SYS_BACKCAR_SND_ATT = "Sys_BackCar_Snd_Att";
    public static final String SYS_BACKCAR_VIDEO_TYPE = "Sys_backcar_Video_Type";
    public static final String SYS_BAMBIENT_LIGHT = "Sys_AmbientLight_on_off";
    public static final String SYS_BENZ_CONTROL_INDEX = "SYS_BENZ_CONTROL_INDEX";
    public static final String SYS_BIT_DEPTH_KEY = "SYS_BIT_DEPTH_KEY ";
    public static final String SYS_BRIGHT_SET = "Sys_bright_set";
    public static final String SYS_BT_TYPE_KEY = "Sys_BTDeviceType";
    public static final String SYS_CAR_TYPE_KEY = "Sys_CarType";
    public static final String SYS_CMMB_ONOFF_KEY = "SYS_CMMB_ONOFF_KEY";
    public static final String SYS_CONTRAST_SET = "Sys_Light_contrast_set";
    public static final String SYS_COUNTRY = "Sys_Country";
    public static final String SYS_DAY_NIGHT_MODE = "SYS_DAY_NIGHT_MODE";
    public static final String SYS_DISPLAY_MODE = "Sys_DisplayMode";
    public static final String SYS_DISPLAY_MODE2 = "Sys_DisplayMode2";
    public static final String SYS_DOOR_DISPLAYSET_VALUE_INDEX_KEY = "SYS_DOOR_DISPLAYSET_VALUE_INDEX_KEY";
    public static final String SYS_DOOR_SET_VALUE_INDEX_KEY = "SYS_DOOR_SET_VALUE_INDEX_KEY";
    public static final String SYS_FACTORY_SET_MCU_SET_6752_CHOICE_TYPE = "SYS_FACTORY_SET_MCU_SET_6752_CHOICE_TYPE";
    public static final String SYS_FACTORY_SET_MCU_SET_8844_CHOICE_TYPE = "SYS_FACTORY_SET_MCU_SET_8844_CHOICE_TYPE";
    public static final String SYS_FACTORY_SET_SHOW_KSW_LOGO = "SYS_FACTORY_SET_SHOW_KSW_LOGO";
    public static final String SYS_FAT_SET_ZHONGJIPAN = "Sys_Fat_Set_ZhongJiPam";
    public static final String SYS_GOOGLE_VOICE_SWITCH = "Sys_GoogleVoiceSwitch";
    public static final String SYS_GPSMIXSET_KEY = "SYS_GPSMIXSET_KEY";
    public static final String SYS_GPSVOL_VAL = "Sys_GpsVol_Val";
    public static final String SYS_GPS_SWITCH = "Sys_GPSSwitch";
    public static final String SYS_HIGH_FREQ_MODE = "SYS_HIGH_FREQ_MODE";
    public static final String SYS_HUE_SET = "Sys_hue_set";
    public static final String SYS_INPUT_METHOD = "SYS_INPUT_METHOD";
    public static final String SYS_LANDSCAPE_KEY = "Sys_Landscape";
    public static final String SYS_LAST_MODE_KEY = "Sys_Last_Mode";
    public static final String SYS_LEDCOLOR_SET_KEY = "SYS_LEDColor_Set";
    public static final String SYS_MCU_SET_KEY = "Sys_McuSet";
    public static final String SYS_MODE_MEMORY = "Sys_Mode_Memory";
    public static final String SYS_MUSIC_VOL_VAL_XINGSHUO = "SYS_MUSIC_VOL_VAL_XINGSHUO";
    public static final String SYS_ORIGINAL_CAMERA = "SYS_ORIGINAL_CAMERA";
    public static final String SYS_RADAR_DETECTION = "SYS_RADAR_DETECTION";
    public static final String SYS_RDS_ONOFF_KEY = "SYS_RDS_OnOff";
    public static final String SYS_REVERSE_IMAGE = "Sys_ReverseImage";
    public static final String SYS_SCREEN_DENSITY_KEY = "Sys_Screen_Density";
    public static final String SYS_SETDEFAULT_WALLPAPER = "SYS_SETDEFAULT_WALLPAPER";
    public static final String SYS_SETLOGO_INDEX = "SYS_SETLOGO_INDEX";
    public static final String SYS_SLEEP_SWITCH = "SYS_SLEEP_SWITCH";
    public static final String SYS_SLEEP_TIME = "SYS_SLEEP_TIME";
    public static final String SYS_SOUND_MODE = "Sys_SoundMode";
    public static final String SYS_TOUCH_ASSISTANT = "Sys_TouchAssistant";
    public static final String SYS_TOUCH_ORGIN_KEY = "Sys_TouchOrgin";
    public static final String SYS_TV_OUT_ON_OFF_SET_VALUE_INDEX_KEY = "SYS_TV_OUT_ON_OFF_SET_VALUE_INDEX_KEY";
    public static final String SYS_UI_NUMBER_KEY = "Sys_UINumber";
    public static final String SYS_VOLUME_BAR = "SYS_VOLUME_BAR";
    public static final String SYS_WHEEL_TRACK = "Sys_Wheel_Track";
    public static final String SYS_WIFI_PASSWORD_SWITCH = "Sys_WifiPasswordSwitch";
    public static final String SYS_WIFI_STATE = "SYS_WIFI_STATE";
    public static final String SYS_WND_IN_TOP = "Sys_Wnd_In_Top";
    public static final String SYS_ZHTY_UI_SELECT = "SYS_ZHTY_UI_SELECT";
    private static final String TAG = "SysProviderOpt";
    public static final String TVOUT_OUTPUT_ALL = "TVOUT_OUTPUT_ALL";
    public static final int UI_ANCHANGXING = 2;
    public static final int UI_AOCHEKAI = 32;
    public static final int UI_BORUIZENGHENG = 36;
    public static final int UI_CHEKU_1280X480 = 44;
    public static final int UI_CHWY_1280X480 = 48;
    public static final int UI_HANGFEI_1280X480 = 38;
    public static final int UI_HANGRUN = 4;
    public static final int UI_HUANGRUN_800X4800 = 35;
    public static final int UI_IMITATE_ORIGINAL_CAR_STYLE = 102;
    public static final int UI_KANGHUI_800X480 = 43;
    public static final int UI_KESAIWEI_1280X480 = 41;
    public static final int UI_KESHANG = 1;
    public static final int UI_KESHANG_1280X480 = 40;
    public static final int UI_MAIRUIWEI_800x480 = 45;
    public static final int UI_MAISILUO_1280X480 = 37;
    public static final int UI_NORMAL = 0;
    public static final int UI_NORMAL_1920x720 = 101;
    public static final int UI_PUSIRUI = 3;
    public static final int UI_XINGSHUO_800X480 = 42;
    public static final int UI_ZHONGHANGTIANYI_800x480 = 39;
    public static final String VDO_FullscreenMode_KEY = "Vdo_FullscreenMode";
    public static final String VDO_LastLoopMode_KEY = "Vdo_LastLoopMode";
    public static final String VDO_LastPlayFilePath_KEY = "Vdo_LastPlayFilePath";
    public static final String VDO_LastPlayFilePos_KEY = "Vdo_LastPlayFilePos";
    public static final String VDO_LastPlayFileSize_KEY = "Vdo_LastPlayFileSize";
    public static final String VDO_StorageType_KEY = "Vdo_StorageType";
    public static final String VIDEO_ACTIVITYNAME = "Video_ActivityName";
    public static final String VIDEO_PACKAGENAME = "Video_PackageName";
    public static final String VOICE_ACTIVITYNAME = "Voice_ActivityName";
    public static final String VOICE_PACKAGENAME = "Voice_PackageName";
    public static final String Video_SurfaceViewState = "Video_SurfaceViewState";
    public static final String XINGSHUO_SYS_CANBUSINFO = "XINGSHUO_SYS_CANBUSINFO";
    public static final String XINGSHUO_SYS_DVR = "XINGSHUO_SYS_DVR";
    public static final String XINGSHUO_SYS_GOOGLEPLAY = "XINGSHUO_SYS_GOOGLEPLAY";
    public static final String XINGSHUO_SYS_VOICE_SWITCH = "XINGSHUO_SYS_VOICE_SWITCH";
    public static final String ZHTY_TXZ_VOICE_ICON_SHOW = "ZXW_TXZ_VOICE_ICON_CONTROL";
    public static final String ZXW_ACTION_COPY_TO_PRIVDATA = "ZXW_ACTION_COPY_TO_PRIVDATA";
    public static final String ZXW_ACTION_WIFI_AP_SWITCH = "ZXW_ACTION_WIFI_AP_SWITCH";
    public static final String ZXW_ACTION_WIFI_SWITCH = "ZXW_ACTION_WIFI_SWITCH";
    public static final String ZXW_BT_SHOW_SPLIT_SCREEN_WND = "ZXW_BT_SHOW_SPLIT_SCREEN_WEND";
    public static final String ZXW_BT_VERSION = "ZXW_BT_VERSION";
    public static final String ZXW_CAN_VERSION = "com.szchoiceway.eventcenter.ZXW_CAN_VERSION";
    public static final String ZXW_CHEKU_SMALL_GPS_OR_WEATHER_PAGE = "ZXW_CHEKU_SMALL_GPS_OR_WEATHER_PAGE";
    public static final String ZXW_IS_ADD_AIR_VIEW = "mIsAddAirView";
    public static final String ZXW_IS_HAVE_DVD_APK = "ZXW_IS_HAVE_DVD_APK";
    public static final String ZXW_START_MUSIC_FOR_THE_FIRST_TIME = "Start_music_for_the_first_time";
    public static final String ZXW_START_RADIO_FOR_THE_FIRST_TIME = "Start_radio_for_the_first_time";
    public static final String ZXW_START_VIDEO_FOR_THE_FIRST_TIME = "Start_video_for_the_first_time";
    private static SysProviderOpt mSysProviderOpt = null;
    public static final boolean m_bMouse = false;
    public static boolean m_bSupportFocusMove = false;
    public static int m_iTest;
    private ContentResolver mCntResolver;
    private Context mContext;
    private Uri mUri = Uri.parse(CONTENT_NAME);

    public SysProviderOpt(Context context) {
        this.mCntResolver = context.getContentResolver();
    }

    public static SysProviderOpt getInstance(Context context) {
        if (mSysProviderOpt == null) {
            synchronized (SysProviderOpt.class) {
                if (mSysProviderOpt == null) {
                    mSysProviderOpt = new SysProviderOpt(context);
                }
            }
        }
        return mSysProviderOpt;
    }

    public Uri insertRecord(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("keyname", str);
        contentValues.put("keyvalue", str2);
        try {
            return this.mCntResolver.insert(this.mUri, contentValues);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0053 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getRecordValue(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = ""
            java.lang.String r4 = "keyname=?"
            r1 = 1
            java.lang.String[] r5 = new java.lang.String[r1]
            r1 = 0
            r5[r1] = r9
            r9 = 0
            android.content.ContentResolver r1 = r8.mCntResolver     // Catch:{ Exception -> 0x003a }
            android.net.Uri r2 = r8.mUri     // Catch:{ Exception -> 0x003a }
            r3 = 0
            r6 = 0
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x003a }
            if (r9 == 0) goto L_0x002e
            int r1 = r9.getCount()     // Catch:{ Exception -> 0x003a }
            if (r1 <= 0) goto L_0x002e
            boolean r1 = r9.moveToNext()     // Catch:{ Exception -> 0x003a }
            if (r1 == 0) goto L_0x002e
            java.lang.String r1 = "keyvalue"
            int r1 = r9.getColumnIndex(r1)     // Catch:{ Exception -> 0x003a }
            java.lang.String r1 = r9.getString(r1)     // Catch:{ Exception -> 0x003a }
            goto L_0x002f
        L_0x002e:
            r1 = r0
        L_0x002f:
            if (r9 == 0) goto L_0x004b
            r9.close()     // Catch:{ Exception -> 0x0035 }
            goto L_0x004b
        L_0x0035:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x003c
        L_0x003a:
            r1 = move-exception
            r2 = r0
        L_0x003c:
            if (r9 == 0) goto L_0x0041
            r9.close()
        L_0x0041:
            java.lang.String r9 = r1.toString()
            java.lang.String r1 = "SysProviderOpt"
            android.util.Log.e(r1, r9)
            r1 = r2
        L_0x004b:
            if (r1 == 0) goto L_0x0054
            boolean r9 = r0.equals(r1)
            if (r9 != 0) goto L_0x0054
            return r1
        L_0x0054:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.SysProviderOpt.getRecordValue(java.lang.String, java.lang.String):java.lang.String");
    }

    public String getRecordValue(String str) {
        String str2 = BuildConfig.FLAVOR;
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                str2 = query.getString(query.getColumnIndex("keyvalue"));
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return str2;
    }

    public int getRecordInteger(String str, int i) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    i = Integer.valueOf(string).intValue();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return i;
    }

    public long getRecordLong(String str, long j) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    j = Long.valueOf(string).longValue();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return j;
    }

    public float getRecordFloat(String str, float f) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    f = Float.valueOf(string).floatValue();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return f;
    }

    public boolean getRecordBoolean(String str, boolean z) {
        boolean z2 = true;
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    if (Integer.valueOf(string).intValue() != 1) {
                        z2 = false;
                    }
                    z = z2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return z;
    }

    public byte getRecordByte(String str, byte b) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    b = Byte.valueOf(string).byteValue();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return b;
    }

    public double getRecordDouble(String str, double d) {
        String recordValue = getRecordValue(str, BuildConfig.FLAVOR);
        return recordValue.length() > 0 ? Double.valueOf(recordValue).doubleValue() : d;
    }

    public void updateRecord(String str, String str2) {
        updateRecord(str, str2, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044 A[SYNTHETIC, Splitter:B:20:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateRecord(java.lang.String r11, java.lang.String r12, boolean r13) {
        /*
            r10 = this;
            java.lang.String r6 = "keyname=?"
            r0 = 1
            java.lang.String[] r7 = new java.lang.String[r0]
            r0 = 0
            r7[r0] = r11
            java.lang.String r8 = "keyvalue"
            java.lang.String[] r2 = new java.lang.String[]{r8}
            r9 = 0
            android.content.ContentResolver r0 = r10.mCntResolver     // Catch:{ Exception -> 0x0048 }
            android.net.Uri r1 = r10.mUri     // Catch:{ Exception -> 0x0048 }
            r5 = 0
            r3 = r6
            r4 = r7
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0048 }
            if (r0 == 0) goto L_0x003c
            int r1 = r0.getCount()     // Catch:{ Exception -> 0x0039 }
            if (r1 <= 0) goto L_0x003c
            android.content.ContentValues r11 = new android.content.ContentValues     // Catch:{ Exception -> 0x0039 }
            r11.<init>()     // Catch:{ Exception -> 0x0039 }
            r11.put(r8, r12)     // Catch:{ Exception -> 0x0039 }
            if (r0 == 0) goto L_0x0030
            r0.close()     // Catch:{ Exception -> 0x0039 }
            goto L_0x0031
        L_0x0030:
            r9 = r0
        L_0x0031:
            android.content.ContentResolver r12 = r10.mCntResolver     // Catch:{ Exception -> 0x0048 }
            android.net.Uri r13 = r10.mUri     // Catch:{ Exception -> 0x0048 }
            r12.update(r13, r11, r6, r7)     // Catch:{ Exception -> 0x0048 }
            goto L_0x0042
        L_0x0039:
            r11 = move-exception
            r9 = r0
            goto L_0x0049
        L_0x003c:
            if (r13 == 0) goto L_0x0041
            r10.insertRecord(r11, r12)     // Catch:{ Exception -> 0x0039 }
        L_0x0041:
            r9 = r0
        L_0x0042:
            if (r9 == 0) goto L_0x0057
            r9.close()     // Catch:{ Exception -> 0x0048 }
            goto L_0x0057
        L_0x0048:
            r11 = move-exception
        L_0x0049:
            if (r9 == 0) goto L_0x004e
            r9.close()
        L_0x004e:
            java.lang.String r11 = r11.toString()
            java.lang.String r12 = "SysProviderOpt"
            android.util.Log.e(r12, r11)
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.SysProviderOpt.updateRecord(java.lang.String, java.lang.String, boolean):void");
    }

    public void setRecordDefaultValue(String str, String str2) {
        if (!checkRecordExist(str)) {
            insertRecord(str, str2);
        }
    }

    public boolean checkRecordExist(String str) {
        boolean z = true;
        boolean z2 = false;
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            cursor = this.mCntResolver.query(this.mUri, new String[]{"keyvalue"}, "keyname=?", strArr, (String) null);
            if (cursor == null || cursor.getCount() <= 0) {
                z = false;
            }
            if (cursor == null) {
                return z;
            }
            try {
                cursor.close();
                return z;
            } catch (Exception e) {
                e = e;
                z2 = z;
            }
        } catch (Exception e2) {
            e = e2;
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
            return z2;
        }
    }

    public ArrayList<String> queryRecordItemName() {
        new String[]{BuildConfig.FLAVOR};
        String[] strArr = {"keyname"};
        ArrayList<String> arrayList = new ArrayList<>(100);
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, strArr, (String) null, (String[]) null, (String) null);
            int count = query.getCount();
            query.getColumnIndex("keyname");
            query.moveToFirst();
            if (query != null && count > 0) {
                for (int i = 0; i < count; i++) {
                    arrayList.add(query.getString(0));
                    query.moveToNext();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return arrayList;
    }
}
