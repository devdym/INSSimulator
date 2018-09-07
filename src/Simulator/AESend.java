/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 *
 * @author Dym
 */
class AESend extends Thread{
    private AECubbyHole aecubbyhole;
    int port;
    String ip;
    
    @Override
    public void run(){
        try {
            DatagramSocket serverSocket = new DatagramSocket();
            byte[] sendData = new byte[202];
            while (true) {
                InetAddress IPAddress = InetAddress.getByName(ip);
                String msg = aecubbyhole.get();
                sendData = hexStringToByteArray(msg);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }         
        } catch (SocketException ex) {
            Logger.getLogger(ODIMSend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ODIMSend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ODIMSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AESend(AECubbyHole c, String ipV, int portV){
        ip = ipV;
        port = portV;
        aecubbyhole = c;
    }
    
    public static byte[] hexToBytes(String hexString) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        byte[] bytes = adapter.unmarshal(hexString);
        return bytes;
    }
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length()-1;
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
