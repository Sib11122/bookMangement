package com.sehaj.booksManagement.Controller;

import com.sehaj.booksManagement.Dto.request.BookRequest;
import com.sehaj.booksManagement.Dto.response.BookResponse;
import com.sehaj.booksManagement.Entity.Books;
import com.sehaj.booksManagement.Service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
@CrossOrigin(origins = "*")
public class BooksController {

    @Autowired
    private BooksService booksService;

    // CREATE
    @PostMapping
    public ResponseEntity<BookResponse> addNewBook(
            @RequestBody BookRequest bookRequest) {

        BookResponse saved = booksService.addNewEntry(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(booksService.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        return booksService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Book not found with id: " + id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        return booksService.deleteBook(id)
                ? ResponseEntity.ok("Book deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found with id: " + id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(
            @PathVariable String id,
            @RequestBody BookRequest updatedBook) {

        return booksService.updateBook(id, updatedBook)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Book not found with id: " + id));
    }
}
