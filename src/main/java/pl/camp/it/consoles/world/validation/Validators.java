package pl.camp.it.consoles.world.validation;

import org.apache.commons.codec.digest.DigestUtils;
import pl.camp.it.consoles.world.database.IProductRepository;
import pl.camp.it.consoles.world.database.IUserRepository;
import pl.camp.it.consoles.world.model.Product;
import pl.camp.it.consoles.world.model.User;
import pl.camp.it.consoles.world.model.view.EditAccountData;
import pl.camp.it.consoles.world.model.view.RegistrationData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Validators {

    public static List<String> validateRegistrationData(RegistrationData registerData, IUserRepository userRepository) {
        List<String> messages = new ArrayList<>();
        String message;
        if (((message = validateLogin(registerData.getLogin(), userRepository)) != null)) {
            messages.add(message);
        }
        if (((message = validateEmail(registerData.getEmail(), userRepository)) != null)) {
            messages.add(message);
        }
        if (((message = validateFirstName(registerData.getFirstName())) != null)) {
            messages.add(message);
        }
        if (((message = validateLastName(registerData.getLastName())) != null)) {
            messages.add(message);
        }
        if (((message = validatePassword(registerData.getPassword())) != null)) {
            messages.add(message);
        }
        if (((message = validateRepeatedPassword(registerData.getPassword(), registerData.getRepeatedPassword())) != null)) {
            messages.add(message);
        }
        if (messages.size() == 0) {
            return null;
        } else {
            return messages;
        }
    }

    public static List<String> validateEditAccountData(EditAccountData editAccountData, IUserRepository userRepository, User loggedUser) {
        List<String> messages = new ArrayList<>();
        String message;
        if (((message = validateLogin(editAccountData.getLogin(), userRepository, loggedUser)) != null)) {
            messages.add(message);
        }
        if (((message = validateEmail(editAccountData.getEmail(), userRepository, loggedUser)) != null)) {
            messages.add(message);
        }
        if (((message = validateFirstName(editAccountData.getFirstName())) != null)) {
            messages.add(message);
        }
        if (((message = validateLastName(editAccountData.getLastName())) != null)) {
            messages.add(message);
        }
        if (((message = validateOldPassword(editAccountData.getOldPassword(), userRepository, loggedUser)) != null)) {
            messages.add(message);
        }
        if (!(editAccountData.getNewPassword().equals("") && editAccountData.getRepeatedNewPassword().equals(""))) {
            if (((message = validateNewPassword(editAccountData.getNewPassword())) != null)) {
                messages.add(message);
            }
            if (((message = validateRepeatedNewPassword(editAccountData.getNewPassword(), editAccountData.getRepeatedNewPassword())) != null)) {
                messages.add(message);
            }
        }
        if (messages.size() == 0) {
            return null;
        } else {
            return messages;
        }
    }

    public static List<String> validateNewProductData(Product newProduct, IProductRepository productRepository){
        List<String> messages = new ArrayList<>();
        String message;
        if ((message = validateProductName(newProduct.getName(), productRepository)) != null) {
            messages.add(message);
        }
        if (((message = validateBrand(newProduct.getBrand())) != null)) {
            messages.add(message);
        }
        if ((message = validateManufacturerCode(newProduct.getManufacturerCode(), productRepository)) != null) {
            messages.add(message);
        }
        if (((message = validatePrice(newProduct.getPrice())) != null)) {
            messages.add(message);
        }
        if (((message = validatePieces(newProduct.getPieces())) != null)) {
            messages.add(message);
        }
        if (messages.size() == 0) {
            return null;
        } else {
            return messages;
        }
    }

    public static List<String> validateEditProductData(Product newProduct, IProductRepository productRepository, Product editedProduct){
        List<String> messages = new ArrayList<>();
        String message;
        if ((message = validateProductName(newProduct.getName(), productRepository,editedProduct)) != null) {
            messages.add(message);
        }
        if (((message = validateBrand(newProduct.getBrand())) != null)) {
            messages.add(message);
        }
        if ((message = validateManufacturerCode(newProduct.getManufacturerCode(), productRepository,editedProduct)) != null) {
            messages.add(message);
        }
        if (((message = validatePrice(newProduct.getPrice())) != null)) {
            messages.add(message);
        }
        if (((message = validatePieces(newProduct.getPieces())) != null)) {
            messages.add(message);
        }
        if (messages.size() == 0) {
            return null;
        } else {
            return messages;
        }
    }

    private static String validateString(String string, String stringName, String regexp) {
        if (string.equals("")) {
            return "Pole \"" + stringName + "\" jest puste!";
        } else if (!string.matches(regexp)) {
            return "Niepoprawny format pola \"" + stringName + "\"!";
        }
        return null;
    }

    private static String validateLogin(String login, IUserRepository userRepository, User excludedUser) {
        String message;
        if ((message = validateString(login, "login", ".{3,}")) != null) {
            return message;
        } else {
            if (!userRepository.isLoginAvailableExcludingUser(login, excludedUser)) {
                return "Login jest zajęty!";
            }
        }
        return null;
    }

    private static String validateLogin(String login, IUserRepository userRepository) {
        String message;
        if ((message = validateString(login, "login", ".{3,}")) != null) {
            return message;
        } else {
            if (!userRepository.isLoginAvailable(login)) {
                return "Login jest zajęty!";
            }
        }
        return null;
    }

    private static String validateEmail(String email, IUserRepository userRepository) {
        String message;
        if ((message = validateString(email, "email", ".+@[a-zA-Z0-9]{2,}\\.[a-zA-Z]{2,3}")) != null) {
            return message;
        } else {
            if (!userRepository.isLoginAvailable(email)) {
                return "Email jest zajęty!";
            }
        }
        return null;
    }

    private static String validateEmail(String email, IUserRepository userRepository, User excludedUser) {
        String message;
        if ((message = validateString(email, "email", ".+@[a-zA-Z0-9]{2,}\\.[a-zA-Z]{2,3}")) != null) {
            return message;
        } else {
            if (!userRepository.isLoginAvailableExcludingUser(email, excludedUser)) {
                return "Email jest zajęty!";
            }
        }
        return null;
    }

    private static String validateFirstName(String firstName) {
        return validateString(firstName, "imię", "[A-ZĄĆĘŁŃÓŚŻŹ]+[a-ząćęłńóśżź]{2,}");
    }

    private static String validateLastName(String lastName) {
        return validateString(lastName, "nazwisko", "[A-ZĄĆĘŁŃÓŚŻŹ]+[a-ząćęłńóśżź-]{2,}");
    }

    private static String validatePassword(String password) {
        return validateString(password, "hasło", ".{5,}");
    }

    private static String validateNewPassword(String password) {
        return validateString(password, "nowe hasło", ".{5,}");
    }

    private static String validateOldPassword(String oldPassword, IUserRepository userRepository, User loggedUser) {
        if (oldPassword.equals("")) {
            return "Pole \"stare hasło\" jest puste!";
        } else {
            User dataBaseUser = userRepository.findByLogin(loggedUser.getLogin());
            if (!dataBaseUser.getPassword().equals(DigestUtils.md5Hex(oldPassword))) {
                return "Podano niepoprawne stare hasło!";
            } else {
                return null;
            }
        }

    }

    private static String validateRepeatedPassword(String password, String repeatedPassword) {
        if (repeatedPassword.equals("")) {
            return "Pole \"powtórz hasło\" jest puste!";
        } else {
            if (!password.equals(repeatedPassword)) {
                return "Podane hasła różnią się!";
            }
        }
        return null;
    }

    private static String validateRepeatedNewPassword(String password, String repeatedPassword) {
        if (repeatedPassword.equals("")) {
            return "Pole \"powtórz nowe hasło\" jest puste!";
        } else {
            if (!password.equals(repeatedPassword)) {
                return "Podane nowe hasła różnią się!";
            }
        }
        return null;
    }

    private static String validateProductName(String name, IProductRepository productRepository) {
        String message;
        if ((message = validateString(name, "nazwa", "[A-ZĄĆĘŁŃÓŚŻŹ]+.+")) != null) {
            return message;
        } else {
            if (!productRepository.isNameAvailable(name)) {
                return "Nazwa produktu jest zajęta!";
            }
        }
        return null;
    }

    private static String validateProductName(String name, IProductRepository productRepository,Product excludedProduct) {
        String message;
        if ((message = validateString(name, "nazwa", "[A-ZĄĆĘŁŃÓŚŻŹ]+.+")) != null) {
            return message;
        } else {
            if (!productRepository.isNameAvailableExcludingProduct(name,excludedProduct)) {
                return "Nazwa produktu jest zajęta!";
            }
        }
        return null;
    }

    private static String validateManufacturerCode(String manufacturerCode, IProductRepository productRepository) {
        String message;
        if ((message = validateString(manufacturerCode, "kod producenta", ".{3,}")) != null) {
            return message;
        } else {
            if (!productRepository.isManufacturerCodeAvailable(manufacturerCode)) {
                return "Kod producenta jest zajęty!";
            }
        }
        return null;
    }

    private static String validateManufacturerCode(String manufacturerCode, IProductRepository productRepository,Product excludedProduct) {
        String message;
        if ((message = validateString(manufacturerCode, "kod producenta", ".{3,}")) != null) {
            return message;
        } else {
            if (!productRepository.isManufacturerCodeAvailableExcludingProduct(manufacturerCode,excludedProduct)) {
                return "Kod producenta jest zajęty!";
            }
        }
        return null;
    }

    private static String validateBrand(String brand) {
        return validateString(brand, "producent", "[A-ZĄĆĘŁŃÓŚŻŹ]+.+");
    }

    private static String validatePieces(int pieces) {
        if (pieces<=0) {
            return "Ilość produktu musi być większa od zera!";
        }
        return null;
    }

    private static String validatePrice(BigDecimal price) {
        if (price==null||price.compareTo(BigDecimal.valueOf(0))<=0) {
            return "Cena produktu musi być większa od zera!";
        }
        return null;
    }
}
