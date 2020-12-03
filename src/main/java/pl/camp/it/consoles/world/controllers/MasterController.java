package pl.camp.it.consoles.world.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.consoles.world.database.IProductRepository;
import pl.camp.it.consoles.world.database.IUserRepository;
import pl.camp.it.consoles.world.model.Product;
import pl.camp.it.consoles.world.session.SessionObject;
import pl.camp.it.consoles.world.validation.Validators;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MasterController {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IProductRepository productRepository;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/add-product",method = RequestMethod.GET)
    public String showLoginForm(Model model){
        if (this.sessionObject.isMaster()) {
            model.addAttribute("sessionObject", this.sessionObject);
            model.addAttribute("product",new Product(this.sessionObject.pollProductData()));
            return "/add-product";
        }else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/add-product",method = RequestMethod.POST)
    public String showLoginForm(@ModelAttribute Product product){
        List<String> messages;
        if (((messages= Validators.validateNewProductData(product,productRepository))==null)) {
            productRepository.addProduct(product);
            this.sessionObject.putMessage("Dodano nowy produkt!");
        }else {
            this.sessionObject.setProductData(product);
            this.sessionObject.putMessages(messages);
        }
        return "redirect:/add-product";
    }

    @RequestMapping(value = "/edit-product",method = RequestMethod.GET)
    public String editProduct(@RequestParam String code, Model model){
        if (this.sessionObject.isMaster()) {
            Product product=this.productRepository.getProductByCode(code);
            model.addAttribute("sessionObject", this.sessionObject);
            model.addAttribute("productData", product);
            return "edit-product";
        }else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/edit-product",method = RequestMethod.POST)
    public String getEditProductData(@ModelAttribute Product productData,
                                     @RequestParam String code){
        Product editedProduct=productRepository.getProductByCode(code);
        List<String> messages;
        if (((messages= Validators.validateEditProductData(productData,productRepository,editedProduct))==null)) {
            productRepository.editProduct(editedProduct,productData);
            this.sessionObject.putMessage("Edytowano dane produktu!");
            return "redirect:/main";
        }else {
            this.sessionObject.putMessages(messages);
            return "redirect:/edit-product?code="+code;
        }

    }

}
