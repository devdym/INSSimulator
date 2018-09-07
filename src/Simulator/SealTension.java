package Simulator;

import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dpatsiliandra
 */
class SealTension extends Thread{
    private SealCubbyHole cubbyhole;
    int p, str = 0, StrNb;
    String msg;
    public static boolean done=true;
    public void run() {
        while(done) {
            for (int i = 1; i <= StrNb; i++) {
                msg = produceMsg(i);
                cubbyhole.put(msg);
            }
            try {
                sleep(p * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SealTension.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public SealTension(SealCubbyHole c, int periodStr, int StrNbV) {
        StrNb = StrNbV;
        cubbyhole = c;
        p = periodStr;  
    }

    private String produceMsg(int str) {
        String cMsg = null;
        String tenmsg1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<SERCEL><SEAL><STREAMER_HEAD_MEASURE Version=\"2\">"
                + "<Streamer_Nb>";
        String tenmsg2 = "</Streamer_Nb>"
                + "<Date>";
        String ddate;
        // + "2013-04-25T11:16:47"
        String tenmsg3 = "</Date>"
                + "<Tension>";
        // + "1072"
        int ten = 0;
        String tenmsg4 = "</Tension>"
                + "<PowerOn>true</PowerOn>"
                + "<Current>100.9</Current>"
                + "<OutputVoltage>3.22364</OutputVoltage>"
                + "</STREAMER_HEAD_MEASURE></SEAL></SERCEL>\n";
        Random rand = new Random();
        java.util.Date date = new java.util.Date();

        ten = rand.nextInt((2000 - 1000) + 1) + 1000;
        ddate = new Timestamp(date.getTime()).toString();
        ddate = ddate.replace(" ", "T");
        ddate = ddate.substring(0, ddate.indexOf("."));
        cMsg = tenmsg1 + str + tenmsg2 + ddate + tenmsg3 + ten + tenmsg4;
        //System.out.println(cMsg);
        return cMsg;
    }
}


            
            
       

