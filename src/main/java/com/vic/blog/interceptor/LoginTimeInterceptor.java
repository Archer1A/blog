package com.vic.blog.interceptor;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

/**
 * 创建拦截器
 */
@Component
public class LoginTimeInterceptor extends HandlerInterceptorAdapter {

    /**
     *  该方法在控制器执行前调用，可用于权限控制，登录验证等
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,Object handler) throws ServletException, IOException {
        System.out.println("执行preHandle方法-->01");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("userName");
        String uri =  request.getRequestURL().toString();
        if (user != null){
            return true; //通过拦截器，继续执行
        }else {
            System.out.println(request.getContextPath()+"/login");
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
    }

    /**
     * 后端控制器执行完成后调用
     * @param request
     * @param response
     * @param handle
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request,
                      HttpServletResponse response, Object handle, ModelAndView modelAndView) throws Exception {
        System.out.println("执行postHandle方法-->02");
        super.postHandle(request,response,handle,modelAndView);
    }

    /**
     * 整个请求完成后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception {
        System.out.println("执行afterCompletion方法-->003");
        super.afterCompletion(request,response,handler,ex);
    }
}
