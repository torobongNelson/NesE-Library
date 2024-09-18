package ebook.nelsEBook.Repository;

import ebook.nelsEBook.Model.Author;
import ebook.nelsEBook.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleAndAuthor(String title, Author author);
}
