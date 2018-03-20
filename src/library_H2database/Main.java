package library_H2database;

public class Main {
    public static void main(String[] args) {

        /*
        *   This application works with H2 database.
        *   It realize a simple library of books.
        */

        try {
            H2Database h2 = new H2Database();
            System.out.println("____________________________print all authors____________________________");
            h2.printAuthors();
            System.out.println("____________________________print all users in order by ISBN____________________________");
            h2.printBooksByISBN();
            System.out.println("____________________________print 3 first books in order by ISBN____________________________");
            h2.printBooksByISBN(3);
            System.out.println("____________________________add one book____________________________");
            h2.addBook(457866514,"War and Peace","Lev Tolstoy");
            System.out.println("____________________________change information about remained amounts for some books____________________________");
            h2.setRemainedAmount(100685487, 3);
            h2.setRemainedAmount(112445781, 6);
            h2.setRemainedAmount(255478579, 4);
            h2.setRemainedAmount(352648751, 8);
            h2.setRemainedAmount(522214671, 2);
            h2.setRemainedAmount(526114571, 13);
            System.out.println("____________________________print all users in order by titles____________________________");
            h2.printBooksByTitle();
            System.out.println("____________________________print all users in order by authors____________________________");
            h2.printBooksByAuthor();
            System.out.println("____________________________print all users in order by remained amounts____________________________");
            h2.printBooksByRemainedAmount();
            System.out.println("____________________________print all users in order by dates____________________________");
            h2.printBooksByDate();
            System.out.println("____________________________print information about the book by ISBN____________________________");
            h2.printBook(100685487);
            h2.printBook(352648751);
            h2.printBook(522214671);

        } finally {
            H2Database.closeConnection();
        }
    }
}
