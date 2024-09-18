package ebook.nelsEBook.Interface;

import ebook.nelsEBook.Dto.AuthorRequestDto;
import ebook.nelsEBook.Model.Author;

import java.util.List;

public interface AuthorServiceInterface {
    Author createAuthor(Author author );
    List <Author> findAuthors();
    Author findAuthor(Long id);
    Author updateAuthor(Long id, Author authorInfo);
    String deleteAuthor(Long id);

}
