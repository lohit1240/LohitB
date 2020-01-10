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

public class Client {

    public static void main(String args[]) {
        Socket scserv = null;
        String IP = "127.0.0.1";
        System.out.println("Client Running...!");
        try {
            scserv = new Socket(IP, 6001); // Socket created
            DataOutputStream dataOut = new DataOutputStream(scserv.getOutputStream());
            DataInputStream dataIn = new DataInputStream(scserv.getInputStream());
            Scanner scanner = new Scanner(System.in);
            boolean buf = true, inp;
            int op, id, it;
            String name;
            while (buf) {
                System.out.println("Enter the Operation to be performed:\n1.Write\n2.Read\n3.Take\n4.Exit\n");
                op = scanner.nextInt();
                if (op == 1) {
                    System.out.println("Enter the Input order: ");
                    System.out.println("Sample input: Ravi 11661 true");
                    name = scanner.next();
                    id = scanner.nextInt();
                    inp = scanner.nextBoolean();
                    dataOut.writeChars(name);
                    dataOut.write(id);
                    dataOut.writeBoolean(inp);

                } 
                else if (op == 2) {
                    System.out.println("\n Reading the Values in the tuple space:\n");
                    it = dataIn.read();
                    for (int i = 0; i < it; i++) {
                        System.out.println(dataIn.readUTF() + "\t" + dataIn.read() + "\t" + dataIn.readBoolean());
                    }
                }
                else if (op == 3) {
                    System.out.println("\n Take the value from the Tuple Space:");
                    System.out.println("The following Tuple is removed:\n" + dataIn.readUTF() + "\t" + dataIn.read() + "\t" + dataIn.readBoolean());
                }
                else if (op == 4) {
                    exit(0);
                }
                else {
                    System.out.println("Please enter a valid input:");

                }
            }
        } //Exception handled 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
