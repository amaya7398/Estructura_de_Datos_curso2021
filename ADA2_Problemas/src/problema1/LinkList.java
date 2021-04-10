class LinkList {
    private Link first;
    
    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return (first==null);
    }

    public void insertFirst(double dd) { 
        Link newLink = new Link(dd);
        newLink.next = first; 
        first = newLink;
    }

    public Link deleteFirst() {
        Link temp=null; 
        if(!isEmpty()){
            temp = first;
            first = first.next;
        }
        return temp; 
    }

    public void displayList() {
        System.out.print("List (first--> ");
        Link current = first;
        while(current != null) {
            current.displayLink(); 
            current = current.next; 
        }
        System.out.println("<--last)");
    }

}