package pl.camp.it.consoles.world.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.consoles.world.services.IProductService;
import pl.camp.it.consoles.world.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectToMain() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String showProductsByCategory(Model model,
                                         @RequestParam(defaultValue = "all") String category) {
        model.addAttribute("products", this.productService.getProductsByCategoryAndKeyword(category,this.sessionObject.getSearchKeyword()));
        model.addAttribute("sessionObject", this.sessionObject);
        return "mainPage";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String showContact(Model model) {
        model.addAttribute("sessionObject", this.sessionObject);
        return "contact.html";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String searchKeyword){
        sessionObject.setSearchKeyword(searchKeyword);
    return "redirect:/main";
    }

}
