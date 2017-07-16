package model.user;

import model.XMLBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class UsersXMLHandler {

    XMLBase xmlCreator = new XMLBase();
    Document doc;
    Element users;

    public void formatXmlFile(String localFileName){
        ArrayList<User> parsedUsers = UserManager.getUsers();
        doc = xmlCreator.docCreator();
        Element rootElement = doc.createElement("users");
        doc.appendChild(rootElement);
        for (User parsedUser: parsedUsers) {
            createUserElement(rootElement,parsedUser);
            formatPassword(users,parsedUser);
            formatEmailAddress(users,parsedUser);
        }
        createFile(localFileName);
    }

    private void createUserElement(Element rootElement,User parsedUser) {
        users = doc.createElement("username");
        rootElement.appendChild(users);
        users.setAttribute("username", parsedUser.getUsername());
    }

    private void formatPassword(Element users, User parsedUser) {
        Element password = doc.createElement("password");
        password.appendChild((doc.createTextNode(parsedUser.getPassword())));
        users.appendChild(password);
    }

    private void formatEmailAddress(Element users,User parsedUser) {
        Element emailAddress = doc.createElement("emailAddress");
        emailAddress.appendChild((doc.createTextNode(parsedUser.getEmailAddress())));
        users.appendChild(emailAddress);
    }

    public void createFile(String localFileName){
        xmlCreator.createXmlFile(doc, localFileName);
    }
}
