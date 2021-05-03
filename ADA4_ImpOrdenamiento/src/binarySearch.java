import java.util.ArrayList;

public class binarySearch {
    
    binarySearch(ArrayList<Movie> list, int data){
        search(list, data, 0, list.size());
    }

    public int search(ArrayList<Movie> list, int data, int first, int last) {

        //int mid = (first + last) / 2;
        int mid = (int) Math.floor((first + last) / 2);
    
        if (last < first)                       return -1;
        if (data == list.get(mid).getId())      return mid;
        if (data < list.get(mid).getId())       return search(list, data, first, mid - 1);
        if (data > list.get(mid).getId())       return search(list, data, mid + 1, last);
        return -1;
    }

}
