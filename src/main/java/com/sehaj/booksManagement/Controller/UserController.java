package com.sehaj.booksManagement.Controller;

import com.sehaj.booksManagement.Entity.Users;
import com.sehaj.booksManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody Users users){
        boolean savedUsers=userService.addNewUser(users);
        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<Users> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PutMapping("/{usersId}/books/{booksId}")
    public ResponseEntity<?> addBooksForUsers(@PathVariable String usersId, @PathVariable String booksId){
        boolean addNewBook=userService.addNewBookEntry(usersId,booksId);
        return new ResponseEntity<>(addNewBook,HttpStatus.CREATED);
    }
    @DeleteMapping("/{usersId}/books/{booksId}")
    public ResponseEntity<?> deleteBookFromUsers(@PathVariable String usersId,@PathVariable String booksId){
        boolean deleteBook=userService.deleteBookFromUser(usersId,booksId);
        return new ResponseEntity<>(deleteBook,HttpStatus.ACCEPTED);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestParam String field, @RequestBody Users users) {
        boolean updated = userService.updateUserById(id, field, users);
        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
