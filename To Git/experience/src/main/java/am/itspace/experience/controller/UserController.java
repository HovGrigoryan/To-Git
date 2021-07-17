package am.itspace.experience.controller;

import am.itspace.experience.dto.UserReqDto;
import am.itspace.experience.model.User;
import am.itspace.experience.repository.UserRepository;
import am.itspace.experience.service.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          EmailService emailService,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @PostMapping("/user/add")
    public String addUser(@ModelAttribute UserReqDto userReqDto) {
        User user = User.builder()
                .name(userReqDto.getName())
                .surname(userReqDto.getSurname())
                .username(userReqDto.getUsername())
                .password(passwordEncoder.encode(userReqDto.getPassword()))
                .active(false)
                .token(UUID.randomUUID().toString())
                .role(userReqDto.getRole())
                .build();
        userRepository.save(user);
        String link = "http://localhost:8080/user/activate?email=" + user.getUsername() + "&token=" + user.getToken();
        emailService.send(user.getUsername(), "subject", "hello " + user.getName() + " jan" + "please click " + link);
        return "redirect:/?msg=User was added";
    }

    @GetMapping("/user/activate")
    public String activate(@RequestParam("email") String email, @RequestParam("token") String token) {
        Optional<User> byUsername = userRepository.findByUsername(email);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if (user.getToken().equals(token)) {
                user.setActive(true);
                user.setToken("");
                userRepository.save(user);
                return "redirect:/?msg=user was activate";
            }
        }
        return "redirect:/";
    }
}

