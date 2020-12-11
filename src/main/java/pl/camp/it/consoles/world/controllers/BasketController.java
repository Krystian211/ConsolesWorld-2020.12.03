package pl.camp.it.consoles.world.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.consoles.world.services.IBasketService;
import pl.camp.it.consoles.world.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class BasketController {

    @Autowired
    IBasketService basketService;

    @Resource
    SessionObject sessionObject;


    @RequestMapping(value="/buy",method = RequestMethod.GET)
    public String buyProduct(@RequestParam int productId){
        this.basketService.addToBasket(productId);
        return "redirect:/main";
    }

    @RequestMapping(value="/basket",method = RequestMethod.GET)
    public String showBasket(Model model){
        if(this.sessionObject.isCustomer()){
            model.addAttribute("sessionObject",this.sessionObject);
            this.sessionObject.setOverallPrice(this.basketService.calculateOverallPrice());
            System.out.println(this.sessionObject.getOverallPrice());
            return "basket";
        }else{
            return "redirect:/main";
        }

    }
}

