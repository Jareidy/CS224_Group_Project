package model;

import controller.ImportDetailsViewController;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

public class ImportFile {

    private final FileChooser fileChooser = new FileChooser();
    private String fileExtension;
    private BufferedImage bufferedImage;
    private File file;

    public void chooseFile() throws IOException, InvocationTargetException {
        extensionFilters();
        file = fileChooser.showOpenDialog(null);
        bufferedImage = ImageIO.read(file);
    }

    private void extensionFilters(){
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)","*.JPG");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)","*.PNG");
        FileChooser.ExtensionFilter jpegFilter = new FileChooser.ExtensionFilter("JPEG files(*.jpeg)","*.JPEG");
        fileChooser.getExtensionFilters().add(jpgFilter);
        fileChooser.getExtensionFilters().add(pngFilter);
        fileChooser.getExtensionFilters().add(jpegFilter);
    }

    public BufferedImage getBufferedImage(){
        return bufferedImage;
    }

    public File getFile(){
        return file;
    }

    public void saveFile(File file, String title) {
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
