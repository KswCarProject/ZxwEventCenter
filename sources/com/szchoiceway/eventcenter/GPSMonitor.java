package com.szchoiceway.eventcenter;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import com.example.mylibrary.BuildConfig;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class GPSMonitor {
    protected static final String TAG = "GPSMonitor";
    private static final int maxSatellites = 12;
    private long GpsTime;
    /* access modifiers changed from: private */
    public GpsStatus gpsStatus;
    /* access modifiers changed from: private */
    public boolean hasUpdateTime = false;
    /* access modifiers changed from: private */
    public Location location;
    private final LocationListener locationListener = new LocationListener() {
        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onProviderDisabled(String str) {
            Log.d(GPSMonitor.TAG, "onProviderDisabled");
        }

        public void onLocationChanged(Location location) {
            GPSMonitor.this.updateToNewLocation(location);
        }
    };
    /* access modifiers changed from: private */
    public LocationManager locationManager;
    private float mAltitude = 0.0f;
    private float mBear = 0.0f;
    private Context mContext;
    private float mGPSTimer = 0.0f;
    private float mLatitude = 0.0f;
    private float mLongitude = 0.0f;
    private float mSpeed = 0.0f;
    private String mTime = BuildConfig.FLAVOR;
    private final GpsStatus.Listener statusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int i) {
            if (GPSMonitor.this.locationManager != null) {
                if (!GPSMonitor.this.hasUpdateTime) {
                    for (String lastKnownLocation : GPSMonitor.this.locationManager.getProviders(true)) {
                        Location lastKnownLocation2 = GPSMonitor.this.locationManager.getLastKnownLocation(lastKnownLocation);
                        if (lastKnownLocation2 != null) {
                            Location unused = GPSMonitor.this.location = lastKnownLocation2;
                        }
                    }
                    if (GPSMonitor.this.location != null) {
                        GPSMonitor gPSMonitor = GPSMonitor.this;
                        gPSMonitor.updateToNewLocation(gPSMonitor.location);
                        boolean unused2 = GPSMonitor.this.hasUpdateTime = true;
                    }
                }
                if (i == 4) {
                    try {
                        GPSMonitor gPSMonitor2 = GPSMonitor.this;
                        GpsStatus unused3 = gPSMonitor2.gpsStatus = gPSMonitor2.locationManager.getGpsStatus((GpsStatus) null);
                    } catch (Exception e) {
                        Log.e(GPSMonitor.TAG, e.toString());
                    }
                }
            }
        }
    };
    private String strLocationInfor;
    private int timeType = 0;
    private boolean updateTime;

    public static int getMaxSatellites() {
        return 12;
    }

    public GpsStatus getGpsStatus() {
        return this.gpsStatus;
    }

    public float getmAltitude() {
        return this.mAltitude;
    }

    public float getmSpeed() {
        return this.mSpeed;
    }

    public float getmLongitude() {
        return this.mLongitude;
    }

    public float getmLatitude() {
        return this.mLatitude;
    }

    public float getmBear() {
        return this.mBear;
    }

    public float getmGPSTimer() {
        return this.mGPSTimer;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setUpdateTime(boolean z) {
        this.updateTime = z;
    }

    public boolean getUpdateTime() {
        return this.updateTime;
    }

    public GPSMonitor(Context context) {
        Log.i(TAG, "GPSMonitor: ");
        this.mContext = context;
        setUpdateTime(true);
        this.locationManager = (LocationManager) context.getSystemService("location");
        getLocation();
    }

    public void releaseGPSResource() {
        LocationManager locationManager2 = this.locationManager;
        if (locationManager2 != null) {
            locationManager2.removeUpdates(this.locationListener);
            this.locationManager.removeGpsStatusListener(this.statusListener);
            this.locationManager = null;
        }
    }

    public void getLocation() {
        Log.d(TAG, "getLocation");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(1);
        criteria.setSpeedRequired(true);
        String bestProvider = this.locationManager.getBestProvider(criteria, true);
        Iterator<String> it = this.locationManager.getProviders(true).iterator();
        while (true) {
            String str = bestProvider;
            while (it.hasNext()) {
                bestProvider = it.next();
                Location lastKnownLocation = this.locationManager.getLastKnownLocation(bestProvider);
                if (lastKnownLocation != null) {
                    this.location = lastKnownLocation;
                }
            }
            Log.d(TAG, "mProvider = " + str);
            Log.d(TAG, "location = " + this.location);
            updateToNewLocation(this.location);
            try {
                this.locationManager.requestLocationUpdates(str, 1000, 0.0f, this.locationListener);
                this.locationManager.addGpsStatusListener(this.statusListener);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateToNewLocation(Location location2) {
        this.timeType = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KESAIWEI_SYS_USER_TIME_TYPE, 0);
        if (location2 != null) {
            this.mBear = location2.getBearing();
            this.mLatitude = (float) location2.getLatitude();
            this.mLongitude = (float) location2.getLongitude();
            this.mSpeed = location2.getSpeed() * 3.7f;
            this.GpsTime = location2.getTime();
            Date date = new Date(this.GpsTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.mAltitude = (float) location2.getAltitude();
            this.mTime = simpleDateFormat.format(date);
            if (this.timeType == 1 && getUpdateTime()) {
                Log.d(TAG, "updateGPS time");
                SystemClock.setCurrentTimeMillis(this.GpsTime);
                this.mContext.sendBroadcast(new Intent("android.intent.action.TIME_SET"));
                if (Settings.Global.getString(this.mContext.getContentResolver(), "auto_time").contentEquals("0")) {
                    Settings.Global.putString(this.mContext.getContentResolver(), "auto_time", "1");
                }
                setUpdateTime(false);
            }
        }
    }

    private void updateWithNewLocation(Location location2) {
        String str;
        SystemClock.sleep(50);
        if (location2 != null) {
            Log.i("TAG", "location != null");
            location2.getLatitude();
            location2.getLongitude();
            Geocoder geocoder = new Geocoder(this.mContext, Locale.CHINA);
            Log.i(TAG, "updateWithNewLocation: present = " + Geocoder.isPresent());
            List<Address> list = null;
            try {
                list = geocoder.getFromLocation(location2.getLatitude(), location2.getLongitude(), 5);
                Log.i(TAG, "updateWithNewLocation: places.size() = " + list.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (list == null || list.size() <= 0) {
                str = BuildConfig.FLAVOR;
            } else {
                str = list.get(0).getAddressLine(0) + ", " + System.getProperty("line.separator") + list.get(0).getAddressLine(1) + ", " + list.get(0).getAddressLine(2);
            }
            this.strLocationInfor = str;
        } else {
            this.strLocationInfor = "无法获取地理信息";
        }
        Log.i("TAG", "您当前的位置是:" + this.strLocationInfor);
    }
}
