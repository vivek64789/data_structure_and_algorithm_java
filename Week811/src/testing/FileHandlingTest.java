package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileHandlingTest {
	
	backend.FileHandling fileHandlingObj;
	backend.Variables variablesObj;

	@BeforeEach
	void setUp() throws Exception {
		fileHandlingObj = new backend.FileHandling();
		variablesObj = new backend.Variables();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void fetchAllDevicesTest() {
		ArrayList<String> actual = fileHandlingObj.fetchAllDevices(variablesObj.deviceFileName);
		assertNotEquals(null, actual);
	}
	
	
	@Test
	void readMatrixDataTest() throws IOException {
		int[][] actual = fileHandlingObj.readMatrix();
		assertNotEquals(null, actual);
	}
		
	
	@Test
	void updateMatrixData() throws IOException {
		String[] device = new String[]{"6","Six","123","Router","1244","Okay","6"};
		boolean actual = fileHandlingObj.UpdateFile(variablesObj.deviceFileName,device);
		assertEquals(true, actual);
	}
	
	

}
