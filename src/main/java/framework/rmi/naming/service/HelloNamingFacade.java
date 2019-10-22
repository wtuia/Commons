package framework.rmi.naming.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 接口继承 Remote
 */
public interface HelloNamingFacade extends Remote {

    String hello2Name(String name) throws RemoteException;
}
