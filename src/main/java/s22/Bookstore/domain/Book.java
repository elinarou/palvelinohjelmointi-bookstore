package s22.Bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Size(min = 1, max = 50)
	private String title;
	private String author;
	
	@Min(value = 1900, message = "min value is 1900")
	@Max(value = 2024, message = "max value is 2024")
	private int bookYear;
	
	@Size(min = 1, max = 30)
	private String isbn;
	
	private double price;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;
	
	public Book() {
		super();
	}

	public Book(String title, String author, int bookYear, String isbn, double price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.bookYear = bookYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getBookYear() {
		return bookYear;
	}

	public void setBookYear(int bookYear) {
		this.bookYear = bookYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", bookYear=" + bookYear + ", isbn="
					+ isbn + ", price=" + price + "]";
	}	
}
