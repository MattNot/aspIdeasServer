package unical.matteonotaro.aspIdeasServer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import unical.matteonotaro.aspIdeasServer.dto.Login;
import unical.matteonotaro.aspIdeasServer.dto.User;
import unical.matteonotaro.aspIdeasServer.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@Slf4j
public class LoginController {
    private final HttpServletRequest request;

    final
    UserRepository userRepository;

    public LoginController(UserRepository userRepository, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.request = request;
    }

    @PostMapping(value = "/newUser")
    public void register(@RequestBody Login login) {
        User u = new User();
        u.setPassword(login.getPassword());
        u.setUsername(login.getUsername());
        u.setRelatedProjects(new ArrayList<>());
        userRepository.save(u);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Optional<User> optionalUser = userRepository.findByUsername(login.getUsername());
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        if (login.getPassword().equals(user.getPassword())) {
            request.getSession().setAttribute("USER_ID", user.getId());
            log.info("New user " + user.getId());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }
}
