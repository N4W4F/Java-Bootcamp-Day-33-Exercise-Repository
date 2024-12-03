package com.example.usermanagement.Service;

import com.example.usermanagement.ApiResponse.ApiException;
import com.example.usermanagement.Model.User;
import com.example.usermanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null)
            throw new ApiException("User with ID" + id + " was not found");

        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null)
            throw new ApiException("User with ID: " + id + " was not found");

        userRepository.delete(oldUser);
    }

    public User checkUserAndPass(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user == null)
            throw new ApiException("Username or password are wrong");

        return user;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null)
            throw new ApiException("User with this email was not found");

        return user;
    }

    public List<User> getUserByRole(String role) {
        List<User> users = userRepository.findUserByRole(role);
        if (users.isEmpty())
            throw new ApiException("There are no users with this role");

        return users;
    }

    public List<User> getUserByAge(Integer age) {
        List<User> users = userRepository.getUserByAge(age);
        if (users.isEmpty())
            throw new ApiException("There are no users by this age");

        return users;
    }
}
