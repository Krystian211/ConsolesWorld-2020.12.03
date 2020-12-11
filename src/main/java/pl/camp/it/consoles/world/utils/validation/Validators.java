package pl.camp.it.consoles.world.utils.validation;

import java.math.BigDecimal;

public class Validators {

    private static String validateString(String string, String stringName, String regexp) {
        String message;
        if ((message=validateIfStringIsEmpty(string,stringName))!=null) {
            return message;
        } else if (!string.matches(regexp)) {
            return "Niepoprawny format pola \"" + stringName + "\"!";
        }
        return null;
    }
     private static String validateIfStringIsEmpty(String string, String stringName){
        if (string.equals("")){
            return "Pole \""+stringName+"\" jest puste!";
        }
        return null;
     }

    public static String validateLogin(String login) {
        return validateString(login, "login", ".{3,}");
    }

    public static String validateEmail(String email) {
        return validateString(email, "email", ".+@[a-zA-Z0-9]{2,}\\.[a-zA-Z]{2,3}");
    }

    public static String validateFirstName(String firstName) {
        return validateString(firstName, "imię", "[A-ZĄĆĘŁŃÓŚŻŹ]+[a-ząćęłńóśżź]{2,}");
    }

    public static String validateLastName(String lastName) {
        return validateString(lastName, "nazwisko", "[A-ZĄĆĘŁŃÓŚŻŹ]+[a-ząćęłńóśżź-]{2,}");
    }

    public static String validatePassword(String password) {
        return validateString(password, "hasło", ".{5,}");
    }

    public static String validateNewPassword(String password) {
        return validateString(password, "nowe hasło", ".{5,}");
    }

    public static String validateCurrentPassword(String currentPassword) {
         return validateIfStringIsEmpty(currentPassword,"aktualne hasło");
    }

    public static String validateRepeatedPassword( String repeatedPassword) {
        return validateIfStringIsEmpty(repeatedPassword,"powtórz hasło");
    }

    public static String validateRepeatedNewPassword(String repeatedPassword) {
        return validateIfStringIsEmpty(repeatedPassword,"powtórz nowe hasło");
    }

    public static String validateProductName(String name) {
       return validateString(name, "nazwa", "[A-ZĄĆĘŁŃÓŚŻŹ]+.+");
    }

    public static String validateManufacturerCode(String manufacturerCode) {
        return validateString(manufacturerCode, "kod producenta", ".{3,}");
    }

    public static String validateBrand(String brand) {
        return validateString(brand, "producent", "[A-ZĄĆĘŁŃÓŚŻŹ]+.+");
    }

    public static String validatePieces(int pieces) {
        if (pieces<=0) {
            return "Ilość produktu musi być większa od zera!";
        }
        return null;
    }

    public static String validatePrice(BigDecimal price) {
        if (price==null||price.compareTo(BigDecimal.valueOf(0))<=0) {
            return "Cena produktu musi być większa od zera!";
        }
        return null;
    }
}
