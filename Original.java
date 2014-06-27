import java.util.*;

class Original {

	private Node root;


	// Post: an empty tree has been created.
        // + Original()
	public Original() {
		root = null;            //lege boom aangemaakt
	}
        //post: returns root
        //+ getRoot()  : Node
	public Node getRoot() {
		return root;     //root wordt geretourneerd
	}


	// post: returns true iff tree is empty
	// +isEmpty(): boolean
	public boolean isEmpty() {
		return root == null;             //true als boom is leeg
	}

	// pre: isEmpty()
	// post: returns the number of Nodes in the tree
	// +size() : int
	public int getSize() {
		if (root == null)       //als leeg dan geen Nodes in tree
			return 0;
		return size(1, root);     //anders wordt recursief de boom doorlopen en de Nodes geteld
	}
        
	private int size(int s, Node node) {
		for (int i = 0; i < node.numberOfChildren(); i++) {    //elk kind van node wordt doorlopen en +1 gedaan per kind.
			s = size(s + 1, node.getChild(i + 1));           //recursief wordt hele boom doorlopen en voor elk kind size +1 gedaan
		}
		return s;
	}

	// pre: isEmpty()
	// post: if pre root refers to a Node containing
	// Name, gender, birthyear,
	// otherwise NonEmptyTreeException has been thrown
	// +addRoot(p : Node)
	public void addRoot(Node p) throws NonEmptyTreeException {
		if (root == null) {             //als boom leeg is
			root = p;               //p wordt root
		} else
			throw new NonEmptyTreeException("Tree is not empty"); //anders error
	}

	// post: returns the number of branches between p and the root
	// +generation(p : Node) : int
	public int generation(Node p) {
		int tel = 1;
		while (p.hasParent()) {                  //als persoon een ouder heeft, telt er een generatie bij
			tel = tel + 1;                    //en wordt bij de parent gekeken of hij een ouder heeft.
			p = p.getParent();					//dit loopje wordt herhaalt todat we in de root zijn (geen ouders).
		}
		return tel;
	}

	// post: returns the person with the largest number of children in the tree.
	//+biggestFamily()  : Node
	public Node biggestFamily() {
		return biggestFamily(root, root);                    //tree van bovenaf recursief doorlopen
	}

	private Node biggestFamily(Node node, Node max) {
        for (int i = 1; i <= node.numberOfChildren(); i++) {    			  //kinderen van node worden doorlopen
			if (node.getChild(i).numberOfChildren() > max.numberOfChildren())   //als aantal kinderen daarvan groter zijn dan de max tot dan toe
				max = biggestFamily(node.getChild(i), node.getChild(i));     //dan word het maximum veranderd en recursief de rest van de tree langsgelopen
			else
				max = biggestFamily(node.getChild(i), max);               //anders rest van tree doorlopen met dezelfde max.
		}
		return max;
	}
	
        //pre: arraylist is not empty
	// post: prints all men of the family tree and puts them in an arraylist.
	// + allMen() : ArrayList<Node>
	public ArrayList<Node> allMen() throws EmptyException {
		if (!this.isEmpty()) {                           //lijst mag niet leeg zijn
			ArrayList<Node> list = new ArrayList<Node>();    //nieuwe arraylist gemaakt
			return allMen(root, list);                         //doorloopt tree en zet mannen in list
		} else
			throw new EmptyException("");
	}

	private ArrayList<Node> allMen(Node node, ArrayList<Node> list) {
		if (node.getGender().equals("male")) {                  //als man dan wordt het in de list gezet
			list.add(node);
		}
		for (int i = 1; i <= node.numberOfChildren(); i++) {       //per kind wordt gekeken of het een man is
			allMen(node.getChild(i), list);                    //recursief worden alle kinderen en kinderen van de kinderen etc. doorlopen
		}
		return list;
	}
	
	//post: allMen has been sorted from oldest first to youngest last.
	// +sort(list : ArrayList<Node>)  :  ArrayList<Node>
	public ArrayList<Node> sort(ArrayList<Node> list){
		for(int i=0; i<list.size(); i++){                                        //Doorlopen alle Nodes in de arraylist
			while(i+1!=list.size() && list.get(i).getBirthyear()>list.get(i+1).getBirthyear() ){       //de whileloop stopt zodra de arraylist doorlopen is
				Node temp = list.get(i);                                                 //Het geboortejaar op plaats i wordt vergeleken met het geboortejaar op plaats i+1
				list.set(i,list.get(i+1));                                               //Als i > i+1 worden de twee personen gewisseld in de list en wordt de methode opnieuw doorlopen
				list.set(i+1,temp);
			}
		}
		return list;
	}

}
