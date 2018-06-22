package moproDCC.moproDCC;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class mainClass {		
		public static void main(String[] args) {
			TestListenerAdapter tla = new TestListenerAdapter();
			TestNG testng = new TestNG();
			testng.setTestClasses(new Class[] { createprojectTest.class });
			testng.addListener(tla);
			testng.run();	        
    } 
}
