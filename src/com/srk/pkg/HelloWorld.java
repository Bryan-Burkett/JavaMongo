package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srk.pkg.TimeSeriesDatabaseServiceData;
/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		TimeSeriesDatabaseServiceJDBCSession tsd = new TimeSeriesDatabaseServiceJDBCSession();
		try{
			String coll = "test1";
			String user1 = "test1";
			String user2 = "test2";
			String user3 = "test3";
//			tsd.testConnection();
//			tsd.createCollection(coll);
//			
//			List<TimeSeriesDatabaseServiceData> hardCodeData= new ArrayList<TimeSeriesDatabaseServiceData>();
//			hardCodeData.add(new TimeSeriesDatabaseServiceData(user1,9));
//			hardCodeData.add(new TimeSeriesDatabaseServiceData(user2,90));
//			hardCodeData.add(new TimeSeriesDatabaseServiceData(user3,99));
//			String insert = tsd.insertMultiple(hardCodeData, coll);
//			out.print(insert);
			
//			String sqlPDD = tsd.insertData(new TimeSeriesDatabaseServiceData(user1, 92)); 
//			tsd.insertData(new TimeSeriesDatabaseServiceData(user2, 93));
//			tsd.insertData(new TimeSeriesDatabaseServiceData(user3, 94));
//			out.print(sqlPDD);
//			tsd.listAllDevices(coll);
			//tsd.removeData(user1, coll);
//			tsd.updateData(user2, 2, coll);
//			System.out.print("Inbetween prints");
			tsd.listAllDevices(coll);
			System.out.print("Inbetween prints");
			tsd.findOne(user1, coll);
			System.out.print("Inbetween prints2");
//			tsd.dropCollection(coll);
//			System.out.print("Inbetween prints");
//			tsd.listAllDevices(coll);
//			List<TimeSeriesDatabaseServiceData> listOfDevices = tsd.listAllDevices();
//			for (TimeSeriesDatabaseServiceData obj : listOfDevices){
//				out.print(obj.toString());
//			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}


