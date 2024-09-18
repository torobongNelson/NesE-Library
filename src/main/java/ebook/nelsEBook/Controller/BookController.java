package ebook.nelsEBook.Controller;

import ebook.nelsEBook.Exception.AuthorNotFoundException;
import ebook.nelsEBook.Exception.BookNotFoundException;
import ebook.nelsEBook.Model.Author;
import ebook.nelsEBook.Model.Book;
import ebook.nelsEBook.Service.AuthorService;
import ebook.nelsEBook.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }
   @PostMapping("/add-book")
   public ResponseEntity<Author> addABook(Long id, Book book)throws AuthorNotFoundException {
        var addedBook = bookService.addBook(id,book);
        return new ResponseEntity<>(addedBook, HttpStatus.OK);
    }

    @GetMapping("/view-all")
    public ResponseEntity <List<Book>> view(){
       var viewedBooks = bookService.viewBooks();
       return new ResponseEntity<>(viewedBooks,HttpStatus.OK);
    }

    @GetMapping("/view-book")
    public ResponseEntity<Book> viewBook(Long id)throws BookNotFoundException {
        var viewedBook = bookService.viewABook(id);
        return new ResponseEntity<>(viewedBook, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> update(Long id, Book book)throws BookNotFoundException{
        var updated = bookService.updateBook(id, book);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(Long id)throws BookNotFoundException{
        var deleted = bookService.delete(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
