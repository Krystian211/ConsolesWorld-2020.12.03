package pl.camp.it.consoles.world.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.consoles.world.model.Product;
import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.InitialFormData;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionObject {
    private User loggedUser =null;
    private String searchKeyword=null;
    private List<String> messageList=new ArrayList<>();
    private InitialFormData initialFormData=new InitialFormData();
    private Product productData=new Product();
    private List<Product> basket=new ArrayList<>();

    public List<Product> getBasket() {
        return basket;
    }

    public BigDecimal getTotalProductPrice(Product basketProduct){
        return basketProduct.getPrice().multiply(BigDecimal.valueOf(basketProduct.getPieces()));
    }

    public BigDecimal getTotalPrice(){
        BigDecimal totalPrice=BigDecimal.valueOf(0);
        for (Product product : basket) {
            totalPrice=totalPrice.add(getTotalProductPrice(product));
        }
        return totalPrice;
    }

    public void setBasket(List<Product> basket) {
        this.basket = basket;
    }

    public void buyProduct(Product product){
        for (Product basketProduct : basket) {
            if (basketProduct.getManufacturerCode().equals(product.getManufacturerCode())) {
                basketProduct.setPieces(basketProduct.getPieces()+1);
                return;
            }
        }
        Product basketProduct=new Product(product);
        basketProduct.setPieces(1);
        basket.add(basketProduct);
    }

    public Product getProductData() {
        return productData;
    }
    public Product pollProductData(){
        Product product=new Product(this.productData);
        this.productData.setName(null);
        this.productData.setBrand(null);
        this.productData.setCategory(null);
        this.productData.setManufacturerCode(null);
        this.productData.setPieces(0);
        this.productData.setPrice(null);
        return product;
    }

    public void setProductData(Product productData) {
        this.productData = productData;
    }

    public InitialFormData getInitialFormData() {
        return initialFormData;
    }

    public InitialFormData pollInitialFormData(){
        InitialFormData initialFormData=this.initialFormData.copy();
        this.initialFormData.clear();
        return initialFormData;
    }

    public void setInitialFormData(InitialFormData initialFormData) {
        this.initialFormData = initialFormData;
    }

    public SessionObject() {
    }

    public boolean isLogged(){
        return loggedUser !=null;
    }

    public boolean isMaster(){
        if (isLogged()) {
            return loggedUser.isMaster();
        }
        return false;
    }

    public boolean isCustomer(){
        if (isLogged()) {
            return !loggedUser.isMaster();
        }
        return false;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }

    public List<String> getAllMessages(){
        List<String> messages = new ArrayList<>(messageList);
        messageList.clear();
        return messages;
    }

    public boolean isMessageAvailable(){
        if (messageList.size()==0) {
            return false;
        }
        else {
            return true;
        }
    }

    public void putMessage(String message){
        if (message!=null){
            messageList.add(message);
        }
    }

    public void putMessages(List<String> messages){
        if(messages!=null){
            this.messageList.addAll(messages);
        }
    }
}
