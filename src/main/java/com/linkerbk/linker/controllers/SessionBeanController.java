package com.linkerbk.linker.controllers;

import com.linkerbk.linker.component.UserSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
@CrossOrigin ("*")
public class SessionBeanController {

    private final UserSession userSession;

    public SessionBeanController(UserSession userSession) {
        this.userSession = userSession;
    }

    @PostMapping("/set-user")
    public ResponseEntity<String>setUser(@RequestBody String name) {
        userSession.setUsername(name);
        System.out.println(userSession);
        return ResponseEntity.ok().body("User set in session bean!");
    }

    @GetMapping("/get-user")
    public String getUser() {
        return userSession.getUsername() != null ? "User: " + userSession.getUsername() : "No user found!";
    }

    @PostMapping("/sync")
    public String SyncWith(@RequestBody String connectionKey){

        return "edit";
    }

}
