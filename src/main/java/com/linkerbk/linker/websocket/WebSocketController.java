package com.linkerbk.linker.websocket;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ws")
public class WebSocketController {

    private final WebSocketService webSocketService;

    public WebSocketController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    // API endpoint to send a message to a specific client
    @PostMapping("/send/{sessionId}")
    public String sendMessageToClient(@PathVariable String sessionId, @RequestParam String message) {
        webSocketService.sendMessageToClient(sessionId, message);
        return "Message sent to client " + sessionId;
    }
}

