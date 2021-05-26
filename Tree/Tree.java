import java.util.Queue;
import java.util.LinkedList;

public class Tree<T extends Comparable <T>> {

	private Node<T> root;

	public Tree() { 
		root = null; 
	}

	public T maximum(){
		Node<T> local = root;
		if (local == null)
			return null;
		while (local.getLeftChild() != null)
			local = local.getRightChild();
		return local.getData();
	}

	public T minimun(){
		Node<T> local = root;
		if (local == null)
			return null;
		while (local.getLeftChild() != null)
			local = local.getLeftChild();
		return local.getData();
	}

	private int height(Node<T> node){
		if (node == null)
			return 0;
		return node.getHeight();	
	}

	public Node<T> insert(T data) {
		root = insert(root, data);
		switch (balanceFactor(root)) {
		case 1:
			root = rotateLeft(root);
			break;
		case -1:
			root = rotateRight(root);
			break;
		default:
			break;
		}
		return root;
	}

	public Node<T> insert(Node<T> node, T data) {
		if (node == null)
			return new Node<T>(data);
		if (node.getData().compareTo(data) > 0) {
			node = new Node<T>(node.getData(), insert(node.getLeftChild(), data),
					node.getRightChild());
		} else if (node.getData().compareTo(data) < 0) {
			node = new Node<T>(node.getData(), node.getLeftChild(), insert(node.getRightChild(), data));
		}
		// After insert the new node, check and rebalance the current node if
		// necessary.
		switch (balanceFactor(node)) {
		case 1:
			node = rotateLeft(node);
			break;
		case -1:
			node = rotateRight(node);
			break;
		default:
			return node;
		}
		return node;
	}

	//add method 
	public int balanceFactor(Node<T> node){
		int L = height(node.getLeftChild());
		int R = height(node.getRightChild());
		if ((L - R) >= 2)
			return -1;
		else if ((L - R) <= -2)
			return 1;
		return 0;
	}

	private Node<T> rotateLeft(Node<T> node) {
		Node<T> q = node;
		Node<T> p = q.getRightChild();
		Node<T> c = q.getLeftChild();
		Node<T> a = p.getLeftChild();
		Node<T> b = p.getRightChild();
		q = new Node<T>(q.getData(), c, a);
		p = new Node<T>(p.getData(), q, b);
		return p;
	}

	private Node<T> rotateRight(Node<T> node) {
		Node<T> q = node;
		Node<T> p = q.getLeftChild();
		Node<T> c = q.getRightChild();
		Node<T> a = p.getLeftChild();
		Node<T> b = p.getRightChild();
		q = new Node<T>(q.getData(), b, c);
		p = new Node<T>(p.getData(), a, q);
		return p;
	}

	public Node getRoot(){
		return root;
	}

	void deleteNode(Node<T> n, T data){
		if(n!=null)
			if(n.getData().compareTo(data) > 0 ){
				deleteNode(n.getLeftChild(), data);
				n.setLeftChild(root);
			} else
				if(n.getData().compareTo(data) < 0){
					deleteNode(n.getRightChild(), data);
					n.setRightChild(root);
				} else{
					Node aux1, aux2, aux3;
					aux3 = n;
					if(aux3.getRightChild() == null)
						if(aux3.getLeftChild() == null)
							n = null;
						else
							n = aux3.getLeftChild();
					else
						if(aux3.getLeftChild() == null)
							n = aux3.getRightChild();
						else {
							aux1 = aux3.getLeftChild();
							aux2 = aux3;
							while(aux1.getRightChild() != null){
								aux2 = aux1;
								aux1 = aux1.getRightChild();
							}
							aux3.setData(aux1.getData());
							if(aux3 == aux2)
								aux3.setLeftChild(null);
							else
								if(aux1.getLeftChild() == null)
									aux2.setRightChild(null);
								else
									aux2.setRightChild(aux1.getLeftChild());
							aux3 = aux1;                     
						}
					aux3 = null;
				}
				root = n;			
	}

	public boolean find(T data) {
		Node<T> local = root;
		while (local != null) {
			if (local.getData().compareTo(data) == 0)
				return true;
			else if (local.getData().compareTo(data) > 0)
				local = local.getLeftChild();
			else
				local = local.getRightChild();
		}
		return false;
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

	public void printTree() {		
		root.setLevel(0);
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node<T> node = queue.poll();
			System.out.println(node);
			int level = node.getLevel();
			Node<T> left = node.getLeftChild();
			Node<T> right = node.getRightChild();
			if (left != null) {
				left.setLevel(level + 1);
				queue.add(left);
			}
			if (right != null) {
				right.setLevel(level + 1);
				queue.add(right);
			}
		}
	}
	
	public void traverseLevelOrder() {		
		if (root == null) {
			return;
		}
		root.setLevel(0);

		Queue<Node<T>> nodes = new LinkedList<Node<T>>();
		nodes.add(root);
	 
		while (!nodes.isEmpty()) {
			
			Node node = nodes.remove();
			node.displayNode();
			if (node.getLeftChild() != null) {
				nodes.add(node.getLeftChild());
			}
	 
			if (node.getRightChild()!= null) {
				nodes.add(node.getRightChild());
			}
		}
	}

	private void preOrder(Node<T> auxRoot) {
		if(auxRoot != null) {
			System.out.print(auxRoot.getData() + " "); 
			preOrder(auxRoot.getLeftChild());
			preOrder(auxRoot.getRightChild());
		}
	}

	private void inOrder(Node<T> auxRoot) {
		if(auxRoot != null) {
			inOrder(auxRoot.getLeftChild());
			System.out.print(auxRoot.getData() + " "); 
			inOrder(auxRoot.getRightChild());
		}
	}

	private void postOrder(Node<T> auxRoot) {
		if(auxRoot != null) {
			postOrder(auxRoot.getLeftChild()); 
			postOrder(auxRoot.getRightChild()); 
			System.out.print(auxRoot.getData() + " "); }
	}

}
