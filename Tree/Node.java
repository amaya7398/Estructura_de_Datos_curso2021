public class Node <T extends Comparable<T>> implements Comparable <Node <T>>{
	private T data;
	private Node<T> leftChild;
	private Node<T> rightChild;
	private int level; //add level
	private int height; //add height

	//add constructor
	public Node(T data){
		this(data, null, null);
	}

	//add constructor
	public Node(T data, Node<T> left, Node<T> right){
		super();
		this.data = data;
		this.setLeftChild(left);
		this.setRightChild(right);
		if(left == null && right==null)
			setHeight(0);
		else if(left == null)
			setHeight(right.getHeight() + 1);
		else if(right==null)
			setHeight(left.getHeight() + 1);
		else
			setHeight( Math.max( left.getHeight()+1, right.getHeight() ) );	 	
	}

	public void setLevel(int level){
		this.level = level;
	}

	public int getLevel(){
		return this.level;
	}

	public void displayNode() {
		System.out.print("("); 
		System.out.print(data); 
		System.out.print(")");
	}

	public T getData(){
		return data;
	}
	   
	public void setData(T data){
		this.data = data;
	}

	public Node<T> getLeftChild(){
		 return leftChild;
	}

	public Node<T> getRightChild(){
		return rightChild;
   	}
	
	public void setLeftChild(Node<T> leftChild){
		this.leftChild = leftChild; 
	}

	public void setRightChild(Node<T> rightChild){
		this.rightChild = rightChild; 
	}

	public int getHeight(){
		return height;
	}

	public void setHeight(int height){
		this.height = height;
	}

	@Override
	public int compareTo(Node<T> o){
		return this.data.compareTo(o.data);
	}

	@Override
	public String toString(){
		return "Level " + level + ": " + data;
	}

}
