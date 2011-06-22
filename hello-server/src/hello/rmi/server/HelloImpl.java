package hello.rmi.server;

import hello.rmi.common.HelloInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.jmock.Expectations;
import org.jmock.Mockery;

public class HelloImpl implements HelloInterface {
    
    public String sayHello() {
        return "[SERVER] HelloWorld!";
    }
    
    public static void main(String[] args) {
    	try {
    		
    		Runtime.getRuntime().exec("rmiregistry");
    		//Runtime.getRuntime().exec("rmid -J-Djava.security.policy=rmid.policy");
    		
    		//HelloInterface server = new HelloImpl();
    		
    		Mockery context = new Mockery();
            final HelloInterface server = context.mock(HelloInterface.class);
            
            
            context.checking(new Expectations() {{
            	one(server).sayHello();
            	will(returnValue("MOCK HelloWORLDDDD MOCKKKKKK"));
            }});
    		
    		
    		HelloInterface stub = (HelloInterface)UnicastRemoteObject.exportObject(server, 0);
    		    		
    		Registry r = LocateRegistry.getRegistry();
    		//r.unbind("HelloServer");
    		r.rebind("HelloServer", stub);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
}