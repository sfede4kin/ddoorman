package ru.ddoorman.door.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.List;
import java.util.Map;
public class DoorInboundChannelInterceptor implements ChannelInterceptor {
    private final static Logger log = LoggerFactory.getLogger(DoorInboundChannelInterceptor.class);
    @Autowired
    private DoorSessionComponent doorSessionComponent;
    public Message<?> preSend(Message message, MessageChannel channel) throws IllegalArgumentException{
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        log.info("message command " + headerAccessor.getCommand());

        if(StompCommand.CONNECT == headerAccessor.getCommand()){

            var nativeHeaders = (Map<String, List<String>>)message.getHeaders().get("nativeHeaders");
            var doorId = Long.parseLong(nativeHeaders.get("doorId").get(0));
            log.info("door id parsed: {}", doorId);
            if(doorSessionComponent.containsValue(doorId)){
                log.error("door is already connected: {}", doorId);
                throw new IllegalArgumentException("door is already connected");
            }
        }
        return message;
    }
}