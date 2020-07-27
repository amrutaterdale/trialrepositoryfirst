package usit1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usit1.UtilityJson;
import usit1.daolayer;
import usit1.employee;

@WebServlet("/addempservlet")
public class addempservlet extends HttpServlet {

	public static final long serialVersionUID=1L;
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String requestdata=request.getReader().readLine();
		System.out.println(requestdata);
		
		employee p=(employee) UtilityJson.getObjectFromJSON(requestdata, employee.class);
		
		Map<String, String> map = null;
		try
		{
			map = daolayer.addemp(p);
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		String responsedata = (String) UtilityJson.getJSONFromObject(map);
		
		response.getWriter().write(responsedata);
		
		response.flushBuffer();
	}
}
		