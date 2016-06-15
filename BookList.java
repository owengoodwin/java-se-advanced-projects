package finalReview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import finalReview.Book.BookType;

/*
 * Write a class called BookList that keeps track of two lists- one for fiction books and one for non fiction books.

    Write an addBook(Book b) method that adds a book to the appropriate list.  
    Write a removeBook(Book b) method that removes the book from the appropriate list.
    Write a printBooks() method that prints both lists.
    Write a separate thread class that is described by a BookList object, a Book, and a type (add or remove). 
    	The thread class is responsible for adding or removing the book to/from the list. 
    Create several add and remove threads and start them up.
    Make sure all methods are threadsafe. Consider two different ways you could do this.
 */

public class BookList {

	private List<Book> fictionList;
	private List<Book> nonFictionList;
	
	private Lock bookListLock;
	private Condition containsCondition;
	
	public BookList(){
		fictionList = new CopyOnWriteArrayList<Book>();
		nonFictionList = new CopyOnWriteArrayList<Book>();
		bookListLock = new ReentrantLock();
		containsCondition = bookListLock.newCondition();
	}
	
	public boolean addBook(Book book){
		bookListLock.lock();
		try{
			if(book.getBookType() == BookType.FICTION){
				return fictionList.add(book);
			}
			else if(book.getBookType() == BookType.NON_FICTION){
				return nonFictionList.add(book);
			}
			else{
				assert book.getBookType() == null;
				return false;
			}
		} finally {
			//notify remove threads that a book has been added 
			containsCondition.signalAll();
			bookListLock.unlock();
		}
		
	}
	
	public boolean removeBook(Book book){
		bookListLock.lock();
		try{
			if(book.getBookType() == BookType.FICTION){
				while(!fictionList.contains(book)){
					containsCondition.await();
				}
				return fictionList.remove(book);
			}
			else if(book.getBookType() == BookType.NON_FICTION){
				while(!nonFictionList.contains(book)){
					containsCondition.await();
				}
				return nonFictionList.remove(book);
			}
			else{
				assert book.getBookType() == null;
				return false;
			}
		}  catch(InterruptedException ex)  {
				return false;
		} finally {
			bookListLock.unlock();
		}
	}
	
	public void printBooks(){
		System.out.println("Fiction list: ");
		fictionList.forEach(System.out::println);
		
		System.out.println();
		System.out.println("Non-fiction list: ");
		nonFictionList.forEach(System.out::println);
	}
	
}
