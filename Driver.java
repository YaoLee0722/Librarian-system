import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        Library library = new Library();
        try {
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            // add all books to library
            int booksNum = Integer.parseInt(in.readLine());
            for(int i = 0; i < booksNum; ++i){
                String title = in.readLine();
                String author = in.readLine();
                library.AddBook(new Book(title, author));
            }

            // add all users to library
            int usersNum = Integer.parseInt(in.readLine());
            for(int i = 0; i < usersNum; ++i){
                String name = in.readLine();
                library.AddUser(new User(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Run
        library.Admin();
    }
}
