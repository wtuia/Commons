package rmi.registry.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 接口继承 Remote
 */
public interface HelloRegistryFacade extends Remote {

    String hello2Name(String name) throws RemoteException;
}
