package lh.cn.edu.henu.upto.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import lh.cn.edu.henu.upto.aidl.Book;

import java.util.List;

public interface BookManger extends IInterface {

    void addBook(Book book) throws RemoteException;

    List<Book> getBookList() throws RemoteException;
}
