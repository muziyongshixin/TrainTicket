import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.plaf.basic.*;

public class TrainTime extends JFrame implements ActionListener, MouseListener, ListSelectionListener, KeyListener {
    //创建描述表格标题的vector
    private Vector<String> vhead = new Vector<String>();

    {
        vhead.add("车次");
        vhead.add("始发站");
        vhead.add("终点站");
        vhead.add("列车类型");
        vhead.add("发站");
        vhead.add("发时");
        vhead.add("到站");
        vhead.add("到时");
    }

    //创建包含表格数据的vector
    private Vector<Vector> vdata = new Vector<Vector>();
    //创建表格型对象
    private DefaultTableModel dtm = new DefaultTableModel(vdata, vhead);
    //创建表格控件
    private JTable jt = new JTable(dtm);
    //将表格控件放到滚动窗体中
    private JScrollPane jspTable = new JScrollPane(jt);
    //创建表格的行排序器
    TableRowSorter sorter = new TableRowSorter(dtm);
    //创建面板,用来查询
    private JPanel jp = new JPanel();
    //创建窗体分割
    private JSplitPane jsp = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT, jspTable, jp);//类似于Box容器，JSplitPane容器允许我们在单行或单列中显示组件。然而Box可以包含任意数量的组件，JSplitPane只可以用来显示两个组件。组件可以变化尺寸并通过一个可移动的分隔栏进行分隔。分隔栏可以使得用户可以通过拖拽分隔栏来调整所包含组件的尺寸

    //创建单选按钮数组
    private JRadioButton[] jrbArray = {
            new JRadioButton("站站查询", true),
            new JRadioButton("车次查询"),
            new JRadioButton("车站所有车次查询")};
    //创建单选按钮组
    private ButtonGroup bgStation = new ButtonGroup();
    //创建复选框
    private JCheckBox jcbMidStation = new JCheckBox("中转站");
    private JCheckBox jcbReverseStation = new JCheckBox("反向站站查询");
    //创建标签和文本框
    private JLabel jlStart = new JLabel("出发站:");
    private JComboBox jcbStart = new JComboBox();
    private JLabel jlEnd = new JLabel("目的站:");
    private JComboBox jcbEnd = new JComboBox();
    private JLabel jlMid = new JLabel("中转站:");
    private JComboBox jcbMid = new JComboBox();
    private JLabel jlTid = new JLabel("车次:");
    private JTextField jtfTname = new JTextField();
    private JLabel jlSid = new JLabel("车站站名:");
    private JComboBox jcbSname = new JComboBox();
    //创建按钮
    private JButton jbQuery = new JButton("查询");
    private JButton jbApp = new JButton("附加功能");
    //创建可编辑组合框的默认编辑器
    private BasicComboBoxEditor bcbeStart = new BasicComboBoxEditor();
    //创建组合框的默认模型
    private DefaultComboBoxModel dcbmStart = new DefaultComboBoxModel();
    private BasicComboBoxEditor bcbeEnd = new BasicComboBoxEditor();
    private DefaultComboBoxModel dcbmEnd = new DefaultComboBoxModel();
    private BasicComboBoxEditor bcbeMid = new BasicComboBoxEditor();
    private DefaultComboBoxModel dcbmMid = new DefaultComboBoxModel();
    private BasicComboBoxEditor bcbeSname = new BasicComboBoxEditor();
    private DefaultComboBoxModel dcbmSname = new DefaultComboBoxModel();
    //创建弹出菜单
    PopupMenu popup = new PopupMenu();
    //创建菜单
    Menu jm = new Menu("Add");
    //创建菜单项数组
    MenuItem[] item = {new MenuItem("Add train"), new MenuItem("Add Station"),
            new MenuItem("Add Relation"), new MenuItem("EXIT")};
    //定义SystemTray成员变量
    SystemTray tray;//系统托盘
    //定义TrayIcon成员变量
    TrayIcon trayIcon;


    public TrainTime(int sw)
    {
        this();
        if(sw==0)
            jp.remove(jbApp);


    }

    public TrainTime()//构造方法
    {
        this.add(jsp);
        //设置分隔条的位置
        jsp.setDividerLocation(400);
        //设置分隔条大小
        jsp.setDividerSize(2);
        //为表格添加排序器
        jt.setRowSorter(sorter);
        jt.setRowHeight(20);//设置表格行高
        //为表格选择模型注册监听器
        jt.getSelectionModel().addListSelectionListener(this);
        jt.addMouseListener(this);
        //设置表头不可编辑
        jt.getTableHeader().setReorderingAllowed(false);
        //设置JPanel布局为空
        jp.setLayout(null);
        //初始化JRadioButton数组
        initJRadioButton();
        //初始化复选框
        initJCheckBox();
        //初始化文本框
        initJTextField();
        //初始化系统托盘
        initTray();
        //加载窗体图标
        Image icon = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
        //设置窗体图标
        this.setIconImage(icon);
        //设置窗体的名称
        this.setTitle("火车票查询系统");
        //使最大化按钮不可用
        this.setResizable(false);
        //设置窗体的大小及位置
        this.setBounds(120, 50, 800, 600);
        //设置窗体的可见性
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出
    }

    public void initJRadioButton()//初始化按钮
    {
        for (int i = 0; i < jrbArray.length; i++) {
            //设置单选按钮的大小
            jrbArray[i].setBounds(150, 50 + i * 30, 150, 30);
            jrbArray[i].setFont(new Font("微软雅黑", Font.PLAIN, 14));
            //将单选按钮加到JPanel中
            jp.add(jrbArray[i]);
            //将单选按钮加到按钮组里面
            bgStation.add(jrbArray[i]);
            //注册事件监听器
            jrbArray[i].addActionListener(this);
        }
    }

    public void initJCheckBox() //初始化复选框
    {
        //设置复选框的大小
        jcbReverseStation.setBounds(350, 50, 130, 30);
        jcbReverseStation.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //将复选框加到JPanel中
        jp.add(jcbReverseStation);
        //注册事件监听器
        jcbReverseStation.addActionListener(this);
        jcbMidStation.setBounds(520, 50, 80, 30);
        jcbMidStation.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jcbMidStation);
        jcbMidStation.addActionListener(this);
    }

    public void initJTextField()//初始化文本域
    {
        //设置标签大小并将它加入到JPanel
        jlStart.setBounds(150, 20, 50, 30);
        jlStart.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jlStart);
        //设置jcbStart的大小及位置
        jcbStart.setBounds(210, 25, 80, 20);
        jcbStart.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //设置jcbStart为可编辑状态
        jcbStart.setEditable(true);
        //为jcbStart设置编辑器
        jcbStart.setEditor(bcbeStart);
        //为jcbStart设置model
        jcbStart.setModel(dcbmStart);
        jp.add(jcbStart);
        //为文本框注册事件监听器
