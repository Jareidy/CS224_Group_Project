package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;

public class UsersXMLHandler {

    public static UserDataParser userDataParser = new UserDataParser();

    public void formatXmlFile(){

        ArrayList<User> parsedUsers = userDataParser.getUsersArrayList();

        Document doc = docCreator();

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
        createXmlFile(doc);

    }

    public void createXmlFile(Document doc){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.getProperty("user.dir")+"/src/res/Users.xml");

            transformer.transform(source, result);
        }
        catch(TransformerException e){
            e.printStackTrace();
        }
    }

    public Document docCreator(){
        Document doc = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            doc = docBuilder.newDocument();
        }
        catch(ParserConfigurationException e){
            e.printStackTrace();
        }
        return doc;
    }
}
