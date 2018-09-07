package Simulator;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmitriypatsiliandra
 */
class SealTailData extends Thread{
    private SealCubbyHole cubbyhole;
    String msg;
    int p, str = 0, StrNb;
    public static boolean done = true;
    
    public void run(){
        while (done) {
            for (int i = 1; i <= StrNb; i++) {
                msg = produceMsg(i);
                cubbyhole.put(msg);
            }
            try {
                sleep(p * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SealTailData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public SealTailData(SealCubbyHole c, int periodTen, int StrNbV){
        StrNb = StrNbV;
        cubbyhole = c;
        p = periodTen;
    }

    private String produceMsg(int str) {
        int Str;
        String cMsg, ddate;
        String tailmsg1
                = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
                + "<SERCEL><SEAL><STREAMER_TAIL_MEASURE Version=\"2\">"
                + "<Streamer_Nb>",
                tailmsg2 = "</Streamer_Nb>"
                + "<Date>",
                tailmsg3 = "</Date>"
                + "<PowerOn>true</PowerOn>"
                + "<Current>98</Current>"
                + "<OutputVoltage>49.9724</OutputVoltage>"
                + "<TapuSealHvOn>false</TapuSealHvOn>"
                + "</STREAMER_TAIL_MEASURE></SEAL></SERCEL>\n";
        java.util.Date date = new java.util.Date();
        ddate = new Timestamp(date.getTime()).toString();
        cMsg = tailmsg1 + str + tailmsg2 + ddate + tailmsg3;
        return cMsg;
    }
}
