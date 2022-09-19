package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserStorage {

    User create(User user);

    Collection<User> findAll();

    User findById(Long id);

    User update(User user);

    void deleteById(Long id);
}
