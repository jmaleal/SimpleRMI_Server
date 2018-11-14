/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplermi_server;

import rmi_interface.ExemploInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI:Comunicação entre aplicações que permite que o cliente faça invocação
 * remota de métodos a objetos presnetes no servidor. Para isso é criada uma
 * interface (no exemplo ExemploInterface) que tem de ser comum em ambos os
 * lados e inclusivamente o package de cliente e servidor onde está essa
 * interface têm de ter o mesmo nome. O objeto retornado tem de implementar a
 * Interface Serializable!
 *
 * Links: https://pt.wikipedia.org/wiki/RMI;
 *
 * @author jorgeleal
 */
public class SimpleRMI_Server extends UnicastRemoteObject implements ExemploInterface {

    protected SimpleRMI_Server() throws RemoteException {
        super();
    }

    @Override
    public String mensagem(String name) throws RemoteException {
        System.err.println(name + " is trying to contact!");
        return "Server says hello to " + name;
    }

    public static void main(String[] args) {
        try {
            System.getProperties().put("java.security.policy", "./server.policy");
            /*if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }*/

            Registry rgsty = LocateRegistry.createRegistry(1099);
            rgsty.rebind("/RMIService", new SimpleRMI_Server());

            /*
              Naming.rebind("/PresencesRemote", new SimpleRMI_Server());*/
            //  Naming.rebind("/PresencesRemote", new SimpleRMI_Server());
            System.err.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
