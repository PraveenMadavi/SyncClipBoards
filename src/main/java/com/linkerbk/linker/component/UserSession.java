package com.linkerbk.linker.component;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@ToString
@Getter
@Setter
public class UserSession {
    private Long userId;
}
