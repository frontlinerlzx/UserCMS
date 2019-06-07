package com.frontlinerlzx.controller;


import com.frontlinerlzx.domain.SysLog;
import com.frontlinerlzx.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime;//开始时间
    private Class clazz;
    private Method method;

    //前置通知:主要获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.frontlinerlzx.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); //获取当前时间
        clazz = jp.getTarget().getClass();//具体访问的类对象
        String methodName = jp.getSignature().getName();//获取方法名字

        Object[] args = jp.getArgs();//获取访问方法的参数

        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArg = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArg[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArg);
        }

    }

    //后置通知：
    @After("execution(* com.frontlinerlzx.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        String url = "";
        long time = new Date().getTime() - visitTime.getTime();//获取访问的时长

        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                String[] clazzValue = clazzAnnotation.value();

                //后去方法上的@Requestmapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = clazzValue[0] + methodValue[0];


                    //获取ip地址
                    String ip = request.getRemoteAddr();
                    //获取操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文获取当前登陆的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
//        将日志相关信息封装到SysLog
                    SysLog sysLog = new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setExecutionTime(time);
                    sysLog.setUsername(username);
                    sysLog.setUrl(url);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());

                    //调用Service完成日子记录
                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
