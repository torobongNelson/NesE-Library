package ebook.nelsEBook.Service;

import ebook.nelsEBook.Exception.AuthorNotFoundException;
import ebook.nelsEBook.Interface.AuthorServiceInterface;
import ebook.nelsEBook.Model.Author;
import ebook.nelsEBook.Repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements AuthorServiceInterface {
    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author createAuthor(Author author) {
        Author newAuthor = new Author();
         newAuthor.setFirstName(author.getFirstName());
         newAuthor.setLastName(author.getLastName());
         newAuthor.setBooks(author.getBooks());

        return authorRepository.save(newAuthor);
    }

    @Override
    public List<Author> findAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(STR."Author with id:\{id}Not found"));
    }

    @Override
    public Author updateAuthor(Long id, Author authorInfo) {
        Author  existingAuthor  =  authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException("Author with id:" + id + "Not found"));

        existingAuthor.setFirstName(authorInfo.getFirstName());
        existingAuthor.setLastName(authorInfo.getLastName());

        return authorRepository.save(existingAuthor);

    }

    @Override
    public String deleteAuthor(Long id) {
        if(authorRepository.findById(id).isPresent()){
            authorRepository.deleteById(id);
            return "The author with the id:" + id + "is deleted";

        }else {
            throw new AuthorNotFoundException("Author with id:" + id + "Not found\" ");
        }

    }
}
