public class DoublyLinkedList {
    private DoublyLink first; 
    private DoublyLink last; 
    private String msgError = "No se puede, no hay tortillas";
    
    public DoublyLinkedList() {
        first = null; 
        last = null;
    }

    public String getError(){return msgError;}
    public boolean isEmpty() { 
        return first==null; 
    }

    public DoublyLink showFirst(){
        if(isEmpty())   System.out.println("Lista vacía");
        return first;
    }
    public DoublyLink showLast(){
        if(isEmpty())   System.out.println("Lista vacía");
        return last;
    }

    public void insertFirst(long dd){
        DoublyLink newLink = new DoublyLink(dd); 
        if( isEmpty() ) 
            last = newLink; 
        else
            first.previous = newLink;
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(long dd) {
        DoublyLink newLink = new DoublyLink(dd); 
        if( isEmpty() ) 
            first = newLink; 
        else {
            last.next = newLink; 
            newLink.previous = last; 
        }
        last = newLink; 
    }

    public DoublyLink deleteFirst() { 
        DoublyLink temp = first;
        if(first.next == null) 
            last = null;
        else
            first.next.previous = null;
        first = first.next; 
        return temp;
    }

    public DoublyLink deleteLast(){
        DoublyLink temp = last;
        if(first.next == null) 
            first = null; 
        else
            last.previous.next = null; 
        last = last.previous; 
        return temp;
    }
    
    public boolean insertAfter(long key, long dd) { 
        DoublyLink current = search(key); 
        if(current == last) insertLast(dd); //Solo quedaría ser first o en medio
        else                insertLink(current, current.next, dd);
        return true; 
    }

    public boolean insertBefore(long key, long dd){
        DoublyLink current = search(key); 
        if(current == first) insertFirst(key);//Solo quedaría ser last o en medio
        else                 insertLink(current.previous, current, dd);
        return true;
    }

    //Acomodamos el insertLink para que vaya de izquierda a derecha sin
    //importar si es after o before. After empieza con current -> current.next
    //before empieza con current.before -> current
    public boolean insertLink (DoublyLink route, DoublyLink link, long key){
        //Si no primero ni ultimo; Será medio
            DoublyLink newLink = new DoublyLink(key);
            newLink.next = link;
            newLink.previous = route;
            route.next = newLink;
            route.next.next.previous = newLink;
        return true;
    }

    public DoublyLink deleteAfter(long key){
        DoublyLink current = search(key);         
        if(current==last) System.out.println(getError());
        else              return deleteKey(current.next.dData);
    return null;
    }

    public DoublyLink deleteBefore(long key){
        DoublyLink current = search(key);         
        if(current==first) System.out.println(getError());
        else               return deleteKey(current.previous.dData);
    return null;
    }

    
    public DoublyLink deleteKey(long key) { //Eliminar nodo dado
        DoublyLink current = search(key);
        
        if(current != first && current != last){ //Si no es primero ni
            current.previous.next = current.next;   //ultimo, estará en
            current.next.previous = current.previous;  //medio de nodos
        } else {
            if(current==first)  deleteFirst();
            else                deleteLast();
        }
    return current;
    }

    public void updateData(long key, long newData){
        DoublyLink current = search(key);   //Actualizar dato de NODO con key
        if(current != null){
            current.dData = newData;
        }

    }

    public DoublyLink  search(long key){    //Buscar nodo por llave/dData
        DoublyLink current = first;
        while(current!=null){
            if(current.dData == key) break;
            current = current.next;
        }
        return current;
    }

    public void displayForward() {
        System.out.print("List (first-->last): ");
        DoublyLink current = first;
        while(current != null) {
            current.displayLink(); 
            current = current.next; 
        }
        System.out.println(" =>");
    }

    public void displayBackward() {
        System.out.print("List (last-->first): ");
        DoublyLink current = last;
        while(current != null) {
            current.displayLink(); 
            current = current.previous; 
        }
        System.out.println(" =>");
    }
} 