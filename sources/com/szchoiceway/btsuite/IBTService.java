package com.szchoiceway.btsuite;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBTService extends IInterface {
    public static final String DESCRIPTOR = "com.szchoiceway.btsuite.IBTService";

    public static class Default implements IBTService {
        public IBinder asBinder() {
            return null;
        }

        public String getContractAddress() throws RemoteException {
            return null;
        }

        public void hideBTFloatWnd() throws RemoteException {
        }

        public void sendData(String str) throws RemoteException {
        }
    }

    String getContractAddress() throws RemoteException;

    void hideBTFloatWnd() throws RemoteException;

    void sendData(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IBTService {
        static final int TRANSACTION_getContractAddress = 1;
        static final int TRANSACTION_hideBTFloatWnd = 2;
        static final int TRANSACTION_sendData = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IBTService.DESCRIPTOR);
        }

        public static IBTService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBTService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBTService)) {
                return new Proxy(iBinder);
            }
            return (IBTService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(IBTService.DESCRIPTOR);
                return true;
            } else if (i == 1) {
                parcel.enforceInterface(IBTService.DESCRIPTOR);
                String contractAddress = getContractAddress();
                parcel2.writeNoException();
                parcel2.writeString(contractAddress);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(IBTService.DESCRIPTOR);
                hideBTFloatWnd();
                parcel2.writeNoException();
                return true;
            } else if (i != 3) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel.enforceInterface(IBTService.DESCRIPTOR);
                sendData(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
        }

        private static class Proxy implements IBTService {
            public static IBTService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IBTService.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getContractAddress() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBTService.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getContractAddress();
                    }
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void hideBTFloatWnd() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBTService.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().hideBTFloatWnd();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendData(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBTService.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().sendData(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBTService iBTService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iBTService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iBTService;
                return true;
            }
        }

        public static IBTService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
