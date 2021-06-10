import java.util.ArrayList;

public class TreeGenerico<T> {
    private NodeGenerico<T> root;
    private ArrayList <NodeGenerico<T>> operandos = new ArrayList<>();
    private ArrayList <NodeGenerico<T>> operadores = new ArrayList<>();

    public TreeGenerico(){
        root = null;
    }

    public NodeGenerico<T> getRoot(){
        return root;
    }
    private void clear(){ //Reiniciamos para siguiente infija
        operandos.clear();
        operadores.clear();
    }

    public void expresionesAritmeticas(String infija){
        clear();
        System.out.println("Añadiendo caracteres de cadena infija a las pilas");
        System.out.println(infija);
        System.out.println("Creando árbol desde las pilas");
    }

    public String tresRecorridos(){
        String recorridos;
        recorridos = traverse(1);
        //recorridos = traverse(2);
        //recorridos = traverse(3);
        return recorridos;
    }

    public String traverse(int type) {
        String recorrido;
        switch(type) {
        case 1:
            recorrido = "Preorder traversal: " ;
            recorrido += preOrder(root);
            return recorrido + "\n";
        /*case 2:
            System.out.print("\nInorder traversal: "); 
            inOrder(root);
            break;
        case 3:
            System.out.print("\nPostorder traversal: "); 
            postOrder(root);
            break;*/
        default:
            return "ERROR!";
        }
    }

    private String preOrder(NodeGenerico<T> auxRoot) {
        String recorrido = auxRoot.getData(); 
        recorrido += " ";
        if (auxRoot.getLeftChild() != null)     recorrido += preOrder(auxRoot.getLeftChild());
        if (auxRoot.getRightChild() != null)    recorrido += preOrder(auxRoot.getRightChild());
        return recorrido;
    }

    private void inOrder(NodeGenerico<T> auxRoot) {
        if (auxRoot.getLeftChild() != null)     inOrder(auxRoot.getLeftChild());
        System.out.println(auxRoot.getData()); 
        if (auxRoot.getRightChild() != null)    inOrder(auxRoot.getRightChild());
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
