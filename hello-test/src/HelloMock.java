import hello.rmi.common.HelloInterface;

import java.rmi.RemoteException;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;



public class HelloMock {
	
	Mockery context = new Mockery();
	HelloInterface obj;
	
	@Before
	public void before() throws RemoteException {
		obj = context.mock(HelloInterface.class);
        context.checking(new Expectations() {{
        	allowing(obj).sayHello();
        	will(returnValue("HelloWorld Server Mock"));
        }});
	}
	
    @Test
	public void testMockInterface() throws RemoteException {
        Assert.assertEquals("HelloWorld Server Mock", obj.sayHello());
	}
     
}
