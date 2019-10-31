/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.ejb;

import com.cpwm20.thrift.server.TimeClient;
import javax.ejb.Stateful;

/**
 * Service for adding timestamps. Based on milliseconds since epoch.
 * @author cpwm20
 */

@Stateful
public class TimestampService {
    
    private final TimeClient client;

    /**
     * Constructor.
     */
    public TimestampService() {
        client = new TimeClient();
    }
    
    /**
     * Produces a timestamp for the logs.
     */
    public void timestamp() {
        client.start();
    }
    
    /**
     * Inline timestamp for logs.
     * @return Timestamp as string.
     */
    public String inlineTimestamp() {
        return client.time();
    }
}
