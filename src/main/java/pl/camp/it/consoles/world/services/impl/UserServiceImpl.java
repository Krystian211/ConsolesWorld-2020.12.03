package pl.camp.it.consoles.world.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.consoles.world.dao.IUserDAO;
import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.EditUserAccountData;
import pl.camp.it.consoles.world.model.view.UserLoginData;
import pl.camp.it.consoles.world.model.view.UserRegistrationData;
import pl.camp.it.consoles.world.services.IUserService;
import pl.camp.it.consoles.world.session.SessionObject;
import pl.camp.it.consoles.world.utils.validation.Validators;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public User authenticate(UserLoginData userLoginData) {
        User dbUser;
        if ((dbUser = this.userDAO.getUserByLogin(userLoginData.getLogin().toLowerCase())) != null &&
                dbUser.getPassword().equals(userLoginData.getPassword())) {
            return dbUser;
        }else {
            return null;
        }
    }

    @Override
    public List<String> registerUser(UserRegistrationData userRegistrationData) {
        List<String> messages;
        if ((messages=validateUserRegistrationData(userRegistrationData))==null) {
            userDAO.persistUser(new User(0,
                    userRegistrationData.getLogin().toLowerCase(),
                    DigestUtils.md5Hex(userRegistrationData.getPassword()),
                    userRegistrationData.getFirstName(),
                    userRegistrationData.getLastName(),
                    userRegistrationData.getEmail(),
                    false));
        }
        return messages;
    }

    @Override
    public List<String> updateUserData(EditUserAccountData editUserAccountData) {
        if (!this.sessionObject.isLogged()) {
            return null;
        }
        List<String> messages;
        if ((messages=validateEditUserAccountData(editUserAccountData))==null) {
            if (this.authenticate(new UserLoginData(this.sessionObject.getLoggedUser().getLogin(),
                    editUserAccountData.getCurrentPassword()))!=null) {
                User updatedUser=new User();
                updatedUser.setId(this.sessionObject.getLoggedUser().getId());
                updatedUser.setLogin(editUserAccountData.getLogin().toLowerCase());
                updatedUser.setFirstName(editUserAccountData.getFirstName());
                updatedUser.setLastName(editUserAccountData.getLastName());
                updatedUser.setEmail(editUserAccountData.getEmail());
                updatedUser.setMaster(this.sessionObject.getLoggedUser().isMaster());
                if(editUserAccountData.getNewPassword().equals("")){
                    updatedUser.setPassword(DigestUtils.md5Hex(editUserAccountData.getCurrentPassword()));
                }else {
                    updatedUser.setPassword(DigestUtils.md5Hex(editUserAccountData.getNewPassword()));
                }
                this.userDAO.updateUser(updatedUser);
                this.sessionObject.setLoggedUser(updatedUser);
            }else {
                messages=new ArrayList<>();
                messages.add("Niepoprawne aktualne hasło!");
            }
        }
        return messages;
    }

    private List<String> validateEditUserAccountData(EditUserAccountData editUserAccountData){
        List<String> messageList = new ArrayList<>();
        String message;
        if ((message = Validators.validateLogin(editUserAccountData.getLogin().toLowerCase())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateFirstName(editUserAccountData.getFirstName())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateLastName(editUserAccountData.getLastName())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateEmail(editUserAccountData.getEmail())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateCurrentPassword(editUserAccountData.getCurrentPassword())) != null) {
            messageList.add(message);
        }
        if (!(editUserAccountData.getNewPassword().equals("")&&editUserAccountData.getRepeatedNewPassword().equals(""))){
            if ((message = Validators.validateNewPassword(editUserAccountData.getNewPassword())) != null) {
                messageList.add(message);
            }
            if ((message = Validators.validateRepeatedNewPassword(editUserAccountData.getRepeatedNewPassword())) != null) {
                messageList.add(message);
            }else if(!editUserAccountData.getNewPassword().equals(editUserAccountData.getRepeatedNewPassword())){
                messageList.add("Nowe hasła różnią się!");
            }
        }
        if (messageList.size()==0) {
            return null;
        }
        return messageList;
    }

    private List<String> validateUserRegistrationData(UserRegistrationData userRegistrationData) {
        List<String> messageList = new ArrayList<>();
        String message;
        if ((message = Validators.validateLogin(userRegistrationData.getLogin().toLowerCase())) != null) {
            messageList.add(message);
        } else if (userDAO.getUserByLogin(userRegistrationData.getLogin().toLowerCase()) != null) {
            messageList.add("Login jest zajęty!");
        }
        if ((message = Validators.validateFirstName(userRegistrationData.getFirstName())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateLastName(userRegistrationData.getLastName())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateEmail(userRegistrationData.getEmail())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validatePassword(userRegistrationData.getPassword())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateEmail(userRegistrationData.getEmail())) != null) {
            messageList.add(message);
        }
        if ((message = Validators.validateRepeatedPassword(userRegistrationData.getRepeatedPassword())) != null) {
            messageList.add(message);
        } else if (!(userRegistrationData.getPassword().equals(userRegistrationData.getRepeatedPassword()))) {
            messageList.add("Podane hasła różnią się!");
        }
        if (messageList.size()==0) {
            return null;
        }
        return messageList;
    }
}
