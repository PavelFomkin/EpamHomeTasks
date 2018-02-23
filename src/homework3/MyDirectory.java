package homework3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

class MyDirectory {
    private Path currentPath;
    private String name;
    private boolean permissionCreateDirectory;
    MyDirectory(Path dirPath, String name, boolean permissionCreateDirectory){
        currentPath = Paths.get(dirPath.toAbsolutePath().toString());
        this.name = name;
        this.permissionCreateDirectory = permissionCreateDirectory;
        if (!Files.exists(currentPath) && !Files.isDirectory(currentPath)){
            System.out.println("Unfortunately, the default "+name+" was pointed wrong.");
            if(!permissionCreateDirectory){
                currentPath = Paths.get(searchExistsDirectory(currentPath).toString());
                showCurrentDirectory();
            }
            else{
                System.out.println("Do you want to create a "+name+" with path: "+currentPath.toString()+"? Y/N");
                Scanner scanner = new Scanner(System.in);
                boolean answer = false;
                while (!answer) {
                    String currentAnswer = scanner.next().trim();
                    if (currentAnswer.equalsIgnoreCase("Y")) {
                        String label = currentPath.getFileName().toString();
                        currentPath = Paths.get(currentPath.getParent().toString());
                        createDirectory(label);
                        answer = true;
                    } else if (currentAnswer.equalsIgnoreCase("N")) {
                        currentPath = Paths.get(searchExistsDirectory(currentPath).toString());
                        showCurrentDirectory();
                        answer = true;
                    } else {
                        System.out.println("Please type Y or N.");
                    }
                }
            }
        }
        else{
            showCurrentDirectory();
        }
    }
    Path getPath() {
        return currentPath;
    }
    public String getName() {
        return name;
    }
    void showCurrentDirectory(){
        System.out.print("Current "+name+": ");
        System.out.println(currentPath.toAbsolutePath().toString());
    }
    private Path searchExistsDirectory(Path path){
        while (!Files.exists(path.toAbsolutePath())&&!Files.isDirectory(path.toAbsolutePath())){
            path = Paths.get(path.toAbsolutePath().getParent().toString());
            if (path.toString().equals(path.toAbsolutePath().getRoot().toString())){
                break;
            }
        }
        return path;
    }
    private void changeDirectory(String path) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_/. ]+");
        if(!pattern.matcher(path).matches()){
            Pattern pattern2 = Pattern.compile("^[A-Z]:/.*");
            if (pattern2.matcher(path).matches()) {
                if (Files.isDirectory(Paths.get(path))) {
                    currentPath = Paths.get(path);
                    System.out.println("The " + name + " was changed.");
                    showCurrentDirectory();
                } else {
                    System.out.println("Invalid name. Don't use next symbols: \\ @ $ # ^ % \" ' etc.");
                }
            }
        }
        else {
            Path newPath = Paths.get(currentPath.toAbsolutePath().toString(), path);
            if (Files.isDirectory(newPath)){
                currentPath = newPath;
                System.out.println("The "+name+" was changed.");
                showCurrentDirectory();
            }
            else {
                if (Files.exists(newPath)){
                    System.out.println("\""+path+"\" isn't a directory.");
                }
                else {
                    System.out.println("The "+name+" was pointed wrong.");
                }
            }
        }
    }
    private void fromDirectory(){
        if (currentPath.toString().equals(currentPath.toAbsolutePath().getRoot().toString())){
            System.out.println("It's impossible, it is the root of the "+name+".");;
        }
        else {
            currentPath = Paths.get(currentPath.toAbsolutePath().getParent().toString());
            System.out.println("The "+name+" was changed.");
            showCurrentDirectory();
        }
    }
    private void createDirectory(String label){
        if (!permissionCreateDirectory){
            System.out.println("You should use exist directories as a "+name+".");
        }
        else{
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9_/]+$");
            if (pattern.matcher(label).matches()){
                Path newPath = Paths.get(currentPath.toAbsolutePath().toString(),label);
                try {
                    Files.createDirectories(newPath);
                    System.out.println("The "+name+" created.");
                    changeDirectory(label);
                } catch (IOException e) {
                    System.out.println("The connection is broken.");
                }
            }
            else {
                System.out.println("The name of the "+name+" isn't legal.");
            }
        }
    }
    private void deleteDirectory(String label){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_. ]+$");
        if (!permissionCreateDirectory){
            System.out.println("You can't delete directories.");
        }
        else if(!pattern.matcher(label).matches()){
            System.out.println("The "+name+" was pointed wrong.");
        }
        else{
            Path delPath = Paths.get(currentPath.toString(),label);
            if(Files.isDirectory(delPath)){
                try {
                    Files.delete(delPath);
                    System.out.println("The directory \""+label+"\" was deleted.");
                    showCurrentDirectory();
                } catch (IOException e) {
                    System.out.println("This directory is not empty.");
                }
            }
            else{
                System.out.println("Can't find this directory in current.");
            }
        }
    }
    void printFiles(){
        if (Files.isDirectory(currentPath)){
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(currentPath)) {
                for (Path element : dirStream){
                    System.out.println(element.getFileName());
                }
            } catch (IOException e) {
                System.out.println("The connection is broken.");
            }
        }
    }
    private void printTree(Path path, int indent){
        String space = "";
        for(int i=0; i<indent; i++){
            space += "\t";
        }
        if (Files.isDirectory(path)){
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
                for (Path element : dirStream){
                    System.out.println(space+(element.getFileName().toString()));
                    if (Files.isDirectory(element)&&Files.isReadable(element)){
                        printTree(Paths.get(element.toAbsolutePath().toString()),indent+1);
                    }
                }

            } catch (IOException e) {
                System.out.println("The connection is broken.");
            }
        }
    }
    void settings(){
        Scanner scanner = new Scanner(System.in);
        boolean back = false;
        System.out.println("Use the commands to change this directory. For help, type - \"help\".");
        while(!back){
            String command = scanner.nextLine();
            String useCommand = command.toLowerCase();
            String secondPart = "";
            if (command.contains(" ")){
                useCommand = command.substring(0,command.indexOf(" ")).toLowerCase();
                secondPart = command.substring(command.indexOf(" ")).trim();
            }
            label:
            switch (useCommand) {
                case ("dir"):
                    showCurrentDirectory();
                    break;
                case ("cd"):
                    switch (secondPart) {
                        case "":
                            System.out.println("You need to point the name of the directory");
                            break label;
                        case "..":
                            fromDirectory();
                            break label;
                        default:
                            changeDirectory(secondPart);
                            break label;
                    }
                case ("files"):
                    printFiles();
                    break;
                case ("tree"):
                    printTree(currentPath, 0);
                    break;
                case ("create"):
                    if (secondPart.equals("")) {
                        System.out.println("You need to point the name of the directory");
                        break;
                    }
                    createDirectory(secondPart);
                    break;
                case ("delete"):
                    if (secondPart.equals("")) {
                        System.out.println("You need to point the name of the directory");
                        break;
                    }
                    deleteDirectory(secondPart);
                    break;
                case ("help"):
                    System.out.println("The list of the commands:");
                    System.out.println("\"dir\" - Show the " + name + ".");
                    System.out.println("\"cd #path\" - Change the "+ name +".");
                    System.out.println("\"cd ..\" - One step from current directory.");
                    System.out.println("\"files\" - Show list of files inside current directory.");
                    System.out.println("\"tree\" - Show tree of files inside current directory.");
                    System.out.println("\"create #name\" or \"create #name/name/name\" - Create one or more directories inside current directory.");
                    System.out.println("\"delete #name\" - Delete a directory inside current directory. It must be empty to delete.");
                    System.out.println("\"help\" - Show the list of the commands.");
                    System.out.println("\"back\" - The end of the settings for current path.");
                    break;
                case ("back"):
                    back = true;
                    break;
                default:
                    System.out.println("The command is wrong. Type \"help\" to look at list of commands.");
            }
        }
    }
}
