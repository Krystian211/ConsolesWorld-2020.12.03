package pl.camp.it.consoles.world.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.consoles.world.model.Product;
import pl.camp.it.consoles.world.services.IProductService;
import pl.camp.it.consoles.world.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MasterController {

    @Autowired
    IProductService productService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/add-product",method = RequestMethod.GET)
    public String showLoginForm(Model model){
        if (this.sessionObject.isMaster()) {
            model.addAttribute("sessionObject", this.sessionObject);
            model.addAttribute("productData",this.sessionObject.pollProductData());
            return "/add-product";
        }else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/add-product",method = RequestMethod.POST)
    public String showLoginForm(@ModelAttribute Product productData){
        List<String> messages;
        if ((messages=this.productService.addProduct(productData))==null) {
            this.sessionObject.putMessage("Dodano nowy produkt!");
        }else {
            this.sessionObject.setProductData(productData);
            this.sessionObject.putMessages(messages);
        }
        return "redirect:/add-product";
    }

    @RequestMapping(value = "/edit-product",method = RequestMethod.GET)
    public String editProduct(@RequestParam int productId, Model model){
        if (this.sessionObject.isMaster()) {
            Product productData=this.productService.getProductById(productId);
            model.addAttribute("sessionObject", this.sessionObject);
            model.addAttribute("productData", productData);
            return "edit-product";
        }else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/edit-product",method = RequestMethod.POST)
    public String getEditProductData(@ModelAttribute Product productData,
                                     @RequestParam int productId){
        List<String> messages;
        productData.setId(productId);
        if ((messages=this.productService.updateProduct(productData))==null) {
            this.sessionObject.putMessage("Edytowano dane produktu!");
        }else {
            this.sessionObject.putMessages(messages);
        }
        return "redirect:/edit-product?productId="+productId;
    }
}
