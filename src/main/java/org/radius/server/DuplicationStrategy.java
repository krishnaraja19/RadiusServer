package org.radius.server;

import org.aaa4j.radius.core.packet.Packet;

import java.net.InetSocketAddress;

public interface DuplicationStrategy {

    Result handleRequest(InetSocketAddress clientAddress, Packet requestPacket);

    void handleResponse(InetSocketAddress clientAddress, Packet requestPacket, Packet responsePacket);

    void unhandleRequest(InetSocketAddress clientAddress, Packet requestPacket);


    final class Result {

        private final State state;

        private final Packet responsePacket;

        public Result(State state, Packet responsePacket) {
            this.state = state;
            this.responsePacket = responsePacket;
        }

        public State getState() {
            return state;
        }

        public Packet getResponsePacket() {
            return responsePacket;
        }

        public enum State {
            NEW_REQUEST,
            IN_PROGRESS_REQUEST,
            CACHED_RESPONSE
        }

    }

}