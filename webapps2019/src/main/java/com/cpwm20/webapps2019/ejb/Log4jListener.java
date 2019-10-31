/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.ejb;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * Servlet Listener for log4j.
 * @author cpwm20
 */
public class Log4jListener implements ServletContextListener {

    /**
     *
     * @param arg
     */
    @Override
    public void contextInitialized(ServletContextEvent arg) {
        System.out.println("contextInitialized...");

        //remove the jsf root logger, avoid duplicated logging
        //try comment out this and see the different on the console
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    /**
     *
     * @param arg
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg) {
        System.out.println("contextDestroyed...");

    }

}
