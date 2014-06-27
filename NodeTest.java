import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void TestGetName() {
		Node Node1 = new Node("Alexandra", "female", 1995, null);
		Node Node2 = new Node("Paul", "male", 1995, null);
		Node Node3 = new Node("Laura", "female", 1995, Node1);

		assertEquals(Node1.getName(), "Alexandra");
		assertNotEquals(Node2.getName(), "peit");
		assertEquals(Node3.getName(), "Laura");
	}

	@Test
	public void TestgetGender() {

		Node Node1 = new Node("Alexandra", "female", 1995, null);
		Node Node2 = new Node("Paul", "male", 1995, null);
		Node Node3 = new Node("Laura", null, 1995, Node1);

		assertEquals(Node1.getGender(), "female");
		assertEquals(Node3.getGender(), null);
	}

	@Test
	public void TestaddChild() throws AlreadyParentException {
		Node Node1 = new Node("Alexandra", "female", 1995, null);
		Node Node2 = new Node("Paul", "male", 1995, null);
		Node Node3 = new Node("Laura", "female", 1995, Node1);

		Node1.addChild(Node3);
		Node2.addChild(null);
		Node2.addChild(Node3);

		assertEquals(Node1.getChild(1), Node3);
		assertNotEquals(Node2.getChild(1), null);

	}

	@Test
	public void TesthasChild() throws AlreadyParentException {
		Node Node1 = new Node("Alexandra", "female", 1995, null);
		Node Node2 = new Node("Paul", "male", 1995, null);
		Node Node3 = new Node("Laura", "female", 1995, Node1);

		Node1.addChild(Node3);
		Node2.addChild(null);

		assertEquals(Node1.hasChild(), true);
		assertEquals(Node2.hasChild(), false);
	}

	@Test
	public void TesthasParent() {
		Node Node1 = new Node("Alexandra", "female", 1995, null);
		Node Node2 = new Node("Paul", "male", 1995, null);
		Node Node3 = new Node("Laura", "female", 1995, Node1);

		assertEquals(Node1.hasParent(), false);
		assertEquals(Node3.hasParent(), true);
	}

}
