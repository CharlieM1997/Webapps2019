/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.thrift.server;

import org.apache.thrift.TException;

class TimeServerImpl implements TimeServer.Iface {

    @Override
    public long time() throws TException {
        long time = System.currentTimeMillis();
        System.out.println("time() called: " + time);
        return time;
    }
}
