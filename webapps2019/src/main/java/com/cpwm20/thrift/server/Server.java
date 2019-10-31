/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.thrift.server;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author cpwm20
 */
public class Server {

    private void start() {
        try {
            TServerSocket serverTransport = new TServerSocket(10000);
            TimeServer.Processor processor = new TimeServer.Processor(new TimeServerImpl());
            Factory protFactory = new TBinaryProtocol.Factory(true, true);

            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
            serverArgs.processor(processor);
            serverArgs.protocolFactory(protFactory);

            TServer server = new TThreadPoolServer(serverArgs);
            System.out.println("Starting server on port 10000 ...");
            server.serve();
        } catch (TTransportException e) {
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        Server srv = new Server();
        srv.start();

    }
}
