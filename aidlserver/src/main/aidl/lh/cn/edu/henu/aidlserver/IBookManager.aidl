// IBookManager.aidl
package lh.cn.edu.henu.aidlserver;

// Declare any non-default types here with import statements
import lh.cn.edu.henu.aidlserver.Book;

interface IBookManager {

    List<Book> getBookList();

    void addBook(in Book book);
 }
