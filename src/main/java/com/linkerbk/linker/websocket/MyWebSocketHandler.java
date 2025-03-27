package com.linkerbk.linker.websocket;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyWebSocketHandler extends TextWebSocketHandler {

    // Store sessions with their unique session ID
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session); // Store session by session ID
        System.out.println("Client connected: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session,@NonNull org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session.getId()); // Remove session on disconnect
        System.out.println("Client disconnected: " + session.getId());
    }

    // Method to send a message to a specific client by session ID
    public void sendMessageToClient(String sessionId, String message) {
        WebSocketSession session = sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
                System.out.println("Sent message to client " + sessionId);
            } catch (IOException e) {
                System.out.println("IOException : "+ e);
            }
        } else {
            System.out.println("Session not found or closed for ID: " + sessionId);
        }
    }
}

