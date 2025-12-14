package com.sehaj.booksManagement.Repository;

import com.sehaj.booksManagement.Entity.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends MongoRepository<Books, String> {
}
