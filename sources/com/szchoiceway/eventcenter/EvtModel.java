package com.szchoiceway.eventcenter;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.example.mylibrary.BuildConfig;
import com.szchoiceway.eventcenter.EventUtils;
import com.szchoiceway.eventcenter.ICallbackfn;
import java.util.Iterator;

public class EvtModel extends BroadcastReceiver {
    public static final String ACTION_PHONE_STATE = "android.intent.action.PHONE_STATE";
    public static final String ACTION_UNISOUND_VOICE_END = "com.unisound.intent.action.ACTION_UNISOUND_VOICE_END";
    public static final String ACTION_UNISOUND_VOICE_START = "com.unisound.intent.action.ACTION_UNISOUND_VOICE_START";
    public static final String ACTION_VOICE_CTRL = "com.szchoiceway.ACTION_VOICE_CTRL";
    public static final String BROADCAST_APP_QUIT = "net.easyconn.app.quit";
    public static final String BROADCAST_BT_A2DP_ACQUIRE = "net.easyconn.a2dp.acquire";
    public static final String BROADCAST_BT_A2DP_RELEASE = "net.easyconn.a2dp.release";
    public static final String BROADCAST_BT_CHECKSTATUS = "net.easyconn.bt.checkstatus";
    public static final String BROADCAST_BT_CONNECT = "net.easyconn.bt.connect";
    public static final String BROADCAST_BT_CONNECTED = "net.easyconn.bt.connected";
    public static final String BROADCAST_BT_NOT_CONNECTED = "net.easyconn.bt.notconnected";
    public static final String BROADCAST_BT_OPENED = "net.easyconn.bt.opened";
    public static final String BROADCAST_CONNECTION_BREAK = "net.easyconn.connection.break";
    public static final String BROADCAST_EASYCONN_OPEN = "net.easyconn.app.open";
    public static final String BROADCAST_EASYCONN_PAUSE = "net.easyconn.app.pause";
    public static final String BROADCAST_EASYCONN_RESUME = "net.easyconn.app.resume";
    public static final String BROADCAST_MIUDRIVE_ONPAUSE = "com.didi365.miudrive.navi.main.onPause";
    public static final String BROADCAST_MIUDRIVE_ONRESUME = "com.didi365.miudrive.navi.main.onResume";
    public static final String BROADCAST_MIUDRIVE_ONSTATR = "com.didi365.miudrive.navi.main.onStart";
    public static final String BROADCAST_MIUDRIVE_ONSTOP = "com.didi365.miudrive.navi.main.onStop";
    public static final String EXTRA_APP_KEY_DIM = "VoiceKeyDIM";
    public static final String EXTRA_APP_KEY_MAINVOL = "VoiceKeyMAINVOL";
    public static final String EXTRA_APP_KEY_MUTE = "VoiceKeyMUTE";
    public static final String EXTRA_VOICE_KEY_WORD = "VoiceKeyWord";
    public static final String EXTRA_VOICE_PARAM = "VoiceParam";
    public static final String EXTRA_guanji = "guanji";
    public static final String EXTRA_jingyin = "jingyin";
    public static final String EXTRA_liangdu = "liangdu";
    public static final String EXTRA_yinliangjia = "yinliangjia";
    public static final String EXTRA_yinliangjian = "yinliangjian";
    public static final String EXTRA_yinliangzhi = "yinliangzhi";
    private static final String LOG_TAG = "HomeReceiver EvtModel";
    public static final String RECV_ECAR_ACTION_EVENT = "com.android.ecar.send";
    public static final String RECV_SYSTEM_DATA_EVT = "android.systemui.toapp.data";
    public static final String SEND_APP_ACTION_EVT = "com.szchoiceway.SEND_APP_ACTION_EVT";
    public static final String SEND_ECAR_ACTION_EVENT = "com.android.ecar.recv";
    public static final String SEND_PHONE_NAME_NUM_EVENT = "com.szchoiceway.btsuite.SEND_PHONE_NAME_NUM_EVENT";
    public static final String SEND_PHONE_NAME_NUM_EXTRA = "com.szchoiceway.btsuite.SEND_PHONE_NAME_NUM_EXTRA";
    private static final String SYSTEM_DIALOG_REASON_ASSIST = "assist";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_LOCK = "lock";
    private static final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    private static final String TAG = "EvtModel";
    public static final String TXZ_TO_TXZ_KILLPROCESS = "com.txznet.TXZSDKDemo.TXZ_TO_TXZ_KILLPROCESS";
    public static final String TXZ_TO_TXZ_KILLPROCESS_EXTRA = "com.txznet.TXZSDKDemo.TXZ_TO_TXZ_KILLPROCESS_EXTRA";
    public static final int VOL_MAX = 40;
    private static final int WM_RESET_MCU_EVT = 2;
    private static final String ZXW_ACTION_SYS_DSP_VOL_EVT = "com.choiceway.action.dsp_vol";
    private static final String ZXW_ACTION_SYS_DSP_VOL_EVT_EXTRA = "com.choiceway.action.dsp_vol_extra";
    private static final String ZXW_ACTION_SYS_DSP_VOL_EVT_EXTRA_ADD = "dsp_vol_add";
    private static final String ZXW_ACTION_SYS_DSP_VOL_EVT_EXTRA_SUB = "dsp_vol_sub";
    public static final String ZXW_CAN_KEY_EVT_NEXT = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_NEXT";
    public static final String ZXW_CAN_KEY_EVT_PLAY_PAUSE = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_PLAY_PAUSE";
    public static final String ZXW_CAN_KEY_EVT_PREV = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_PREV";
    public static final String ZXW_CAN_WHEEL_TRACK_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT";
    public static final String ZXW_CAN_WHEEL_TRACK_EVT_EXTRA = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT_EXTRA";
    public static final String ZXW_RELOAD_TOUCH_KEY_CFG = "com.choiceway.FatUtils.ZXW_RELOAD_TOUCH_KEY_CFG";
    public static final String ZXW_SOUND_CONTROL_BACK_EVT = "com.choiceway.eventcenter.EventUtils.ZXW_SOUND_CONTROL_BACK_EVT";
    public static final String ZXW_TOUCH_LEARN_ID = "com.choiceway.FatUtils.ZXW_TOUCH_LEARN_ID";
    public static final String ZXW_TOUCH_LEARN_ID_EXTRA = "com.choiceway.FatUtils.ZXW_TOUCH_LEARN_ID_EXTRA";
    public static String _CMD_ = "ecarSendKey";
    public static String _KEYS_ = "keySet";
    public static String _TYPE_ = "cmdType";
    public static String _TYPE_STANDCMD_ = "standCMD";
    public static String card_dir = "/storage/external_sd/";
    public static String gpscard_dir = "/storage/external_sd0/";
    /* access modifiers changed from: private */
    public static String mcu_upgrade_ZHTY_filename = "Update.nbd";
    public static String mediacard_dir = "/storage/external_sd/";
    public static String usb_dir = "/stor    age/usb_storage0/";
    private static final String usb_otg_switch = "/sys/devices/platform/soc/4e00000.ssusb/mode";
    private boolean bAmapautoSound = false;
    private boolean bTXZSound = false;
    boolean bWifiNetWorkState = false;
    private boolean bXingshuoAuxChannal = false;
    Handler handleProgress = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 2 && EvtModel.this.mContext != null) {
                EvtModel.this.mContext.SendResetMcu((byte) 1);
            }
        }
    };
    private final AudioManager.OnAudioFocusChangeListener mAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            Log.i(EvtModel.TAG, "onAudioFocusChange: focusChange = " + i);
        }
    };
    private AudioFocusRequest mAudioFocusRequest;
    private AudioManager mAudioManager;
    private int mBtStatus = -1;
    protected EventService mContext = null;
    private int mLastDisplayMode = 0;
    private ICallbackfn.Stub mModeCallback = new ICallbackfn.Stub() {
        public void notifyEvt(int i, int i2, int i3, byte[] bArr, String str) throws RemoteException {
        }
    };
    private int mStreamVolume;
    protected int m_iCurrDim = 0;
    protected boolean register = false;

    private void initPhoneListener() {
    }

    static {
        if (Build.VERSION.SDK_INT > 19) {
        }
    }

    public EvtModel(EventService eventService) {
        this.mContext = eventService;
    }

    public void registerReceiver() {
        if (this.register) {
            unregisterReceiver();
        }
        if (this.mContext != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(EventUtils.HBCP_EVT_HSHF_STATUS);
            intentFilter.addAction(EventUtils.HBCP_EVT_HSHF_GET_STATUS);
            intentFilter.addAction("android.net.wifi.STATE_CHANGE");
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
            intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            intentFilter.addAction(EventUtils.ZXW_ARRAY_BYTE_DATA_EVT);
            intentFilter.addAction("com.choiceway.FatUtils.ZXW_TOUCH_LEARN_ID");
            intentFilter.addAction("com.android.quicksetting.BROADCAST");
            intentFilter.addAction(RECV_SYSTEM_DATA_EVT);
            intentFilter.addAction(EventUtils.ZXW_CAN_KEY_EVT);
            intentFilter.addAction("com.choiceway.FatUtils.ZXW_RELOAD_TOUCH_KEY_CFG");
            intentFilter.addAction("com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT");
            intentFilter.addAction("net.easyconn.app.open");
            intentFilter.addAction("net.easyconn.app.resume");
            intentFilter.addAction("net.easyconn.bt.checkstatus");
            intentFilter.addAction("net.easyconn.a2dp.acquire");
            intentFilter.addAction("net.easyconn.a2dp.release");
            intentFilter.addAction("net.easyconn.app.quit");
            intentFilter.addAction("net.easyconn.bt.connect");
            intentFilter.addAction(BROADCAST_EASYCONN_PAUSE);
            intentFilter.addAction(EventUtils.ACTION_CHANGE_MODE_EVENT);
            intentFilter.addAction(RECV_SYSTEM_DATA_EVT);
            intentFilter.addAction(BROADCAST_MIUDRIVE_ONSTATR);
            intentFilter.addAction(BROADCAST_MIUDRIVE_ONRESUME);
            intentFilter.addAction(BROADCAST_MIUDRIVE_ONPAUSE);
            intentFilter.addAction(BROADCAST_MIUDRIVE_ONSTOP);
            intentFilter.addAction(EventUtils.ACTION_MCU_CMD_EVENT);
            intentFilter.addAction("com.choiceway.eventcenter.EventUtils.ZXW_SOUND_CONTROL_BACK_EVT");
            intentFilter.addAction("com.kugou.android.musicservicecommand");
            intentFilter.addAction("com.android.music.musicservicecommand");
            intentFilter.addAction("com.android.music.playbackcomplete");
            intentFilter.addAction("com.android.music.playstatechanged");
            intentFilter.addAction("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_PREV");
            intentFilter.addAction("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_PLAY_PAUSE");
            intentFilter.addAction("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_NEXT");
            intentFilter.addAction(EventUtils.ZXW_SENDBROADCAST8902MOD);
            intentFilter.addAction(EventUtils.ACTION_SWITCH_ORIGINACAR);
            intentFilter.addAction(EventUtils.ACTION_SWITCH_USBDVRMODE);
            intentFilter.addAction("android.NaviOne.voiceprotocol");
            intentFilter.addAction(EventUtils.ZXW_EVENT_MCUUPGRADE_DATA_EVT);
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction(EventUtils.ZXW_SET_VALIDE_MODE_INFO);
            intentFilter.addAction(EventUtils.ZXW_AVM_LEFT_RIGHT_BACK);
            intentFilter.addAction(EventUtils.MSG_EVENT_TEST);
            intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
            intentFilter.addAction(EventUtils.ZHTY_MCU_SWITCH_MEDIA_CHANNAL);
            intentFilter.addAction(EventUtils.ZHTY_MCU_SWITCH_GPS_CHANNAL);
            intentFilter.addAction(EventUtils.ZHTY_SEND_MEDIA_BT_GPS_VOL);
            intentFilter.addAction(EventUtils.CHEKU_BOTTOM_KEY);
            intentFilter.addAction(EventUtils.ZXW_KILL_ALL_APK_XINGSHUO);
            intentFilter.addAction(EventUtils.KSW_RETURN_MODE_REQUEST);
            intentFilter.addAction(EventUtils.BROADCAST_KSW_DVD_MUSIC_SWITCH_VIDEO_VIEW);
            intentFilter.addAction(EventUtils.REC_AUTONAVI_STANDARD);
            intentFilter.addAction("CLEAR_ALL_ACTIVITY");
            intentFilter.addAction(TXZ_TO_TXZ_KILLPROCESS);
            intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
            intentFilter.addAction(ACTION_VOICE_CTRL);
            intentFilter.addAction("com.unisound.intent.action.ACTION_UNISOUND_VOICE_START");
            intentFilter.addAction("com.unisound.intent.action.ACTION_UNISOUND_VOICE_END");
            intentFilter.addAction(EventUtils.ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_EVT);
            if (this.mContext.m_iUITypeVer == 39 || this.mContext.m_iUITypeVer == 1 || this.mContext.m_iUITypeVer == 41 || this.mContext.m_iUITypeVer == 42 || this.mContext.m_iUITypeVer == 101 || this.mContext.m_iUITypeVer == 48) {
                intentFilter.addAction("com.zxw.upgrade");
                intentFilter.addAction("com.android.SoundFocus.Broadcast");
            }
            intentFilter.addAction(EventUtils.ZXW_ACTION_CONTROL_SPLIT_SCREEN_KEY);
            intentFilter.addAction(EventUtils.CHEKU_WEATHER_IPC);
            intentFilter.addAction(EventUtils.ZXW_SYS_KEYBOARD_SHOW);
            intentFilter.addAction(EventUtils.ZXW_SYS_KEYBOARD_HIDE);
            intentFilter.addAction("com.szchoiceway.initEventState");
            intentFilter.addAction(EventUtils.VALID_MODE_INFOR_CHANGE);
            intentFilter.addAction(EventUtils.ZXW_CAN_MAIN_OTHER_VER);
            intentFilter.addAction(EventUtils.MCU_CAR_CAN_RADAR_INFO);
            intentFilter.addAction("com.szchoiceway.action.startFocusMove");
            intentFilter.addAction(EventUtils.ZLINK_MODE_PACKAGE_NAME);
            intentFilter.addAction(EventUtils.HICAR_MODE_PACKAGE_NAME);
            intentFilter.addAction(EventUtils.ACTION_SETTINGS_FACTORY_RESET);
            intentFilter.addAction("com.android.sz.zxw.factoryreset");
            intentFilter.addAction(EventUtils.MCU_MSG_MAIL_VOL);
            intentFilter.addAction("com.choiceway.dsp.action.SendDataToMcu");
            intentFilter.addAction("com.szchoiceway.zxwauto.ACTION_MEDIAPLAYSTATE");
            intentFilter.addAction("com.szchoiceway.zxwauto.ACTION_CONNECTED");
            intentFilter.addAction("com.szchoiceway.zxwauto.ACTION_DISCONNECT");
            intentFilter.addAction("com.szchoiceway.zxwauto.ACTION_KEYEVENTNOTIFY");
            intentFilter.addAction("com.szchoiceway.zxwauto.ACTION_AUTOMODENOTIFY");
            intentFilter.addAction("com.szchoiceway.carplay.ACTION_NOTIFY");
            intentFilter.addAction("com.szchoiceway.carplay.ACTION_AUDIOFOCUS");
            intentFilter.addAction("com.txznet.txz.record.show");
            intentFilter.addAction("com.txznet.txz.record.dismiss");
            intentFilter.addAction("com.liaoy.rcp.ACTION_VERSION");
            intentFilter.addAction("com.liaoy.rcp.ACTION_SHOWTIPUI");
            intentFilter.addAction("com.liaoy.rcp.ACTION_HUHAVEGPS");
            intentFilter.addAction("com.szchoiceway.action.toSaveConfig");
            intentFilter.addAction("com.szchoiceway.action.reboot");
            intentFilter.addAction("com.szchoiceway.action.Illegal_authorization");
            intentFilter.addAction("com.choiceway.action.btsuite_version");
            intentFilter.addAction("com.systemui.ethernet.connected");
            intentFilter.addAction(EventUtils.HICAR_MODE_PACKAGE_NAME);
            intentFilter.addAction("com.liaoyuan.mylink");
            intentFilter.addAction(EventUtils.CARLINK_MODE_PACKAGE_NAME);
            intentFilter.addAction(EventUtils.ZXW_ACTION_UPDATE_GPS_TIME);
            intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
            intentFilter.addAction(EventUtils.ZXW_ACTION_LETTER_BROADCAST);
            intentFilter.addAction(EventUtils.ZXW_ACTION_UPDATE_CONFIGURATION);
            intentFilter.addAction(EventUtils.ZXW_ACTION_KSW_END_MCU_LOGCAT);
            intentFilter.addAction(EventUtils.ZXW_ACTION_SYS_MODE_TITLE_CHANGE_EVT);
            intentFilter.addAction(EventUtils.ZXW_ACTION_TOUCH_CALIBRATION);
            intentFilter.addAction(EventUtils.ZXW_ACTION_RESTART_ZXWMEDIA);
            intentFilter.addAction("com.atelectronic.atavm3d.tosys");
            intentFilter.addAction("com.carletter.link.sendKeyCode");
            intentFilter.addAction("carletter.action.wired.carplay.state");
            intentFilter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
            intentFilter.addAction(EventUtils.ZXW_ACTION_SET_RESOLUTION_BY_DENSITY);
            intentFilter.addAction(EventUtils.ZXW_TEST_ACTION_SET_DENSITY);
            intentFilter.addAction("com.szchoiceway.eventcenter.test_ap");
            intentFilter.addAction("com.szchoiceway.eventcenter.test_check_config");
            intentFilter.addAction(EventUtils.ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_RECEIVE);
            intentFilter.addAction("ZXW_TEST_ACTION_READ_TOUCH_PARAM");
            intentFilter.addAction("ZXW_TEST_ACTION_REQUEST_AIR_DATA");
            intentFilter.addAction(EventUtils.ZXW_ACTION_REBOOT_SYS_REBOOT);
            intentFilter.addAction(EventUtils.ZXW_ACTION_ENTER_THIRD_MEDIA);
            intentFilter.addAction(EventUtils.ZXW_ACTION_LIDIAN_DIALOG_STATUS);
            intentFilter.addAction(EventUtils.ZXW_ACTION_XYQ_DIALOG_STATUS_EXTRA);
            intentFilter.addAction(EventUtils.ZXW_KSW_SEND_MODE_0X67);
            intentFilter.addAction(EventUtils.ZXW_SET_TOUCH_TYPE);
            intentFilter.addAction("TEST_ACTION_GET_TOP_ACTIVITY");
            intentFilter.addAction(EventUtils.ACTION_CARPLAY_ENABLE_AP);
            intentFilter.addAction(EventUtils.ACTION_CARPLAY_DISABLE_AP);
            intentFilter.addAction("TEST_KILL_HICAR");
            intentFilter.addAction(EventUtils.ZXW_LAUNCHER_ACTION_KILL_PROGRESS);
            intentFilter.addAction(EventUtils.ZXW_ACTION_SEND_THIRD_MEDIA_SOURCE);
            intentFilter.addAction(EventUtils.ZXW_ACTION_VOLUME_ADD);
            intentFilter.addAction(EventUtils.ZXW_ACTION_VOLUME_REDUCE);
            intentFilter.addAction(EventUtils.ZXW_ACTION_REFRESH_SMALL_CLOCK_INDEX);
            intentFilter.addAction("txz_care_test");
            intentFilter.addAction(EventUtils.ZXW_ACTION_BT_SERVICE_ON_DESTROY);
            intentFilter.addAction(EventUtils.ZXW_ACTION_KSW_THEME_CHANGE);
            intentFilter.addAction(EventUtils.ZXW_LAUNCHER_ACTION_IN_RECENT);
            intentFilter.addAction(EventUtils.ZXW_LAUNCHER_ACTION_SPLIT_SCREEN);
            intentFilter.addAction(EventUtils.ZXW_ACTION_KSW_VIDEOPLAYER_FULL_SCREEN);
            intentFilter.addAction(EventUtils.ZXW_ACTION_KSW_SHOW_SOFT_KEYBOARD);
            intentFilter.addAction(EventUtils.ZXW_ACTION_DELAY_AUX);
            intentFilter.addAction(EventUtils.ZXW_ACTION_START_EQ);
            intentFilter.addAction(EventUtils.ZXW_ACTION_REQUEST_BT_STATUS);
            intentFilter.addAction("TEST_SEND_CARPLAY_KEYCODE");
            intentFilter.addAction("TEST_ACTION_SET_VOLUME");
            intentFilter.addAction("360_test");
            intentFilter.addAction("360_test2");
            intentFilter.addAction("media_test");
            intentFilter.addAction("backcar_mute_test");
            intentFilter.addAction("light_test");
            intentFilter.addAction("hw_test_enter");
            intentFilter.addAction("hw_test_exit");
            intentFilter.addAction("switch_9211");
            intentFilter.addAction("allpath_test");
            intentFilter.addAction("top_test");
            intentFilter.addAction("decoder_test");
            intentFilter.addAction("air_test");
            intentFilter.addAction("zlink_keycode_test");
            intentFilter.addAction("bt_status_test");
            intentFilter.addAction("360_radar_test");
            intentFilter.addAction("txz_icon_test");
            this.mContext.registerReceiver(this, intentFilter);
            this.register = true;
        }
        Log.i(TAG, "registerReceiver");
    }

    public void unregisterReceiver() {
        EventService eventService;
        if (this.register && (eventService = this.mContext) != null) {
            eventService.unregisterReceiver(this);
            this.register = false;
        }
        Log.i(TAG, "unregisterReceiver");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:1169:0x1ef1  */
    /* JADX WARNING: Removed duplicated region for block: B:1174:0x1f19  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r27, android.content.Intent r28) {
        /*
            r26 = this;
            r1 = r26
            r2 = r27
            r3 = r28
            java.lang.String r4 = r28.getAction()
            java.lang.String r0 = "com.szchoiceway.btsuite.HBCP_EVT_HSHF_STATUS"
            boolean r0 = r4.equals(r0)
            java.lang.String r5 = "COM.SZCHOICEWAY_BRIGHTNESS_SETTINGS"
            r6 = 7
            java.lang.String r7 = "1"
            r8 = 6
            r10 = -1
            r14 = 3
            java.lang.String r15 = "EvtModel"
            r11 = 1
            r13 = 0
            if (r0 != 0) goto L_0x00d0
            java.lang.String r0 = "com.szchoiceway.btsuite.HBCP_EVT_HSHF_GET_STATUS"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0028
            goto L_0x00d0
        L_0x0028:
            java.lang.String r0 = "android.net.wifi.STATE_CHANGE"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0081
            java.lang.String r0 = "networkInfo"
            android.os.Parcelable r0 = r3.getParcelableExtra(r0)
            if (r0 == 0) goto L_0x006d
            android.net.NetworkInfo r0 = (android.net.NetworkInfo) r0
            android.net.NetworkInfo$State r0 = r0.getState()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r12 = "onReceive: WIFI状态-state = "
            r9.append(r12)
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            android.util.Log.i(r15, r9)
            android.net.NetworkInfo$State r9 = android.net.NetworkInfo.State.DISCONNECTED
            if (r0 != r9) goto L_0x0067
            r1.bWifiNetWorkState = r13
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.GPSMonitor r0 = r0.getGPSMonitor()
            if (r0 == 0) goto L_0x006d
            r0.setUpdateTime(r11)
            r0.getLocation()
            goto L_0x006d
        L_0x0067:
            android.net.NetworkInfo$State r9 = android.net.NetworkInfo.State.CONNECTED
            if (r0 != r9) goto L_0x006d
            r1.bWifiNetWorkState = r11
        L_0x006d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x01fa
            boolean r9 = r1.bWifiNetWorkState
            r0.refreshWifiStatus((boolean) r9)
            boolean r0 = r1.bWifiNetWorkState
            if (r0 != 0) goto L_0x01fa
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.refreshWifiStatus((int) r13)
            goto L_0x01fa
        L_0x0081:
            java.lang.String r0 = "android.net.wifi.WIFI_STATE_CHANGED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x01fa
            java.lang.String r0 = "wifi_state"
            int r0 = r3.getIntExtra(r0, r13)
            if (r0 == r11) goto L_0x00a5
            if (r0 == r14) goto L_0x0095
            goto L_0x01fa
        L_0x0095:
            java.lang.String r0 = "onReceive WIFI_STATE_ENABLED"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r9 = "ZXW_ACTION_WIFI_SWITCH"
            r0.updateRecord(r9, r7)
            goto L_0x01fa
        L_0x00a5:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r9 = "onReceive WIFI_STATE_DISABLED ksw_m_b_acc_off = "
            r0.append(r9)
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            boolean r9 = r9.ksw_m_b_acc_off
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.ksw_m_b_acc_off
            if (r0 != 0) goto L_0x01fa
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r9 = "ZXW_ACTION_WIFI_SWITCH"
            java.lang.String r12 = "0"
            r0.updateRecord(r9, r12)
            goto L_0x01fa
        L_0x00d0:
            java.lang.String r0 = "com.szchoiceway.btsuite.DATA_STR"
            r3.getStringExtra(r0)
            java.lang.String r0 = "com.szchoiceway.btsuite.DATA_INT"
            int r0 = r3.getIntExtra(r0, r13)
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            r9.NotifyBTStatus(r0)
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            byte r12 = (byte) r0
            r9.sendBTState(r12)
            int r9 = r1.mBtStatus
            if (r9 != r0) goto L_0x00eb
            return
        L_0x00eb:
            if (r9 != r10) goto L_0x00f6
            if (r0 != r8) goto L_0x00f6
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r12 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_BT
            r9.sendKSW_0x00_0x67(r12, r13)
        L_0x00f6:
            r1.mBtStatus = r0
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            if (r9 == 0) goto L_0x01fa
            int r9 = r9.m_iUITypeVer
            r12 = 48
            if (r9 == r12) goto L_0x0112
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            int r9 = r9.m_iUITypeVer
            r12 = 101(0x65, float:1.42E-43)
            if (r9 == r12) goto L_0x0112
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            int r9 = r9.m_iUITypeVer
            r12 = 102(0x66, float:1.43E-43)
            if (r9 != r12) goto L_0x013a
        L_0x0112:
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            int r9 = r9.m_iBTTypeVer
            if (r9 != r6) goto L_0x0122
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            if (r0 != r8) goto L_0x011e
            r12 = 1
            goto L_0x011f
        L_0x011e:
            r12 = 0
        L_0x011f:
            r9.switchMicToBTPhone(r12)
        L_0x0122:
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r12 = "com.incarmedia.record"
            r9.<init>(r12)
            if (r0 <= r14) goto L_0x012d
            r12 = 0
            goto L_0x012e
        L_0x012d:
            r12 = 1
        L_0x012e:
            java.lang.String r6 = "isrecord"
            r9.putExtra(r6, r12)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            android.os.UserHandle r12 = android.os.UserHandle.ALL
            r6.sendBroadcastAsUser(r9, r12)
        L_0x013a:
            r6 = 4
            if (r0 == r6) goto L_0x01a4
            r6 = 5
            if (r0 == r6) goto L_0x01a4
            if (r0 == r8) goto L_0x0162
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r6 = 41
            if (r0 != r6) goto L_0x015b
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTNormalMode()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTNormalMode_KeyDisable_KSW()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTSpeakMode_MCU_KSW(r13)
            goto L_0x01fa
        L_0x015b:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTNormalMode()
            goto L_0x01fa
        L_0x0162:
            java.lang.String r0 = "onReceive: HBCP_STATUS_HSHF_ACTIVE_CALL"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r6 = 39
            if (r0 != r6) goto L_0x0176
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTSpeakMode()
            goto L_0x01fa
        L_0x0176:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r6 = 41
            if (r0 != r6) goto L_0x018e
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTSpeakMode()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTSpeakMode_KeyDisable_KSW()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTSpeakMode_MCU_KSW(r11)
            goto L_0x01fa
        L_0x018e:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r6 = 101(0x65, float:1.42E-43)
            if (r0 == r6) goto L_0x019e
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r6 = 48
            if (r0 != r6) goto L_0x01fa
        L_0x019e:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTSpeakMode()
            goto L_0x01fa
        L_0x01a4:
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            int r6 = r6.m_iUITypeVer
            r9 = 41
            if (r6 != r9) goto L_0x01c8
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            if (r6 == 0) goto L_0x01ed
            r6.BTSpeakMode_KeyDisable_KSW()
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            r6.BTSpeakMode()
            r6 = 4
            if (r0 != r6) goto L_0x01c1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTSpeakMode_MCU_KSW(r14)
            goto L_0x01ed
        L_0x01c1:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 2
            r0.BTSpeakMode_MCU_KSW(r6)
            goto L_0x01ed
        L_0x01c8:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.GetCurrDim()
            if (r0 != r10) goto L_0x01e8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SendBlackState(r13)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.LoadNLightVal()
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            r9 = 20
            r6.putSettingInt(r5, r9)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            byte r0 = (byte) r0
            r6.SendBLVal(r9, r0)
        L_0x01e8:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.BTSpeakMode()
        L_0x01ed:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.CarPlaySocketServer r0 = r0.mCarPlaySocketServer
            if (r0 == 0) goto L_0x01fa
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.CarPlaySocketServer r0 = r0.mCarPlaySocketServer
            r0.removeView()
        L_0x01fa:
            java.lang.String r0 = "android.intent.action.CLOSE_SYSTEM_DIALOGS"
            boolean r0 = r4.equals(r0)
            java.lang.String r6 = "com.szchoiceway.settings"
            java.lang.String r9 = "status"
            java.lang.String r12 = "data"
            if (r0 == 0) goto L_0x0265
            java.lang.String r0 = "reason"
            java.lang.String r0 = r3.getStringExtra(r0)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "reason: "
            r5.append(r10)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            java.lang.String r10 = "HomeReceiver EvtModel"
            android.util.Log.i(r10, r5)
            java.lang.String r5 = "homekey"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x023b
            java.lang.String r0 = "homekey"
            android.util.Log.i(r10, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.HideTopFloat(r11)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bModeHome = r11
            goto L_0x028c
        L_0x023b:
            java.lang.String r5 = "recentapps"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0249
            java.lang.String r0 = "long press home key or activity switch"
            android.util.Log.i(r10, r0)
            goto L_0x028c
        L_0x0249:
            java.lang.String r5 = "lock"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0257
            java.lang.String r0 = "lock"
            android.util.Log.i(r10, r0)
            goto L_0x028c
        L_0x0257:
            java.lang.String r5 = "assist"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x028c
            java.lang.String r0 = "assist"
            android.util.Log.i(r10, r0)
            goto L_0x028c
        L_0x0265:
            java.lang.String r0 = "com.szchoiceway.eventcenter.EventUtils.ACTION_CHANGE_MODE_EVENT"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0292
            java.lang.String r0 = "EventUtils.ACTION_MODE_DATA"
            int r0 = r3.getIntExtra(r0, r13)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "onReceive: mode =  "
            r5.append(r10)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.postRunModeActivity(r0)
        L_0x028c:
            r18 = r6
            r5 = r9
            r6 = r12
            goto L_0x1ee8
        L_0x0292:
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ARRAY_BYTE_DATA"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x02b9
            java.lang.String r0 = "ZXW Array byte data Evt"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x028c
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZXW_ARRAY_BYTE_EXTRA_DATA_EVT"
            byte[] r0 = r3.getByteArrayExtra(r0)
            int r5 = r0.length
            r10 = 2
            if (r5 != r10) goto L_0x02b3
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.SendDataUpgradeMode(r0)
            goto L_0x028c
        L_0x02b3:
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.SendDataNoFrameHead(r0)
            goto L_0x028c
        L_0x02b9:
            java.lang.String r0 = "com.android.quicksetting.BROADCAST"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x04ec
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "msg: "
            r0.append(r5)
            java.lang.String r5 = "msg"
            java.lang.String r5 = r3.getStringExtra(r5)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r15, r0)
            java.lang.String r0 = "msg"
            java.lang.String r0 = r3.getStringExtra(r0)
            java.lang.String r5 = "backlight"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x0392
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x02f7
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.backLightOff_KSW()
            goto L_0x028c
        L_0x02f7:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.LoadNLightVal()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "OS backlight Evt iNLightVal = "
            r5.append(r10)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            int r5 = r5.GetCurrDim()
            r1.m_iCurrDim = r5
            int r5 = r5 + r11
            r1.m_iCurrDim = r5
            if (r5 <= r14) goto L_0x0320
            r1.m_iCurrDim = r13
        L_0x0320:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = "OS backlight Evt m_iCurrDim = "
            r5.append(r10)
            int r10 = r1.m_iCurrDim
            r5.append(r10)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            int r5 = r1.m_iCurrDim
            if (r5 != 0) goto L_0x0346
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.SendBlackState(r13)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            byte r0 = (byte) r0
            r5.SendBLVal(r13, r0)
            goto L_0x0375
        L_0x0346:
            if (r5 != r11) goto L_0x0355
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.SendBlackState(r13)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            byte r0 = (byte) r0
            r10 = 7
            r5.SendBLVal(r10, r0)
            goto L_0x0375
        L_0x0355:
            r10 = 2
            if (r5 != r10) goto L_0x0366
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.SendBlackState(r13)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r10 = 12
            byte r0 = (byte) r0
            r5.SendBLVal(r10, r0)
            goto L_0x0375
        L_0x0366:
            if (r5 != r14) goto L_0x0375
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.SendBlackState(r13)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r10 = 20
            byte r0 = (byte) r0
            r5.SendBLVal(r10, r0)
        L_0x0375:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r5 = r1.m_iCurrDim
            r0.SetCurrDim(r5)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendToOSData()
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r5 = "com.szchoiceway.settings.bl"
            r0.setAction(r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.sendBroadcast(r0)
            goto L_0x028c
        L_0x0392:
            java.lang.String r5 = "car_sound_kai"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x039c
            goto L_0x028c
        L_0x039c:
            java.lang.String r5 = "car_sound_guan"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x03a6
            goto L_0x028c
        L_0x03a6:
            java.lang.String r5 = "navigation"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x0441
            java.lang.String r0 = "OS navigation Evt "
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x028c
            int r0 = r0.m_iUITypeVer
            r5 = 44
            if (r0 != r5) goto L_0x03ea
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r5 = "android.intent.category.LAUNCHER"
            r0.addCategory(r5)
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            java.lang.String r10 = "GotoPageNum"
            r5.putInt(r10, r13)
            r0.putExtras(r5)
            android.content.ComponentName r5 = new android.content.ComponentName
            java.lang.String r10 = "com.szchoiceway.settings.MainActivity"
            r5.<init>(r6, r10)
            r0.setComponent(r5)
            r5 = 270532608(0x10200000, float:3.1554436E-29)
            r0.setFlags(r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.startActivity(r0)
            goto L_0x028c
        L_0x03ea:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x03fd
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            java.lang.String r5 = "com.szchoiceway.navigation"
            java.lang.String r10 = "com.szchoiceway.navigation.MainActivity"
            com.szchoiceway.eventcenter.EventUtils.startActivityIfNotRuning(r0, r5, r10)
            goto L_0x028c
        L_0x03fd:
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r5 = "android.intent.category.LAUNCHER"
            r0.addCategory(r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            int r5 = r5.m_iUITypeVer
            r10 = 38
            if (r5 == r10) goto L_0x042b
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            int r5 = r5.m_iUITypeVer
            r10 = 40
            if (r5 == r10) goto L_0x042b
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            int r5 = r5.m_iUITypeVer
            r10 = 37
            if (r5 != r10) goto L_0x0420
            goto L_0x042b
        L_0x0420:
            android.content.ComponentName r5 = new android.content.ComponentName
            java.lang.String r10 = "com.szchoiceway.settings.NaviSetActivity"
            r5.<init>(r6, r10)
            r0.setComponent(r5)
            goto L_0x0435
        L_0x042b:
            android.content.ComponentName r5 = new android.content.ComponentName
            java.lang.String r10 = "com.szchoiceway.settings.MainActivity"
            r5.<init>(r6, r10)
            r0.setComponent(r5)
        L_0x0435:
            r5 = 270532608(0x10200000, float:3.1554436E-29)
            r0.setFlags(r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.startActivity(r0)
            goto L_0x028c
        L_0x0441:
            java.lang.String r5 = "volume"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x04c7
            java.lang.String r0 = "OS volume Evt "
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 37
            if (r0 != r5) goto L_0x045a
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            goto L_0x028c
        L_0x045a:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x0490
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r5 = "android.intent.category.LAUNCHER"
            r0.addCategory(r5)
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            java.lang.String r10 = "GotoPageNum"
            r8 = 4
            r5.putInt(r10, r8)
            r0.putExtras(r5)
            android.content.ComponentName r5 = new android.content.ComponentName
            java.lang.String r8 = "com.szchoiceway.settings.MainActivity"
            r5.<init>(r6, r8)
            r0.setComponent(r5)
            r5 = 270532608(0x10200000, float:3.1554436E-29)
            r0.setFlags(r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.startActivity(r0)
            goto L_0x028c
        L_0x0490:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 101(0x65, float:1.42E-43)
            if (r0 == r5) goto L_0x04ae
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 48
            if (r0 != r5) goto L_0x04a1
            goto L_0x04ae
        L_0x04a1:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x028c
            r5 = 18
            java.lang.String r8 = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_KEY"
            com.szchoiceway.eventcenter.EventUtils.sendSysBroadcast(r0, r8, r5)
            goto L_0x028c
        L_0x04ae:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = -24
            java.lang.String r8 = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_KEY"
            com.szchoiceway.eventcenter.EventUtils.sendSysBroadcast(r0, r8, r5)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.content.Intent r5 = new android.content.Intent
            java.lang.String r8 = "com.choiceway.action.dsp_vol"
            r5.<init>(r8)
            android.os.UserHandle r8 = android.os.UserHandle.ALL
            r0.sendBroadcastAsUser(r5, r8)
            goto L_0x028c
        L_0x04c7:
            java.lang.String r5 = "close_screen"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x028c
            java.lang.String r0 = "--->>> KSW close_screen"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x028c
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x04e5
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.backLightOff_KSW()
            goto L_0x028c
        L_0x04e5:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ProcessCanKey(r11)
            goto L_0x028c
        L_0x04ec:
            r0 = 7
            java.lang.String r8 = "android.systemui.toapp.data"
            boolean r8 = r4.equals(r8)
            if (r8 == 0) goto L_0x085b
            java.lang.String r0 = "jingyin"
            int r0 = r3.getIntExtra(r0, r13)
            java.lang.String r8 = "liangdu"
            int r8 = r3.getIntExtra(r8, r13)
            java.lang.String r14 = "yinliangjia"
            int r14 = r3.getIntExtra(r14, r13)
            java.lang.String r11 = "yinliangjian"
            int r11 = r3.getIntExtra(r11, r13)
            java.lang.String r13 = "yinliangzhi"
            int r13 = r3.getIntExtra(r13, r10)
            java.lang.String r10 = "guanji"
            r18 = r6
            r6 = 0
            int r10 = r3.getIntExtra(r10, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r19 = r12
            java.lang.String r12 = "onReceive: RECV_SYSTEM_DATA_EVT-----iMtue="
            r6.append(r12)
            r6.append(r0)
            java.lang.String r12 = "--iDim="
            r6.append(r12)
            r6.append(r8)
            java.lang.String r12 = "--iVolAdd="
            r6.append(r12)
            r6.append(r14)
            java.lang.String r12 = "--iVolSub="
            r6.append(r12)
            r6.append(r11)
            java.lang.String r12 = "--iMainVol="
            r6.append(r12)
            r6.append(r13)
            java.lang.String r12 = "--iPower="
            r6.append(r12)
            r6.append(r10)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r15, r6)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            if (r6 != 0) goto L_0x055f
            return
        L_0x055f:
            if (r0 != 0) goto L_0x05cb
            if (r8 != 0) goto L_0x05cb
            if (r14 != 0) goto L_0x05cb
            if (r11 != 0) goto L_0x05cb
            r6 = -1
            if (r13 != r6) goto L_0x05cb
            if (r10 != 0) goto L_0x05cb
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r2 = "com.szchoiceway.SEND_APP_ACTION_EVT"
            r0.<init>(r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            boolean r2 = r2.IsMuteOn()
            java.lang.String r3 = "VoiceKeyMUTE"
            r0.putExtra(r3, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            byte r2 = r2.getMainVolval()
            java.lang.String r3 = "VoiceKeyMAINVOL"
            r0.putExtra(r3, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            int r2 = r2.GetCurrDim()
            r3 = 1
            int r2 = r2 + r3
            if (r2 != r3) goto L_0x059f
            java.lang.String r2 = "VoiceKeyDIM"
            r4 = 2
            r0.putExtra(r2, r4)
            java.lang.String r2 = "onReceive: mContext.GetCurrDim() + 1 == 1"
            android.util.Log.i(r15, r2)
            goto L_0x05c5
        L_0x059f:
            r4 = 2
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            int r2 = r2.GetCurrDim()
            int r2 = r2 + r3
            if (r2 != r4) goto L_0x05b4
            java.lang.String r2 = "VoiceKeyDIM"
            r0.putExtra(r2, r3)
            java.lang.String r2 = "onReceive: mContext.GetCurrDim() + 1 == 2"
            android.util.Log.i(r15, r2)
            goto L_0x05c5
        L_0x05b4:
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            int r2 = r2.GetCurrDim()
            int r2 = r2 + r3
            java.lang.String r3 = "VoiceKeyDIM"
            r0.putExtra(r3, r2)
            java.lang.String r2 = "onReceive: mContext.GetCurrDim()  else"
            android.util.Log.i(r15, r2)
        L_0x05c5:
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.sendBroadcast(r0)
            return
        L_0x05cb:
            java.lang.String r6 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_00"
            r12 = 1
            if (r0 != r12) goto L_0x06a7
            java.lang.String r0 = "onReceive: iMtue == 1"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iModeSet
            r12 = 14
            if (r0 != r12) goto L_0x063c
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.media.AudioManager r0 = r0.mAudioMgr
            r12 = 3
            int r0 = r0.getStreamVolume(r12)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r20 = r9
            java.lang.String r9 = "onReceive: streamVolume = "
            r12.append(r9)
            r12.append(r0)
            java.lang.String r9 = r12.toString()
            android.util.Log.i(r15, r9)
            if (r0 != 0) goto L_0x060c
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.media.AudioManager r0 = r0.mAudioMgr
            r9 = 100
            r21 = r7
            r7 = 1
            r12 = 3
            r0.adjustStreamVolume(r12, r9, r7)
            goto L_0x0619
        L_0x060c:
            r21 = r7
            r7 = 1
            r12 = 3
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.media.AudioManager r0 = r0.mAudioMgr
            r9 = -100
            r0.adjustStreamVolume(r12, r9, r7)
        L_0x0619:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.media.AudioManager r0 = r0.mAudioMgr
            int r0 = r0.getStreamVolume(r12)
            if (r0 != 0) goto L_0x0630
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r9 = 0
            r0.SendVol_KSW(r7, r7, r7, r9)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendMuteState(r7)
            goto L_0x06ab
        L_0x0630:
            r9 = 0
            com.szchoiceway.eventcenter.EventService r12 = r1.mContext
            r12.SendVol_KSW(r9, r7, r7, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendMuteState(r9)
            goto L_0x06ab
        L_0x063c:
            r21 = r7
            r20 = r9
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.getMuteStatus()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "onReceive: mtue = "
            r7.append(r9)
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            android.util.Log.i(r15, r7)
            if (r0 == 0) goto L_0x0671
            com.szchoiceway.eventcenter.EventService r7 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r7 = r7.mSysProviderOpt
            if (r7 == 0) goto L_0x066e
            com.szchoiceway.eventcenter.EventService r7 = r1.mContext
            r9 = 0
            int r12 = r7.getSettingInt(r6, r9)
            r2 = 1
            r7.SendVol_KSW(r9, r2, r2, r12)
            goto L_0x0678
        L_0x066e:
            r2 = 1
            r9 = 0
            goto L_0x0678
        L_0x0671:
            r2 = 1
            r9 = 0
            com.szchoiceway.eventcenter.EventService r7 = r1.mContext
            r7.SendVol_KSW(r2, r2, r2, r9)
        L_0x0678:
            com.szchoiceway.eventcenter.EventService r7 = r1.mContext
            r0 = r0 ^ r2
            r7.sendMuteState(r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.getSettingInt(r6, r9)
            if (r0 != 0) goto L_0x068b
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendMuteState(r2)
        L_0x068b:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 12
            r0.sendSystemKey(r2)
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r2 = "com.szchoiceway.settings.vol"
            r0.setAction(r2)
            java.lang.String r2 = "vol"
            r0.putExtra(r2, r13)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.sendBroadcast(r0)
            goto L_0x06ab
        L_0x06a7:
            r21 = r7
            r20 = r9
        L_0x06ab:
            r2 = 1
            if (r14 != r2) goto L_0x06e3
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r7 = 18
            java.lang.String r9 = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_KEY"
            com.szchoiceway.eventcenter.EventUtils.sendSysBroadcast(r0, r9, r7)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            if (r0 == 0) goto L_0x06e3
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r7 = 0
            int r0 = r0.getSettingInt(r6, r7)
            int r0 = r0 + r2
            r2 = 40
            if (r0 <= r2) goto L_0x06ce
            r0 = 40
            r13 = 40
            goto L_0x06cf
        L_0x06ce:
            r13 = r0
        L_0x06cf:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "onReceive: 音量++"
            r0.append(r2)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r15, r0)
        L_0x06e3:
            r2 = 1
            if (r11 != r2) goto L_0x071f
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 19
            java.lang.String r7 = "com.choiceway.eventcenter.EventUtils.ZXW_SYS_KEY"
            com.szchoiceway.eventcenter.EventUtils.sendSysBroadcast(r0, r7, r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            if (r0 == 0) goto L_0x071f
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 0
            int r0 = r0.getSettingInt(r6, r2)
            if (r0 != 0) goto L_0x06ff
            return
        L_0x06ff:
            r2 = -1
            int r0 = r0 + r2
            if (r0 >= 0) goto L_0x070a
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 1
            r0.sendMuteState(r2)
            r0 = 0
        L_0x070a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "onReceive: 音量--"
            r2.append(r7)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r15, r2)
            r13 = r0
        L_0x071f:
            r0 = -1
            if (r13 == r0) goto L_0x0798
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.putSettingInt(r6, r13)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.appySetting()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.commitSetting()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.getMuteStatus()
            if (r0 == 0) goto L_0x0743
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 0
            r0.sendMuteState(r2)
        L_0x0743:
            if (r13 != 0) goto L_0x0753
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.getMuteStatus()
            if (r0 != 0) goto L_0x0753
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 1
            r0.sendMuteState(r2)
        L_0x0753:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            byte r2 = (byte) r13
            r0.setSysMainVol(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 5
            r0.sendSetup(r6, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "onReceive: 下拉的音量设置"
            r0.append(r6)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 1
            r7 = 0
            r0.SendVol_KSW(r7, r6, r6, r2)
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r2 = "com.szchoiceway.settings.vol"
            r0.setAction(r2)
            java.lang.String r2 = "vol"
            r0.putExtra(r2, r13)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.sendBroadcast(r0)
            if (r14 == r6) goto L_0x0792
            if (r11 != r6) goto L_0x0799
        L_0x0792:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendToOSData()
            goto L_0x0799
        L_0x0798:
            r6 = 1
        L_0x0799:
            if (r10 != r6) goto L_0x07b4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r2 = 41
            if (r0 != r2) goto L_0x07a9
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.backLightOff_KSW()
            goto L_0x07ae
        L_0x07a9:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ProcessCanKey(r6)
        L_0x07ae:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendToOSData()
            goto L_0x07cb
        L_0x07b4:
            r2 = 2
            if (r10 != r2) goto L_0x07cb
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r2 = 41
            if (r0 != r2) goto L_0x07c0
            goto L_0x07c6
        L_0x07c0:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = -5
            r0.ProcessCanKey(r2)
        L_0x07c6:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendToOSData()
        L_0x07cb:
            r2 = 1
            if (r8 != r2) goto L_0x08a2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.GetCurrDim()
            r1.m_iCurrDim = r0
            int r0 = r0 + r2
            r1.m_iCurrDim = r0
            r2 = 3
            if (r0 <= r2) goto L_0x07e0
            r2 = 0
            r1.m_iCurrDim = r2
            goto L_0x07e1
        L_0x07e0:
            r2 = 0
        L_0x07e1:
            int r0 = r1.m_iCurrDim
            if (r0 != 0) goto L_0x07f5
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SendBlackState(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SendBLVal(r2, r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.putSettingInt(r5, r2)
            goto L_0x0835
        L_0x07f5:
            r6 = 1
            if (r0 != r6) goto L_0x080a
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SendBlackState(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 33
            r0.SendBLVal(r6, r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.putSettingInt(r5, r6)
            goto L_0x0835
        L_0x080a:
            r6 = 2
            if (r0 != r6) goto L_0x081f
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SendBlackState(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 66
            r0.SendBLVal(r6, r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.putSettingInt(r5, r6)
            goto L_0x0835
        L_0x081f:
            r6 = 3
            if (r0 != r6) goto L_0x0835
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SendBlackState(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 100
            r0.SendBLVal(r6, r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 100
            r0.putSettingInt(r5, r2)
        L_0x0835:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.appySetting()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.commitSetting()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r2 = r1.m_iCurrDim
            r0.SetCurrDim(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendToOSData()
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r2 = "com.szchoiceway.settings.bl"
            r0.setAction(r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.sendBroadcast(r0)
            goto L_0x08a2
        L_0x085b:
            r18 = r6
            r21 = r7
            r20 = r9
            r19 = r12
            java.lang.String r2 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x08ac
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA"
            r2 = 0
            int r0 = r3.getIntExtra(r0, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "iCanKey = "
            r2.append(r5)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r15, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            if (r2 == 0) goto L_0x08a2
            r5 = 6
            if (r0 != r5) goto L_0x089f
            int r2 = r2.m_iUITypeVer
            r3 = 102(0x66, float:1.43E-43)
            if (r2 != r3) goto L_0x0899
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.ProcessCanKey(r0)
            goto L_0x089e
        L_0x0899:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.delaySendPausePlayKey()
        L_0x089e:
            return
        L_0x089f:
            r2.ProcessCanKey(r0)
        L_0x08a2:
            r2 = r27
        L_0x08a4:
            r6 = r19
            r5 = r20
            r7 = r21
            goto L_0x1ee8
        L_0x08ac:
            java.lang.String r2 = "com.choiceway.FatUtils.ZXW_TOUCH_LEARN_ID"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x08f4
            java.lang.String r0 = "com.choiceway.FatUtils.ZXW_TOUCH_LEARN_ID_EXTRA"
            r2 = -1
            int r0 = r3.getIntExtra(r0, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "iTouchKey = "
            r2.append(r5)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r15, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "Touch Key id = "
            r2.append(r5)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r15, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            if (r2 == 0) goto L_0x08a2
            r5 = -1
            if (r0 != r5) goto L_0x08ef
            r6 = 0
            r2.SetInTouchLearnMode(r6, r5)
            goto L_0x08a2
        L_0x08ef:
            r5 = 1
            r2.SetInTouchLearnMode(r5, r0)
            goto L_0x08a2
        L_0x08f4:
            java.lang.String r2 = "com.choiceway.FatUtils.ZXW_RELOAD_TOUCH_KEY_CFG"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0904
            java.lang.String r0 = "<<<<Reload Touch Key cfg>>>>"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            goto L_0x08a2
        L_0x0904:
            java.lang.String r2 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0938
            java.lang.String r0 = "<<<<ZXW_CAN_WHEEL_TRACK_EVT>>>>"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a2
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_WHEEL_TRACK_EVT_EXTRA"
            r2 = 0
            int r0 = r3.getIntExtra(r0, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "iWheelTrack = "
            r2.append(r5)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r15, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            byte r0 = (byte) r0
            r2.SetWheelTrackData(r0)
            goto L_0x08a2
        L_0x0938:
            java.lang.String r2 = "net.easyconn.bt.checkstatus"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0972
            java.lang.String r0 = "........... BROADCAST_BT_CHECKSTATUS ..........."
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a2
            int r0 = r0.getBTStatus()
            r2 = 3
            if (r0 < r2) goto L_0x0961
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r2 = "net.easyconn.bt.connected"
            r0.setAction(r2)
            r2 = r27
            r2.sendBroadcast(r0)
            goto L_0x08a4
        L_0x0961:
            r2 = r27
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r5 = "net.easyconn.bt.opened"
            r0.setAction(r5)
            r2.sendBroadcast(r0)
            goto L_0x08a4
        L_0x0972:
            r2 = r27
            java.lang.String r5 = "net.easyconn.app.open"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x09a0
            java.lang.String r0 = "........... BROADCAST_EASYCONN_OPEN ..........."
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r5 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_MOBILE_APP
            int r5 = r5.getIntValue()
            com.szchoiceway.eventcenter.ICallbackfn$Stub r6 = r1.mModeCallback
            r0.setCurModeCallback(r5, r6)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 1
            r0.setEasyconnState_KSW(r5)
            goto L_0x08a4
        L_0x09a0:
            java.lang.String r5 = "net.easyconn.app.resume"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x09bf
            java.lang.String r0 = "........... BROADCAST_EASYCONN_RESUME ..........."
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 2
            r0.setEasyconnState_KSW(r5)
            goto L_0x08a4
        L_0x09bf:
            java.lang.String r5 = "net.easyconn.a2dp.acquire"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x09fc
            java.lang.String r0 = "........... BROADCAST_BT_A2DP_ACQUIRE ..........."
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r5 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_BTMUSIC
            r6 = 1
            r0.sendMode(r5, r6)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendPlayState(r6)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 3
            r0.setEasyconnState_KSW(r5)
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r5 = "com.choiceway.eventcenter.ZHTY_PHONELINK_UNMUTE"
            r0.<init>(r5)
            java.lang.String r5 = "com.choiceway.eventcenter.ZHTY_PHONELINK_UNMUTE_DATA"
            r0.putExtra(r5, r6)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.sendBroadcast(r0)
            goto L_0x08a4
        L_0x09fc:
            java.lang.String r5 = "net.easyconn.a2dp.release"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0a2d
            java.lang.String r0 = "........... BROADCAST_BT_A2DP_RELEASE ..........."
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            r5 = 4
            r0.setEasyconnState_KSW(r5)
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r5 = "com.choiceway.eventcenter.ZHTY_PHONELINK_UNMUTE"
            r0.<init>(r5)
            java.lang.String r5 = "com.choiceway.eventcenter.ZHTY_PHONELINK_UNMUTE_DATA"
            r6 = 0
            r0.putExtra(r5, r6)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.sendBroadcast(r0)
            goto L_0x08a4
        L_0x0a2d:
            java.lang.String r5 = "net.easyconn.app.quit"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0a5a
            java.lang.String r0 = "........... BROADCAST_APP_QUIT ..........."
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r5 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_NULL
            r6 = 1
            r0.sendMode(r5, r6)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 0
            r0.sendPlayState(r5)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 5
            r0.setEasyconnState_KSW(r5)
            goto L_0x08a4
        L_0x0a5a:
            java.lang.String r5 = "net.easyconn.app.pause"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0a69
            java.lang.String r0 = "........... BROADCAST_EASYCONN_PAUSE ..........."
            android.util.Log.i(r15, r0)
            goto L_0x08a4
        L_0x0a69:
            java.lang.String r5 = "com.didi365.miudrive.navi.main.onStart"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0a88
            java.lang.String r0 = "onReceive: BROADCAST_MIUDRIVE_ONSTATR"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 1
            r0.setEasyconnState_KSW(r5)
            goto L_0x08a4
        L_0x0a88:
            java.lang.String r5 = "com.didi365.miudrive.navi.main.onResume"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0aa7
            java.lang.String r0 = "onReceive: BROADCAST_MIUDRIVE_ONRESUME"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 2
            r0.setEasyconnState_KSW(r5)
            goto L_0x08a4
        L_0x0aa7:
            java.lang.String r5 = "com.didi365.miudrive.navi.main.onPause"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0ab6
            java.lang.String r0 = "onReceive: BROADCAST_MIUDRIVE_ONPAUSE"
            android.util.Log.i(r15, r0)
            goto L_0x08a4
        L_0x0ab6:
            java.lang.String r5 = "com.didi365.miudrive.navi.main.onStop"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0add
            java.lang.String r0 = "onReceive: BROADCAST_MIUDRIVE_ONSTOP"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r5 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_NULL
            r6 = 1
            r0.sendMode(r5, r6)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 5
            r0.setEasyconnState_KSW(r5)
            goto L_0x08a4
        L_0x0add:
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZXW_SOUND_CONTROL_BACK_EVT"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0aec
            java.lang.String r0 = "ZXW_SOUND_CONTROL_BACK_EVT *************"
            android.util.Log.i(r15, r0)
            goto L_0x08a4
        L_0x0aec:
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_PREV"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0b18
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "iCanKey PREV= "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x08a4
            r5.ProcessCanKey(r0)
            goto L_0x08a4
        L_0x0b18:
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_PLAY_PAUSE"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0b44
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "iCanKey PLAY_PAUSE= "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x08a4
            r5.ProcessCanKey(r0)
            goto L_0x08a4
        L_0x0b44:
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_NEXT"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0b70
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "iCanKey NEXT= "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x08a4
            r5.ProcessCanKey(r0)
            goto L_0x08a4
        L_0x0b70:
            java.lang.String r5 = "com.szchoiceway.eventcenter.EventUtils.ACTION_MCU_CMD_EVENT"
            boolean r5 = r4.equals(r5)
            java.lang.String r6 = ""
            if (r5 == 0) goto L_0x0bc0
            java.lang.String r0 = "EventUtils.MCU_CMD_DATA"
            byte[] r0 = r3.getByteArrayExtra(r0)
            java.lang.String r5 = "EventUtils.VW_KEY_DATA"
            r7 = 0
            r3.getIntExtra(r5, r7)
            r5 = 0
        L_0x0b87:
            int r7 = r0.length
            if (r5 >= r7) goto L_0x0ba3
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r6)
            java.lang.String r6 = " "
            r7.append(r6)
            byte r6 = r0[r5]
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            int r5 = r5 + 1
            goto L_0x0b87
        L_0x0ba3:
            int r5 = r0.length
            r6 = 2
            if (r5 >= r6) goto L_0x0ba8
            return
        L_0x0ba8:
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x08a4
            r6 = 1
            byte r7 = r0[r6]
            if (r7 != r6) goto L_0x0bb7
            r7 = 0
            r5.sendPlayState(r7)
            goto L_0x08a4
        L_0x0bb7:
            byte r0 = r0[r6]
            if (r0 != 0) goto L_0x08a4
            r5.sendPlayState(r6)
            goto L_0x08a4
        L_0x0bc0:
            java.lang.String r5 = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0d82
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            java.lang.String r0 = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_EXTRA"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "onReceive ZXW_SENDBROADCAST8902MOD iExtra = "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r15, r5)
            switch(r0) {
                case 1: goto L_0x0d7a;
                case 2: goto L_0x0d7a;
                case 3: goto L_0x0d7a;
                case 4: goto L_0x0d7a;
                case 5: goto L_0x0d7a;
                case 6: goto L_0x0d7a;
                case 7: goto L_0x0d73;
                case 8: goto L_0x0d6c;
                case 9: goto L_0x0d65;
                case 10: goto L_0x0d5e;
                case 11: goto L_0x0d57;
                case 12: goto L_0x0d50;
                case 13: goto L_0x0d49;
                case 14: goto L_0x0d42;
                case 15: goto L_0x0d3b;
                case 16: goto L_0x0d34;
                case 17: goto L_0x0d2d;
                case 18: goto L_0x0d26;
                case 19: goto L_0x0d1f;
                case 20: goto L_0x0d18;
                case 21: goto L_0x0d11;
                case 22: goto L_0x0d0a;
                case 23: goto L_0x0d03;
                case 24: goto L_0x0cfc;
                case 25: goto L_0x0cf5;
                case 26: goto L_0x0cee;
                case 27: goto L_0x0ce7;
                case 28: goto L_0x0ce0;
                case 29: goto L_0x0cd9;
                case 30: goto L_0x0cd2;
                case 31: goto L_0x0ccb;
                case 32: goto L_0x0bea;
                case 33: goto L_0x0cc4;
                case 34: goto L_0x0cbd;
                case 35: goto L_0x0cb6;
                case 36: goto L_0x0caf;
                case 37: goto L_0x0ca8;
                case 38: goto L_0x0ca1;
                case 39: goto L_0x0bea;
                case 40: goto L_0x0c9a;
                case 41: goto L_0x0c93;
                case 42: goto L_0x0c8c;
                case 43: goto L_0x0c85;
                case 44: goto L_0x0c7a;
                case 45: goto L_0x0c70;
                case 46: goto L_0x0c68;
                case 47: goto L_0x0bea;
                case 48: goto L_0x0c54;
                case 49: goto L_0x0c4d;
                case 50: goto L_0x0c46;
                case 51: goto L_0x0c3f;
                case 52: goto L_0x0c38;
                case 53: goto L_0x0c31;
                case 54: goto L_0x0c2a;
                case 55: goto L_0x0c23;
                case 56: goto L_0x0c1c;
                case 57: goto L_0x0c15;
                case 58: goto L_0x0bea;
                case 59: goto L_0x0bea;
                case 60: goto L_0x0bea;
                case 61: goto L_0x0bea;
                case 62: goto L_0x0bea;
                case 63: goto L_0x0bea;
                case 64: goto L_0x0c0e;
                case 65: goto L_0x0c07;
                case 66: goto L_0x0bfc;
                case 67: goto L_0x0bf5;
                case 68: goto L_0x0bec;
                default: goto L_0x0bea;
            }
        L_0x0bea:
            goto L_0x08a4
        L_0x0bec:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 44
            r0.send0x70FactorySettings(r5)
            goto L_0x08a4
        L_0x0bf5:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.kswSendSpecialSetLR()
            goto L_0x08a4
        L_0x0bfc:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r5 = 3015(0xbc7, float:4.225E-42)
            r0.sendEmptyMessage(r5)
            goto L_0x08a4
        L_0x0c07:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x41()
            goto L_0x08a4
        L_0x0c0e:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x40()
            goto L_0x08a4
        L_0x0c15:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x39()
            goto L_0x08a4
        L_0x0c1c:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x38()
            goto L_0x08a4
        L_0x0c23:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x37()
            goto L_0x08a4
        L_0x0c2a:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x36()
            goto L_0x08a4
        L_0x0c31:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x35()
            goto L_0x08a4
        L_0x0c38:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x34()
            goto L_0x08a4
        L_0x0c3f:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x33()
            goto L_0x08a4
        L_0x0c46:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x32()
            goto L_0x08a4
        L_0x0c4d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x31()
            goto L_0x08a4
        L_0x0c54:
            java.lang.String r0 = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_DATA0"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            java.lang.String r6 = "com.szchoiceway.eventcenter.EventUtils.ZXW_SENDBROADCAST8902MOD_DATA1"
            int r6 = r3.getIntExtra(r6, r5)
            com.szchoiceway.eventcenter.EventService r7 = r1.mContext
            r7.sendKSW_0x00_0x70(r0, r6)
            goto L_0x08a4
        L_0x0c68:
            r5 = 0
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x2D()
            goto L_0x08a4
        L_0x0c70:
            r5 = 0
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 21
            r0.sendKSW_0x00_0x69(r6, r5, r5)
            goto L_0x08a4
        L_0x0c7a:
            r5 = 0
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 21
            r7 = 1
            r0.sendKSW_0x00_0x69(r6, r7, r5)
            goto L_0x08a4
        L_0x0c85:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x2B()
            goto L_0x08a4
        L_0x0c8c:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x2A()
            goto L_0x08a4
        L_0x0c93:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x29()
            goto L_0x08a4
        L_0x0c9a:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x28()
            goto L_0x08a4
        L_0x0ca1:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x26()
            goto L_0x08a4
        L_0x0ca8:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x25()
            goto L_0x08a4
        L_0x0caf:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x24()
            goto L_0x08a4
        L_0x0cb6:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x23()
            goto L_0x08a4
        L_0x0cbd:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x22()
            goto L_0x08a4
        L_0x0cc4:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x21()
            goto L_0x08a4
        L_0x0ccb:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x1f()
            goto L_0x08a4
        L_0x0cd2:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x1e()
            goto L_0x08a4
        L_0x0cd9:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x1d()
            goto L_0x08a4
        L_0x0ce0:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x1c()
            goto L_0x08a4
        L_0x0ce7:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x1b()
            goto L_0x08a4
        L_0x0cee:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x1a()
            goto L_0x08a4
        L_0x0cf5:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x19()
            goto L_0x08a4
        L_0x0cfc:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x18()
            goto L_0x08a4
        L_0x0d03:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x17()
            goto L_0x08a4
        L_0x0d0a:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x16()
            goto L_0x08a4
        L_0x0d11:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x15()
            goto L_0x08a4
        L_0x0d18:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x14()
            goto L_0x08a4
        L_0x0d1f:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x13()
            goto L_0x08a4
        L_0x0d26:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x12()
            goto L_0x08a4
        L_0x0d2d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x11()
            goto L_0x08a4
        L_0x0d34:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x10()
            goto L_0x08a4
        L_0x0d3b:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x0f()
            goto L_0x08a4
        L_0x0d42:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x0e()
            goto L_0x08a4
        L_0x0d49:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x0d()
            goto L_0x08a4
        L_0x0d50:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x0c()
            goto L_0x08a4
        L_0x0d57:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x0b()
            goto L_0x08a4
        L_0x0d5e:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x0a()
            goto L_0x08a4
        L_0x0d65:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x09()
            goto L_0x08a4
        L_0x0d6c:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x08()
            goto L_0x08a4
        L_0x0d73:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x07()
            goto L_0x08a4
        L_0x0d7a:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 0
            r0.ksw_Send8902BackcarMod(r5)
            goto L_0x08a4
        L_0x0d82:
            java.lang.String r5 = "com.szchoiceway.eventcenter.EventUtils.ACTION_SWITCH_ORIGINACAR"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0d93
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            r0.sendSwitchOriginaCar()
            goto L_0x08a4
        L_0x0d93:
            java.lang.String r5 = "com.szchoiceway.ACTION_SWITCH_USBDVRMODE"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0dbc
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x0db3
            java.lang.String r0 = "com.szchoiceway.ACTION_SWITCH_USBDVRMODE_EXTRA"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x08a4
            r5.SendSwitchModeExtra_KSW(r0)
            goto L_0x08a4
        L_0x0db3:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            r0.SendSwitchUsbDvrMode()
            goto L_0x08a4
        L_0x0dbc:
            java.lang.String r5 = "android.intent.action.TIME_TICK"
            boolean r5 = r4.equals(r5)
            if (r5 != 0) goto L_0x1ed6
            java.lang.String r5 = "android.intent.action.TIME_SET"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0dce
            goto L_0x1ed6
        L_0x0dce:
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZXW_EVENT_MCUUPGRADE_DATA_EVT"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0de9
            java.lang.String r0 = "--->>> EventUtils.ZXW_EVENT_MCUUPGRADE_DATA_EVT"
            android.util.Log.i(r15, r0)
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZXW_EVENT_MCUUPGRADE_DATA"
            byte[] r0 = r3.getByteArrayExtra(r0)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            int r6 = r0.length
            r5.Send8902McuUpgradeData(r0, r6)
            goto L_0x08a4
        L_0x0de9:
            java.lang.String r5 = "com.szchoiceway.musicplayer.ZXW_SET_VALIDE_MODE_INFO"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0e17
            com.szchoiceway.eventcenter.EventService r7 = r1.mContext
            java.lang.String r0 = "stValidInfo.iPlayStatus"
            r5 = 0
            int r8 = r3.getIntExtra(r0, r5)
            java.lang.String r0 = "stValidInfo.iCurTime"
            int r9 = r3.getIntExtra(r0, r5)
            java.lang.String r0 = "stValidInfo.iTotTime"
            int r10 = r3.getIntExtra(r0, r5)
            java.lang.String r0 = "stValidInfo.iCurTrack"
            int r11 = r3.getIntExtra(r0, r5)
            java.lang.String r0 = "stValidInfo.iTotTrack"
            int r12 = r3.getIntExtra(r0, r5)
            r7.setValideModeInfo(r8, r9, r10, r11, r12)
            goto L_0x08a4
        L_0x0e17:
            java.lang.String r5 = "com.szchoiceway.eventcenter.ZXW_AVM_LEFT_RIGHT_BACK"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0e2d
            java.lang.String r0 = "--->>> EventUtils.ZXW_AVM_LEFT_RIGHT_BACK"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x08a4
            r0.notifyAvmLeftRightBack()
            goto L_0x08a4
        L_0x0e2d:
            java.lang.String r5 = "android.intent.action.LOCALE_CHANGED"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0e9f
            java.lang.String r0 = "--->>> 系统语言改变"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0e41
            r0.notifyLocalLanguageChanged()
        L_0x0e41:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x0e54
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.notifyLocalLanguageChanged_KSW()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_Send8902BackcarMod_0x20()
            goto L_0x0e61
        L_0x0e54:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 101(0x65, float:1.42E-43)
            if (r0 != r5) goto L_0x0e5d
            goto L_0x0e61
        L_0x0e5d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
        L_0x0e61:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            java.util.List<android.content.pm.ResolveInfo> r0 = r0.mRecentTasks
            r0.clear()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.mCurTopClassName = r6
            java.lang.String r0 = "onReceive: 系统语言改变-WHAT_HIDE_FUNCTION_BAR_WND"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendToOSData()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r5 = 10001(0x2711, float:1.4014E-41)
            r7 = 4000(0xfa0, double:1.9763E-320)
            r0.sendEmptyMessageDelayed(r5, r7)
            java.lang.String r0 = "com.estrongs.android.pop"
            com.szchoiceway.eventcenter.EventUtils.killProcess(r0)
            java.lang.String r0 = "com.es.file.explorer.manager"
            com.szchoiceway.eventcenter.EventUtils.killProcess(r0)
            java.lang.String r0 = "com.nng.igo.primong.igoworld"
            com.szchoiceway.eventcenter.EventUtils.killProcess(r0)
            java.lang.String r0 = "com.nng.igoprimoisrael.javaclient"
            com.szchoiceway.eventcenter.EventUtils.killProcess(r0)
            java.lang.String r0 = "com.nng.igo.primong.hun10th"
            com.szchoiceway.eventcenter.EventUtils.killProcess(r0)
            com.szchoiceway.eventcenter.EventUtils.killProcess(r6)
            goto L_0x08a4
        L_0x0e9f:
            java.lang.String r5 = r28.getAction()
            java.lang.String r7 = "com.choiceway.eventcenter.ZHTY_MCU_SWITCH_MEDIA_CHANNAL"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0ebb
            java.lang.String r0 = "com.choiceway.eventcenter.ZHTY_MCU_SWITCH_MEDIA_CHANNAL_DATA"
            r5 = 0
            boolean r0 = r3.getBooleanExtra(r0, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x08a4
            r5.zhtySwitchMediaChannal(r0)
            goto L_0x08a4
        L_0x0ebb:
            java.lang.String r5 = r28.getAction()
            java.lang.String r7 = "com.choiceway.eventcenter.ZHTY_MCU_SWITCH_GPS_CHANNAL"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0ed7
            java.lang.String r0 = "com.choiceway.eventcenter.ZHTY_MCU_SWITCH_GPS_CHANNAL_DATA"
            r5 = 0
            boolean r0 = r3.getBooleanExtra(r0, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x08a4
            r5.zhtySwitchGpsChannal(r0)
            goto L_0x08a4
        L_0x0ed7:
            java.lang.String r5 = r28.getAction()
            java.lang.String r7 = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_MEDIA_BT_GPS_VOL"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0f13
            r0 = 10
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_MEDIA_VOL_DATA"
            int r7 = r3.getIntExtra(r5, r0)
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_BT_VOL_DATA"
            int r8 = r3.getIntExtra(r5, r0)
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_GPS_VOL_DATA"
            int r9 = r3.getIntExtra(r5, r0)
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_MEDIA_VOL_BOOLEAN"
            r5 = 0
            boolean r10 = r3.getBooleanExtra(r0, r5)
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_BT_VOL_BOOLEAN"
            boolean r11 = r3.getBooleanExtra(r0, r5)
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZHTY_SEND_GPS_VOL_BOOLEAN"
            boolean r12 = r3.getBooleanExtra(r0, r5)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            if (r6 == 0) goto L_0x08a4
            r6.zhtySendToMcuMediaBtGpsVol(r7, r8, r9, r10, r11, r12)
            goto L_0x08a4
        L_0x0f13:
            java.lang.String r5 = "android.intent.action.MEDIA_MOUNTED"
            boolean r5 = r4.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x0f32
            java.lang.String r0 = "--->>> Media --- USB --- MOUNTED"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 39
            if (r0 != r5) goto L_0x08a4
            com.szchoiceway.eventcenter.EvtModel$1 r0 = new com.szchoiceway.eventcenter.EvtModel$1
            r0.<init>()
            r0.start()
            goto L_0x08a4
        L_0x0f32:
            java.lang.String r5 = "com.zxw.upgrade"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0f96
            java.lang.String r0 = "--->>> com.zxw.upgrade"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r5 = 41
            if (r0 != r5) goto L_0x0f5d
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0f6f
            r5 = 0
            r0.sendBeatTimer_KSW(r5)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.initArmUpgrade()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 16
            r7 = 3
            r0.sendKSW_0x00_0x69(r6, r7, r5)
            goto L_0x0f6f
        L_0x0f5d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0f6f
            r5 = 1
            r0.SendARMUpgrade(r5)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SendARMUpgrade(r5)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SendARMUpgrade(r5)
        L_0x0f6f:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            if (r0 == 0) goto L_0x0f93
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r5 = "Start_music_for_the_first_time"
            r7 = r21
            r0.updateRecord(r5, r7)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r5 = "Start_video_for_the_first_time"
            r0.updateRecord(r5, r7)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r5 = "Start_radio_for_the_first_time"
            r0.updateRecord(r5, r7)
            goto L_0x0fab
        L_0x0f93:
            r7 = r21
            goto L_0x0fab
        L_0x0f96:
            r7 = r21
            java.lang.String r5 = "com.unisound.intent.action.ACTION_UNISOUND_VOICE_START"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0fb1
            java.lang.String r0 = "ACTION_UNISOUND_VOICE_START"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 1
            r0.ksw_original_endVoice(r5)
        L_0x0fab:
            r6 = r19
            r5 = r20
            goto L_0x1ee8
        L_0x0fb1:
            java.lang.String r5 = "com.unisound.intent.action.ACTION_UNISOUND_VOICE_END"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0fc5
            java.lang.String r0 = "ACTION_UNISOUND_VOICE_END"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r5 = 0
            r0.ksw_original_endVoice(r5)
            goto L_0x0fab
        L_0x0fc5:
            java.lang.String r5 = "com.szchoiceway.ACTION_VOICE_CTRL"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0fd3
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext     // Catch:{ Exception -> 0x0fab }
            r0.startVoiceCtrl(r3)     // Catch:{ Exception -> 0x0fab }
            goto L_0x0fab
        L_0x0fd3:
            java.lang.String r5 = "MSG_EVENT_TEST_TEST"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0fe4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0fab
            r5 = 0
            r0.notifySendDataTest(r5)
            goto L_0x0fab
        L_0x0fe4:
            java.lang.String r5 = "com.android.SoundFocus.Broadcast"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x10e0
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 != 0) goto L_0x0ff1
            return
        L_0x0ff1:
            java.lang.String r0 = "PackageName"
            java.lang.String r0 = r3.getStringExtra(r0)
            java.lang.String r5 = "State"
            java.lang.String r5 = r3.getStringExtra(r5)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "--->>> strPageName = "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r8 = "; --->>> strState = "
            r6.append(r8)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r15, r6)
            java.lang.String r6 = "com.txznet.txz"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x102c
            java.lang.String r6 = "request"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x102c
            r6 = 1
            r1.bTXZSound = r6
        L_0x102c:
            java.lang.String r6 = "com.txznet.txz"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x103f
            java.lang.String r6 = "abandon"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x103f
            r6 = 0
            r1.bTXZSound = r6
        L_0x103f:
            java.lang.String r6 = "com.autonavi.amapauto"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x1052
            java.lang.String r6 = "request"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x1052
            r6 = 1
            r1.bAmapautoSound = r6
        L_0x1052:
            java.lang.String r6 = "com.autonavi.amapauto"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x1069
            java.lang.String r6 = "abandon"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x1069
            r6 = 0
            r1.bAmapautoSound = r6
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            int r6 = r6.m_iUITypeVer
        L_0x1069:
            java.lang.String r6 = "cld.navi.c3525.mainframe"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x107c
            java.lang.String r6 = "request"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x107c
            r6 = 1
            r1.bAmapautoSound = r6
        L_0x107c:
            java.lang.String r6 = "cld.navi.c3525.mainframe"
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x1093
            java.lang.String r6 = "abandon"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x1093
            r6 = 0
            r1.bAmapautoSound = r6
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            int r6 = r6.m_iUITypeVer
        L_0x1093:
            java.lang.String r6 = "com.txznet.music"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x10c3
            java.lang.String r0 = "request"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x10c3
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.getValidMode()
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r5 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_TXZ_MUSIC
            int r5 = r5.getIntValue()
            if (r0 == r5) goto L_0x10c3
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r5 = "com.choiceway.eventcenter.ZHTY_EXIT_DIANTAIZHIJIA"
            r0.<init>(r5)
            java.lang.String r5 = "com.choiceway.eventcenter.ZHTY_EXIT_DIANTAIZHIJIA_DATA"
            r6 = 0
            r0.putExtra(r5, r6)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.sendBroadcast(r0)
        L_0x10c3:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x10dd
            boolean r5 = r1.bTXZSound
            if (r5 != 0) goto L_0x10d6
            boolean r5 = r1.bAmapautoSound
            if (r5 == 0) goto L_0x10d0
            goto L_0x10d6
        L_0x10d0:
            r5 = 0
            r0.zhtySwitchGpsChannal(r5)
            goto L_0x0fab
        L_0x10d6:
            r5 = 0
            r6 = 1
            r0.zhtySwitchGpsChannal(r6)
            goto L_0x0fab
        L_0x10dd:
            r5 = 0
            goto L_0x0fab
        L_0x10e0:
            r5 = 0
            java.lang.String r8 = "com.szchoiceway.eventcenter.EventUtils.CHEKU_BOTTOM_KEY"
            boolean r8 = r4.equals(r8)
            if (r8 == 0) goto L_0x111f
            java.lang.String r0 = "com.szchoiceway.eventcenter.EventUtils.CHEKU_BOTTOM_KEY_DATA"
            int r0 = r3.getIntExtra(r0, r5)
            java.lang.String r6 = "com.szchoiceway.eventcenter.EventUtils.CHEKU_BOTTOM_KEY_DATA_PARKDOWN"
            boolean r6 = r3.getBooleanExtra(r6, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "cheku_iKey = "
            r5.append(r8)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            r5 = 9
            if (r0 != r5) goto L_0x1116
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x0fab
            r5.ProcessKey_cheku_bottom(r0, r6)
            goto L_0x0fab
        L_0x1116:
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x0fab
            r5.ProcessKey_cheku_bottom(r0)
            goto L_0x0fab
        L_0x111f:
            java.lang.String r5 = "com.szchoiceway.eventcenter.EventUtils.ZXW_KILL_ALL_APK_XINGSHUO"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x1130
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0fab
            r0.killAllRunningAPK_XingShuo()
            goto L_0x0fab
        L_0x1130:
            java.lang.String r5 = "com.szchoiceway.KSW_RETURN_MODE_REQUEST"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x115c
            java.lang.String r0 = "com.szchoiceway.KSW_RETURN_MODE_REQUEST_DATA"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "onReceive: KSW_RETURN_MODE_REQUEST-ksw_imode = "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x0fab
            r5.retrurnModeRequst_KSW(r0)
            goto L_0x0fab
        L_0x115c:
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.BROADCAST_KSW_DVD_MUSIC_SWITCH_VIDEO_VIEW"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x1174
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.BROADCAST_KSW_DVD_MUSIC_SWITCH_VIDEO_VIEW_DATA"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x0fab
            r5.ksw_dvd_switch_8836_arm_view(r0)
            goto L_0x0fab
        L_0x1174:
            java.lang.String r5 = "com.txznet.TXZSDKDemo.TXZ_TO_TXZ_KILLPROCESS"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x11c1
            java.lang.String r0 = "com.txznet.TXZSDKDemo.TXZ_TO_TXZ_KILLPROCESS_EXTRA"
            java.lang.String r0 = r3.getStringExtra(r0)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "close "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r6 = " by txz"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.d(r15, r5)
            if (r0 == 0) goto L_0x11ab
            java.lang.String r5 = "com.autonavi.amapauto"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x11ab
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r6 = 4
            r5.ksw_rev_amap_auto(r6)
        L_0x11ab:
            if (r0 == 0) goto L_0x11ba
            java.lang.String r5 = "com.choiceway.dsp"
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x11ba
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            r5.endEqMode_KSW()
        L_0x11ba:
            if (r0 == 0) goto L_0x0fab
            com.szchoiceway.eventcenter.EventUtils.killProcess(r0)
            goto L_0x0fab
        L_0x11c1:
            java.lang.String r5 = "AUTONAVI_STANDARD_BROADCAST_SEND"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x11ee
            java.lang.String r0 = "KEY_TYPE"
            r5 = 0
            int r0 = r3.getIntExtra(r0, r5)
            java.lang.String r6 = "EXTRA_STATE"
            int r6 = r3.getIntExtra(r6, r5)
            r5 = 10019(0x2723, float:1.404E-41)
            if (r0 != r5) goto L_0x11e1
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x11e1
            r5.ksw_rev_amap_auto(r6)
        L_0x11e1:
            android.os.Bundle r5 = r28.getExtras()
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext
            if (r8 == 0) goto L_0x0fab
            r8.refreshNaviInfo(r0, r6, r5)
            goto L_0x0fab
        L_0x11ee:
            java.lang.String r5 = "CLEAR_ALL_ACTIVITY"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x1204
            java.lang.String r0 = "--->>> CLEAR_ALL_ACTIVITY"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0fab
            r0.sendKSW_0x00_0x74_all()
            goto L_0x0fab
        L_0x1204:
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_EVT"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x121c
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.ZXW_ORIGINAL_MCU_KEY_MOUSE_MOVE_DATA"
            r5 = -1
            int r0 = r3.getIntExtra(r0, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x0fab
            r5.OnRefreshMouseWnd(r0)
            goto L_0x0fab
        L_0x121c:
            java.lang.String r5 = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_CONTROL_SPLIT_SCREEN_KEY"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x1239
            java.lang.String r0 = "onReceive: action = ZXW_ACTION_CONTROL_SPLIT_SCREEN_KEY"
            android.util.Log.i(r15, r0)
            java.lang.String r0 = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_CONTROL_SPLIT_SCREEN_EXTRA"
            r5 = -1
            int r0 = r3.getIntExtra(r0, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x0fab
            r5.controlSplitScreen(r0)
            goto L_0x0fab
        L_0x1239:
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x128b
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_UpdateInfor"
            r3.getStringExtra(r0)
            java.lang.String r0 = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherTemp"
            java.lang.String r0 = r3.getStringExtra(r0)
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherDay"
            r3.getStringExtra(r5)
            java.lang.String r5 = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherTitle"
            java.lang.String r5 = r3.getStringExtra(r5)
            java.lang.String r8 = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_CityName"
            java.lang.String r8 = r3.getStringExtra(r8)
            java.lang.String r9 = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherInfor"
            r3.getStringExtra(r9)
            java.lang.String r9 = "com.choiceway.eventcenter.EventUtils.CHEKU_WEATHER_IPC_WeatherIcon"
            java.lang.String r9 = r3.getStringExtra(r9)
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L_0x1270
            java.lang.String r9 = "0"
        L_0x1270:
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            if (r6 == 0) goto L_0x0fab
            r10 = 5
            java.lang.String[] r11 = new java.lang.String[r10]
            r10 = 0
            r11[r10] = r0
            r10 = 1
            r11[r10] = r0
            r10 = 2
            r11[r10] = r5
            r5 = 3
            r11[r5] = r8
            r5 = 4
            r11[r5] = r9
            r6.updateWeatherInfor(r11)
            goto L_0x0fab
        L_0x128b:
            java.lang.String r5 = "com.choiceway.keyboard.ZXW_SYS_KEYBOARD_SHOW"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x12a2
            java.lang.String r0 = "onReceive: ZXW_SYS_KEYBOARD_SHOW"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0fab
            r5 = 1
            r0.setbSysKeyboardIsShow(r5)
            goto L_0x0fab
        L_0x12a2:
            java.lang.String r5 = "com.choiceway.keyboard.ZXW_SYS_KEYBOARD_HIDE"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x12b9
            java.lang.String r0 = "onReceive: ZXW_SYS_KEYBOARD_HIDE"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0fab
            r5 = 0
            r0.setbSysKeyboardIsShow(r5)
            goto L_0x0fab
        L_0x12b9:
            java.lang.String r5 = "com.szchoiceway.initEventState"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x12ca
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0fab
            r0.initEventState2()
            goto L_0x0fab
        L_0x12ca:
            java.lang.String r5 = "com.szchoiceway.eventcenter.EventUtils.VALID_MODE_INFOR_CHANGE"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x12db
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x0fab
            r0.refreshPlayState()
            goto L_0x0fab
        L_0x12db:
            java.lang.String r5 = "com.szchoiceway.can.ZXW_CAN_MAIN_OTHER_VER"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x131d
            java.lang.String r0 = "com.szchoiceway.can.ZXW_CAN_MAIN_OTHER_VER_DATA0"
            java.lang.String r0 = r3.getStringExtra(r0)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "onReceive: ZXW_CAN_MAIN_OTHER_VER-strCanVer = "
            r5.append(r8)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r15, r5)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r5 = r5.mSysProviderOpt
            if (r5 == 0) goto L_0x0fab
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r5 = r5.mSysProviderOpt
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            r8.append(r6)
            java.lang.String r0 = r8.toString()
            java.lang.String r6 = "com.szchoiceway.eventcenter.ZXW_CAN_VERSION"
            r5.updateRecord(r6, r0)
            goto L_0x0fab
        L_0x131d:
            java.lang.String r5 = "com.szchoiceway.eventcenter.MCU_CAR_CAN_RADAR_INFO"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x1339
            java.lang.String r0 = "onReceive: MCU_CAR_CAN_RADAR_INFO"
            android.util.Log.i(r15, r0)
            java.lang.String r0 = "com.szchoiceway.eventcenter.CAR_CAN_DATA"
            byte[] r0 = r3.getByteArrayExtra(r0)
            com.szchoiceway.eventcenter.EventService r5 = r1.mContext
            if (r5 == 0) goto L_0x0fab
            r5.setRadarData(r0)
            goto L_0x0fab
        L_0x1339:
            java.lang.String r5 = "com.szchoiceway.action.startFocusMove"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x135d
            android.content.Intent r0 = new android.content.Intent
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            java.lang.Class<com.szchoiceway.eventcenter.AssistiveTouch.TopFloatService> r5 = com.szchoiceway.eventcenter.AssistiveTouch.TopFloatService.class
            r0.<init>(r3, r5)
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            r3.startService(r0)
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            com.szchoiceway.eventcenter.AssistiveTouch.TopFloatService.SetEvtServiceContext(r3)
            r5 = 1
            com.szchoiceway.eventcenter.SysProviderOpt.m_bSupportFocusMove = r5
            r6 = r19
            r5 = r20
            goto L_0x1ee9
        L_0x135d:
            r5 = 1
            java.lang.String r8 = "com.zjinnova.zlink"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x15a0
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            int r6 = r6.mConnectState
            if (r6 != r5) goto L_0x136d
            return
        L_0x136d:
            r5 = r20
            java.lang.String r6 = r3.getStringExtra(r5)
            java.lang.String r8 = "command"
            java.lang.String r8 = r3.getStringExtra(r8)
            java.lang.String r9 = "phoneMode"
            java.lang.String r9 = r3.getStringExtra(r9)
            java.lang.String r10 = "onReceive zlink"
            android.util.Log.d(r15, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "status = "
            r10.append(r11)
            r10.append(r6)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r15, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "command = "
            r10.append(r11)
            r10.append(r8)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r15, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "phoneMode = "
            r10.append(r11)
            r10.append(r9)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r15, r10)
            java.lang.String r10 = "specFuncCode"
            r11 = 0
            int r10 = r3.getIntExtra(r10, r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "specFuncCode = "
            r11.append(r12)
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            android.util.Log.d(r15, r10)
            if (r6 == 0) goto L_0x157a
            int r10 = r6.hashCode()
            switch(r10) {
                case -2087582999: goto L_0x1436;
                case -1843701849: goto L_0x142c;
                case -1478680495: goto L_0x1422;
                case -497207953: goto L_0x1418;
                case 2142494: goto L_0x140e;
                case 1015497884: goto L_0x1404;
                case 1405544733: goto L_0x13f9;
                case 1709675476: goto L_0x13ef;
                case 1766422463: goto L_0x13e5;
                default: goto L_0x13e4;
            }
        L_0x13e4:
            goto L_0x1440
        L_0x13e5:
            java.lang.String r10 = "PHONE_CALL_OFF"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 6
            goto L_0x1441
        L_0x13ef:
            java.lang.String r10 = "MAIN_PAGE_HIDDEN"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 3
            goto L_0x1441
        L_0x13f9:
            java.lang.String r10 = "SIRI_OFF"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 8
            goto L_0x1441
        L_0x1404:
            java.lang.String r10 = "DISCONNECT"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 1
            goto L_0x1441
        L_0x140e:
            java.lang.String r10 = "EXIT"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 4
            goto L_0x1441
        L_0x1418:
            java.lang.String r10 = "PHONE_CALL_ON"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 5
            goto L_0x1441
        L_0x1422:
            java.lang.String r10 = "SIRI_ON"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 7
            goto L_0x1441
        L_0x142c:
            java.lang.String r10 = "MAIN_PAGE_SHOW"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 2
            goto L_0x1441
        L_0x1436:
            java.lang.String r10 = "CONNECTED"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x1440
            r6 = 0
            goto L_0x1441
        L_0x1440:
            r6 = -1
        L_0x1441:
            switch(r6) {
                case 0: goto L_0x14a0;
                case 1: goto L_0x1484;
                case 2: goto L_0x146f;
                case 3: goto L_0x1467;
                case 4: goto L_0x145f;
                case 5: goto L_0x1452;
                case 6: goto L_0x1446;
                default: goto L_0x1444;
            }
        L_0x1444:
            goto L_0x157a
        L_0x1446:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.bZlinkCarplayPhoneOn = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.thirdPhoneOff(r6)
            goto L_0x157a
        L_0x1452:
            r6 = 0
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r9 = 1
            r0.bZlinkCarplayPhoneOn = r9
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.thirdPhoneOn()
            goto L_0x157a
        L_0x145f:
            r6 = 0
            r9 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayResume = r6
            goto L_0x157a
        L_0x1467:
            r6 = 0
            r9 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayResume = r6
            goto L_0x157a
        L_0x146f:
            r9 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayResume = r9
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            com.szchoiceway.eventcenter.-$$Lambda$EvtModel$7T3yo2IVsWoo2latEZc6LsgeeXE r6 = new com.szchoiceway.eventcenter.-$$Lambda$EvtModel$7T3yo2IVsWoo2latEZc6LsgeeXE
            r6.<init>()
            r9 = 2000(0x7d0, double:9.88E-321)
            r0.postDelayed(r6, r9)
            goto L_0x157a
        L_0x1484:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.bZlinkCarplayConnected = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.show360CarplayConnected = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.mMuteMusicZlinkPhone
            if (r0 == 0) goto L_0x1498
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.muteMusicAndAlarm(r6)
        L_0x1498:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 1
            com.szchoiceway.eventcenter.EventUtils.showTXZIcon(r0, r6)
            goto L_0x157a
        L_0x14a0:
            r6 = 1
            com.szchoiceway.eventcenter.EventService r10 = r1.mContext
            r10.bZlinkCarplayConnected = r6
            com.szchoiceway.eventcenter.EventService r10 = r1.mContext
            boolean r10 = r10.show360Dialog
            if (r10 == 0) goto L_0x14b0
            com.szchoiceway.eventcenter.EventService r10 = r1.mContext
            r10.show360CarplayConnected = r6
            goto L_0x14c6
        L_0x14b0:
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils.startZlink(r6)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            r21 = 4097(0x1001, float:5.741E-42)
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r20 = r6
            r20.notifyValidModeEvt(r21, r22, r23, r24, r25)
        L_0x14c6:
            if (r9 == 0) goto L_0x1574
            r9.hashCode()
            int r6 = r9.hashCode()
            switch(r6) {
                case -1934599382: goto L_0x1549;
                case -1471446065: goto L_0x153e;
                case -1413160370: goto L_0x1533;
                case -1107119326: goto L_0x1528;
                case -405676408: goto L_0x151d;
                case -36609553: goto L_0x1512;
                case 286838830: goto L_0x1507;
                case 532439285: goto L_0x14fc;
                case 729020223: goto L_0x14ef;
                case 1213791358: goto L_0x14e2;
                case 1348961147: goto L_0x14d5;
                default: goto L_0x14d2;
            }
        L_0x14d2:
            r6 = -1
            goto L_0x1553
        L_0x14d5:
            java.lang.String r0 = "dlna_wired"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x14de
            goto L_0x14d2
        L_0x14de:
            r6 = 10
            goto L_0x1553
        L_0x14e2:
            java.lang.String r0 = "airplay_wired"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x14eb
            goto L_0x14d2
        L_0x14eb:
            r6 = 9
            goto L_0x1553
        L_0x14ef:
            java.lang.String r0 = "airplay_wireless"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x14f8
            goto L_0x14d2
        L_0x14f8:
            r6 = 8
            goto L_0x1553
        L_0x14fc:
            java.lang.String r6 = "carplay_wireless"
            boolean r6 = r9.equals(r6)
            if (r6 != 0) goto L_0x1505
            goto L_0x14d2
        L_0x1505:
            r6 = 7
            goto L_0x1553
        L_0x1507:
            java.lang.String r0 = "auto_wireless"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1510
            goto L_0x14d2
        L_0x1510:
            r6 = 6
            goto L_0x1553
        L_0x1512:
            java.lang.String r0 = "auto_wired"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x151b
            goto L_0x14d2
        L_0x151b:
            r6 = 5
            goto L_0x1553
        L_0x151d:
            java.lang.String r0 = "carplay_wired"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1526
            goto L_0x14d2
        L_0x1526:
            r6 = 4
            goto L_0x1553
        L_0x1528:
            java.lang.String r0 = "dlna_wireless"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1531
            goto L_0x14d2
        L_0x1531:
            r6 = 3
            goto L_0x1553
        L_0x1533:
            java.lang.String r0 = "android_mirror_wireless"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x153c
            goto L_0x14d2
        L_0x153c:
            r6 = 2
            goto L_0x1553
        L_0x153e:
            java.lang.String r0 = "android_mirror_wired"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1547
            goto L_0x14d2
        L_0x1547:
            r6 = 1
            goto L_0x1553
        L_0x1549:
            java.lang.String r0 = "hicar_wireless"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1552
            goto L_0x14d2
        L_0x1552:
            r6 = 0
        L_0x1553:
            switch(r6) {
                case 0: goto L_0x156f;
                case 1: goto L_0x1569;
                case 2: goto L_0x1569;
                case 3: goto L_0x1563;
                case 4: goto L_0x155d;
                case 5: goto L_0x1557;
                case 6: goto L_0x1557;
                case 7: goto L_0x155d;
                case 8: goto L_0x1569;
                case 9: goto L_0x1569;
                case 10: goto L_0x1563;
                default: goto L_0x1556;
            }
        L_0x1556:
            goto L_0x1574
        L_0x1557:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 1
            r0.mZlinLinkMode = r6
            goto L_0x1574
        L_0x155d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.mZlinLinkMode = r6
            goto L_0x1574
        L_0x1563:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 4
            r0.mZlinLinkMode = r6
            goto L_0x1574
        L_0x1569:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 3
            r0.mZlinLinkMode = r6
            goto L_0x1574
        L_0x156f:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 5
            r0.mZlinLinkMode = r6
        L_0x1574:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            com.szchoiceway.eventcenter.EventUtils.showTXZIcon(r0, r6)
        L_0x157a:
            java.lang.String r0 = "CMD_MIC_START"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x158c
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 1
            r0.bZlinkCarplayMic = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.zlinkMicStart()
        L_0x158c:
            java.lang.String r0 = "CMD_MIC_STOP"
            boolean r0 = r0.equals(r8)
            if (r0 == 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.bZlinkCarplayMic = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.zlinkMicStop()
            goto L_0x16b2
        L_0x15a0:
            r5 = r20
            java.lang.String r8 = "com.ucarhu.carlink"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x16b6
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            int r6 = r6.mConnectState
            r8 = 1
            if (r6 != r8) goto L_0x15b2
            return
        L_0x15b2:
            java.lang.String r6 = r3.getStringExtra(r5)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "onReceive carlink status = "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r15, r8)
            java.lang.String r8 = "specFuncCode"
            r9 = 0
            int r8 = r3.getIntExtra(r8, r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "specFuncCode = "
            r9.append(r10)
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            android.util.Log.d(r15, r8)
            if (r6 == 0) goto L_0x16b2
            r6.hashCode()
            int r8 = r6.hashCode()
            switch(r8) {
                case -2087582999: goto L_0x1641;
                case -2021015817: goto L_0x1636;
                case -1843701849: goto L_0x162b;
                case -497207953: goto L_0x1620;
                case 935892539: goto L_0x1615;
                case 1709675476: goto L_0x160a;
                case 1766422463: goto L_0x15ff;
                case 1773018935: goto L_0x15f4;
                default: goto L_0x15f1;
            }
        L_0x15f1:
            r6 = -1
            goto L_0x164b
        L_0x15f4:
            java.lang.String r8 = "MIC_OFF"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x15fd
            goto L_0x15f1
        L_0x15fd:
            r6 = 7
            goto L_0x164b
        L_0x15ff:
            java.lang.String r0 = "PHONE_CALL_OFF"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x1608
            goto L_0x15f1
        L_0x1608:
            r6 = 6
            goto L_0x164b
        L_0x160a:
            java.lang.String r0 = "MAIN_PAGE_HIDDEN"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x1613
            goto L_0x15f1
        L_0x1613:
            r6 = 5
            goto L_0x164b
        L_0x1615:
            java.lang.String r0 = "DISCONNECTED"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x161e
            goto L_0x15f1
        L_0x161e:
            r6 = 4
            goto L_0x164b
        L_0x1620:
            java.lang.String r0 = "PHONE_CALL_ON"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x1629
            goto L_0x15f1
        L_0x1629:
            r6 = 3
            goto L_0x164b
        L_0x162b:
            java.lang.String r0 = "MAIN_PAGE_SHOW"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x1634
            goto L_0x15f1
        L_0x1634:
            r6 = 2
            goto L_0x164b
        L_0x1636:
            java.lang.String r0 = "MIC_ON"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x163f
            goto L_0x15f1
        L_0x163f:
            r6 = 1
            goto L_0x164b
        L_0x1641:
            java.lang.String r0 = "CONNECTED"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x164a
            goto L_0x15f1
        L_0x164a:
            r6 = 0
        L_0x164b:
            switch(r6) {
                case 0: goto L_0x16a3;
                case 1: goto L_0x1698;
                case 2: goto L_0x1684;
                case 3: goto L_0x1679;
                case 4: goto L_0x166d;
                case 5: goto L_0x1666;
                case 6: goto L_0x165a;
                case 7: goto L_0x164f;
                default: goto L_0x164e;
            }
        L_0x164e:
            goto L_0x16b2
        L_0x164f:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.bZlinkCarplayMic = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.zlinkMicStop()
            goto L_0x16b2
        L_0x165a:
            r6 = 0
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayPhoneOn = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r8 = 1
            r0.thirdPhoneOff(r8)
            goto L_0x16b2
        L_0x1666:
            r6 = 0
            r8 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayResume = r6
            goto L_0x16b2
        L_0x166d:
            r6 = 0
            r8 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayConnected = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.muteMusicAndAlarm(r6)
            goto L_0x16b2
        L_0x1679:
            r8 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayPhoneOn = r8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.thirdPhoneOn()
            goto L_0x16b2
        L_0x1684:
            r8 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayResume = r8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            com.szchoiceway.eventcenter.-$$Lambda$EvtModel$Z1uQmLnwVlO8iD559-GbEtYgoW4 r6 = new com.szchoiceway.eventcenter.-$$Lambda$EvtModel$Z1uQmLnwVlO8iD559-GbEtYgoW4
            r6.<init>()
            r9 = 2000(0x7d0, double:9.88E-321)
            r0.postDelayed(r6, r9)
            goto L_0x16b2
        L_0x1698:
            r8 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayMic = r8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.zlinkMicStart()
            goto L_0x16b2
        L_0x16a3:
            r8 = 1
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bZlinkCarplayConnected = r8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.enterZlinkCarplay()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 2
            r0.mZlinLinkMode = r6
        L_0x16b2:
            r6 = r19
            goto L_0x1ee8
        L_0x16b6:
            java.lang.String r8 = "com.android.settings.action.FactoryReset"
            boolean r8 = r8.equals(r4)
            if (r8 != 0) goto L_0x1eb1
            java.lang.String r8 = "com.android.sz.zxw.factoryreset"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x16c8
            goto L_0x1eb1
        L_0x16c8:
            java.lang.String r8 = "com.choiceway.eventcenter.EventUtils.MCU_MSG_MAIL_VOL"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x16d1
            goto L_0x16b2
        L_0x16d1:
            java.lang.String r8 = "com.choiceway.dsp.action.SendDataToMcu"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x16e5
            java.lang.String r0 = "dsp_data"
            byte[] r0 = r3.getByteArrayExtra(r0)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            r6.sendDataToMcu(r0)
            goto L_0x16b2
        L_0x16e5:
            java.lang.String r8 = "com.szchoiceway.zxwauto.ACTION_CONNECTED"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x170e
            java.lang.String r0 = "onReceive: com.szchoiceway.zxwauto CarPlay已连接"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r6 = "Sys_user_close_wifi"
            r8 = 0
            boolean r0 = r0.getRecordBoolean(r6, r8)
            if (r0 == 0) goto L_0x170a
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r6 = "Sys_user_close_wifi"
            java.lang.String r8 = "0"
            r0.updateRecord(r6, r8)
        L_0x170a:
            r26.releaseAudioFocus()
            goto L_0x16b2
        L_0x170e:
            java.lang.String r8 = "com.szchoiceway.zxwauto.ACTION_DISCONNECT"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x1736
            java.lang.String r0 = "onReceive: com.szchoiceway.zxwauto CarPlay已断开"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 127(0x7f, float:1.78E-43)
            r0.processAutoKey(r6)
            r26.requestFocus()
            java.lang.String r0 = android.os.Build.MODEL
            java.lang.String r6 = "rk3399"
            boolean r0 = r0.startsWith(r6)
            if (r0 != 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.rebootService()
            goto L_0x16b2
        L_0x1736:
            java.lang.String r8 = "com.szchoiceway.zxwauto.ACTION_MEDIAPLAYSTATE"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x177f
            java.lang.String r0 = "MediaPlayState"
            r6 = 0
            int r0 = r3.getIntExtra(r0, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "onReceive: mediaPlayState = "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r15, r6)
            r6 = 1
            if (r0 == r6) goto L_0x1776
            r6 = 2
            if (r0 == r6) goto L_0x176d
            r6 = 3
            if (r0 == r6) goto L_0x1764
            goto L_0x16b2
        L_0x1764:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 86
            r0.processAutoKey(r6)
            goto L_0x16b2
        L_0x176d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 127(0x7f, float:1.78E-43)
            r0.processAutoKey(r6)
            goto L_0x16b2
        L_0x1776:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 126(0x7e, float:1.77E-43)
            r0.processAutoKey(r6)
            goto L_0x16b2
        L_0x177f:
            java.lang.String r8 = "com.szchoiceway.zxwauto.ACTION_KEYEVENTNOTIFY"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x17bb
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUITypeVer
            r6 = 102(0x66, float:1.43E-43)
            if (r0 == r6) goto L_0x1790
            return
        L_0x1790:
            java.lang.String r0 = "keycode"
            r6 = 0
            int r0 = r3.getIntExtra(r0, r6)
            java.lang.String r8 = "down"
            int r8 = r3.getIntExtra(r8, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = "onReceive: down_key = "
            r6.append(r9)
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r15, r6)
            r6 = 1
            if (r8 != r6) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            r6.processAutoKey(r0)
            goto L_0x16b2
        L_0x17bb:
            java.lang.String r8 = "com.szchoiceway.zxwauto.ACTION_AUTOMODENOTIFY"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x17e6
            java.lang.String r0 = "AndroidAutoModeState"
            r6 = 0
            int r0 = r3.getIntExtra(r0, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "onReceive: com.szchoiceway.zxwauto androidAutoModeState = "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r15, r6)
            r6 = 1
            if (r0 != r6) goto L_0x16b2
            r26.releaseAudioFocus()
            goto L_0x16b2
        L_0x17e6:
            java.lang.String r8 = "com.szchoiceway.carplay.ACTION_NOTIFY"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x18c2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r8 = "OS_RESOLUTION"
            java.lang.String r0 = r0.getRecordValue(r8, r6)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "onReceive: ACTION_NOTIFY os_resolution = "
            r8.append(r9)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r15, r8)
            java.lang.String r8 = "1024x600"
            boolean r8 = r8.equalsIgnoreCase(r0)
            if (r8 == 0) goto L_0x1816
            java.lang.String r0 = "800x480"
        L_0x1816:
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r8 = r8.mSysProviderOpt
            java.lang.String r9 = "Sys_DisplayMode2"
            r10 = 0
            int r8 = r8.getRecordInteger(r9, r10)
            int r9 = r1.mLastDisplayMode
            if (r9 == r8) goto L_0x182a
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext
            r8.SaveConfig()
        L_0x182a:
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r8 = r8.mSysProviderOpt
            java.lang.String r9 = "RESOLUTION"
            java.lang.String r6 = r8.getRecordValue(r9, r6)
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x184c
            java.lang.String r0 = "onReceive: !os_resolution.equals(strResolution)"
            android.util.Log.i(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            java.lang.String r6 = "Inconsistent resolution detected, will restart automatically"
            com.szchoiceway.eventcenter.EventUtils.showTipString((android.content.Context) r0, (java.lang.String) r6)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.initSystemParam()
            goto L_0x18b2
        L_0x184c:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.CarPlaySocketServer r0 = r0.mCarPlaySocketServer
            if (r0 == 0) goto L_0x185d
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.CarPlaySocketServer r0 = r0.mCarPlaySocketServer
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            java.lang.String r6 = r6.mCurTopClassName
            r0.setCurTopClassName(r6)
        L_0x185d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.mIsFirstConnect
            if (r0 == 0) goto L_0x18b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.mIsFirstConnect = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r6 = "Startup_PackageName"
            java.lang.String r0 = r0.getRecordValue(r6)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r6.mSysProviderOpt
            java.lang.String r8 = "Startup_ActivityName"
            java.lang.String r6 = r6.getRecordValue(r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "onReceive: packageName = "
            r8.append(r9)
            r8.append(r0)
            java.lang.String r9 = ", className = "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r15, r8)
            boolean r8 = android.text.TextUtils.isEmpty(r0)
            if (r8 != 0) goto L_0x18b2
            boolean r8 = android.text.TextUtils.isEmpty(r6)
            if (r8 != 0) goto L_0x18b2
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext
            android.os.Handler r8 = r8.mEventHandler
            com.szchoiceway.eventcenter.EvtModel$2 r9 = new com.szchoiceway.eventcenter.EvtModel$2
            r9.<init>(r0, r6)
            r10 = 2500(0x9c4, double:1.235E-320)
            r8.postDelayed(r9, r10)
        L_0x18b2:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            com.szchoiceway.eventcenter.EvtModel$3 r6 = new com.szchoiceway.eventcenter.EvtModel$3
            r6.<init>()
            r8 = 300(0x12c, double:1.48E-321)
            r0.postDelayed(r6, r8)
            goto L_0x16b2
        L_0x18c2:
            java.lang.String r8 = "com.szchoiceway.carplay.ACTION_AUDIOFOCUS"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x191b
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            java.lang.String r6 = "audioFocus"
            r8 = 0
            int r6 = r3.getIntExtra(r6, r8)
            r0.mCarplayAudioFocus = r6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "onReceive: mCarplayAudioFocus = "
            r0.append(r6)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            int r6 = r6.mCarplayAudioFocus
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r15, r0)
            android.media.AudioManager r0 = r1.mAudioManager
            if (r0 != 0) goto L_0x18fd
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            java.lang.String r6 = "audio"
            java.lang.Object r0 = r0.getSystemService(r6)
            android.media.AudioManager r0 = (android.media.AudioManager) r0
            r1.mAudioManager = r0
        L_0x18fd:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.mCarplayAudioFocus
            if (r0 != 0) goto L_0x190f
            r26.requestFocus()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 127(0x7f, float:1.78E-43)
            r0.processAutoKey(r6)
            goto L_0x16b2
        L_0x190f:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.mCarplayAudioFocus
            r8 = 1
            if (r0 != r8) goto L_0x16b2
            r26.releaseAudioFocus()
            goto L_0x16b2
        L_0x191b:
            r8 = 1
            java.lang.String r9 = "com.txznet.txz.record.show"
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x195e
            java.lang.String r0 = "onReceive com.txznet.txz.record.show"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.mTxzDialogShow = r8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.media.AudioManager r0 = r0.mAudioMgr
            r6 = 3
            r8 = 0
            r0.setStreamVolume(r6, r8, r8)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.media.AudioManager r0 = r0.mAudioMgr
            r6 = 4
            int r0 = r0.getStreamMaxVolume(r6)
            com.szchoiceway.eventcenter.EventService r9 = r1.mContext
            android.media.AudioManager r9 = r9.mAudioMgr
            r9.setStreamVolume(r6, r0, r8)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.mRecordOpen = r8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.b_Original_View
            if (r0 == 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.virtualCenterClick()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r6 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_THIRD_APP
            r0.sendKSW_0x00_0x67(r6, r8)
            goto L_0x16b2
        L_0x195e:
            r8 = 0
            java.lang.String r9 = "com.txznet.txz.record.dismiss"
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x1980
            java.lang.String r0 = "onReceive com.txznet.txz.record.dismiss"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.mTxzDialogShow = r8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            com.szchoiceway.eventcenter.EvtModel$4 r6 = new com.szchoiceway.eventcenter.EvtModel$4
            r6.<init>()
            r8 = 500(0x1f4, double:2.47E-321)
            r0.postDelayed(r6, r8)
            goto L_0x16b2
        L_0x1980:
            java.lang.String r8 = "com.liaoy.rcp.ACTION_SHOWTIPUI"
            boolean r8 = r8.equalsIgnoreCase(r4)
            if (r8 != 0) goto L_0x1ea1
            java.lang.String r8 = "com.szchoiceway.action.Illegal_authorization"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x1992
            goto L_0x1ea1
        L_0x1992:
            java.lang.String r8 = "com.liaoy.rcp.ACTION_HUHAVEGPS"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x19aa
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r6 = "support_original_gps"
            r0.updateRecord(r6, r7)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.refreshSupportGpsStatus()
            goto L_0x16b2
        L_0x19aa:
            java.lang.String r8 = "com.liaoyuan.mylink.ACTION_WIFICPSPEECH_STATE"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x1a0a
            java.lang.String r0 = "WIFICPSPEECH_STATUS"
            r6 = 0
            int r0 = r3.getIntExtra(r0, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "onReceive: wificpspeech_status = "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r15, r6)
            android.media.AudioManager r6 = r1.mAudioManager
            if (r6 != 0) goto L_0x19dd
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            java.lang.String r8 = "audio"
            java.lang.Object r6 = r6.getSystemService(r8)
            android.media.AudioManager r6 = (android.media.AudioManager) r6
            r1.mAudioManager = r6
        L_0x19dd:
            r6 = 1
            if (r0 != r6) goto L_0x19ef
            android.media.AudioManager r0 = r1.mAudioManager
            r6 = 4
            r8 = 0
            r0.setStreamVolume(r6, r8, r8)
            android.media.AudioManager r0 = r1.mAudioManager
            r9 = 3
            r0.setStreamVolume(r9, r8, r8)
            goto L_0x16b2
        L_0x19ef:
            r6 = 4
            r8 = 0
            r9 = 3
            android.media.AudioManager r0 = r1.mAudioManager
            int r0 = r0.getStreamMaxVolume(r6)
            android.media.AudioManager r10 = r1.mAudioManager
            int r10 = r10.getStreamMaxVolume(r9)
            android.media.AudioManager r11 = r1.mAudioManager
            r11.setStreamVolume(r6, r0, r8)
            android.media.AudioManager r0 = r1.mAudioManager
            r0.setStreamVolume(r9, r10, r8)
            goto L_0x16b2
        L_0x1a0a:
            java.lang.String r8 = "com.szchoiceway.action.toSaveConfig"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x1a19
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.SaveConfig()
            goto L_0x16b2
        L_0x1a19:
            java.lang.String r8 = "com.szchoiceway.action.reboot"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x1a28
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.rebootService()
            goto L_0x16b2
        L_0x1a28:
            java.lang.String r8 = "com.choiceway.action.btsuite_version"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x1a41
            java.lang.String r0 = "version"
            java.lang.String r0 = r3.getStringExtra(r0)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r6.mSysProviderOpt
            java.lang.String r8 = "ZXW_BT_VERSION"
            r6.updateRecord(r8, r0)
            goto L_0x16b2
        L_0x1a41:
            java.lang.String r8 = "com.systemui.ethernet.connected"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x1a7a
            java.lang.String r0 = "connected"
            r6 = 0
            boolean r0 = r3.getBooleanExtra(r0, r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "onReceive: 蓝牙直连 -- connected = "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r15, r6)
            if (r0 == 0) goto L_0x1a6e
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r8 = -1
            r0.refreshWifiStatus((int) r8)
            goto L_0x16b2
        L_0x1a6e:
            boolean r0 = r1.bWifiNetWorkState
            if (r0 != 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.refreshWifiStatus((int) r6)
            goto L_0x16b2
        L_0x1a7a:
            r8 = -1
            java.lang.String r9 = "com.liaoyuan.mylink"
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x1ae1
            java.lang.String r0 = r3.getStringExtra(r5)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "onReceive: liaoyuan.mylink ---- status = "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.i(r15, r6)
            java.lang.String r6 = "CONNECTED"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x1aaf
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 1
            r0.bZlinkHicarConnected = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.refreshCarAutoStatus(r6)
            goto L_0x16b2
        L_0x1aaf:
            java.lang.String r6 = "DISCONNECT"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x1ac3
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.bZlinkHicarConnected = r6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.refreshCarAutoStatus(r6)
            goto L_0x16b2
        L_0x1ac3:
            java.lang.String r6 = "MAIN_PAGE_SHOW"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x1ad2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 1
            r0.mLinkVisible = r6
            goto L_0x16b2
        L_0x1ad2:
            java.lang.String r6 = "MAIN_PAGE_HIDDEN"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 0
            r0.mLinkVisible = r6
            goto L_0x16b2
        L_0x1ae1:
            java.lang.String r9 = "android.intent.action.SIM_STATE_CHANGED"
            boolean r9 = r4.equals(r9)
            if (r9 == 0) goto L_0x1af0
            java.lang.String r0 = "onReceive: ACTION_SIM_STATE_CHANGED"
            android.util.Log.i(r15, r0)
            goto L_0x16b2
        L_0x1af0:
            java.lang.String r9 = "com.carletter.link"
            boolean r9 = r4.equals(r9)
            if (r9 == 0) goto L_0x1b48
            java.lang.String r0 = "onReceive: com.carletter.link"
            android.util.Log.d(r15, r0)
            java.lang.String r0 = r3.getStringExtra(r5)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "status = "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r15, r6)
            java.lang.String r6 = "CONNECTED"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.b_Original_View
            if (r0 == 0) goto L_0x1b33
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r6 = 18
            r8 = 1
            r9 = 0
            r0.sendKSW_0x00_0x69(r6, r8, r9)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r6 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_CARPLAY
            r0.sendMode(r6)
        L_0x1b33:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.isLetterTop()
            if (r0 != 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r6 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_CARPLAY
            int r6 = r6.getIntValue()
            r0.postRunModeActivity(r6)
            goto L_0x16b2
        L_0x1b48:
            java.lang.String r9 = "ZXW_ACTION_UPDATE_CONFIGURATION"
            boolean r9 = r4.equals(r9)
            if (r9 == 0) goto L_0x1b57
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.ksw_sd_usb_factory_xml_update()
            goto L_0x16b2
        L_0x1b57:
            java.lang.String r9 = "EventUtils.ZXW_ACTION_UPDATE_GPS_TIME"
            boolean r9 = r4.equals(r9)
            if (r9 == 0) goto L_0x1b8b
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.checkNetworkAvailable()
            if (r0 == 0) goto L_0x1b77
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r8 = "android.intent.action.TIME_SET"
            r6.<init>(r8)
            android.os.UserHandle r8 = android.os.UserHandle.ALL
            r0.sendBroadcastAsUser(r6, r8)
            goto L_0x16b2
        L_0x1b77:
            java.lang.String r0 = "update time by gps"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.GPSMonitor r0 = r0.getGPSMonitor()
            r6 = 1
            r0.setUpdateTime(r6)
            r0.getLocation()
            goto L_0x16b2
        L_0x1b8b:
            java.lang.String r9 = "EventUtils.ZXW_ACTION_KSW_END_MCU_LOGCAT"
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x1ba8
            java.lang.String r0 = "onReceive "
            android.util.Log.d(r15, r0)
            r0 = 10
            byte[] r0 = new byte[r0]
            r0 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0} // fill-array
            android.serialport.SerialPortManager r6 = android.serialport.SerialPortManager.instance()
            r6.sendData(r0)
            goto L_0x16b2
        L_0x1ba8:
            java.lang.String r9 = "com.szchoiceway.action.mode_title_change"
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x1be4
            r0 = 99
            java.lang.String r6 = "com.szchoiceway.action.mode_title_change_extra"
            int r0 = r3.getIntExtra(r6, r0)
            java.lang.String r6 = "com.szchoiceway.action.mode_title_change_screen_extra"
            r8 = 0
            boolean r6 = r3.getBooleanExtra(r6, r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "mode = "
            r8.append(r9)
            r8.append(r0)
            java.lang.String r9 = ", isPrimary = "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r15, r8)
            if (r6 == 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            r6.setCurrentPage(r0)
            goto L_0x16b2
        L_0x1be4:
            java.lang.String r9 = "com.szchoiceway.eventcenter.touch_calibration"
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x1bf3
            java.lang.String r0 = "onReceive com.szchoiceway.eventcenter.touch_calibration"
            android.util.Log.d(r15, r0)
            goto L_0x16b2
        L_0x1bf3:
            java.lang.String r9 = "ZXW_ACTION_RESTART_ZXWMEDIA"
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x1c02
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.restartZXWMedia()
            goto L_0x16b2
        L_0x1c02:
            java.lang.String r9 = "com.atelectronic.atavm3d.tosys"
            boolean r9 = r9.equals(r4)
            if (r9 == 0) goto L_0x1cda
            java.lang.String r9 = "carinfo"
            java.lang.Object r9 = r3.getExtra(r9)
            if (r9 == 0) goto L_0x1c1d
            java.lang.String r9 = "carinfo"
            java.lang.Object r9 = r3.getExtra(r9)
            java.lang.String r9 = r9.toString()
            goto L_0x1c1e
        L_0x1c1d:
            r9 = r6
        L_0x1c1e:
            r9.hashCode()
            int r10 = r9.hashCode()
            switch(r10) {
                case 3089326: goto L_0x1c77;
                case 102970646: goto L_0x1c6c;
                case 106370576: goto L_0x1c61;
                case 108270342: goto L_0x1c56;
                case 109641799: goto L_0x1c4b;
                case 1449633563: goto L_0x1c40;
                case 1817050621: goto L_0x1c35;
                case 2119462413: goto L_0x1c2a;
                default: goto L_0x1c28;
            }
        L_0x1c28:
            goto L_0x1c81
        L_0x1c2a:
            java.lang.String r10 = "steering_wheel"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x1c33
            goto L_0x1c81
        L_0x1c33:
            r8 = 7
            goto L_0x1c81
        L_0x1c35:
            java.lang.String r0 = "sync_all"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1c3e
            goto L_0x1c81
        L_0x1c3e:
            r8 = 6
            goto L_0x1c81
        L_0x1c40:
            java.lang.String r0 = "gear_box"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1c49
            goto L_0x1c81
        L_0x1c49:
            r8 = 5
            goto L_0x1c81
        L_0x1c4b:
            java.lang.String r0 = "speed"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1c54
            goto L_0x1c81
        L_0x1c54:
            r8 = 4
            goto L_0x1c81
        L_0x1c56:
            java.lang.String r0 = "radar"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1c5f
            goto L_0x1c81
        L_0x1c5f:
            r8 = 3
            goto L_0x1c81
        L_0x1c61:
            java.lang.String r0 = "p_key"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1c6a
            goto L_0x1c81
        L_0x1c6a:
            r8 = 2
            goto L_0x1c81
        L_0x1c6c:
            java.lang.String r0 = "light"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1c75
            goto L_0x1c81
        L_0x1c75:
            r8 = 1
            goto L_0x1c81
        L_0x1c77:
            java.lang.String r0 = "door"
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x1c80
            goto L_0x1c81
        L_0x1c80:
            r8 = 0
        L_0x1c81:
            switch(r8) {
                case 0: goto L_0x1caf;
                case 1: goto L_0x1ca9;
                case 2: goto L_0x1ca3;
                case 3: goto L_0x1c9d;
                case 4: goto L_0x1c97;
                case 5: goto L_0x1c91;
                case 6: goto L_0x1c8b;
                case 7: goto L_0x1c85;
                default: goto L_0x1c84;
            }
        L_0x1c84:
            goto L_0x1cb4
        L_0x1c85:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendWheelMessageToLD()
            goto L_0x1cb4
        L_0x1c8b:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendAllMessageToLD()
            goto L_0x1cb4
        L_0x1c91:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendGearMessageToLD()
            goto L_0x1cb4
        L_0x1c97:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendSpeedMessageToLD()
            goto L_0x1cb4
        L_0x1c9d:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendRadarMessageToLD()
            goto L_0x1cb4
        L_0x1ca3:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendPMessageToLD()
            goto L_0x1cb4
        L_0x1ca9:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendLightMessageToLD()
            goto L_0x1cb4
        L_0x1caf:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendDoorMessageToLD()
        L_0x1cb4:
            java.lang.String r0 = "app_status"
            java.lang.Object r0 = r3.getExtra(r0)
            if (r0 == 0) goto L_0x1cc6
            java.lang.String r0 = "app_status"
            java.lang.Object r0 = r3.getExtra(r0)
            java.lang.String r6 = r0.toString()
        L_0x1cc6:
            java.lang.String r0 = "readyok"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x16b2
            java.lang.String r0 = "360初始化完成"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.readyOk360()
            goto L_0x16b2
        L_0x1cda:
            java.lang.String r0 = "com.carletter.link.sendKeyCode"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1cf5
            java.lang.String r0 = "onReceive letter keyCode"
            android.util.Log.d(r15, r0)
            java.lang.String r0 = "com.carletter.link.sendKeyCode_extra"
            r6 = 0
            int r0 = r3.getIntExtra(r0, r6)
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext
            r8.sendLetterKeyCode(r0)
            goto L_0x16b2
        L_0x1cf5:
            r6 = 0
            java.lang.String r0 = "android.net.wifi.WIFI_AP_STATE_CHANGED"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1d45
            java.lang.String r0 = "wifi_state"
            int r0 = r3.getIntExtra(r0, r6)
            r6 = 13
            if (r0 != r6) goto L_0x1d16
            java.lang.String r6 = "onReceive WIFI_AP_STATE_ENABLED"
            android.util.Log.d(r15, r6)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r6.mSysProviderOpt
            java.lang.String r8 = "ZXW_ACTION_WIFI_AP_SWITCH"
            r6.updateRecord(r8, r7)
        L_0x1d16:
            r6 = 11
            if (r0 != r6) goto L_0x16b2
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "onReceive WIFI_AP_STATE_DISABLED ksw_m_b_acc_off = "
            r0.append(r6)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            boolean r6 = r6.ksw_m_b_acc_off
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.ksw_m_b_acc_off
            if (r0 != 0) goto L_0x16b2
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            java.lang.String r6 = "ZXW_ACTION_WIFI_AP_SWITCH"
            java.lang.String r8 = "0"
            r0.updateRecord(r6, r8)
            goto L_0x16b2
        L_0x1d45:
            java.lang.String r0 = "ZXW_ACTION_SET_RESOLUTION_BY_DENSITY"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1d70
            r0 = 240(0xf0, float:3.36E-43)
            java.lang.String r6 = "ZXW_ACTION_SET_RESOLUTION_BY_DENSITY_EXTRA"
            int r0 = r3.getIntExtra(r6, r0)
            r6 = 160(0xa0, float:2.24E-43)
            if (r0 != r6) goto L_0x1d5c
            java.lang.String r0 = "1280x480"
            goto L_0x1d5e
        L_0x1d5c:
            java.lang.String r0 = "1920x720"
        L_0x1d5e:
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r6 = r6.mSysProviderOpt
            java.lang.String r8 = "RESOLUTION"
            r6.updateRecord(r8, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.ConfigUtil r0 = r0.configUtil
            r0.checkConfig()
            goto L_0x16b2
        L_0x1d70:
            java.lang.String r0 = "ZXW_TEST_ACTION_SET_DENSITY"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1da1
            r0 = 160(0xa0, float:2.24E-43)
            java.lang.String r6 = "ZXW_TEST_ACTION_SET_DENSITY_EXTRA"
            int r0 = r3.getIntExtra(r6, r0)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "onReceive test action set densityDpi dpi = "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r15, r6)
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            android.content.ContentResolver r6 = r6.getContentResolver()
            java.lang.String r8 = "display_density_forced"
            android.provider.Settings.Secure.putInt(r6, r8, r0)
            goto L_0x16b2
        L_0x1da1:
            java.lang.String r0 = "com.szchoiceway.eventcenter.test_ap"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1db1
            java.lang.String r0 = "extra"
            r6 = 0
            r3.getIntExtra(r0, r6)
            goto L_0x16b2
        L_0x1db1:
            java.lang.String r0 = "carletter.action.wired.carplay.state"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1dbb
            goto L_0x16b2
        L_0x1dbb:
            java.lang.String r0 = "com.szchoiceway.eventcenter.test_check_config"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1dcf
            com.szchoiceway.eventcenter.ConfigUtil r0 = new com.szchoiceway.eventcenter.ConfigUtil
            com.szchoiceway.eventcenter.EventService r6 = r1.mContext
            r0.<init>(r6)
            r0.checkConfig()
            goto L_0x16b2
        L_0x1dcf:
            java.lang.String r0 = "ZXW_ACTION_UPDATE_BENZ_CONTROL_DATA_RECEIVE"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1de4
            r6 = r19
            java.util.ArrayList r0 = r3.getIntegerArrayListExtra(r6)
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext
            r8.kswSendBenzControlKey(r0)
            goto L_0x1ee8
        L_0x1de4:
            r6 = r19
            java.lang.String r0 = "ZXW_TEST_ACTION_READ_TOUCH_PARAM"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1e54
            java.lang.String r0 = "onReceive ZXW_TEST_ACTION_READ_TOUCH_PARAM"
            android.util.Log.d(r15, r0)
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "/mnt/privdata1/"
            r8.append(r9)
            java.lang.String r9 = "touchParam.txt"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r0.<init>(r8)
            boolean r8 = r0.exists()
            if (r8 == 0) goto L_0x1ee8
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x1e4e }
            r8.<init>(r0)     // Catch:{ IOException -> 0x1e4e }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x1e4e }
            r0.<init>(r8)     // Catch:{ IOException -> 0x1e4e }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ IOException -> 0x1e4e }
            r9.<init>(r0)     // Catch:{ IOException -> 0x1e4e }
            java.lang.StringBuffer r10 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x1e4e }
            r10.<init>()     // Catch:{ IOException -> 0x1e4e }
        L_0x1e25:
            java.lang.String r11 = r9.readLine()     // Catch:{ IOException -> 0x1e4e }
            if (r11 == 0) goto L_0x1e2f
            r10.append(r11)     // Catch:{ IOException -> 0x1e4e }
            goto L_0x1e25
        L_0x1e2f:
            r9.close()     // Catch:{ IOException -> 0x1e4e }
            r0.close()     // Catch:{ IOException -> 0x1e4e }
            r8.close()     // Catch:{ IOException -> 0x1e4e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x1e4e }
            r0.<init>()     // Catch:{ IOException -> 0x1e4e }
            java.lang.String r8 = "touchParam = "
            r0.append(r8)     // Catch:{ IOException -> 0x1e4e }
            r0.append(r10)     // Catch:{ IOException -> 0x1e4e }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x1e4e }
            android.util.Log.d(r15, r0)     // Catch:{ IOException -> 0x1e4e }
            goto L_0x1ee8
        L_0x1e4e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x1ee8
        L_0x1e54:
            java.lang.String r0 = "ZXW_TEST_ACTION_REQUEST_AIR_DATA"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1e68
            java.lang.String r0 = "ZXW_TEST_ACTION_REQUEST_AIR_DATA"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.kswRequestAirData()
            goto L_0x1ee8
        L_0x1e68:
            java.lang.String r0 = "ZXW_ACTION_REBOOT_SYS_REBOOT"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1e7c
            com.szchoiceway.eventcenter.View.DialogRebootWithoutTime r0 = new com.szchoiceway.eventcenter.View.DialogRebootWithoutTime
            com.szchoiceway.eventcenter.EventService r8 = r1.mContext
            r0.<init>(r8)
            r0.showDialog()
            goto L_0x1ee8
        L_0x1e7c:
            java.lang.String r0 = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_ENTER_THIRD_MEDIA"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x1ee8
            java.lang.String r0 = "com.szchoiceway.eventcenter.EventUtils.ZXW_ACTION_ENTER_THIRD_MEDIA_EXTRA"
            r8 = 0
            int r0 = r3.getIntExtra(r0, r8)
            if (r0 == 0) goto L_0x1e99
            r8 = 1
            if (r0 == r8) goto L_0x1e91
            goto L_0x1ee8
        L_0x1e91:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r8 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_MOVIE
            r0.sendMode(r8)
            goto L_0x1ee8
        L_0x1e99:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r8 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_MUSIC
            r0.sendMode(r8)
            goto L_0x1ee8
        L_0x1ea1:
            r6 = r19
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.FloatingWindowManager r0 = r0.mFloatingWindowManager
            if (r0 == 0) goto L_0x1ee8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.FloatingWindowManager r0 = r0.mFloatingWindowManager
            r0.showTipView()
            goto L_0x1ee8
        L_0x1eb1:
            r6 = r19
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            java.lang.String r8 = "factory_reset..."
            r9 = 1
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r8, r9)
            r0.show()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            boolean r0 = r0.bIsResetting
            if (r0 != 0) goto L_0x1ee8
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.bIsResetting = r9
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r3 = "com.szchoiceway.eventcenter.action.BackupFinished"
            r0.<init>(r3)
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            r3.sendBroadcast(r0)
            goto L_0x1ee9
        L_0x1ed6:
            r6 = r19
            r5 = r20
            r7 = r21
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            if (r0 == 0) goto L_0x1ee8
            r0.notify8902McuUpdateTime()
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.updateTimerInfor()
        L_0x1ee8:
            r0 = r3
        L_0x1ee9:
            java.lang.String r3 = "com.megaview.avm.window_status"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x1f19
            java.lang.String r2 = "app_status"
            r3 = 0
            int r0 = r0.getIntExtra(r2, r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onReceive ld_window_status extra = "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r15, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r3 = 1
            if (r0 != r3) goto L_0x1f13
            r11 = 1
            goto L_0x1f14
        L_0x1f13:
            r11 = 0
        L_0x1f14:
            r2.show360Dialog(r11)
            goto L_0x25a4
        L_0x1f19:
            java.lang.String r3 = "com.ivicar.avm.action.APP_STATE"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x1f49
            java.lang.String r2 = "state"
            r3 = 0
            int r0 = r0.getIntExtra(r2, r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onReceive xyq_window_status extra = "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r15, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r3 = 1
            if (r0 != r3) goto L_0x1f43
            r11 = 1
            goto L_0x1f44
        L_0x1f43:
            r11 = 0
        L_0x1f44:
            r2.show360Dialog(r11)
            goto L_0x25a4
        L_0x1f49:
            java.lang.String r3 = "ZXW_KSW_SEND_MODE_0X67"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x1f76
            java.lang.String r2 = "ZXW_KSW_SEND_MODE_0X67_EXTRA"
            r3 = 6
            int r0 = r0.getIntExtra(r2, r3)
            r2 = 40
            if (r0 == r2) goto L_0x1f6c
            r2 = 55
            if (r0 == r2) goto L_0x1f62
            goto L_0x25a4
        L_0x1f62:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r2 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_THIRD_APP
            r3 = 0
            r0.sendKSW_0x00_0x67(r2, r3)
            goto L_0x25a4
        L_0x1f6c:
            r3 = 0
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils$eSrcMode r2 = com.szchoiceway.eventcenter.EventUtils.eSrcMode.SRC_AUX
            r0.sendKSW_0x00_0x67(r2, r3)
            goto L_0x25a4
        L_0x1f76:
            java.lang.String r3 = "ZXW_SET_TOUCH_TYPE"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x1f8b
            java.lang.String r2 = "ZXW_SET_TOUCH_TYPE_EXTRA"
            java.lang.String r0 = r0.getStringExtra(r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.setTouchInfor(r0)
            goto L_0x25a4
        L_0x1f8b:
            java.lang.String r3 = "TEST_SEND_CARPLAY_KEYCODE"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x1fa1
            java.lang.String r2 = "TEST_SEND_CARPLAY_KEYCODE_DATA"
            r3 = 0
            int r0 = r0.getIntExtra(r2, r3)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.sendKeyDownUpSync(r0)
            goto L_0x25a4
        L_0x1fa1:
            java.lang.String r3 = r0.getAction()
            java.lang.String r8 = "android.media.VOLUME_CHANGED_ACTION"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x1fe4
            java.lang.String r2 = "android.media.EXTRA_VOLUME_STREAM_TYPE"
            r3 = 3
            int r0 = r0.getIntExtra(r2, r3)
            if (r0 == 0) goto L_0x1fdb
            if (r0 == r3) goto L_0x1fd2
            r2 = 4
            if (r0 == r2) goto L_0x1fc9
            r2 = 5
            if (r0 == r2) goto L_0x1fc0
            goto L_0x25a4
        L_0x1fc0:
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            android.media.AudioManager r2 = r2.mAudioMgr
            r2.getStreamVolume(r0)
            goto L_0x25a4
        L_0x1fc9:
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            android.media.AudioManager r2 = r2.mAudioMgr
            r2.getStreamVolume(r0)
            goto L_0x25a4
        L_0x1fd2:
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            android.media.AudioManager r2 = r2.mAudioMgr
            r2.getStreamVolume(r0)
            goto L_0x25a4
        L_0x1fdb:
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            android.media.AudioManager r2 = r2.mAudioMgr
            r2.getStreamVolume(r0)
            goto L_0x25a4
        L_0x1fe4:
            java.lang.String r3 = "TEST_ACTION_SET_VOLUME"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2002
            java.lang.String r2 = "TEST_ACTION_SET_VOLUME_TYPE"
            r3 = 0
            int r2 = r0.getIntExtra(r2, r3)
            java.lang.String r4 = "TEST_ACTION_SET_VOLUME_DATA"
            int r0 = r0.getIntExtra(r4, r3)
            com.szchoiceway.eventcenter.EventService r4 = r1.mContext
            android.media.AudioManager r4 = r4.mAudioMgr
            r4.setStreamVolume(r2, r0, r3)
            goto L_0x25a4
        L_0x2002:
            java.lang.String r3 = "TEST_ACTION_GET_TOP_ACTIVITY"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x204e
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r2.getSystemService(r0)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            r2 = 1
            java.util.List r0 = r0.getRunningTasks(r2)
            if (r0 == 0) goto L_0x25a4
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x25a4
            r2 = 0
            java.lang.Object r0 = r0.get(r2)
            android.app.ActivityManager$RunningTaskInfo r0 = (android.app.ActivityManager.RunningTaskInfo) r0
            android.content.ComponentName r0 = r0.topActivity
            java.lang.String r2 = r0.getPackageName()
            java.lang.String r0 = r0.getClassName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "TEST_ACTION_GET_TOP_ACTIVITY topPackageName = "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = ", topActivity = "
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r15, r0)
            goto L_0x25a4
        L_0x204e:
            java.lang.String r3 = "com.zjinnova.zlink.action.ENABLE_AP"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2063
            java.lang.String r0 = "onReceive ACTION_CARPLAY_ENABLE_AP"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 1
            r0.setWifiApEnabled(r2)
            goto L_0x25a4
        L_0x2063:
            java.lang.String r3 = "com.zjinnova.zlink.action.DISABLE_AP"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2078
            java.lang.String r0 = "onReceive ACTION_CARPLAY_DISABLE_AP"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 0
            r0.setWifiApEnabled(r2)
            goto L_0x25a4
        L_0x2078:
            java.lang.String r3 = "TEST_KILL_HICAR"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2087
            java.lang.String r0 = "com.huawei"
            r1.killProcessByPid(r0)
            goto L_0x25a4
        L_0x2087:
            java.lang.String r3 = "ZXW_LAUNCHER_ACTION_KILL_PROGRESS"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x209c
            java.lang.String r2 = "packageName"
            java.lang.String r0 = r0.getStringExtra(r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.clearSoundPid(r0)
            goto L_0x25a4
        L_0x209c:
            java.lang.String r3 = "ZXW_ACTION_SEND_THIRD_MEDIA_SOURCE"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x20ab
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.sendKSW_0x00_0x67_third()
            goto L_0x25a4
        L_0x20ab:
            java.lang.String r3 = "ZXW_ACTION_VOLUME_ADD"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x20bb
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 1
            r0.changeLRCopilotVolume(r2)
            goto L_0x25a4
        L_0x20bb:
            java.lang.String r3 = "ZXW_ACTION_VOLUME_REDUCE"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x20cb
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r3 = 0
            r0.changeLRCopilotVolume(r3)
            goto L_0x25a4
        L_0x20cb:
            r3 = 0
            java.lang.String r8 = "txz_care_test"
            boolean r8 = r8.equals(r4)
            if (r8 == 0) goto L_0x21b2
            java.lang.String r4 = "key"
            int r4 = r0.getIntExtra(r4, r3)
            int r0 = r0.getIntExtra(r6, r3)
            java.lang.String r3 = "com.szchoiceway.ACTION_TXZ_VOICE__BROADCAST_VALUE"
            java.lang.String r5 = "com.szchoiceway.ACTION_TXZ_VOICE__BROADCAST_KEY"
            java.lang.String r6 = "com.szchoiceway.ACTION_TXZ_VOICE__BROADCAST"
            if (r4 == 0) goto L_0x218c
            r7 = 1
            if (r4 == r7) goto L_0x2166
            r8 = 2
            if (r4 == r8) goto L_0x2140
            r7 = 3
            if (r4 == r7) goto L_0x211a
            r9 = 4
            if (r4 == r9) goto L_0x20f4
            goto L_0x25a4
        L_0x20f4:
            android.content.Intent r4 = new android.content.Intent
            r4.<init>(r6)
            java.lang.String r6 = "temperature"
            r4.putExtra(r5, r6)
            r4.putExtra(r3, r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "sendTxzIntent temperature "
            r3.append(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r15, r0)
            r2.sendBroadcast(r4)
            goto L_0x25a4
        L_0x211a:
            android.content.Intent r4 = new android.content.Intent
            r4.<init>(r6)
            java.lang.String r6 = "speed"
            r4.putExtra(r5, r6)
            r4.putExtra(r3, r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "sendTxzIntent speed "
            r3.append(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r15, r0)
            r2.sendBroadcast(r4)
            goto L_0x25a4
        L_0x2140:
            android.content.Intent r4 = new android.content.Intent
            r4.<init>(r6)
            java.lang.String r6 = "oil"
            r4.putExtra(r5, r6)
            r4.putExtra(r3, r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "sendTxzIntent oil "
            r3.append(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r15, r0)
            r2.sendBroadcast(r4)
            goto L_0x25a4
        L_0x2166:
            android.content.Intent r4 = new android.content.Intent
            r4.<init>(r6)
            java.lang.String r6 = "seatBelt"
            r4.putExtra(r5, r6)
            r4.putExtra(r3, r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "sendTxzIntent seatBelt "
            r3.append(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r15, r0)
            r2.sendBroadcast(r4)
            goto L_0x25a4
        L_0x218c:
            android.content.Intent r4 = new android.content.Intent
            r4.<init>(r6)
            java.lang.String r6 = "door"
            r4.putExtra(r5, r6)
            r4.putExtra(r3, r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "sendTxzIntent door "
            r3.append(r5)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r15, r0)
            r2.sendBroadcast(r4)
            goto L_0x25a4
        L_0x21b2:
            r8 = 2
            r9 = 4
            java.lang.String r3 = "ZXW_ACTION_BT_SERVICE_ON_DESTROY"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x21ce
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r2 = 3013(0xbc5, float:4.222E-42)
            r0.removeMessages(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r0.sendEmptyMessage(r2)
            goto L_0x25a4
        L_0x21ce:
            java.lang.String r3 = "ZXW_ACTION_KSW_THEME_CHANGE"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x220b
            java.lang.String r0 = "onReceive ZXW_ACTION_KSW_THEME_CHANGE"
            android.util.Log.d(r15, r0)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iUiIndex
            r2 = 5
            if (r0 != r2) goto L_0x25a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 0
            r0.showLRBottomView(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            int r0 = r0.m_iModeSet
            r2 = 39
            if (r0 != r2) goto L_0x21f6
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.createLRCBottomView()
            goto L_0x21fb
        L_0x21f6:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.createLRBottomView()
        L_0x21fb:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            com.szchoiceway.eventcenter.-$$Lambda$EvtModel$qdFsJDs8VIx4DoNs7o0PO9W5--w r2 = new com.szchoiceway.eventcenter.-$$Lambda$EvtModel$qdFsJDs8VIx4DoNs7o0PO9W5--w
            r2.<init>()
            r3 = 200(0xc8, double:9.9E-322)
            r0.postDelayed(r2, r3)
            goto L_0x25a4
        L_0x220b:
            java.lang.String r3 = "com.android.launcher3.IN_RECENT"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2224
            r3 = 0
            int r0 = r0.getIntExtra(r5, r3)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r10 = 1
            if (r0 != r10) goto L_0x221f
            r11 = 1
            goto L_0x2220
        L_0x221f:
            r11 = 0
        L_0x2220:
            r2.mInRecentTask = r11
            goto L_0x25a4
        L_0x2224:
            r3 = 0
            r10 = 1
            java.lang.String r11 = "com.android.launcher3.SPLIT_SCREEN"
            boolean r11 = r11.equals(r4)
            if (r11 == 0) goto L_0x223d
            int r0 = r0.getIntExtra(r5, r3)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            if (r0 != r10) goto L_0x2238
            r11 = 1
            goto L_0x2239
        L_0x2238:
            r11 = 0
        L_0x2239:
            r2.mSplitScreen = r11
            goto L_0x25a4
        L_0x223d:
            java.lang.String r11 = "com.szchoiceway.eventcenter.ZXW_ACTION_KSW_VIDEOPLAYER_FULL_SCREEN"
            boolean r11 = r11.equals(r4)
            if (r11 == 0) goto L_0x2254
            int r0 = r0.getIntExtra(r5, r3)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            if (r0 != r10) goto L_0x224f
            r11 = 1
            goto L_0x2250
        L_0x224f:
            r11 = 0
        L_0x2250:
            r2.mInVideoFullScreen = r11
            goto L_0x25a4
        L_0x2254:
            java.lang.String r11 = "ZXW_ACTION_KSW_SHOW_SOFT_KEYBOARD"
            boolean r11 = r11.equals(r4)
            if (r11 == 0) goto L_0x2283
            int r0 = r0.getIntExtra(r5, r3)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            if (r0 != r10) goto L_0x2266
            r11 = 1
            goto L_0x2267
        L_0x2266:
            r11 = 0
        L_0x2267:
            r2.mShowSoftKeyboard = r11
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "onReceive ZXW_ACTION_KSW_SHOW_SOFT_KEYBOARD showKeyboard = "
            r0.append(r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            boolean r2 = r2.mShowSoftKeyboard
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r15, r0)
            goto L_0x25a4
        L_0x2283:
            java.lang.String r3 = "ZXW_ACTION_DELAY_AUX"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x22ac
            java.lang.String r2 = "ZXW_ACTION_DELAY_AUX_DATA"
            r3 = 0
            int r0 = r0.getIntExtra(r2, r3)
            android.os.Message r2 = new android.os.Message
            r2.<init>()
            r3 = 3033(0xbd9, float:4.25E-42)
            r2.what = r3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.obj = r0
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r3 = 1000(0x3e8, double:4.94E-321)
            r0.sendMessageDelayed(r2, r3)
            goto L_0x25a4
        L_0x22ac:
            java.lang.String r3 = "ZXW_ACTION_START_EQ"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2300
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r2 = r2.mSysProviderOpt
            java.lang.String r3 = "KSW_HAVE_EQ"
            r4 = 1
            boolean r2 = r2.getRecordBoolean(r3, r4)
            if (r2 == 0) goto L_0x22cc
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            java.lang.String r2 = "com.szchoiceway.equalizer"
            java.lang.String r3 = "com.szchoiceway.equalizer.MainActivity"
            com.szchoiceway.eventcenter.EventUtils.startActivityIfNotRuning(r0, r2, r3)
            goto L_0x25a4
        L_0x22cc:
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            int r2 = r2.m_iUiIndex
            switch(r2) {
                case 1: goto L_0x22d7;
                case 2: goto L_0x22d5;
                case 3: goto L_0x22d7;
                case 4: goto L_0x22d3;
                case 5: goto L_0x22d5;
                case 6: goto L_0x22d3;
                case 7: goto L_0x22d5;
                default: goto L_0x22d3;
            }
        L_0x22d3:
            r11 = 3
            goto L_0x22d8
        L_0x22d5:
            r11 = 2
            goto L_0x22d8
        L_0x22d7:
            r11 = 4
        L_0x22d8:
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.lang.String r3 = "GotoPageNum"
            r2.putInt(r3, r11)
            r0.putExtras(r2)
            android.content.ComponentName r2 = new android.content.ComponentName
            java.lang.String r3 = "com.szchoiceway.settings.MainActivity"
            r4 = r18
            r2.<init>(r4, r3)
            r0.setComponent(r2)
            r0.setPackage(r4)
            r2 = 270532608(0x10200000, float:3.1554436E-29)
            r0.setFlags(r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.startActivity(r0)
            goto L_0x25a4
        L_0x2300:
            java.lang.String r3 = "ZXW_ACTION_REQUEST_BT_STATUS"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2327
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r2 = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW"
            r0.<init>(r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            int r2 = r2.getBTStatus()
            r3 = 3
            if (r2 < r3) goto L_0x231a
            r11 = 1
            goto L_0x231b
        L_0x231a:
            r11 = 0
        L_0x231b:
            java.lang.String r2 = "com.choiceway.eventcenter.EventUtils.KSW_ZXW_BT_CONNECED_SHOW_VIEW_DATA"
            r0.putExtra(r2, r11)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.sendBroadcast(r0)
            goto L_0x25a4
        L_0x2327:
            java.lang.String r3 = "360_test"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2347
            r0 = 9
            byte[] r0 = new byte[r0]
            r0 = {0, 1, 16, 32, 48, 64, 80, 96, 112} // fill-array
            android.content.Intent r3 = new android.content.Intent
            java.lang.String r4 = "com.szchoiceway.eventcenter.EventUtils.MCU_CAR_CAN_RADAR_INFO"
            r3.<init>(r4)
            java.lang.String r4 = "EventUtils.CAR_CAN_DATA"
            r3.putExtra(r4, r0)
            r2.sendBroadcast(r3)
            goto L_0x25a4
        L_0x2347:
            java.lang.String r3 = "360_test2"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x2367
            r0 = 9
            byte[] r0 = new byte[r0]
            r0 = {0, 0, 0, 0, 0, 0, 0, 0, 0} // fill-array
            android.content.Intent r3 = new android.content.Intent
            java.lang.String r4 = "com.szchoiceway.eventcenter.EventUtils.MCU_CAR_CAN_RADAR_INFO"
            r3.<init>(r4)
            java.lang.String r4 = "EventUtils.CAR_CAN_DATA"
            r3.putExtra(r4, r0)
            r2.sendBroadcast(r3)
            goto L_0x25a4
        L_0x2367:
            java.lang.String r2 = "ZXW_ACTION_REFRESH_SMALL_CLOCK_INDEX"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x2381
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r2 = 3009(0xbc1, float:4.217E-42)
            r0.removeMessages(r2)
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r0.sendEmptyMessage(r2)
            goto L_0x25a4
        L_0x2381:
            java.lang.String r2 = "media_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x2390
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r0.copyMedia()
            goto L_0x25a4
        L_0x2390:
            java.lang.String r2 = "backcar_mute_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x23a7
            java.lang.String r0 = r0.getStringExtra(r6)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            boolean r0 = r7.equals(r0)
            r2.send360Status(r0)
            goto L_0x25a4
        L_0x23a7:
            java.lang.String r2 = "light_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x23fc
            java.lang.String r2 = "left"
            java.lang.String r2 = r0.getStringExtra(r2)
            boolean r2 = r7.equals(r2)
            java.lang.String r3 = "right"
            java.lang.String r0 = r0.getStringExtra(r3)
            boolean r0 = r7.equals(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "onReceive light_test left = "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r4 = ", right = "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r15, r3)
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            com.szchoiceway.eventcenter.CameraUtilXYQ r3 = r3.cameraUtilXYQ
            if (r3 == 0) goto L_0x23ec
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            com.szchoiceway.eventcenter.CameraUtilXYQ r3 = r3.cameraUtilXYQ
            r3.sendLightMessage(r2, r0)
        L_0x23ec:
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            com.szchoiceway.eventcenter.CameraUtil r3 = r3.cameraUtil
            if (r3 == 0) goto L_0x25a4
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            com.szchoiceway.eventcenter.CameraUtil r3 = r3.cameraUtil
            r5 = 0
            r3.sendLightMessage2(r5, r2, r0)
            goto L_0x25a4
        L_0x23fc:
            r5 = 0
            java.lang.String r2 = "hw_test_enter"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x2418
            int r0 = r0.getIntExtra(r6, r5)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            com.szchoiceway.eventcenter.BackcarEventHw r2 = r2.mBackcarEvtHw
            if (r2 == 0) goto L_0x25a4
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            com.szchoiceway.eventcenter.BackcarEventHw r2 = r2.mBackcarEvtHw
            r2.startCamera(r0, r5)
            goto L_0x25a4
        L_0x2418:
            java.lang.String r2 = "hw_test_exit"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x242f
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.BackcarEventHw r0 = r0.mBackcarEvtHw
            if (r0 == 0) goto L_0x25a4
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.BackcarEventHw r0 = r0.mBackcarEvtHw
            r0.stopCamera()
            goto L_0x25a4
        L_0x242f:
            java.lang.String r2 = "switch_9211"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x2458
            java.lang.String r0 = r0.getStringExtra(r6)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onReceive switch_9211 data = "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r15, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            com.szchoiceway.eventcenter.ConfigUtil r2 = r2.configUtil
            r2.switch9211(r0)
            goto L_0x25a4
        L_0x2458:
            java.lang.String r2 = "allpath_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x2467
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils.isMcuUpgradeFileExist(r0)
            goto L_0x25a4
        L_0x2467:
            java.lang.String r2 = "top_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x2499
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            java.lang.String r0 = com.szchoiceway.eventcenter.EventUtils.getTopPackageNameUnfiltered(r0)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            java.lang.String r2 = com.szchoiceway.eventcenter.EventUtils.getTopActivityNameUnfiltered(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "onReceive top_test pkg = "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = ", activity = "
            r3.append(r0)
            r3.append(r2)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r15, r0)
            goto L_0x25a4
        L_0x2499:
            java.lang.String r2 = "decoder_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x24ab
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            r2 = 16
            r3 = 0
            r0.sendKSW_0x00_0x68(r2, r3)
            goto L_0x25a4
        L_0x24ab:
            r3 = 0
            java.lang.String r2 = "air_test"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 == 0) goto L_0x24e5
            int r0 = r0.getIntExtra(r6, r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onReceive air_test data = "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r15, r2)
            r2 = 1
            if (r0 != r2) goto L_0x24da
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r2 = 4134(0x1026, float:5.793E-42)
            r0.sendEmptyMessage(r2)
            goto L_0x25a4
        L_0x24da:
            com.szchoiceway.eventcenter.EventService r0 = r1.mContext
            android.os.Handler r0 = r0.mEventHandler
            r2 = 4132(0x1024, float:5.79E-42)
            r0.sendEmptyMessage(r2)
            goto L_0x25a4
        L_0x24e5:
            java.lang.String r2 = "zlink_keycode_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x24fb
            java.lang.String r2 = "keyCode"
            r3 = 0
            int r0 = r0.getIntExtra(r2, r3)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            com.szchoiceway.eventcenter.EventUtils.sendZlinkKeyCode(r2, r0)
            goto L_0x25a4
        L_0x24fb:
            r3 = 0
            java.lang.String r2 = "bt_status_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x252b
            int r0 = r0.getIntExtra(r6, r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onreceive "
            r2.append(r3)
            r2.append(r4)
            java.lang.String r3 = " data = "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r15, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            r2.BTSpeakMode_MCU_KSW(r0)
            goto L_0x25a4
        L_0x252b:
            java.lang.String r2 = "360_radar_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x2570
            r2 = 0
            int r0 = r0.getIntExtra(r6, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "onreceive "
            r2.append(r3)
            r2.append(r4)
            java.lang.String r3 = " data = "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r15, r2)
            com.szchoiceway.eventcenter.EventService r2 = r1.mContext
            com.szchoiceway.eventcenter.CameraUtilXYQ r2 = r2.cameraUtilXYQ
            r25 = 0
            r16 = r2
            r17 = r0
            r18 = r0
            r19 = r0
            r20 = r0
            r21 = r0
            r22 = r0
            r23 = r0
            r24 = r0
            r16.sendRadarMessage(r17, r18, r19, r20, r21, r22, r23, r24, r25)
            goto L_0x25a4
        L_0x2570:
            java.lang.String r2 = "txz_icon_test"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x25a4
            r2 = 0
            int r0 = r0.getIntExtra(r6, r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "onreceive "
            r3.append(r5)
            r3.append(r4)
            java.lang.String r4 = " data = "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r15, r3)
            com.szchoiceway.eventcenter.EventService r3 = r1.mContext
            r4 = 1
            if (r0 != r4) goto L_0x25a0
            r11 = 1
            goto L_0x25a1
        L_0x25a0:
            r11 = 0
        L_0x25a1:
            com.szchoiceway.eventcenter.EventUtils.showTXZIcon(r3, r11)
        L_0x25a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.EvtModel.onReceive(android.content.Context, android.content.Intent):void");
    }

    public /* synthetic */ void lambda$onReceive$0$EvtModel() {
        if (!this.mContext.bZlinkCarplayPhoneOn && this.mContext.getBTStatus() <= 3) {
            this.mContext.sendKSW_0x00_0x67_third();
            this.mContext.setCurModeCallback(EventUtils.eSrcMode.SRC_CARPLAY.getIntValue(), this.mModeCallback);
        }
    }

    public /* synthetic */ void lambda$onReceive$1$EvtModel() {
        if (!this.mContext.bZlinkCarplayPhoneOn && this.mContext.getBTStatus() <= 3) {
            this.mContext.sendKSW_0x00_0x67_third();
            this.mContext.setCurModeCallback(EventUtils.eSrcMode.SRC_CARPLAY.getIntValue(), this.mModeCallback);
        }
    }

    public /* synthetic */ void lambda$onReceive$2$EvtModel() {
        this.mContext.showLRBottomView(true);
    }

    class MobilePhoneStateListener extends PhoneStateListener {
        public void onDataActivity(int i) {
        }

        public MobilePhoneStateListener(Looper looper) {
            super(looper);
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            int i;
            if (!signalStrength.isGsm()) {
                i = signalStrength.getCdmaLevel();
            } else {
                i = signalStrength.getLevel();
            }
            Log.i(EvtModel.TAG, "onSignalStrengthsChanged: level = " + i);
            EvtModel.this.mContext.refresh4gLevel(i);
            Intent intent = new Intent(EventUtils.ACTION_REFRESH_PHONE_SIGNAL);
            intent.putExtra(EventUtils.ACTION_REFRESH_PHONE_SIGNAL_DATA_LEVEL, i);
            EvtModel.this.mContext.sendBroadcast(intent);
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            Log.i(EvtModel.TAG, "onServiceStateChanged: ");
        }

        public void onDataConnectionStateChanged(int i, int i2) {
            Log.i(EvtModel.TAG, "onDataConnectionStateChanged: state = " + i + ", networkType = " + i2);
        }

        public void onCarrierNetworkChange(boolean z) {
            Log.i(EvtModel.TAG, "onCarrierNetworkChange: ");
        }
    }

    private void sendHiCarCallState(String str) {
        Intent intent = new Intent("com.choiceway.hicar");
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, str);
        this.mContext.sendBroadcast(intent);
    }

    public void SendBluetoothState(Context context, int i) {
        if (context != null) {
            String valueOf = String.valueOf(i);
            Intent intent = new Intent(SEND_ECAR_ACTION_EVENT);
            intent.putExtra(_CMD_, "BluetoothState");
            intent.putExtra(_TYPE_, _TYPE_STANDCMD_);
            intent.putExtra(_KEYS_, EventUtils.ZXW_ACTION_LIGHT_EXTRA);
            intent.putExtra(EventUtils.ZXW_ACTION_LIGHT_EXTRA, valueOf);
            context.sendBroadcast(intent);
        }
    }

    public static void SendCallState(Context context, int i) {
        if (context != null) {
            String valueOf = String.valueOf(i);
            Intent intent = new Intent(SEND_ECAR_ACTION_EVENT);
            intent.putExtra(_CMD_, "CallState");
            intent.putExtra(_TYPE_, _TYPE_STANDCMD_);
            intent.putExtra(_KEYS_, EventUtils.ZXW_ACTION_LIGHT_EXTRA);
            intent.putExtra(EventUtils.ZXW_ACTION_LIGHT_EXTRA, valueOf);
            context.sendBroadcast(intent);
        }
    }

    public static void MakeCall(Context context) {
        if (context != null) {
            Intent intent = new Intent(SEND_ECAR_ACTION_EVENT);
            intent.putExtra(_CMD_, "MakeCall");
            intent.putExtra(_TYPE_, _TYPE_STANDCMD_);
            intent.putExtra(_KEYS_, BuildConfig.FLAVOR);
            context.sendBroadcast(intent);
        }
    }

    public static void EntryApp(Context context, String str, String str2) {
        if (context != null) {
            Intent intent = new Intent(SEND_ECAR_ACTION_EVENT);
            intent.putExtra(_CMD_, "ENTRY_APP");
            intent.putExtra(_TYPE_, _TYPE_STANDCMD_);
            intent.putExtra(_KEYS_, "AppName,PackageName");
            intent.putExtra("AppName", str);
            intent.putExtra("PackageName", str2);
            context.sendBroadcast(intent);
        }
    }

    public static void startCarPlayService(Context context, boolean z) {
        Log.d(TAG, "startCarPlayService start");
        Intent intent = new Intent(EventUtils.ZLINK_MODE_PACKAGE_NAME);
        if (z) {
            intent.putExtra("command", "ACTION_ENTER");
        } else {
            intent.putExtra("command", "ACTION_EXIT");
        }
        context.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    public void requestFocus() {
        int i;
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (this.mAudioFocusRequest == null) {
                this.mAudioFocusRequest = new AudioFocusRequest.Builder(1).setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(this.mAudioFocusChange).build();
            }
            i = this.mAudioManager.requestAudioFocus(this.mAudioFocusRequest);
        } else {
            i = this.mAudioManager.requestAudioFocus(this.mAudioFocusChange, 3, 1);
        }
        Log.i(TAG, "requestFocus: result = " + i);
    }

    public void releaseAudioFocus() {
        if (Build.VERSION.SDK_INT >= 26) {
            AudioFocusRequest audioFocusRequest = this.mAudioFocusRequest;
            if (audioFocusRequest != null) {
                this.mAudioManager.abandonAudioFocusRequest(audioFocusRequest);
            }
        } else if (this.mAudioFocusRequest != null) {
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusChange);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0049 A[SYNTHETIC, Splitter:B:30:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0054 A[SYNTHETIC, Splitter:B:35:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void openAdb(boolean r4) {
        /*
            r3 = this;
            com.szchoiceway.eventcenter.EventService r0 = r3.mContext
            com.szchoiceway.eventcenter.SysProviderOpt r0 = r0.mSysProviderOpt
            if (r4 == 0) goto L_0x0009
            java.lang.String r1 = "1"
            goto L_0x000b
        L_0x0009:
            java.lang.String r1 = "0"
        L_0x000b:
            java.lang.String r2 = "KSW_USB_HOST_MODE"
            r0.updateRecord(r2, r1)
            if (r4 == 0) goto L_0x0015
            java.lang.String r4 = "host"
            goto L_0x0017
        L_0x0015:
            java.lang.String r4 = "peripheral"
        L_0x0017:
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/sys/devices/platform/soc/4e00000.ssusb/mode"
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0025
            return
        L_0x0025:
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0043 }
            r2.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0043 }
            byte[] r4 = r4.getBytes()     // Catch:{ IOException -> 0x0039 }
            r2.write(r4)     // Catch:{ IOException -> 0x0039 }
            goto L_0x003d
        L_0x0033:
            r4 = move-exception
            r1 = r2
            goto L_0x0052
        L_0x0036:
            r4 = move-exception
            r1 = r2
            goto L_0x0044
        L_0x0039:
            r4 = move-exception
            r4.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0036, all -> 0x0033 }
        L_0x003d:
            r2.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0051
        L_0x0041:
            r4 = move-exception
            goto L_0x0052
        L_0x0043:
            r4 = move-exception
        L_0x0044:
            r4.printStackTrace()     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0051
            r1.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0051:
            return
        L_0x0052:
            if (r1 == 0) goto L_0x005c
            r1.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.EvtModel.openAdb(boolean):void");
    }

    private void killProcessByPid(String str) {
        int i;
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.processName.startsWith("com.huawei")) {
                Log.d(TAG, "running process name = " + next.processName);
                Log.d(TAG, "running process pid = " + next.pid);
            }
            if (next.processName.startsWith(str)) {
                i = next.pid;
                break;
            }
        }
        if (i != -1) {
            Log.d(TAG, "killProcessByPid pid = " + i);
            Process.killProcess(i);
            return;
        }
        Log.d(TAG, "killProcessByPid getPid error");
    }
}
