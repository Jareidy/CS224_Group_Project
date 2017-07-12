package model.picture;

import javafx.scene.image.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PictureDataParser {

    private Document document;
    private PictureBuilder builder;
    public Image image;

    public void parsePictureData(File file) {
        try{
            readXMLFile(file);
        }catch(IOException | SAXException | ParserConfigurationException e){
            e.printStackTrace();
        }
    }

    private void readXMLFile(File file) throws IOException, SAXException, ParserConfigurationException {
        if(file.length()==0){
        }else {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);
            readImageFromDocument();
        }
    }

    private void readImageFromDocument() {
        NodeList pictureNodes = document.getElementsByTagName("picture");
        for(int i = 0; i<pictureNodes.getLength();i++){
            Node pictureNode = pictureNodes.item(i);
            if(pictureNode.getNodeType()==Node.ELEMENT_NODE){
                readImageDetails(pictureNode,i);
            }
        }
    }

    private void readImageDetails(Node pictureNode, int i) {
        Element pictureElement = (Element) pictureNode;
        builder = new PictureBuilder();
        readTitle(pictureElement);
        readLocation(pictureElement);
        readContinent(pictureElement);
        readDescription(pictureElement);
        readImage(pictureElement);
        Picture newPicture = builder.build();
        PictureManager.addImage(newPicture);
        readCommentsFromDocument(i,pictureElement);
    }

    private void readTitle(Element pictureElement) {
        String title =pictureElement.getAttribute("pictureName");
        builder.setTitle(title);
    }

    private void readLocation(Element pictureElement) {
        String location = pictureElement.getElementsByTagName("location").item(0).getTextContent();
        builder.setLocation(location);
    }

    private void readContinent(Element pictureElement) {
        String continent = pictureElement.getElementsByTagName("continent").item(0).getTextContent();
        builder.setContintent(continent);
    }

    private void readDescription(Element pictureElement) {
        String description = pictureElement.getElementsByTagName("description").item(0).getTextContent();
        builder.setDescription(description);
    }

    private void readImage(Element pictureElement) {
        String imagePath = pictureElement.getElementsByTagName("fileName").item(0).getTextContent();
        image = new Image(imagePath);
        builder.setImageLink(image);
        readFileExtension(imagePath);
    }

    private void readFileExtension(String imagePath) {
        String fileExtension = imagePath.substring(imagePath.lastIndexOf("."), imagePath.length());
        builder.setExtension(fileExtension);
    }

    public ArrayList<Picture> getImages(){
        return PictureManager.getImages();
    }

    private void readCommentsFromDocument(int i,Element pictureElement) {
        NodeList commentNodes = pictureElement.getElementsByTagName("comments");
        for(int j = 0; j<commentNodes.getLength();j++){
            Node commentNode = commentNodes.item(j);
            if(commentNode.getNodeType()==Node.ELEMENT_NODE) {
                Element commentElement = (Element) commentNode;
                String user = commentElement.getAttribute("username");
                String comment = commentElement.getElementsByTagName("comment").item(0).getTextContent();
                PictureManager.getImages().get(i).addComment(comment);
            }
        }
    }

    private void readRatings(Element pictureElement,int i) {
        Integer positiveRatings = Integer.valueOf(pictureElement.getElementsByTagName("positiveRatings").item(0).getTextContent());
        Integer negativeRatings = Integer.valueOf(pictureElement.getElementsByTagName("negativeRatings").item(0).getTextContent());
        PictureManager.getImages().get(i).addLike();
        PictureManager.getImages().get(i).addDislike();
    }
}