package homework4;

import java.time.LocalDateTime;

class Book{
    public static final String TYPE = "BOOK";
    public static final int DIFFERENCE_IN_TIME = 1;
    private final String ISBN;
    private String title;
    private String author;
    private KeeperOfBooks keeper;
    private LocalDateTime dateOfCreation = LocalDateTime.now().plusHours(DIFFERENCE_IN_TIME);
    private LocalDateTime dateOfLastChange;
    Book(String ISBN, String title, String author){
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public KeeperOfBooks getKeeper() {
        return keeper;
    }

    public void setKeeper(KeeperOfBooks keeper) {
        this.keeper = keeper;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDateTime getDateOfLastChange() {
        return dateOfLastChange;
    }

    public void setDateOfLastChange(LocalDateTime dateOfLastChange) {
        this.dateOfLastChange = dateOfLastChange;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Book
                && ISBN.equals(((Book) obj).getISBN());
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(ISBN)+title.hashCode()+author.hashCode()*2;
    }

    @Override
    public String toString() {
        return TYPE+"\t"+ISBN+"\t"+title+"\t"+author+"\t"+keeper.getUniqueValue()+"\t"+dateOfCreation+"\t"+dateOfLastChange;
    }
}
