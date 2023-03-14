import java.io.DataInputStream;
import java.io.IOException;

import java.io.InputStream;


import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.sql.*;
public class Moisture extends JFrame{
	

	static String header[]={"Status","Date","Time"};
 static DefaultTableModel grid=new DefaultTableModel(header,0);
 JTable tab=new JTable(grid);
 JScrollPane pane=new JScrollPane(tab);
	
	String[] getSoil()
	  {
		pane.setBounds(0,0,1024,768);
		add(pane);
		setSize(1024,768);
		setVisible(true);
		 
		 // 0-TEMP, 1- HUMIDITY, 2- DATE, 3- Time
		String HT[]={"NONE","0000-00-00","00:00:00"};
		int n;
		try{
			Runtime run=Runtime.getRuntime();
			Process pr=run.exec("python3 /home/pi/Desktop/mypi/moisture.py");  // 14 PIN
			InputStream in=pr.getInputStream();
			DataInputStream din=new DataInputStream(in);
			String  line=din.readLine();
			if(line!=null)
			{
				String[] two=line.split(",");
				if(two!=null && two.length==3)
				{
				 HT[0]=two[0];
			 	 HT[1]=two[1];
				 HT[2]=two[2];
				 
	                        
			}
				}  
				
		}catch(Exception ee)
		{
		}
	     return(HT);
	  
	  }
	public static void addRecordToDB(String M[])
	{
		try
		{
			Connection con=Conn1.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into soildata values (?,?,?)");
			ps.setString(1,M[0]);
			ps.setString(2,M[1]);
			ps.setString(3,M[2]);
			ps.executeUpdate();
		}
		catch(Exception ee)
		{
			
		}
	}
	
	static public void display() throws Exception
	{

		final Runtime r=Runtime.getRuntime();
		r.exec("gpio -g mode 20 out");
		System.out.println("Ranchi");
	    final Moisture v1=new Moisture();
	    Runnable run=new Runnable()
	    {
	    	public void run()
	    	{
	    		while(true)
	    		{
	    		String M[]=v1.getSoil();
	    		grid.addRow(M);
	    		addRecordToDB(M);
	    		System.out.println(M[0]+" , "+M[1]+" , "+M[2]);
	    		  try{Thread.sleep(5000);}catch(Exception ee){}
	    		  try{
	    		  if(M[0].trim().equals("DRY"))
	    			  r.exec("gpio -g write 20 0");
	    		  
	    		  else 
	    			  r.exec("gpio -g write 20 1");
	    		  }catch(Exception ee){}
	    			  
	    		}
	    	}
	    };
	    Thread th=new Thread(run);
	    th.start();
	}


	public static void main(String fg[]) throws Exception
	 {
		display();
	 }

}
