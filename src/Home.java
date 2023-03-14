
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.event.*;
public class Home extends JFrame implements ActionListener{
	JLabel limg;
	JButton b1,b2;
	void display()
	{
		limg=new JLabel(new ImageIcon("farm.jpg"));
		b1=new JButton("SOIL MOISTURE");
		b2=new JButton("HUMIDITY & TEMPREATURE");
		
		b1.setBounds(200,200,200,100);
		b2.setBounds(500,200,200,100);
		limg.setBounds(0,0,1024,768);
		
		add(b1);add(b2);add(limg);
		setSize(1024,768);
		setLayout(null);
		setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try{
			Moisture mm=new Moisture();
			mm.display();
			}
			catch(Exception ee)
			{
			}
			}
		
		else if(ae.getSource()==b2)
		{
		TestTemp tt=new TestTemp();
		tt.display();
		
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Home hh=new Home();
		hh.display();

	}

}
