package com.sehaj.booksManagement.Repository;

import com.sehaj.booksManagement.Entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<Users, String> {

    Optional<Users> findByName(String username);
}
