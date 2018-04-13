package com.jzli.proxy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//编写实现类
@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private IPersonService service;

    public String action(String msg) {
        System.out.println("FooService, method doing.");
        //直接调用方法，无法使用AOP切入
        this.work(msg);

        //使用代理类，调用方法
//        ((IPersonService) AopContext.currentProxy()).work(msg);
        //使用注入的Spring代理对象
        service.work(msg);
        return "[" + msg + "]";
    }

    @Override
    public String work(String msg) {
        System.out.println("work: * " + msg + " *");
        return "* " + msg + " *";
    }

}
