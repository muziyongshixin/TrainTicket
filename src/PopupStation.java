import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;

public class PopupStation extends JFrame implements ListSelectionListener
{
	//���������������vector
	private Vector<String> vhead = new Vector<String>();
	{
		vhead.add("��תվ");
	}
	//��������������ݵ�vector
	private Vector<Vector> vdata = new Vector<Vector>();
	//��������Ͷ���
	private DefaultTableModel dtm = new DefaultTableModel(vdata,vhead);
	//�������ؼ�
	private JTable jt = new JTable(dtm);
	//����������������
	TableRowSorter sorter = new TableRowSorter(dtm);
	//�����ؼ��ŵ�����������
	private JScrollPane jspTable = new JScrollPane(jt);
	//����jpanel����
	private JPanel jp = new JPanel();
	Vector<String> station;
	TrainTime tt;
	String start;
	String end;
	public PopupStation(Vector<String> station,
							TrainTime tt,String start,String end)
	{
		//��Ա������ʼ��
		this.station = station;
		this.tt = tt;
		this.start = start;
		this.end = end;
		//�������ӵ�jpanel
		jp.add(jspTable);
		this.add(jp);
		//Ϊ������������
		jt.setRowSorter(sorter);
		jt.setRowHeight(20);//���ñ���и�
		//���ñ�ͷ���ɱ༭
		jt.getTableHeader().setReorderingAllowed(false);
		//Ϊ���ѡ��ģ��ע�������
		jt.getSelectionModel().addListSelectionListener(this);
		this.initTable(station);		
		//���ش���ͼ��
		Image icon = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
		//���ô���ͼ��
		this.setIconImage(icon);		
		//���ô��ڴ�С,�ɼ���,����,�رն���
		this.setBounds(350,200,460,400);
		this.setVisible(true);
		this.setTitle("��תվ");
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
		//���ñ��ģ�͵�����������
		dtm.setDataVector(vtemp,vhead);
		//���±��
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
			//�õ�����վ����תվ֮��ĳ�
			Vector<Vector> vstart = tt.getSameVector(start,tname);
			//ɾ����վ֮�䲻�Ϸ��ĳ�
			vstart = tt.deleteData(vstart);
			//����תվ��Ŀ��վ֮��ĳ�
			Vector<Vector> vend = tt.getSameVector(tname,end);
			//ɾ����վ�䲻�Ϸ��ĳ�
			vend = tt.deleteData(vend);
			if(!tt.getFlag())
			{
				//��ӿ���
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
				//��ӿ���
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