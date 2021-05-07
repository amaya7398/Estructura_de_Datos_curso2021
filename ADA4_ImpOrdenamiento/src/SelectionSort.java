import java.util.ArrayList;

public class SelectionSort {

    public SelectionSort(){
		//empty
    }

	private static void printArray( ArrayList<Movie> a ) {
		int n = a.size();
		for (int i=0; i<n; ++i)
			System.out.println(a.get(i));
	}

	private static void printArray(int[] a ) {
		int n = a.length;
		for (int i=0; i<n; ++i)
			System.out.println(a[i]);
	}

    public static void sortByID( ArrayList<Movie> a) {
		//Execution variables
		long compare = 0;
		long swap = 0;
		
		int n = a.size();
		
		// One by one move boundary of unsorted subarray
		for (int i=0 ; i<n-1 ; i++) {
			int minIdx = i;
            int idSmall = a.get(i).getId();
			// Find the minimum element in unsorted array
			for (int j = i+1; j < n; j++){
                int idIter = a.get(j).getId();
				
				compare++; //Number of compare done
				if ( idIter < idSmall){
					swap++; //Number of swap done
					minIdx = j;
                    idSmall = a.get(j).getId();
                }
            }
			// Swap the found minimum element with the first element
			Movie temp = a.get(minIdx);
            a.set(minIdx, a.get(i));
            a.set(i,temp);
            //System.out.println(a.get(minIdx).getId() + "<=>" + a.get(i).getId() );
		}
		System.out.println("Number of compare done: "+compare+"\nNumber of swap done: "+swap);
        //printArray(a);
	}

    public static void sortByTittle( ArrayList<Movie> a){
        //Execution variables
		long compare = 0;
		long swap = 0;
		
		int n = a.size();
		for(int i=0 ; i<n-1 ; i++){
			int smallIndex = i;
			String smallTitle = a.get(smallIndex).getTitle();
			for(int j=i+1 ; j<n ; j++){
				String title = a.get(j).getTitle();
				
				compare++; //Number of compare done
				// -1 == title<SmallTitle | 0 == equals() | 1 == title>smallTitle
				if( title.compareTo(smallTitle ) < 0){
					swap++; //Number of swap done
					smallIndex = j;
					smallTitle = title;
				}
			}
			Movie temp = a.get(smallIndex);
			//      index  ,  element
			a.set(smallIndex, a.get(i));
			a.set(i , temp);
		}
		System.out.println("Number of compare done: "+compare+"\nNumber of swap done: "+swap);
		//printArray(a);
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
