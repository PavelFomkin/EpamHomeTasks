package homework4.hometask1;

import java.util.Comparator;

class BookISBNComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return ((Book)o1).getISBN().compareTo(((Book)o2).getISBN());
    }
}

class BookTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
    }
}

class BookAuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        int result = ((Book)o1).getAuthor().compareTo(((Book)o2).getAuthor());
        if (result==0){
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
        } else{
            return result;
        }
    }
}

class BookCreateTimeComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        int result = ((Book)o1).getDateOfCreation().compareTo(((Book)o2).getDateOfCreation());
        if (result==0){
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
        } else{
            return result;
        }
    }
}

class BookLastChangeComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        int result = ((Book)o1).getDateOfLastChange().compareTo(((Book)o2).getDateOfLastChange());
        if (result==0){
            return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());
        } else{
            return result;
        }
    }
}

class UserNameComparator implements Comparator<UserOfLibrary> {
    @Override
    public int compare(UserOfLibrary o1, UserOfLibrary o2) {
        return ((UserOfLibrary)o1).getName().compareTo(((UserOfLibrary)o2).getName());
    }
}

class UserAgeComparator implements Comparator<UserOfLibrary> {
    @Override
    public int compare(UserOfLibrary o1, UserOfLibrary o2) {
        int result = Integer.compare(((UserOfLibrary)o1).getAge(), ((UserOfLibrary)o2).getAge());
        if(result==0){
            return ((UserOfLibrary)o1).getName().compareTo(((UserOfLibrary)o2).getName());
        }else{
            return result;
        }
    }
}

class UserIdComparator implements Comparator<UserOfLibrary> {
    @Override
    public int compare(UserOfLibrary o1, UserOfLibrary o2) {
        return Integer.compare(((UserOfLibrary)o1).getID(), ((UserOfLibrary)o2).getID());
    }
}



