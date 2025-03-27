package com.linkerbk.linker.controllers;

import com.linkerbk.linker.entity.Clipboard;
import com.linkerbk.linker.entity.User;
import com.linkerbk.linker.repository.ClipBoardRepo;
import com.linkerbk.linker.repository.UserRepository;
import com.linkerbk.linker.services.RandomGenerator;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/session")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class SessionBeanController {
    private static final Logger logger = LoggerFactory.getLogger(SessionBeanController.class);


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClipBoardRepo clipRepo;

//    private final UserSession userSession;
//    public SessionBeanController(UserSession userSession) {
//        this.userSession = userSession;
//    }

    @PostMapping("/set-user")
    public ResponseEntity<String> setUser(@RequestBody User user, HttpSession session) {
        user.setConnectionKey(RandomGenerator.generateUnique6DigitNumber());
        User savedUser = userRepository.save(user); //save into H2DB
        session.setAttribute("userId", savedUser.getId()); //for save only userId in session

        System.out.println("Saved user" + savedUser); //comment line after testing
//        userSession.setUserId(savedUser.getId());
//        System.out.println(userSession);
//        System.out.println("Httpsession : "+session);

        return ResponseEntity.ok().body("User set in session bean!");
    }

//    @GetMapping("/get-user")
//    public String getUser() {
//        return userSession.getUsername() != null ? "User: " + userSession.getUsername() : "No user found!";
//    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncWith(@RequestBody String connectionKey, HttpSession session) {
        int Key = Integer.parseInt(connectionKey);
        System.out.println("User passed connection key to sync : " + Key);
        User partnerUser = userRepository.findByConnectionKey(Key)
                .orElseThrow(() -> new RuntimeException("User not found with connectionKey: " + Key));
        Clipboard clipboard = clipRepo.save(new Clipboard());

        try {
            partnerUser.setClipboard(clipboard);
            try {
                userRepository.save(partnerUser);
                System.out.println("Partner details added and saved : " + partnerUser);
            } catch (Exception e) {
                logger.atError().log("Error during save partners clipboard!");
                throw new RuntimeException(e);
            }

            System.out.println("Test Sync : User details : " + session.getAttribute("userId"));
            try {
                User user = userRepository.getReferenceById((Long) session.getAttribute("userId"));
                user.setClipboard(clipboard);
                userRepository.save(user);
            } catch (Exception e) {
                System.out.println("Test : error : error during persist instance of user ");
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok().body("Both system clipboards are synced now.");

        } catch (Exception e) {
            throw new RuntimeException(e);
//            return ResponseEntity.internalServerError().body("An unexpected error occurred.");

        }

//        try {
//            logger.info("User provided Key: {}", connectionKey);
//            return ResponseEntity.ok().body("Key received successfully");
//        } catch (IllegalArgumentException e) {
//            logger.warn("Invalid connection key: {}", e.getMessage());
//        } catch (Exception e) {
//            logger.error("Unexpected error occurred", e);
//        }
    }


}
