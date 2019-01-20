package com.vic.blog.Controller;

import com.vic.blog.model.User;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping("login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("BootStrapIndex");
        return  modelAndView;
    }

    @GetMapping("index")
    public ModelAndView Index(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("login")
    public ModelAndView login(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult) {
        System.out.println(user.getUserName());
        if (bindingResult.hasErrors()){
            modelAndView.addObject("error", bindingResult.getFieldError().getDefaultMessage());
            modelAndView.setViewName("BootStrapIndex");
            return modelAndView;
        }
        String userName = user.getUserName();
        String password = user.getPassword();

        if (!"admin".equals(userName)){
            modelAndView.addObject("error", "无此用户！");
            modelAndView.setViewName("BootStrapIndex");
            return modelAndView;
        }
        if(!"123456".equals(password)){
            modelAndView.addObject("error","密码错误！");
            modelAndView.setViewName("BootStrapIndex");
            return modelAndView;
        }
        modelAndView.addObject("userName",userName);
        modelAndView.setViewName("index");
        return modelAndView;

    }

}
