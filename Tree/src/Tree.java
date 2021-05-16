public class Tree{
    private Node root;

    public Tree(){
        root = null;
    }

    public Node find(int data) {
        Node current = root;
        while(current.getData() != data) {
            if(data < current.getData())
                current = current.getLeftChild();
            else
                current = current.getRightChild();
            if(current == null)
                return null;
        }
        return current;
    }

    public void insert(int data) {
        Node current, parent;
        Node newNode = new Node();
        newNode.setData(data);
        if(root==null)
            root = newNode; 
        else{
            current = root;
            while(true) {
                parent = current; 
                if(data < current.getData()){
                    current = current.getLeftChild();           
                    if(current == null) {
                        parent.setLeftChild(newNode);
                        return;
                    }
                }
                else{
                    current = current.getRightChild();
                    if(current == null) { 
                        parent.setRightChild(newNode);
                        return;
                    }
                }       
            }
        }
    }

    public void traverse(int type) {
        switch(type) {
        case 1:
            System.out.print("\nPreorder traversal: "); 
            preOrder(root);
            break;
        case 2:
            System.out.print("\nInorder traversal: "); 
            inOrder(root);
            break;
        case 3:
            System.out.print("\nPostorder traversal: "); 
            postOrder(root);
            break;
        } 
        System.out.println(); 
    }

    private void preOrder(Node auxRoot) {
        System.out.println(auxRoot.getData()); 
        if (auxRoot.getLeftChild() != null)     postOrder(auxRoot.getLeftChild());
        if (auxRoot.getRightChild() != null)    postOrder(auxRoot.getRightChild());
    }

    private void inOrder(Node auxRoot) {
        if (auxRoot.getLeftChild() != null)     postOrder(auxRoot.getLeftChild());
        System.out.println(auxRoot.getData()); 
        if (auxRoot.getRightChild() != null)    postOrder(auxRoot.getRightChild());
    }

    private void postOrder(Node auxRoot) {
        if (auxRoot.getLeftChild() != null)     postOrder(auxRoot.getLeftChild());
        if (auxRoot.getRightChild() != null)    postOrder(auxRoot.getRightChild());
        System.out.println(auxRoot.getData());
    }

    public void displayTree(){
        displayNodes(root);
    }
    private void displayNodes(Node node){
        if(node.getLeftChild() == null && node.getRightChild() == null)
            System.out.println("("+node+")");
        else {
            System.out.println( node.getLeftChild() +" <= "+ node +" => "+ node.getRightChild() );
            if(node.getLeftChild() != null )    displayNodes(node.getLeftChild());
            if(node.getRightChild() != null )   displayNodes(node.getRightChild());
        }
    }

    public static void main(String[] args) {
        Tree a = new Tree();
        a.insert(10);
        a.insert(15);
        a.insert(12);
        a.insert(5);
        a.insert(7);
        a.displayTree();
        a.traverse(1);
        a.traverse(2);
        a.traverse(3);
        
    }

}