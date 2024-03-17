package com.szchoiceway.eventcenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICameraCrashService extends IInterface {
    public static final String DESCRIPTOR = "com.szchoiceway.eventcenter.ICameraCrashService";

    public static class Default implements ICameraCrashService {
        public void OpenADASInterface() throws RemoteException {
        }

        public boolean PrepareMedia(int i, int i2, int i3) throws RemoteException {
            return false;
        }

        public void SetCoordinate(byte b, byte b2, int i, byte b3, int i2) throws RemoteException {
        }

        public void SetSpeed(int i) throws RemoteException {
        }

        public void SetTime(int i, byte b, byte b2, byte b3, byte b4, byte b5) throws RemoteException {
        }

        public boolean StartRecording(int i, String str) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }

        public void backRecord() throws RemoteException {
        }

        public int getRecordState() throws RemoteException {
            return 0;
        }

        public void setRecordState(int i) throws RemoteException {
        }

        public void startPreview() throws RemoteException {
        }

        public void startRecording() throws RemoteException {
        }

        public void stopPreview() throws RemoteException {
        }

        public void stopRecording() throws RemoteException {
        }
    }

    void OpenADASInterface() throws RemoteException;

    boolean PrepareMedia(int i, int i2, int i3) throws RemoteException;

    void SetCoordinate(byte b, byte b2, int i, byte b3, int i2) throws RemoteException;

    void SetSpeed(int i) throws RemoteException;

    void SetTime(int i, byte b, byte b2, byte b3, byte b4, byte b5) throws RemoteException;

    boolean StartRecording(int i, String str) throws RemoteException;

    void backRecord() throws RemoteException;

    int getRecordState() throws RemoteException;

    void setRecordState(int i) throws RemoteException;

    void startPreview() throws RemoteException;

    void startRecording() throws RemoteException;

    void stopPreview() throws RemoteException;

    void stopRecording() throws RemoteException;

    public static abstract class Stub extends Binder implements ICameraCrashService {
        static final int TRANSACTION_OpenADASInterface = 13;
        static final int TRANSACTION_PrepareMedia = 1;
        static final int TRANSACTION_SetCoordinate = 10;
        static final int TRANSACTION_SetSpeed = 11;
        static final int TRANSACTION_SetTime = 12;
        static final int TRANSACTION_StartRecording = 4;
        static final int TRANSACTION_backRecord = 8;
        static final int TRANSACTION_getRecordState = 5;
        static final int TRANSACTION_setRecordState = 9;
        static final int TRANSACTION_startPreview = 6;
        static final int TRANSACTION_startRecording = 2;
        static final int TRANSACTION_stopPreview = 7;
        static final int TRANSACTION_stopRecording = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICameraCrashService.DESCRIPTOR);
        }

        public static ICameraCrashService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICameraCrashService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICameraCrashService)) {
                return new Proxy(iBinder);
            }
            return (ICameraCrashService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        boolean PrepareMedia = PrepareMedia(parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(PrepareMedia ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        startRecording();
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        stopRecording();
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        boolean StartRecording = StartRecording(parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(StartRecording ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        int recordState = getRecordState();
                        parcel2.writeNoException();
                        parcel2.writeInt(recordState);
                        return true;
                    case 6:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        startPreview();
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        stopPreview();
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        backRecord();
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        setRecordState(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        SetCoordinate(parcel.readByte(), parcel.readByte(), parcel.readInt(), parcel.readByte(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 11:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        SetSpeed(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        SetTime(parcel.readInt(), parcel.readByte(), parcel.readByte(), parcel.readByte(), parcel.readByte(), parcel.readByte());
                        parcel2.writeNoException();
                        return true;
                    case 13:
                        parcel.enforceInterface(ICameraCrashService.DESCRIPTOR);
                        OpenADASInterface();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(ICameraCrashService.DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements ICameraCrashService {
            public static ICameraCrashService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return ICameraCrashService.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public boolean PrepareMedia(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().PrepareMedia(i, i2, i3);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startRecording() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startRecording();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopRecording() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopRecording();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean StartRecording(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StartRecording(i, str);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getRecordState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRecordState();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startPreview() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startPreview();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopPreview() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopPreview();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void backRecord() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    if (this.mRemote.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().backRecord();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setRecordState(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setRecordState(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void SetCoordinate(byte b, byte b2, int i, byte b3, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    obtain.writeByte(b);
                    obtain.writeByte(b2);
                    obtain.writeInt(i);
                    obtain.writeByte(b3);
                    obtain.writeInt(i2);
                    if (this.mRemote.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().SetCoordinate(b, b2, i, b3, i2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void SetSpeed(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().SetSpeed(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void SetTime(int i, byte b, byte b2, byte b3, byte b4, byte b5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    int i2 = i;
                    obtain.writeInt(i);
                    byte b6 = b;
                    obtain.writeByte(b);
                    byte b7 = b2;
                    obtain.writeByte(b2);
                    obtain.writeByte(b3);
                    obtain.writeByte(b4);
                    obtain.writeByte(b5);
                    try {
                        if (this.mRemote.transact(12, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().SetTime(i, b, b2, b3, b4, b5);
                        obtain2.recycle();
                        obtain.recycle();
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void OpenADASInterface() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICameraCrashService.DESCRIPTOR);
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().OpenADASInterface();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraCrashService iCameraCrashService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iCameraCrashService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iCameraCrashService;
                return true;
            }
        }

        public static ICameraCrashService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
