package com.szchoiceway.eventcenter;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class SysVarProvider extends ContentProvider {
    private static final int SYSTEMVAR = 2;
    private static final int SYSTEMVARS = 1;
    private static final String TAG = "SysVarProvider";
    private static final UriMatcher URI_MATCHER;
    private SysVarDbMan mDbManager;

    public String getType(Uri uri) {
        return null;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        URI_MATCHER = uriMatcher;
        uriMatcher.addURI("com.szchoiceway.eventcenter.SysVarProvider", "SysVar", 1);
        uriMatcher.addURI("com.szchoiceway.eventcenter.SysVarProvider", "SysVar/#", 2);
    }

    public boolean onCreate() {
        this.mDbManager = new SysVarDbMan(getContext());
        Log.i(TAG, "mDbManager *** = " + this.mDbManager);
        return true;
    }

    public void shutdown() {
        super.shutdown();
        SysVarDbMan sysVarDbMan = this.mDbManager;
        if (sysVarDbMan != null) {
            sysVarDbMan.closeDB();
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int i;
        int match = URI_MATCHER.match(uri);
        if (match == 1) {
            SysVarDbMan sysVarDbMan = this.mDbManager;
            if (sysVarDbMan != null) {
                i = sysVarDbMan.deleteSysVar(str, strArr);
                Log.i(TAG, "---->>delete count = " + i);
                return i;
            }
        } else if (match == 2) {
            String[] strArr2 = {String.valueOf(ContentUris.parseId(uri))};
            SysVarDbMan sysVarDbMan2 = this.mDbManager;
            if (sysVarDbMan2 != null) {
                i = sysVarDbMan2.deleteSysVar(" _id = ?", strArr2);
                Log.i(TAG, "---->>delete count = " + i);
                return i;
            }
        }
        i = -1;
        Log.i(TAG, "---->>delete count = " + i);
        return i;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri uri2;
        long j = 0;
        if (URI_MATCHER.match(uri) == 1) {
            SysVarDbMan sysVarDbMan = this.mDbManager;
            if (sysVarDbMan != null) {
                j = sysVarDbMan.insertSysVar(contentValues);
            }
            uri2 = ContentUris.withAppendedId(uri, j);
        } else {
            uri2 = null;
        }
        Log.i(TAG, "---->>insert id = " + j);
        return uri2;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int match = URI_MATCHER.match(uri);
        if (match == 1) {
            SysVarDbMan sysVarDbMan = this.mDbManager;
            if (sysVarDbMan != null) {
                return sysVarDbMan.querySysVar(strArr, str, strArr2, str2);
            }
        } else if (match == 2) {
            String[] strArr3 = {String.valueOf(ContentUris.parseId(uri))};
            SysVarDbMan sysVarDbMan2 = this.mDbManager;
            if (sysVarDbMan2 != null) {
                return sysVarDbMan2.querySysVar(strArr, " _id = ?", strArr3, str2);
            }
        }
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int i;
        int match = URI_MATCHER.match(uri);
        if (match == 1) {
            i = this.mDbManager.updateSysVar(contentValues, str, strArr);
        } else if (match != 2) {
            i = -1;
        } else {
            i = this.mDbManager.updateSysVar(contentValues, "_id = ?", new String[]{String.valueOf(ContentUris.parseId(uri))});
        }
        Log.i(TAG, "---->>update Count = " + i);
        return i;
    }

    class SysVarDbMan {
        SQLiteDatabase database = null;
        private DBHelper helper = null;

        public SysVarDbMan(Context context) {
            DBHelper dBHelper = new DBHelper(context);
            this.helper = dBHelper;
            this.database = dBHelper.getWritableDatabase();
        }

        public void closeDB() {
            SQLiteDatabase sQLiteDatabase = this.database;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
                this.database = null;
            }
        }

        public long insertSysVar(ContentValues contentValues) {
            try {
                SQLiteDatabase sQLiteDatabase = this.database;
                if (sQLiteDatabase != null) {
                    return sQLiteDatabase.insert("sysvar", (String) null, contentValues);
                }
                return -1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }

        public int deleteSysVar(String str, String[] strArr) {
            try {
                SQLiteDatabase sQLiteDatabase = this.database;
                if (sQLiteDatabase != null) {
                    return sQLiteDatabase.delete("sysvar", str, strArr);
                }
                return -1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }

        public int updateSysVar(ContentValues contentValues, String str, String[] strArr) {
            try {
                SQLiteDatabase sQLiteDatabase = this.database;
                if (sQLiteDatabase != null) {
                    return sQLiteDatabase.update("sysvar", contentValues, str, strArr);
                }
                return -1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }

        public Cursor querySysVar(String[] strArr, String str, String[] strArr2, String str2) {
            try {
                SQLiteDatabase sQLiteDatabase = this.database;
                if (sQLiteDatabase != null) {
                    return sQLiteDatabase.query(true, "sysvar", strArr, str, strArr2, (String) null, (String) null, str2, (String) null);
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        class DBHelper extends SQLiteOpenHelper {
            private static final String DATABASE_NAME = "SysVar.db";
            private static final int DATABASE_VERSION = 1;

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            }

            public DBHelper(Context context) {
                super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
            }

            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS sysvar (keyname VARCHAR PRIMARY KEY, keyvalue VARCHAR)");
            }
        }
    }
}
