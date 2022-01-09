package model;

import backend.FileHandling;
import backend.Variables;

public class Register {

    public boolean registerUser(String[] userData) {
        FileHandling fileHandlingObj = new FileHandling();
        return fileHandlingObj.create(Variables.userFileName, userData);
    }

}
