package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Example bean class demonstrating bean lifecycle methods.
 */
@Service
@Profile("BeanCycle")
public class MyBean {

    /**
     * Constructor for MyBean class.
     */
    public MyBean () {
        System.out.println("Instantiation");
    }

    /**
     * Method called after the bean is instantiated.
     */
    @PostConstruct
    public void init(){
        System.out.println("Initializing..");
    }

    /**
     * Method called before the bean is destroyed.
     */
    @PreDestroy
    public void destroy(){
        System.out.println("Destroying..");
    }
}

