package com.example.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class LoginAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* com.example.app.controller.*.*(..)) "
            + "&& !within(com.example.app.controller.admin..*)"
            + "&& !within(com.example.app.controller.RootController)")
    public void controllerMethodsExceptLoginController() {}

    @Around("controllerMethodsExceptLoginController()")
    public Object checkLogin(
    		ProceedingJoinPoint joinPoint
    ) throws Throwable {
        System.out.println("*************check****************" + joinPoint);
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // ログインしていない場合は、ログインページへのリダイレクトを行う
            return "redirect:/timeout";
        } else {
            // ログイン済みの場合は、通常の処理を続行
            return joinPoint.proceed();
        }
    }

}