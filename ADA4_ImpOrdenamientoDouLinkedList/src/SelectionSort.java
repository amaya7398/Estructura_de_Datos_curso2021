import java.util.ArrayList;

public class SelectionSort {

    public SelectionSort(){
		//empty
    }

	
	public static void sortingBy(MovieDoublyLinkedList a, boolean sortBy){
		//Execution variables
		long compare = 0;
		long swap = 0;
		
		MovieDoublyLink current = a.firstLinked();
		MovieDoublyLink iteraLink = null ;
		MovieDoublyLink smallLink = null ;
		
		while(current.next != null){	//No entra al ultimo link, el while anidado SI
			smallLink = current;
			iteraLink = current.next;
			
			while(iteraLink != null){	//Desde current.next hasta el último link
				
				compare++; //Number of compare done
				
				if(sortBy) {	//sortBy(TRUE) => SORT BY ID
					if (iteraLink.dData.getId() < smallLink.dData.getId() ){
						smallLink = iteraLink;
						swap++; //Number of swap done
					}
				} else { 		//sortBy(false) => SORT BY TITTLE
					if(iteraLink.dData.getTitle().compareTo(smallLink.dData.getTitle()) < 0){
						smallLink = iteraLink;
						swap++; //Number of swap done
					}
				}
				iteraLink = iteraLink.next;
			}
			current = a.swap(current, smallLink);
			current = current.next;
		}
		System.out.println("Number of compare done: "+compare+"\nNumber of swap done: "+swap);
	}

	/*
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

		MovieDoublyLink current = a.firstLinked();
		MovieDoublyLink iteraLink = null ;
		MovieDoublyLink smallLink = null ;
		
		while(current.next != null){	//No entra al ultimo link, el while anidado SI
			smallLink = current;
			if(current.next != null)	iteraLink = current.next;
			else						iteraLink = current;
			
			while(iteraLink != null){	//Desde current.next hasta el último link

				compare++; //Number of compare done
				if(iteraLink.dData.getTitle().compareTo(smallLink.dData.getTitle()) < 0){
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
	*/
}
