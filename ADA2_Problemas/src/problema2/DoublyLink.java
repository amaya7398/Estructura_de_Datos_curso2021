public class DoublyLink<E> {
    public E dData; 
    public DoublyLink<E> next; 
    public DoublyLink<E> previous; 

    public DoublyLink(E d) { 
        dData = d; 
    }

    public void displayLink() { 
        System.out.print(dData + ","); 
    }
} 