public class DoublyLinkedList<E> {
    private DoublyLink<E> first; 
    private DoublyLink<E> last; 
    public String msgError = "No se puede, no hay tortillas";
    
    public DoublyLinkedList() {
        first = null; 
        last = null;
    }

    public boolean isEmpty() { 
        return first==null; 
    }

    public E showFirst(){
        if(isEmpty())   System.out.println("Lista vacía");
        return first.dData;
    }
    public E showLast(){
        if(isEmpty())   System.out.println("Lista vacía");
        return last.dData;
    }

    public void insertFirst(E dd){
        DoublyLink<E> newLink = new DoublyLink<> (dd); 
        if( isEmpty() ) 
            last = newLink; 
        else
            first.previous = newLink;
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(E dd) {
        DoublyLink<E> newLink = new DoublyLink<> (dd); 
        if( isEmpty() ) 
            first = newLink; 
        else {
            last.next = newLink; 
            newLink.previous = last; 
        }
        last = newLink; 
    }

    public DoublyLink<E> deleteFirst() { 
        DoublyLink<E> temp = first;
        if(first.next == null) 
            last = null;
        else
            first.next.previous = null;
        first = first.next; 
        return temp;
    }

    public DoublyLink<E> deleteLast(){
        DoublyLink<E> temp = last;
        if(first.next == null) 
            first = null; 
        else
            last.previous.next = null; 
        last = last.previous; 
        return temp;
    }
    
    public boolean insertAfter(E key, E dd) { 
        DoublyLink<E> current = search(key);    /*#####if(search(key) != null)*/
        if(current == last) insertLast(dd); //Solo quedaría ser first o en medio
        else                insertLink(current, current.next, dd);
        return true; 
    }

    public boolean insertBefore(E key, E dd){
        DoublyLink<E> current = search(key);    /*#####if(search(key) != null*/
        if(current == first) insertFirst(dd);//Solo quedaría ser last o en medio
        else                 insertLink(current.previous, current, dd);
        return true;
    }

    //Acomodamos el insertLink para que vaya de izquierda a derecha sin
    //importar si es after o before. After empieza con current -> current.next
    //before empieza con current.before -> current
    public boolean insertLink (DoublyLink<E> route, DoublyLink<E> link, E ddNew){
        //Si no primero ni ultimo; Será medio
            DoublyLink<E> newLink = new DoublyLink<>(ddNew);
            newLink.next = link;
            newLink.previous = route;
            route.next = newLink;
            route.next.next.previous = newLink;
        return true;
    }

    public DoublyLink<E> deleteAfter(E key){
        DoublyLink<E> current = search(key);         
        if(current==last || current == null) System.out.println(msgError);
        else              return deleteKey(current.next.dData);
    return current; //Por aquí debería ser null
    }

    public DoublyLink<E> deleteBefore(E key){
        DoublyLink<E> current = search(key);
        if(current==first || current == null) System.out.println(msgError);
        else               return deleteKey(current.previous.dData);
    return current; //Por aquí debería ser null
    }

    
    public DoublyLink<E> deleteKey(E key) { //Eliminar nodo dado
        DoublyLink<E> current = search(key);
        
        if(current != first && current != last){ //Si no es primero ni
            current.previous.next = current.next;   //ultimo, estará en
            current.next.previous = current.previous;  //medio de nodos
        } else {
            if(current==first) current = deleteFirst();
            else               current = deleteLast();
        }
    return current;
    }

    public void updateData(E key, E newData){
        DoublyLink<E> current = search(key);   //Actualizar dato de NODO con key
        if(current != null)     current.dData = newData;
        else                    System.out.println("Nodo no encontrado - "+msgError);
    }

    public DoublyLink<E>  search(E key){    //Buscar nodo por llave/dData
        DoublyLink<E> current = first;
        while(current!=null){
            if(current.dData == key)    break;
            current = current.next;
        }
        return current;
    }

    public void displayForward() {
        System.out.print("List (first-->last): ");
        DoublyLink<E> current = first;
        while(current != null) {
            current.displayLink(); 
            current = current.next; 
        }
        System.out.println(" =>");
    }

    public void displayBackward() {
        System.out.print("List (last-->first): ");
        DoublyLink<E> current = last;
        while(current != null) {
            current.displayLink(); 
            current = current.previous; 
        }
        System.out.println(" =>");
    }
} 