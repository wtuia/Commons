package rmi.registry.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 实现类继承 UnicastRemoteObject
 */
public class HelloRegistryFacadeImpl extends UnicastRemoteObject implements HelloRegistryFacade{

    protected HelloRegistryFacadeImpl() throws RemoteException {
        super();
    }

    @Override
    public String hello2Name(String name) throws RemoteException {
        return "[Registry]:" + name;
    }
}
