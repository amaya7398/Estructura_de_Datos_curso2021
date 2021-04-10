public class Link {
    public double dData;
    public Link next; 

    public Link(double dd) {
        dData = dd;
    }

    public void displayLink() {
        System.out.print("{" + dData + "} ");
    }
}