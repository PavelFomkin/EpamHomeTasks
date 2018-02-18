package homework3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeFile {
    private static boolean isMusicFile(Path path){
        boolean isRegularExecutableFile = Files.isRegularFile(path) && Files.isReadable(path) && Files.isExecutable(path);
        try {
            if(isRegularExecutableFile && Files.size(path)<=1048576){
                return !path.toFile().getName().matches("data_[0-9]*") && !path.toFile().getName().equals("index");
            }
        } catch (IOException e) {
            System.out.println("The directory of the file was pointed wrong!");
            return false;
        }
        return false;
    }
    public static void renameToMp3(Path path){
        if (path.toString().matches(".+\\.mp3$")){
            System.out.println("It's already mp3 file.");
            return;
        }
        if(!Files.exists(Paths.get(path.getParent()+"\\mp3"))){
            try {
                Files.createDirectory(Paths.get(path.getParent()+"\\mp3"));
            } catch (IOException e) {
                System.out.println("The directory was pointed wrong!");
            }
        }
        Path pathCopy = Paths.get(path.getParent()+"\\mp3\\"+path.getFileName()+".mp3");
        try {
            Files.copy(path,pathCopy);
        } catch (IOException e) {
            System.out.println("This mp3 file has already exist.");
        }


    }
    public static void renameAllMusicFileToMp3(Path pathDir){
        if (Files.isDirectory(pathDir)){
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(pathDir)) {
                for (Path song : dirStream){
                    if (isMusicFile(song)){
                        renameToMp3(song);
                    }
                }
            } catch (IOException e) {
                System.out.println("The directory was pointed wrong!");
            }
        }
    }
    public static void printDirectory(Path pathDir){
        if (Files.isDirectory(pathDir)){
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(pathDir)) {
                for (Path element : dirStream){
                    System.out.println(element.getFileName());
                }
            } catch (IOException e) {
                System.out.println("The directory was pointed wrong!");
            }
        }
    }
    public static void printMusicFiles(Path pathDir){
        if (Files.isDirectory(pathDir)){
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(pathDir)) {
                for (Path song : dirStream){
                    if (isMusicFile(song)){
                        System.out.println(song.getFileName());
                    }
                }
            } catch (IOException e) {
                System.out.println("The directory was pointed wrong!");
            }
        }
    }
}
