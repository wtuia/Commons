package rmi.registry.client;

import rmi.registry.service.HelloRegistryFacade;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryClient {
    public static void main(String[] args) {
        try {
            // 从端口获取
            Registry registry = LocateRegistry.getRegistry(1099);
            // 通过注册名字加载
            HelloRegistryFacade hello = (HelloRegistryFacade) registry.lookup("HelloRegistry");
            String result = hello.hello2Name("client");
            System.out.println(result);
        }catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
