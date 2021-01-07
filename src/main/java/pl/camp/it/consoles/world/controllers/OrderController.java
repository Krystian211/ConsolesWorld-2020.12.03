package pl.camp.it.consoles.world.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.consoles.world.services.IOrderService;
import pl.camp.it.consoles.world.session.SessionObject;

@Controller
public class OrderController {

    @Autowired
    SessionObject sessionObject;

    @Autowired
    IOrderService orderService;

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public String showOrders(Model model){
        if (this.sessionObject.isCustomer()) {
            model.addAttribute("sessionObject", this.sessionObject);
            model.addAttribute("orders",this.orderService.getOrdersForCurrentUser());
            System.out.println(this.orderService.getOrdersForCurrentUser().get(0));
            return "orders";
        }else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/confirm-order",method = RequestMethod.GET)
    public String confirmOrder(Model model){
        model.addAttribute("sessionObject", this.sessionObject);
        this.orderService.confirmOrder();
        return "redirect:/basket";
    }
}
