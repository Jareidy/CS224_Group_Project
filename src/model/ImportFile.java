package model;

import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class ImportFile {

    FileChooser fileChooser = new FileChooser();
    String fileExtension;
    BufferedImage bufferedImage;
    File file;

    public void chooseFile(){
        extensionFilters();
        file = fileChooser.showOpenDialog(null);
        try{
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void extensionFilters(){
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)","*.JPG");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)","*.PNG");
        FileChooser.ExtensionFilter jpegFilter = new FileChooser.ExtensionFilter("JPEG files(*.jpeg)","*.JPEG");
    }

    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }

    public File getFile(){
        return file;
    }

    public void saveFile(BufferedImage importedFile, File file,String title) {
        try{
            File saved = new File(title+findFileExtension(file));
            ImageIO.write(importedFile,"jpg",saved);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private String findFileExtension(File file) {
        String fileName = file.getName();
        fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return fileExtension;
    }

    public String getFileExtension(){
        return fileExtension;
    }

}
