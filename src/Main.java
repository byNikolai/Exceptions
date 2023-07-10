import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;

public class Main {
    private static final String VALIDATE_PATTERN = "^[a-zA-Z0-9-_]+$";
    public static void main(String[] args) {
        privateInfoCheck("lol", "lolpass", "lolpass");
        privateInfoCheck("KJLHGBkjbhsdf8679fvsdgsgzfvsdf", "qwerty", "qwerty");
        privateInfoCheck("dsf/s32-0165236879=++'%", "qwerty", "qwerty");
        privateInfoCheck("admin", "qwerty", "qwerty1");
        privateInfoCheck("admin", "qwerty]'%^&*(", "qwerty]'%^&*(");
        privateInfoCheck("admin", "qwertyqwertyqwertyqwerty", "qwertyqwertyqwertyqwerty");
    }

    private static boolean privateInfoCheck(String login, String password, String passwordConfirm) {
        boolean validation = true;
        try {
            loginCheck(login);
            passwordCheck(password, passwordConfirm);
        } catch (WrongLoginException e) {
            System.out.println("Login Error: " + e.getMessage());
            validation = false;
        } catch (WrongPasswordException e) {
            System.out.println("Password Error: " + e.getMessage());
            validation = false;
        }
        return validation;
    }

    private static void loginCheck(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException("Login должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Login должен быть равен или меньше 20 символов.");
        }
    }

    private static void passwordCheck(String password, String passwordConfirm) throws WrongPasswordException {
        if (!password.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException("Password должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } else if (password.length() > 20) {
            throw new WrongPasswordException("Password должен быть равен или меньше 20 символов.");
        } else if (!password.equals(passwordConfirm)) {
            throw new WrongPasswordException("Параметры password и confirmPassword должны быть равны.");
        }

    }
}