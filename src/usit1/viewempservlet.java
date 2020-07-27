package usit1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usit1.UtilityJson;
import usit1.daolayer;
import usit1.employee;

@WebServlet("/viewempservlet")
public class viewempservlet extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		try {
			ArrayList<employee> list = daolayer.getAllEmp();
			
			System.out.println(list);
			String responseData = (String) UtilityJson.getJSONFromObject(list);
			

			response.getWriter().write(responseData);

			response.flushBuffer();
			

		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{		
			e.printStackTrace();
		}
	}
		
	}

