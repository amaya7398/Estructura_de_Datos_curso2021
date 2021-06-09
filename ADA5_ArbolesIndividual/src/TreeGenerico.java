import java.lang.reflect.Array;

public class TreeGenerico<T extends Comparable<T>> {
    private NodeGenerico<T> root;

    public TreeGenerico(){
        root = null;
    }

    public NodeGenerico<T> find(T data) {
        NodeGenerico<T> current = root;
        try {
            while(current.getData() != data) {
                if(data.compareTo(current.getData()) < 0)
                    current = current.getLeftChild();
                else
                    current = current.getRightChild();
                if(current == null)
                    break;
            }
        }  catch (Exception e) {
            System.out.println("TREE EMPTY");
        }
        return current;
    }

    public NodeGenerico<T> findParent (T data){
        NodeGenerico<T> current = root;
        NodeGenerico<T> parent = null;
        try {
            while(current.getData() != data) {
                parent = current;
                if(data.compareTo(current.getData()) < 0)
                    current = current.getLeftChild();
                else
                    current = current.getRightChild();
                if(current == null){ //No existe NODO con valor "T data"
                    parent = null;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("TREE EMPTY");
        }
        return parent;
    }

    public NodeGenerico<T>[] findWithParent (T data){
        NodeGenerico<T> current = root;
        NodeGenerico<T> parent = null;
        @SuppressWarnings("unchecked")
        NodeGenerico<T>[] CuPa = (NodeGenerico<T>[]) Array.newInstance(NodeGenerico.class, 2); //Node<T>[] Cu_Pa = (Node<T>[]) new Object<T>[2]; //No se puede, no hay tortillas
        
        try {
            while(current.getData() != data) {
                parent = current;
                if(data.compareTo(current.getData()) < 0)
                    current = current.getLeftChild();
                else
                    current = current.getRightChild();
                if(current == null){    //NOT FOUND
                    parent = current;//return (Node<T>[]) Array.newInstance(Node.class, 2); //
                    break;                       //
                }
            }    
        } catch (Exception e) {
            System.out.println("TREE EMPTY");
        }
        
        CuPa[0] = parent;
        CuPa[1] = current;
        return CuPa;
    }

    public void insert(T data) {
        NodeGenerico<T> current, parent;
        NodeGenerico<T> newNode = new NodeGenerico<>();
        newNode.setData(data);
        if(root==null)
            root = newNode; 
        else{
            current = root;
            while(true) {
                parent = current; 
                if( data.compareTo(current.getData()) < 0 ){
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

    public void delete(T data){ //PARENTandSON[0] = PARENT      PARENTandSON[1] = SON
        NodeGenerico<T>[] parentAndSon = (NodeGenerico<T>[]) Array.newInstance(NodeGenerico.class, 2);  //[0]: PARENT [1]: SON
        parentAndSon = findWithParent(data);
        
        boolean twoChilds = false;
        if (parentAndSon[1].getLeftChild() != null && parentAndSon[1].getRightChild() != null)
            twoChilds = true;
        
        if(parentAndSon[1] instanceof NodeGenerico<?>){ //IF instaceOf NODE it was found
            if(twoChilds){  //DOS HIJOS
                NodeGenerico<T> mostRight; //parentAndSon[1] == SON. The 
                mostRight = subTreeLeft(parentAndSon[1]); //Got the mostRight NODE from subTreeLEFT()
                System.out.println("ELIMINADO CON DOS HIJOS \nSUBARBOL IZQUIERDO, más a la derecha");
                if(mostRight != null){    //Means subTree_LEFT has right NODE and we change its MORE RIGHT NODE
                    mostRight.setLeftChild(parentAndSon[1].getLeftChild());
                    mostRight.setRightChild(parentAndSon[1].getRightChild());
                    if(root == parentAndSon[1] )
                        root = mostRight;
                    parentAndSon[1].setLeftChild(null);
                    parentAndSon[1].setRightChild(null);
                }else{                               //Means subTree_LEFT dont have right NODE. Just points subTree_left.RIGHT() to NODE_to_DELETE. RIGHT()
                    parentAndSon[1].getLeftChild().setRightChild(parentAndSon[1].getRightChild()); //SubTree_Right() points to  = Node_To_Delete.Right()
                    if(parentAndSon[0].getLeftChild() == parentAndSon[1])
                        parentAndSon[0].setLeftChild(parentAndSon[1].getLeftChild());
                    else
                        parentAndSon[0].setRightChild(parentAndSon[1].getLeftChild());
                    parentAndSon[1].setLeftChild(null);
                    parentAndSon[1].setRightChild(null);
                }
            } else {        //HOJA o 1 HIJO
                System.out.println("HOJA o 1 HIJO");
                deleteLeaftOrSon(parentAndSon);
            }
        } else {
            System.out.println("NODE "+data+" NOT FOUND");
        }
    }

    private void deleteLeaftOrSon(NodeGenerico<T>[] parentAndSon){
        boolean leaft = false;
        if(parentAndSon[1].getLeftChild() == null && parentAndSon[1].getRightChild() == null) // ITS LEAFT
            leaft = true;
        
        if( parentAndSon[0].getLeftChild() == parentAndSon[1]){ //Parent LEFT
            if(leaft)                                               //LEAFT
                parentAndSon[0].setLeftChild(null);                 
            if(!leaft && parentAndSon[1].getLeftChild() == null)    // NOT LEAFT and SON_left() == null
                parentAndSon[0].setLeftChild(parentAndSon[1].getRightChild());
            if(!leaft && parentAndSon[1].getRightChild() == null)   // NOT LEAFT and SON_right() == null
                parentAndSon[0].setLeftChild(parentAndSon[1].getLeftChild());
        } else {                                                //Parent RIGHT
            if(leaft)                                               //LEAFT
                parentAndSon[0].setRightChild(null);
            if(!leaft && parentAndSon[1].getLeftChild() == null)    // NOT LEAFT and SON_left() == null
                parentAndSon[0].setRightChild(parentAndSon[1].getRightChild());
            if(!leaft && parentAndSon[1].getRightChild() == null)   // NOT LEAFT and SON_right() == null
                parentAndSon[0].setRightChild(parentAndSon[1].getLeftChild());
        }
    }

    private NodeGenerico<T> subTreeLeft(NodeGenerico<T> toDelete){
        NodeGenerico<T> parent; //If some child of subTREE have not right NODE
        boolean notLeaft = true;
        if(toDelete.getLeftChild().getRightChild() == null) //SubTREE dont have right NODE, we deal it in the public function
            return null;
        
        toDelete = toDelete.getLeftChild();
        while (notLeaft){  //While not LEAFT
            if(toDelete.getLeftChild() == null && toDelete.getRightChild() == null)
                break;  //LEAFT, return CURRENT
            if(toDelete.getRightChild() != null){
                toDelete = toDelete.getRightChild();
            } else {    //NOT RIGHT CHILD, But there is a LEFT CHILD. ## ALSO IS NOT A LEAFT ##
                parent = findParent(toDelete.getData()); //Properly close the pointers
                parent.setRightChild(toDelete.getLeftChild());
                notLeaft = false;
            }
        }
        return toDelete;
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
        default:
            System.out.println("ERROR!");
        }
        System.out.println(); 
    }

    private void preOrder(NodeGenerico<T> auxRoot) {
        System.out.println(auxRoot.getData()); 
        if (auxRoot.getLeftChild() != null)     postOrder(auxRoot.getLeftChild());
        if (auxRoot.getRightChild() != null)    postOrder(auxRoot.getRightChild());
    }

    private void inOrder(NodeGenerico<T> auxRoot) {
        if (auxRoot.getLeftChild() != null)     postOrder(auxRoot.getLeftChild());
        System.out.println(auxRoot.getData()); 
        if (auxRoot.getRightChild() != null)    postOrder(auxRoot.getRightChild());
    }

    private void postOrder(NodeGenerico<T> auxRoot) {
        if (auxRoot.getLeftChild() != null)     postOrder(auxRoot.getLeftChild());
        if (auxRoot.getRightChild() != null)    postOrder(auxRoot.getRightChild());
        System.out.println(auxRoot.getData());
    }

    public void displayTree(){
        displayNodes(root);
    }
    private void displayNodes(NodeGenerico<T> node){
        if(node.getLeftChild() == null && node.getRightChild() == null)
            System.out.println("("+node+")");
        else {
            System.out.println( node.getLeftChild() +" <= "+ node +" => "+ node.getRightChild() );
            if(node.getLeftChild() != null )    displayNodes(node.getLeftChild());
            if(node.getRightChild() != null )   displayNodes(node.getRightChild());
        }
    }

    public static void main(String[] args) {

        TreeGenerico<Integer> a1 = new TreeGenerico<>();
        a1.insert(10);
        a1.insert(15);
        a1.insert(12);
        a1.insert(5);
        a1.insert(7);
        System.out.println("Finding: "+a1.find(11)+"\t"+ a1.find(5)+"\t"+ a1.find(15));
        a1.displayTree();
        a1.traverse(1);
        a1.traverse(2);
        a1.traverse(3);

    System.out.println("============================================================");
        
        TreeGenerico<String> a2 = new TreeGenerico<>();
        a2.insert("C");
        a2.insert("E");
        a2.insert("D");
        a2.insert("A");
        a2.insert("B");
        a2.insert("F");
        System.out.println("Finding: "+a2.find("G")+"\t"+ a2.find("B")+"\t"+ a2.find("E"));
        a2.displayTree();
        a2.delete("A");
        a2.delete("B");
        a2.delete("E");
        a2.displayTree();
        //System.out.println("PARENT: "+a2.findParent("D"));
        NodeGenerico<String>[] both = a2.findWithParent("F");
        System.out.println("BOTH Parent: "+both[0]+ " Son: "+both[1] );
    //a2.displayTree();
        a2.traverse(1);
        a2.traverse(2);
        a2.traverse(3);
        
    }
}
