package homework3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException{
        String UserName = System.getProperty("user.name");
        Path pathDirForMp3 = Paths.get("mp3");
        Path pathDir = Paths.get(pathDirForMp3.toAbsolutePath().getRoot().toString(), "Users", UserName, "AppData", "Local", "Google", "Chrome", "User Data", "Default", "Media Cache"); // default path (for windows)
        MyDirectory cacheDirectory = new MyDirectory(pathDir, "directory with Chrome cache", false);
        MyDirectory directoryForMp3 = new MyDirectory(pathDirForMp3, "directory for mp3 files", true);
        Mp3Manager myMp3Manager = new Mp3Manager(cacheDirectory, directoryForMp3);
        myMp3Manager.start();
    }
}