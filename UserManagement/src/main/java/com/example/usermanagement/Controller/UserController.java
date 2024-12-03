package com.example.usermanagement.Controller;

import com.example.usermanagement.ApiResponse.ApiResponse;
import com.example.usermanagement.Model.User;
import com.example.usermanagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user) {
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user) {
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User with ID: " + id + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User with ID: " + id + " has been deleted successfully"));
    }

    @GetMapping("/get/by-usersname-and-password/{username}")
    public ResponseEntity checkUserAndPass(@PathVariable String username, @RequestBody String password) {
        return ResponseEntity.status(200).body(userService.checkUserAndPass(username, password));
    }

    @GetMapping("/get/by-email")
    public ResponseEntity getUserByEmail(@RequestBody String email) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("/get/by-role/{role}")
    public ResponseEntity getUserByRole(@PathVariable String role) {
        return ResponseEntity.status(200).body(userService.getUserByRole(role));
    }

    @GetMapping("/get/by-age/{age}")
    public ResponseEntity getUserByAge(@PathVariable Integer age) {
        return ResponseEntity.status(200).body(userService.getUserByAge(age));
    }
}
