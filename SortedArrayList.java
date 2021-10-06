import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<? super E>> extends ArrayList<E> {
    // insert an element and maintain the ascending feature of list
    public void insert(E e) {
        // get the size of list
        int size = this.size();

        // find the proper location to insert
        for (int i = 0; i < size; ++i){
            // If it is smaller than i-th element
            if (this.get(i).compareTo(e) > 0){
                super.add(i, e);
                return;
            }
        }
        super.add(size, e);
    }
}
