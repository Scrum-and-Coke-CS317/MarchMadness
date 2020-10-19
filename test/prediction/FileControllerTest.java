/**
 * 
 */
package prediction;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author abreyen
 *
 */
class FileControllerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link prediction.FileController#FileController()}.
	 */
	@Test
	final void testFileController() {
		FileController fc = new FileController();
		assertTrue(fc!=null);
	}

	/**
	 * Test method for {@link prediction.FileController#FileController(java.io.File)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testFileControllerFile() throws FileNotFoundException {
		File file = new File("file/testfile.csv");
		FileController fc = new FileController(file);
		assertTrue(fc!=null);
	}

	/**
	 * Test method for {@link prediction.FileController#getAllTeams()}.
	 * @throws FileNotFoundException 
	 */
	@Test
	final void testGetAllTeams() throws FileNotFoundException {
		File file = new File("file/testfile.csv");
		FileController fc = new FileController(file);
		
		assertTrue(fc.getAllTeams().size()==5);
	}



}