//		jcbStart.addActionListener(this);
        //注册鼠标事件监听器
        jcbStart.getEditor().getEditorComponent().addMouseListener(this);
        //注册键盘监听器
        jcbStart.getEditor().getEditorComponent().addKeyListener(this);
        jlEnd.setBounds(330, 20, 50, 30);
        jlEnd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jlEnd);
        jcbEnd.setBounds(390, 25, 80, 20);
        jcbEnd.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //将jcbEnd设置为可编辑
        jcbEnd.setEditable(true);
        //为jcbEnd设置editor及model
        jcbEnd.setEditor(bcbeEnd);
        jcbEnd.setModel(dcbmEnd);
        jp.add(jcbEnd);
        //为jcbEnd添加鼠标和键盘监听器
        jcbEnd.getEditor().getEditorComponent().addMouseListener(this);
        jcbEnd.getEditor().getEditorComponent().addKeyListener(this);
        jlMid.setBounds(510, 20, 50, 30);
        jlMid.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jlMid);
        jcbMid.setBounds(570, 25, 80, 20);
        jcbMid.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //将jcbMid设置为可编辑
        jcbMid.setEditable(true);
        //为jcbMid设置editor及model
        jcbMid.setEditor(bcbeMid);
        jcbMid.setModel(dcbmMid);
        jp.add(jcbMid);
        //为jcbMid添加鼠标和键盘监听器
        jcbMid.getEditor().getEditorComponent().addMouseListener(this);
        jcbMid.getEditor().getEditorComponent().addKeyListener(this);
        jlTid.setBounds(350, 80, 40, 30);
        jlTid.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jlTid);
        jtfTname.setBounds(420, 85, 80, 20);
        jtfTname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jtfTname);
        //为jtfTname添加鼠标监听器
        jtfTname.addMouseListener(this);
        //为jtfTname添加事件监听器
        jtfTname.addActionListener(this);
        jlSid.setBounds(350, 110, 60, 30);
        jlSid.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jlSid);
        jcbSname.setBounds(420, 115, 80, 20);
        jcbSname.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        //将jcbSname设置为可编辑
        jcbSname.setEditable(true);
        //为jcbSname设置editor及model
        jcbSname.setEditor(bcbeSname);
        jcbSname.setModel(dcbmSname);
        jp.add(jcbSname);
        //为jcbSname添加鼠标监听器
        jcbSname.getEditor().getEditorComponent().addMouseListener(this);
        //为jcbSname添加键盘监听器
        jcbSname.getEditor().getEditorComponent().addKeyListener(this);
        //为jcbSname添加事件监听器
        jcbSname.addActionListener(this);
        //设置按钮的位置及大小并加到JPanel
        jbQuery.setBounds(560, 105, 80, 30);
        jbQuery.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jbQuery);
        //为按钮添加事件监听器和鼠标监听器
        jbQuery.addActionListener(this);
        jbQuery.addMouseListener(this);
        jbApp.setBounds(665, 105, 100, 30);
        jbApp.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        jp.add(jbApp);
        //添加监听器
        jbApp.addMouseListener(this);
        jbApp.addActionListener(this);
    }

    public void initTray() //初始化菜单
    {
        //添加菜单
        for (int i = 0; i < item.length; i++) {
            //为菜单注册监听器
            item[i].addActionListener(this);
            //为菜单项数组中前3个菜单项添加到"弹出菜单"
            if (i < 3) {
                jm.add(item[i]);
            }
        }
        //将弹出消息菜单与退出程序菜单添加到弹出菜单
        popup.add(jm);
        popup.add(item[3]);
        //判断当前操作系统是否支持系统托盘
        if (SystemTray.isSupported()) {
            //得到系统托盘
            tray = SystemTray.getSystemTray();
            //加载图像
            Image image = Toolkit.getDefaultToolkit().getImage("./picture/trian1.jpg");
            //创建TrayIcon对象得到托盘图标
            trayIcon = new TrayIcon(image, "提示信息", popup);
            //设置托盘图标将自动设置尺寸
            trayIcon.setImageAutoSize(true);
            try {
                //将托盘图标设置到系统托盘中
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //为托盘图标注册监听器
            trayIcon.addActionListener(this);
        }
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //将窗体隐藏
                TrainTime.this.hide();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jcbMidStation || e.getSource() == jcbReverseStation)
        {//当单击复选框时
            if (jcbMidStation.isSelected() || jcbReverseStation.isSelected())
            {//当至少有一个复选框选中时
                jrbArray[0].setSelected(true);
                jcbStart.requestFocus();
            }
        } else if (e.getSource() == jrbArray[0]) {
            jcbStart.requestFocus();
        } else if (e.getSource() == jrbArray[1]) {
            //将两个复选框设置为未选中状态
            jcbMidStation.setSelected(false);
            jcbReverseStation.setSelected(false);
            jtfTname.requestFocus();
        } else if (e.getSource() == jrbArray[2]) {
            //将两个复选框设置为未选中状态
            jcbMidStation.setSelected(false);
            jcbReverseStation.setSelected(false);
            jcbSname.requestFocus();
        } else if (e.getSource() == jcbStart.getEditor()) {
            //将焦点切换到文本框jcbEnd
            jcbEnd.requestFocus();
        } else if (e.getSource() == jtfTname) {
            jbQuery.requestFocus();
        } else if (e.getSource() == jcbSname.getEditor()) {
            jbQuery.requestFocus();
        } else if (e.getSource() == jbQuery) {
            //判断输入是否合法  true-合法  false-不合法
            boolean flag = this.isLegal();
            if (flag) {
                searchStation();
            }
        } else if (e.getSource() == trayIcon) {//双击托盘图标执行的代码
            //将窗体按原来的方式显示出来
            this.show(true);
        } else if (e.getSource() == item[3]) {//点击退出程序菜单执行的动作
            //结束程序安全退出
            System.exit(0);
        } else if (e.getSource() == item[0]) {

            //创建AddData对象并显示卡片train
            new AddData(this, true).showCard("train");
        } else if (e.getSource() == item[1]) {

            //创建AddData对象并显示卡片station
            new AddData(this, true).showCard("station");
        } else if (e.getSource() == item[2]) {

            //创建AddData对象并显示卡片relation
            new AddData(this, true).showCard("relation");
        } else if (e.getSource() == jbApp) {
            new AddData(this, true);//添加功能
        }
    }


    //用来检查输入是否合法
    public boolean isLegal() {
        //得到出发站文本框的值
        JTextField jtfStart = (JTextField) jcbStart.getEditor().getEditorComponent();
        String start = jtfStart.getText().trim();//.trim自动省略掉最后的空格
        //得到目的站文本框的值
        JTextField jtfEnd = (JTextField) jcbEnd.getEditor().getEditorComponent();
        String end = jtfEnd.getText().trim();
        //得到中转站文本框的值
        JTextField jtfMid = (JTextField) jcbMid.getEditor().getEditorComponent();
        String mid = jtfMid.getText().trim();
        String tname = jtfTname.getText().trim();
        //得到车站文本框的值
        JTextField jtfSname = (JTextField) jcbSname.getEditor().getEditorComponent();
        String sname = jtfSname.getText().trim();
        if (jrbArray[0].isSelected()) {//当站站查询选项中时
            if (start.equals("")) {//出发站为空时
                //弹出错误框
                JOptionPane.showMessageDialog(this,
                        "出发站不能为空!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (end.equals("")) {//目的站为空
                JOptionPane.showMessageDialog(this,
                        "目的站不能为空!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (start.equals(end)) {//出发站和目的站相同
                JOptionPane.showMessageDialog(this,
                        "出发站和目的站不能相同!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (start.equals(mid)) {//出发站和中转站相同
                //当中转站复框选中时
                if (jcbMidStation.isSelected()) {
                    JOptionPane.showMessageDialog(this,
                            "出发站和中转站不能相同!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            if (mid.equals(end)) {//目的站和中转站相同
                //当中转站复选框选中时
                if (jcbMidStation.isSelected()) {
                    JOptionPane.showMessageDialog(this,
                            "目的站和中转站不能相同!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } else if (jrbArray[1].isSelected()) {//当车站查询选中时
            if (tname.equals("")) {//如果车次文本框为空
                JOptionPane.showMessageDialog(this,
                        "车次不能为空!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else if (jrbArray[2].isSelected()) {//当车站所有车次查询选项中时
            if (sname.equals("")) {
                JOptionPane.showMessageDialog(this,
                        "车站名不能为空!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public void searchStation() {
        if (jrbArray[0].isSelected()) {//站站查询
            if (jcbReverseStation.isSelected() && jcbMidStation.isSelected()) {//当两个复选框都选中时
                this.zzcxAndMid();
            } else if (jcbReverseStation.isSelected() && !jcbMidStation.isSelected()) {//当反向车站选中,无中转站时
                this.zzcx();
            } else if (!jcbReverseStation.isSelected() && jcbMidStation.isSelected()) {//当中转站选中,反向车站未选中
                this.zzcxAndMid();
            } else {//两个复选框都未选中
                this.zzcx();
            }
        } else if (jrbArray[1].isSelected()) {//车次查询
            this.trainSearch();
        } else if (jrbArray[2].isSelected()) {//车站查询
            this.stationSearch();
        }
    }

    public Vector<Vector> combine(Vector<Vector> temp,
                                  Vector<Vector> temp1, Vector<Vector> temp2) {//将这三个Vector组合成一个
        for (int i = 0; i < temp.size(); i++) {
            Vector v1 = temp.get(i);
            if (i < temp1.size()) {
                Vector v2 = temp1.get(i);
                //将V2里面的元素加到V1里面
                for (int j = 0; j < v2.size(); j++) {
                    v1.add(v2.get(j));
                }
            } else {
                //没有关系时添加空
                v1.add("");
            }

        }
        for (int i = 0; i < temp.size(); i++) {
            Vector v1 = temp.get(i);
            if (i < temp2.size()) {
                Vector v2 = temp2.get(i);
                //将V2里面的元素加到V1里面
                for (int j = 0; j < v2.size(); j++) {
                    v1.add(v2.get(j));
                }
            } else {
                //没有关系时添加空
                v1.add("");
            }

        }
        //寻找记录中出发站和目的站相同的记录
        for (int i = 0; i < temp.size(); i++) {
            Vector v1 = temp.get(i);
            //得到出发站
            String start = (String) v1.get(4);
            //得到目的站
            String end = (String) v1.get(6);
            //当出发站和目的站相同时
            if (start.equals(end)) {
                //将出发站设置为始发站
                v1.set(4, v1.get(1));
                //查询发车时间
                String sql = "select Rstarttime from relation " +
                        "where Tid=" +
                        "(select Tid from train " +
                        "where Tname='" + (String) v1.get(0) + "') " +
                        "and Sid=" +
                        "(select Sid from station " +
                        "where Sname='" + (String) v1.get(1) + "')";
                //得到包含发车时间的向量
                Vector<Vector> v2 = DBTrain.getVector(sql);
                Vector v3 = v2.get(0);
                //得到发车时间并设置出发站时间
                v1.set(5, v3.get(0));
            }
        }
        return temp;
    }

    public Vector<Vector> getSameVector(String start, String end)
    {
        //查找车名,始发站,终点站和车类型
        String sql = "select Tname, Tstartstation, Tterminus, Ttype from train, relation r1,relation r2, station  st1, station  st2 where r1.Tid=r2.Tid and train.Tid=r1.tid  and st1.Sname='"+start+"'   and st2.Sname='"+end+"'and r1.Sid=st1.Sid and r2.Sid=st2.Sid;";
        System.out.println(sql);

        /*测试段
        System.out.println(sql);
        try {
            ConnectAccess.Test(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        //得到有关火车信息的Vector
        Vector<Vector> temp = DBTrain.getVector(sql);
//
        //当反向站查询按钮选中时
        if (jcbReverseStation.isSelected()) {
            //交换出发站和目的站
            String str = start;
            start = end;
            end = str;
        }

        //查找出发站和火车开车的时间
        String sql1 = "select st1.Sname,r1.Rstarttime FROM train, relation AS r1, relation AS r2, station AS st1, station AS st2 WHERE r1.Tid=r2.Tid and train.Tid=r1.tid and r1.Sid=st1.Sid and st1.Sname='"+start+"' and r2.Sid=st2.Sid and st2.Sname='"+end+"'";

        //查找终点站和火车到站时间
        String sql2 = "select st2.Sname,r2.Rarrivetime from train, relation r1,relation r2, station  st1, station  st2 where r1.Tid=r2.Tid and train.Tid=r1.tid and r1.Sid=st1.Sid and st1.Sname='"+start+"'  and r2.Sid=st2.Sid and st2.Sname='"+end+"';";

        //得到有关火车站的信息
        Vector<Vector> temp1 = DBTrain.getVector(sql1);
   //     System.out.println(temp1.elementAt(0));
        Vector<Vector> temp2 = DBTrain.getVector(sql2);
    //    System.out.println(temp2.elementAt(0));
        //将查询结果组合到一起
        temp = combine(temp, temp1, temp2);
   //     System.out.println(temp.elementAt(0));

        return temp;
    }

    public void zzcx() {//站站查询
        //两个复选框都未选中
        JTextField jtfStart = (JTextField) jcbStart.getEditor().getEditorComponent();
        String start = jtfStart.getText().trim();
        //得到目的站文本框的值
        JTextField jtfEnd = (JTextField) jcbEnd.getEditor().getEditorComponent();
        String end = jtfEnd.getText().trim();
        //得到有关记录的Vector
        Vector<Vector> temp = this.getSameVector(start, end);
        //删除vector中不合法的数据

       // Vector<Vector> v=temp;
        Vector<Vector> v = this.deleteData(temp);////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.updateTable(v);
        if (v.size() == 0) {
            //弹出信息提示对话框
            JOptionPane.showMessageDialog(this, "对不起,没有你要找的车!!!",
                    "信息提示", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void zzcxAndMid() {//当中转站选中,反向车站未选中
        //得到出发站文本框的值
        JTextField jtfStart = (JTextField) jcbStart.getEditor().getEditorComponent();
        String start = jtfStart.getText().trim();
        //得到目的站文本框的值
        JTextField jtfEnd = (JTextField) jcbEnd.getEditor().getEditorComponent();
        String end = jtfEnd.getText().trim();
        //得到中转站文本框的值
        JTextField jtfMid = (JTextField) jcbMid.getEditor().getEditorComponent();
        String mid = jtfMid.getText().trim();
        //当用户填写中转站时
        if (!mid.equals("")) {
            //得到从出发站到中转站的车的信息
            Vector<Vector> qtemp = this.getSameVector(start, mid);
            //得到从中转站到终点站的信息
            Vector<Vector> htemp = this.getSameVector(mid, end);
            //删除qtemp中不合法的数据
            Vector<Vector> vqtemp = this.deleteData(qtemp);
            //删除htemp中不合法的数据
            Vector<Vector> vhtemp = this.deleteData(htemp);
            int flag = 0;
            //判断是否有记录,若为空代表没有车可到达
            if (vhtemp.size() == 0 || vqtemp.size() == 0) {
                flag++;
            }
            //将vqtemp里面的元素加到vhtemp里面
            for (Vector v : vhtemp) {
                vqtemp.add(v);
            }
            //设置表格模型的数据与列名
            if (flag != 0) {
                //取清所有选中的行和列
                jt.clearSelection();
                dtm.setDataVector(new Vector(), vhead);
            } else {
                //取清所有选中的行和列
                jt.clearSelection();
                dtm.setDataVector(vqtemp, vhead);
            }
            //更新表格
            this.jt.repaint();
            this.jt.updateUI();
            if (flag != 0) {
                //弹出信息提示对话框
                JOptionPane.showMessageDialog(this, "对不起,没有你要找的车!!!",
                        "信息提示", JOptionPane.PLAIN_MESSAGE);
                return;
            }
        } else {//用户没有填写中转站
            //找到两站之间有车的车
            Vector<String> midstation = DBTrain.getMidStation(start, end);
            //当要查询的两站之间没有车时
            if (midstation.size() == 0) {
                //弹出信息提示对话框
                JOptionPane.showMessageDialog(this, "对不起,没有中转站可供选择!!!",
                        "信息提示", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            //创建用来存放合法的中转站的vector
            Vector<String> vlegal = new Vector<String>();
            Vector<Vector> vline = this.getSameVector(start, end);
            vline = this.deleteData(vline);
            //当两站之间有直达的车时直接显示
            if (vline.size() > 0) {
                //取清所有选中的行和列
                jt.clearSelection();
                //设置表格数据
                dtm.setDataVector(vline, vhead);
                //更新表格
                this.jt.repaint();
                this.jt.updateUI();
                return;
            } else {//当两站间没有直达的车时
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
                    //弹出信息提示对话框
                    JOptionPane.showMessageDialog(this, "对不起,没有中转站可供选择!!!",
                            "信息提示", JOptionPane.PLAIN_MESSAGE);
                } else {
                    new PopupStation(vlegal, this, start, end);
                }
            }
        }
    }

    public void trainSearch() {//车次查询

        String tname = this.jtfTname.getText().trim();
        //查找车名,始发站,终点站和车类型
        String sql = "select Tname,Tstartstation,Tterminus,Ttype " +
                "from train where Tname='" + tname + "'";
        //查找出发站和火车开车的时间
        String sql1 = "select Tstartstation,Rstarttime from train,relation " +
                "where train.Tid=relation.Tid and " +
                "Tname='" + tname + "' and relation.Sid=" +
                "(select Sid from station " +
                "where Sname=train.Tstartstation)";
        //查找终点站和火车到站时间
        String sql2 = "select Tterminus,Rarrivetime from train,relation " +
                "where train.Tid=relation.Tid and " +
                "Tname='" + tname + "' and relation.Sid=" +
                "(select Sid from station " +
                "where Sname=train.Tterminus)";
        //得到车名,始发站,终点站和车类型的Vector
        Vector<Vector> temp = DBTrain.getVector(sql);
        //得到出发站和火车开车时间的vector
        Vector<Vector> temp1 = DBTrain.getVector(sql1);
        //得到终点站和火车到站时间的vector
        Vector<Vector> temp2 = DBTrain.getVector(sql2);
        temp = combine(temp, temp1, temp2);
        if (temp.size() == 0) {
            //弹出信息提示对话框
            JOptionPane.showMessageDialog(this, "对不起,没有你要找的车!!!",
                    "信息提示", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        this.updateTable(temp);
    }

    public void stationSearch() {//车站查询
        //得到要查询车站名字
        JTextField jtfSname = (JTextField) jcbSname.getEditor().getEditorComponent();
        String station = jtfSname.getText().trim();
        //查询有关火车的信息
        String sql = "select  distinct Tname,Tstartstation,Tterminus,Ttype from train,relation,station where train.Tid=relation.Tid and (relation.Sid=station.Sid or relation.Rid=station.Sid) and Sname='"+station+"';";

        System.out.println(sql);
        //查询出发站及出发时间
        String sql1 = "select '" + station + "',Rstarttime from relation "
                + "where Sid = " +
                "(select Sid from station where " +
                "Sname='" + station + "')";
        System.out.println(sql1);
        //查询目的站及到站时间
        String sql2 = "select Tterminus,Rarrivetime from train,relation" +
                " where train.Tid=relation.Tid " +
                " and relation.Sid=" +
                "(select Sid from station where " +
                "Sname=train.Tterminus) and Train.Tid in " +
                "(select Tid from relation where Sid in " +
                "(select Sid from station where " +
                "Sname='" + station + "'))";

        System.out.println(sql2);
        //得到有关信息的向量
        Vector<Vector> temp = DBTrain.getVector(sql);
        Vector<Vector> temp1 = DBTrain.getVector(sql1);
        Vector<Vector> temp2 = DBTrain.getVector(sql2);
        //将三个Vector组合在一起
        temp = combine(temp, temp1, temp2);
        if (temp.size() == 0) {
            //弹出信息提示对话框
            JOptionPane.showMessageDialog(this, "对不起,没有你要找的车!!!",
                    "信息提示", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        this.updateTable(temp);
    }


    public Vector<Vector> deleteData(Vector<Vector> vtemp) {
        Vector<Vector> temp = new Vector<Vector>();
        //删除vtemp中不合法的数据
        for (int i = 0; i < vtemp.size(); i++) {
            //得到temp中的第i个元素
            Vector v1 = vtemp.get(i);
            //得到列车的名字
            String tname = (String) v1.get(0);
            //得到出发站
            String tstart = (String) v1.get(4);
            //得到终点站
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
        if (e.getSource() == jcbStart.getEditor().getEditorComponent()) {//当出发站得到焦点时
            //让站站查询选中
            jrbArray[0].setSelected(true);
        } else if (e.getSource() == jcbEnd.getEditor().getEditorComponent()) {//当目的站得到焦点时
            //让站站查询选中
            jrbArray[0].setSelected(true);
        } else if (e.getSource() == jcbMid.getEditor().getEditorComponent()) {//当中转站得到焦点时
            //让站站查询选中
            jrbArray[0].setSelected(true);
            //让中转站复选框选中
            jcbMidStation.setSelected(true);
        } else if (e.getSource() == jtfTname) {//当车次文本框得到焦点时
            //让车次查询选中
            jrbArray[1].setSelected(true);
        } else if (e.getSource() == jcbSname.getEditor().getEditorComponent()) {//当车站查询框得到焦点时
            //让车站查询选中
            jrbArray[2].setSelected(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String str = "输入站名首字母即可";
        if (e.getSource() == jcbStart.getEditor().getEditorComponent()) {
            //设置提示信息
            jcbStart.setToolTipText(str);
        } else if (e.getSource() == jcbEnd.getEditor().getEditorComponent()) {
            //设置提示信息
            jcbEnd.setToolTipText(str);
        } else if (e.getSource() == jcbMid.getEditor().getEditorComponent()) {
            //设置提示气球信息
            jcbMid.setToolTipText(str);
        } else if (e.getSource() == jcbSname.getEditor().getEditorComponent()) {
            //为下拉列表设置提示信息
            jcbSname.setToolTipText(str);
        } else if (e.getSource() == jtfTname) {
            //为文本框架设置提示信息
            String s = "输入要查询车次即可";
            jtfTname.setToolTipText(s);
        } else if (e.getSource() == jt) {
            //为表格设置提示信息
            String s = "鼠标左键单击可以查看详细信息";
            jt.setToolTipText(s);
        } else if (e.getSource() == jbApp) {
            String s = "点击即可插入列车及车站信息";
            jbApp.setToolTipText(s);
        } else if (e.getSource() == jbQuery) {
            String s = "单击该按钮即可进行查询";
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
        //如果在jcbStart上输入时
        if (e.getSource() == jcbStart.getEditor().getEditorComponent()) {
            //得到编辑器组件
            JTextField jtf = (JTextField) bcbeStart.getEditorComponent();
            //当输入的不匹配字母时返回
            if (!jtf.getText().trim().matches("[a-zA-Z]+")) {
                return;
            }
            //得到编辑器里面的内容
            String s = jtf.getText();
            //调用setCurrentModel得到下拉列表框里面的内容
            DBTrain.setCurrentModel(jtf.getText(), dcbmStart);
            jtf.setText(s);
            //设置下拉列表框的可见性为true
            jcbStart.setPopupVisible(true);
        } else if (e.getSource() == jcbEnd.getEditor().getEditorComponent()) {
            //得到编辑器组件
            JTextField jtf = (JTextField) bcbeEnd.getEditorComponent();
            //当输入的不匹配字母时返回
            if (!jtf.getText().trim().matches("[a-zA-Z]+")) {
                return;
            }
            //得到编辑器里面的内容
            String s = jtf.getText();
            //调用setCurrentModel得到下拉列表框里面的内容
            DBTrain.setCurrentModel(jtf.getText(), dcbmEnd);
            jtf.setText(s);
            //设置下拉列表框的可见性为true
            jcbEnd.setPopupVisible(true);
        } else if (e.getSource() == jcbMid.getEditor().getEditorComponent()) {
            //得到编辑器组件
            JTextField jtf = (JTextField) bcbeMid.getEditorComponent();
            //当输入的不匹配字母时返回
            if (!jtf.getText().trim().matches("[a-zA-Z]+")) {
                return;
            }
            //得到编辑器里面的内容
            String s = jtf.getText();
            //调用setCurrentModel得到下拉列表框里面的内容
            DBTrain.setCurrentModel(jtf.getText(), dcbmMid);
            jtf.setText(s);
            //设置下拉列表框的可见性为true
            jcbMid.setPopupVisible(true);
        } else if (e.getSource() == jcbSname.getEditor().getEditorComponent()) {
            //得到编辑器组件
            JTextField jtf = (JTextField) bcbeSname.getEditorComponent();
            //当输入的不匹配字母时返回
            if (!jtf.getText().trim().matches("[a-zA-Z]+")) {
                return;
            }
            //得到编辑器里面的内容
            String s = jtf.getText();
            //调用setCurrentModel得到下拉列表框里面的内容
            DBTrain.setCurrentModel(jtf.getText(), dcbmSname);
            jtf.setText(s);
            //设置下拉列表框的可见性为true
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
        //取清所有选中的行和列
        jt.clearSelection();
        //设置表格数据
        dtm.setDataVector(vt, vhead);
        //更新表格
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