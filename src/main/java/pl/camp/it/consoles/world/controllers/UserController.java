package pl.camp.it.consoles.world.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.consoles.world.model.view.EditUserAccountData;
import pl.camp.it.consoles.world.model.view.InitialUserRegistrationFormData;
import pl.camp.it.consoles.world.model.view.UserLoginData;
import pl.camp.it.consoles.world.model.view.UserRegistrationData;
import pl.camp.it.consoles.world.services.IProductService;
import pl.camp.it.consoles.world.services.IUserService;
import pl.camp.it.consoles.world.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    IProductService productService;

    @Autowired
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("userLoginData", new UserLoginData());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getLoginForm(@ModelAttribute UserLoginData userLoginData) {
        this.sessionObject.setLoggedUser(this.userService.authenticate(userLoginData));
        if (this.sessionObject.isLogged()) {
            return "redirect:/main";
        } else {
            this.sessionObject.putMessage("Logowanie nieudane!");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        this.sessionObject.logout();
        return "redirect:/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        if (!this.sessionObject.isLogged()) {
            model.addAttribute("sessionObject", this.sessionObject);
            model.addAttribute("userRegistrationData", new UserRegistrationData(this.sessionObject.pollInitialFormData()));
            return "/register";
        } else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String getRegistrationData(@ModelAttribute UserRegistrationData userRegistrationData) {
        List<String> messages;
        if ((messages = userService.registerUser(userRegistrationData)) == null) {
            this.sessionObject.putMessage("Zarejestrowano!");
            return "redirect:/login";
        } else {
            this.sessionObject.setInitialUserRegistrationFormData(new InitialUserRegistrationFormData(userRegistrationData));
            this.sessionObject.putMessages(messages);
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "edit-account", method = RequestMethod.GET)
    public String showEditAccountForm(Model model) {
        if (this.sessionObject.isLogged()) {
            model.addAttribute("sessionObject", this.sessionObject);
            model.addAttribute("editUserAccountData", new EditUserAccountData(this.sessionObject.getLoggedUser()));
            return "edit-account";
        } else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "edit-account", method = RequestMethod.POST)
    public String getEditAccountData(@ModelAttribute EditUserAccountData editUserAccountData) {
        List<String> messages;
        if ((messages=userService.updateUserData(editUserAccountData))==null) {
            this.sessionObject.putMessage("Edytowano dane konta!");
        }else {
            this.sessionObject.putMessages(messages);
        }
        return "redirect:/edit-account";
    }
}
