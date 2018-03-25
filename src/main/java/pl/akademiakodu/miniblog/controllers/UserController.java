package pl.akademiakodu.miniblog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.akademiakodu.miniblog.model.entities.User;
import pl.akademiakodu.miniblog.model.forms.LoginForm;
import pl.akademiakodu.miniblog.model.forms.RegisterForm;
import pl.akademiakodu.miniblog.model.repositories.UserRepository;
import pl.akademiakodu.miniblog.services.UserSessionService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    public UserController(UserRepository userRepository, UserSessionService userSessionService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionService;
        this.modelMapper = modelMapper;
    }

    private UserRepository userRepository;
    private UserSessionService userSessionService;
    private ModelMapper modelMapper;


    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid RegisterForm registerForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "register";
        }

        User user = modelMapper.map(registerForm, User.class);
        userRepository.save(user);
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult, Model model){

        boolean logged = userSessionService.loginUser(loginForm.getUserName(), loginForm.getPassword());
        if(!logged){
            bindingResult.rejectValue("userName", null,"Uzytkownik nie istnieje.");
        }

        if(bindingResult.hasErrors()){
            return "login";
        }

        model.addAttribute("loggedUser", logged);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
}
