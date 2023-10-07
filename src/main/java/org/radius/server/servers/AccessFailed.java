package org.radius.server.servers;

import org.aaa4j.radius.core.attribute.Attribute;
import org.aaa4j.radius.core.packet.Packet;

import java.util.List;

public class AccessFailed extends Packet {

    public static final int CODE = 6;
    public static final String NAME = "Access-Failed";

    public AccessFailed() {
        super(6);
    }

    public AccessFailed(List<Attribute<?>> attributes) {
        super(6, attributes);
    }

    public AccessFailed(List<Attribute<?>> attributes, Packet.ReceivedFields receivedFields) {
        super(6, attributes, receivedFields);
    }
}
