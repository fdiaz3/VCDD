package resistorSorterdb.persist;

import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.model.Pair;

public interface IDatabase {
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title);
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(String lastname);
	public Pair<Author, Book> insertBooks(String lastname, String firstname, String title, String isbn,
			String published);
	
	
}
