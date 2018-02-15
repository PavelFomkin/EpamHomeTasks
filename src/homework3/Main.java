package homework3;


import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String UserName = System.getProperty("user.name");
        Path pathDir = Paths.get("C:\\Users\\" + UserName + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Media Cache");

//        ChangeFile.printDirectory(pathDir);
//        System.out.println();
//        System.out.println();
//        ChangeFile.printMusicFiles(pathDir);

//        ChangeFile.renameAllMusicFileToMp3(pathDir);



        int i =0;
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(pathDir)) {
            for (Path element : dirStream){
//                System.out.println(element.getFileName());
                i++;
                if (i>4){
                    FileInputStream in = new FileInputStream(element.toFile());
                    BufferedInputStream bufIn = new BufferedInputStream(in);
                    byte[] type = new byte[3];
                    bufIn.read(type);
//                    System.out.println(new String(type));
                    if (new String(type).equals("ID3")){
//                        bufIn.skip(20);
                        byte[] arr = new byte[120];
                        bufIn.read(arr);
                        String name = new String(arr);
                        System.out.println(name);
//                        System.out.println(name.substring(0,name.indexOf("TCON")));
                    }
//                    if (i>50){
//                        break;
//                    }
                }
            }
        } catch (IOException e) {
            System.out.println("The directory was pointed wrong!");
        }

    }

}
