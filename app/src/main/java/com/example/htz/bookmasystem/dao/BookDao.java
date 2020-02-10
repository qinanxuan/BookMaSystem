package com.example.htz.bookmasystem.dao;

import com.example.htz.bookmasystem.model.Book;

import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface BookDao {
    public List<Book> getAllBooks();

    public Book getBookByBno(String bno);

    public boolean insertBook(Book book);

    public boolean delBook(String bno);

    public boolean updateBook(Book book);
}
