package pl.coderslab.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = "/login")
    String showLoginForm() {
        return "auth/login";
    }

    @GetMapping(path = "/403")
    String showAccessDeniedPage() {
        return "auth/403";
    }

}
