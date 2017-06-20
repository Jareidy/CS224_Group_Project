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

    ImageManager imageManager = new ImageManager();
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
                String location =pictureElement.getAttribute("location");
                String imagePath = pictureElement.getElementsByTagName("path").item(0).getTextContent();
                String title = pictureElement.getElementsByTagName("title").item(0).getTextContent();
                String description = pictureElement.getElementsByTagName("description").item(0).getTextContent();
                readCommentsFromDocument();
                Image image = new Image(imagePath);
                imageManager.addImage(title,image,location,description);
            }
        }
    }

    public ArrayList<Picture> getImages(){
        return imageManager.getImages();
    }

    private void readCommentsFromDocument() {
        NodeList commentNodes = document.getElementsByTagName("comments");
        for(int j = 0; j<commentNodes.getLength();j++){
            Node commentNode = commentNodes.item(j);
            if(commentNode.getNodeType()==Node.ELEMENT_NODE) {
                Element commentElement = (Element) commentNode;
                String user = commentElement.getAttribute("user");
                String comment = commentElement.getElementsByTagName("comment").item(0).getTextContent();
            }
        }
    }
}