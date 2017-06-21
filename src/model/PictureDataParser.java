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

    public static ImageManager imageManager = new ImageManager();
    Document document;

    public void parsePictureData() {
        try{
            readXMLFile();
            readImageFromDocument();
        }catch(IOException e){
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private void readXMLFile() throws IOException, SAXException, ParserConfigurationException {
        File file = new File(System.getProperty("user.dir")+"/src/res/"+"PictureData.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(file);
    }

    private void readImageFromDocument() {
        NodeList pictureNodes = document.getElementsByTagName("picture");
        for(int i = 0; i<pictureNodes.getLength();i++){
            Node pictureNode = pictureNodes.item(i);
            if(pictureNode.getNodeType()==Node.ELEMENT_NODE){
                Element pictureElement = (Element) pictureNode;
                String title =pictureElement.getAttribute("pictureName");
                String imagePath = pictureElement.getElementsByTagName("fileName").item(0).getTextContent();
                String location = pictureElement.getElementsByTagName("location").item(0).getTextContent();
                String description = pictureElement.getElementsByTagName("description").item(0).getTextContent();
                Integer positiveRatings = Integer.valueOf(pictureElement.getElementsByTagName("positiveRatings").item(0).getTextContent());
                Integer negativeRatings = Integer.valueOf(pictureElement.getElementsByTagName("negativeRatings").item(0).getTextContent());
                Image image = new Image(imagePath);
                String fileExtension = imagePath.substring(imagePath.lastIndexOf("."), imagePath.length());
                System.out.println(fileExtension);
                imageManager.addImage(title,image,location,description,fileExtension);
                readCommentsFromDocument(i);
                imageManager.getImages().get(i).addLike(positiveRatings);
                imageManager.getImages().get(i).addDislike(negativeRatings);
            }
        }
    }

    public ArrayList<Picture> getImages(){
        return imageManager.getImages();
    }

    private void readCommentsFromDocument(int i) {
        NodeList commentNodes = document.getElementsByTagName("comments");
        for(int j = 0; j<commentNodes.getLength();j++){
            Node commentNode = commentNodes.item(j);
            if(commentNode.getNodeType()==Node.ELEMENT_NODE) {
                Element commentElement = (Element) commentNode;
                String user = commentElement.getAttribute("username");
                String comment = commentElement.getElementsByTagName("comment").item(0).getTextContent();
                imageManager.getImages().get(i).returnComments().clear();
                imageManager.getImages().get(i).addComment(user,comment);
            }
        }
    }
}