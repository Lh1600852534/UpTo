package lh.cn.edu.henu.upto.aidl;

import android.media.audiofx.AudioEffect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Stub extends Binder implements BookManger {

    private static final String DESCRIPTOR = "lh.cn.edu.henu.upto.aidl.BookManger";

    public Stub(){
        Log.i("ttqqs", "Stub()");
        this.attachInterface(this, DESCRIPTOR);
        RemoteCallbackList<Stub> remoteCallbackList = new RemoteCallbackList<>();
    }

    public static BookManger asInterface(IBinder binder){

        Log.i("ttqqs", "asInterface() ");
        if(binder == null){
            return null;
        }
        IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
        if(iin != null && iin instanceof BookManger){
            return (BookManger)iin;
        }
        return new Proxy(binder);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {

        switch (code){

            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;

            case TRANSACTION_getBookList:
                Log.i("ttqqs", "onTransact() ");
                data.enforceInterface(DESCRIPTOR);
                List<Book> result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;

            case TRANSACTION_addBook:
                data.enforceInterface(DESCRIPTOR);
                Book arg0 = null;
                if(data.readInt() != 0){
                    arg0 = Book.CREATOR.createFromParcel(data);
                }
                this.addBook(arg0);
                reply.writeNoException();
                return true;
        }

        return super.onTransact(code, data, reply, flags);
    }

    public static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION;
    public static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;
}
