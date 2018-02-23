package homework3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ArchiveManager {
    private static final int BUFFER = 1024;
    private MyDirectory from;
    private MyDirectory to;
    private boolean deleteFiles = false;
    private List<Path> filesForArchiving = new ArrayList<>();
    ArchiveManager(MyDirectory from, MyDirectory to, boolean deleteFiles){
        this.from = from;
        this.to = to;
        this.deleteFiles = deleteFiles;
    }
    private void addFileForArchiving(String name){
        Path path = Paths.get(from.getPath().toAbsolutePath().toString(),name);
        if (isFile(path)){
            if (filesForArchiving.contains(path)){
                System.out.println("File is already added.");
                return;
            }
            filesForArchiving.add(path);
        }
    }
    private void showFilesForArchiving(){
        if (!filesForArchiving.isEmpty()){
            for(Path p : filesForArchiving){
                System.out.println(p.getFileName());
            }
        }
        else{
            System.out.println("We don't have files for archiving.");
        }
    }
    private void clearFilesForArchiving(){
        if (!filesForArchiving.isEmpty()){
            filesForArchiving.clear();
            System.out.println("Files was cleared.");
        }
        else{
            System.out.println("We don't have files for archiving.");
        }
    }
    private boolean isFile(Path path){
        if (Files.exists(path) && Files.isRegularFile(path)){
            if (Files.isReadable(path)){
                return true;
            }
            else{
                System.out.println("You don't have a permission to read this file.");
                return false;
            }
        }
        else {
            System.out.println("The file \""+path.getFileName()+"\" doesn't exist.");
            return false;
        }
    }
    private boolean isArchive(Path archiveName){
        if (isFile(archiveName)){
            Pattern pattern = Pattern.compile(".*.zip$");
            return pattern.matcher(archiveName.getFileName().toString()).matches();
        }
        return false;
    }
    private void compress(){
        if (filesForArchiving.isEmpty()){
            System.out.println("We don't have files for archiving.");
            return;
        }
        String archiveName = filesForArchiving.get(0).getFileName().toString();
        if(filesForArchiving.get(0).toString().contains(".")){
            archiveName = archiveName.substring(0,archiveName.lastIndexOf(".")) + ".zip";
        }
        else{
            archiveName = archiveName + ".zip";
        }
        Path pathTo = Paths.get(to.getPath().toString(),archiveName);
        try (FileOutputStream fos = new FileOutputStream(pathTo.toString());
             ZipOutputStream zfos = new ZipOutputStream(fos)){
            for(Path path : filesForArchiving){
                FileInputStream fis = new FileInputStream(path.toString());
                zfos.putNextEntry(new ZipEntry(path.getFileName().toString()));
                byte[] buffer = new byte[BUFFER];
                int len;
                while ((len = fis.read(buffer))>=0){
                    zfos.write(buffer,0,len);
                }
                zfos.closeEntry();
                fis.close();
            }
            if (deleteFiles){
                for (Path path : filesForArchiving){
                    Files.delete(path);
                    System.out.println(path.toString());
                }
            }
            filesForArchiving.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void decompressed(String archiveName){
        Path pathFrom = Paths.get(from.getPath().toAbsolutePath().toString(),archiveName);
        if (isArchive(pathFrom)){
            try (FileInputStream fis = new FileInputStream(pathFrom.toString());
                 ZipInputStream zis = new ZipInputStream(fis);
                 ZipFile zipFile = new ZipFile(pathFrom.toString())) {
                Enumeration enumeration = zipFile.entries();
                while (enumeration.hasMoreElements()){
                    ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
                    Path pathTo = Paths.get(to.getPath().toString(),zipEntry.getName());
                    try (InputStream isEntry = zipFile.getInputStream(zipEntry);
                         FileOutputStream fos = new FileOutputStream(pathTo.toString())) {
                        byte[] buffer = new byte[BUFFER];
                        int len;
                        while ((len = isEntry.read(buffer)) >= 0) {
                            fos.write(buffer, 0, len);
                        }
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (deleteFiles){
                try {
                    Files.delete(pathFrom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("\""+archiveName+"\" isn't a ZIP archive.");
        }
    }
    void start(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Use the commands to work with this application. For help, type - \"help\".");
        while(!exit){
            String command = scanner.nextLine();
            String useCommand = command.toLowerCase();
            String secondPart = "";
            if (command.contains(" ")){
                useCommand = command.substring(0,command.indexOf(" ")).toLowerCase();
                secondPart = command.substring(command.indexOf(" ")).trim();
            }
            switch(useCommand){
                case ("edit_dir"):
                    switch(secondPart){
                        case ("start"):
                            from.settings();
                            break;
                        case ("end"):
                            to.settings();
                            break;
                        default:
                            System.out.println("Not correct. Use the names \"start\" or \"end\".");
                    }
                    break;
                case ("dir"):
                    switch(secondPart){
                        case ("start"):
                            from.showCurrentDirectory();
                            break;
                        case ("end"):
                            to.showCurrentDirectory();
                            break;
                        default:
                            from.showCurrentDirectory();
                            to.showCurrentDirectory();
                    }
                    break;
                case ("files"):
                    switch(secondPart){
                        case ("start"):
                            from.printFiles();
                            break;
                        case ("end"):
                            to.printFiles();
                            break;
                        default:
                            System.out.println("Not correct. Use the names \"start\" or \"end\".");
                    }
                    break;
                case ("add"):
                    if (secondPart.equals("")){
                        System.out.println("You need to point a file name.");
                        break;
                    }else{
                        addFileForArchiving(secondPart);
                        break;
                    }
                case ("cloud"):
                    showFilesForArchiving();
                    break;
                case ("clear"):
                    clearFilesForArchiving();
                    System.out.println("You don't have any files for archiving.");
                    break;
                case ("compress"):
                    compress();
                    break;
                case ("decompress"):
                    if (secondPart.equals("")){
                        System.out.println("You need to point a ZIP archive name.");
                        break;
                    }else{
                        decompressed(secondPart);
                        break;
                    }
                case ("deleteFilesAfterWork"):
                    switch(secondPart){
                        case ("true"):
                            deleteFiles = true;
                            break;
                        case ("false"):
                            deleteFiles = false;
                            break;
                        default:
                            System.out.println("Not correct. Use the words \"true\" or \"false\".");
                    }
                    break;
                case ("help"):
                    System.out.println("List of commands:");
                    System.out.println("\"edit_dir start\" - Edit the "+from.getName()+".");
                    System.out.println("\"edit_dir end\" - Edit the "+to.getName()+".");
                    System.out.println("\"dir start\" - Show the "+from.getName()+".");
                    System.out.println("\"dir end\" - Show the "+to.getName()+".");
                    System.out.println("\"dir\" - Show the both directories.");
                    System.out.println("\"files start\" - Show list of files inside the "+from.getName()+".");
                    System.out.println("\"files end\" - Show list of files inside the "+to.getName()+".");
                    System.out.println("\"add #file_name\" - Add a file to the files cloud for archiving.");
                    System.out.println("\"cloud\" - Show the files inside the files cloud.");
                    System.out.println("\"clear\" - Clear the files inside the files cloud.");
                    System.out.println("\"compress\" - Put clouds files to ZIP archive.");
                    System.out.println("\"decompress #archive_name\" - Put archives files to \""+to.getName()+"\".");
                    System.out.println("\"deleteFilesAfterWork true\" or \"false\" - It's extra setting.");
                    System.out.println("\"help\" - Show list of commands.");
                    System.out.println("\"exit\" - The end of the application.");
                    break;
                case ("exit"):
                    exit=true;
                    break;
                default:
                    System.out.println("Wrong command. Type \"help\" to look at list of commands.");
            }
        }
    }
}
