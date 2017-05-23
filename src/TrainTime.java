import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.plaf.basic.*;

public class TrainTime extends JFrame implements ActionListener, MouseListener, ListSelectionListener, KeyListener {
    //���������������vector
    private Vector<String> vhead = new Vector<String>();

    {
        vhead.add("����");
        vhead.add("ʼ��վ");
        vhead.add("�յ�վ");
        vhead.add("�г�����");
        vhead.add("��վ");
        vhead.add("��ʱ");
        vhead.add("��վ");
        vhead.add("��ʱ");
    }

    //��������������ݵ�vector
    private Vector<Vector> vdata = new Vector<Vector>();
    //��������Ͷ���
    private DefaultTableModel dtm = new DefaultTableModel(vdata, vhead);
    //�������ؼ�
    private JTable jt = new JTable(dtm);
    //�����ؼ��ŵ�����������
    private JScrollPane jspTable = new JScrollPane(jt);
    //����������������
    TableRowSorter sorter = new TableRowSorter(dtm);
    //�������,������ѯ
    private JPanel jp = new JPanel();
    //��������ָ�
    private JSplitPane jsp = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT, jspTable, jp);//������Box������JSplitPane�������������ڵ��л�������ʾ�����Ȼ��Box���԰������������������JSplitPaneֻ����������ʾ���������������Ա仯�ߴ粢ͨ��һ�����ƶ��ķָ������зָ����ָ�������ʹ���û�����ͨ����ק�ָ�������������������ĳߴ�

    //������ѡ��ť����
    private JRadioButton[] jrbArray = {
            new JRadioButton("վվ��ѯ", true),
            new JRadioButton("���β�ѯ"),
            new JRadioButton("��վ���г��β�ѯ")};
    //������ѡ��ť��
    private ButtonGroup bgStation = new ButtonGroup();
    //������ѡ��
    private JCheckBox jcbMidStation = new JCheckBox("��תվ");
    private JCheckBox jcbReverseStation = new JCheckBox("����վվ��ѯ");
    //������ǩ���ı���
    private JLabel jlStart = new JLabel("����վ:");
    private JComboBox jcbStart = new JComboBox();
    private JLabel jlEnd = new JLabel("Ŀ��վ:");
    private JComboBox jcbEnd = new JComboBox();
    private JLabel jlMid = new JLabel("��תվ:");
    private JComboBox jcbMid = new JComboBox();
    private JLabel jlTid = new JLabel("����:");
    private JTextField jtfTname = new JTextField();
    private JLabel jlSid = new JLabel("��վվ��:");
    private JComboBox jcbSname = new JComboBox();
    //������ť
    private JButton jbQuery = new JButton("��ѯ");
    private JButton jbApp = new JButton("���ӹ���");
    //�����ɱ༭��Ͽ��Ĭ�ϱ༭��
    private BasicComboBoxEditor bcbeStart = new BasicComboBoxEditor();
    //������Ͽ��Ĭ��ģ��
    private DefaultComboBoxModel dcbmStart = new DefaultComboBoxModel();
    private BasicComboBoxEditor bcbeEnd = new BasicComboBoxEditor();
    private DefaultComboBoxModel dcbmEnd = new DefaultComboBoxModel();
    private BasicComboBoxEditor bcbeMid = new BasicComboBoxEditor();
    private DefaultComboBoxModel dcbmMid = new DefaultComboBoxModel();
    private BasicComboBoxEditor bcbeSname = new BasicComboBoxEditor();
    private DefaultComboBoxModel dcbmSname = new DefaultComboBoxModel();
    //���������˵�
    PopupMenu popup = new PopupMenu();
    //�����˵�
    Menu jm = new Menu("Add");
    //�����˵�������
    MenuItem[] item = {new MenuItem("Add train"), new MenuItem("Add Station"),
            new MenuItem("Add Relation"), new MenuItem("EXIT")};
    //����SystemTray��Ա����
    SystemTray tray;//ϵͳ����
    //����TrayIcon��Ա����
    TrayIcon trayIcon;


    public TrainTime(int sw)
    {
        this();
        if(sw==0)
            jp.remove(jbApp);


    }

    public TrainTime()//���췽��
    {
        this.add(jsp);
        //���÷ָ�����λ��
        jsp.setDividerLocation(400);
        //���÷ָ�����С
        jsp.setDividerSize(2);
        //Ϊ������������
        jt.setRowSorter(sorter);
        jt.setRowHeight(20);//���ñ���и�
        //Ϊ���ѡ��ģ��ע�������
        jt.getSelectionModel().addListSelectionListener(this);
        jt.addMouseListener(this);
        //���ñ�ͷ���ɱ༭
        jt.getTableHeader().setReorderingAllowed(false);
        //����JPanel����Ϊ��
        jp.setLayout(null);
        //��ʼ��JRadioButton����
        initJRadioButton();
        //��ʼ����ѡ��
        initJCheckBox();
        //��ʼ���ı���
        initJTextField();
        //��ʼ��ϵͳ����
        initTray();
        //���ش���ͼ��
        Image icon = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
        //���ô���ͼ��
        this.setIconImage(icon);
        //���ô��������
        this.setTitle("��Ʊ��ѯϵͳ");
        //ʹ��󻯰�ť������
        this.setResizable(false);
        //���ô���Ĵ�С��λ��
        this.setBounds(120, 50, 800, 600);
        //���ô���Ŀɼ���
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳�
    }

    public void initJRadioButton()//��ʼ����ť
    {
        for (int i = 0; i < jrbArray.length; i++) {
            //���õ�ѡ��ť�Ĵ�С
            jrbArray[i].setBounds(150, 50 + i * 30, 150, 30);
            jrbArray[i].setFont(new Font("΢���ź�", Font.PLAIN, 14));
            //����ѡ��ť�ӵ�JPanel��
            jp.add(jrbArray[i]);
            //����ѡ��ť�ӵ���ť������
            bgStation.add(jrbArray[i]);
            //ע���¼�������
            jrbArray[i].addActionListener(this);
        }
    }

    public void initJCheckBox() //��ʼ����ѡ��
    {
        //���ø�ѡ��Ĵ�С
        jcbReverseStation.setBounds(350, 50, 130, 30);
        jcbReverseStation.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //����ѡ��ӵ�JPanel��
        jp.add(jcbReverseStation);
        //ע���¼�������
        jcbReverseStation.addActionListener(this);
        jcbMidStation.setBounds(520, 50, 80, 30);
        jcbMidStation.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jcbMidStation);
        jcbMidStation.addActionListener(this);
    }

    public void initJTextField()//��ʼ���ı���
    {
        //���ñ�ǩ��С���������뵽JPanel
        jlStart.setBounds(150, 20, 50, 30);
        jlStart.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jlStart);
        //����jcbStart�Ĵ�С��λ��
        jcbStart.setBounds(210, 25, 80, 20);
        jcbStart.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //����jcbStartΪ�ɱ༭״̬
        jcbStart.setEditable(true);
        //ΪjcbStart���ñ༭��
        jcbStart.setEditor(bcbeStart);
        //ΪjcbStart����model
        jcbStart.setModel(dcbmStart);
        jp.add(jcbStart);
        //Ϊ�ı���ע���¼�������
