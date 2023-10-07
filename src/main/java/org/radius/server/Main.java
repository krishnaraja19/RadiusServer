package org.radius.server;


import org.aaa4j.radius.core.attribute.attributes.UserName;
import org.aaa4j.radius.core.attribute.attributes.UserPassword;
import org.aaa4j.radius.core.packet.Packet;
import org.aaa4j.radius.core.packet.packets.AccessAccept;
import org.aaa4j.radius.core.packet.packets.AccessReject;
import org.aaa4j.radius.core.packet.packets.AccessRequest;
import org.radius.server.servers.AccessFailed;
import org.radius.server.servers.UdpRadiusServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        RadiusServer radiusServer = UdpRadiusServer.newBuilder()
                .bindAddress(new InetSocketAddress(1812))
                .handler(new RadiusHandler())
                .build();

        radiusServer.start();

    }

    private static class RadiusHandler implements RadiusServer.Handler {

        @Override
        public byte[] handleClient(InetAddress clientAddress) {
            if (clientAddress.getHostAddress().equals("127.0.0.1")) {
                return "sharedsecret".getBytes(UTF_8);
            }

            return null;
        }

        @Override
        public Packet handlePacket(InetAddress clientAddress, Packet requestPacket) {
            if (requestPacket instanceof AccessRequest) {
                Optional<UserName> userNameAttribute = requestPacket.getAttribute(UserName.class);
                Optional<UserPassword> userPasswordAttribute = requestPacket.getAttribute(UserPassword.class);

                if (userNameAttribute.isPresent() && userPasswordAttribute.isPresent()) {
                    String username = userNameAttribute.get().getData().getValue();
                    String password = new String(userPasswordAttribute.get().getData().getValue(), UTF_8);

                    if (username.equals("frans1") && password.equals("fran123!")) {
                        return new AccessAccept();
                    } else if (username.equals("frans2") && password.equals("fran123!")) {
                        return new AccessAccept();
                    } else if(!password.isEmpty()){
                        return new AccessFailed();
                    }

                }

                return new AccessReject();
            }

            return null;
        }

    }
}