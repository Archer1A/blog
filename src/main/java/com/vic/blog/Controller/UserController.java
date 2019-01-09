package com.vic.blog.Controller;

import com.vic.blog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public String getUsers(Model model){
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i <10; i++) {
            User user = new User();
            user.setAddress("浙江丽水");
            user.setName("Vic");
            user.setAge("18");
            user.setId(1);
            userList.add(user);

        }
        model.addAttribute("users", userList);
        return "/user/list";

    }

}
