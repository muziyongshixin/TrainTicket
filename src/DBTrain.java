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
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//�������ݿ�����
			Connection con = DriverManager.getConnection(url);
			//����Statement����
			Statement st = con.createStatement();
			//ִ�м����õ������
			ResultSet rs = st.executeQuery(sql);
			//��ȡ�������Ԫ����
			ResultSetMetaData rsmd = rs.getMetaData();
			//��ȡ����
			int col = rsmd.getColumnCount();
			//������ȡ�Ľ����
			while(rs.next())
			{
                Vector<Object> vtemp = new Vector<Object>();
				for(int i=1;i<=col;i++)
				{
					vtemp.add(rs.getObject(i));
				}
				vdata.add(vtemp);
			}
			//�رս����,��估����
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
	//�����ж�Rid�Ĵ�С
	public static boolean isLarger(String start,String end,String name)
	{
		int tempstart=0;
		int tempend = 0;
		try
		{
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//�������ݿ�����
			Connection con = DriverManager.getConnection(url);
			//����Statement����
			Statement st = con.createStatement();
			//ִ�м����õ������
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
			//ִ�м����õ������
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
			//�رս����,��估����
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
		//ɾ���б���е�����Ԫ��
		dcbm.removeAllElements();
		try
		{
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//�������ݿ�����
			Connection con = DriverManager.getConnection(url);
			//����Statement����
			Statement st = con.createStatement();
			//�����������
			String sql = "select Sname from station "+
								"where Spy like '"+key+"%'";
			//ִ�м����õ������
			ResultSet rs = st.executeQuery(sql);
			//���������
			while(rs.next())
			{
				//�������б�������Ԫ��
				dcbm.addElement(rs.getString(1));
			}
			//�رս����,��估����
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public static  String account(String sql)//��ѯ�Ƿ��и��û�
	{
        String rt="";
        String ins ="insert";
        String del="delete";

       try {
            //����JDBC-ODBC��������
            Class.forName("com.hxtt.sql.access.AccessDriver");
            String url="jdbc:Access:///./database/Train.mdb";
            //�������ݿ�����
            Connection con = DriverManager.getConnection(url);
            //����Statement����
            Statement st = con.createStatement();
            //ִ�м����õ������
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
               //�رս����,��估����
               rs.close();
           }

            //���������

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
		//��������,�����������Ҫ�����תվ
		Vector<String> midStation = new Vector<String>();
		//������תվ��sql���
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
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//�������ݿ�����
			Connection con = DriverManager.getConnection(url);
			//����Statement����
			Statement st = con.createStatement();
			//ִ�м����õ������
			ResultSet rs = st.executeQuery(sql);
			//���������			
			while(rs.next())
			{
				String str = rs.getString(1);
				midStation.add(str);
			}
			//�رս����,��估����
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//ɾ�����Ϸ�������
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
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//�������ݿ�����
			Connection con = DriverManager.getConnection(url);
			//����Statement����
			Statement st = con.createStatement();
			//ִ�м����õ������
			ResultSet rs = st.executeQuery(sql);
			//�鿴�����			
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			//�رս����,��估����
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
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//�������ݿ�����
			Connection con = DriverManager.getConnection(url);
			//����Statement����
			Statement st = con.createStatement();
			//ִ�м����õ������
			ResultSet rs = st.executeQuery(sql);
			//�鿴�����			
			if(rs.next())
			{
				//������-true
				flag = true;
			}
			//�رս����,��估����
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
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//�������ݿ�����
			Connection con = DriverManager.getConnection(url);
			//����Statement����
			Statement st = con.createStatement();
			//ִ�и���
			count = st.executeUpdate(sql);
			//�ر���估����
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
			//����JDBC-ODBC��������
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			String url="jdbc:Access:///./database/Train.mdb";
			//�������ݿ�����
			Connection con = DriverManager.getConnection(url);
			//����Statement����
			Statement st = con.createStatement();
			//ִ�м����õ������
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			//�رս����,��估����
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