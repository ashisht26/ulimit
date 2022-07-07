package com.ulimit.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;

public class Application{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

            ConvertFiles convert = (ConvertFiles) context.getBean("convertFiles");
            convert.convertFiles(args);

    }
}