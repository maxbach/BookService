package validator;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.UserService;

/**
 * Created by maxbacinskiy on 01.03.17.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Заполните поле");

        if (user.getLogin().length() < 6 || user.getLogin().length() > 32) {
            errors.rejectValue("login", "login.size", "Размер логина от 6 до 32 символов");
        }

        if (userService.getUserByLogin(user.getLogin()) != null) {
            errors.rejectValue("login", "login.repeat", "Пользователь с таким логином уже существует.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "passwprd.size", "Размер пароля от 8 до 32 символов");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "password.different", "Не совпадают введеные пароли");
        }
    }
}
