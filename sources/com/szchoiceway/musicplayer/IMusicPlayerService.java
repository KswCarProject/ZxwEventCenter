package com.szchoiceway.musicplayer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMusicPlayerService extends IInterface {
    public static final String DESCRIPTOR = "com.szchoiceway.musicplayer.IMusicPlayerService";

    public static class Default implements IMusicPlayerService {
        public IBinder asBinder() {
            return null;
        }

        public long duration() throws RemoteException {
            return 0;
        }

        public void enableVisualizer(boolean z) throws RemoteException {
        }

        public int getAblumID() throws RemoteException {
            return 0;
        }

        public String getAlbumName() throws RemoteException {
            return null;
        }

        public String getArtistName() throws RemoteException {
            return null;
        }

        public int getAudioSessionId() throws RemoteException {
            return 0;
        }

        public String getCurrPlayFilePath() throws RemoteException {
            return null;
        }

        public int getSongID() throws RemoteException {
            return 0;
        }

        public String getTitle() throws RemoteException {
            return null;
        }

        public String getTrackName() throws RemoteException {
            return null;
        }

        public boolean isPlaying() throws RemoteException {
            return false;
        }

        public void next() throws RemoteException {
        }

        public void notifyNavPlaySoundEvent(boolean z) throws RemoteException {
        }

        public void pause() throws RemoteException {
        }

        public void play() throws RemoteException {
        }

        public void playFile(String str, int i) throws RemoteException {
        }

        public long position() throws RemoteException {
            return 0;
        }

        public void prev() throws RemoteException {
        }

        public long seek(long j) throws RemoteException {
            return 0;
        }

        public void stop() throws RemoteException {
        }
    }

    long duration() throws RemoteException;

    void enableVisualizer(boolean z) throws RemoteException;

    int getAblumID() throws RemoteException;

    String getAlbumName() throws RemoteException;

    String getArtistName() throws RemoteException;

    int getAudioSessionId() throws RemoteException;

    String getCurrPlayFilePath() throws RemoteException;

    int getSongID() throws RemoteException;

    String getTitle() throws RemoteException;

    String getTrackName() throws RemoteException;

    boolean isPlaying() throws RemoteException;

    void next() throws RemoteException;

    void notifyNavPlaySoundEvent(boolean z) throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void playFile(String str, int i) throws RemoteException;

    long position() throws RemoteException;

    void prev() throws RemoteException;

    long seek(long j) throws RemoteException;

    void stop() throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicPlayerService {
        static final int TRANSACTION_duration = 8;
        static final int TRANSACTION_enableVisualizer = 16;
        static final int TRANSACTION_getAblumID = 19;
        static final int TRANSACTION_getAlbumName = 12;
        static final int TRANSACTION_getArtistName = 13;
        static final int TRANSACTION_getAudioSessionId = 15;
        static final int TRANSACTION_getCurrPlayFilePath = 17;
        static final int TRANSACTION_getSongID = 18;
        static final int TRANSACTION_getTitle = 14;
        static final int TRANSACTION_getTrackName = 11;
        static final int TRANSACTION_isPlaying = 2;
        static final int TRANSACTION_next = 7;
        static final int TRANSACTION_notifyNavPlaySoundEvent = 20;
        static final int TRANSACTION_pause = 4;
        static final int TRANSACTION_play = 5;
        static final int TRANSACTION_playFile = 1;
        static final int TRANSACTION_position = 9;
        static final int TRANSACTION_prev = 6;
        static final int TRANSACTION_seek = 10;
        static final int TRANSACTION_stop = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMusicPlayerService.DESCRIPTOR);
        }

        public static IMusicPlayerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMusicPlayerService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMusicPlayerService)) {
                return new Proxy(iBinder);
            }
            return (IMusicPlayerService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                boolean z = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        playFile(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        boolean isPlaying = isPlaying();
                        parcel2.writeNoException();
                        parcel2.writeInt(isPlaying ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        stop();
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        pause();
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        play();
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        prev();
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        next();
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        long duration = duration();
                        parcel2.writeNoException();
                        parcel2.writeLong(duration);
                        return true;
                    case 9:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        long position = position();
                        parcel2.writeNoException();
                        parcel2.writeLong(position);
                        return true;
                    case 10:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        long seek = seek(parcel.readLong());
                        parcel2.writeNoException();
                        parcel2.writeLong(seek);
                        return true;
                    case 11:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        String trackName = getTrackName();
                        parcel2.writeNoException();
                        parcel2.writeString(trackName);
                        return true;
                    case 12:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        String albumName = getAlbumName();
                        parcel2.writeNoException();
                        parcel2.writeString(albumName);
                        return true;
                    case 13:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        String artistName = getArtistName();
                        parcel2.writeNoException();
                        parcel2.writeString(artistName);
                        return true;
                    case 14:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        String title = getTitle();
                        parcel2.writeNoException();
                        parcel2.writeString(title);
                        return true;
                    case 15:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        int audioSessionId = getAudioSessionId();
                        parcel2.writeNoException();
                        parcel2.writeInt(audioSessionId);
                        return true;
                    case 16:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        enableVisualizer(z);
                        parcel2.writeNoException();
                        return true;
                    case 17:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        String currPlayFilePath = getCurrPlayFilePath();
                        parcel2.writeNoException();
                        parcel2.writeString(currPlayFilePath);
                        return true;
                    case 18:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        int songID = getSongID();
                        parcel2.writeNoException();
                        parcel2.writeInt(songID);
                        return true;
                    case 19:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        int ablumID = getAblumID();
                        parcel2.writeNoException();
                        parcel2.writeInt(ablumID);
                        return true;
                    case 20:
                        parcel.enforceInterface(IMusicPlayerService.DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        notifyNavPlaySoundEvent(z);
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(IMusicPlayerService.DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IMusicPlayerService {
            public static IMusicPlayerService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IMusicPlayerService.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void playFile(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().playFile(str, i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isPlaying() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPlaying();
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

            public void stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stop();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().pause();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void play() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().play();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void prev() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().prev();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().next();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long duration() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().duration();
                    }
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    obtain2.recycle();
                    obtain.recycle();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long position() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().position();
                    }
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    obtain2.recycle();
                    obtain.recycle();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long seek(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().seek(j);
                    }
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    obtain2.recycle();
                    obtain.recycle();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getTrackName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTrackName();
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

            public String getAlbumName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAlbumName();
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

            public String getArtistName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getArtistName();
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

            public String getTitle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTitle();
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

            public int getAudioSessionId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioSessionId();
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

            public void enableVisualizer(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(16, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().enableVisualizer(z);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getCurrPlayFilePath() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrPlayFilePath();
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

            public int getSongID() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSongID();
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

            public int getAblumID() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAblumID();
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

            public void notifyNavPlaySoundEvent(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMusicPlayerService.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(20, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().notifyNavPlaySoundEvent(z);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicPlayerService iMusicPlayerService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iMusicPlayerService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iMusicPlayerService;
                return true;
            }
        }

        public static IMusicPlayerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
