package model.user;

import model.XMLBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class UsersXMLHandler {

    public static UserDataParser userDataParser = new UserDataParser();
    XMLBase xmlCreator = new XMLBase();

    public void formatXmlFile(String localFileName){

        ArrayList<User> parsedUsers = UserManager.returnUsers();

        Document doc = xmlCreator.docCreator();

        Element rootElement = doc.createElement("users");
        doc.appendChild(rootElement);
        for (User parsedUser: parsedUsers) {
            Element users = doc.createElement("username");
            rootElement.appendChild(users);
            users.setAttribute("username", parsedUser.getUsername());

            Element password = doc.createElement("password");
            password.appendChild((doc.createTextNode(parsedUser.getPassword())));
            users.appendChild(password);

            Element emailAddress = doc.createElement("emailAddress");
            emailAddress.appendChild((doc.createTextNode(parsedUser.getEmailAddress())));
            users.appendChild(emailAddress);
        }
        xmlCreator.createXmlFile(doc, localFileName);

    }
}
