package com.praveenpremaratne.securitysample.controllers;

import com.praveenpremaratne.securitysample.entities.User;
import com.praveenpremaratne.securitysample.interfaces.UserControllerInterface;
import com.praveenpremaratne.securitysample.interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserControllerInterface {

    @Autowired
    private UserInterface userInterface;

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(userInterface.getUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String username) {
        return new ResponseEntity<User>(userInterface.getUser(username), HttpStatus.OK);
    }
}
