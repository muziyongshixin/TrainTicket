import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

/**
 * Created by ????锟?? on 2016/5/5.
 */
public class Login extends JFrame implements ActionListener,MouseListener{
    JPanel mb1,mb2,mb3;//?锟斤拷??
    //user面板组件
    JLabel bq1;//north
    JLabel an1,an2,an3;
    JTabbedPane xxk;//middle//??椤癸拷??
    JLabel bq2,bq3,bq4,bq5;
    JTextField wbk;
    JPasswordField mmk;
    JButton an4;
    JCheckBox fxk1,fxk2;

//下面是admin面板的组件

    JLabel ad2,ad3,ad4,ad5;
    JTextField adwbk;
    JPasswordField admmk;
    JButton adan4;
    JCheckBox adfxk1,adfxk2;




    public Login()
    {

        /*图片大小调整*/
        ImageIcon t = new ImageIcon("./picture/login1.png");
        Image image = t.getImage();
        Image newing = image.getScaledInstance(400, 70, Image.SCALE_SMOOTH);
        ImageIcon a = new ImageIcon(newing);
        Image newing2 = image.getScaledInstance(30, 10, Image.SCALE_SMOOTH);
        ImageIcon b = new ImageIcon(newing2);


        bq2 = new JLabel("用户名", JLabel.CENTER);
        bq2.setFont(new Font("微软雅黑", Font.PLAIN, 16));//设置字体和大小
        bq2.setBounds(0,0,100,30);

        bq3 = new JLabel("密码", JLabel.CENTER);
        bq3.setFont(new Font("微软雅黑", Font.PLAIN, 16));//设置字体和大小
        bq3.setBounds(0,30,100,30);

        bq4 = new JLabel("注册账号");
        bq4.setFont(new Font("微软雅黑", Font.PLAIN, 15));//设置字体和大小
        bq4.setForeground(Color.red);
        bq4.setBounds(275,40,100,30);
        bq4.addMouseListener(this);
        bq4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标指向变成手型

        bq5 = new URLLabel("https://kyfw.12306.cn/otn/forgetPassword/initforgetMyPassword","找回密码");//?锟斤拷?锟斤拷????锟???锟斤拷???锟斤拷?锟斤拷??
        bq5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//锟????锟??锟???锟斤拷????????
        bq5.setBounds(275,65,100,30);
        bq5.setFont(new Font("楷体", Font.PLAIN, 14));


        wbk = new JTextField("访客登录无需账号密码");
        wbk.setBounds(100,3,150,30);
        wbk.addMouseListener(this);
        mmk = new JPasswordField();
        mmk.setBounds(100,35,150,30);
        mmk.addMouseListener(this);
        an4 = new JButton("登录");
        an4.setBounds(275,3,75,30);
        an4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        an4.addActionListener(this);
        fxk1 = new JCheckBox("访客登陆");
        fxk1.setBounds(20,65,100,30);
        fxk1.setFont(new Font("楷体", Font.PLAIN, 14));
        fxk2 = new JCheckBox("记住密码");
        fxk2.setBounds(150,65,100,30);
        fxk2.setFont(new Font("楷体", Font.PLAIN, 14));

        bq1 = new JLabel(a);//上部的图片
        mb1 = new JPanel();//底部的三个按钮
        an1 = new URLLabel("http://www.12306.cn/mormhweb/khmail/","问题反馈");
        an1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        an1.setFont(new Font("微软雅黑", Font.PLAIN, 13));//设置字体和大小
        an1.setForeground(Color.gray);

        an2 =  new URLLabel("http://www.12306.cn","版本信息");
        an2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        an2.setFont(new Font("微软雅黑", Font.PLAIN, 13));//设置字体和大小
        an2.setForeground(Color.gray);
        an3 =new URLLabel("http://www.12306.cn/mormhweb/gljd/gywm/","联系我们");
        an3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        an3.setFont(new Font("微软雅黑", Font.PLAIN, 13));//设置字体和大小
        an3.setForeground(Color.gray);



        ad2 = new JLabel("管理员账号", JLabel.CENTER);
        ad2.setFont(new Font("微软雅黑", Font.PLAIN, 16));//设置字体和大小
        ad2.setBounds(0,0,100,30);
        ad3 = new JLabel("管理员密码", JLabel.CENTER);
        ad3.setFont(new Font("微软雅黑", Font.PLAIN, 16));//设置字体和大小
        ad3.setBounds(0,30,100,30);
        ad4 = new JLabel("注册管理员");
        ad4.setFont(new Font("微软雅黑", Font.PLAIN, 15));//设置字体和大小
        ad4.setForeground(Color.red);
        ad4.setBounds(275,40,100,30);
        ad4.addMouseListener(this);
        ad4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标指向变成手型
        ad5 = new URLLabel("https://kyfw.12306.cn/otn/forgetPassword/initforgetMyPassword","找回密码");//?锟斤拷?锟斤拷????锟???锟斤拷???锟斤拷?锟斤拷??
        ad5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标指向变成手型
        ad5.setBounds(275,65,100,30);
        ad5.setFont(new Font("楷体", Font.PLAIN, 14));

        adwbk = new JTextField();
        adwbk.setBounds(100,3,150,30);
        admmk = new JPasswordField();
        admmk.setBounds(100,35,150,30);
        admmk.addMouseListener(this);
        adan4 = new JButton("管理员登录");
        adan4.setBounds(265,3,110,30);
        adan4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        adan4.addActionListener(this);
 //       adfxk1 = new JCheckBox("访客登陆");
 //       adfxk1.setBounds(20,65,100,30);
        adfxk2 = new JCheckBox("记住密码");
        adfxk2.setBounds(100,65,100,30);
        adfxk2.setFont(new Font("楷体", Font.PLAIN, 14));

        xxk = new JTabbedPane();//middle
        mb2 = new JPanel();
        mb3 = new JPanel();


        xxk.add("用户", mb2);
        xxk.add("管理员", mb3);
        xxk.setFont(new Font("宋体", Font.PLAIN, 14));


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


        this.setTitle("登陆系统");
        this.setIconImage((new ImageIcon("./picture/trian1.jpg")).getImage());
        this.setSize(400, 270);
        this.setLocation(500, 300);
        this.setResizable(true);//锟??姝?锟????锟???锟藉ぇ锟??
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    public  boolean Account_verification(String name,String key, int sw)//账户验证 sw为选择用户还是管理员
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



    public  boolean isLegal(int sw)//检查文本框输入是否合法
    {
        switch (sw)
        {
            case 0:
            {

                String username = wbk.getText().trim();//.trim自动省略掉最后的空格
                String userkey=mmk.getText().trim();
                System.out.println(username);
                if (username.equals("")) {//出发站为空时
                    //弹出错误框
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this,
                            "用户名不能为空!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    return false;
                }
                if (userkey.equals("")) {//目的站为空
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this,
                            "密码不能为空!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    return false;
                } else {
                    boolean allow = this.Account_verification(username, userkey, 0);//输入合法即进行账户验证，验证通过即可登陆
                    if (allow)
                        return true;
                    else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this,
                                "验证失败!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                        return false;

                    }
                }

            }
            case 1: {
                String adm_name = adwbk.getText().trim();//.trim自动省略掉最后的空格
                String adm_key = admmk.getText().trim();
                System.out.println(adm_name);
                System.out.println(adm_key);
                if (adm_name.equals("")) {//管理员为空时
                    //弹出错误框
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this,
                            "管理员账户不能为空!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    return false;
                }
                if (adm_key.equals("")) {//密码框为空
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this,
                            "管理员密码不能为空!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    return false;
                } else {
                    boolean allow = this.Account_verification(adm_name, adm_key, 1);//账户验证，验证通过即可登陆
                    if (allow)
                        return true;
                    else {
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(this,
                                "验证失败!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

                        return false;

                    }
                }

            }
            default:
                return false;
        }

    }

    public void save_account(String name,String key,int sw)//保存密码和用户名
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

    public void delet_account(String name,String key,int sw)//删除用户或者管理员保存的密码
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
                System.out.println("删除管理员信息1");
                String sql="select Uname from user where Utype=1 and Uname='"+name+"'";
                String exist=DBTrain.account(sql);
                System.out.println("删除管理员信息2"+exist+name);
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
    public void FillPassowrd(int sw)//自动填充密码
    {
       switch (sw)
       {
           case 0://普通用户登录界面自动填写密码
           {
               String name=wbk.getText().trim();
               String sql="select Uname from user where Utype=0 and Uname='"+name+"'";
               String exist=DBTrain.account(sql);
               if(!(exist.equals("")))//如果数据库中保存有密码
               {
                   String sql1="select Ukey from user where Utype=0 and Uname='"+name+"'";
                   String key=DBTrain.account(sql1);
                   mmk.setText(key);
                   fxk2.setSelected(true);
               }
               break;
           }
           case 1://管理员登录界面自动填写密码
           {
               String name=adwbk.getText().trim();
               String sql="select Uname from user where Utype=1 and Uname='"+name+"'";
               String exist=DBTrain.account(sql);
               if(!(exist.equals("")))//如果数据库中保存有密码
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
           if(fxk1.isSelected())//是否为访客登陆（访客登陆不需要用户名和密码）
           {
               TrainTime train0=new TrainTime(0);
               this.dispose();//关闭登陆界面
           }
           else//正常用户登录
           {
               boolean flag=this.isLegal(0);//输入检查与账户验证
               if(flag)
               {
                   TrainTime train1=new TrainTime(0);//验证通过进入系统
                   this.dispose();//关闭登陆界面
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
        else if(e.getSource()==adan4)//管理员登录
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
                    System.out.println("删除管理员信息");
                    String name=adwbk.getText().trim();
                    String key=admmk.getText().trim();
                    this.delet_account(name,key,1);

                }

                this.dispose();//关闭登陆界面
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
            this.FillPassowrd(0);//自动填写密码
        }
        else if(e.getSource()==wbk)
        {
            wbk.setText("");
        }
        else if(e.getSource()==admmk)
        {
            this.FillPassowrd(1);
        }
        else if(e.getSource()==bq4)//注册账户
        {
            if(Rejister.sum>0)
            {
               // Rejister.sum=0;//减少一个可以打开的窗口数
                Rejister.sw=0;
                boolean flag=true;
                Rejister rej0=new Rejister(this,flag);
            }

        }
        else if(e.getSource()==ad4)//注册管理员
        {

          //  Rejister.sum=0;//减少一个可以打开的窗口数
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
        JLabel l=new URLLabel("http://zhidao.baidu.com/question/130767272.html","百度知道");
        f.add(l,"South");
        f.setSize(345,123);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    public URLLabel (String ustr,String ttt)
    {
        this.ustr=ustr;
        this.setText(ttt);
        this.setForeground(Color.blue);//设置链接颜色
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置鼠标样式
        this.setToolTipText(ttt);//设置提示文字
        this.addMouseListener(this);
    }

    //点击时打开默认浏览器浏览指定的页面。
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
