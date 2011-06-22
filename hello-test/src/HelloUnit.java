import hello.rmi.common.HelloInterface;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class HelloUnit {

	Mockery context = new Mockery();
	HelloInterface server;
	Registry r;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		 Runtime.getRuntime().exec("rmiregistry");
	}
	
	@Before
	public void before() throws RemoteException {
		server = context.mock(HelloInterface.class);
        context.checking(new Expectations() {{
        	one(server).sayHello();
        	will(returnValue("HelloWorld Server Mock"));
        }});
        
        // Generate Stub
		HelloInterface stub = (HelloInterface)UnicastRemoteObject.exportObject(server, 0);
		r = LocateRegistry.getRegistry();
		r.rebind("HelloServer", stub);
	}

	@Test
	public void testServerRMISayHello() throws NotBoundException, IOException {
		Assert.assertEquals("HelloWorld Server Mock", server.sayHello());
	}
	
	@After
	public void after() throws AccessException, RemoteException, NotBoundException {
		r.unbind("HelloServer");
	}
     
}
