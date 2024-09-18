package ebook.nelsEBook.Controller;

import ebook.nelsEBook.Exception.AuthorNotFoundException;
import ebook.nelsEBook.Model.Author;
import ebook.nelsEBook.Service.AuthorService;
import ebook.nelsEBook.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
    }


    @PostMapping("/register")
   public ResponseEntity<Author> register(@RequestBody Author author)  {
        Author createdAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/authors/")
    public ResponseEntity<List<Author>> Authors() {
        var bookAuthors = authorService.findAuthors();
        return new ResponseEntity<>(bookAuthors,HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> Author(@PathVariable Long id) throws AuthorNotFoundException {
        var bookAuthor = authorService.findAuthor(id);
        return new ResponseEntity<>(bookAuthor, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, Author authorInfo)throws AuthorNotFoundException {
        var updatedAuthor = authorService.updateAuthor(id,authorInfo);
        return new ResponseEntity<>(updatedAuthor,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)throws AuthorNotFoundException{
        String message = authorService.deleteAuthor(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    }
