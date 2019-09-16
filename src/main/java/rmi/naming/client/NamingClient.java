package rmi.naming.client;


import rmi.naming.service.HelloNamingFacade;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class NamingClient {

    public static void main(String[] args) {
        String remoteAddr = "rmi://localhost:1100/HelloNaming";
        try {
            HelloNamingFacade hello = (HelloNamingFacade) Naming.lookup(remoteAddr);
            String result = hello.hello2Name("Naming client");
            System.out.println(result);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
