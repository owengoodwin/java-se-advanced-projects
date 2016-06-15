package finalReview;

import java.util.ArrayList;
import java.util.List;

import finalReview.Book.BookType;

//Write a separate thread class that is described by a BookList object, a Book, and a type (add or remove). 
//The thread class is responsible for adding or removing the book to/from the list. 

public class BookThread implements Runnable{
	
	private BookList bookList;
	private Book myBook;
	public enum BookListAction {ADD, REMOVE};
	private BookListAction myAction;
	
	BookThread(BookList bookList, Book myBook, BookListAction myAction){
		this.bookList = bookList;
		this.myBook = myBook;
		this.myAction = myAction;
	}

	@Override
	public void run() {
		if(myAction == BookListAction.ADD){
			bookList.addBook(myBook);
		} else 
		if(myAction == BookListAction.REMOVE){
			bookList.removeBook(myBook);
		}
	}
	
	public BookList getBookList() {
		return bookList;
	}

	public static void main(String[] args) {
		BookList myBookList = new BookList();
		List<Book> myBooks = new ArrayList<>();
		myBooks.add(new Book.BookBuilder("Hand Hand Fingers Thumb", "Perkins, Al")
			.publishYear(1969)
			.expectedSales(600000)
			.actualSales(800000)
			.availableDigitial(true)
			.bookType(BookType.FICTION)
			.cost(4.95)
			.build());
		myBooks.add(new Book.BookBuilder("There's a Wocket In My Pocket", "Seuss, Dr.")
			.publishYear(1974)
			.expectedSales(500000)
			.actualSales(500000)
			.availableDigitial(true)
			.bookType(BookType.FICTION)
			.cost(5.95)
			.build());
		myBooks.add(new Book.BookBuilder("Mr. Brown Can Moo! Can You?", "Seuss, Dr.")
			.publishYear(1970)
			.expectedSales(400000)
			.actualSales(600000)
			.availableDigitial(true)
			.bookType(BookType.FICTION)
			.cost(5.95)
			.build());
		myBooks.add(new Book.BookBuilder("Goodnight Moon", "Brown, Margaret Wise")
			.publishYear(1947)
			.expectedSales(2000000)
			.actualSales(600000)
			.availableDigitial(false)
			.bookType(BookType.NON_FICTION)
			.cost(6.95)
			.build());
		myBooks.add(new Book.BookBuilder("Where the Sidewalk Ends", "Silverstein, Shel")
			.publishYear(1974)
			.expectedSales(600000)
			.actualSales(3000000)
			.availableDigitial(false)
			.bookType(BookType.NON_FICTION)
			.cost(11.95)
			.build());
		
		myBooks.forEach(b -> {
			BookThread myBookThread = new BookThread(myBookList, b, BookListAction.ADD);
			Thread myThread = new Thread(myBookThread);
			myThread.start();
		});
		
		System.out.println("printing out books after adding");
		myBookList.printBooks();
		
		
		//remove a book
		BookThread myBookThread1 = new BookThread(myBookList, myBooks.get(0), BookListAction.REMOVE);
		Thread myThread1 = new Thread(myBookThread1);
		myThread1.start();
		
		BookThread myBookThread2 = new BookThread(myBookList, myBooks.get(myBooks.size() - 1), BookListAction.REMOVE);
		Thread myThread2 = new Thread(myBookThread2);
		myThread2.start();
		
		System.out.println();
		System.out.println("printing out books after deleting");
		myBookList.printBooks();
		
	}
}