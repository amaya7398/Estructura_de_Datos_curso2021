import java.util.ArrayList;

public class SelectionSort {

    public SelectionSort(){

    }

	private static void printArray(int[] a ) {
		int n = a.length;
		for (int i=0; i<n; ++i)
			System.out.println(a[i]);
	}
	private static void printArray( ArrayList<Movie> a ) {
		int n = a.size();
		for (int i=0; i<n; ++i)
			System.out.println(a.get(i));
	}

    public static void sortByID( ArrayList<Movie> a) {
		int n = a.size();

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n-1; i++) {
			int minIdx = i;
            int idSmall = a.get(i).getId();
			// Find the minimum element in unsorted array
			for (int j = i+1; j < n; j++){
                int idIter = a.get(j).getId();

				if ( idIter < idSmall){
					minIdx = j;
                    idSmall = a.get(j).getId();
                }
            }
			// Swap the found minimum element with the first element
			Movie temp = a.get(minIdx);
            a.set(minIdx, a.get(i));
            a.set(i,temp);
            System.out.println(a.get(minIdx).getId() + "<=>" + a.get(i).getId() );
		}
        //printArray(a);
	}

    public static void sortByTittle( ArrayList<Movie> a){
        System.out.println("...");
    }
    

    
    public static void sort(int[] a) {
		int n = a.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n-1; i++) {
			// Find the minimum element in unsorted array
			int minIdx = i;
			for (int j = i+1; j < n; j++)
				if (a[j] < a[minIdx])
					minIdx = j;

			// Swap the found minimum element with the first element
			int temp = a[minIdx];
			a[minIdx] = a[i];
			a[i] = temp;
		}

        printArray(a);
	}

}
