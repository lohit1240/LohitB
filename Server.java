/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuplespace;

/**
 *
 * @author lohit
 */
import java.util.*;
import java.net.*;
import java.io.*;
import static java.lang.System.exit;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String args[]) throws IOException {
        ServerSocket scclient = null;
        Socket s = null;
        DataOutputStream DOSclient = null;
        DataInputStream DISclient = null;
        String IP = "127.0.0.1";

        System.out.println("Server initiated...!");
        //
        try {
            scclient = new ServerSocket(6001);
            while (true) {
                // Connecting to client
                s = scclient.accept();
                DOSclient = new DataOutputStream(s.getOutputStream());
                DISclient = new DataInputStream((s.getInputStream()));

                int service = DISclient.read();
                //DISserver.read();
                //String a="9001";

                ServerThread st = new ServerThread(s, DOSclient, DISclient, service);
                Thread t = new Thread(st);
                t.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread {

    final DataInputStream dataIn;
    final DataOutputStream dataOut;
    final Socket sc;
    int op, i = 0;
    int id;
    String name;
    boolean inp;

    public ServerThread(Socket scserve, DataOutputStream dataOut, DataInputStream dataIn, int service) {
        this.dataIn = dataIn;
        this.sc = scserve;
        this.dataOut = dataOut;
        this.op = service;
    }

    @Override
    public void run() {
        TupleSpace[] t = new TupleSpace[200];
        while (true) {
            try {
                if (op == 1) {
                    System.out.println("Write operation requested:");
                    name = dataIn.readUTF();
                    id = dataIn.read();
                    inp = dataIn.readBoolean();

                    t[i] = new TupleSpace(name, id, inp);
                    i++;
                }
                else if (op == 2) {
                    System.out.println("Read operation requested:");
                    dataOut.write(i);
                    for (int j = 0; j < i; j++) {
                        dataOut.writeUTF(t[i].name);
                        dataOut.write(t[i].id);
                        dataOut.writeBoolean(t[i].inp);
                    }
                }
                else if (op == 3) {
                    System.out.println("Take operation requested:");
                    dataOut.writeUTF(t[i].name);
                    dataOut.write(t[i].id);
                    dataOut.writeBoolean(t[i].inp);
                    i--;
                }

            } //Exception handled 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
