public class binarySearch {
    
    binarySearch(int[] list, int data){
        search(list, data, 0, list.length);
    }



    public int search(int[] list, int data, int first, int last) {

        //int mid = (first + last) / 2;
        int mid = (int) Math.floor((first + last) / 2);
    
        if (last < first)           return -1;
        if (data == list[mid])      return mid;
        if (data < list[mid])       return search(list, data, first, mid - 1);
        if (data > list[mid])       return search(list, data, mid + 1, last);
        return -1;
    }

}
