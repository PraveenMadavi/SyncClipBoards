package com.linkerbk.linker.controllers;

import com.linkerbk.linker.services.RandomGenerator;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/linker")
@CrossOrigin("*") // Allows all origins (I am using this only for development purpose)
public class LinkController {

    @GetMapping("/key")
    public int getRandomLinkNumber(HttpSession session){
        return RandomGenerator.generateUnique6DigitNumber();
    }



    @DeleteMapping("/clearSet")
    public ResponseEntity<String> clearSet(){
        RandomGenerator.clearSet();
        return ResponseEntity.status(HttpStatus.OK).body("Cleared");
    }

}
