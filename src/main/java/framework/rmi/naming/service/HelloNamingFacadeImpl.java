package framework.rmi.naming.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 实现类继承 UnicastRemoteObject
 */
public class HelloNamingFacadeImpl extends UnicastRemoteObject implements HelloNamingFacade {

    protected HelloNamingFacadeImpl() throws RemoteException {
        super();
    }

    @Override
    public String hello2Name(String name) throws RemoteException {
        return "[Registry]:" + name;
    }
}
