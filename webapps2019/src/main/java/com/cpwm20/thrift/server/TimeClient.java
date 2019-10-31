/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.thrift.server;

import com.cpwm20.thrift.server.TimeServer.Client;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author cpwm20
 */
public class TimeClient {

    /**
     * Starts the client and outputs time.
     */
    public void start() {
        TTransport transport;
        try {
            transport = new TSocket("localhost", 10000);
            TProtocol protocol = new TBinaryProtocol(transport);
            Client client = new Client(protocol);
            transport.open();
            long time = client.time();
            System.out.println("Time from server:" + time);
            transport.close();
        } catch (TException e) {
            System.out.println("Could not get time. Please restart the server.");
        }
    }

    /**
     * Gets the time
     * @return Time value
     */
    public String time() {
        TTransport transport;
        try {
            transport = new TSocket("localhost", 10000);
            TProtocol protocol = new TBinaryProtocol(transport);
            Client client = new Client(protocol);
            transport.open();
            long time = client.time();
            transport.close();

            return String.valueOf(time);
        } catch (TException e) {
            return "Could not get time. Please restart the server.";
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        TimeClient c = new TimeClient();
        c.start();
    }
}
