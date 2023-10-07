package org.radius.server;

import org.aaa4j.radius.core.packet.Packet;

import java.net.InetAddress;

public interface RadiusServer {

    void start() throws InterruptedException;
    void stop() throws InterruptedException;
    boolean isRunning();
    interface Handler {
        byte[] handleClient(InetAddress clientAddress);
        Packet handlePacket(InetAddress clientAddress, Packet requestPacket);
        default void handleException(Exception exception) {
            exception.printStackTrace();
        }
    }

}