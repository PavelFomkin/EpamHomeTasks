package homework4;

public class Main {
    public static void main(String[] args) {
        Library library = new Library("MyLibrary");

        library.addBook(new Book("5-10-202030","The Hunger Games","Suzanne Collins"));
        library.addBook(new Book("9-15-982300","The Da Vinci Code","Dan Brown"));
        library.addBook(new Book("5-12-101147","Memoirs of a Geisha","Arthur Golden"));
        library.addBook(new Book("2-17-666687","Alice's Adventures in Wonderland","Lewis Carroll"));
        library.addBook(new Book("3-90-467558","The Picture of Dorian Gray","Oscar Wilde"));
        library.addBook(new Book("5-32-758841","Harry Potter and the philosopher's stone.", "Joanne Rowling"));

        library.addUser(new UserOfLibrary(3,"Felix",25));
        library.addUser(new UserOfLibrary(2,"Alex",22));
        library.addUser(new UserOfLibrary(1,"John",23));

        System.out.println("___________________________________print all books______________________________________");
        library.printAllBooks();
        System.out.println("___________________________________print the certain number of books______________________________________");
        library.printBooks(2);
        System.out.println("___________________________________print a certain book______________________________________");
        library.printBook(6);

        library.sortBooks(new BookISBNComparator());
//        library.sortBooks(2, new BookISBNComparator());
//        library.sortBooks(new BookTitleComparator());
//        library.sortBooks(4, new BookTitleComparator());
//        library.sortBooks(new BookAuthorComparator());
//        library.sortBooks(3, new BookAuthorComparator());
//        library.sortBooks(new BookCreateTimeComparator());
//        library.sortBooks(5, new BookCreateTimeComparator());
//        library.sortBooks(new BookLastChangeComparator());
//        library.sortBooks(6, new BookLastChangeComparator());
        System.out.println("___________________________________print all books in sort order______________________________________");
        library.printAllBooks();

        System.out.println("___________________________________print all users______________________________________");
        library.printAllUsers();
        System.out.println("___________________________________print the certain number of books______________________________________");
        library.printUsers(2);
        System.out.println("___________________________________print a certain user______________________________________");
        library.printUser(3);

        library.sortUsers(new UserIdComparator());
//        library.sortUsers(2, new UserIdComparator());
//        library.sortUsers(new UserNameComparator());
//        library.sortUsers(3, new UserNameComparator());
//        library.sortUsers(new UserAgeComparator());
//        library.sortUsers(3,new UserAgeComparator());
        System.out.println("___________________________________print all users in sort order______________________________________");
        library.printAllUsers();

        System.out.println("___________________________________all of users took one or more books______________________________________");
        library.giveBookToUser(library.getBook(1),library.getUser(2));
        library.giveBookToUser(library.getBook(4),library.getUser(1));
        library.giveBookToUser(library.getBook(2),library.getUser(1));
        library.giveBookToUser(library.getBook(6),library.getUser(3));

        System.out.println("___________________________________print this books taken by users______________________________________");
        library.printTakenBooks();
        System.out.println("___________________________________print users with library books______________________________________");
        library.printUsersWithLibraryBooks();

        System.out.println("___________________________________try to take a book, which is already taken______________________________________");
        library.giveBookToUser(library.getBook(1),library.getUser(1));

        System.out.println("___________________________________try to write wrong values______________________________________");
        library.giveBookToUser(library.getBook(45),library.getUser(2));
        library.giveBookToUser(library.getBook(1),library.getUser(45));

        System.out.println("___________________________________try to return book, which is not taken______________________________________");
        library.returnBook(library.getBook(3));
        library.returnBook(library.getBook(5));
        library.returnBook(library.getBook(25));

        System.out.println("___________________________________try to return book, which doesn't exist______________________________________");
        library.returnBook(new Book("1-55-778899","War and Peace","Lev Tolstoy"));

        System.out.println("___________________________________try to print non-existent book and user______________________________________");
        library.printBook(0);
        library.printBook(118);
        library.printUser(-35);

        System.out.println("___________________________________all users return library books______________________________________");
        library.returnBook(library.getBook(1));
        library.returnBook(library.getBook(2));
        library.returnBook(library.getBook(4));
        library.returnBook(library.getBook(6));

        System.out.println("___________________________________print this books taken by users______________________________________");
        library.printTakenBooks();
        System.out.println("___________________________________print users with library books______________________________________");
        library.printUsersWithLibraryBooks();
    }
}
