package com.yberdaliyev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yerlan on 01.03.2017.
 */
public class Main {
    public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//       TextEditor textEditor = (TextEditor) context.getBean("textEditor");

        JavaCollection javaCollection = (JavaCollection) context.getBean("javaCollection");

    }
}
