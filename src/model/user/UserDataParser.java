package model.user;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class UserDataParser {

    public static final UserManager users = new UserManager();
    private static Document document;

    public static void parseUserData() {
        try{
            readXMLFile();
        }catch(IOException | SAXException | ParserConfigurationException e){
            e.printStackTrace();
        }
    }

    private static void readXMLFile() throws IOException, SAXException, ParserConfigurationException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(System.getProperty("user.dir")+"/src/res/Users.xml");
            readImageFromDocument();
    }

    private static void readImageFromDocument() {
        NodeList userNodes = document.getElementsByTagName("username");
        for(int i = 0; i<userNodes.getLength();i++){
            Node userNode = userNodes.item(i);
            if(userNode.getNodeType()==Node.ELEMENT_NODE){
                Element userElement = (Element) userNode;
                String username =userElement.getAttribute("username");
                String password = userElement.getElementsByTagName("password").item(0).getTextContent();
                String emailAddress = userElement.getElementsByTagName("emailAddress").item(0).getTextContent();
                User newUser = new User(username,password,emailAddress);
                users.addUser(newUser);
            }
        }
    }

}
