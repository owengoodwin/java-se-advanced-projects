package finalReview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import finalReview.Book.BookType;

public class BookTester {
	
	public static void main(String[] args){
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book.BookBuilder("Hand Hand Fingers Thumb", "Perkins, Al")
			.publishYear(1969)
			.expectedSales(600000)
			.actualSales(800000)
			.availableDigitial(true)
			.bookType(BookType.FICTION)
			.cost(4.95)
			.build());
		bookList.add(new Book.BookBuilder("There's a Wocket In My Pocket", "Seuss, Dr.")
			.publishYear(1974)
			.expectedSales(500000)
			.actualSales(500000)
			.availableDigitial(true)
			.bookType(BookType.FICTION)
			.cost(5.95)
			.build());
		bookList.add(new Book.BookBuilder("Mr. Brown Can Moo! Can You?", "Seuss, Dr.")
			.publishYear(1970)
			.expectedSales(400000)
			.actualSales(600000)
			.availableDigitial(true)
			.bookType(BookType.FICTION)
			.cost(5.95)
			.build());
		bookList.add(new Book.BookBuilder("Goodnight Moon", "Brown, Margaret Wise")
			.publishYear(1947)
			.expectedSales(2000000)
			.actualSales(600000)
			.availableDigitial(false)
			.bookType(BookType.FICTION)
			.cost(6.95)
			.build());
		bookList.add(new Book.BookBuilder("Where the Sidewalk Ends", "Silverstein, Shel")
			.publishYear(1974)
			.expectedSales(600000)
			.actualSales(3000000)
			.availableDigitial(false)
			.bookType(BookType.FICTION)
			.cost(11.95)
			.build());
		
		System.out.println("Unsorted");
		bookList.forEach(System.out::println);
		
		//sort bookList using compareTo method
		System.out.println();
		System.out.println("Sorted by title, then year, then author");
		Collections.sort(bookList);
		bookList.forEach(System.out::println);
		
		//sort by Comparator class to order books by cost then year.
		System.out.println();
		System.out.println("Sorted by cost, then year");
		Collections.sort(bookList, new CostComparator());
		bookList.forEach(System.out::println);
		
		//Sort using a lambda as a comparator- order by actual sales then expected sales.
		System.out.println();
		System.out.println("Sorted by actual sales, then expected sales");
//		Collections.sort(bookList, (b1, b2) -> {
//			if(Integer.compare(b1.getActualSales(), b2.getActualSales()) == 0){
//				return Integer.compare(b1.getExpectedSales(), b2.getExpectedSales());
//			}
//			else
//				return Integer.compare(b1.getActualSales(), b2.getActualSales());
//		});
//		bookList.forEach(System.out::println);
//		
		bookList.sort(
				Comparator
					.comparing(Book::getActualSales)
					.thenComparing(Book::getExpectedSales)
				);
		bookList.forEach(System.out::println);
		
//		Use streams to obtain the following information through a single statement. Assume you have a nonempty List<Book> bookList. Use at least one method reference somewhere.
		
		// the number of unique authors in the list
		long uniqueAuthorCount = bookList.stream()
				.map(Book::getAuthor)
				.distinct()
				.count();
		System.out.println("Unique author count " + uniqueAuthorCount);
		
		//print the title of every book available digitally, sorted by title
		System.out.println();
		System.out.println("Printing out all digitally available sorted by title:");
		bookList.stream()
			.sorted(Comparator.comparing(Book::getTitle))
			.filter(Book::isAvailableDigitial)
			.forEach(b -> System.out.println(b.getTitle()));
		
		//the average cost of all fiction books published in 2015
		double averageCostOfAllFiction = bookList.stream()
			.filter(b -> b.getBookType() == BookType.FICTION)
			.mapToDouble(Book::getCost)
			.average()
			.getAsDouble();
		System.out.println();
		System.out.println("Average cost of all fiction: " + averageCostOfAllFiction);
		
		//the most expensive book published in 1974 :)
		Book mostExpensiveBookPublished1974 = bookList.stream()
				.filter(b -> b.getPublishYear() == 1974)
				.max(Comparator.comparing(Book::getCost)).get();
		System.out.println();
		System.out.println("the most expensive book published in 1974: " + mostExpensiveBookPublished1974);
		
		//whether or not there are any digital, non-fiction books published in 2015
		boolean anyDigitialNonFiction2015Books = bookList.stream()
			.anyMatch(b -> b.isAvailableDigitial() == true && b.getBookType() == BookType.NON_FICTION 
				&& b.getPublishYear() == 2015);
		System.out.println();
		System.out.println("whether or not there are any digital, non-fiction books published in 2015: " 
				+ anyDigitialNonFiction2015Books);
		
		//whether or not there are any digital, fiction books published in 1974
		boolean anyDigitialFiction1974Books = bookList.stream()
			.anyMatch(b -> b.isAvailableDigitial() == true && b.getBookType() == BookType.FICTION 
				&& b.getPublishYear() == 1974);
		System.out.println();
		System.out.println("whether or not there are any digital, fiction books published in 1974: " 
				+ anyDigitialFiction1974Books);
		
		//generate a list of authors (not books!) for all books whose expected sales were higher than their actual sales
		List<String> authorList = bookList.stream()
				.filter(b -> b.getExpectedSales() > b.getActualSales())
				.map(Book::getAuthor)
				.collect(Collectors.toList());
		System.out.println();
		System.out.println("list of authors (not books!) for all books whose expected sales were higher than their actual sales");
		authorList.forEach(System.out::println);
		
		//generate a map of books by author (key = author, value = list of books)
		Map<String, List<Book>> bookMapByAuthor = bookList.stream()
				.collect(Collectors.groupingBy(Book::getAuthor));
		bookMapByAuthor.forEach((author, books) -> {
			books.forEach(book -> System.out.println("AUTHOR: " + author + " TITLE: " + book.getTitle()));
		});
		
	}

	
	private static class CostComparator implements Comparator<Book>{
		
		public int compare(Book book1, Book book2){
			if(Double.compare(book1.getCost(), book2.getCost()) == 0){
				return Integer.compare(book1.getPublishYear(), book2.getPublishYear());
			}
			else
				return Double.compare(book1.getCost(), book2.getCost());
		}
	}
		
}
