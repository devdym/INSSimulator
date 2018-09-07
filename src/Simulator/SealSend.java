package Simulator;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmitriypatsiliandra
 */
class SealSend extends Thread{
    private SealCubbyHole cubbyhole;
    boolean sendFlag=true;
    public void run() {
        while(sendFlag){
            String value = null;
             try {
                ServerSocket ss = null;
                ss = new ServerSocket(4455);
                Socket s = ss.accept();
                OutputStream os = s.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                while(sendFlag){
                    //sendMsg
                    value = cubbyhole.get();
                    //System.out.print(value);
                    dos.writeUTF(value);
                }
             //   s.close();
            } catch (IOException ex) {
                Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    public SealSend(SealCubbyHole c) {
        cubbyhole = c;
    }
}
 
       
             