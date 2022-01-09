package model;
import backend.FileHandling;
import backend.Variables;

public class Login {

    public boolean checkLogin (String username, String password) {
        FileHandling fileHandlingObj = new FileHandling();
        boolean isUserAuthenticated = false;

        String data[] = fileHandlingObj.readFile(Variables.userFileName, username, 1);
        if (data != null) {
            isUserAuthenticated = authenticateUser(password, data[3]);
        }
        return isUserAuthenticated;
    }

    boolean authenticateUser(String typedPassword, String savedPassword) {
        return typedPassword.equals(savedPassword);
    }

}
