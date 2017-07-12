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
            createPictureElement(importedPicture);
            formatPictureDetails(importedPicture);
        }
        xmlCreator.createXmlFile(doc, localFileName);

    }

    private void createPictureElement(Picture importedPicture) {
        pictures = doc.createElement("picture");
        rootElement.appendChild(pictures);
        pictures.setAttribute("pictureName", importedPicture.getTitle());
    }


    public void formatPictureDetails(Picture importedPicture){
        formatFileName(importedPicture);
        formatLocation(importedPicture);
        formatContinent(importedPicture);
        formatDescription(importedPicture);
        formatPictureUserReviews(importedPicture);
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


    public void formatPictureUserReviews(Picture importedPicture){
        final ArrayList<UserInput> inputs = importedPicture.userInputs;
        Element users = doc.createElement("users");
        pictures.appendChild(users);
        if(inputs.isEmpty()){
            System.out.print(inputs);
        }else {
            for (UserInput userInput : inputs) {
                Element user = doc.createElement("user");
                user.appendChild(doc.createTextNode(userInput.getUser()));
                users.appendChild(user);

                Element comments = doc.createElement("comments");
                user.appendChild(comments);

                ArrayList<Comment> importComments = userInput.getComments();
                for (Comment importComment : importComments) {
                    Element comment = doc.createElement("comment");
                    comment.appendChild(doc.createTextNode(importComment.getComment()));
                    comments.appendChild(comment);

                    Element date = doc.createElement("date");
                    date.appendChild(doc.createTextNode(String.valueOf(importComment.getDate())));
                    comments.appendChild(date);
                }

                Element likeDislike = doc.createElement("likedislike");
                likeDislike.appendChild(doc.createTextNode(userInput.getLikeDislike()));
                users.appendChild(likeDislike);
            }
        }
    }
}
