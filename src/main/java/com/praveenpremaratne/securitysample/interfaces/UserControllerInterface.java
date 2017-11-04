package com.praveenpremaratne.securitysample.interfaces;

import com.praveenpremaratne.securitysample.entities.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserControllerInterface {

    @GetMapping(value = "/users")
    ResponseEntity<List<User>> getUsers();

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> getUser(@RequestParam String username);
}
