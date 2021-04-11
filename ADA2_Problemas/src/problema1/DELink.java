public class DELink <E> {
    public E dData;
    public DELink <E> next; 
    
    public DELink(){ }
    public DELink(E dd) {
        dData = dd;
    }

    public void displayLink() {
        System.out.print("{" + dData + "} ");   }
    
    @Override
    public String toString() {
        return "[{dData=" + dData + "}, next=> " + next + "]";  }
    
}