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

@WebServlet("/deleteempservlet")
public class deleteempservlet extends HttpServlet {
	
	private static final long serialVersionUID=1L;
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String requestdata=request.getReader().readLine();
		System.out.println(requestdata);
		
		employee p=(employee)UtilityJson.getObjectFromJSON(requestdata, employee.class);
		try
		{
			Map<String,String> m=daolayer.deleteEmp(p);
			
			String responseData = (String) UtilityJson.getJSONFromObject(m);

			response.getWriter().write(responseData);

			response.flushBuffer();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		}
}
		
		
