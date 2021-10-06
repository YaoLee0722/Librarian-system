public class Book implements Comparable<Book>{
    private final String title;
    private final String author;
    private boolean loan;       // whether loan
    private String loanUser;    // the name of user who loan it

    public Book(String title, String author){
        this.title = title;
        this.author = author;
        this.loan = false;
        this.loanUser = "";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isLoan() {
        return loan;
    }

    public void setLoan(boolean loan) {
        this.loan = loan;
    }

    public String getLoanUser() {
        return loanUser;
    }

    public void setLoanUser(String loanUser) {
        this.loanUser = loanUser;
    }

    @Override
    public int compareTo(Book book) {
        // only consider the surname of authors
        String[] name_a = this.author.split(" ");
        String[] name_b = book.author.split(" ");
        return name_a[name_a.length-1].compareTo(name_b[name_b.length-1]);
    }
}
