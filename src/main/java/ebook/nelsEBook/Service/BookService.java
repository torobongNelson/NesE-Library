package ebook.nelsEBook.Service;

import ebook.nelsEBook.Exception.AuthorNotFoundException;
import ebook.nelsEBook.Exception.BookAlreadyExistsException;
import ebook.nelsEBook.Exception.BookNotFoundException;
import ebook.nelsEBook.Interface.BookServiceInterface;
import ebook.nelsEBook.Model.Author;
import ebook.nelsEBook.Model.Book;
import ebook.nelsEBook.Repository.AuthorRepository;
import ebook.nelsEBook.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceInterface {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public Author addBook(Long id, Book book) {
        Author author =  authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException(" "));

        String title = book.getTitle();
        if(bookRepository.existsByTitleAndAuthor(title, author)){
            throw new BookAlreadyExistsException(STR."A book with \{title}already exists for this author");
        }

        book.setAuthor(author);
        author.getBooks().add(book);
        return authorRepository.save(author);
    }

    @Override
    public List<Book> viewBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book viewABook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("This Book with the id:" + id + "does not exists"));
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book  existingBook  = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(STR."This Book with the id:\{id}does not exists"));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());

        return bookRepository.save(existingBook);
    }


    @Override
    public String delete(Long id) {
        if(bookRepository.findById(id).isPresent()){
            bookRepository.deleteById(id);
            return STR."book with id:\{id}sucessfully deleted";
        }else{
            throw new BookNotFoundException("Book with id:" + id + "book not found");
        }
    }
}
