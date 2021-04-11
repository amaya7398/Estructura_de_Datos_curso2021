
class DELinkList <E>{
    private DELink <E> first;
    private DELink <E> last; 
    
    public DELinkList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        if(first==null) System.out.println("Vacía, lista vacía");
        return (first==null);
    }
    public DELink <E> first(){
        if (isEmpty()){
            System.out.println("No hay link alguno");
        } else {
            System.out.print("first-> ");
            first.displayLink();
        }
        return first;
    }

    public DELink <E> last(){
        if (isEmpty()){
            System.out.println("No hay link alguno");
        } else {
            last.displayLink();
            System.out.print(" <-Last");
        }
        return last;
    }

    public void insertFirst(E dd) { 
        DELink<E> newLink = new DELink<> (dd);
        if(isEmpty())
            last = newLink;
        newLink.next = first; 
        first = newLink;
    }

    public void insertLast(E dd) { 
        DELink<E> newLink = new DELink<> (dd);
        if(isEmpty())
            first = newLink;
        else
            last.next = newLink;     
        last = newLink;
    }

    public void insertAfterData(E afterIt, E dd ){        
        if (!isEmpty()){
            DELink<E> current = search(afterIt);
            if(current.next != null ){ //Si la base NO es el ultimo nodo
                DELink<E> newNodo = new DELink<E> (dd);
                newNodo.next = current.next;
                current.next = newNodo;
            }
            if(current.next == null ) insertLast(dd); //Si la base es ultimo nodo
        }
    }

    public void insertBeforeData(E beforeIt, E dd){
        if (!isEmpty()){
            DELink<E> current = search(beforeIt);

            if(current == first){
                insertFirst(dd); //La base es el primer nodo    
            } else {
                DELink currentMinus1 = searchMinus1(beforeIt);
                //insertAfterData(currentMinus1.dData, dd);
                DELink newNodo = new DELink(dd);    //Como ya existe una
                newNodo.next = currentMinus1.next;  //función similar se
                currentMinus1.next = newNodo;   //podría usar insertAfterData
            }
        }
    }


    public E deleteFirst() {
        E temp = first.dData;
        if(first.next == null){
            last = null;
        }
        first = first.next;
        return temp;    
    }

    public E deleteLast(){
        E temp = last.dData;
        DELink<E> current = first;
        if(first.next == null){
            first = null;
        }
        while(current.next != null && current.next.next !=null){
            current = current.next;
        }
        last = current;
        current.next = null;
        return temp;  
    }

    public E deleteWUT(E data){ //Double porque necesito NULL
        E temp = null;
        if(first.dData == data) temp = deleteFirst();
        if(last.dData == data) temp = deleteLast();
        return temp;
    }

    public E deleteData(E data){
        E temp = deleteWUT(data); //Si es NULL->no ha sido removido
        if (!isEmpty() && temp == null){ //Ni del inicio ni del final
                DELink<E> current = searchMinus1(data);
                temp = deleteAfterData(current.dData);
        }
        return temp;
    }

    public E deleteAfterData(E data){
        DELink<E> current = search(data);
        E temp = null;

        if(current.next == null) System.out.println("No hay nada después del final");
        if(current.next != null) temp = deleteWUT(current.next.dData);
        if (!isEmpty() && temp == null && current.next != null){
            temp = current.next.dData;
            current.next = current.next.next;
        }
        return temp;
    }

    public DELink<E> search(E dData){
        DELink<E> current = first;
        while( current != null && current.dData != dData) {
            current = current.next; 
        }
        return current;
    }

    public DELink<E> searchMinus1(E dData){
        DELink<E> current = null;
        if(!isEmpty() && first.dData != dData && first.next != null){
            current = first;
            while(current.next != null){
                if(current.next.dData == dData) break;
                current = current.next;
            }
        }
        return current;
    }

    public void updateData(E data, E newData){
        
        DELink<E> current = search(data);
        if(current != null){
            current.dData = newData;
            System.out.println(data + " <=> "+newData+" new data");
        } else {
            System.out.println("Link no encontrado");
        }
    }
    public void displayList() {
        System.out.print("List (first--> ");
        //System.out.println(first+ "<--last)");    //Posible cambio
        DELink<E> current = first;
        while( current != null) {
            current.displayLink(); 
            current = current.next; 
        }
        System.out.print("<--last)\n");
    }

}