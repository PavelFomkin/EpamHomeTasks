package homework3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Change the program for working. Type a number of a chosen program.");
        System.out.println("Program 1: mp3 manager");
        System.out.println("Program 2: archive manager");
        System.out.println("Program 3: print manager");
        boolean chose = false;
        while (!chose){
            String answer = scanner.nextLine().trim();
            switch (answer){
                case "1":
                    String UserName = System.getProperty("user.name");
                    Path pathDirForMp3 = Paths.get("");
                    Path pathDir = Paths.get(pathDirForMp3.toAbsolutePath().getRoot().toString(), "Users", UserName, "AppData", "Local", "Google", "Chrome", "User Data", "Default", "Media Cache"); // default path (for windows)
                    MyDirectory cacheDirectory = new MyDirectory(pathDir, "directory with Chrome cache", false);
                    MyDirectory directoryForMp3 = new MyDirectory(pathDirForMp3, "directory for mp3 files", true);
                    Mp3Manager myMp3Manager = new Mp3Manager(cacheDirectory, directoryForMp3);
                    myMp3Manager.start();
                    chose = true;
                    break;
                case "2":
                    MyDirectory originalDirectory = new MyDirectory(Paths.get(""),"start directory",false);
                    MyDirectory finalDirectory = new MyDirectory(Paths.get(""),"end directory",true);
                    ArchiveManager archiveTest = new ArchiveManager(originalDirectory,finalDirectory,false);
                    archiveTest.start();
                    chose = true;
                    break;
                case "3":
                    MyDirectory currentDirectory = new MyDirectory(Paths.get(""),"directory",false);
                    PrintManager printManager = new PrintManager(currentDirectory);
                    printManager.start();
                    chose = true;
                    break;
                case "exit":
                    chose = true;
                    break;
                default:
                    System.out.println("Type a number of a chosen program or \"exit\" if you want to exit this program.");
            }
        }
        scanner.close();
    }
}