import java.util.ArrayList;

public class TreeGenerico<T> {
    private NodeGenerico<T> root;
    private ArrayList <NodeGenerico<T>> nums = new ArrayList<>(); //OPERANDOS

    public TreeGenerico(){
        root = null;
    }

    public NodeGenerico<T> getRoot(){
        return root;
    }

    public void expresionAritmetica(String postfix){
        clear();                    //Clear all the stacks, nums and operadores stack
        analyzingPostfix(postfix);      //Read the infija line and add nums to nums arrayList and operadores a operadores ArrayLsit
    }

    private void clear(){ //Reiniciamos para siguiente infija
        nums.clear();
    }

    private void analyzingPostfix(String postfix){
        char[] chars = postfix.toCharArray();
        for (char ch : chars){
            if(Character.isDigit(ch))   //Adding just nums in ArrayList nums
                nums.add( 0, new NodeGenerico<>(ch+"") ); //NodeGenerico.data <=> String type
            if( ch=='^' || ch=='/' || ch=='*' || ch=='+' || ch=='-' )
                creatingTree( String.valueOf(ch) );   //Creamos sub Árbol
        }
        root = nums.get(0);     //Root is asigned to the last numeric node, which should be the root
    }

    //####################################################################################
    private void creatingTree(String opData){
        NodeGenerico<T> num1;
        NodeGenerico<T> num2;
        NodeGenerico<T> op = new NodeGenerico<>(opData);

        num2 = nums.remove (0);
        num1 = nums.remove (0);
        op.setLeftChild(num1);
        op.setRightChild(num2);
        nums.add(0, op);        //op added to the top of the stack of the numeric arraylist
    }

    //####################################################################################
    public String tresRecorridos(){
        String recorridos = "";
        recorridos += traverse(1);
        recorridos += traverse(2);
        recorridos += traverse(3);
        return recorridos;
    }

    private String traverse(int type) {
        String recorrido;
        switch(type) {
        case 1:
            recorrido = "Preorder traversal: " ;
            recorrido += preOrder(root);
            return recorrido + "\n";
        case 2:
            recorrido = "Inorder traversal: "; 
            recorrido += inOrder(root);
            return recorrido + "\n";
        case 3:
            recorrido = "Postorder traversal: "; 
            recorrido += postOrder(root);
            return recorrido + "\n";
        default:
            return "ERROR!";
        }
    }

    private String preOrder(NodeGenerico<T> auxRoot) {
        String recorrido = auxRoot.getData() + " "; 
        if (auxRoot.getLeftChild() != null)     recorrido += preOrder(auxRoot.getLeftChild());
        if (auxRoot.getRightChild() != null)    recorrido += preOrder(auxRoot.getRightChild());
        return recorrido;
    }

    private String inOrder(NodeGenerico<T> auxRoot) {
        String recorrido = "";
        if (auxRoot.getLeftChild() != null)     recorrido += inOrder(auxRoot.getLeftChild());
        recorrido += auxRoot.getData() + " "; 
        if (auxRoot.getRightChild() != null)    recorrido += inOrder(auxRoot.getRightChild());
        return recorrido;
    }

    private String postOrder(NodeGenerico<T> auxRoot) {
        String recorrido = "";
        if (auxRoot.getLeftChild() != null)     recorrido += postOrder(auxRoot.getLeftChild());
        if (auxRoot.getRightChild() != null)    recorrido += postOrder(auxRoot.getRightChild());
        recorrido += auxRoot.getData() + " "; 
        return recorrido;
    }

//####################################################################################
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
}


/*
    TreeGenerico<Integer> a1 = new TreeGenerico<>();
    TreeGenerico<String> a2 = new TreeGenerico<>();

        a1.insert(10); a1.insert(15); a1.insert(12); a1.insert(5); a1.insert(7);
        a1.insert(6);  a1.insert(8);  a1.insert(3);  a1.insert(1); a1.insert(2);
        a1.displayTree();
        a1.traverse(1);
        a1.traverse(2);
        a1.traverse(3);

        System.out.println("============================================================");
        BTreePrinter.printNode(a1.getRoot());
        System.out.println("============================================================");

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
*/
