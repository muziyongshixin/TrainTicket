import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 李勇志 on 2016/5/5.
 */
public class Rejister extends JDialog implements ActionListener //注册界面
{

    public static int sum=1;//记录还可以打开的窗口个数
    private  JLabel name,password;
    private  JTextField wbk;//文本框
    private  JTextField mmk;// 密码框
    private  JButton rej;//注册按钮
    public  static  int sw=0;//判断是普通用户还是管理员

    public Rejister(Login lg,boolean flag)
    {
        super(lg,flag);
        name=new JLabel("用户名：");
        name.setBounds(10,50,70,30);
        name.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        password=new JLabel("密码：");
        password.setBounds(10,100,50,30);
        password.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        wbk=new JTextField();
        wbk.setBounds(85,50,130,30);
        wbk.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        mmk=new JTextField();
        mmk.setBounds(85,100,130,30);
        mmk.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        rej=new JButton("注册");
        rej.setBounds(130,160,70,30);
        rej.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        rej.addActionListener(this);

        this.add(name);
        this.add(password);
        this.add(wbk);
        this.add(mmk);
        this.add(rej);
        this.setLayout(null);

        this.setTitle("用户注册");
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
            if(!(name.equals(""))&&!(key.equals("")))//如果用户名框和密码框都不为空
            {
                String sql="select Aname from account where Aname='"+name+"'and Atype="+sw+";";
                String exist=DBTrain.account(sql);
                if(exist.equals(""))
                {
                    String sql1="insert into account (Aname,Apassword,Atype) values('"+name+"','"+key+"',"+sw+")";
                    System.out.println(sql1);
                    DBTrain.account(sql1);
                    JOptionPane.showMessageDialog(this,
                            "注册成功！！！", "congratulations", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "该用户名已存在", "Errors", JOptionPane.ERROR_MESSAGE);


                }
            }
            else//密码框或者用户名框为空
            {
                JOptionPane.showMessageDialog(this,
                        "用户名或者密码为空！！！", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }




//    public static  void main(String arg[])
//    {
//
//        Rejister rej1=new Rejister(lg,);
//    }
}
