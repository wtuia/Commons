package framework.rmi.naming.service;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class NamingService {

    public static void main(String[] args) {
        try {
            //本地主机上的远程对象注册到Registry实例
            LocateRegistry.createRegistry(1100);
            // 创建远程对象
            HelloNamingFacade hello = new HelloNamingFacadeImpl();
            // 绑定URL,标准格式为：rmi://host:port/name
            Naming.bind("rmi://localhost:1100/HelloNaming", hello);

            System.out.println("Naming RMI启动成功");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
