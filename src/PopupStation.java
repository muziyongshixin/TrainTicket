import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;

public class PopupStation extends JFrame implements ListSelectionListener
{
	//创建描述表格标题的vector
	private Vector<String> vhead = new Vector<String>();
	{
		vhead.add("中转站");
	}
	//创建包含表格数据的vector
	private Vector<Vector> vdata = new Vector<Vector>();
	//创建表格型对象
	private DefaultTableModel dtm = new DefaultTableModel(vdata,vhead);
	//创建表格控件
	private JTable jt = new JTable(dtm);
	//创建表格的行排序器
	TableRowSorter sorter = new TableRowSorter(dtm);
	//将表格控件放到滚动窗体中
	private JScrollPane jspTable = new JScrollPane(jt);
	//创建jpanel对象
	private JPanel jp = new JPanel();
	Vector<String> station;
	TrainTime tt;
	String start;
	String end;
	public PopupStation(Vector<String> station,
							TrainTime tt,String start,String end)
	{
		//成员变量初始化
		this.station = station;
		this.tt = tt;
		this.start = start;
		this.end = end;
		//将表格添加到jpanel
		jp.add(jspTable);
		this.add(jp);
		//为表格添加排序器
		jt.setRowSorter(sorter);
		jt.setRowHeight(20);//设置表格行高
		//设置表头不可编辑
		jt.getTableHeader().setReorderingAllowed(false);
		//为表格选择模型注册监听器
		jt.getSelectionModel().addListSelectionListener(this);
		this.initTable(station);		
		//加载窗体图标
		Image icon = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
		//设置窗体图标
		this.setIconImage(icon);		
		//设置窗口大小,可见性,标题,关闭动作
		this.setBounds(350,200,460,400);
		this.setVisible(true);
		this.setTitle("中转站");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void initTable(Vector<String> vt)
	{
		Vector<Vector> vtemp = new Vector<Vector>();
		for(String str:vt)
		{
			Vector<String> v  = new Vector<String>();
			v.add(str);
			vtemp.add(v);
		}
		//设置表格模型的数据与列名
		dtm.setDataVector(vtemp,vhead);
		//更新表格
		this.jt.repaint();
		this.jt.updateUI();		
	}
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		if(e.getValueIsAdjusting())
		{
			int row = jt.getSelectedRow();
			String tname=(String)jt.getValueAt(row,0);
			//得到出发站和中转站之间的车
			Vector<Vector> vstart = tt.getSameVector(start,tname);
			//删除两站之间不合法的车
			vstart = tt.deleteData(vstart);
			//向到中转站和目的站之间的车
			Vector<Vector> vend = tt.getSameVector(tname,end);
			//删除两站间不合法的车
			vend = tt.deleteData(vend);
			if(!tt.getFlag())
			{
				//添加空行
				//vstart.add(new Vector());
				//vstart.add(new Vector());
				//vstart.add(new Vector());
				for(Vector v:vend)
				{
					vstart.add(v);
				}
				tt.updateTable(vstart);				
			}
			else
			{
				//添加空行
				//vend.add(new Vector());
				//vend.add(new Vector());
				//vend.add(new Vector());
				for(Vector v:vstart)
				{
					vend.add(v);
				}
				tt.updateTable(vend);
			}
			this.dispose();	
		}
	}
}