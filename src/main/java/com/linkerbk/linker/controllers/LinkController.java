package com.linkerbk.linker.controllers;

import com.linkerbk.linker.component.UserSession;
import com.linkerbk.linker.entity.User;
import com.linkerbk.linker.repository.UserRepository;
import com.linkerbk.linker.services.RandomGenerator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/linker")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // ✅ Allows credentials for React
public class LinkController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/key")
    public int getRandomLinkNumber(HttpSession session){
        User user = userRepo.getReferenceById((Long) session.getAttribute("userId"));
//        System.out.println("From Linker controller : "+session.getAttribute("userId"));
//        System.out.println("Httpsession during key generation : "+session);
        return user.getConnectionKey();
    }



    @DeleteMapping("/clearSet")
    public ResponseEntity<String> clearSet(){
        RandomGenerator.clearSet();
        return ResponseEntity.status(HttpStatus.OK).body("Cleared");
    }

}
