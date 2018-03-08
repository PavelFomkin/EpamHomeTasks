package homework4;

class Book implements Comparable<Book>{
    public static final String TYPE = "BOOK";
    private String title;
    private String author;
    private KeeperOfBooks keeper;
    Book(String title, String author){
        this.title = title;
        this.author = author;
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

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Book
                && title.equals(((Book) obj).getTitle())
                && author.equals(((Book) obj).getAuthor());
    }

    @Override
    public int hashCode() {
        return title.hashCode()+author.hashCode()*2;
    }

    @Override
    public String toString() {
        return "BOOK\t"+title+"\t"+author+"\t"+keeper.getUniqueValue();
    }

    @Override
    public int compareTo(Book o) {
        return title.compareTo(o.title);
    }
}
