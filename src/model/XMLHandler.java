package model;

import org.w3c.dom.Attr;
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
import java.io.File;

public class XMLHandler {

    public static void xmlWriter(){

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("pictures");
            doc.appendChild(rootElement);

            Element pictures = doc.createElement("picture");
            rootElement.appendChild(pictures);
            pictures.setAttribute("pictureName", "0");

            Element fileName = doc.createElement("fileName");
            fileName.appendChild((doc.createTextNode("0")));
            pictures.appendChild(fileName);

            Element location = doc.createElement("location");
            location.appendChild(doc.createTextNode("0"));
            pictures.appendChild(location);

            Element positiveRatings = doc.createElement("positiveRatings");
            positiveRatings.appendChild(doc.createTextNode("0"));
            pictures.appendChild(positiveRatings);

            Element negativeRatings = doc.createElement("negativeRatings");
            negativeRatings.appendChild(doc.createTextNode("0"));
            pictures.appendChild(negativeRatings);

            Element comments = doc.createElement("comments");
            pictures.appendChild(comments);
            comments.setAttribute("user", "bleh");

            Element comment = doc.createElement("comment");
            comment.appendChild(doc.createTextNode("good"));
            comments.appendChild(comment);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("pictures.xml"));

            transformer.transform(source, result);

        }
        catch (ParserConfigurationException pce){
            pce.printStackTrace();
        }
        catch (TransformerException tfe){
            tfe.printStackTrace();
        }
    }
}
