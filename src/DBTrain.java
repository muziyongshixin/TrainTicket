import   java.sql.*;
import java.util.*;
import javax.swing.plaf.basic.*;
import javax.swing.*;

public class DBTrain
{
		
	public static Vector<Vector> getVector(String sql)
	{
		Vector<Vector> vdata = new Vector<Vector>();
		try
		{
			//加载JDBC-ODBC桥驱动类
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//创建数据库连接
			Connection con = DriverManager.getConnection(url);
			//创建Statement对象
			Statement st = con.createStatement();
			//执行检索得到结果集
			ResultSet rs = st.executeQuery(sql);
			//获取结果集的元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取列数
			int col = rsmd.getColumnCount();
			//遍历获取的结果集
			while(rs.next())
			{
                Vector<Object> vtemp = new Vector<Object>();
				for(int i=1;i<=col;i++)
				{
					vtemp.add(rs.getObject(i));
				}
				vdata.add(vtemp);
			}
			//关闭结果集,语句及连接
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return vdata;
	}
	//用来判断Rid的大小
	public static boolean isLarger(String start,String end,String name)
	{
		int tempstart=0;
		int tempend = 0;
		try
		{
			//加载JDBC-ODBC桥驱动类
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//创建数据库连接
			Connection con = DriverManager.getConnection(url);
			//创建Statement对象
			Statement st = con.createStatement();
			//执行检索得到结果集
			ResultSet rs = st.executeQuery("select Rid from relation "+
											"where relation.Sid="+
											"(select Sid from station "+
											"where Sname='"+start+"') and "+
											"relation.Tid= "+
											"(select Tid from train "+
											"where Tname='"+name+"')");
			if(rs.next())
			{
				tempstart = rs.getInt(1);
			}
			//执行检索得到结果集
			rs = st.executeQuery("select Rid from relation "+
											"where relation.Sid="+
											"(select Sid from station "+
											"where Sname='"+end+"') and "+
											"relation.Tid= "+
											"(select Tid from train "+
											"where Tname='"+name+"')");
			if(rs.next())
			{
				tempend = rs.getInt(1);
			}
			//关闭结果集,语句及连接
			rs.close();
			st.close();
			con.close();
						
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(tempstart<tempend)
		{
			return true;
		}
		return false;
	}
	
	public static void setCurrentModel(String key,DefaultComboBoxModel dcbm)
	{
		//删除列表框中的所有元素
		dcbm.removeAllElements();
		try
		{
			//加载JDBC-ODBC桥驱动类
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//创建数据库连接
			Connection con = DriverManager.getConnection(url);
			//创建Statement对象
			Statement st = con.createStatement();
			//声明搜索语句
			String sql = "select Sname from station "+
								"where Spy like '"+key+"%'";
			//执行检索得到结果集
			ResultSet rs = st.executeQuery(sql);
			//遍历结果集
			while(rs.next())
			{
				//向下拉列表框中添加元素
				dcbm.addElement(rs.getString(1));
			}
			//关闭结果集,语句及连接
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public static  String account(String sql)//查询是否有改用户
	{
        String rt="";
        String ins ="insert";
        String del="delete";

       try {
            //加载JDBC-ODBC桥驱动类
            Class.forName("com.hxtt.sql.access.AccessDriver");
            String url="jdbc:Access:///./database/Train.mdb";
            //创建数据库连接
            Connection con = DriverManager.getConnection(url);
            //创建Statement对象
            Statement st = con.createStatement();
            //执行检索得到结果集
           if(sql.contains(ins)||sql.contains(del))
           {
               st.executeUpdate(sql);
           }
            else
           {
               ResultSet rs = st.executeQuery(sql);
               while(rs.next())
               {
                   rt = rs.getString(1);
                   return  rt;
               }
               //关闭结果集,语句及连接
               rs.close();
           }

            //遍历结果集

            st.close();
            con.close();


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rt;

	}
	public static Vector<String> getMidStation(String start,String end)
	{
		//创建向量,用来存入符合要求的中转站
		Vector<String> midStation = new Vector<String>();
		//搜索中转站的sql语句
		String sql="select a.Sname from station a "+
				"where exists "+
					"(select Tname from train where Tid in "+
					"(select Tid from relation where Sid in"+
					"(select Sid from station where Sname='"+start+"') "+
					"and Tid in"+
					"(select Tid from relation where Sid in "+
					"(select Sid from station where Sname=a.Sname)))) "+
				"and exists "+
					"(select Tname from train where Tid in "+
					"(select Tid from relation where Sid in"+
					"(select Sid from station where Sname='"+end+"') "+
					"and Tid in"+
					"(select Tid from relation where Sid in "+
					"(select Sid from station where Sname=a.Sname))))";
		try
		{
			//加载JDBC-ODBC桥驱动类
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//创建数据库连接
			Connection con = DriverManager.getConnection(url);
			//创建Statement对象
			Statement st = con.createStatement();
			//执行检索得到结果集
			ResultSet rs = st.executeQuery(sql);
			//遍历结果集			
			while(rs.next())
			{
				String str = rs.getString(1);
				midStation.add(str);
			}
			//关闭结果集,语句及连接
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//删除不合法的数据
		midStation.remove(start);
		midStation.remove(end);	
		return midStation;
	}
	
	public static int getInsertId(String name,String tid)
	{
		int id = 0;
		String sql = "select Max("+tid+") from "+name;
		try
		{
			//加载JDBC-ODBC桥驱动类
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//创建数据库连接
			Connection con = DriverManager.getConnection(url);
			//创建Statement对象
			Statement st = con.createStatement();
			//执行检索得到结果集
			ResultSet rs = st.executeQuery(sql);
			//查看结果集			
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			//关闭结果集,语句及连接
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		id++;
		return id;
	}
	
	public static boolean isExist(String sql)
	{
		boolean flag = false;
		try
		{
			//加载JDBC-ODBC桥驱动类
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//创建数据库连接
			Connection con = DriverManager.getConnection(url);
			//创建Statement对象
			Statement st = con.createStatement();
			//执行检索得到结果集
			ResultSet rs = st.executeQuery(sql);
			//查看结果集			
			if(rs.next())
			{
				//若存在-true
				flag = true;
			}
			//关闭结果集,语句及连接
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;	
	}
	
	public static int insert(String sql)
	{
		int count=0;
		try
		{
			//加载JDBC-ODBC桥驱动类
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//创建数据库连接
			Connection con = DriverManager.getConnection(url);
			//创建Statement对象
			Statement st = con.createStatement();
			//执行更新
			count = st.executeUpdate(sql);
			//关闭语句及连接
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;	
	}
	
	public static int getExistId(String sql)
	{
		int id=0;
		try
		{
			//加载JDBC-ODBC桥驱动类
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//创建数据库连接
			Connection con = DriverManager.getConnection(url);
			//创建Statement对象
			Statement st = con.createStatement();
			//执行检索得到结果集
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			//关闭结果集,语句及连接
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}
}