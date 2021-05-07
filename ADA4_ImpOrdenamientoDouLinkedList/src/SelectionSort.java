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

    public static void sortByID( MovieDoublyLinkedList a) {
		//Execution variables
		long compare = 0;
		long swap = 0;
		
		MovieDoublyLink current = a.firstLinked();
		MovieDoublyLink iteraLink = null ;
		MovieDoublyLink smallLink = null ;
		
		while(current.next != null){	//No entra al ultimo link, el while anidado SI
			smallLink = current;
			if(current.next != null)	iteraLink = current.next;
			else						iteraLink = current;
			
			while(iteraLink != null){	//Desde current.next hasta el último link

				compare++; //Number of compare done
				if(iteraLink.dData.getId() < smallLink.dData.getId()){
					smallLink = iteraLink;
					swap++; //Number of swap done
				}
				iteraLink = iteraLink.next;
			}
			current = a.swap(current, smallLink);
			current = current.next;
		}
		System.out.println("Number of compare done: "+compare+"\nNumber of swap done: "+swap);
	}

    public static void sortByTittle( MovieDoublyLinkedList a){
        //Execution variables
		long compare = 0;
		long swap = 0;
		/*
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
		*/
    }
}
