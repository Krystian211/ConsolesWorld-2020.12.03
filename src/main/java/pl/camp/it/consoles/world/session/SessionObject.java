package pl.camp.it.consoles.world.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.consoles.world.model.Product;
import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.InitialUserRegistrationFormData;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionObject {
    private User loggedUser =null;
    private String searchKeyword=null;
    private List<String> messageList=new ArrayList<>();
    private InitialUserRegistrationFormData initialUserRegistrationFormData =new InitialUserRegistrationFormData();
    private Product productData=new Product();
    private List<Product> basket=new ArrayList<>();
    private BigDecimal overallPrice;

    public BigDecimal getTotalProductPrice(Product basketProduct){
        return basketProduct.getPrice().multiply(BigDecimal.valueOf(basketProduct.getPieces()));
    }

    public BigDecimal getOverallPrice() {
        return overallPrice;
    }

    public void setOverallPrice(BigDecimal overallPrice) {
        this.overallPrice = overallPrice;
    }

    public List<Product> getBasket() {
        return basket;
    }

    public void setBasket(List<Product> basket) {
        this.basket = basket;
    }

    public Product getProductData() {
        return productData;
    }

    public Product pollProductData(){
        Product product=(Product) this.productData.clone();
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

    public InitialUserRegistrationFormData getInitialUserRegistrationFormData() {
        return initialUserRegistrationFormData;
    }

    public InitialUserRegistrationFormData pollInitialFormData(){
        InitialUserRegistrationFormData initialUserRegistrationFormData =this.initialUserRegistrationFormData.copy();
        this.initialUserRegistrationFormData.clear();
        return initialUserRegistrationFormData;
    }

    public void setInitialUserRegistrationFormData(InitialUserRegistrationFormData initialUserRegistrationFormData) {
        this.initialUserRegistrationFormData = initialUserRegistrationFormData;
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
        return messageList.size() != 0;
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

    public void logout(){
        this.setLoggedUser(null);
    }
}
