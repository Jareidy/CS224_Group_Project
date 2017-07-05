package model.picture;

import model.XMLBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class PictureXMLHandler {

    XMLBase xmlCreator = new XMLBase();
    Document doc;
    Element rootElement;
    Element pictures;
    private ArrayList<Picture> importedPictures = PictureManager.images;

    public void formatXmlFile(String localFileName){
        doc = xmlCreator.docCreator();
        rootElement = doc.createElement("pictures");
        doc.appendChild(rootElement);
        for (Picture importedPicture: importedPictures) {
            createPictureElement(rootElement,importedPicture);
            formatPictureDetails(importedPicture);
        }
        xmlCreator.createXmlFile(doc, localFileName);

    }

    private void createPictureElement(Element rootElement,Picture importedPicture) {
        pictures = doc.createElement("picture");
        rootElement.appendChild(pictures);
        pictures.setAttribute("pictureName", importedPicture.getTitle());
    }


    public void formatPictureDetails(Picture importedPicture){
        formatFileName(importedPicture);
        formatLocation(importedPicture);
        formatContinent(importedPicture);
        formatDescription(importedPicture);
        formatRatings(importedPicture);
        formatComments(importedPicture);
    }

    private void formatFileName(Picture importedPicture) {
        Element fileName = doc.createElement("fileName");
        fileName.appendChild((doc.createTextNode("/res/" + importedPicture.getTitle() + importedPicture.getFileExtension())));
        pictures.appendChild(fileName);
    }

    private void formatLocation(Picture importedPicture) {
        Element location = doc.createElement("location");
        location.appendChild(doc.createTextNode(importedPicture.getLocation()));
        pictures.appendChild(location);
    }

    private void formatContinent(Picture importedPicture) {
        Element continent = doc.createElement("continent");
        continent.appendChild(doc.createTextNode(importedPicture.getContinent()));
        pictures.appendChild(continent);
    }

    public void formatDescription( Picture importedPicture){
        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(importedPicture.getDescription()));
        pictures.appendChild(description);
    }

    public void formatRatings(Picture importedPicture){
        formatPositiveRatings(importedPicture);
        formatNegativeRatings(importedPicture);
    }

    public void formatPositiveRatings(Picture importedPicture){
        Element positiveRatings = doc.createElement("positiveRatings");
        positiveRatings.appendChild(doc.createTextNode(importedPicture.getLikes().toString()));
        pictures.appendChild(positiveRatings);
    }

    public void formatNegativeRatings(Picture importedPicture){
        Element negativeRatings = doc.createElement("negativeRatings");
        negativeRatings.appendChild(doc.createTextNode(importedPicture.getDislikes().toString()));
        pictures.appendChild(negativeRatings);
    }

    public void formatComments(Picture importedPicture){
        for (int i = 0;i<importedPicture.returnComments().size();i++) {
            Element comments = doc.createElement("comments");
            pictures.appendChild(comments);
            Comment currentComment = (Comment) importedPicture.returnComments().get(i);
            String newUser = currentComment.getUser();
            String newComment = currentComment.getComment();
            comments.setAttribute("username", newUser);

            Element comment = doc.createElement("comment");
            comment.appendChild(doc.createTextNode(newComment));
            comments.appendChild(comment);
        }
    }
}
