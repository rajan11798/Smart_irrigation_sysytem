import java.awt.Toolkit;
import java.sql.*;
import java.awt.event.*;


import javax.swing.*;

import com.mysql.jdbc.PreparedStatement;
public class LoginAllowance extends JFrame implements ActionListener
{
	JLabel j1,j2;
	JTextField t1;
	JPasswordField p1;
	JButton b1,b2;
	void show1()
	{
		Toolkit kit=Toolkit.getDefaultToolkit();
		int width=(int)kit.getScreenSize().getWidth();
		int height=(int)kit.getScreenSize().getHeight();
	j1=	new JLabel("UserId");
	j2=new JLabel("password");
	p1=new JPasswordField("");
	b1=new JButton("Login");
	b2=new JButton("Cancel");
	t1=new JTextField("");
	j1.setBounds(100,100,120,50);
	t1.setBounds(210,100,120,50);
	j2.setBounds(100,170,120,50);
	p1.setBounds(210,170,120,50);
	b1.setBounds(100,240,100,50);
	b2.setBounds(210,240,100,50);
	add(j1);add(j2);add(p1);add(t1);add(b1);add(b2);
	setSize(width,height);
	setLayout(null);
	setVisible(true);
	b1.addActionListener(this);
	b2.addActionListener(this);
	}
	public  void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
		try
		{
			Connection con=Conn1.getConnection();
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select *from logindata where userid=? and password=?");
			ps.setString(1,t1.getText());
			ps.setString(2, p1.getText());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				Home hh=new Home();
				hh.display();	
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Invalid Password");
			}
			
			
		}
		catch(Exception eee){}
	}}
public static void main(String[] args)
{
	LoginAllowance t=new LoginAllowance();
	t.show1();
}
}
