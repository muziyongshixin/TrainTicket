import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddData extends JDialog implements ActionListener {
    //创建按钮
    private JButton jbTrain = new JButton("车次添加");
    private JButton jbStation = new JButton("车站添加");
    private JButton jbRelation = new JButton("关系添加");
    private JButton jbTsub = new JButton("提交");
    private JButton jbRsub = new JButton("提交");
    private JButton jbSsub = new JButton("提交");
    //创建面板
    private JPanel jpLeft = new JPanel();
    private JPanel jpRight = new JPanel();
    private JPanel jpTrain = new JPanel();
    private JPanel jpStation = new JPanel();
    private JPanel jpRelation = new JPanel();
    //创建标签
    private JLabel jlTname = new JLabel("车名");
    private JLabel jlTstartstation = new JLabel("始发站");
    private JLabel jlTterminus = new JLabel("终点站");
    private JLabel jlTtype = new JLabel("列车类型");
    private JLabel jlSname = new JLabel("车站名称");
    private JLabel jlRstarttime = new JLabel("开车时间");
    private JLabel jlRarrivetime = new JLabel("到站时间");
    private JLabel jlRtname = new JLabel("车名");
    private JLabel jlRsname = new JLabel("站名");
    private JLabel jlSfs = new JLabel("车站简称");
    //创建文本框
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
    //创建分割条
    private JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpLeft, jpRight);

    public AddData(TrainTime trian, boolean flag) {
        super(trian, flag);
        this.jpLeft.setLayout(null);
        this.jpRight.setLayout(new CardLayout());
        this.add(jsp);
        //设置分割条的位置
        jsp.setDividerLocation(150);
        //设置分割条的大小
        jsp.setDividerSize(2);
        //设置jbTrain的大小并添加
        jbTrain.setBounds(25, 75, 100, 30);
        jbTrain.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpLeft.add(jbTrain);
        //注册事件监听器
        jbTrain.addActionListener(this);
        //设置jbStation的大小并添加
        jbStation.setBounds(25, 175, 100, 30);
        jbStation.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpLeft.add(jbStation);
        //注册事件监听器
        jbStation.addActionListener(this);
        //设置jbRelation的大小并添加
        jbRelation.setBounds(25, 275, 100, 30);
        jbRelation.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpLeft.add(jbRelation);
        //注册事件监听器
        jbRelation.addActionListener(this);
        //初始化卡片面板
        initCardPanel();
        //添加卡片
        jpRight.add(jpTrain, "train");
        jpRight.add(jpStation, "station");
        jpRight.add(jpRelation, "relation");
        //加载窗体图标
        Image icon = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
        //设置窗体图标
        this.setIconImage(icon);
        this.setTitle("列车添加");
        //设置窗口标题,大小,可见性,关闭动作
        this.setBounds(300, 100, 500, 450);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void initCardPanel() {
        //设置布局为空
        jpTrain.setLayout(null);
        jpStation.setLayout(null);
        jpRelation.setLayout(null);
        //设置车名标签大小及位置并添加
        jlTname.setBounds(50, 100, 80, 30);
        jlTname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jlTname);
        //设置车名文本框大小及位置并添加
        jtfTname.setBounds(110, 105, 100, 20);
        jtfTname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jtfTname);
        //设置车类型标签大小及位置并添加
        jlTtype.setBounds(50, 150, 80, 30);
        jlTtype.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jlTtype);
        //设置车类型文本框大小及位置并添加
        jtfTtype.setBounds(110, 155, 100, 20);
        jtfTtype.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jtfTtype);
        //设置始发站标签大小及位置并添加
        jlTstartstation.setBounds(50, 200, 80, 30);
        jlTstartstation.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jlTstartstation);
        //设置始发站文本框大小及位置并添加
        jtfTstartstation.setBounds(110, 205, 100, 20);
        jtfTstartstation.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jtfTstartstation);
        //设置终点站标签大小及位置并添加
        jlTterminus.setBounds(50, 250, 80, 30);
        jlTterminus.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jlTterminus);
        //设置终点站文本框大小及位置并添加
        jtfTterminus.setBounds(110, 255, 100, 20);
        jtfTterminus.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jtfTterminus);
        //设置站名标签大小位置并初始化
        jlSname.setBounds(50, 150, 80, 30);
        jlSname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpStation.add(jlSname);
        //设置站名文本框大小位置并初始化
        jtfSname.setBounds(110, 155, 100, 20);
        jtfSname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpStation.add(jtfSname);
        //设置站名简写标签大小位置并初始化
        jlSfs.setBounds(50, 200, 80, 30);
        jlSfs.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpStation.add(jlSfs);
        //设置站名文本框大小位置并初始化
        jtfSfs.setBounds(110, 205, 100, 20);
        jtfSfs.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpStation.add(jtfSfs);
        //设置车名标签大小及位置并添加
        jlRtname.setBounds(50, 100, 80, 30);
        jlRtname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jlRtname);
        //设置车名文本框大小及位置并添加
        jtfRtname.setBounds(110, 105, 100, 20);
        jtfRtname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jtfRtname);
        //设置站名标签大小及位置并添加
        jlRsname.setBounds(50, 150, 80, 30);
        jlRsname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jlRsname);
        //设置站名文本框大小及位置并添加
        jtfRsname.setBounds(110, 155, 100, 20);
        jtfRsname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jtfRsname);
        //设置发车时间标签大小及位置并添加
        jlRstarttime.setBounds(50, 250, 80, 30);
        jlRstarttime.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jlRstarttime);
        //设置发车时间文本框大小及位置并添加
        jtfRstarttime.setBounds(110, 255, 100, 20);
        jtfRstarttime.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jtfRstarttime);
        //设置到站时间标签大小及位置并添加
        jlRarrivetime.setBounds(50, 200, 80, 30);
        jlRarrivetime.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jlRarrivetime);
        //设置到站时间文本框大小及位置并添加
        jtfRarrivetime.setBounds(110, 205, 100, 20);
        jtfRarrivetime.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jtfRarrivetime);
        //设置确定按钮的位置及大小并注册事件监听器
        jbTsub.setBounds(230, 300, 80, 30);
        jbTsub.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpTrain.add(jbTsub);
        jbTsub.addActionListener(this);
        //设置确定按钮的位置及大小并注册事件监听器
        jbSsub.setBounds(230, 300, 80, 30);
        jbSsub.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpStation.add(jbSsub);
        jbSsub.addActionListener(this);
        //设置确定按钮的位置及大小并注册事件监听器
        jbRsub.setBounds(230, 300, 80, 30);
        jbRsub.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jpRelation.add(jbRsub);
        jbRsub.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获得卡片布局管理器引用
        CardLayout cl = (CardLayout) jpRight.getLayout();
        if (e.getSource() == jbTrain) {//按下车次添加按钮
            //显示卡片train
            cl.show(jpRight, "train");
        } else if (e.getSource() == jbStation) {//按下车站添加按钮
            cl.show(jpRight, "station");
        } else if (e.getSource() == jbRelation) {//按下关系添加按钮
            cl.show(jpRight, "relation");
        } else if (e.getSource() == jbTsub) {//按下车次提交按钮
            submitTrain();
        } else if (e.getSource() == jbSsub) {//按下车站提交按钮
            submitStation();
        } else if (e.getSource() == jbRsub) {//按下关系提交按钮
            submitRelation();
        }
    }

    public void submitTrain() {
        //得到文本框中输入的内容
        String tname = jtfTname.getText().trim();
        String ttype = jtfTtype.getText().trim();
        String tstartstation = jtfTstartstation.getText().trim();
        String tterminus = jtfTterminus.getText().trim();
        if (tname.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "车名不能为空!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (ttype.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();//发出错误提示音
            JOptionPane.showMessageDialog(this,"车类型不能为空!!!",
            "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else if (tstartstation.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "始发站不能为空!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else if (tterminus.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "终点站不能为空!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }

        //判断输入的列车是否存在
        String sql = "select Tid from train where Tname='" + tname + "'";
        boolean flag = DBTrain.isExist(sql);
        if (flag) {
            //弹出警告信息
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "该车已存在,不可添加!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            return;
        }
        //判断始发站是否存在
        String sqlstart = "select Sid from station where Sname='" + tstartstation + "'";
        boolean flagstart = DBTrain.isExist(sqlstart);
        if (!flagstart) {//若不存在
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "该始发站不存在,请先添加车站!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            //获得卡片布局管理器引用
            CardLayout cl = (CardLayout) jpRight.getLayout();
            //跳转到车站添加界面
            cl.show(jpRight, "station");
            return;
        }
        //判断终点站是否存在
        String sqlend = "select Sid from station where Sname='" + tterminus + "'";
        boolean flagend = DBTrain.isExist(sqlend);
        if (!flagend) {//若不存在
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "该终点站不存在,请先添加车站!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            //获得卡片布局管理器引用
            CardLayout cl = (CardLayout) jpRight.getLayout();
            //跳转到车站添加界面
            cl.show(jpRight, "station");
            return;
        }
        //得到要查入站的ID
        int tid = DBTrain.getInsertId("train", "Tid");
        //执行插入操作
        String sqlinsert = "insert into train values(" + tid + ",'" + tname +
                "','" + tstartstation + "','" + tterminus + "','" + ttype + "')";
        int k = DBTrain.insert(sqlinsert);
        if (k == 0) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "对不起,插入失败!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "恭喜你,操作成功!!!",
                    "INFO", JOptionPane.PLAIN_MESSAGE);

            //将文本框清空
            this.jtfTname.setText("");
            this.jtfTstartstation.setText("");
            this.jtfTterminus.setText("");
            this.jtfTtype.setText("");
        }
    }

    public void submitStation() {
        //得到文本框内容
        String sname = jtfSname.getText().trim();
        String sfs = jtfSfs.getText().trim();
        if (sname.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "站名不能为空!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else if (sfs.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "车简称不能为空!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else if (!sfs.matches("[a-zA-Z]+")) {
            //弹出不匹配对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "车站简称只可输入字母!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }
        //判断始发站是否存在
        String sqlsname = "select Sid from station where Sname='" + sname + "'";
        boolean flagsname = DBTrain.isExist(sqlsname);
        //若该站存在
        if (flagsname) {
            //弹出警告信息
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "该站已存在,不可添加!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            return;
        } else {//若不存在,则向表中添加站
            //得到要插入站的ID
            int sid = DBTrain.getInsertId("station", "Sid");
            String temp = "insert into station values(" + sid + ",'" + sname + "','" + sfs + "')";
            //执行插入操作
            int m = DBTrain.insert(temp);
            if (m == 0) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "对不起,插入失败!!!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);

                return;
            } else {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "恭喜你,操作成功!!!",
                        "INFO", JOptionPane.PLAIN_MESSAGE);

                //将文本框清空
                jtfSname.setText("");
                jtfSfs.setText("");
            }
        }
    }


    public void submitRelation() {
        //得到文本框的内容
        String rtname = jtfRtname.getText().trim();
        String rsname = jtfRsname.getText().trim();
        String rstarttime = jtfRstarttime.getText().trim();
        String rarrivetime = jtfRarrivetime.getText().trim();
        if (rtname.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "车名不能为空!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }
        if (rsname.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "站名不能为空!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }
        if (rstarttime.equals("") && rarrivetime.equals("")) {
            //弹出错误对话框
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "到站时间和发车时间不可同时为空!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        }
        //判断列车是否存在
        String sqltname = "select Tid from train where Tname='" + rtname + "'";
        boolean flagtname = DBTrain.isExist(sqltname);
        //若该车不存在
        if (!flagtname) {
            //弹出警告信息
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "该车不存在,不可添加!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            return;
        }
        //判断车站是否存在
        String sqlsname = "select Sid from station where Sname='" + rsname + "'";
        boolean flagsname = DBTrain.isExist(sqlsname);
        //若该站不存在
        if (!flagsname) {
            //弹出警告信息
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "该站不存在,不可添加!!!",
                    "WARNING", JOptionPane.WARNING_MESSAGE);

            return;
        }
        //得到要插入的关系ID
        int rid = DBTrain.getInsertId("relation", "Rid");
        String sqltid = "select Tid from train where Tname='" + rtname + "'";
        String sqlsid = "select Sid from station where Sname='" + rsname + "'";
        //得到要插入车的ID
        int tid = DBTrain.getExistId(sqltid);
        //得到要插入车站的ID
        int sid = DBTrain.getExistId(sqlsid);
        String sqlrel = "insert into relation values(" + tid + "," + sid + "," +
                rid + ",'" + rarrivetime + "','" + rstarttime + "')";
        //执行插入操作
        int m = DBTrain.insert(sqlrel);
        if (m == 0) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "对不起,插入失败!!!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

            return;
        } else {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "恭喜你,操作成功!!!",
                    "INFO", JOptionPane.PLAIN_MESSAGE);

            //将文本框清空
            jtfRtname.setText("");
            jtfRsname.setText("");
            jtfRarrivetime.setText("");
            jtfRstarttime.setText("");
        }
    }

    public void showCard(String card) {
        //获得卡片布局管理器引用
        CardLayout cl = (CardLayout) jpRight.getLayout();
        //跳转到车站添加界面
        cl.show(jpRight, card);
    }


//	public static void main(String args[])
//	{
//		new AddData().showCard("station");
//	}
//

}