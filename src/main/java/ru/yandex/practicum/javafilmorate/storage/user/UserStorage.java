package ru.yandex.practicum.javafilmorate.storage.user;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.javafilmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component

public interface UserStorage {

    public Map<Long, User> users = new HashMap<>();

    public User create(User user);

    public User update(User user);

    public ArrayList<User> getAllUsers();



}
