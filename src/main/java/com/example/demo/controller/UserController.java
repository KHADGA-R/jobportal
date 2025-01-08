package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    private final  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
  
    @PostMapping("/users") 
    public ResponseEntity<User> createUser(@RequestBody User user) {

        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
        
    }

    @PutMapping("users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
       try {
            // Set the ID from the path variable to ensure we update the correct user
            user.setId(id);
            
            // Call the service method to update the user
            User updatedUser = userService.updateUser(id, user);

            if (updatedUser != null) {
                String successMessage = "User " + updatedUser.getFirstName() + " " + updatedUser.getLastName() + " updated successfully .";
                return ResponseEntity.ok(successMessage);
            } else {
                return ResponseEntity.notFound().build();
            } 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Error updating user: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error occurred while updating user .");
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
        User user = userService.getUserById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error occured while fetching user . ");
        }
    }
    

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        try {
            //First get the user to access their name
            User user = userService.getUserById(id);
            if (user == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found with id: " + id);
            }
            userService.deleteUser(id);
            String successMessage = "User " + user.getFirstName() + " " + user.getLastName() + " deleted successfully .";
            return ResponseEntity.ok(successMessage);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Error deleting user: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error occurred while deleting user .");
        }
    }
    
}
