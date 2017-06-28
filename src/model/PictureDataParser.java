package model;

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

    public static final ImageManager imageManager = new ImageManager();
    private Document document;

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
                Element pictureElement = (Element) pictureNode;
                PictureBuilder builder = new PictureBuilder();
                String title =pictureElement.getAttribute("pictureName");
                builder.setTitle(title);
                String imagePath = pictureElement.getElementsByTagName("fileName").item(0).getTextContent();
                String location = pictureElement.getElementsByTagName("location").item(0).getTextContent();
                builder.setLocation(location);
                String continent = pictureElement.getElementsByTagName("continent").item(0).getTextContent();
                builder.setContintent(continent);
                String description = pictureElement.getElementsByTagName("description").item(0).getTextContent();
                builder.setDescription(description);
                Integer positiveRatings = Integer.valueOf(pictureElement.getElementsByTagName("positiveRatings").item(0).getTextContent());
                Integer negativeRatings = Integer.valueOf(pictureElement.getElementsByTagName("negativeRatings").item(0).getTextContent());
                Image image = new Image(imagePath);
                builder.setImageLink(image);
                String fileExtension = imagePath.substring(imagePath.lastIndexOf("."), imagePath.length());
                builder.setExtension(fileExtension);
                Picture newPicture = builder.build();
                imageManager.addImage(newPicture);
                readCommentsFromDocument(i,pictureElement);
                imageManager.getImages().get(i).addLike(positiveRatings);
                imageManager.getImages().get(i).addDislike(negativeRatings);
            }
        }
    }

    public ArrayList<Picture> getImages(){
        return imageManager.getImages();
    }

    private void readCommentsFromDocument(int i,Element pictureElement) {
        NodeList commentNodes = pictureElement.getElementsByTagName("comments");
        System.out.println(commentNodes.getLength());
        for(int j = 0; j<commentNodes.getLength();j++){
            Node commentNode = commentNodes.item(j);
            if(commentNode.getNodeType()==Node.ELEMENT_NODE) {
                Element commentElement = (Element) commentNode;
                String user = commentElement.getAttribute("username");
                String comment = commentElement.getElementsByTagName("comment").item(0).getTextContent();
                imageManager.getImages().get(i).addComment(user,comment);
            }
        }
    }
}