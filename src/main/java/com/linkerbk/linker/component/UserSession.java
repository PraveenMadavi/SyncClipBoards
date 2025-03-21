package com.linkerbk.linker.component;


import com.linkerbk.linker.models.Clipboard;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@ToString
public class UserSession {
    private String username;
    private Clipboard clipboard;


    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
