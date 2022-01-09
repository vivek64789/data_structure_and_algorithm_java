package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.AdjacencyGraph;

class AdjacencyGraphTest {
	backend.AdjacencyGraph adjacencyGraphObj; 

	@BeforeEach
	void setUp() throws Exception {
		adjacencyGraphObj = new backend.AdjacencyGraph();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void fetchMatrixDataTest() throws IOException {
		int[][] actual = adjacencyGraphObj.readMatrix();
		assertNotEquals(null, actual);
	}
	
	
	@Test
	void addSourceDestinationTest() throws IOException {
		boolean actual = adjacencyGraphObj.addEdge(1, 5);
		assertEquals(true, actual);
	}
	
	

}
