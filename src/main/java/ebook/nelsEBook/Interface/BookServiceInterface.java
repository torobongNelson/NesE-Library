package ebook.nelsEBook.Interface;

import ebook.nelsEBook.Model.Author;
import ebook.nelsEBook.Model.Book;

import java.util.List;

public interface BookServiceInterface {
  Author addBook(Long id, Book book);
  List<Book> viewBooks();
  Book viewABook(Long id);
  Book updateBook(Long id, Book book);
  String delete(Long id);


}
