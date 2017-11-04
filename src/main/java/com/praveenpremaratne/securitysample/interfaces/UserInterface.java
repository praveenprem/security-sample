package com.praveenpremaratne.securitysample.interfaces;

import com.praveenpremaratne.securitysample.entities.User;

import java.util.List;

public interface UserInterface {

    List<User> getUsers();

    User getUser(String username);
}
