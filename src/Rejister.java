import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ����־ on 2016/5/5.
 */
public class Rejister extends JDialog implements ActionListener //ע�����
{

    public static int sum=1;//��¼�����Դ򿪵Ĵ��ڸ���
    private  JLabel name,password;
    private  JTextField wbk;//�ı���
    private  JTextField mmk;// �����
    private  JButton rej;//ע�ᰴť
    public  static  int sw=0;//�ж�����ͨ�û����ǹ���Ա

    public Rejister(Login lg,boolean flag)
    {
        super(lg,flag);
        name=new JLabel("�û�����");
        name.setBounds(10,50,70,30);
        name.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        password=new JLabel("���룺");
        password.setBounds(10,100,50,30);
        password.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        wbk=new JTextField();
        wbk.setBounds(85,50,130,30);
        wbk.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        mmk=new JTextField();
        mmk.setBounds(85,100,130,30);
        mmk.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        rej=new JButton("ע��");
        rej.setBounds(130,160,70,30);
        rej.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        rej.addActionListener(this);

        this.add(name);
        this.add(password);
        this.add(wbk);
        this.add(mmk);
        this.add(rej);
        this.setLayout(null);

        this.setTitle("�û�ע��");
        this.setIconImage((new ImageIcon("./picture/trian1.jpg")).getImage());
        this.setSize(230, 250);
        this.setLocation(600, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==rej)
        {
            String name=wbk.getText().trim();
            String key=mmk.getText().trim();
            if(!(name.equals(""))&&!(key.equals("")))//����û����������򶼲�Ϊ��
            {
                String sql="select Aname from account where Aname='"+name+"'and Atype="+sw+";";
                String exist=DBTrain.account(sql);
                if(exist.equals(""))
                {
                    String sql1="insert into account (Aname,Apassword,Atype) values('"+name+"','"+key+"',"+sw+")";
                    System.out.println(sql1);
                    DBTrain.account(sql1);
                    JOptionPane.showMessageDialog(this,
                            "ע��ɹ�������", "congratulations", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "���û����Ѵ���", "Errors", JOptionPane.ERROR_MESSAGE);


                }
            }
            else//���������û�����Ϊ��
            {
                JOptionPane.showMessageDialog(this,
                        "�û�����������Ϊ�գ�����", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }




//    public static  void main(String arg[])
//    {
//
//        Rejister rej1=new Rejister(lg,);
//    }
}
