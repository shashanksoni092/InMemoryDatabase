package common;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.io.BufferedReader;
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
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class TCPServer {

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		
		try {
			ServerSocket serverSocket=new ServerSocket(1234);
			Socket socket=serverSocket.accept();
			
			
			//InputStream istream=socket.getInputStream();
			//BufferedReader br=new BufferedReader(new InputStreamReader(istream));
			//String filename=br.readLine();
			
			BufferedReader content=new BufferedReader(new FileReader("/home/shashanksoni092/Documents/ppt/Employee.txt"));
			
			 ArrayList data = new ArrayList();
			 //192.168.43.65
			
			Connection connection = InMemoryDB.getConnection();
			InMemoryDB.createDatabase(connection);
			//InMemoryDB.displayIMDB(connection);
			/*
			OutputStream ostream=socket.getOutputStream();
			PrintWriter pw=new PrintWriter(ostream,true);
			String str;
			while((str= content.readLine())!=null)
			{
				pw.println(str);
			}
			*/
			  Connection con = InMemoryDB.getConnection();
            System.out.println("Hello");
			  PreparedStatement searchPreparedStatement = null;
		      String SelectQuery = "select * from user_details ";
		      searchPreparedStatement = connection.prepareStatement(SelectQuery);
		      ResultSet rs = searchPreparedStatement.executeQuery();

             while (rs.next()) {
                     //String id = rs.getString(0);
                     String username = rs.getString(1);
                     String FirstName = rs.getString(2);
                     String LastName = rs.getString(3);
                     String Gender = rs.getString(4);
                     String Password = rs.getString(5);
                     String Status = rs.getString(6);
                     
                     data.add( " " + username + " " + FirstName + " " + LastName+" "+Gender+" "+
                     Password+" "+Status);

             }
             System.out.println("How are you");
             writeToFile(data, "/home/shashanksoni092/Documents/ppt/Employee.txt");
             //rs.close();
             //searchPreparedStatement.close();
             System.out.println("wwdd");
             
             OutputStream ostream=socket.getOutputStream();
 			PrintWriter pw=new PrintWriter(ostream,true);
 			String str;
 			while((str= content.readLine())!=null)
 			{
 				pw.println(str);
 			}
 			
 			
			} catch (IOException e) {
			
				e.printStackTrace();
			
			}
		
		
	}

	 private static void writeToFile(ArrayList<String> ArrayList, String path) {
            BufferedWriter out = null;
            try {
                    File file = new File(path);
                    out = new BufferedWriter(new FileWriter(file, true));
                    for (String s : ArrayList) {
                            out.write(s);
                            out.newLine();

                    }
                    //out.close();
            } catch (IOException e) {
            }
    }
}

