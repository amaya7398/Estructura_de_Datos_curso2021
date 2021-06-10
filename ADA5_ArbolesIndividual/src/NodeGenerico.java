public class NodeGenerico <T>{
    private String data;
    private NodeGenerico<T> leftChild;
    private NodeGenerico<T> rightChild;

    public void displayNode(){
        System.out.println( "("+data+")" ); 
    }

    public String getData(){
        return this.data;
    }
    public void setData(String data){
        this.data = data;
    }

    public NodeGenerico<T> getLeftChild(){
        return this.leftChild;
    }

    public NodeGenerico<T> getRightChild(){
        return this.rightChild;
    }

    public void setLeftChild(NodeGenerico<T> left){
        this.leftChild = left;
    }

    public void setRightChild(NodeGenerico<T> right){
        this.rightChild = right;
    }

    @Override
    public String toString() {
        return data;
    }
}