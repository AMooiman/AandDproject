import java.util.ArrayList;
import java.util.Calendar;

class Node {

	private Node parent;
	private ArrayList<Node> children;
	private String gender;
	private String name;
	private int birthyear;


        //post: created a Node containing the persons name, gender, birthyear and parent
        //+ Node(newName : String, newGender  : String,  newYear : int, newParent  : Node)
	public Node(String newName, String newGender, int newYear, Node newParent) {
		name = newName;
		gender = newGender;
		birthyear = newYear;
		parent = newParent;
		children = new ArrayList<Node>();
	}
        //post: returns the name of the Node
        //+ getName()  : String
	public String getName() {
		return name;
	}
        //post: returns the gender of the Node
        //+ getGender()  : String
	public String getGender() {
		return gender;
	}
        //post: returns the year of birth of the Node
        //+ getBirthyear()  : int
	public int getBirthyear() {
		return birthyear;
	}
        //post: returns the age of the Node
        //+ getAge()  :  int
	public int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - this.getBirthyear();       //2014-birthyear is leeftijd

	}
	
		// Pre: given Node is not null
        // Post: Adds a child to the ArrayList of this
        // + addChild(child : Node)
	public void addChild(Node child) throws AlreadyParentException {
		if (child != null) {                                   //Checks if the child is not null
			children.add(child);							   //adds a child to the list of children
			if (!child.hasParent())							   //checks if the child has a parent
				child.addParent(this);						   //if so, make the this the parent of the child 
		}
	}

	// Post: returns true if the Node has at least one child
	// +HasChild(Node p) : boolean
	public boolean hasChild() {
		return children.size() != 0;
	}

	// Post: returns true if the Node has a parent
	// +HasParent() : boolean
	public boolean hasParent() {
		return (parent != null);
	}

	// Post: returns the parent of Node p
	// +getParent(Node p) : Node
	public Node getParent() {
		return parent;
	}
        //post: adds parent p to a Node, unless the Node has a parent already, then an error occurs.
        //- addParent(p : Node)
	public void addParent(Node p) throws AlreadyParentException {
		if (parent == null) {
			this.parent = p;

		} else {
			throw new AlreadyParentException("Has a parent already");
		}
	}

	// post: returns child number i of a Node, gives an error if the Node has less than i children.
	// +getChild(i  : int) : Node
	public Node getChild(int i) throws IndexOutOfBoundsException {
		if (hasChild() && children.size() >= i)      //als persoon kinderen heeft en kind i bestaat
			return children.get(i - 1);              //dan staat kind i op plaats i-1 in arraylist children.
		throw new IndexOutOfBoundsException(
				"Wrong number of children. Number of Children = "
						+ children.size());
	}
	
	
        //post: returns a string representation of a Node as:
        //<name>
        //Gender = ""
        //Age = ""
        //Birthyear = ""
        //Number of children = "" .
        //+ toString()  : String
	public String toString() {
		String x = "<" + name + ">\n" + "Gender = " + gender + "\n" + "Age = "
				+ getAge() + "\n" + "Birthyear = " + birthyear + "\n"
				+ "Number of children = " + children.size() + "\n";

		return x;
	}
	
	
        //post: returns the number of children of a Node
        //+ numberOfChildren()  : int
	public int numberOfChildren() {
		return children.size();
	}

}
