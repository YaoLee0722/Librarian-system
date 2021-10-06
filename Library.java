import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Library {
    private int numOfBooks;    // the num of books
    private int numOfUsers;    // the num of users
    private SortedArrayList<Book> books;
    private SortedArrayList<User> users;

    public Library(){
        numOfBooks = 0;
        numOfUsers = 0;
        books = new SortedArrayList<>();
        users = new SortedArrayList<>();
    }

    public void AddBook(Book book){
        numOfBooks += 1;
        books.insert(book);
    }

    public void AddUser(User user){
        numOfUsers += 1;
        users.insert(user);
    }

    public void ShowBooks(){
        System.out.println("------------ Books Information ------------");
        System.out.println("There are " + numOfBooks + " books:");
        for (int i = 0; i < numOfBooks; ++i){
            Book book = books.get(i);
            System.out.println(book.getTitle() + ", " + book.getAuthor()
                    + ", " + (!book.isLoan()? "available" : "on loan by " + book.getLoanUser()));
        }
    }

    public void ShowUsers(){
        System.out.println("------------ Users Information ------------");
        System.out.println("There are " + numOfUsers + " users:");
        for(int i = 0; i < numOfUsers; ++i){
            User user = users.get(i);
            System.out.println(user.getName() + ", " + "holding " + user.getLoanNum() + " books");
        }
    }

    private int FindBook(String title){
        // find the book id
        int book_index = -1;
        for(int i = 0; i < numOfBooks; ++i){
            if (title.equals(books.get(i).getTitle())){
                book_index = i;
            }
        }

        // if the book is not exist
        if (book_index == -1){
            System.out.println("No such book!");
        }
        return book_index;
    }

    private int FindUser(String userName){
        // find the book id
        int user_index = -1;
        for(int i = 0; i < numOfUsers; ++i){
            if (userName.equals(users.get(i).getName())){
                user_index = i;
            }
        }

        // if the user is not exist
        if (user_index == -1){
            System.out.println("No such user!");
        }
        return user_index;
    }

    public void Issue(String title, String userName){
        // find the book
        int book_index = FindBook(title);
        if (book_index == -1)
            return;

        // find the book id
        int user_index = FindUser(userName);
        if (user_index == -1)
            return;

        // if the user has max loan
        User user = users.get(user_index);
        if (user.getLoanNum() == User.MAX_LOAN_NUM){
            System.out.println("The user has hold 3 books and cannot issue more!");
            return;
        }

        // if the book is on loan
        Book book = books.get(book_index);
        if (book.isLoan()) {
            System.out.println("The book is on loan!");
            System.out.println("And the holder has been informed that: [the book was requested by another user " +
                    "and should be returned as soon as possible]");
            try {
                PrintWriter pw = new PrintWriter("notes.txt");
                pw.append("the book [")
                        .append(title)
                        .append("] was requested by another user and should be returned as soon as possible");
                pw.close();
            } catch (FileNotFoundException exception){
                System.out.println("this notes.txt file is not exist!");
            }
            return;
        }

        // if the book is available
        user.setLoanNum(user.getLoanNum() + 1);
        book.setLoan(true);
        book.setLoanUser(user.getName());
        System.out.println("Successfully!");
    }

    public void Return(String title, String userName){
        // find the book
        int book_index = FindBook(title);
        if (book_index == -1)
            return;

        // find the book id
        int user_index = FindUser(userName);
        if (user_index == -1)
            return;

        // if the book is on loan
        Book book = books.get(book_index);
        if (!book.isLoan() || !book.getLoanUser().equals(userName)) {
            System.out.println("The book is not on loan by this user!");
            return;
        }

        User user = users.get(user_index);
        user.setLoanNum(user.getLoanNum() - 1);
        book.setLoan(false);
        book.setLoanUser("");
        System.out.println("Successfully!");
    }

    void Admin(){
        System.out.println("Here are the commands");
        System.out.println("f - to finish running the program.");
        System.out.println("b - to display on the screen the information about all the books in the library.");
        System.out.println("u - to display on the screen the information about all the users.");
        System.out.println("i,[title],[userName] - to update the stored data when a book is issued to a user.");
        System.out.println("r,[title],[userName] - to update the stored data when a user returns a book to the library.");

        while (true){
            Scanner in = new Scanner(System.in);
            System.out.print("\nPlease input the command: ");
            String[] command = in.nextLine().split(",");

            switch (command[0]) {
                case "f" -> {
                    System.out.println("Goodbye.");
                    return;
                }
                case "b" -> this.ShowBooks();
                case "u" -> this.ShowUsers();
                case "i" -> {
                    if (command.length != 3) {
                        System.out.println("Wrong command format, it should be i,[title],[userName]");
                        break;
                    }
                    this.Issue(command[1], command[2]);
                }
                case "r" -> {
                    if (command.length != 3) {
                        System.out.println("Wrong command format, it should be r,[title],[userName]");
                        break;
                    }
                    this.Return(command[1], command[2]);
                }
                default -> System.out.println("The command <" + command[0] + "> is not exist!");
            }
        }
    }
}
