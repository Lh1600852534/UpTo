package lh.cn.edu.henu.upto.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {

    private List<Book> books = new ArrayList<>();

    public RemoteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Book book = new Book();
        book.setName("三体");
        book.setPrice(88);
        books.add(book);
        remoteCallbackList.unregister(bookManger);
    }

    private RemoteCallbackList<BookManger> remoteCallbackList = new RemoteCallbackList<>();



    @Override
    public IBinder onBind(Intent intent) {
        return bookManger;
    }

    private final Stub bookManger = new Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this){
                if(books == null){
                    books = new ArrayList<>();
                }

                if(book == null){
                    return;
                }

                book.setPrice(book.getPrice() * 2);
                books.add(book);
            }
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.i("ttqqs", "RemoteService getBookList() ");
            synchronized (this){
                if(books != null){
                    return books;
                }
                return new ArrayList<>();
            }
        }
    };
}
