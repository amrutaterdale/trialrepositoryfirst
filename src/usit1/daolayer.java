package usit1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import usit1.address;
import usit1.employee;

public class daolayer {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usit", "root", "root");
		return con;
	}
	
	public static ArrayList<employee> getAllEmp() throws ClassNotFoundException, SQLException
	{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from employee");
		ResultSet rs=ps.executeQuery();
		
		ArrayList<employee>li=new ArrayList<>();
		 while(rs.next())
		 {
			 employee p=new employee();
			 p.setEmpid(rs.getInt(1));
			 p.setEmpno(rs.getInt(2));
			 p.setName(rs.getString(3));
			 p.setCompany(rs.getString(4));
			 p.setAddressid(rs.getInt(5));
			 p.setRole(rs.getString(6));
			 
			 li.add(p);
		 }
		 
		 return li;
		
		
	}
	
	public static Map<String, String> addemp(employee p) throws ClassNotFoundException, SQLException {

		Connection con = getConnection();

		PreparedStatement ps = con.prepareStatement("insert into employee(empid,empno,name,company,addressid,role) values(?,?,?,?,?,?)");

		int empid=p.getEmpid();
		int empno=p.getEmpno();
		String name = p.getName();
		String company= p.getCompany();
		int addressid= p.getAddressid();
		String role = p.getRole();

		ps.setObject(1, empid);
		ps.setObject(2,empno);
		ps.setObject(3,name);
		ps.setObject(4,company);
		ps.setObject(5,addressid);
		ps.setObject(6,role);


		int status = ps.executeUpdate();

		Map<String, String> map = new HashMap<>();

		if (status == 1) {
			map.put("msg", "done");

		} else {
			map.put("msg", "sorry");
		}
		return map;
	}
	
public static Map<String, String> deleteEmp(employee p) throws ClassNotFoundException, SQLException {
		
		Connection con = getConnection();

		PreparedStatement ps = con.prepareStatement("delete from employee where empid=?");

		ps.setObject(1, p.getEmpid());

		int status = ps.executeUpdate();

		Map<String, String> mp = new HashMap<>();

		if (status == 1) {
			mp.put("msg", "done");
		} else {
			mp.put("msg", "sorry");
		}

		return mp;

	}

public static Map<String, String> updateEmp(employee p) {

	Map<String, String> map=new HashMap<String, String>();
	
	try {
		Connection con = getConnection();

		PreparedStatement ps = con.prepareStatement("update employee set empid=?,empno=?,name=?,company=?,addressid=?,role=? where empid=?");
        
		ps.setInt(1, p.getEmpid());
		ps.setInt(2, p.getEmpno());
		ps.setString(3, p.getName());
		ps.setString(4, p.getCompany());
		ps.setInt(5, p.getAddressid());
		ps.setString(6, p.getRole());
		ps.setInt(7, p.getEmpid());

		int status = ps.executeUpdate();

		if (status == 1)
		{
			map.put("msg", "done");
		}
		else
		{
			map.put("msg", "sorry");
		}

	} 
	catch (Exception ex)
	{
		ex.printStackTrace();
	}

	return map;
}

public static ArrayList<address> getAlladdress() throws SQLException, ClassNotFoundException
{
	Connection con=getConnection();
	PreparedStatement ps=con.prepareStatement("select * from address;");
	
	ResultSet rs=ps.executeQuery();
	ArrayList<address>list=new ArrayList<>();
	
	while(rs.next())
	{
		address r=new address();
		r.setAddressid(rs.getInt(1));
		r.setStreet(rs.getString(2));
		r.setCity(rs.getString(3));
		r.setState(rs.getString(4));
		r.setPincode(rs.getString(5));
		
		list.add(r);
	}
	return list;
}

public static Map<String, String> updateAddress(address p) {

	Map<String, String> map=new HashMap<String, String>();
	
	try {
		Connection con = getConnection();

		PreparedStatement ps = con.prepareStatement("update address set addressid=?,street=?,city=?,state=?,pincode=? where addressid=?");
        
		ps.setInt(1, p.getAddressid());
		ps.setString(2, p.getStreet());
		ps.setString(3, p.getCity());
		ps.setString(4, p.getState());
		ps.setString(5, p.getPincode());
		ps.setInt(6, p.getAddressid());
		

		int status = ps.executeUpdate();

		if (status == 1)
		{
			map.put("msg", "done");
		}
		else
		{
			map.put("msg", "sorry");
		}

	} 
	catch (Exception ex)
	{
		ex.printStackTrace();
	}

	return map;
}


	

}