//		jcbStart.addActionListener(this);
        //ע������¼�������
        jcbStart.getEditor().getEditorComponent().addMouseListener(this);
        //ע����̼�����
        jcbStart.getEditor().getEditorComponent().addKeyListener(this);
        jlEnd.setBounds(330, 20, 50, 30);
        jlEnd.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jlEnd);
        jcbEnd.setBounds(390, 25, 80, 20);
        jcbEnd.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //��jcbEnd����Ϊ�ɱ༭
        jcbEnd.setEditable(true);
        //ΪjcbEnd����editor��model
        jcbEnd.setEditor(bcbeEnd);
        jcbEnd.setModel(dcbmEnd);
        jp.add(jcbEnd);
        //ΪjcbEnd������ͼ��̼�����
        jcbEnd.getEditor().getEditorComponent().addMouseListener(this);
        jcbEnd.getEditor().getEditorComponent().addKeyListener(this);
        jlMid.setBounds(510, 20, 50, 30);
        jlMid.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jlMid);
        jcbMid.setBounds(570, 25, 80, 20);
        jcbMid.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //��jcbMid����Ϊ�ɱ༭
        jcbMid.setEditable(true);
        //ΪjcbMid����editor��model
        jcbMid.setEditor(bcbeMid);
        jcbMid.setModel(dcbmMid);
        jp.add(jcbMid);
        //ΪjcbMid������ͼ��̼�����
        jcbMid.getEditor().getEditorComponent().addMouseListener(this);
        jcbMid.getEditor().getEditorComponent().addKeyListener(this);
        jlTid.setBounds(350, 80, 40, 30);
        jlTid.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jlTid);
        jtfTname.setBounds(420, 85, 80, 20);
        jtfTname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jtfTname);
        //ΪjtfTname�����������
        jtfTname.addMouseListener(this);
        //ΪjtfTname����¼�������
        jtfTname.addActionListener(this);
        jlSid.setBounds(350, 110, 60, 30);
        jlSid.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jlSid);
        jcbSname.setBounds(420, 115, 80, 20);
        jcbSname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        //��jcbSname����Ϊ�ɱ༭
        jcbSname.setEditable(true);
        //ΪjcbSname����editor��model
        jcbSname.setEditor(bcbeSname);
        jcbSname.setModel(dcbmSname);
        jp.add(jcbSname);
        //ΪjcbSname�����������
        jcbSname.getEditor().getEditorComponent().addMouseListener(this);
        //ΪjcbSname��Ӽ��̼�����
        jcbSname.getEditor().getEditorComponent().addKeyListener(this);
        //ΪjcbSname����¼�������
        jcbSname.addActionListener(this);
        //���ð�ť��λ�ü���С���ӵ�JPanel
        jbQuery.setBounds(560, 105, 80, 30);
        jbQuery.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jbQuery);
        //Ϊ��ť����¼�����������������
        jbQuery.addActionListener(this);
        jbQuery.addMouseListener(this);
        jbApp.setBounds(665, 105, 100, 30);
        jbApp.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jp.add(jbApp);
        //��Ӽ�����
        jbApp.addMouseListener(this);
        jbApp.addActionListener(this);
    }

    public void initTray() //��ʼ���˵�
    {
        //��Ӳ˵�
        for (int i = 0; i < item.length; i++) {
            //Ϊ�˵�ע�������
            item[i].addActionListener(this);
            //Ϊ�˵���������ǰ3���˵�����ӵ�"�����˵�"
            if (i < 3) {
                jm.add(item[i]);
            }
        }
        //��������Ϣ�˵����˳�����˵���ӵ������˵�
        popup.add(jm);
        popup.add(item[3]);
        //�жϵ�ǰ����ϵͳ�Ƿ�֧��ϵͳ����
        if (SystemTray.isSupported()) {
            //�õ�ϵͳ����
            tray = SystemTray.getSystemTray();
            //����ͼ��
            Image image = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
            //����TrayIcon����õ�����ͼ��
            trayIcon = new TrayIcon(image, "��ʾ��Ϣ", popup);
            //��������ͼ�꽫�Զ����óߴ�
            trayIcon.setImageAutoSize(true);
            try {
                //������ͼ�����õ�ϵͳ������
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //Ϊ����ͼ��ע�������
            trayIcon.addActionListener(this);
        }
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //����������
                TrainTime.this.hide();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jcbMidStation || e.getSource() == jcbReverseStation)
        {//��������ѡ��ʱ
            if (jcbMidStation.isSelected() || jcbReverseStation.isSelected())
            {//��������һ����ѡ��ѡ��ʱ
                jrbArray[0].setSelected(true);
                jcbStart.requestFocus();
            }
        } else if (e.getSource() == jrbArray[0]) {
            jcbStart.requestFocus();
        } else if (e.getSource() == jrbArray[1]) {
            //��������ѡ������Ϊδѡ��״̬
            jcbMidStation.setSelected(false);
            jcbReverseStation.setSelected(false);
            jtfTname.requestFocus();
        } else if (e.getSource() == jrbArray[2]) {
            //��������ѡ������Ϊδѡ��״̬
            jcbMidStation.setSelected(false);
            jcbReverseStation.setSelected(false);
            jcbSname.requestFocus();
        } else if (e.getSource() == jcbStart.getEditor()) {
            //�������л����ı���jcbEnd
            jcbEnd.requestFocus();
        } else if (e.getSource() == jtfTname) {
            jbQuery.requestFocus();
        } else if (e.getSource() == jcbSname.getEditor()) {
            jbQuery.requestFocus();
        } else if (e.getSource() == jbQuery) {
            //�ж������Ƿ�Ϸ�  true-�Ϸ�  false-���Ϸ�
            boolean flag = this.isLegal();
            if (flag) {
                searchStation();
            }
        } else if (e.getSource() == trayIcon) {//˫������ͼ��ִ�еĴ���
            //�����尴ԭ���ķ�ʽ��ʾ����
            this.show(true);
        } else if (e.getSource() == item[3]) {//����˳�����˵�ִ�еĶ���
            //��������ȫ�˳�
            System.exit(0);
        } else if (e.getSource() == item[0]) {

            //����AddData������ʾ��Ƭtrain
            new AddData(this, true).showCard("train");
        } else if (e.getSource() == item[1]) {

            //����AddData������ʾ��Ƭstation
            new AddData(this, true).showCard("station");
        } else if (e.getSource() == item[2]) {

            //����AddData������ʾ��Ƭrelation
            new AddData(this, true).showCard("relation");
        } else if (e.getSource() == jbApp) {
            new AddData(this, true);//��ӹ���
        }
    }


    //������������Ƿ�Ϸ�
    public boolean isLegal() {
        //�õ�����վ�ı����ֵ
        JTextField jtfStart = (JTextField) jcbStart.getEditor().getEditorComponent();
        String start = jtfStart.getText().trim();//.trim�Զ�ʡ�Ե����Ŀո�
        //�õ�Ŀ��վ�ı����ֵ
        JTextField jtfEnd = (JTextField) jcbEnd.getEditor().getEditorComponent();
        String end = jtfEnd.getText().trim();
        //�õ���תվ�ı����ֵ
        JTextField jtfMid = (JTextField) jcbMid.getEditor().getEditorComponent();
        String mid = jtfMid.getText().trim();
        String tname = jtfTname.getText().trim();
        //�õ���վ�ı����ֵ
        JTextField jtfSname = (JTextField) jcbSname.getEditor().getEditorComponent();
        String sname = jtfSname.getText().trim();
        if (jrbArray[0].isSelected()) {//��վվ��ѯѡ����ʱ
            if (start.equals("")) {//����վΪ��ʱ
                //���������
                JOptionPane.showMessageDialog(this,
                        "����վ����Ϊ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (end.equals("")) {//Ŀ��վΪ��
                JOptionPane.showMessageDialog(this,
                        "Ŀ��վ����Ϊ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (start.equals(end)) {//����վ��Ŀ��վ��ͬ
                JOptionPane.showMessageDialog(this,
                        "����վ��Ŀ��վ������ͬ!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (start.equals(mid)) {//����վ����תվ��ͬ
                //����תվ����ѡ��ʱ
                if (jcbMidStation.isSelected()) {
                    JOptionPane.showMessageDialog(this,
                            "����վ����תվ������ͬ!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            if (mid.equals(end)) {//Ŀ��վ����תվ��ͬ
                //����תվ��ѡ��ѡ��ʱ
                if (jcbMidStation.isSelected()) {
                    JOptionPane.showMessageDialog(this,
                            "Ŀ��վ����תվ������ͬ!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } else if (jrbArray[1].isSelected()) {//����վ��ѯѡ��ʱ
            if (tname.equals("")) {//��������ı���Ϊ��
                JOptionPane.showMessageDialog(this,
                        "���β���Ϊ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else if (jrbArray[2].isSelected()) {//����վ���г��β�ѯѡ����ʱ
            if (sname.equals("")) {
                JOptionPane.showMessageDialog(this,
                        "��վ������Ϊ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public void searchStation() {
        if (jrbArray[0].isSelected()) {//վվ��ѯ
            if (jcbReverseStation.isSelected() && jcbMidStation.isSelected()) {//��������ѡ��ѡ��ʱ
                this.zzcxAndMid();
            } else if (jcbReverseStation.isSelected() && !jcbMidStation.isSelected()) {//������վѡ��,����תվʱ
                this.zzcx();
            } else if (!jcbReverseStation.isSelected() && jcbMidStation.isSelected()) {//����תվѡ��,����վδѡ��
                this.zzcxAndMid();
            } else {//������ѡ��δѡ��
                this.zzcx();
            }
        } else if (jrbArray[1].isSelected()) {//���β�ѯ
            this.trainSearch();
        } else if (jrbArray[2].isSelected()) {//��վ��ѯ
            this.stationSearch();
        }
    }

    public Vector<Vector> combine(Vector<Vector> temp,
                                  Vector<Vector> temp1, Vector<Vector> temp2) {//��������Vector��ϳ�һ��
        for (int i = 0; i < temp.size(); i++) {
            Vector v1 = temp.get(i);
            if (i < temp1.size()) {
                Vector v2 = temp1.get(i);
                //��V2�����Ԫ�ؼӵ�V1����
                for (int j = 0; j < v2.size(); j++) {
                    v1.add(v2.get(j));
                }
            } else {
                //û�й�ϵʱ��ӿ�
                v1.add("");
            }

        }
        for (int i = 0; i < temp.size(); i++) {
            Vector v1 = temp.get(i);
            if (i < temp2.size()) {
                Vector v2 = temp2.get(i);
                //��V2�����Ԫ�ؼӵ�V1����
                for (int j = 0; j < v2.size(); j++) {
                    v1.add(v2.get(j));
                }
            } else {
                //û�й�ϵʱ��ӿ�
                v1.add("");
            }

        }
        //Ѱ�Ҽ�¼�г���վ��Ŀ��վ��ͬ�ļ�¼
        for (int i = 0; i < temp.size(); i++) {
            Vector v1 = temp.get(i);
            //�õ�����վ
            String start = (String) v1.get(4);
            //�õ�Ŀ��վ
            String end = (String) v1.get(6);
            //������վ��Ŀ��վ��ͬʱ
            if (start.equals(end)) {
                //������վ����Ϊʼ��վ
                v1.set(4, v1.get(1));
                //��ѯ����ʱ��
                String sql = "select Rstarttime from relation " +
                        "where Tid=" +
                        "(select Tid from train " +
                        "where Tname='" + (String) v1.get(0) + "') " +
                        "and Sid=" +
                        "(select Sid from station " +
                        "where Sname='" + (String) v1.get(1) + "')";
                //�õ���������ʱ�������
                Vector<Vector> v2 = DBTrain.getVector(sql);
                Vector v3 = v2.get(0);
                //�õ�����ʱ�䲢���ó���վʱ��
                v1.set(5, v3.get(0));
            }
        }
        return temp;
    }

    public Vector<Vector> getSameVector(String start, String end)
    {
        //���ҳ���,ʼ��վ,�յ�վ�ͳ�����
        String sql = "select Tname, Tstartstation, Tterminus, Ttype from train, relation r1,relation r2, station  st1, station  st2 where r1.Tid=r2.Tid and train.Tid=r1.tid  and st1.Sname='"+start+"'   and st2.Sname='"+end+"'and r1.Sid=st1.Sid and r2.Sid=st2.Sid;";
        System.out.println(sql);

        /*���Զ�
        System.out.println(sql);
        try {
            ConnectAccess.Test(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        //�õ��йػ���Ϣ��Vector
        Vector<Vector> temp = DBTrain.getVector(sql);
//
        //������վ��ѯ��ťѡ��ʱ
        if (jcbReverseStation.isSelected()) {
            //��������վ��Ŀ��վ
            String str = start;
            start = end;
            end = str;
        }

        //���ҳ���վ�ͻ𳵿�����ʱ��
        String sql1 = "select st1.Sname,r1.Rstarttime FROM train, relation AS r1, relation AS r2, station AS st1, station AS st2 WHERE r1.Tid=r2.Tid and train.Tid=r1.tid and r1.Sid=st1.Sid and st1.Sname='"+start+"' and r2.Sid=st2.Sid and st2.Sname='"+end+"'";

        //�����յ�վ�ͻ𳵵�վʱ��
        String sql2 = "select st2.Sname,r2.Rarrivetime from train, relation r1,relation r2, station  st1, station  st2 where r1.Tid=r2.Tid and train.Tid=r1.tid and r1.Sid=st1.Sid and st1.Sname='"+start+"'  and r2.Sid=st2.Sid and st2.Sname='"+end+"';";

        //�õ��йػ�վ����Ϣ
        Vector<Vector> temp1 = DBTrain.getVector(sql1);
   //     System.out.println(temp1.elementAt(0));
        Vector<Vector> temp2 = DBTrain.getVector(sql2);
    //    System.out.println(temp2.elementAt(0));
        //����ѯ�����ϵ�һ��
        temp = combine(temp, temp1, temp2);
   //     System.out.println(temp.elementAt(0));

        return temp;
    }

    public void zzcx() {//վվ��ѯ
        //������ѡ��δѡ��
        JTextField jtfStart = (JTextField) jcbStart.getEditor().getEditorComponent();
        String start = jtfStart.getText().trim();
        //�õ�Ŀ��վ�ı����ֵ
        JTextField jtfEnd = (JTextField) jcbEnd.getEditor().getEditorComponent();
        String end = jtfEnd.getText().trim();
        //�õ��йؼ�¼��Vector
        Vector<Vector> temp = this.getSameVector(start, end);
        //ɾ��vector�в��Ϸ�������

       // Vector<Vector> v=temp;
        Vector<Vector> v = this.deleteData(temp);////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.updateTable(v);
        if (v.size() == 0) {
            //������Ϣ��ʾ�Ի���
            JOptionPane.showMessageDialog(this, "�Բ���,û����Ҫ�ҵĳ�!!!",
                    "��Ϣ��ʾ", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void zzcxAndMid() {//����תվѡ��,����վδѡ��
        //�õ�����վ�ı����ֵ
        JTextField jtfStart = (JTextField) jcbStart.getEditor().getEditorComponent();
        String start = jtfStart.getText().trim();
        //�õ�Ŀ��վ�ı����ֵ
        JTextField jtfEnd = (JTextField) jcbEnd.getEditor().getEditorComponent();
        String end = jtfEnd.getText().trim();
        //�õ���תվ�ı����ֵ
        JTextField jtfMid = (JTextField) jcbMid.getEditor().getEditorComponent();
        String mid = jtfMid.getText().trim();
        //���û���д��תվʱ
        if (!mid.equals("")) {
            //�õ��ӳ���վ����תվ�ĳ�����Ϣ
            Vector<Vector> qtemp = this.getSameVector(start, mid);
            //�õ�����תվ���յ�վ����Ϣ
            Vector<Vector> htemp = this.getSameVector(mid, end);
            //ɾ��qtemp�в��Ϸ�������
            Vector<Vector> vqtemp = this.deleteData(qtemp);
            //ɾ��htemp�в��Ϸ�������
            Vector<Vector> vhtemp = this.deleteData(htemp);
            int flag = 0;
            //�ж��Ƿ��м�¼,��Ϊ�մ���û�г��ɵ���
            if (vhtemp.size() == 0 || vqtemp.size() == 0) {
                flag++;
            }
            //��vqtemp�����Ԫ�ؼӵ�vhtemp����
            for (Vector v : vhtemp) {
                vqtemp.add(v);
            }
            //���ñ��ģ�͵�����������
            if (flag != 0) {
                //ȡ������ѡ�е��к���
                jt.clearSelection();
                dtm.setDataVector(new Vector(), vhead);
            } else {
                //ȡ������ѡ�е��к���
                jt.clearSelection();
                dtm.setDataVector(vqtemp, vhead);
            }
            //���±��
            this.jt.repaint();
            this.jt.updateUI();
            if (flag != 0) {
                //������Ϣ��ʾ�Ի���
                JOptionPane.showMessageDialog(this, "�Բ���,û����Ҫ�ҵĳ�!!!",
                        "��Ϣ��ʾ", JOptionPane.PLAIN_MESSAGE);
                return;
            }
        } else {//�û�û����д��תվ
            //�ҵ���վ֮���г��ĳ�
            Vector<String> midstation = DBTrain.getMidStation(start, end);
            //��Ҫ��ѯ����վ֮��û�г�ʱ
            if (midstation.size() == 0) {
                //������Ϣ��ʾ�Ի���
                JOptionPane.showMessageDialog(this, "�Բ���,û����תվ�ɹ�ѡ��!!!",
                        "��Ϣ��ʾ", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            //����������źϷ�����תվ��vector
            Vector<String> vlegal = new Vector<String>();
            Vector<Vector> vline = this.getSameVector(start, end);
            vline = this.deleteData(vline);
            //����վ֮����ֱ��ĳ�ʱֱ����ʾ
            if (vline.size() > 0) {
                //ȡ������ѡ�е��к���
                jt.clearSelection();
                //���ñ������
                dtm.setDataVector(vline, vhead);
                //���±��
                this.jt.repaint();
                this.jt.updateUI();
                return;
            } else {//����վ��û��ֱ��ĳ�ʱ
                for (String str : midstation) {
                    Vector<Vector> vstart = this.getSameVector(start, str);
                    Vector<Vector> vend = this.getSameVector(str, end);
                    vstart = this.deleteData(vstart);
                    vend = this.deleteData(vend);
                    if (vstart.size() != 0 && vend.size() != 0) {
                        vlegal.add(str);
                    }
                }
                if (vlegal.size() == 0) {
                    //������Ϣ��ʾ�Ի���
                    JOptionPane.showMessageDialog(this, "�Բ���,û����תվ�ɹ�ѡ��!!!",
                            "��Ϣ��ʾ", JOptionPane.PLAIN_MESSAGE);
                } else {
                    new PopupStation(vlegal, this, start, end);
                }
            }
        }
    }

    public void trainSearch() {//���β�ѯ

        String tname = this.jtfTname.getText().trim();
        //���ҳ���,ʼ��վ,�յ�վ�ͳ�����
        String sql = "select Tname,Tstartstation,Tterminus,Ttype " +
                "from train where Tname='" + tname + "'";
        //���ҳ���վ�ͻ𳵿�����ʱ��
        String sql1 = "select Tstartstation,Rstarttime from train,relation " +
                "where train.Tid=relation.Tid and " +
                "Tname='" + tname + "' and relation.Sid=" +
                "(select Sid from station " +
                "where Sname=train.Tstartstation)";
        //�����յ�վ�ͻ𳵵�վʱ��
        String sql2 = "select Tterminus,Rarrivetime from train,relation " +
                "where train.Tid=relation.Tid and " +
                "Tname='" + tname + "' and relation.Sid=" +
                "(select Sid from station " +
                "where Sname=train.Tterminus)";
        //�õ�����,ʼ��վ,�յ�վ�ͳ����͵�Vector
        Vector<Vector> temp = DBTrain.getVector(sql);
        //�õ�����վ�ͻ𳵿���ʱ���vector
        Vector<Vector> temp1 = DBTrain.getVector(sql1);
        //�õ��յ�վ�ͻ𳵵�վʱ���vector
        Vector<Vector> temp2 = DBTrain.getVector(sql2);
        temp = combine(temp, temp1, temp2);
        if (temp.size() == 0) {
            //������Ϣ��ʾ�Ի���
            JOptionPane.showMessageDialog(this, "�Բ���,û����Ҫ�ҵĳ�!!!",
                    "��Ϣ��ʾ", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        this.updateTable(temp);
    }

    public void stationSearch() {//��վ��ѯ
        //�õ�Ҫ��ѯ��վ����
        JTextField jtfSname = (JTextField) jcbSname.getEditor().getEditorComponent();
        String station = jtfSname.getText().trim();
        //��ѯ�йػ𳵵���Ϣ
        String sql = "select  distinct Tname,Tstartstation,Tterminus,Ttype from train,relation,station where train.Tid=relation.Tid and (relation.Sid=station.Sid or relation.Rid=station.Sid) and Sname='"+station+"';";

        System.out.println(sql);
        //��ѯ����վ������ʱ��
        String sql1 = "select '" + station + "',Rstarttime from relation "
                + "where Sid = " +
                "(select Sid from station where " +
                "Sname='" + station + "')";
        System.out.println(sql1);
        //��ѯĿ��վ����վʱ��
        String sql2 = "select Tterminus,Rarrivetime from train,relation" +
                " where train.Tid=relation.Tid " +
                " and relation.Sid=" +
                "(select Sid from station where " +
                "Sname=train.Tterminus) and Train.Tid in " +
                "(select Tid from relation where Sid in " +
                "(select Sid from station where " +
                "Sname='" + station + "'))";

        System.out.println(sql2);
        //�õ��й���Ϣ������
        Vector<Vector> temp = DBTrain.getVector(sql);
        Vector<Vector> temp1 = DBTrain.getVector(sql1);
        Vector<Vector> temp2 = DBTrain.getVector(sql2);
        //������Vector�����һ��
        temp = combine(temp, temp1, temp2);
        if (temp.size() == 0) {
            //������Ϣ��ʾ�Ի���
            JOptionPane.showMessageDialog(this, "�Բ���,û����Ҫ�ҵĳ�!!!",
                    "��Ϣ��ʾ", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        this.updateTable(temp);
    }


    public Vector<Vector> deleteData(Vector<Vector> vtemp) {
        Vector<Vector> temp = new Vector<Vector>();
        //ɾ��vtemp�в��Ϸ�������
        for (int i = 0; i < vtemp.size(); i++) {
            //�õ�temp�еĵ�i��Ԫ��
            Vector v1 = vtemp.get(i);
            //�õ��г�������
            String tname = (String) v1.get(0);
            //�õ�����վ
            String tstart = (String) v1.get(4);
            //�õ��յ�վ
            String tend = (String) v1.get(6);
            boolean flag = DBTrain.isLarger(tstart, tend, tname);
            if (flag) {
                temp.add(v1);
            }
        }
        return temp;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == jcbStart.getEditor().getEditorComponent()) {//������վ�õ�����ʱ
            //��վվ��ѯѡ��
            jrbArray[0].setSelected(true);
        } else if (e.getSource() == jcbEnd.getEditor().getEditorComponent()) {//��Ŀ��վ�õ�����ʱ
            //��վվ��ѯѡ��
            jrbArray[0].setSelected(true);
        } else if (e.getSource() == jcbMid.getEditor().getEditorComponent()) {//����תվ�õ�����ʱ
            //��վվ��ѯѡ��
            jrbArray[0].setSelected(true);
            //����תվ��ѡ��ѡ��
            jcbMidStation.setSelected(true);
        } else if (e.getSource() == jtfTname) {//�������ı���õ�����ʱ
            //�ó��β�ѯѡ��
            jrbArray[1].setSelected(true);
        } else if (e.getSource() == jcbSname.getEditor().getEditorComponent()) {//����վ��ѯ��õ�����ʱ
            //�ó�վ��ѯѡ��
            jrbArray[2].setSelected(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String str = "����վ������ĸ����";
        if (e.getSource() == jcbStart.getEditor().getEditorComponent()) {
            //������ʾ��Ϣ
            jcbStart.setToolTipText(str);
        } else if (e.getSource() == jcbEnd.getEditor().getEditorComponent()) {
            //������ʾ��Ϣ
            jcbEnd.setToolTipText(str);
        } else if (e.getSource() == jcbMid.getEditor().getEditorComponent()) {
            //������ʾ������Ϣ
            jcbMid.setToolTipText(str);
        } else if (e.getSource() == jcbSname.getEditor().getEditorComponent()) {
            //Ϊ�����б�������ʾ��Ϣ
            jcbSname.setToolTipText(str);
        } else if (e.getSource() == jtfTname) {
            //Ϊ�ı����������ʾ��Ϣ
            String s = "����Ҫ��ѯ���μ���";
            jtfTname.setToolTipText(s);
        } else if (e.getSource() == jt) {
            //Ϊ���������ʾ��Ϣ
            String s = "�������������Բ鿴��ϸ��Ϣ";
            jt.setToolTipText(s);
        } else if (e.getSource() == jbApp) {
            String s = "������ɲ����г�����վ��Ϣ";
            jbApp.setToolTipText(s);
        } else if (e.getSource() == jbQuery) {
            String s = "�����ð�ť���ɽ��в�ѯ";
            jbQuery.setToolTipText(s);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            int row = jt.getSelectedRow();
            if (row >= 0) {
                String tname = (String) jt.getValueAt(row, 0);
                new PopupRoute(tname);
            }

        }
    }

    public void keyReleased(KeyEvent e) {
        //�����jcbStart������ʱ
        if (e.getSource() == jcbStart.getEditor().getEditorComponent()) {
            //�õ��༭�����
            JTextField jtf = (JTextField) bcbeStart.getEditorComponent();
            //������Ĳ�ƥ����ĸʱ����
            if (!jtf.getText().trim().matches("[a-zA-Z]+")) {
                return;
            }
            //�õ��༭�����������
            String s = jtf.getText();
            //����setCurrentModel�õ������б�����������
            DBTrain.setCurrentModel(jtf.getText(), dcbmStart);
            jtf.setText(s);
            //���������б��Ŀɼ���Ϊtrue
            jcbStart.setPopupVisible(true);
        } else if (e.getSource() == jcbEnd.getEditor().getEditorComponent()) {
            //�õ��༭�����
            JTextField jtf = (JTextField) bcbeEnd.getEditorComponent();
            //������Ĳ�ƥ����ĸʱ����
            if (!jtf.getText().trim().matches("[a-zA-Z]+")) {
                return;
            }
            //�õ��༭�����������
            String s = jtf.getText();
            //����setCurrentModel�õ������б�����������
            DBTrain.setCurrentModel(jtf.getText(), dcbmEnd);
            jtf.setText(s);
            //���������б��Ŀɼ���Ϊtrue
            jcbEnd.setPopupVisible(true);
        } else if (e.getSource() == jcbMid.getEditor().getEditorComponent()) {
            //�õ��༭�����
            JTextField jtf = (JTextField) bcbeMid.getEditorComponent();
            //������Ĳ�ƥ����ĸʱ����
            if (!jtf.getText().trim().matches("[a-zA-Z]+")) {
                return;
            }
            //�õ��༭�����������
            String s = jtf.getText();
            //����setCurrentModel�õ������б�����������
            DBTrain.setCurrentModel(jtf.getText(), dcbmMid);
            jtf.setText(s);
            //���������б��Ŀɼ���Ϊtrue
            jcbMid.setPopupVisible(true);
        } else if (e.getSource() == jcbSname.getEditor().getEditorComponent()) {
            //�õ��༭�����
            JTextField jtf = (JTextField) bcbeSname.getEditorComponent();
            //������Ĳ�ƥ����ĸʱ����
            if (!jtf.getText().trim().matches("[a-zA-Z]+")) {
                return;
            }
            //�õ��༭�����������
            String s = jtf.getText();
            //����setCurrentModel�õ������б�����������
            DBTrain.setCurrentModel(jtf.getText(), dcbmSname);
            jtf.setText(s);
            //���������б��Ŀɼ���Ϊtrue
            jcbSname.setPopupVisible(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void updateTable(Vector<Vector> vt) {
        //ȡ������ѡ�е��к���
        jt.clearSelection();
        //���ñ������
        dtm.setDataVector(vt, vhead);
        //���±��
        jt.updateUI();
        jt.repaint();
    }

    public boolean getFlag() {
        boolean flag = this.jcbReverseStation.isSelected();
        return flag;
    }




    public static void main(String[] args) {
        new TrainTime();
    }
}