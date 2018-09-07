/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dym
 */
class AEThread extends Thread{
private AECubbyHole aecubbyhole;
    int p;
    String msg;
    Random rand = new Random();
     public static boolean done = true;
     
    @Override
    public void run(){
        while(done){
            msg = produceMsg();
            aecubbyhole.put(msg);
            try {
                Thread.sleep(p);
            } catch (InterruptedException ex) {
                Logger.getLogger(AEThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public AEThread(AECubbyHole c) {
        aecubbyhole = c;
        p = 250;
        
    }

    private String produceMsg() {
        String 
            dHeaderMessageID = "00000001",
            dHeaderNumberOfRetryes = "00040001",
            dHeaderWantReceipt,// = Integer.toHexString((winchN + leadinN + wtN)) +  Integer.toHexString((winchN)) + Integer.toHexString((leadinN)) + Integer.toHexString((wtN)),
            //dHeaderWantReceipt = "0e0c0002",
            dHeaderNumberOfByteFollowing = "000000de",
            dHeaderDestinationObject = "00000000",
            dHeaderOrigonObject = "00000000",
            dHeaderCommand = "00000000",
            dHeaderNumberOfByteUserData = "000000a8", 
            iMessageCounter = "af48";
        int rSRTensionValue,
            rSRCableLengthOut,
            rSRCableSpeed,
            rWTTensionValue,
            WTCableLengthOut,
            rWTCableSpeed;
        
        String winchN_hex, leadinN_hex, wtN_hex, strN_hex;
        
        winchN_hex = Integer.toHexString((12));
        if (winchN_hex.length()<2) {
            winchN_hex = "0" + winchN_hex;
        }
        
        leadinN_hex = Integer.toHexString((0));
        if (leadinN_hex.length()<2) {
            leadinN_hex = "0" + leadinN_hex;
        }
        
        wtN_hex = Integer.toHexString((2));
        if (wtN_hex.length()<2) {
            wtN_hex = "0" + wtN_hex;
        }
        
        strN_hex = Integer.toHexString((12 + 0 + 2));
        if (strN_hex.length()<2) {
            strN_hex = "0" + strN_hex;
        }
        
        dHeaderWantReceipt =  strN_hex + winchN_hex + leadinN_hex + wtN_hex;
                
        String cMsg = dHeaderMessageID + dHeaderNumberOfRetryes  + dHeaderWantReceipt  + dHeaderNumberOfByteFollowing
                   + dHeaderDestinationObject  + dHeaderOrigonObject  + dHeaderCommand  + dHeaderNumberOfByteUserData 
                   + iMessageCounter;
           
        for (int i = 0; i < 12; i++) {
            
            rSRTensionValue = rand.nextInt((2000 - 800) + 1) + 800;
//            if(i==5){
//                rSRTensionValue = rSRTensionValue + 400;
//            }
            rSRCableLengthOut = 8100;
            rSRCableSpeed = 0;

            cMsg = cMsg + DblToHex(rSRTensionValue) 
                    + DblToHex(rSRCableLengthOut) 
                    + DblToHex(rSRCableSpeed);
        }
        for (int i = 0; i < 2; i++) {
            rWTTensionValue = rand.nextInt((12000 - 8000) + 1) + 8000;
            WTCableLengthOut = 600;
            rWTCableSpeed = 0;
            
            cMsg = cMsg + DblToHex(rWTTensionValue) 
                    + DblToHex(WTCableLengthOut) 
                    + DblToHex(rWTCableSpeed);
        }
      //  System.out.println();
    //    System.out.println("Msg length: " + cMsg.length() + "\tmsg: " + cMsg + "\n");
        
        return cMsg + "\n";
    }

    private String DblToHex(int rSRCableLengthOut) {
        double d = rSRCableLengthOut;
        float f;
        int bits, s, e, m;

        f = (float) d;
        bits = Float.floatToIntBits(f);
        s = ((bits >> 31) == 0) ? 1 : -1;
        e = ((bits >> 23) & 0xff);
        m = (e == 0)
                ? (bits & 0x7fffff) << 1
                : (bits & 0x7fffff) | 0x800000;
        String hexStr = Integer.toHexString(bits);
        String leadingZeros = "";
      //  System.out.println(hexStr.length() + " length, value: " + hexStr);
        switch (hexStr.length()) {

            case 7:
                leadingZeros = "0";
                break;
            case 6:
                leadingZeros = "00";
                break;
            case 5:
                leadingZeros = "000";
                break;
            case 4:
                leadingZeros = "0000";
                break;
            case 3:
                leadingZeros = "00000";
                break;
            case 2:
                leadingZeros = "000000";
                break;
            case 1:
                leadingZeros = "0000000";
                break;
            case 0:
                leadingZeros = "00000000";
                break;

        }
        hexStr = leadingZeros + hexStr;
       // System.out.println(" Hexadecimal: " + hexStr);
        return hexStr;
    }

    private String putSpace(String p) {
        return p.substring(0, 4) + " " + p.substring(4, 8) + " ";
    }    
}
