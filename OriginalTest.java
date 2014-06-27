import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class OriginalTest {

	@Test
	public void TestAddRoot() {
		Node x = new Node("Alexandra", "female", 1995, null);

		Original original = new Original();
		assertEquals(original.getSize(), 0);

		original.addRoot(x);

		assertEquals(original.getSize(), 1);

		assertEquals(original.getRoot(), x);
	}

	@Test
	public void TestisEmpty() {
		Node x = new Node("Laura", "female", 1995, null);
		Original original = new Original();
		assertEquals(original.isEmpty(), true);
		original.addRoot(x);
		assertEquals(original.isEmpty(), false);
	}

	@Test
	public void TestSize() throws AlreadyParentException {
		Node p = new Node("Alexandra", "female", 1995, null);
		Node x = new Node("Laura", "female", 1995, null);
		Original original = new Original();
		assertEquals(original.getSize(), 0);

		p.addChild(x);
		assertEquals(p.getChild(1), x);

		original.addRoot(p);
		assertEquals(original.getSize(), 2);
	}

	@Test
	public void TestGeneraties() {
		Node Node1 = new Node("Alexandra", "female", 1995, null);
		Node Node2 = new Node("Paul", "male", 1995, Node1);
		Node Node3 = new Node("Laura", "female", 1995, Node2);

		Original original = new Original();
		original.addRoot(Node1);

		assertEquals(original.generation(Node3), 3);
		assertEquals(original.generation(Node2), 2);
	}

	@Test
	public void TestAllMen() throws AlreadyParentException {

		Node Node1 = new Node("Alexandra", "female", 1995, null);
		Node Node2 = new Node("Paul", "male", 1995, Node1);
		Node Node3 = new Node("Laura", "female", 1995, Node2);
		Node Node4 = new Node("Arthur", "male", 1995, Node1);
		Node Node5 = new Node("Karim", "male", 1995, Node2);

		Node1.addChild(Node2);
		Node1.addChild(Node4);
		Node2.addChild(Node3);
		Node2.addChild(Node5);

		Original original = new Original();
		original.addRoot(Node1);

		assertEquals(original.allMen().size(), 3);

	}

	@Test
	public void TestGeneration() throws AlreadyParentException {
		Node paul = new Node("Paul", "male", 1990, null);
		Node roy = new Node("Roy", "male", 1989, null);
		Node alexandra = new Node("alex", "woman", 1988, null);
		Node x = new Node("x", "woman", 1988, null);
		Node z = new Node("z", "male", 1988, null);
		Node p = new Node("p", "male", 1988, null);
		Node c = new Node("c", "male", 1988, null);

		p.addChild(x);
		x.addChild(z);

		x.addChild(paul);
		x.addChild(alexandra);
		paul.addChild(c);
		paul.addChild(roy);

		Original tree = new Original();
		tree.addRoot(p);

		assertEquals(tree.generation(c), 4);
		assertNotEquals(tree.generation(p), 4);
	}

}