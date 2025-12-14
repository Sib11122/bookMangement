package com.sehaj.booksManagement.Service;

import com.sehaj.booksManagement.Entity.Books;
import com.sehaj.booksManagement.Entity.Users;
import com.sehaj.booksManagement.Repository.BooksRepo;
import com.sehaj.booksManagement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BooksRepo booksRepo;
    //add new user-admin
    public boolean addNewUser(Users users){
        userRepo.save(users);
        return true;
    }
    // add new Book - user
    public boolean addNewBookEntry(String userId, String bookId) {

        Optional<Users> userOpt = userRepo.findById(userId);
        Optional<Books> bookOpt = booksRepo.findById(bookId);

        if (userOpt.isEmpty() || bookOpt.isEmpty()) {
            return false;
        }

        Users user = userOpt.get();
        Books book = bookOpt.get();

        if (book.isTaken()) {
            return false; // already taken
        }

        if (user.getBooksIds() == null) {
            user.setBooksIds(new ArrayList<>());
        }

        if (!user.getBooksIds().contains(bookId)) {
            user.getBooksIds().add(bookId);
            book.setTaken(true);

            // 🔥 SAVE BOTH
            userRepo.save(user);
            booksRepo.save(book);

            return true;
        }

        return false;
    }

    public boolean deleteBookFromUser(String userId, String bookId) {

        Optional<Users> userOpt = userRepo.findById(userId);
        Optional<Books> bookOpt = booksRepo.findById(bookId);

        if (userOpt.isEmpty() || bookOpt.isEmpty()) {
            return false;
        }

        Users user = userOpt.get();
        Books book = bookOpt.get();

        if (user.getBooksIds() == null || !user.getBooksIds().contains(bookId)) {
            return false;
        }

        user.getBooksIds().remove(bookId);
        book.setTaken(false);

        // 🔥 SAVE BOTH
        userRepo.save(user);
        booksRepo.save(book);

        return true;
    }




    //update user-admin
    public boolean updateUserById(String userId, String field, Users users) {
        Optional<Users> userOpt = userRepo.findById(userId);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            switch (field.toLowerCase()) {
                case "name":
                    user.setName(users.getName());
                    break;
                case "password":
                    user.setPassword(users.getPassword());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
            userRepo.save(user);
            return true;
        }
        return false;
    }

    //find user-admin
    public boolean findUserById(String id){
        return userRepo.existsById(id);
    }
    //update user-admin
    public boolean deleteUser(String id){
        if(userRepo.existsById(id)){
           userRepo.deleteById(id);
           return true;
        }
        return false;
    }
    //get all users-admin
    public List<Users> getAllUsers(){
        return  userRepo.findAll();
    }

}
