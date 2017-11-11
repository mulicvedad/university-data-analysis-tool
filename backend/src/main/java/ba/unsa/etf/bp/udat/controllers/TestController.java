package ba.unsa.etf.bp.udat.controllers;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
       return "Pozdrav";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestController.class, args);
    }
}