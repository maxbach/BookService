package web;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;
import validator.UserValidator;

import java.util.Map;

/**
 * Created by maxbacinskiy on 28.02.17.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Map<String, Object> map) {
        map.put("newUser", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String newUser(@ModelAttribute("newUser") User user, BindingResult bindingResult, Map<String, Object> map) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerUser(user);
        return "redirect:/home";
    }
}
