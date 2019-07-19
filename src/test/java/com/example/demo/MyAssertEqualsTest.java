package java.com.example.demo;

	import org.junit.Test;
	 
	import com.example.demo.service.Employee;
	 
	import static org.junit.Assert.*;
	 
	public class MyAssertEqualsTest {
	 
	    @Test
	    public void mySimpleEqualsTest(){
	         
	        String expectedName = "Nattu";
	        assertEquals(expectedName, Employee.getEmpNameWithHighestSalary());
	    }
	     
	    @Test
	    public void myObjectEqualsTest(){
	         
	        Employee expectedEmpObj = new Employee(1, "Nattu", 15000);
	        assertEquals(expectedEmpObj, Employee.getHighestPaidEmployee());
	    }
	} 
