package com.eduhub.eduhub_backend.controller;


import com.eduhub.eduhub_backend.component.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    static List<User> userList = new ArrayList<>();
    static
    {
        userList.add(new User(1,"Chris Evans","Chris123"));
        userList.add(new User(2,"Robert","Robert123"));




    }


}
