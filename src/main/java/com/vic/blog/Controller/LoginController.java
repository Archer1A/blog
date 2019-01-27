package com.vic.blog.Controller;

import com.vic.blog.model.ArticleCover;
import com.vic.blog.model.User;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping("login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("BootStrapIndex");
        return  modelAndView;
    }

    @GetMapping( "index")
    public String Index(Model modelAndView,HttpServletRequest request){
        List<ArticleCover> articleCovers = new ArrayList<>();
        ArticleCover articleCover = new ArticleCover();
        articleCover.setAddress("云南-丽江");
        articleCover.setTime("28 二月 2018年");
        articleCover.setImgUrl("/img/blue1.jpg");
        articleCovers.add(articleCover);
        modelAndView.addAttribute("articleCovers",articleCovers);
        HttpSession session = request.getSession();
        if (session.getAttribute("userName") != null){
            System.out.println(  session.getAttribute("userName"));

        }else {
            System.out.println("dont have session");
        }
//        modelAndView.addAttribute("address","浙江-丽水");
//        modelAndView.addAttribute("time","2017年");
        return "index";
    }

    @PostMapping("login")
    public ModelAndView login(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
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
       // modelAndView.addObject("userName",userName);
//       / modelAndView = new ModelAndView("")

        HttpSession session = request.getSession();
        if (session.getAttribute("userName") == null){
            session.setAttribute("userName",userName);
        }else {
            session.getAttribute("userName");
            System.out.println("session");
        }
        modelAndView.setViewName("redirect:/index");
        return modelAndView;

    }

}
