package com.example.usermanagement.Repository;

import com.example.usermanagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    @Query("select u from User u where u.email = ?1")
    User getUserByEmail(String email);


    List<User> findUserByRole(String role);

    @Query("select u from User u where u.age >= ?1")
    List<User> getUserByAge(Integer age);

    User findUserByUsernameAndPassword(String username, String password);
}
