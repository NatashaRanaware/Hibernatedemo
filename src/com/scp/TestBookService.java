package com.scp;


public class TestBookService {
	public static void main(String[] args) {
		//write testing code for service...>!
		//
	}
}


//contract
interface BookService{
	public int addBook(Book book);
	public Book getBook(int bookId);
	public boolean deleteBook(int bookId);
	public Book updateBook(Book book);
	public Book[] getAlBooks();
}

class Book{
	private int bookId;
	private String bookName;
	private String autherName;
	private double bookPrice;


	//ToString method is used to represent the object
	@Override
	public String toString() {
		return "\n Book [bookId=" + bookId + ", bookName=" + bookName
				+ ", autherName=" + autherName + ", bookPrice=" + bookPrice
				+ "]";
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAutherName() {
		return autherName;
	}
	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public Book(int bookId, String bookName, String autherName, double bookPrice) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.autherName = autherName;
		this.bookPrice = bookPrice;
	}
	
	
	
	
	
}

class BookServiceImpl implements BookService{

	Book [] listOfBooks = new Book[10];
	int index = 0;
	
	@Override
	public int addBook(Book book) {
		
		if(!(index<10)){ // to check we have capacity or not
			System.out.println("capacity Exceed..cannot add more books");
			return -1;
		}
		
		if(checkForNull(book)){ // whatever book object user has pass should not be null
			System.out.println("In addBook  -- book cannot be null");
			return -1;
		}
		
		if(checkIdPresent(book.getBookId())){ // user book should have valid id
				Book alreadyBook = getBook(book.getBookId());
				if(null != alreadyBook)
					System.out.println("Duplicate book");
				else{
					listOfBooks[index] = book;
					System.out.println("Book Added Successfully" +index);
					index++;
				}
			return book.getBookId();
		}
			
		System.out.println("Bookid is not present");
		return -1;	
		
		
	}

	@Override
	public Book getBook(int bookId) {
		if(checkIdPresent(bookId)){
			for(int i=0;i<index;i++){
					Book book = listOfBooks[i];
					if(book.getBookId()==bookId){
						System.out.println("yes..here is your book");
						return book;
					}
			}
		}
		System.out.println("Invalid Book Id. so cannot give book.");
		
	return null;
	}

	@Override
	public boolean deleteBook(int bookId) {
		if(checkIdPresent(bookId)){
			for(int i=0;i<index;i++){
					Book book = listOfBooks[i];
					if(book.getBookId()==bookId){
						book.setBookId(-10);
						System.out.println("Book Deleted Successfully....!");
						return true;
					}
			}
		}
		System.out.println("Invalid Book Id. so cannot delete a book.");
		return false;
	}

	@Override
	public Book updateBook(Book userBook) {

		
	boolean isBookFound = false;
	
	if(checkForNull(userBook)){
		System.out.println("In UpdateBook  -- book cannot be null");
		}
	
	if(checkIdPresent(userBook.getBookId())){
		isBookFound=true;
		for(int i=0;i<index;i++){
				Book arrBook = listOfBooks[i];
				if(arrBook.getBookId() == userBook.getBookId()){
					arrBook.setBookName(userBook.getBookName());
					arrBook.setAutherName(userBook.getAutherName());
					arrBook.setBookPrice(userBook.getBookPrice());
					System.out.println("Book Updated Successfully");
					return arrBook;
				}
				
		}
	}
	
	if(isBookFound)
		System.out.println("Given Id doesnt exit..so cannot update");
	else
		System.out.println("Invalid Book Id. so cannot Update a book.");
	
	
	return null;

	}

	@Override
	public Book[] getAlBooks() {
		return listOfBooks;
	}

	
	private boolean checkForNull(Book book){
		if(null==book){
			return true;
		}
		return false;
	}
	
	private boolean checkIdPresent(int bookId){
		if(bookId <=0 )
			return false;
		
		return true;
	}
	
}
