package com.example.TestingSpringApp;


import com.example.TestingSpringApp.domain.User;
import com.example.TestingSpringApp.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HelloController {
    private final UsersRepo usersRepo;

    @Autowired
    public HelloController(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        showUsers(model);
        return "main";
    }

    @PostMapping("/add-user")
    public String add(@RequestParam String name, @RequestParam String lastName, @RequestParam String password,
                      @RequestParam String email, Map<String, Object> model) {
        if(usersRepo.findUserByEmail(email) != null) {
            showUsers(model);
            return "main";
        } else {
            User user = new User(name, lastName, password, email);
            usersRepo.save(user);
            showUsers(model);
            return "main";
        }
    }

    @GetMapping("/delete")
    public String deleteAllUsers() {
        usersRepo.deleteAll();
        return "main";
//        showUsers(model);
    }

    private void showUsers(Map<String, Object> model){
        Iterable<User> users = usersRepo.findAll();
        model.put("users",users);
    }

//    @RequestMapping("/login")
//    public String logIn() {
//        return "logIn";
//    }
//
//    @PostMapping("/login")
//    public String auth(@RequestParam String email,
//                       @RequestParam String password) {
//        Iterable<User> users = usersRepo.findUsersByEmail(email);
//        if(users!=null) {
//            for (User user : users) {
//                if (user.getPassword().equals(password)) {
//                    System.out.println("Good");
//                    return "main";
//                } else {
//                    System.out.println("Bad");
//                    return "login";
//                }
//            }
//        }
//        else{
//            return "No such user";
//        }
//        return "logIn";
//    }

    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return usersRepo.findAll();
    }
}
