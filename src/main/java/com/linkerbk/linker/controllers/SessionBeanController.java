package com.linkerbk.linker.controllers;

//import com.linkerbk.linker.component.UserSession;
import com.linkerbk.linker.entity.User;
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

//    private final UserSession userSession;
//    public SessionBeanController(UserSession userSession) {
//        this.userSession = userSession;
//    }

    @PostMapping("/set-user")
    public ResponseEntity<String>setUser(@RequestBody User user, HttpSession session) {
        user.setConnectionKey(RandomGenerator.generateUnique6DigitNumber());
        User savedUser = userRepository.save(user); //save into H2DB
        session.setAttribute("userId",savedUser.getId()); //for save only userId in session

        System.out.println( "Saved user" + savedUser); //comment line after testing
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
    public ResponseEntity<String> syncWith(@RequestBody String connectionKey) {

        try {
            logger.info("User provided Key: {}", connectionKey);
            return ResponseEntity.ok().body("Key received successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid connection key: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred", e);
        }
        return ResponseEntity.internalServerError().body("An unexpected error occurred.");
    }


}
