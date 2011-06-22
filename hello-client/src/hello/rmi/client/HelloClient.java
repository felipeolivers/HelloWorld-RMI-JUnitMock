package hello.rmi.client;

import hello.rmi.common.HelloInterface;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class HelloClient {
    
	static HelloInterface obj;
	
	public static void main(String args[ ]) {
    //	Cria e instala o security manager
    	System.setSecurityManager (new RMISecurityManager());
        try {
            obj = (HelloInterface)Naming.lookup("rmi://localhost/HelloServer");
            System.out.println("[CLIENT] " + obj.sayHello());
        } catch(Exception e) {
            System.out.println("HelloClient erro " + e.getMessage());
        }
        System.exit(0);
    }

	public String sayHello() throws RemoteException {
		return "[CLIENT] " + obj.sayHello();
	}
}