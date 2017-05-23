import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;

//用来查找某列车所经过的站
public class PopupRoute extends JFrame
{
	//创建描述表格标题的vector
	private Vector<String> vhead = new Vector<String>();
	{
		vhead.add("车站");
		vhead.add("到时");
		vhead.add("发时");
	}
	//创建包含表格数据的vector
	private Vector<Vector> vdata = new Vector<Vector>();
	//创建表格型对象
	private DefaultTableModel dtm = new DefaultTableModel(vdata,vhead);
	//创建表格控件
	private JTable jt = new JTable(dtm);
	//将表格控件放到滚动窗体中
	private JScrollPane jspTable = new JScrollPane(jt);
	//创建jpanel对象
	private JPanel jp = new JPanel();
	//代表列车的名字
	private String tname;
	public PopupRoute(String tname)
	{
		this.tname = tname;
		//将JPanel加到JFrame里面
		this.add(jp);
		//添加表格
		jp.add(jspTable);
		//设置表头不可编辑
		jt.getTableHeader().setReorderingAllowed(false);
		//得到列车经过的站
		this.getInfo();
		//加载窗体图标
		Image icon = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
		//设置窗体图标
		this.setIconImage(icon);
		//设置大小及标题,可见性,关闭动作
		this.setBounds(300,100,460,450);
		this.setTitle("列车经过站");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void getInfo()
	{
		//查找某列车经过的车站
		String sql = "select Sname,Rarrivetime,Rstarttime "+
							"from station,"+
							"(select Sid,Rid,Rarrivetime,Rstarttime "+
							"from relation where Tid="+
							"(select Tid from train "+
							"where Tname='"+tname+"')) a "+
							"where a.Sid=station.Sid order by Rid";	
		//得到符合要求的站
		Vector<Vector> vtemp = DBTrain.getVector(sql);		
		//设置表格模型的数据与列名
		dtm.setDataVector(vtemp,vhead);
		//更新表格
		this.jt.repaint();
		this.jt.updateUI();
	}
}