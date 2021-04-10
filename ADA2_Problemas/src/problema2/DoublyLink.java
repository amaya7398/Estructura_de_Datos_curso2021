public class DoublyLink {
    public long dData; 
    public DoublyLink next; 
    public DoublyLink previous; 

    public DoublyLink(long d) { 
        dData = d; 
    }

    public void displayLink() { 
        System.out.print(dData + ","); 
    }
} 