import java.io.*;
class ReadTH
{
 
 double[] getDTH()
  {
	double HT[]={-55,-55};
	try{
		Runtime run=Runtime.getRuntime();
		Process pr=run.exec("python3 /home/pi/Desktop/mypi/jtmp.py");
		InputStream in=pr.getInputStream();
		DataInputStream din=new DataInputStream(in);
		String  line=din.readLine();
		if(line!=null)
		{
			String[] two=line.split(",");
			if(two!=null && two.length==2)
			{
			 HT[0]=Double.parseDouble(two[0]);
			HT[1]=Double.parseDouble(two[1]);
                        Thread.sleep(5000);
		}
			}
			
	}catch(Exception ee)
	{
	}
     return(HT);

  }


public static void main(String fg[])
 {
    ReadTH v1=new ReadTH();
   double HT[]=v1.getDTH();
  System.out.println("TEMPRAT="+HT[1]);
  System.out.println("HUMIDITY="+HT[0]);
 }

}

