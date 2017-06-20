package model;

import controller.ImportDetailsViewController;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;


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
            String path = System.getProperty("user.dir")+"/src/res/"+title+findFileExtension(file);
            System.out.println(path);
            File saved = new File(path);
            Files.copy(file.toPath(),saved.toPath());
        }catch(FileAlreadyExistsException e){
            ImportDetailsViewController importDetailsViewController = new ImportDetailsViewController();
            importDetailsViewController.displayErrorFileAlreadyExists();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String findFileExtension(File file) {
        String fileName = file.getName();
        fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        return fileExtension;
    }

    public String getFileExtension(){
        return fileExtension;
    }

}
