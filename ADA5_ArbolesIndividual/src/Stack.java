
    class Stack <E> {
    private int maxSize;
    private int top;
    private E[] stackArray;
     
   
    public Stack(int s) {
        this.maxSize = s;
        this.stackArray = (E[])new Object[maxSize];
        this.top = -1;
    }

    public void push(E j) throws StackException {
        if ( isFull() ) {
   	        throw new StackException("Stack overflow");
   	    }
        stackArray[++top] = j;
    }
    
    public E pop() throws StackException {
        E item;
        if (isEmpty()){
            throw new StackException("Stack underflow");
        }
        item = stackArray[top--];
        return item;
    }
     
    public E peek() {
        return stackArray[top]; 
    }
    
    public boolean isEmpty() {
        return (top == -1); 
    }
     
    public boolean isFull() {
        return (top == maxSize-1); 
    }
}

