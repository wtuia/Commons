package rmi.registry.service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// 服务器监听
public class RegistryService {

    public static void main(String[] args) {
        try {
            // 本地主机上的远程对象注册到Registry实例,默认端口1099
            Registry registry = LocateRegistry.createRegistry(1099);
            // 创建远程对象
            HelloRegistryFacade hello = new HelloRegistryFacadeImpl();
            // 把远程对象注册到RMI注册服务器上，命名HelloRegistry
            registry.rebind("HelloRegistry", hello);

            System.out.println("Registry RMI启动成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
