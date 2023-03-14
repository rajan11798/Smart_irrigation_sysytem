import java.io.DataInputStream;
import java.sql.*;
import java.io.InputStream;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class ReadTH extends JFrame
{
	
 
 String[] getDTH()
  {
	 
	 // 0-TEMP, 1- HUMIDITY, 2- DATE, 3- Time
	String HT[]={"-55","-55","0000-00-00","00:00:00"};
	int n;
	int n1;
	try{
		Runtime run=Runtime.getRuntime();
		run.exec("gpio -g mode 20 out");
		Process pr=run.exec("python3 /home/pi/Desktop/mypi/jtmp.py");
		InputStream in=pr.getInputStream();
		DataInputStream din=new DataInputStream(in);
		String  line=din.readLine();
		if(line!=null)
		{
			String[] two=line.split(",");
			if(two!=null && two.length==4)
			{
			 HT[0]=two[0];
			 n1=(int)(Double.parseDouble(HT[0]));
			HT[1]=two[1];
			n=(int)(Double.parseDouble(HT[1]));
			if(n>=29 && n1>=35)
			{  
				run.exec("gpio -g write 20 0");
				System.out.println("MOTOR ON");
			}
			else
			{   
				System.out.println("MOTOR OFF");
				run.exec("gpio -g write 20 1");
			}
			HT[2]=two[2];
			HT[3]=two[3];
                        
		}
			}  
			
	}catch(Exception ee)
	{
	}
     return(HT);
  
  }


public static void main(String fg[]) throws Exception
 {
	
	System.out.println("Ranchi");
    final ReadTH v1=new ReadTH();
    
 final Connection con=Conn1.getConnection();
    Runnable run=new Runnable()
       {
            public void run()
             {
            	  while(true)
            	  {
            		  
					    String HT[]=v1.getDTH();
					    System.out.println("TEMPRAT="+HT[1]+"\t"+"HUMIDITY="+HT[0]+"\t"+"DATE="+HT[2]+"\t"+"TIME="+HT[3]);
					   
					    
							    try{
							    	
							    	PreparedStatement ed=con.prepareStatement("insert into weatherinfo values(?,?,?,?)");
							    	ed.setString(1,"RANCHI");
							    	ed.setFloat(2,Float.parseFloat(HT[1]));
							    	ed.setFloat(3,Float.parseFloat(HT[0]));
							    	ed.setString (4,HT[2]+" "+HT[3]);
							    	ed.executeUpdate();
							    	ed.close();
							    	Thread.sleep(5000);
							    	
							    }
							    catch(Exception eer)
							    {
							    }
            	  }
             } // run()
       }; // run object
       Thread th=new Thread(run); th.start();
    }

 }


