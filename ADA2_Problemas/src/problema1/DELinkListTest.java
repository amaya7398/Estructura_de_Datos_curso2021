public class DELinkListTest {
    
    public static void main(String[] args) {
        /**
         * devolver primer elemento de la lista
         * devolver ultimo elemento de la lista
         * insertar y eliminar antes de un vlor dado
         * insertar y eliminar después de un valor dado
         * Busqueda y eliinar nodo dado             
         * Actualizar un nodo
         */

        DELinkList list = new DELinkList(); 
        list.insertFirst(2.99);
        list.insertFirst(4.99);
        list.insertFirst(6.99);
        list.insertFirst(8.99);
            list.displayList(); 
    System.out.println(list.deleteData(4.99)+" fue eliminado");
            list.displayList();
    System.out.println("VALORES ACTUALIZADOS");
        list.updateData(2.99, 3.99); //2.99-> 3.99
        list.first();
        list.last();
                System.out.println("\n");
    System.out.print("#################");
            list.displayList();
        list.insertAfterData(3.99, 2.00);
    System.out.print("## AFTER DATA  ##");
            list.displayList();
        list.insertBeforeData(8.99, 9.99);
    System.out.print("## BEFORE DATA ##");
            list.displayList();
        list.insertAfterData(6.99, 5.99);
    System.out.print("## AFTER DATA  ##");
            list.displayList();
        list.insertBeforeData(6.99, 7.99);
    System.out.print("## BEFORE DATA ##");
            list.displayList();
    } 
}