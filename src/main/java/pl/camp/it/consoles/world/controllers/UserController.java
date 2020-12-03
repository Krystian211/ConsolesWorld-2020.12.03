package pl.camp.it.consoles.world.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.consoles.world.database.IProductRepository;
import pl.camp.it.consoles.world.database.IUserRepository;
import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.EditAccountData;
import pl.camp.it.consoles.world.model.view.InitialFormData;
import pl.camp.it.consoles.world.model.view.LoginData;
import pl.camp.it.consoles.world.model.view.RegistrationData;
import pl.camp.it.consoles.world.session.SessionObject;
import pl.camp.it.consoles.world.validation.Validators;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IProductRepository productRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showLoginForm(Model model){
        model.addAttribute("sessionObject", this.sessionObject);
        model.addAttribute("loginData",new LoginData());
        return "/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String showLoginForm(@ModelAttribute LoginData loginData){
        User loggedUser;
        if ((loggedUser=userRepository.authenticate(loginData))!=null) {
            this.sessionObject.setLoggedUser(loggedUser);
            return "redirect:/main";
        }else {
            this.sessionObject.putMessage("Logowanie nieudane!");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(Model model){
        model.addAttribute("sessionObject", this.sessionObject);
        this.sessionObject.setLoggedUser(null);
        return "redirect:/main";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegistrationForm(Model model){
        if(!this.sessionObject.isLogged()){
            model.addAttribute("sessionObject",this.sessionObject);
            model.addAttribute("registrationData",new RegistrationData(this.sessionObject.pollInitialFormData()));
            return "/register";
        }else {
            return "redirect:/main";
        }

    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String getRegistrationData(@ModelAttribute RegistrationData registrationData){
        List<String> messages;
        if (((messages=Validators.validateRegistrationData(registrationData,userRepository))==null)) {
            userRepository.addUser(registrationData);
            this.sessionObject.putMessage("Zarejestrowano!");
            return "redirect:/login";
        }else {
            this.sessionObject.setInitialFormData(new InitialFormData(registrationData));
            this.sessionObject.putMessages(messages);
            return "redirect:/register";
        }
    }

    @RequestMapping(value="edit-account",method = RequestMethod.GET)
    public String showEditAccountForm(Model model){
        if(this.sessionObject.isLogged()){
            model.addAttribute("sessionObject",this.sessionObject);
            model.addAttribute("editAccountData",new EditAccountData(this.sessionObject.getLoggedUser()));
            return "edit-account";
        }else {
            return "redirect:/main";
        }

    }

    @RequestMapping(value="edit-account",method = RequestMethod.POST)
    public String getEditAccountData(@ModelAttribute EditAccountData editAccountData){
        List<String> messages;
        if (((messages= Validators.validateEditAccountData(editAccountData,userRepository,this.sessionObject.getLoggedUser()))==null)) {
            userRepository.editUser(this.sessionObject.getLoggedUser(),editAccountData);
            this.sessionObject.putMessage("Edytowano dane konta!");
            return "redirect:/main";
        }else {
            this.sessionObject.putMessages(messages);
            return "redirect:/edit-account";
        }
    }

    @RequestMapping(value="buy",method = RequestMethod.GET)
    public String buyProduct(@RequestParam String manufacturerCode){
        this.sessionObject.buyProduct(productRepository.getProductByCode(manufacturerCode));
        return "redirect:/main";
    }

    @RequestMapping(value="basket",method = RequestMethod.GET)
    public String showBasket(Model model){
        if(this.sessionObject.isCustomer()){
            model.addAttribute("sessionObject",this.sessionObject);
            return "basket";
        }else{
            return "redirect:/main";
        }

    }
}
