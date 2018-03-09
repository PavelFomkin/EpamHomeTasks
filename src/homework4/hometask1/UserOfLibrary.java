package homework4.hometask1;

import java.util.ArrayList;
import java.util.List;

class UserOfLibrary implements KeeperOfBooks, Comparable<UserOfLibrary>{
    public static final String TYPE = "USER";
    private final int ID;
    private String name;
    private int age;
    private List<String> booksTitles = new ArrayList<>();
    UserOfLibrary(int userID, String name, int age){
        ID = userID;
        this.name = name;
        this.age = age;
    }

    @Override
    public String getUniqueValue() {
        return String.valueOf(ID);
    }

    public int getCountOfBooks() {
        return booksTitles.size();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getID() {
        return ID;
    }

    public void listOfLibraryBooks() {
        if (booksTitles.size()==0){
            System.out.println(name+" has no library books.");
        }else{
            System.out.println(name+" has the following library books:");
            for (int i = 0; i < booksTitles.size(); i++) {
                System.out.println("Book "+(i+1)+": "+booksTitles.get(i));
            }
        }
    }

    public void takeBook(String title){
        booksTitles.add(title);
    }

    public boolean returnBook(String title){
        if(booksTitles.contains(title)){
            booksTitles.remove(title);
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.ID;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof UserOfLibrary && ((UserOfLibrary)obj).ID==this.ID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(TYPE+"\t"+ID+"\t"+name+"\t"+age);
        for (String book : booksTitles){
            sb.append("\t").append(book);
        }
        return sb.toString();
    }

    @Override
    public int compareTo(UserOfLibrary o) {
        return Integer.compare(this.ID,o.ID);
    }
}
