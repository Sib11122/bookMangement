package com.sehaj.booksManagement.Service;

import com.sehaj.booksManagement.Dto.request.AddBookRequest;
import com.sehaj.booksManagement.Dto.request.BookRequest;
import com.sehaj.booksManagement.Dto.response.BookResponse;
import com.sehaj.booksManagement.Entity.Books;
import com.sehaj.booksManagement.Entity.Users;
import com.sehaj.booksManagement.Repository.BooksRepo;
import com.sehaj.booksManagement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.TreeUI;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BooksRepo booksRepo;

    // CREATE
    public BookResponse addNewEntry(BookRequest dto) {
        Books book = new Books();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuther());
        book.setTaken(false);

        Books saved = booksRepo.save(book);
        return mapToResponse(saved);
    }

    // GET ALL
    public List<BookResponse> getAll() {
        return booksRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET BY ID
    public Optional<BookResponse> getBookById(String id) {
        return booksRepo.findById(id).map(this::mapToResponse);
    }

    // DELETE
    public boolean deleteBook(String id) {
        if (booksRepo.existsById(id)) {
            booksRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // UPDATE
    public Optional<BookResponse> updateBook(String id, BookRequest dto) {
        return booksRepo.findById(id).map(book -> {
            book.setName(dto.getName());
            book.setAuthor(dto.getAuther());
            return mapToResponse(booksRepo.save(book));
        });
    }

    // MAPPER
    private BookResponse mapToResponse(Books book) {
        return new BookResponse(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.isTaken()
        );
    }
}

