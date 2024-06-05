
//--------------------------------------------------------------------------


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import alturas.*;


//--------------------------------------------------------------------------

public class TestRunnerPr53 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestPais {
		private Pais p1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Pais JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Pais JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
			p1 = new Pais("Turkey", "Euro/Asia", 1.74);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryCtorTest1() {
			assertAll("countryCtorTest1",
					() -> assertEquals("Turkey", p1.getNombre(),
							"\n> Error: p1.getNombre():"),
					() -> assertEquals("Euro/Asia", p1.getContinente(),
							"\n> Error: p1.getContinentee():"),
					() -> assertEquals(1.74, p1.getAltura(), 0.00001,
							"\n> Error: p1.getAltura():"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryCtorTest2() {
			assertTrue(((Object)p1 instanceof Comparable<?>), "\n> Error: Pais implements Comparable<?>:");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryEqualsTest1() throws Exception {
			precond("Turkey", p1.getNombre());
			precond("Euro/Asia", p1.getContinente());
			precond(1.74, p1.getAltura(), 0.00001);
			//----------------------
			Pais p2 = new Pais("Turkey", "Euro/Asia", 1.74);
			Pais p3 = new Pais("Turkey", "Asia", 1.74);
			Pais p4 = new Pais("Turkey", "Euro/Asia", 2.00);
			Pais p5 = new Pais("TURKEY", "Euro/Asia", 1.74);
			Pais p6 = new Pais("Iceland", "Europe", 1.81);
			//------------------------
			assertAll("countryEqualsTest1",
					() -> assertTrue(p1.equals(p2), "\n> Error: p1.equals(p2): "),
					() -> assertTrue(p1.equals(p3), "\n> Error: p1.equals(p3): "),
					() -> assertTrue(p1.equals(p4), "\n> Error: p1.equals(p4): "),
					() -> assertFalse(p1.equals(p5), "\n> Error: p1.equals(p5): "),
					() -> assertFalse(p1.equals(p6), "\n> Error: p1.equals(p6): "),
					//------------------------
					() -> assertFalse(p1.equals(null), "\n> Error: p1.equals(null): "),
					() -> assertFalse(p1.equals("Esto es un String"), "\n> Error: p1.equals(\"Esto es un String\"): "));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryHashCodeTest1() throws Exception {
			precond("Turkey", p1.getNombre());
			precond("Euro/Asia", p1.getContinente());
			precond(1.74, p1.getAltura(), 0.00001);
			//----------------------
			int p1HashCode = p1.hashCode();
			//------------------------
			Pais p2 = new Pais("Turkey", "Euro/Asia", 1.74);
			Pais p3 = new Pais("Turkey", "Asia", 1.74);
			Pais p4 = new Pais("Turkey", "Euro/Asia", 2.00);
			Pais p5 = new Pais("TURKEY", "Euro/Asia", 1.74);
			Pais p6 = new Pais("Iceland", "Europe", 1.81);
			//------------------------
			assertAll("countryHashCodeTest1",
					() -> assertEquals(p1HashCode, p2.hashCode(), "\n> Error: p2.hashCode(): "),
					() -> assertEquals(p1HashCode, p3.hashCode(), "\n> Error: p3.hashCode(): "),
					() -> assertEquals(p1HashCode, p4.hashCode(), "\n> Error: p4.hashCode(): "),
					//------------------------
					() -> assertNotEquals(p1HashCode, p5.hashCode(), "\n> Error: p5.hashCode():" ),
					() -> assertNotEquals(p1HashCode, p6.hashCode(), "\n> Error: p5.hashCode(): "));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryCompareToTest1() throws Exception {
			precond("Turkey", p1.getNombre());
			precond("Euro/Asia", p1.getContinente());
			precond(1.74, p1.getAltura(), 0.00001);
			//----------------------
			Pais p2 = new Pais("Turkey", "Euro/Asia", 1.74);
			Pais p3 = new Pais("Turkey", "Asia", 1.74);
			Pais p4 = new Pais("Turkey", "Euro/Asia", 2.00);
			Pais p5 = new Pais("TURKEY", "Euro/Asia", 1.74);
			Pais p6 = new Pais("Iceland", "Europe", 1.81);
			//------------------------
			assertAll("countryCompareToTest1",
					() -> assertEquals(0, p1.compareTo(p2), "\n> Error: p1.compareTo(p2): "),
					() -> assertEquals(0, p1.compareTo(p3), "\n> Error: p1.compareTo(p3): "),
					() -> assertEquals(0, p1.compareTo(p4), "\n> Error: p1.compareTo(p4): "),
					//------------------------
					() -> assertTrue(p1.compareTo(p5) > 0, "\n> Error: p1.compareTo(p5): "),
					() -> assertTrue(p5.compareTo(p1) < 0, "\n> Error: p5.compareTo(p1): "),
					//------------------------
					() -> assertTrue(p1.compareTo(p6) > 0, "\n> Error: p1.compareTo(p6): "),
					() -> assertTrue(p6.compareTo(p1) < 0, "\n> Error: p6.compareTo(p1): "));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	private static final String[] inputData = {
			"Albania,Europe,1.74",
			"Algeria,Africa,1.722",
			"Argentina,South America,1.745",
			"Australia,Oceania,1.756",
			"Austria,Europe,1.792",
			"Azerbaijan,Asia,1.718",
			"Bahrain,Asia,1.651",
			"Error,Error,Error",
			"Belgium,Europe,1.786",
			"Bolivia,South America,1.6",
			"Bosnia & Herzegovina,Europe,1.839",
			"Brazil,South America,1.731",
			"Error;Error;Error",
			"Bulgaria,Europe,1.752",
			"Cambodia,Asia,1.625",
			"Cameroon,Africa,1.706",
			"Canada,North America,1.751",
			"Chile,South America,1.71",
			"China,Asia,1.67",
			"Colombia,South America,1.706",
			"Croatia,Europe,1.805",
			"Cuba,North America,1.68",
			"Czech Republic,Europe,1.803",
	};
	private static final String inputList = normalize("[ Pais ( Albania , Europe , 1.74 ) , Pais ( Algeria , Africa , 1.722 ) , Pais ( Argentina , South America , 1.745 ) , Pais ( Australia , Oceania , 1.756 ) , Pais ( Austria , Europe , 1.792 ) , Pais ( Azerbaijan , Asia , 1.718 ) , Pais ( Bahrain , Asia , 1.651 ) , Pais ( Belgium , Europe , 1.786 ) , Pais ( Bolivia , South America , 1.6 ) , Pais ( Bosnia & Herzegovina , Europe , 1.839 ) , Pais ( Brazil , South America , 1.731 ) , Pais ( Bulgaria , Europe , 1.752 ) , Pais ( Cambodia , Asia , 1.625 ) , Pais ( Cameroon , Africa , 1.706 ) , Pais ( Canada , North America , 1.751 ) , Pais ( Chile , South America , 1.71 ) , Pais ( China , Asia , 1.67 ) , Pais ( Colombia , South America , 1.706 ) , Pais ( Croatia , Europe , 1.805 ) , Pais ( Cuba , North America , 1.68 ) , Pais ( Czech Republic , Europe , 1.803 ) ]");
	public static Mundo createMundo() throws Exception {
		Mundo mnd1 = new Mundo();
		try {
			createFile("hghts.txt", inputData);
			mnd1.cargar("hghts.txt");
		} finally {
			deleteFile("hghts.txt");
		}
		return mnd1;
	}
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestMundo {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Mundo JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Mundo JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCtorTest1() throws Exception {
			Mundo mnd1 = createMundo();
			assertEquals(normalize(inputList),
					normalize(mnd1.getPaises().toString()),
					"\n> Error: mnd1.getPaises():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCtorTest2() throws Exception {
			Mundo mnd1 = new Mundo();
			assertEquals(normalize("[ ]"),
					normalize(mnd1.getPaises().toString()),
					"\n> Error: mnd1.getPaises():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldPresentInPWTest1() {
			java.util.TreeMap<String,String> map = new java.util.TreeMap<>();
			map.put("joe", "mary");
			map.put("dolly", "juan");
			map.put("jack", "eve");
			String salida = "";
			java.io.StringWriter strwrtr = new java.io.StringWriter();
			try (java.io.PrintWriter pw = new java.io.PrintWriter(strwrtr)) {
				Mundo.presentaEnPW(pw, map);
			} finally {
				salida = strwrtr.toString();
			}
			assertEquals(normalize("dolly juan jack eve joe mary"),
					normalize(salida),
					"\n> Error: Mundo.presentInPW(map):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldPresentConsoleTest1() {
			java.util.TreeMap<String,String> map = new java.util.TreeMap<>();
			map.put("joe", "mary");
			map.put("dolly", "juan");
			map.put("jack", "eve");
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				Mundo.presentaEnConsola(map);
			} finally {
				output = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("dolly juan jack eve joe mary"),
					normalize(output), "\n> Error: Mundo.presentOnConsole(map):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldNumberOfCountriesPerContinentTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = 2 , Asia = 4 , Europe = 7 , North America = 2 , Oceania = 1 , South America = 5 }"),
					normalize(mnd1.numeroDePaisesPorContinente().toString()),
					"\n> Error: mnd1.numberOfCountriesPerContinent():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesPerHeightTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("{ 1.6 = [ Pais ( Bahrain , Asia , 1.651 ) , Pais ( Bolivia , South America , 1.6 ) , Pais ( Cambodia , Asia , 1.625 ) , Pais ( China , Asia , 1.67 ) , Pais ( Cuba , North America , 1.68 ) ] , 1.7 = [ Pais ( Albania , Europe , 1.74 ) , Pais ( Algeria , Africa , 1.722 ) , Pais ( Argentina , South America , 1.745 ) , Pais ( Australia , Oceania , 1.756 ) , Pais ( Austria , Europe , 1.792 ) , Pais ( Azerbaijan , Asia , 1.718 ) , Pais ( Belgium , Europe , 1.786 ) , Pais ( Brazil , South America , 1.731 ) , Pais ( Bulgaria , Europe , 1.752 ) , Pais ( Cameroon , Africa , 1.706 ) , Pais ( Canada , North America , 1.751 ) , Pais ( Chile , South America , 1.71 ) , Pais ( Colombia , South America , 1.706 ) ] , 1.8 = [ Pais ( Bosnia & Herzegovina , Europe , 1.839 ) , Pais ( Croatia , Europe , 1.805 ) , Pais ( Czech Republic , Europe , 1.803 ) ] }"),
					normalize(mnd1.paisesPorAltura().toString()),
					"\n> Error: mnd1.countriesPerHeight():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesPerContinentTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = [ Pais ( Algeria , Africa , 1.722 ) , Pais ( Cameroon , Africa , 1.706 ) ] , Asia = [ Pais ( Azerbaijan , Asia , 1.718 ) , Pais ( Bahrain , Asia , 1.651 ) , Pais ( Cambodia , Asia , 1.625 ) , Pais ( China , Asia , 1.67 ) ] , Europe = [ Pais ( Albania , Europe , 1.74 ) , Pais ( Austria , Europe , 1.792 ) , Pais ( Belgium , Europe , 1.786 ) , Pais ( Bosnia & Herzegovina , Europe , 1.839 ) , Pais ( Bulgaria , Europe , 1.752 ) , Pais ( Croatia , Europe , 1.805 ) , Pais ( Czech Republic , Europe , 1.803 ) ] , North America = [ Pais ( Canada , North America , 1.751 ) , Pais ( Cuba , North America , 1.68 ) ] , Oceania = [ Pais ( Australia , Oceania , 1.756 ) ] , South America = [ Pais ( Argentina , South America , 1.745 ) , Pais ( Bolivia , South America , 1.6 ) , Pais ( Brazil , South America , 1.731 ) , Pais ( Chile , South America , 1.71 ) , Pais ( Colombia , South America , 1.706 ) ] }"),
					normalize(mnd1.paisesPorContinente().toString()),
					"\n> Error: mnd1.paisesPorContinente():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesPerInitialTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("{ A = [ Pais ( Albania , Europe , 1.74 ) , Pais ( Algeria , Africa , 1.722 ) , Pais ( Argentina , South America , 1.745 ) , Pais ( Australia , Oceania , 1.756 ) , Pais ( Austria , Europe , 1.792 ) , Pais ( Azerbaijan , Asia , 1.718 ) ] , B = [ Pais ( Bahrain , Asia , 1.651 ) , Pais ( Belgium , Europe , 1.786 ) , Pais ( Bolivia , South America , 1.6 ) , Pais ( Bosnia & Herzegovina , Europe , 1.839 ) , Pais ( Brazil , South America , 1.731 ) , Pais ( Bulgaria , Europe , 1.752 ) ] , C = [ Pais ( Cambodia , Asia , 1.625 ) , Pais ( Cameroon , Africa , 1.706 ) , Pais ( Canada , North America , 1.751 ) , Pais ( Chile , South America , 1.71 ) , Pais ( China , Asia , 1.67 ) , Pais ( Colombia , South America , 1.706 ) , Pais ( Croatia , Europe , 1.805 ) , Pais ( Cuba , North America , 1.68 ) , Pais ( Czech Republic , Europe , 1.803 ) ] }"),
					normalize(mnd1.paisesPorInicial().toString()),
					"\n> Error: mnd1.paisesPorInicial():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldAveragePerContinentTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = 1.714 , Asia = 1.666 , Europe = 1.788142857142857 , North America = 1.7155 , Oceania = 1.756 , South America = 1.6984000000000001 }"),
					normalize(mnd1.mediaPorContinente().toString()),
					"\n> Error: mnd1.mediaPorContinente():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldContinentsWithMoreCountriesTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("[ Europe ]"),
					normalize(mnd1.continentesConMasPaises().toString()),
					"\n> Error: mnd1.continentesConMasPaises():");
		}
		//------------------------------------------------------------------
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesByHeightTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("[ Pais ( Bolivia , South America , 1.6 ) , Pais ( Cambodia , Asia , 1.625 ) , Pais ( Bahrain , Asia , 1.651 ) , Pais ( China , Asia , 1.67 ) , Pais ( Cuba , North America , 1.68 ) , Pais ( Cameroon , Africa , 1.706 ) , Pais ( Colombia , South America , 1.706 ) , Pais ( Chile , South America , 1.71 ) , Pais ( Azerbaijan , Asia , 1.718 ) , Pais ( Algeria , Africa , 1.722 ) , Pais ( Brazil , South America , 1.731 ) , Pais ( Albania , Europe , 1.74 ) , Pais ( Argentina , South America , 1.745 ) , Pais ( Canada , North America , 1.751 ) , Pais ( Bulgaria , Europe , 1.752 ) , Pais ( Australia , Oceania , 1.756 ) , Pais ( Belgium , Europe , 1.786 ) , Pais ( Austria , Europe , 1.792 ) , Pais ( Czech Republic , Europe , 1.803 ) , Pais ( Croatia , Europe , 1.805 ) , Pais ( Bosnia & Herzegovina , Europe , 1.839 ) ]"),
					normalize(mnd1.paisesOrdenadosPorAltura().toString()),
					"\n> Error: mnd1.paisesPorAltura():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesByHeightPerContinentTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = [ Pais ( Cameroon , Africa , 1.706 ) , Pais ( Algeria , Africa , 1.722 ) ] , Asia = [ Pais ( Cambodia , Asia , 1.625 ) , Pais ( Bahrain , Asia , 1.651 ) , Pais ( China , Asia , 1.67 ) , Pais ( Azerbaijan , Asia , 1.718 ) ] , Europe = [ Pais ( Albania , Europe , 1.74 ) , Pais ( Bulgaria , Europe , 1.752 ) , Pais ( Belgium , Europe , 1.786 ) , Pais ( Austria , Europe , 1.792 ) , Pais ( Czech Republic , Europe , 1.803 ) , Pais ( Croatia , Europe , 1.805 ) , Pais ( Bosnia & Herzegovina , Europe , 1.839 ) ] , North America = [ Pais ( Cuba , North America , 1.68 ) , Pais ( Canada , North America , 1.751 ) ] , Oceania = [ Pais ( Australia , Oceania , 1.756 ) ] , South America = [ Pais ( Bolivia , South America , 1.6 ) , Pais ( Colombia , South America , 1.706 ) , Pais ( Chile , South America , 1.71 ) , Pais ( Brazil , South America , 1.731 ) , Pais ( Argentina , South America , 1.745 ) ] }"),
					normalize(mnd1.paisesPorContinenteAltura().toString()),
					"\n> Error: mnd1.paisesPorAlturaPerContinent():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesByReverseHeightPerContinentTest1() throws Exception {
			Mundo mnd1 = createMundo();
			precond(normalize(inputList), normalize(mnd1.getPaises().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = [ Pais ( Algeria , Africa , 1.722 ) , Pais ( Cameroon , Africa , 1.706 ) ] , Asia = [ Pais ( Azerbaijan , Asia , 1.718 ) , Pais ( China , Asia , 1.67 ) , Pais ( Bahrain , Asia , 1.651 ) , Pais ( Cambodia , Asia , 1.625 ) ] , Europe = [ Pais ( Bosnia & Herzegovina , Europe , 1.839 ) , Pais ( Croatia , Europe , 1.805 ) , Pais ( Czech Republic , Europe , 1.803 ) , Pais ( Austria , Europe , 1.792 ) , Pais ( Belgium , Europe , 1.786 ) , Pais ( Bulgaria , Europe , 1.752 ) , Pais ( Albania , Europe , 1.74 ) ] , North America = [ Pais ( Canada , North America , 1.751 ) , Pais ( Cuba , North America , 1.68 ) ] , Oceania = [ Pais ( Australia , Oceania , 1.756 ) ] , South America = [ Pais ( Argentina , South America , 1.745 ) , Pais ( Brazil , South America , 1.731 ) , Pais ( Chile , South America , 1.71 ) , Pais ( Colombia , South America , 1.706 ) , Pais ( Bolivia , South America , 1.6 ) ] }"),
					normalize(mnd1.paisesPorContinenteAlturaDec().toString()),
					"\n> Error: mnd1.paisesPorContinenteAlturaDec():");
		}
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({ JUnitTestPais.class ,
			JUnitTestMundo.class
	})
	public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request =
				LauncherDiscoveryRequestBuilder.request()
						.selectors(
								selectClass(JUnitTestPais.class),
								selectClass(JUnitTestMundo.class))
						.build();

		final Launcher launcher = LauncherFactory.create();
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();

		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);

		TestExecutionSummary summary = listener.getSummary();

//		summary.printTo(new PrintWriter(System.out, true));

		long abortedCount = summary.getTestsAbortedCount();
		long succeededCount = summary.getTestsFoundCount();
		long foundCount = summary.getTestsSucceededCount();
		long skippedCount = summary.getTestsSkippedCount();
		long failedCount = summary.getTestsFailedCount();
		long startedCount = summary.getTestsStartedCount();
		if (failedCount > 0) {
			System.out.println(">>> ------");
			summary.getFailures().forEach(failure -> System.out.println("failure - " + failure.getException()));
		}
		if ((abortedCount > 0)||(failedCount > 0)||(skippedCount > 0)) {
			System.out.println(">>> ------");
			if (skippedCount > 0) {
				System.out.println(">>> Error: Some tests ("+skippedCount+") were ignored");
			}
			if (abortedCount > 0) {
				System.out.println(">>> Error: ("+abortedCount+") tests could not be run due to errors in other methods");
			}
			if (failedCount > 0) {
				System.out.println(">>> Error: ("+failedCount+") tests failed due to errors in methods");
			}
		}
		if (succeededCount == foundCount) {
			System.out.print(">>> JUnit Test Succeeded");
		} else {
			System.out.print(">>> Error: JUnit Test Failed");
		}
		System.out.println(" (Tests: "+foundCount+", Errors: "+failedCount+", ErrorPrecond: "+abortedCount+", Ignored: "+skippedCount+")");

//		public static Result result = null;
//		result = JUnitCore.runClasses(JUnitTestSuite.class);
//		int rc = result.getRunCount();
//		int fc = result.getFailureCount();
//		int ac = 0;//result.getAssumptionFailureCount();
//		int ic = result.getIgnoreCount();
//		if (fc > 0) {
//			System.out.println(">>> ------");
//		}
//		for (Failure failure : result.getFailures()) {
//			System.out.println(failure.toString());
//		}
//		if ((ac > 0)||(fc > 0)) {
//			System.out.println(">>> ------");
//			if (ac > 0) {
//				System.out.println(">>> Error: "+ac+" tests could not be run due to errors in other methods");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: "+fc+" tests failed due to errors in methods");
//			}
//		}
//		if (result.wasSuccessful()) {
//			System.out.print(">>> JUnit Test Succeeded");
//		} else {
//			System.out.print(">>> Error: JUnit Test Failed");
//		}
//		System.out.println(" ("+rc+", "+fc+", "+ac+", "+ic+")");
	}
	//----------------------------------------------------------------------
	//-- Utils -------------------------------------------------------------
	//----------------------------------------------------------------------
	private static char normalizeUnicode(char ch) {
		switch (ch) {
			case '\n':
			case '\r':
			case '\t':
			case '\f':
				ch = ' ';
				break;
			case '\u20AC':
				ch = 'E';
				break;
			case '\u00A1':
				ch = '!';
				break;
			case '\u00AA':
				ch = 'a';
				break;
			case '\u00BA':
				ch = 'o';
				break;
			case '\u00BF':
				ch = '?';
				break;
			case '\u00C0':
			case '\u00C1':
			case '\u00C2':
			case '\u00C3':
			case '\u00C4':
			case '\u00C5':
			case '\u00C6':
				ch = 'A';
				break;
			case '\u00C7':
				ch = 'C';
				break;
			case '\u00C8':
			case '\u00C9':
			case '\u00CA':
			case '\u00CB':
				ch = 'E';
				break;
			case '\u00CC':
			case '\u00CD':
			case '\u00CE':
			case '\u00CF':
				ch = 'I';
				break;
			case '\u00D0':
				ch = 'D';
				break;
			case '\u00D1':
				ch = 'N';
				break;
			case '\u00D2':
			case '\u00D3':
			case '\u00D4':
			case '\u00D5':
			case '\u00D6':
				ch = 'O';
				break;
			case '\u00D9':
			case '\u00DA':
			case '\u00DB':
			case '\u00DC':
				ch = 'U';
				break;
			case '\u00DD':
				ch = 'Y';
				break;
			case '\u00E0':
			case '\u00E1':
			case '\u00E2':
			case '\u00E3':
			case '\u00E4':
			case '\u00E5':
			case '\u00E6':
				ch = 'a';
				break;
			case '\u00E7':
				ch = 'c';
				break;
			case '\u00E8':
			case '\u00E9':
			case '\u00EA':
			case '\u00EB':
				ch = 'e';
				break;
			case '\u00EC':
			case '\u00ED':
			case '\u00EE':
			case '\u00EF':
				ch = 'i';
				break;
			case '\u00F0':
				ch = 'd';
				break;
			case '\u00F1':
				ch = 'n';
				break;
			case '\u00F2':
			case '\u00F3':
			case '\u00F4':
			case '\u00F5':
			case '\u00F6':
				ch = 'o';
				break;
			case '\u00F9':
			case '\u00FA':
			case '\u00FB':
			case '\u00FC':
				ch = 'u';
				break;
			case '\u00FD':
			case '\u00FF':
				ch = 'y';
				break;
		}
		return ch;
	}
	//------------------------------------------------------------------
	//private static java.util.regex.Pattern float_pattern = java.util.regex.Pattern.compile("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)([eE][+-]?[0-9]+)?");
	private static java.util.regex.Pattern float_pattern = java.util.regex.Pattern.compile("[+-]?(([0-9]+[.][0-9]+([eE][+-]?[0-9]+)?)|([0-9]+[eE][+-]?[0-9]+))");
	private static String normalize_real_numbers(CharSequence csq) {
		String res = "";
		try {
			StringBuilder sbres = new StringBuilder(csq.length());
			java.util.regex.Matcher matcher = float_pattern.matcher(csq);
			int idx = 0;
			while (matcher.find()) {
				int inicio = matcher.start();
				int fin = matcher.end();
				String num1 = csq.subSequence(inicio, fin).toString();
				String formato = "%.6f";
				if (num1.contains("e") || num1.contains("E")) {
					formato = "%.6e";
				}
				double num2 = Double.parseDouble(num1);
				String num3 = String.format(java.util.Locale.UK, formato, num2);
				sbres.append(csq.subSequence(idx, inicio));
				sbres.append(num3);
				idx = fin;
			}
			sbres.append(csq.subSequence(idx, csq.length()));
			res = sbres.toString();
		} catch (Throwable e) {
			res = csq.toString();
		}
		return res;
	}
	//----------------------------------------------------------------------
	private static String normalize(String s1) {
		int sz = s1 == null ? 16 : s1.length() == 0 ? 16 : 2*s1.length();
		StringBuilder sb = new StringBuilder(sz);
		sb.append(' ');
		if (s1 != null) {
			for (int i = 0; i < s1.length(); ++i) {
				char ch = normalizeUnicode(s1.charAt(i));
				char sbLastChar = sb.charAt(sb.length()-1);
				if (Character.isLetter(ch)) {
					if ( ! Character.isLetter(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isDigit(ch)) {
					if ((i >= 2)
							&& (s1.charAt(i-1) == '.')
							&& Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 ."
						sb.append('.');
					} else if ((i >= 2)
							&& ((s1.charAt(i-1) == 'e')||(s1.charAt(i-1) == 'E'))
							&& Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 e"
						sb.append('e');
					} else if ((i >= 3)
							&& (s1.charAt(i-1) == '+')
							&& ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							&& Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e +"
						sb.append('e');
					} else if ((i >= 3)
							&& (s1.charAt(i-1) == '-')
							&& ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							&& Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e -"
						sb.append("e-");
					} else if ( (sbLastChar != '-') && ! Character.isDigit(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isSpaceChar(ch)) {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
				} else {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
					sb.append(ch);
				}
			}
		}
		if (Character.isSpaceChar(sb.charAt(sb.length()-1))) {
			sb.setLength(sb.length()-1);
		}
		if ((sb.length() > 0) && Character.isSpaceChar(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return normalize_real_numbers(sb);
	}
	//------------------------------------------------------------------
	private static String normalizeListStr(String listaStr, String delims, String prefix, String suffix) {
		listaStr = listaStr.trim();
		String res = listaStr;
		try {
			if (prefix.length() > 0 && listaStr.startsWith(prefix)) {
				listaStr = listaStr.substring(prefix.length());
			}
			if (suffix.length() > 0 && listaStr.endsWith(suffix)) {
				listaStr = listaStr.substring(0, listaStr.length()-suffix.length());
			}
			listaStr = listaStr.trim();
			java.util.List<String> lista = new java.util.ArrayList<>(java.util.List.of(listaStr.split(delims)));
			lista.sort(null);
			res = lista.toString();
		} catch (Throwable e) {
			// ignorar
		}
		return res;
	}
	//----------------------------------------------------------------------
	private static final String precondMessage = "\n> Warning: the test could not be executed due to previous errors";
	//----------------------------------------------------------------------
	private static void precond(boolean expectedValue, boolean currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(char expectedValue, char currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(short expectedValue, short currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(int expectedValue, int currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(long expectedValue, long currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(float expectedValue, float currValue, float delta) {
		assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(double expectedValue, double currValue, double delta) {
		assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(Object expectedValue, Object currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			assumeTrue(expectedValue.equals(currValue), precondMessage);
		}
	}
	//------------------------------------------------------------------
	private static void precond(int[] expectedValue, int[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void precond(double[] expectedValue, double[] currValue, double delta) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i], delta);
			}
		}
	}
	private static <T> void precond(T[] expectedValue, T[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	//----------------------------------------------------------------------
	private static void precondNorm(String expectedValue, String currValue) {
		precond(normalize(expectedValue), normalize(currValue));
	}
	private static void precondNorm(String[] expectedValue, String[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precondNorm(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void assertEqualsNorm(String msg, String expectedValue, String currValue) {
		assertEquals(msg, normalize(expectedValue), normalize(currValue));
	}
	private static void assertEqualsNorm(String msg, java.util.List<String> expectedValue, java.util.List<String> currValue) {
		assertEquals(expectedValue.size(), currValue.size(), msg);
		for (int i = 0; i < expectedValue.size(); ++i) {
			assertEquals(normalize(expectedValue.get(i)), normalize(currValue.get(i)), msg);
		}
	}
	private static void assertArrayEqualsNorm(String msg, String[] expectedValue, String[] currValue) {
		assertEquals(expectedValue.length, currValue.length, msg);
		for (int i = 0; i < expectedValue.length; ++i) {
			assertEquals(msg, normalize(expectedValue[i]), normalize(currValue[i]));
		}
	}
	//----------------------------------------------------------------------
	private static void deleteFile(String filename) {
		new java.io.File(filename).delete();
	}
	private static void createFile(String filename, String inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			pw.println(inputData);
		}
	}
	private static void createFile(String filename, String[] inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			for (String linea : inputData) {
				pw.println(linea);
			}
		}
	}
	private static String cargarFile(String filename) throws Exception {
		java.util.StringJoiner sj = new java.util.StringJoiner("\n");
		try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(filename))) {
			while (sc.hasNextLine()) {
				sj.add(sc.nextLine());
			}
		}
		return sj.toString();
	}
	//----------------------------------------------------------------------
	//------------------------------------------------------------------
	private static Object getMemberObject(Object obj, Class objClass, Class memberClass, String memberName) {
		//--------------------------
		// OBJ puede ser NULL en caso de variable STATIC
		// OBJCLASS puede ser NULL si OBJ no es NULL
		// MEMBERCLASS no puede ser NULL
		// MEMBERNAME puede ser NULL, si solo hay una unica variable de ese tipo
		//--------------------------
		String memberId = (memberName == null ? "" : memberName)+":"+(memberClass == null ? "" : memberClass.getName());
		Object res = null;
		int idx = -1;
		try {
			if ((objClass == null)&&(obj != null)) {
				objClass = obj.getClass();
			}
			if ((objClass != null)&&(memberClass != null)) {
				int cnt = 0;
				int aux = -1;
				java.lang.reflect.Field[] objFields = objClass.getDeclaredFields();
				for (int i = 0; i < objFields.length; ++i) {
					if (memberClass.equals(objFields[i].getType())) {
						if ((memberName != null)&&(memberName.equalsIgnoreCase(objFields[i].getName()))) {
							idx = i;
						} else {
							aux = i;
						}
						++cnt;
					}
				}
				if ((idx < 0)&&(cnt == 1)) {
					idx = aux;	// si solo tiene una variable de ese tipo, no importa el nombre
				}
				if (idx >= 0) {
					objFields[idx].setAccessible(true);
					res = objFields[idx].get(obj);
				}
			}
		} catch (Throwable e) {
			fail("\n> Unexpected Error. getMemberObject["+memberId+"]: " + e);
		}
		if (idx < 0) {
			fail("\n> Error: no ha sido posible encontrar la variable ["+memberId+"]");
		}
		if (res == null) {
			fail("\n> Error: la variable ["+memberId+"] no se ha creado correctamente");
		}
		return res;
	}
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	private static class SysOutCapture {
		private SysOutErrCapture systemout;
		private SysOutErrCapture systemerr;
		public SysOutCapture() {
			systemout = new SysOutErrCapture(false);
			systemerr = new SysOutErrCapture(true);
		}
		public void sysOutCapture() throws RuntimeException {
			try {
				systemout.sysOutCapture();
			} finally {
				systemerr.sysOutCapture();
			}
		}
		public String sysOutRelease() throws RuntimeException {
			String s1 = "";
			String s2 = "";
			try {
				s1 = systemout.sysOutRelease();
			} finally {
				s2 = systemerr.sysOutRelease();
			}
			return s1 + " " + s2 ;
		}
		//--------------------------
		private static class SysOutErrCapture {
			//--------------------------------
			private java.io.PrintStream sysoutOld;
			private java.io.PrintStream sysoutstr;
			private java.io.ByteArrayOutputStream baos;
			private final boolean systemErr;
			private int estado;
			//--------------------------------
			public SysOutErrCapture(boolean syserr) {
				sysoutstr = null ;
				baos = null;
				sysoutOld = null ;
				estado = 0;
				systemErr = syserr;
			}
			//--------------------------------
			public void sysOutCapture() throws RuntimeException {
				if (estado != 0) {
					throw new RuntimeException("sysOutCapture:BadState");
				} else {
					estado = 1;
					try {
						if (systemErr) {
							sysoutOld = System.err;
						} else {
							sysoutOld = System.out;
						}
						baos = new java.io.ByteArrayOutputStream();
						sysoutstr = new java.io.PrintStream(baos);
						if (systemErr) {
							System.setErr(sysoutstr);
						} else {
							System.setOut(sysoutstr);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutCapture:InternalError");
					}
				}
			}
			//--------------------------------
			public String sysOutRelease() throws RuntimeException {
				String result = "";
				if (estado != 1) {
					throw new RuntimeException("sysOutRelease:BadState");
				} else {
					estado = 0;
					try {
						if (sysoutstr != null) {
							sysoutstr.flush();
						}
						if (baos != null) {
							baos.flush();
							result = new String(baos.toByteArray()); //java.nio.charset.StandardCharsets.ISO_8859_1);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutRelease:InternalError1");
					} finally {
						try {
							if (systemErr) {
								System.setErr(sysoutOld);
							} else {
								System.setOut(sysoutOld);
							}
							if (sysoutstr != null) {
								sysoutstr.close();
								sysoutstr = null;
							}
							if (baos != null) {
								baos.close();
								baos = null;
							}
						} catch (Throwable e) {
							throw new RuntimeException("sysOutRelease:InternalError2");
						}
					}
				}
				return result;
			}
			//--------------------------------
		}
	}
	//----------------------------------------------------------------------
	//--TestRunner-End---------------------------------------------------
	//----------------------------------------------------------------------
}
//--------------------------------------------------------------------------
