package Simulator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dmitriypatsiliandra
 */
class SealAcq extends Thread{
    private SealCubbyHole cubbyhole;
    int pAcq, fileNb, spNb, sq;
    String msg, laneName, ddate;
    
    public void run(){
        while(true){
            msg = ProduceMsg();
            cubbyhole.put(msg);
            try {
                Thread.sleep(pAcq * 1000);
            } catch (InterruptedException e) {
                return;
            }
        }      
    }

    public SealAcq(SealCubbyHole c, int periodAcq, String lName, int shotP, int fileN, int seq) {
        cubbyhole = c;
        pAcq = periodAcq;
        fileNb = fileN;
        sq = seq;
        spNb =shotP;
        laneName = lName;
    }

    private String ProduceMsg() {
        Date ddate = new Date();
       
        SimpleDateFormat df = new SimpleDateFormat("dd MMM YYYY");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat jf = new SimpleDateFormat("D");
        String sealmsg = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
            + "<SERCEL><SEAL><ACQ_LOG>"
            + "<Line_Name>"+ laneName +"</Line_Name>"
            + "<Sequence_Number>"+ sq +"</Sequence_Number>"
            + "<Shot_Point_Number>"+ spNb++ +"</Shot_Point_Number>"
            + "<File_Number>"+ fileNb++ +"</File_Number>"
            + "<T0_Date>"+df.format(ddate)+"</T0_Date>"
            + "<T0_Time>"+tf.format(ddate)+"</T0_Time>"
            + "<Julian_Day>"+jf.format(ddate)+"</Julian_Day>"
            + "<T0_Mode>Internal</T0_Mode>"
            + "<Record_Type>Normal</Record_Type>"
            + "<File_Type>Tb Triggered</File_Type>"
            + "<Type_of_Test>N/A</Type_of_Test>"
            + "<Water_Delay>0</Water_Delay>"
            + "<Navigation_Message_Length>0</Navigation_Message_Length>"
            + "<Total_Number_of_Traces>480</Total_Number_of_Traces>"
            + "<Number_of_Aux_Traces>0</Number_of_Aux_Traces>"
            + "<Number_of_Seis_Traces>480</Number_of_Seis_Traces>"
            + "<Number_of_Dead_Seis_Channels>468</Number_of_Dead_Seis_Channels>"
            + "<Seal_Seis_Record_Length>6000</Seal_Seis_Record_Length>"
            + "<Seal_Seis_Sample_Rate>2000</Seal_Seis_Sample_Rate>"
            + "<Seal_Seis_Number_of_Samples>3001</Seal_Seis_Number_of_Samples>"
            + "<Seal_Aux_Record_Length>6000</Seal_Aux_Record_Length>"
            + "<Seal_Aux_Sample_Rate>2000</Seal_Aux_Sample_Rate>"
            + "<Seal_Aux_Number_of_Samples>3001</Seal_Aux_Number_of_Samples>"
            + "<Aux_Digital_Low_Cut_Filter>3</Aux_Digital_Low_Cut_Filter>"
            + "<Seis_Digital_Low_Cut_Filter>0</Seis_Digital_Low_Cut_Filter>"
            + "<Seis3dB_Compound_Low_Cut_Filter>2</Seis3dB_Compound_Low_Cut_Filter>"
            + "<Nb_Of_Recorded_Channel_Set>16</Nb_Of_Recorded_Channel_Set>"
            + "<External_Header></External_Header>"
            + "<External_Header_Size>4096</External_Header_Size>"
            + "<SEGD_Disk_Write_Error>No</SEGD_Disk_Write_Error>"
            + "<Trace_Summing_Description></Trace_Summing_Description>"
            + "<List_Of_Error_Traces>"
            + "</List_Of_Error_Traces>"
            + "</ACQ_LOG></SEAL></SERCEL>\n";
        return sealmsg;
    }
}

