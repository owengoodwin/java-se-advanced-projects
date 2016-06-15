package finalReview;

import java.util.Comparator;

/*
 * 
    Write a Book class described by the following:
        String title, author
        int publishYear, expectedSales, actualSales
        double cost
        boolean availableDigital
        BookType type
            Write the BooKType enum with values FICTION, NON_FICTION
    Have the Book class implement the Comparable interface. Use generics. Order books by title, then year, then author.
    Write a complete Comparator class to order books by cost then year.
    Write code to sort a collection of books using a lambda as a comparator- order by actual sales then expected sales.
    Use streams to obtain the following information through a single statement. Assume you have a nonempty List<Book> bookList. Use at least one method reference somewhere.
        the number of unique authors in the list
        print the title of every book available digitally, sorted by title
        the average cost of all fiction books published in 2015
        the most expensive book published in 2015
        whether or not there are any digital, non-fiction books published in 2015
        generate a list of authors (not books!) for all books whose expected sales were higher than their actual sales
        generate a map of books by author (key = author, value = list of books)
    Write a complete thread class called GameSpinner.
        The class takes in two parameters: the number of sections on the spinner and the number of spins.
        When the thread is run, it randomly selects a space on the spinner the specified number of times. (It can just print the output.)
        Write a program to create and start multiple spinner threads.
    Write a class called BookList that keeps track of two lists- one for fiction books and one for non fiction books.
        Write an addBook(Book b) method that adds a book to the appropriate list.  
        Write a removeBook(Book b) method that removes the book from the appropriate list.
        Write a printBooks() method that prints both lists.
        Write a separate thread class that is described by a BookList object, a Book, and a type (add or remove). The thread class is responsible for adding or removing the book to/from the list. 
        Create several add and remove threads and start them up.
        Make sure all methods are threadsafe. Consider two different ways you could do this.
    Write an immutable class called Author. An author is described by String name, int firstYearPublished, double totalSales, and List<Book> bookList.
 */

public class Book implements Comparable<Book> {

	private String title, author;
	private int publishYear, expectedSales, actualSales;
	private double cost;
	private boolean availableDigitial;
	public enum BookType {FICTION, NON_FICTION};
	private BookType bookType;

	private Book(BookBuilder bookBuilder){
		this.title = bookBuilder.title;
		this.author = bookBuilder.author;
		this.publishYear = bookBuilder.publishYear;
		this.expectedSales = bookBuilder.expectedSales;
		this.actualSales = bookBuilder.actualSales;
		this.cost = bookBuilder.cost;
		this.availableDigitial = bookBuilder.availableDigitial;
		this.bookType = bookBuilder.bookType;
	}
	
	public static class BookBuilder {
		String title, author;
		int publishYear, expectedSales, actualSales;
		double cost;
		boolean availableDigitial;
		BookType bookType;
		
		public BookBuilder(String title, String author){
			this.title = title;
			this.author = author;
		}
		public BookBuilder publishYear(int publishYear){
			this.publishYear = publishYear;
			return this;
		}
		public BookBuilder expectedSales(int expectedSales){
			this.expectedSales = expectedSales;
			return this;
		}
		public BookBuilder actualSales(int actualSales){
			this.actualSales = actualSales;
			return this;
		}
		public BookBuilder cost(double cost){
			this.cost = cost;
			return this;
		}
		public BookBuilder availableDigitial(boolean availableDigitial){
			this.availableDigitial = availableDigitial;
			return this;
		}
		public BookBuilder bookType(BookType bookType){
			this.bookType = bookType;
			return this;
		}
		public Book build(){
			return new Book(this);
		}
	}

	@Override
	public int compareTo(Book otherBook) {
		if(title.compareToIgnoreCase(otherBook.title) == 0){
			if(Integer.compare(publishYear, otherBook.publishYear) == 0){
				return author.compareToIgnoreCase(otherBook.author);
			}
			else
				return Integer.compare(publishYear, otherBook.publishYear);
		}
		else
			return title.compareToIgnoreCase(otherBook.title);
	}


	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publishYear="
				+ publishYear + ", expectedSales=" + expectedSales
				+ ", actualSales=" + actualSales + ", cost=" + cost
				+ ", availableDigitial=" + availableDigitial + ", bookType="
				+ bookType + "]";
	}


	public String getTitle() {
		return title;
	}


	public String getAuthor() {
		return author;
	}


	public int getPublishYear() {
		return publishYear;
	}


	public int getExpectedSales() {
		return expectedSales;
	}


	public int getActualSales() {
		return actualSales;
	}


	public double getCost() {
		return cost;
	}


	public boolean isAvailableDigitial() {
		return availableDigitial;
	}


	public BookType getBookType() {
		return bookType;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Book){
			Book otherBook = (Book) obj;
			return this.author.equalsIgnoreCase(otherBook.author);
		}
		else
			return false	;
	}
}
