public class DoublyLinkedTest {
    public static void main(String[] args) {
    /**
* devolver primer elemento de la lista
* devolver ultimo elemento de la lista
         * insertar y eliminar antes de un vlor dado
         * insertar y eliminar después de un valor dado
* Busqueda y eliinar nodo dado             
* Actualizar un nodo
         */
    DoublyLinkedList theList = new DoublyLinkedList();
    theList.insertFirst(22); 
    theList.insertFirst(44);
    theList.insertFirst(66);
    theList.insertLast(11); 
    theList.insertLast(33);
    theList.insertLast(55);
            theList.displayForward(); 
    theList.updateData(22, 22);
            theList.displayBackward(); 
    theList.deleteFirst(); 
    theList.deleteLast(); 
    theList.deleteKey(11); 
            theList.displayForward(); 
    theList.insertAfter(22, 77); 
    theList.insertAfter(33, 88); 
            theList.displayForward(); 
    } 
}