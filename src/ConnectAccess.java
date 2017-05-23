/**
 * Created by ????å¿? on 2016/4/29.
 */

import java.sql.*;

import java.sql.*;
import java.util.Properties;
import java.util.Vector;

public class ConnectAccess {
    /**
     * ??å­???è¯·æ³¨??ï¼?
     * 1:??å»ºç??ä¸?ä¸?access??ä»?a1.mdb,å¹¶æ?¾å??D:/ä¸?;
     * 2:?¨æ?°æ??åº???ä»?a1.mdbä¸?å»ºç??ä¸?ä¸?è¡?Table1ï¼?
     * 3ï¼?ä¸?Table1æ·»å??ä¸???ï¼?å¹¶æ???¥è?³å?ä¸??¡è?°å?ï¼?
     * 4ï¼???????ä¸?ä¸?å®??´ç??ç±»ï??´æ?¥æ?¿å?»è?è¡?å°±å??ä»¥ã??
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
        String sql="select Tname,Tstartstation,Tterminus,Ttype from train,relation,station where Sname='±±¾©' and S;";
        Class.forName("com.hxtt.sql.access.AccessDriver");
        Connection conn = DriverManager.getConnection("jdbc:Access:///./database/Train.mdb",properties);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        System.out.println("²éÑ¯³É¹¦");
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
            //¼ÓÔØJDBC-ODBCÇÅÇý¶¯Àà
            Class.forName("com.hxtt.sql.access.AccessDriver");
            String url="jdbc:Access:///./database/Train.mdb";
            //´´½¨Êý¾Ý¿âÁ¬½Ó
            Connection con = DriverManager.getConnection(url);
            //´´½¨Statement¶ÔÏó
            Statement st = con.createStatement();
            //Ö´ÐÐ¼ìË÷µÃµ½½á¹û¼¯
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
                //¹Ø±Õ½á¹û¼¯,Óï¾ä¼°Á¬½Ó
                rs.close();
            }

            //±éÀú½á¹û¼¯

            st.close();
            con.close();


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}