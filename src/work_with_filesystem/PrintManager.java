package work_with_filesystem;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrintManager {
    private MyDirectory currentDirectory;
    private List<Path> listOfPaths;
    private List<String> list;
    private boolean directories;
    private boolean files;
    PrintManager(MyDirectory currentDirectory){
        this.currentDirectory = currentDirectory;
        copyAllFiles();
        useAll();
    }
    private void copyAllFiles(){
        listOfPaths = new ArrayList<>();
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory.getPath())) {
            for (Path path : stream){
                listOfPaths.add(path);
            }
        } catch (IOException e) {
            System.out.println("You don't have a permission for reading this directory");
        }
    }
    private void refreshSelection(){
        if (directories && files){
            useAll();
        }
        else if (directories){
            useDirectories();
        }
        else{
            useFiles();
        }
    }
    private void useAll(){
        directories = true;
        files = true;
        list = new ArrayList<>();
        for (Path name : listOfPaths){
            list.add(name.getFileName().toString());
        }
    }
    private void useFiles(){
        directories = false;
        files = true;
        list = new ArrayList<>();
        for (Path name : listOfPaths){
            if (Files.isRegularFile(name)){
                list.add(name.getFileName().toString());
            }
        }
    }
    private void useDirectories(){
        directories = true;
        files = false;
        list = new ArrayList<>();
        for (Path name : listOfPaths){
            if (Files.isDirectory(name)){
                list.add(name.getFileName().toString());
            }
        }
    }
    private void print(List<String> arr){
        if (!arr.isEmpty()){
            for (String name : arr){
                System.out.println(name);
            }
        }
        else {
            System.out.println("There is nothing.");
        }
    }
    private void printOriginal(){
        refreshSelection();
        print(list);
    }
    private void printABC(){
        Collections.sort(list);
        print(list);
    }
    private void printReverse(){
        list.sort(Collections.reverseOrder());
        print(list);
    }
    private void printCount(){
        System.out.println(list.size());
    }
    void start(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        System.out.println("The application has its own list of commands. Type - \"help\" for to look into.");
        while(!exit){
            String command = scanner.nextLine();
            String useCommand = command.toLowerCase();
            String secondPart = "";
            if (command.contains(" ")){
                useCommand = command.substring(0,command.indexOf(" ")).toLowerCase();
                secondPart = command.substring(command.indexOf(" ")).toLowerCase().trim();
            }
            switch(useCommand){
                case "cd":
                    switch(secondPart) {
                        case "":
                            currentDirectory.showCurrentDirectory();
                            break;
                        case "dir":
                            currentDirectory.settings();
                            copyAllFiles();
                            refreshSelection();
                            break;
                        default:
                            System.out.println("Use \"dir\" to edit a directory.");
                    }
                    break;
                case "use":
                    switch(secondPart){
                        case "":
                            System.out.println("You need use an argument.");
                            break;
                        case "all":
                            useAll();
                            break;
                        case "dirs":
                            useDirectories();
                            break;
                        case "files":
                            useFiles();
                            break;
                        default:
                            System.out.println("\""+secondPart+"\" is not correct argument.");
                    }
                    break;
                case ("print"):
                    switch(secondPart){
                        case "":
                            print(list);
                            break;
                        case ("abc"):
                            printABC();
                            break;
                        case ("reverse"):
                            printReverse();
                            break;
                        case ("count"):
                            printCount();
                            break;
                        case "original":
                            printOriginal();
                            break;
                        default:
                            System.out.println("\""+secondPart+"\" is not correct argument.");
                    }
                    break;
                case ("help"):
                    System.out.println("List of commands:");
                    System.out.println("\"cd\" - Show path of the "+currentDirectory.getName()+".");
                    System.out.println("\"cd dir\" - Edit the "+currentDirectory.getName()+".");
                    System.out.println("\"use all\" - Use directories and files as current list to print.");
                    System.out.println("\"use files\" - Use files as current list to print.");
                    System.out.println("\"use dirs\" - Use directories as current list to print.");
                    System.out.println("\"print\" - Show current list in last used order.");
                    System.out.println("\"print abc\" - Show current list in alphabetic order.");
                    System.out.println("\"print reverse\" - Show current list in reverse alphabetic order.");
                    System.out.println("\"print original\" - Show current list in original order.");
                    System.out.println("\"print count\" - Show count of elements in current list.");
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
