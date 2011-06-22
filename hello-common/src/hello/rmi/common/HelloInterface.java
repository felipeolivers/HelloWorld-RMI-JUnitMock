package hello.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote {
	public String sayHello() throws RemoteException;
}
