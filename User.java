public class User implements Comparable<User> {
    public static final int MAX_LOAN_NUM = 3;
    private final String name;
    private int loanNum;

    public User(String name){
        this.name = name;
        this.loanNum = 0;
    }

    public String getName() {
        return name;
    }

    public int getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(int loanNum) {
        this.loanNum = loanNum;
    }

    @Override
    public int compareTo(User user) {
        // only consider the surname of authors
        String[] name_a = this.name.split(" ");
        String[] name_b = user.name.split(" ");

        // whether the surname is same
        if (!name_a[name_a.length-1].equals(name_b[name_b.length-1]))
            return name_a[name_a.length-1].compareTo(name_b[name_b.length-1]);
        return name_a[0].compareTo(name_b[0]);
    }
}
