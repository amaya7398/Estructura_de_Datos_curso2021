public class MovieDoublyLinkedList {
    private MovieDoublyLink first; 
    private MovieDoublyLink last; 
    private int size = 0;
    public String msgError = "No se puede, no hay tortillas";
    
    public MovieDoublyLinkedList() {
        first = null; 
        last = null;
    }

    public int sizeList(){
        return size;
    }

    public boolean isEmpty() { 
        return first==null; 
    }

    public MovieDoublyLink showFirst(){
        if(isEmpty())   System.out.println("Lista vacía");
        return first;
    }
    public MovieDoublyLink showLast(){
        if(isEmpty())   System.out.println("Lista vacía");
        return last;
    }

    public void insertFirst(Movie dd){
        MovieDoublyLink newLink = new MovieDoublyLink(dd); 
        if( isEmpty() ) 
            this.last = newLink; 
        else
            first.previous = newLink;
        newLink.next = first;
        this.first = newLink;
        this.size++;
    }

    public void insertLast(Movie dd) {
        MovieDoublyLink newLink = new MovieDoublyLink(dd); 
        if( isEmpty() ) 
            this.first = newLink; 
        else {
            last.next = newLink; 
            newLink.previous = last; 
        }
        this.last = newLink;
        this.size++;
    }

    public MovieDoublyLink deleteFirst() { 
        MovieDoublyLink temp = first;
        if(first.next == null) 
            last = null;
        else
            first.next.previous = null;
        first = first.next;
        this.size--;
        return temp;
    }

    public MovieDoublyLink deleteLast(){
        MovieDoublyLink temp = last;
        if(first.next == null) 
            first = null; 
        else
            last.previous.next = null; 
        last = last.previous;
        this.size--;
        return temp;
    }
    
    public boolean insertAfter(long key, Movie dd) { 
        MovieDoublyLink current = search(key); 
        if(current == last) insertLast(dd); //Solo quedaría ser first o en medio
        else                insertLink(current, current.next, dd);
        return true; 
    }   

    public boolean insertBefore(long key, Movie dd){
        MovieDoublyLink current = search(key); 
        if(current == first) insertFirst(dd);//Solo quedaría ser last o en medio
        else                 insertLink(current.previous, current, dd);
        return true;
    }

    //Acomodamos el insertLink para que vaya de izquierda a derecha sin
    //importar si es after o before. After empieza con current -> current.next
    //before empieza con current.before -> current
    public boolean insertLink (MovieDoublyLink route, MovieDoublyLink link, Movie ddNew){
        //Si no primero ni ultimo; Será medio
            MovieDoublyLink newLink = new MovieDoublyLink(ddNew);
            newLink.next = link;
            newLink.previous = route;
            route.next = newLink;
            route.next.next.previous = newLink;
            this.size++;
        return true;
    }

    public MovieDoublyLink deleteAfter(long key){
        MovieDoublyLink current = search(key);         
        if(current==last || current == null) System.out.println(msgError);
        //else              return deleteKey(current.next.dData.getId());
        else              return deleteKeyPointer(current.next);
    return null;
    }

    public MovieDoublyLink deleteBefore(long key){
        MovieDoublyLink current = search(key);         
        if(current==first || current == null) System.out.println(msgError);
        //else               return deleteKey(current.previous.dData.getId());
        else               return deleteKeyPointer(current.previous);
    return null;
    }

    
    public MovieDoublyLink deleteKey(long key) { //Eliminar nodo dado
        MovieDoublyLink current = search(key);
        
        if(current != first && current != last){ //Si no es primero ni
            current.previous.next = current.next;   //ultimo, estará en
            current.next.previous = current.previous;  //medio de nodos
            this.size--;
        } else {
            if(current==first)  current = deleteFirst();
            else                current = deleteLast();
        }
    return current;
    }

    public MovieDoublyLink deleteKeyPointer(MovieDoublyLink current) { //Eliminar nodo dado
        if(current != first && current != last){ //Si no es primero ni
            current.previous.next = current.next;   //ultimo, estará en
            current.next.previous = current.previous;  //medio de nodos
            this.size--;
        } else {
            if(current==first)  current = deleteFirst();
            else                current = deleteLast();
        }
    return current;
    }

    public void updateData(Long key, Movie newData){
        MovieDoublyLink current = search(key);   //Actualizar dato de NODO con key
        if(current != null)   current.dData = newData;
        else                  System.out.println("Nodo no encontrado - "+msgError);
    }

    public MovieDoublyLink  search(long key){    //Buscar nodo por llave/dData
        MovieDoublyLink current = first;
        while(current!=null){
            if(current.dData.getId() == key)    break;
            current = current.next;
        }
        return current;
    }

    //###############################################################################
    // Functions FOR SELECTIONSORT.JAVA
    //###############################################################################

    public MovieDoublyLink firstLinked(){
        return first;
    }

    public MovieDoublyLink lastLinked(){
        return last;
    }

    public MovieDoublyLink swap(MovieDoublyLink i , MovieDoublyLink sm){
        //i será el primer objeto "desordenado" => SI small == I, estará ordenado
        if(i != sm){
            insertLink(sm.previous, sm, i.getMovie());
            insertLink(i, i.next, deleteKeyPointer(sm).getMovie());
            MovieDoublyLink temp = i.next;
            deleteKeyPointer(i);
            return temp;
        }
        return i;
    }

    // v ##### DISPLAY FORMATS, NO MORE CODE ##### v
    // ###############################################

    public void displayForward() {
        System.out.print("List (first-->last): ");
        MovieDoublyLink current = first;
        while(current != null) {
            current.displayLink(); 
            current = current.next; 
        }
        System.out.println(" =>");
    }

    public void displayBackward() {
        System.out.print("List (last-->first): ");
        MovieDoublyLink current = last;
        while(current != null) {
            current.displayLink(); 
            current = current.previous; 
        }
        System.out.println(" =>");
    }

    public void displayForward(int max) {
        MovieDoublyLink current = first;
        int cont=0;
        while(current != null && cont<max) {
            current.displayLink(); 
            current = current.next; 
            cont++;
        }
    }

    public void displayBackward(int max) {
        MovieDoublyLink current = last;
        int cont=0;
        while(current != null && cont<max) {
            current.displayLink(); 
            current = current.previous;
            cont++;
        }
    }
} 
