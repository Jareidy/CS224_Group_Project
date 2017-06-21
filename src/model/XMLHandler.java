package model;

import javafx.collections.ObservableList;
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
import java.util.ArrayList;

public class XMLHandler {

    public void XMLWriter(){

        ImageManager imageManager = PictureDataParser.imageManager;
        ArrayList<Picture> importedPictures = imageManager.getImages();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("pictures");
            doc.appendChild(rootElement);
            for (Picture importedPicture: importedPictures) {
                Element pictures = doc.createElement("picture");
                rootElement.appendChild(pictures);
                pictures.setAttribute("pictureName", importedPicture.getTitle());

                Element fileName = doc.createElement("fileName");
                fileName.appendChild((doc.createTextNode("/res/" + importedPicture.getTitle() + importedPicture.getFileExtension())));
                pictures.appendChild(fileName);

                Element location = doc.createElement("location");
                location.appendChild(doc.createTextNode(importedPicture.getLocation()));
                pictures.appendChild(location);

                Element description = doc.createElement("description");
                description.appendChild(doc.createTextNode(importedPicture.getDescription()));
                pictures.appendChild(description);

                Element positiveRatings = doc.createElement("positiveRatings");
                positiveRatings.appendChild(doc.createTextNode(importedPicture.getLikes().toString()));
                pictures.appendChild(positiveRatings);

                Element negativeRatings = doc.createElement("negativeRatings");
                negativeRatings.appendChild(doc.createTextNode(importedPicture.getDislikes().toString()));
                pictures.appendChild(negativeRatings);

                for (int i = 0;i<importedPicture.returnComments().size();i++) {
                    Element comments = doc.createElement("comments");
                    pictures.appendChild(comments);
                    Comment currentComment = (Comment) importedPicture.returnComments().get(i);
                    String newuser = currentComment.getUser();
                    String newcomment = currentComment.getComment();
                    comments.setAttribute("username", newuser);

                    Element comment = doc.createElement("comment");
                    comment.appendChild(doc.createTextNode(newcomment));
                    comments.appendChild(comment);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.getProperty("user.dir")+"/src/res/PictureData.xml");

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
