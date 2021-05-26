public class Node{
    private int data;
    private Node leftChild;
    private Node rightChild;

    public void displayNode(){
        System.out.print("("); 
        System.out.print(data); 
        System.out.print(")");
    }

    public int getData(){
        return this.data;
    }
    public void setData(int data){
        this.data = data;
    }

    public Node getLeftChild(){
        return this.leftChild;
    }

    public Node getRightChild(){
        return this.rightChild;
    }

    public void setLeftChild(Node left){
        this.leftChild = left;
    }

    public void setRightChild(Node right){
        this.rightChild = right;
    }
}