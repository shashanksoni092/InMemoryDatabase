package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TCPClient {
	

	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		
		
		try {
		      
		    
			
			Socket socket=new Socket("127.0.0.1",1234);
			
			
			InputStream istream=socket.getInputStream();
			BufferedReader content=new BufferedReader(new InputStreamReader(istream));
			String str;
			while((str=content.readLine())!=null)
			{
				System.out.println(str);
			}
			
			
			
		} catch (IOException e) {
			
				e.printStackTrace();
		}
		
		
	}
}





