package com.linkerbk.linker.websocket;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final MyWebSocketHandler webSocketHandler;

    public WebSocketService(MyWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    // Public method to send messages to a specific client
    public void sendMessageToClient(String sessionId, String message) {
        webSocketHandler.sendMessageToClient(sessionId, message);
    }
}
