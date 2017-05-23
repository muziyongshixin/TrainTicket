import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddData extends JDialog implements ActionListener {
    //������ť
    private JButton jbTrain = new JButton("�������");
    private JButton jbStation = new JButton("��վ���");
    private JButton jbRelation = new JButton("��ϵ���");
    private JButton jbTsub = new JButton("�ύ");
    private JButton jbRsub = new JButton("�ύ");
    private JButton jbSsub = new JButton("�ύ");
    //�������
    private JPanel jpLeft = new JPanel();
    private JPanel jpRight = new JPanel();
    private JPanel jpTrain = new JPanel();
    private JPanel jpStation = new JPanel();
    private JPanel jpRelation = new JPanel();
    //������ǩ
    private JLabel jlTname = new JLabel("����");
    private JLabel jlTstartstation = new JLabel("ʼ��վ");
    private JLabel jlTterminus = new JLabel("�յ�վ");
    private JLabel jlTtype = new JLabel("�г�����");
    private JLabel jlSname = new JLabel("��վ����");
    private JLabel jlRstarttime = new JLabel("����ʱ��");
    private JLabel jlRarrivetime = new JLabel("��վʱ��");
    private JLabel jlRtname = new JLabel("����");
    private JLabel jlRsname = new JLabel("վ��");
    private JLabel jlSfs = new JLabel("��վ���");
    //�����ı���
    private JTextField jtfTname = new JTextField();
    private JTextField jtfTstartstation = new JTextField();
    private JTextField jtfTterminus = new JTextField();
    private JTextField jtfTtype = new JTextField();
    private JTextField jtfSname = new JTextField();
    private JTextField jtfRstarttime = new JTextField();
    private JTextField jtfRarrivetime = new JTextField();
    private JTextField jtfRtname = new JTextField();
    private JTextField jtfRsname = new JTextField();
    private JTextField jtfSfs = new JTextField();
    //�����ָ���
    private JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpLeft, jpRight);

    public AddData(TrainTime trian, boolean flag) {
        super(trian, flag);
        this.jpLeft.setLayout(null);
        this.jpRight.setLayout(new CardLayout());
        this.add(jsp);
        //���÷ָ�����λ��
        jsp.setDividerLocation(150);
        //���÷ָ����Ĵ�С
        jsp.setDividerSize(2);
        //����jbTrain�Ĵ�С�����
        jbTrain.setBounds(25, 75, 100, 30);
        jbTrain.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpLeft.add(jbTrain);
        //ע���¼�������
        jbTrain.addActionListener(this);
        //����jbStation�Ĵ�С�����
        jbStation.setBounds(25, 175, 100, 30);
        jbStation.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpLeft.add(jbStation);
        //ע���¼�������
        jbStation.addActionListener(this);
        //����jbRelation�Ĵ�С�����
        jbRelation.setBounds(25, 275, 100, 30);
        jbRelation.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpLeft.add(jbRelation);
        //ע���¼�������
        jbRelation.addActionListener(this);
        //��ʼ����Ƭ���
        initCardPanel();
        //��ӿ�Ƭ
        jpRight.add(jpTrain, "train");
        jpRight.add(jpStation, "station");
        jpRight.add(jpRelation, "relation");
        //���ش���ͼ��
        Image icon = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
        //���ô���ͼ��
        this.setIconImage(icon);
        this.setTitle("�г����");
        //���ô��ڱ���,��С,�ɼ���,�رն���
        this.setBounds(300, 100, 500, 450);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void initCardPanel() {
        //���ò���Ϊ��
        jpTrain.setLayout(null);
        jpStation.setLayout(null);
        jpRelation.setLayout(null);
        //���ó�����ǩ��С��λ�ò����
        jlTname.setBounds(50, 100, 80, 30);
        jlTname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jlTname);
        //���ó����ı����С��λ�ò����
        jtfTname.setBounds(110, 105, 100, 20);
        jtfTname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jtfTname);
        //���ó����ͱ�ǩ��С��λ�ò����
        jlTtype.setBounds(50, 150, 80, 30);
        jlTtype.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jlTtype);
        //���ó������ı����С��λ�ò����
        jtfTtype.setBounds(110, 155, 100, 20);
        jtfTtype.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jtfTtype);
        //����ʼ��վ��ǩ��С��λ�ò����
        jlTstartstation.setBounds(50, 200, 80, 30);
        jlTstartstation.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jlTstartstation);
        //����ʼ��վ�ı����С��λ�ò����
        jtfTstartstation.setBounds(110, 205, 100, 20);
        jtfTstartstation.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jtfTstartstation);
        //�����յ�վ��ǩ��С��λ�ò����
        jlTterminus.setBounds(50, 250, 80, 30);
        jlTterminus.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jlTterminus);
        //�����յ�վ�ı����С��λ�ò����
        jtfTterminus.setBounds(110, 255, 100, 20);
        jtfTterminus.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jtfTterminus);
        //����վ����ǩ��Сλ�ò���ʼ��
        jlSname.setBounds(50, 150, 80, 30);
        jlSname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpStation.add(jlSname);
        //����վ���ı����Сλ�ò���ʼ��
        jtfSname.setBounds(110, 155, 100, 20);
        jtfSname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpStation.add(jtfSname);
        //����վ����д��ǩ��Сλ�ò���ʼ��
        jlSfs.setBounds(50, 200, 80, 30);
        jlSfs.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpStation.add(jlSfs);
        //����վ���ı����Сλ�ò���ʼ��
        jtfSfs.setBounds(110, 205, 100, 20);
        jtfSfs.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpStation.add(jtfSfs);
        //���ó�����ǩ��С��λ�ò����
        jlRtname.setBounds(50, 100, 80, 30);
        jlRtname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jlRtname);
        //���ó����ı����С��λ�ò����
        jtfRtname.setBounds(110, 105, 100, 20);
        jtfRtname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jtfRtname);
        //����վ����ǩ��С��λ�ò����
        jlRsname.setBounds(50, 150, 80, 30);
        jlRsname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jlRsname);
        //����վ���ı����С��λ�ò����
        jtfRsname.setBounds(110, 155, 100, 20);
        jtfRsname.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jtfRsname);
        //���÷���ʱ���ǩ��С��λ�ò����
        jlRstarttime.setBounds(50, 250, 80, 30);
        jlRstarttime.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jlRstarttime);
        //���÷���ʱ���ı����С��λ�ò����
        jtfRstarttime.setBounds(110, 255, 100, 20);
        jtfRstarttime.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jtfRstarttime);
        //���õ�վʱ���ǩ��С��λ�ò����
        jlRarrivetime.setBounds(50, 200, 80, 30);
        jlRarrivetime.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jlRarrivetime);
        //���õ�վʱ���ı����С��λ�ò����
        jtfRarrivetime.setBounds(110, 205, 100, 20);
        jtfRarrivetime.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jtfRarrivetime);
        //����ȷ����ť��λ�ü���С��ע���¼�������
        jbTsub.setBounds(230, 300, 80, 30);
        jbTsub.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpTrain.add(jbTsub);
        jbTsub.addActionListener(this);
        //����ȷ����ť��λ�ü���С��ע���¼�������
        jbSsub.setBounds(230, 300, 80, 30);
        jbSsub.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpStation.add(jbSsub);
        jbSsub.addActionListener(this);
        //����ȷ����ť��λ�ü���С��ע���¼�������
        jbRsub.setBounds(230, 300, 80, 30);
        jbRsub.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        jpRelation.add(jbRsub);
        jbRsub.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //��ÿ�Ƭ���ֹ���������
        CardLayout cl = (CardLayout) jpRight.getLayout();
        if (e.getSource() == jbTrain) {//���³�����Ӱ�ť
            //��ʾ��Ƭtrain
            cl.show(jpRight, "train");
        } else if (e.getSource() == jbStation) {//���³�վ��Ӱ�ť
            cl.show(jpRight, "station");
        } else if (e.getSource() == jbRelation) {//���¹�ϵ��Ӱ�ť
            cl.show(jpRight, "relation");
        } else if (e.getSource() == jbTsub) {//���³����ύ��ť
            submitTrain();
        } else if (e.getSource() == jbSsub) {//���³�վ�ύ��ť
            submitStation();
        } else if (e.getSource() == jbRsub) {//���¹�ϵ�ύ��ť
            submitRelation();
        }
    }

    public void submitTrain() {
        //�õ��ı��������������
        String tname = jtfTname.getText().trim();
        String ttype = jtfTtype.getText().trim();
        String tstartstation = jtfTstartstation.getText().trim();
        String tterminus = jtfTterminus.getText().trim();
        if (tname.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��������Ϊ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (ttype.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();//����������ʾ��
            JOptionPane.showMessageDialog(this,"�����Ͳ���Ϊ��!!!",
            "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else if (tstartstation.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "ʼ��վ����Ϊ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else if (tterminus.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "�յ�վ����Ϊ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }

        //�ж�������г��Ƿ����
        String sql = "select Tid from train where Tname='" + tname + "'";
        boolean flag = DBTrain.isExist(sql);
        if (flag) {
            //����������Ϣ
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "�ó��Ѵ���,�������!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            return;
        }
        //�ж�ʼ��վ�Ƿ����
        String sqlstart = "select Sid from station where Sname='" + tstartstation + "'";
        boolean flagstart = DBTrain.isExist(sqlstart);
        if (!flagstart) {//��������
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��ʼ��վ������,������ӳ�վ!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            //��ÿ�Ƭ���ֹ���������
            CardLayout cl = (CardLayout) jpRight.getLayout();
            //��ת����վ��ӽ���
            cl.show(jpRight, "station");
            return;
        }
        //�ж��յ�վ�Ƿ����
        String sqlend = "select Sid from station where Sname='" + tterminus + "'";
        boolean flagend = DBTrain.isExist(sqlend);
        if (!flagend) {//��������
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "���յ�վ������,������ӳ�վ!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            //��ÿ�Ƭ���ֹ���������
            CardLayout cl = (CardLayout) jpRight.getLayout();
            //��ת����վ��ӽ���
            cl.show(jpRight, "station");
            return;
        }
        //�õ�Ҫ����վ��ID
        int tid = DBTrain.getInsertId("train", "Tid");
        //ִ�в������
        String sqlinsert = "insert into train values(" + tid + ",'" + tname +
                "','" + tstartstation + "','" + tterminus + "','" + ttype + "')";
        int k = DBTrain.insert(sqlinsert);
        if (k == 0) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "�Բ���,����ʧ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��ϲ��,�����ɹ�!!!",
                    "INFO", JOptionPane.PLAIN_MESSAGE);

            //���ı������
            this.jtfTname.setText("");
            this.jtfTstartstation.setText("");
            this.jtfTterminus.setText("");
            this.jtfTtype.setText("");
        }
    }

    public void submitStation() {
        //�õ��ı�������
        String sname = jtfSname.getText().trim();
        String sfs = jtfSfs.getText().trim();
        if (sname.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "վ������Ϊ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else if (sfs.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "����Ʋ���Ϊ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else if (!sfs.matches("[a-zA-Z]+")) {
            //������ƥ��Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��վ���ֻ��������ĸ!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }
        //�ж�ʼ��վ�Ƿ����
        String sqlsname = "select Sid from station where Sname='" + sname + "'";
        boolean flagsname = DBTrain.isExist(sqlsname);
        //����վ����
        if (flagsname) {
            //����������Ϣ
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��վ�Ѵ���,�������!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            return;
        } else {//��������,����������վ
            //�õ�Ҫ����վ��ID
            int sid = DBTrain.getInsertId("station", "Sid");
            String temp = "insert into station values(" + sid + ",'" + sname + "','" + sfs + "')";
            //ִ�в������
            int m = DBTrain.insert(temp);
            if (m == 0) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "�Բ���,����ʧ��!!!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);

                return;
            } else {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "��ϲ��,�����ɹ�!!!",
                        "INFO", JOptionPane.PLAIN_MESSAGE);

                //���ı������
                jtfSname.setText("");
                jtfSfs.setText("");
            }
        }
    }


    public void submitRelation() {
        //�õ��ı��������
        String rtname = jtfRtname.getText().trim();
        String rsname = jtfRsname.getText().trim();
        String rstarttime = jtfRstarttime.getText().trim();
        String rarrivetime = jtfRarrivetime.getText().trim();
        if (rtname.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��������Ϊ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }
        if (rsname.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "վ������Ϊ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }
        if (rstarttime.equals("") && rarrivetime.equals("")) {
            //��������Ի���
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��վʱ��ͷ���ʱ�䲻��ͬʱΪ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }
        //�ж��г��Ƿ����
        String sqltname = "select Tid from train where Tname='" + rtname + "'";
        boolean flagtname = DBTrain.isExist(sqltname);
        //���ó�������
        if (!flagtname) {
            //����������Ϣ
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "�ó�������,�������!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            return;
        }
        //�жϳ�վ�Ƿ����
        String sqlsname = "select Sid from station where Sname='" + rsname + "'";
        boolean flagsname = DBTrain.isExist(sqlsname);
        //����վ������
        if (!flagsname) {
            //����������Ϣ
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��վ������,�������!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            return;
        }
        //�õ�Ҫ����Ĺ�ϵID
        int rid = DBTrain.getInsertId("relation", "Rid");
        String sqltid = "select Tid from train where Tname='" + rtname + "'";
        String sqlsid = "select Sid from station where Sname='" + rsname + "'";
        //�õ�Ҫ���복��ID
        int tid = DBTrain.getExistId(sqltid);
        //�õ�Ҫ���복վ��ID
        int sid = DBTrain.getExistId(sqlsid);
        String sqlrel = "insert into relation values(" + tid + "," + sid + "," +
                rid + ",'" + rarrivetime + "','" + rstarttime + "')";
        //ִ�в������
        int m = DBTrain.insert(sqlrel);
        if (m == 0) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "�Բ���,����ʧ��!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "��ϲ��,�����ɹ�!!!",
                    "INFO", JOptionPane.PLAIN_MESSAGE);

            //���ı������
            jtfRtname.setText("");
            jtfRsname.setText("");
            jtfRarrivetime.setText("");
            jtfRstarttime.setText("");
        }
    }

    public void showCard(String card) {
        //��ÿ�Ƭ���ֹ���������
        CardLayout cl = (CardLayout) jpRight.getLayout();
        //��ת����վ��ӽ���
        cl.show(jpRight, card);
    }


//	public static void main(String args[])
//	{
//		new AddData().showCard("station");
//	}
//

}