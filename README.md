# java-se-advanced-projects

exploring class design, design patterns, 1.8 features and concurrency

these projects were completed in Jessica Master's Advanced Java course at CCSF.

<h4>Practice</h4>
<ol id="yui_3_15_0_2_1466015198822_228">
<li><span style="font-size: 13.6px; line-height: 1.4;">Write a Book class described by the following:</span>
<ul>
<li><span style="font-size: 13.6px; line-height: 1.4;">String title, author</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">int publishYear, expectedSales, actualSales</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">double cost</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">boolean availableDigital</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">BookType type</span>
<ul>
<li><span style="font-size: 13.6px; line-height: 1.4;">Write the BooKType <a class="autolink" title="enum" href="https://insight.ccsf.edu/mod/resource/view.php?id=520313">enum</a> with values FICTION, NON_FICTION</span></li>
</ul>
</li>
</ul>
</li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Have the Book class implement the Comparable interface. Use <a class="autolink" title="Generics" href="https://insight.ccsf.edu/mod/resource/view.php?id=527102">generics</a>. Order books by title, then year, then author.</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Write a complete Comparator class to order books by cost then year.</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Write code to sort a collection of books using a lambda as a comparator- order by actual sales then expected sales.</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Use streams to obtain the following information through a single statement. Assume you have a nonempty List&lt;Book&gt; bookList. Use at least one method reference somewhere.</span>
<ul>
<li><span style="font-size: 13.6px; line-height: 1.4;">the number of unique authors in the list</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">print the title of every book available digitally, sorted by title</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">the average cost of all fiction books published in 2015</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">the most expensive book published in 2015</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">whether or not there are any digital, non-fiction books published in 2015</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">generate a list of authors (not books!) for all books whose expected sales were higher than their actual sales</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">generate a map of books by author (key = author, value = list of books)</span></li>
</ul>
</li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Write a complete thread class called GameSpinner. </span>
<ul>
<li><span style="font-size: 13.6px; line-height: 1.4;">The class takes in two parameters: the number of sections on the spinner and the number of spins. </span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">When the thread is run, it randomly selects a space on the spinner the specified number of times. (It can just print the output.) </span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Write a program to create and start multiple spinner threads.</span></li>
</ul>
</li>
<li id="yui_3_15_0_2_1466015198822_227"><span style="font-size: 13.6px; line-height: 1.4;">Write a class called BookList that keeps track of two lists- one for fiction books and one for non fiction books. </span>
<ul id="yui_3_15_0_2_1466015198822_226">
<li><span style="font-size: 13.6px; line-height: 1.4;">Write an addBook(Book b) method that adds a book to the appropriate list. &nbsp;</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Write a removeBook(Book b) method that removes the book from the appropriate list.</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Write a printBooks() method that prints both lists.</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Write a separate thread class that is described by a BookList object, a Book, and a type (add or remove). The thread class is responsible for adding or removing the book to/from the list.&nbsp;</span></li>
<li><span style="font-size: 13.6px; line-height: 1.4;">Create several add and remove threads and start them up.</span></li>
<li id="yui_3_15_0_2_1466015198822_225"><span style="font-size: 13.6px; line-height: 1.4;">Make sure all methods are threadsafe. Consider two different ways you could do this.</span>
