package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class UserStorageMemImpl implements UserStorage {

    private final AtomicLong currentId = new AtomicLong(0);
    private final ConcurrentHashMap<Long, User> usersStorage = new ConcurrentHashMap<>();

    @Override
    public User create(User user) {
        user.setId(currentId.incrementAndGet());
        user.setBooks(new ArrayList<>());
        usersStorage.put(currentId.get(), user);
        log.info("create user with ID: {}", user);
        return user;
    }

    @Override
    public Collection<User> findAll() {
        return usersStorage.values();
    }

    @Override
    public User findById(Long id) {
        return usersStorage.get(id);
    }

    @Override
    public User update(User user) {
        if (!usersStorage.containsKey(user.getId())) {
            create(user);
        } else {
            User updateUser = usersStorage.get(user.getId());
            updateUser.setAge(user.getAge());
            updateUser.setFullName(user.getFullName());
            updateUser.setTitle(user.getTitle());
            log.info("user update: {}", user);
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        usersStorage.remove(id);
    }
}
