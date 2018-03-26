package work_with_filesystem;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class Mp3Manager {
    private static final int MAX_SIZE_OF_PART = 1048576;
    private MyDirectory from;
    private MyDirectory to;
    Mp3Manager(MyDirectory from, MyDirectory to){
        this.from = from;
        this.to = to;
    }
    private boolean isMusicFile(Path path){
        if(!Files.exists(path)){
            System.out.println("\""+path.getFileName().toString()+"\" was pointed wrong.");
        }
        else if(!Files.isRegularFile(path)){
            System.out.println("\""+path.getFileName().toString()+"\" isn't regular file.");
        }
        try {
            if(Files.isReadable(path) && Files.size(from.getPath())<= MAX_SIZE_OF_PART){
                // also we can add other checks
                return path.getFileName().toString().matches("^f_[a-zA-Z0-9]+");
            }
        } catch (IOException e) {
            System.out.println("You don't have permission to read this file.");
        }
        return false;
    }
    private boolean renameToMp3(String label){
        Path currentSong = Paths.get(from.getPath().toString(),label);
        if(isMusicFile(currentSong)){
            Path pathCopy = Paths.get(to.getPath().toString(),label+".mp3");
            try {
                Files.copy(currentSong,pathCopy);
                return true;
            } catch (IOException e) {
                System.out.println("The mp3 file \""+label+".mp3\" has already exist");
                return false;
            }
        }
        else{
            System.out.println("\""+label+"\" doesn't similar to mp3 file.");
            return false;
        }
    }
    private void renameEachMusicFileToMp3(){
        int files = 0;
        int mp3Files = 0;
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(from.getPath())) {
            for (Path song : dirStream){
                if (renameToMp3(song.getFileName().toString())){
                   mp3Files++;
                }
                files++;
            }
        } catch (IOException e) {
            System.out.println("The connection is broken.");
        } finally {
            System.out.println("Number of files: "+files);
            System.out.println("Number of renamed files to mp3: "+mp3Files);
        }
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
                secondPart = command.substring(command.indexOf(" ")).trim();
            }
            switch(useCommand){
                case ("cd"):
                    switch(secondPart){
                        case "":
                            from.showCurrentDirectory();
                            to.showCurrentDirectory();
                            break;
                        case ("cache"):
                            from.settings();
                            break;
                        case ("mp3"):
                            to.settings();
                            break;
                        default:
                            System.out.println("Use \"cache\" or \"mp3\" to edit a directory.");
                    }
                    break;
                case ("dir"):
                    switch(secondPart){
                        case ("cache"):
                            from.printFiles();
                            break;
                        case ("mp3"):
                            to.printFiles();
                            break;
                        default:
                            System.out.println("Use \"cache\" or \"mp3\" to print files inside a directory.");
                    }
                    break;
                case ("move"):
                    switch (secondPart) {
                        case ("all"):
                            renameEachMusicFileToMp3();
                            break;
                        case (""):
                            System.out.println("You need to point the name of the file or use \"all\"");
                            break;
                        default:
                            renameToMp3(secondPart);
                            break;
                    }
                    break;
                case ("help"):
                    System.out.println("List of commands:");
                    System.out.println("\"cd\" - Show the directories' paths.");
                    System.out.println("\"cd cache\" - Edit the "+from.getName()+".");
                    System.out.println("\"cd mp3\" - Edit the "+to.getName()+".");
                    System.out.println("\"dir cache\" - Show list of files inside the "+from.getName()+".");
                    System.out.println("\"dir mp3\" - Show list of files inside the "+to.getName()+".");
                    System.out.println("\"move #name\" - Copy certain \"music\" file to "+to.getName()+" and rename it to mp3.");
                    System.out.println("\"move all\" - Copy all \"music\" files to "+to.getName()+" and rename them to mp3.");
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
