import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

/**
 * Created by ????�?? on 2016/5/5.
 */
public class Login extends JFrame implements ActionListener,MouseListener{
    JPanel mb1,mb2,mb3;//?��??
    //user������
    JLabel bq1;//north
    JLabel an1,an2,an3;
    JTabbedPane xxk;//middle//??项�??
    JLabel bq2,bq3,bq4,bq5;
    JTextField wbk;
    JPasswordField mmk;
    JButton an4;
    JCheckBox fxk1,fxk2;

//������admin�������

    JLabel ad2,ad3,ad4,ad5;
    JTextField adwbk;
    JPasswordField admmk;
    JButton adan4;
    JCheckBox adfxk1,adfxk2;




    public Login()
    {

        /*ͼƬ��С����*/
        ImageIcon t = new ImageIcon("./picture/login1.png");
        Image image = t.getImage();
        Image newing = image.getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        ImageIcon a = new ImageIcon(newing);
        Image newing2 = image.getScaledInstance(30, 10, Image.SCALE_SMOOTH);
        ImageIcon b = new ImageIcon(newing2);


        bq2 = new JLabel("�û���", JLabel.CENTER);
        bq2.setFont(new Font("΢���ź�", Font.PLAIN, 16));//��������ʹ�С
        bq2.setBounds(0,0,100,30);

        bq3 = new JLabel("����", JLabel.CENTER);
        bq3.setFont(new Font("΢���ź�", Font.PLAIN, 16));//��������ʹ�С
        bq3.setBounds(0,30,100,30);

        bq4 = new JLabel("ע���˺�");
        bq4.setFont(new Font("΢���ź�", Font.PLAIN, 15));//��������ʹ�С
        bq4.setForeground(Color.red);
        bq4.setBounds(275,40,100,30);
        bq4.addMouseListener(this);
        bq4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//���ָ��������

        bq5 = new URLLabel("https://kyfw.12306.cn/otn/forgetPassword/initforgetMyPassword","�һ�����");//?��?��????�???��???��?��??
        bq5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�????�??�???��????????
        bq5.setBounds(275,65,100,30);
        bq5.setFont(new Font("����", Font.PLAIN, 14));


        wbk = new JTextField("�ÿ͵�¼�����˺�����");
        wbk.setBounds(100,3,150,30);
        wbk.addMouseListener(this);
        mmk = new JPasswordField();
        mmk.setBounds(100,35,150,30);
        mmk.addMouseListener(this);
        an4 = new JButton("��¼");
        an4.setBounds(275,3,75,30);
        an4.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        an4.addActionListener(this);
        fxk1 = new JCheckBox("�ÿ͵�½");
        fxk1.setBounds(20,65,100,30);
        fxk1.setFont(new Font("����", Font.PLAIN, 14));
        fxk2 = new JCheckBox("��ס����");
        fxk2.setBounds(150,65,100,30);
        fxk2.setFont(new Font("����", Font.PLAIN, 14));

        bq1 = new JLabel(a);//�ϲ���ͼƬ
        mb1 = new JPanel();//�ײ���������ť
        an1 = new URLLabel("http://www.12306.cn/mormhweb/khmail/","���ⷴ��");
        an1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        an1.setFont(new Font("΢���ź�", Font.PLAIN, 13));//��������ʹ�С
        an1.setForeground(Color.gray);

        an2 =  new URLLabel("http://www.12306.cn","�汾��Ϣ");
        an2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        an2.setFont(new Font("΢���ź�", Font.PLAIN, 13));//��������ʹ�С
        an2.setForeground(Color.gray);
        an3 =new URLLabel("http://www.12306.cn/mormhweb/gljd/gywm/","��ϵ����");
        an3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        an3.setFont(new Font("΢���ź�", Font.PLAIN, 13));//��������ʹ�С
        an3.setForeground(Color.gray);



        ad2 = new JLabel("����Ա�˺�", JLabel.CENTER);
        ad2.setFont(new Font("΢���ź�", Font.PLAIN, 16));//��������ʹ�С
        ad2.setBounds(0,0,100,30);
        ad3 = new JLabel("����Ա����", JLabel.CENTER);
        ad3.setFont(new Font("΢���ź�", Font.PLAIN, 16));//��������ʹ�С
        ad3.setBounds(0,30,100,30);
        ad4 = new JLabel("ע�����Ա");
        ad4.setFont(new Font("΢���ź�", Font.PLAIN, 15));//��������ʹ�С
        ad4.setForeground(Color.red);
        ad4.setBounds(275,40,100,30);
        ad4.addMouseListener(this);
        ad4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//���ָ��������
        ad5 = new URLLabel("https://kyfw.12306.cn/otn/forgetPassword/initforgetMyPassword","�һ�����");//?��?��????�???��???��?��??
        ad5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//���ָ��������
        ad5.setBounds(275,65,100,30);
        ad5.setFont(new Font("����", Font.PLAIN, 14));

        adwbk = new JTextField();
        adwbk.setBounds(100,3,150,30);
        admmk = new JPasswordField();
        admmk.setBounds(100,35,150,30);
        admmk.addMouseListener(this);
        adan4 = new JButton("����Ա��¼");
        adan4.setBounds(265,3,110,30);
        adan4.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        adan4.addActionListener(this);
 //       adfxk1 = new JCheckBox("�ÿ͵�½");
 //       adfxk1.setBounds(20,65,100,30);
        adfxk2 = new JCheckBox("��ס����");
        adfxk2.setBounds(100,65,100,30);
        adfxk2.setFont(new Font("����", Font.PLAIN, 14));

        xxk = new JTabbedPane();//middle
        mb2 = new JPanel();
        mb3 = new JPanel();


        xxk.add("�û�", mb2);
        xxk.add("����Ա", mb3);
        xxk.setFont(new Font("����", Font.PLAIN, 14));


        mb1.add(an1);
        mb1.add(an2);
        mb1.add(an3);
        mb1.setLayout(new FlowLayout());

        mb2.add(bq2);
        mb2.add(wbk);
        mb2.add(an4);
        mb2.add(bq3);
        mb2.add(mmk);
        mb2.add(bq4);
        mb2.add(fxk1);
        mb2.add(fxk2);
        mb2.add(bq5);
      //  mb2.setLayout(new GridLayout(3, 3));
        mb2.setLayout(null);

        mb3.add(ad2);
        mb3.add(adwbk);
        mb3.add(adan4);
        mb3.add(ad3);
        mb3.add(admmk);
        mb3.add(ad4);
 //       mb3.add(adfxk1);
        mb3.add(adfxk2);
        mb3.add(ad5);
        mb3.setLayout(null);

        this.add(bq1, BorderLayout.NORTH);
        this.add(mb1, BorderLayout.SOUTH);
        this.add(xxk, BorderLayout.CENTER);


        this.setTitle("��½ϵͳ");
        this.setIconImage((new ImageIcon("./picture/trian1.jpg")).getImage());
        this.setSize(400, 270);
        this.setLocation(500, 300);
        this.setResizable(true);//�??�?�????�???�大�??
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    public  boolean Account_verification(String name,String key, int sw)//�˻���֤ swΪѡ���û����ǹ���Ա
    {
        switch (sw)
        {
            case 0:{
                String sql0="select Apassword from account where Atype=0 and Aname='"+name+"'";
                String get_password=DBTrain.account(sql0);
                if(get_password.equals(key))
                    return true;
                else
                    return false;
            }
            case 1:
            {
                String sql1="select Apassword from account where Atype=1 and Aname='"+name+"'";
                String get_password=DBTrain.account(sql1);
                if(get_password.equals(key))
                    return true;
                else
                    return false;
            }
            default:
                return false;
        }
    }



    public  boolean isLegal(int sw)//����ı��������Ƿ�Ϸ�
    {
        switch (sw)
        {
            case 0:
            {

                String username = wbk.getText().trim();//.trim�Զ�ʡ�Ե����Ŀո�
                String userkey=mmk.getText().trim();
                System.out.println(username);
                if (username.equals("")) {//����վΪ��ʱ
                    //���������
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this,
                            "�û�������Ϊ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    return false;
                }
                if (userkey.equals("")) {//Ŀ��վΪ��
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this,
                            "���벻��Ϊ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    return false;
                } else {
                    boolean allow = this.Account_verification(username, userkey, 0);//����Ϸ��������˻���֤����֤ͨ�����ɵ�½
                    if (allow)
                        return true;
                    else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this,
                                "��֤ʧ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                        return false;

                    }
                }

            }
            case 1: {
                String adm_name = adwbk.getText().trim();//.trim�Զ�ʡ�Ե����Ŀո�
                String adm_key = admmk.getText().trim();
                System.out.println(adm_name);
                System.out.println(adm_key);
                if (adm_name.equals("")) {//����ԱΪ��ʱ
                    //���������
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this,
                            "����Ա�˻�����Ϊ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    return false;
                }
                if (adm_key.equals("")) {//�����Ϊ��
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this,
                            "����Ա���벻��Ϊ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    return false;
                } else {
                    boolean allow = this.Account_verification(adm_name, adm_key, 1);//�˻���֤����֤ͨ�����ɵ�½
                    if (allow)
                        return true;
                    else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this,
                                "��֤ʧ��!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                        return false;

                    }
                }

            }
            default:
                return false;
        }

    }

    public void save_account(String name,String key,int sw)//����������û���
    {
        switch (sw)
        {
            case 0:
            {
                String sql="select Uname from user where Utype=0 and Uname='"+name+"'";
                System.out.println(sql);
                String exist=DBTrain.account(sql);
                if(exist.equals(""))
                {
                    String  sql1="insert into user (Uname,Ukey,Utype) values('"+name+"','"+key+"',0)";
                    System.out.println(sql1);
                    DBTrain.account(sql1);
                }
                break;
            }
            case 1:
            {
                String sql="select Uname from user where Utype=1 and Uname='"+name+"'";
                System.out.println(sql);
                String exist=DBTrain.account(sql);
                if(exist.equals(""))
                {
                    String  sql1="insert into user (Uname,Ukey,Utype) values('"+name+"','"+key+"',1)";
                    System.out.println(sql1);
                    DBTrain.account(sql1);
                }
                break;
            }
        }

    }

    public void delet_account(String name,String key,int sw)//ɾ���û����߹���Ա���������
    {
        switch (sw)
        {
            case 0:
            {
                String sql="select Uname from user where Utype=0 and Uname='"+name+"'";
                String exist=DBTrain.account(sql);
                if(!(exist.equals("")))
                {
                    String  sql1="delete from user where Utype=0 and Uname='"+name+"'";
                    System.out.println(sql1);
                    DBTrain.account(sql1);
                }
                break;
            }
            case 1:
            {
                System.out.println("ɾ������Ա��Ϣ1");
                String sql="select Uname from user where Utype=1 and Uname='"+name+"'";
                String exist=DBTrain.account(sql);
                System.out.println("ɾ������Ա��Ϣ2"+exist+name);
                if(!(exist.equals("")))
                {
                    String  sql1="delete from user where Utype=1 and Uname='"+name+"'";
                    System.out.println(sql1);
                    DBTrain.account(sql1);
                }
                break;

            }
        }

    }
    public void FillPassowrd(int sw)//�Զ��������
    {
       switch (sw)
       {
           case 0://��ͨ�û���¼�����Զ���д����
           {
               String name=wbk.getText().trim();
               String sql="select Uname from user where Utype=0 and Uname='"+name+"'";
               String exist=DBTrain.account(sql);
               if(!(exist.equals("")))//������ݿ��б���������
               {
                   String sql1="select Ukey from user where Utype=0 and Uname='"+name+"'";
                   String key=DBTrain.account(sql1);
                   mmk.setText(key);
                   fxk2.setSelected(true);
               }
               break;
           }
           case 1://����Ա��¼�����Զ���д����
           {
               String name=adwbk.getText().trim();
               String sql="select Uname from user where Utype=1 and Uname='"+name+"'";
               String exist=DBTrain.account(sql);
               if(!(exist.equals("")))//������ݿ��б���������
               {
                   String sql1="select Ukey from user where Utype=1 and Uname='"+name+"'";
                   String key=DBTrain.account(sql1);
                   admmk.setText(key);
                   adfxk2.setSelected(true);
               }
               break;

           }
       }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==an4)
        {
           if(fxk1.isSelected())//�Ƿ�Ϊ�ÿ͵�½���ÿ͵�½����Ҫ�û��������룩
           {
               TrainTime train0=new TrainTime(0);
               this.dispose();//�رյ�½����
           }
           else//�����û���¼
           {
               boolean flag=this.isLegal(0);//���������˻���֤
               if(flag)
               {
                   TrainTime train1=new TrainTime(0);//��֤ͨ������ϵͳ
                   this.dispose();//�رյ�½����
                   if(fxk2.isSelected())
                   {
                       String name=wbk.getText().trim();
                       String key=mmk.getText().trim();
                       this.save_account(name,key,0);
                   }
                   else
                   {

                       String name=wbk.getText().trim();
                       String key=mmk.getText().trim();
                       this.delet_account(name,key,0);


                   }

               }

           }
        }
        else if(e.getSource()==adan4)//����Ա��¼
        {
            boolean flag=this.isLegal(1);
            if(flag)
            {
                TrainTime train2=new TrainTime(1);

                if(adfxk2.isSelected())
                {
                    String name=adwbk.getText().trim();
                    String key=admmk.getText().trim();
                    this.save_account(name,key,1);
                }
                else
                {
                    System.out.println("ɾ������Ա��Ϣ");
                    String name=adwbk.getText().trim();
                    String key=admmk.getText().trim();
                    this.delet_account(name,key,1);

                }

                this.dispose();//�رյ�½����
            }
        }

    }

    public static void main(String arg[]) {
        Login lg1 = new Login();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == mmk) {
            System.out.println("mimakuang");
            this.FillPassowrd(0);//�Զ���д����
        }
        else if(e.getSource()==wbk)
        {
            wbk.setText("");
        }
        else if(e.getSource()==admmk)
        {
            this.FillPassowrd(1);
        }
        else if(e.getSource()==bq4)//ע���˻�
        {
            if(Rejister.sum>0)
            {
               // Rejister.sum=0;//����һ�����Դ򿪵Ĵ�����
                Rejister.sw=0;
                boolean flag=true;
                Rejister rej0=new Rejister(this,flag);
            }

        }
        else if(e.getSource()==ad4)//ע�����Ա
        {

          //  Rejister.sum=0;//����һ�����Դ򿪵Ĵ�����
              Rejister.sw=1;
              boolean flag=true;
              Rejister rej1=new Rejister(this,flag);


        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
















class URLLabel extends JLabel implements MouseListener
{
    //private static final long serialVersionUID = 2194613519655846206L;
    private String ustr;

    public static void main(String[] args) {
        ToolTipManager.sharedInstance().setInitialDelay(0);
        JFrame f=new JFrame("URLLabel test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l=new URLLabel("http://zhidao.baidu.com/question/130767272.html","�ٶ�֪��");
        f.add(l,"South");
        f.setSize(345,123);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    public URLLabel (String ustr,String ttt)
    {
        this.ustr=ustr;
        this.setText(ttt);
        this.setForeground(Color.blue);//����������ɫ
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//���������ʽ
        this.setToolTipText(ttt);//������ʾ����
        this.addMouseListener(this);
    }

    //���ʱ��Ĭ����������ָ����ҳ�档
    public void mouseClicked(MouseEvent e) {
        try {
            Desktop.getDesktop().browse(new URI(ustr));
        } catch (Exception e1) { e.paramString();}
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }




}
