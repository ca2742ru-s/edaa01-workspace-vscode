package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class TestBinarySearchTree {
	private BinarySearchTree<Integer> intTree;

	@BeforeEach
	void setUp() {
		intTree = new BinarySearchTree<Integer>();
	}

	@AfterEach
	void tearDown() {
		intTree = null;
	}

	/**
	 * Test empty tree.
	 */
	@Test
	void testEmptyTree() {
		assertEquals(0, intTree.size(), "Wrong size of empty tree");
		assertEquals(0, intTree.height(), "Wrong height of empty tree");
		intTree.clear();
		assertEquals(0, intTree.size(), "Wrong size of empty tree after clear");
	}

	/** Test a single add. */
	@Test
	void testAddOneElement() {
		assertTrue(intTree.add(500));
		assertEquals(1, intTree.size(), "Wrong size of empty tree");
		assertEquals(1, intTree.height(), "Wrong height of empty tree");
		intTree.clear();
		assertEquals(0, intTree.size(), "Wrong size after clear");
	}

	/**
	 * Test add.
	 */
	@Test
	void testAdd() {
		assertTrue(intTree.add(500));
		assertTrue(intTree.add(200));
		assertTrue(intTree.add(300));
		assertTrue(intTree.add(400));
		assertTrue(intTree.add(100));
		assertTrue(intTree.add(700));
		assertTrue(intTree.add(600));
		assertTrue(intTree.add(800));
		assertTrue(intTree.add(1000));
		assertTrue(intTree.add(900));
		assertEquals(10, intTree.size(), "Wrong size after add");
		assertEquals(5, intTree.height(), "Wrong height after add");
		intTree.clear();
		assertEquals(0, intTree.size(), "Wrong size after clear");
	}

	/**
	 * Test duplicates.
	 */
	@Test
	void testDuplicates() {
		assertTrue(intTree.add(500));
		assertTrue(intTree.add(200));
		assertTrue(intTree.add(300));
		assertTrue(intTree.add(400));
		assertTrue(intTree.add(100));
		assertTrue(intTree.add(700));
		assertTrue(intTree.add(600));
		assertTrue(intTree.add(800));
		assertTrue(intTree.add(1000));
		assertTrue(intTree.add(900));
		for (int i = 100; i <= 1000; i = i + 100) {
			assertFalse(intTree.add(i));
		}
		assertEquals(10, intTree.size(), "Wrong size after add of duplicate");
		assertEquals(5, intTree.height(), "Wrong height of add of duplicate");
		assertTrue(intTree.add(950));
		assertEquals(11, intTree.size(), "Wrong size after add");
		assertEquals(6, intTree.height(), "Wrong height of tree after add");
	}

	/**
	 * Test tree with comparator.
	 */
	@Test
	void testComparator() {
		BinarySearchTree<String> bst = new BinarySearchTree<>((s1, s2) -> s1.length() -s2.length());
		assertTrue(bst.add("BB"));	
		assertTrue(bst.add("CCC"));	
		assertTrue(bst.add("DDDD"));
		assertTrue(bst.add("EEEEE"));
		assertTrue(bst.add("A"));
		assertEquals(5, bst.size(), "Wrong size after add");
		assertEquals(4, bst.height(), "Wrong height after add");	
		assertFalse(bst.add("1"));
		assertFalse(bst.add("22"));
		assertFalse(bst.add("333"));
		assertFalse(bst.add("4444"));
		assertFalse(bst.add("55555"));
		assertTrue(bst.add("666666"));
		assertEquals(6, bst.size(), "Wrong size after add of duplicate");
		assertEquals(5, bst.height(), "Wrong height of add of duplicate");
	}
}
