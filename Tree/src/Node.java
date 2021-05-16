public class Node <T>{
    private T data;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public void displayNode(){
        System.out.println( "("+data+")" ); 
    }

    public T getData(){
        return this.data;
    }
    public void setData(T data){
        this.data = data;
    }

    public Node<T> getLeftChild(){
        return this.leftChild;
    }

    public Node<T> getRightChild(){
        return this.rightChild;
    }

    public void setLeftChild(Node<T> left){
        this.leftChild = left;
    }

    public void setRightChild(Node<T> right){
        this.rightChild = right;
    }

    @Override
    public String toString() {
        return "" + data;
    }
}