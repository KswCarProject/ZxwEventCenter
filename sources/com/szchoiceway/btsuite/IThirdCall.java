package com.szchoiceway.btsuite;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IThirdCall extends IInterface {
    public static final String DESCRIPTOR = "com.szchoiceway.btsuite.IThirdCall";

    public static class Default implements IThirdCall {
        public IBinder asBinder() {
            return null;
        }

        public void notifyThirdCallingAceppt(String str) throws RemoteException {
        }

        public void notifyThirdCallingNum(String str) throws RemoteException {
        }

        public void notifyThirdHandUp(String str) throws RemoteException {
        }

        public void notifyThirdInCall(String str, boolean z) throws RemoteException {
        }
    }

    void notifyThirdCallingAceppt(String str) throws RemoteException;

    void notifyThirdCallingNum(String str) throws RemoteException;

    void notifyThirdHandUp(String str) throws RemoteException;

    void notifyThirdInCall(String str, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IThirdCall {
        static final int TRANSACTION_notifyThirdCallingAceppt = 4;
        static final int TRANSACTION_notifyThirdCallingNum = 3;
        static final int TRANSACTION_notifyThirdHandUp = 2;
        static final int TRANSACTION_notifyThirdInCall = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IThirdCall.DESCRIPTOR);
        }

        public static IThirdCall asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IThirdCall.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IThirdCall)) {
                return new Proxy(iBinder);
            }
            return (IThirdCall) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(IThirdCall.DESCRIPTOR);
                return true;
            } else if (i == 1) {
                parcel.enforceInterface(IThirdCall.DESCRIPTOR);
                notifyThirdInCall(parcel.readString(), parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(IThirdCall.DESCRIPTOR);
                notifyThirdHandUp(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(IThirdCall.DESCRIPTOR);
                notifyThirdCallingNum(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 4) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(IThirdCall.DESCRIPTOR);
                notifyThirdCallingAceppt(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
        }

        private static class Proxy implements IThirdCall {
            public static IThirdCall sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IThirdCall.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void notifyThirdInCall(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IThirdCall.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().notifyThirdInCall(str, z);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void notifyThirdHandUp(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IThirdCall.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().notifyThirdHandUp(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void notifyThirdCallingNum(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IThirdCall.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().notifyThirdCallingNum(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void notifyThirdCallingAceppt(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IThirdCall.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().notifyThirdCallingAceppt(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IThirdCall iThirdCall) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iThirdCall == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iThirdCall;
                return true;
            }
        }

        public static IThirdCall getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
