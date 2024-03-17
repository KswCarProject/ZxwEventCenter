package com.szchoiceway.eventcenter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.szchoiceway.musicplayer.IMusicPlayerService;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class MusicUtils {
    private static final String TAG = "MusicUtils";
    private static final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
    private static final BitmapFactory.Options sBitmapOptions;
    private static HashMap<Context, ServiceBinder> sConnectionMap = new HashMap<>();
    private static final String sExternalMediaUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString();
    public static IMusicPlayerService sService;

    private static Bitmap getDefaultArtwork(Context context) {
        return null;
    }

    static {
        BitmapFactory.Options options = new BitmapFactory.Options();
        sBitmapOptions = options;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inDither = false;
        options.outHeight = EventUtils.HANDLER_ORIGINAL_START_KSW;
        options.outWidth = EventUtils.HANDLER_BACKCAR_START_KSW;
    }

    public static class ServiceToken {
        ContextWrapper mWrappedContext;

        ServiceToken(ContextWrapper contextWrapper) {
            this.mWrappedContext = contextWrapper;
        }
    }

    public static ServiceToken bindToService(Activity activity) {
        Activity parent = activity.getParent();
        if (parent != null) {
            activity = parent;
        }
        ContextWrapper contextWrapper = new ContextWrapper(activity);
        contextWrapper.startService(new Intent(contextWrapper, EventService.class));
        Log.e(TAG, "Failed to bind to service");
        return null;
    }

    public static void unbindFromService(ServiceToken serviceToken) {
        if (serviceToken == null) {
            Log.e(TAG, "Trying to unbind with null token");
            return;
        }
        ContextWrapper contextWrapper = serviceToken.mWrappedContext;
        ServiceBinder remove = sConnectionMap.remove(contextWrapper);
        if (remove == null) {
            Log.e(TAG, "Trying to unbind for unknown Context");
            return;
        }
        contextWrapper.unbindService(remove);
        if (sConnectionMap.isEmpty()) {
            sService = null;
        }
    }

    private static class ServiceBinder implements ServiceConnection {
        ServiceConnection mCallback;

        ServiceBinder(ServiceConnection serviceConnection) {
            this.mCallback = serviceConnection;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicUtils.sService = IMusicPlayerService.Stub.asInterface(iBinder);
            ServiceConnection serviceConnection = this.mCallback;
            if (serviceConnection != null) {
                serviceConnection.onServiceConnected(componentName, iBinder);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            ServiceConnection serviceConnection = this.mCallback;
            if (serviceConnection != null) {
                serviceConnection.onServiceDisconnected(componentName);
            }
            MusicUtils.sService = null;
        }
    }

    public static String getProgressFromPosition(int i) {
        int i2 = i / 60;
        if ((i2 / 60) / 1000 > 0) {
            long j = (long) ((i / 3600000) % 24);
            return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(j), Long.valueOf((long) ((i / 60000) % 60)), Long.valueOf((long) ((i / 1000) % 60))});
        } else if (i2 / 1000 > 0) {
            return String.format("00:%02d:%02d", new Object[]{Long.valueOf((long) ((i / 60000) % 60)), Long.valueOf((long) ((i / 1000) % 60))});
        } else {
            return String.format("00:00:%02d", new Object[]{Long.valueOf((long) ((i / 1000) % 60))});
        }
    }

    public static String convFileByteSize(long j) {
        if (j >= 1073741824) {
            return String.format("%.02f GB", new Object[]{Float.valueOf(((float) j) / 1.07374182E9f)});
        } else if (j >= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            return String.format("%.02f MB", new Object[]{Float.valueOf(((float) j) / 1048576.0f)});
        } else if (j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return String.format("%.02f KB", new Object[]{Float.valueOf(((float) j) / 1024.0f)});
        } else {
            return String.format("%d B", new Object[]{Long.valueOf(j)});
        }
    }

    public static Bitmap getArtwork(Context context, long j, long j2) {
        return getArtwork(context, j, j2, true);
    }

    public static Bitmap getArtwork(Context context) {
        int i;
        Log.i(TAG, "getArtwork: sService = " + sService);
        IMusicPlayerService iMusicPlayerService = sService;
        if (iMusicPlayerService == null) {
            return null;
        }
        int i2 = 0;
        try {
            i = iMusicPlayerService.getSongID();
            try {
                i2 = sService.getAblumID();
            } catch (RemoteException e) {
                e = e;
                e.printStackTrace();
                return getArtwork(context, (long) i, (long) i2, true);
            }
        } catch (RemoteException e2) {
            e = e2;
            i = 0;
            e.printStackTrace();
            return getArtwork(context, (long) i, (long) i2, true);
        }
        return getArtwork(context, (long) i, (long) i2, true);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x003f */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0045 A[Catch:{ all -> 0x003d }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0068 A[SYNTHETIC, Splitter:B:45:0x0068] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x006e A[SYNTHETIC, Splitter:B:50:0x006e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap getArtwork(android.content.Context r4, long r5, long r7, boolean r9) {
        /*
            r0 = 0
            r2 = 0
            int r3 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r3 >= 0) goto L_0x001c
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 < 0) goto L_0x0014
            r7 = -1
            android.graphics.Bitmap r5 = getArtworkFromFile(r4, r5, r7)
            if (r5 == 0) goto L_0x0014
            return r5
        L_0x0014:
            if (r9 == 0) goto L_0x001b
            android.graphics.Bitmap r4 = getDefaultArtwork(r4)
            return r4
        L_0x001b:
            return r2
        L_0x001c:
            android.content.ContentResolver r0 = r4.getContentResolver()
            android.net.Uri r1 = sArtworkUri
            android.net.Uri r1 = android.content.ContentUris.withAppendedId(r1, r7)
            if (r1 == 0) goto L_0x0072
            java.io.InputStream r0 = r0.openInputStream(r1)     // Catch:{ FileNotFoundException -> 0x003f }
            android.graphics.BitmapFactory$Options r1 = sBitmapOptions     // Catch:{ FileNotFoundException -> 0x003b, all -> 0x0038 }
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r0, r2, r1)     // Catch:{ FileNotFoundException -> 0x003b, all -> 0x0038 }
            if (r0 == 0) goto L_0x0037
            r0.close()     // Catch:{ IOException -> 0x0037 }
        L_0x0037:
            return r4
        L_0x0038:
            r4 = move-exception
            r2 = r0
            goto L_0x006c
        L_0x003b:
            r2 = r0
            goto L_0x003f
        L_0x003d:
            r4 = move-exception
            goto L_0x006c
        L_0x003f:
            android.graphics.Bitmap r5 = getArtworkFromFile(r4, r5, r7)     // Catch:{ all -> 0x003d }
            if (r5 == 0) goto L_0x0060
            android.graphics.Bitmap$Config r6 = r5.getConfig()     // Catch:{ all -> 0x003d }
            if (r6 != 0) goto L_0x0066
            android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ all -> 0x003d }
            r7 = 0
            android.graphics.Bitmap r5 = r5.copy(r6, r7)     // Catch:{ all -> 0x003d }
            if (r5 != 0) goto L_0x0066
            if (r9 == 0) goto L_0x0066
            android.graphics.Bitmap r4 = getDefaultArtwork(r4)     // Catch:{ all -> 0x003d }
            if (r2 == 0) goto L_0x005f
            r2.close()     // Catch:{ IOException -> 0x005f }
        L_0x005f:
            return r4
        L_0x0060:
            if (r9 == 0) goto L_0x0066
            android.graphics.Bitmap r5 = getDefaultArtwork(r4)     // Catch:{ all -> 0x003d }
        L_0x0066:
            if (r2 == 0) goto L_0x006b
            r2.close()     // Catch:{ IOException -> 0x006b }
        L_0x006b:
            return r5
        L_0x006c:
            if (r2 == 0) goto L_0x0071
            r2.close()     // Catch:{ IOException -> 0x0071 }
        L_0x0071:
            throw r4
        L_0x0072:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.MusicUtils.getArtwork(android.content.Context, long, long, boolean):android.graphics.Bitmap");
    }

    private static Bitmap getArtworkFromFile(Context context, long j, long j2) {
        int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i < 0 && j < 0) {
            throw new IllegalArgumentException("Must specify an album or a song id");
        } else if (i < 0) {
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(Uri.parse("content://media/external/audio/media/" + j + "/albumart"), "r");
                if (openFileDescriptor != null) {
                    return BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor());
                }
                return null;
            } catch (FileNotFoundException | IllegalStateException unused) {
                return null;
            }
        } else {
            ParcelFileDescriptor openFileDescriptor2 = context.getContentResolver().openFileDescriptor(ContentUris.withAppendedId(sArtworkUri, j2), "r");
            if (openFileDescriptor2 != null) {
                return BitmapFactory.decodeFileDescriptor(openFileDescriptor2.getFileDescriptor());
            }
            return null;
        }
    }

    public static int computeSampleSize(BitmapFactory.Options options, int i) {
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        int max = Math.max(i2 / i, i3 / i);
        if (max == 0) {
            return 1;
        }
        if (max > 1 && i2 > i && i2 / max < i) {
            max--;
        }
        return (max <= 1 || i3 <= i || i3 / max >= i) ? max : max - 1;
    }
}
