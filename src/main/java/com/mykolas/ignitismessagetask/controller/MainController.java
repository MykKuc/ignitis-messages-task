package com.mykolas.ignitismessagetask.controller;

import com.mykolas.ignitismessagetask.jooqdatabase.Tables;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.Users;
import com.mykolas.ignitismessagetask.user.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO Delete this controller later. Not needed.
@RestController
public class MainController {

    @Autowired
    private DSLContext create;

    @GetMapping("test")
    public String getText() {
        return "Testing if this works.";
    }

    @GetMapping("/jooqusers")
    public List<User> getListOfUsersWithJooq() {
      Result<Record> result = create.select().from(Users.USERS).fetch();
      List<User> userIdEmails  = result.into(User.class);
      return userIdEmails;
    }
}
