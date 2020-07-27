package usit1;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usit1.UtilityJson;
import usit1.daolayer;
import usit1.employee;

@WebServlet("/updateempservlet")
public class updateempservlet extends HttpServlet {
	
	public static final long serialVersionUID=1L;
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String requestdata=request.getReader().readLine();
		
employee p = (employee) UtilityJson.getObjectFromJSON(requestdata, employee.class);
		

		Map<String, String> map = daolayer.updateEmp(p);

		String responseData = (String) UtilityJson.getJSONFromObject(map);
		
		response.getWriter().write(responseData);
		
		response.flushBuffer();

	}

}
