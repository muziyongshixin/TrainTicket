/**
 * Created by ????�? on 2016/4/29.
 */

import java.sql.*;

import java.sql.*;
import java.util.Properties;
import java.util.Vector;

public class ConnectAccess {
    /**
     * ??�???请注??�?
     * 1:??建�??�?�?access??�?a1.mdb,并�?��??D:/�?;
     * 2:?��?��??�???�?a1.mdb�?建�??�?�?�?Table1�?
     * 3�?�?Table1添�??�???�?并�???��?��?�??��?��?�?
     * 4�???????�?�?�??��??类�??��?��?��?��?�?就�??以�??
     */
    public static void main(String args[]) throws Exception {
        ConnectAccess ca=new ConnectAccess();
        ca.ConnectAccessFile();
        //ca.ConnectAccessDataSource();
    }
    public void ConnectAccessFile() throws Exception
    {
        Vector<Vector> vdata = new Vector<Vector>();
        Properties properties = new Properties() ;
        properties.setProperty("charSet", "GB2312") ;
        String sql="select Tname,Tstartstation,Tterminus,Ttype from train,relation,station where Sname='����' and S;";
        Class.forName("com.hxtt.sql.access.AccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:Access:///./database/Train.mdb",properties);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        System.out.println("��ѯ�ɹ�");
        while (rs.next()) {
            String tid=rs.getString(1);
            String tname=rs.getString(2);
            String startstation=rs.getString(3);
            String endstation=rs.getString(4);

            System.out.println(tid +tname +startstation+endstation);
        }
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int col = rsmd.getColumnCount();
//        while(rs.next())
//        {
//            Vector<Object> vtemp = new Vector<Object>();
//            for(int i=1;i<=col;i++)
//            {
//                vtemp.add(rs.getObject(i));
//            }
//            vdata.add(vtemp);
//        }
        rs.close();
        stmt.close();
        conn.close();
    }
    public static void Test(String sql)throws Exception
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
                    System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));

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
    }
}