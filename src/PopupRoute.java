import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;

//��������ĳ�г���������վ
public class PopupRoute extends JFrame
{
	//���������������vector
	private Vector<String> vhead = new Vector<String>();
	{
		vhead.add("��վ");
		vhead.add("��ʱ");
		vhead.add("��ʱ");
	}
	//��������������ݵ�vector
	private Vector<Vector> vdata = new Vector<Vector>();
	//��������Ͷ���
	private DefaultTableModel dtm = new DefaultTableModel(vdata,vhead);
	//�������ؼ�
	private JTable jt = new JTable(dtm);
	//�����ؼ��ŵ�����������
	private JScrollPane jspTable = new JScrollPane(jt);
	//����jpanel����
	private JPanel jp = new JPanel();
	//�����г�������
	private String tname;
	public PopupRoute(String tname)
	{
		this.tname = tname;
		//��JPanel�ӵ�JFrame����
		this.add(jp);
		//��ӱ��
		jp.add(jspTable);
		//���ñ�ͷ���ɱ༭
		jt.getTableHeader().setReorderingAllowed(false);
		//�õ��г�������վ
		this.getInfo();
		//���ش���ͼ��
		Image icon = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
		//���ô���ͼ��
		this.setIconImage(icon);
		//���ô�С������,�ɼ���,�رն���
		this.setBounds(300,100,460,450);
		this.setTitle("�г�����վ");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void getInfo()
	{
		//����ĳ�г������ĳ�վ
		String sql = "select Sname,Rarrivetime,Rstarttime "+
							"from station,"+
							"(select Sid,Rid,Rarrivetime,Rstarttime "+
							"from relation where Tid="+
							"(select Tid from train "+
							"where Tname='"+tname+"')) a "+
							"where a.Sid=station.Sid order by Rid";	
		//�õ�����Ҫ���վ
		Vector<Vector> vtemp = DBTrain.getVector(sql);		
		//���ñ��ģ�͵�����������
		dtm.setDataVector(vtemp,vhead);
		//���±��
		this.jt.repaint();
		this.jt.updateUI();
	}
}