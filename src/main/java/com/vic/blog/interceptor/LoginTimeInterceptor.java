package com.vic.blog.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * 创建拦截器
 */
public class LoginTimeInterceptor extends HandlerInterceptorAdapter {

    private int startTime;

    private int endTime;

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,Object handler) throws ServletException, IOException {
        System.out.println("执行preHandle方法-->01");
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (startTime<=hour &&hour<endTime){
            return true; //通过拦截器，继续执行
        }else {
            request.setAttribute("msg", "非登录时段");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
    }
    public void postHandle(HttpServletRequest request,
                      HttpServletResponse response, Object handle, ModelAndView modelAndView) throws Exception {
        System.out.println("执行postHandle方法-->02");
        super.postHandle(request,response,handle,modelAndView);
    }

    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception {
        System.out.println("执行afterCompletion方法-->003");
        super.afterCompletion(request,response,handler,ex);
    }
}
