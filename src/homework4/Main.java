package homework4;

public class Main {
    public static void main(String[] args) {
        BookLibrary library = new BookLibrary("BooksLibrary");

        library.addBook(new Book("The Hunger Games","Suzanne Collins"));
        library.addBook(new Book("The Da Vinci Code","Dan Brown"));
        library.addBook(new Book("Memoirs of a Geisha","Arthur Golden"));
        library.addBook(new Book("Alice's Adventures in Wonderland","Lewis Carroll"));
        library.addBook(new Book("The Picture of Dorian Gray","Oscar Wilde"));
        library.addBook(new Book("Harry Potter chapter 1", "Joanne Rowling"));
        library.addBook(new Book("Harry Potter chapter 2", "Joanne Rowling"));
        library.addBook(new Book("Harry Potter chapter 3", "Joanne Rowling"));
        library.addBook(new Book("Harry Potter chapter 4", "Joanne Rowling"));
        library.addBook(new Book("Harry Potter chapter 5", "Joanne Rowling"));

        System.out.println("___________________________________print all books______________________________________");
        library.printAllBooks();

        library.sortBooks();
        System.out.println("___________________________________print all books in sort order______________________________________");
        library.printAllBooks();

        library.addUser(new UserOfLibrary(3,"Felix",25));
        library.addUser(new UserOfLibrary(2,"Alex",22));
        library.addUser(new UserOfLibrary(1,"John",23));

        System.out.println("___________________________________print all users______________________________________");
        library.printAllUsers();

        library.sortUsers();
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
        library.returnBook(library.getBook(7));
        library.returnBook(library.getBook(10));

        System.out.println("___________________________________try to return book, which doesn't exist______________________________________");
        library.returnBook(new Book("War and Peace","Lev Tolstoy"));

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
