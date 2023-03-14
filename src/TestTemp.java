import java.io.DataInputStream;
import java.sql.*;
import java.io.InputStream;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class TestTemp extends JFrame
{
	String header[]={"Humidity","Tempreature","Date","Time"};
 DefaultTableModel grid=new DefaultTableModel(header,0);
 JTable tab=new JTable(grid);
 JScrollPane pane=new JScrollPane(tab);
 void display()
 {
	
	 
	 pane.setBounds(0, 0,1366,768);
	 add(pane);
	 setSize(1366,768);
	 setVisible(true);
	 //final Connection con=Conn1.getConnection();
	 //final TestTemp t1=new TestTemp();
	    Runnable run=new Runnable()
	       {
	            public void run()
	             {
	            	  while(true)
	            	  {
	            		  
						    String HT[]=getDTH();
						    System.out.println("TEMPRAT="+HT[1]+"\t"+"HUMIDITY="+HT[0]+"\t"+"DATE="+HT[2]+"\t"+"TIME="+HT[3]);
						   grid.addRow(HT);
						   if(HT[1].length()!=4 && HT[0].length()!=4)
						   {
						   int n1=(int)Double.parseDouble(HT[1]);
						   int n2=(int)Double.parseDouble(HT[0]);
						   
						   //MOTOR CODE
						   try{
								Runtime run=Runtime.getRuntime();
								run.exec("gpio -g mode 20 out");
								
									if(n1>=29 && n2>=80)//here you can change tempreature and humidity condition for motor
									{  
										run.exec("gpio -g write 20 0");
										System.out.println("MOTOR ON");
									}
									else
									{   
										System.out.println("MOTOR OFF");
										run.exec("gpio -g write 20 1");
									}
									
						                        
								
									 
									
							}
						   catch(Exception ee)
							{
							}
						   
						   
						   }
						   
						   //
						   
						   
						   
						   
						   
						   
						   
						   
						   
						   
						   
							   
						    
								 try{
								    	Connection con=Conn1.getConnection();
								    	PreparedStatement ed=con.prepareStatement("insert into WeatherInfo values(?,?,?,?)");
								    	ed.setString(1,HT[1]);
								    	ed.setString(2,HT[0]);
								    	ed.setString(3,HT[2]);
								    	ed.setString (4,HT[3]);
								    	ed.executeUpdate();
								    	ed.close();
								    	Thread.sleep(5000);
								    	
								    }
								    catch(Exception eer)
								    {
								    	System.out.println("");
								    }
	            	  }
	             } // run()
	       }; // run object
	       Thread th=new Thread(run); th.start();

 }
 String[] getDTH()
  {
	 
	 // 0-TEMP, 1- HUMIDITY, 2- DATE, 3- Time
	String HT[]={"-55","-55","0000-00-00","00:00:00"};
	try{
		Runtime run=Runtime.getRuntime();
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
			HT[1]=two[1];
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
    TestTemp v1=new TestTemp();
    v1.display();
     }

 }


