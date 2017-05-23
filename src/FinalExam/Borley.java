package FinalExam; /***********************************
 *SwimFish_1.java
 *
 *Originally created by Wenbin Lian
 *Revised by Zifei Zhong
 *
 *Dept. of Computer Science
 *Wuhan University
 *Wuhan 430072 China
 ***********************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Fish extends Thread
{
    int x,		  //该鱼的x坐标
            y,		  //该鱼的y坐标
            width,	  //该鱼使用的图片的宽度
            height;	  //该鱼使用的图片的高度

    int v_mul  = 40,  //控制v值的一个乘数
            v_plus = 10,  //控制v值的一个加数
            l_mul  = 10,  //控制l值的一个乘数
            x1_mul = 1800,//控制x1值的一个乘数
            y1_mul = 800; //控制y1值的一个乘数

    //随机产生鱼初始游的方向
    int down = (int)(Math.random()*10)%2;	//down=1 : 向下游
    int right= (int)(Math.random()*10)%2;	//right=1: 向右游
    int v = (int)(Math.random()*v_mul)+v_plus;//v用来控制刷新周期,v越小,两次刷新之间的时间间隔就越小,从而速度就越快
    int l = (int)(Math.random()*l_mul);	//随机产生斜游角度比x:y=l,即在x方向上游动l次之后才在y方向上移动一次

    //随机产生鱼上下左右游动的最大上限，即振幅
    int x1 = (int)(Math.random()*x1_mul);
    int y1 = (int)(Math.random()*y1_mul);

    int i,	//鱼的标志：第i条鱼(本程序中共有8条鱼)
            count_x1 = 0,//x方向上振幅计数
            count_y1 = 0;//y方向上振幅计数


    boolean running = true;
    FishPanel fishpanel;

    public Fish(FishPanel _fishpanel, int _x, int _y, int _width, int _height, int _i)
    {
        fishpanel = _fishpanel;
        x = _x;
        y = _y;
        width = _width;
        height = _height;
        i = _i;
        start();
    }

    public void start()
    {
        running = true;
        super.start();
    }

    public void run()
    {
        while(running)
        {
            computeX();
            computeY();
            try
            {
                sleep(v);//v
            }catch(Exception e){}
            fishpanel.repaint();
        }
    }

    public void halt()
    {
        running = false;
    }

    public void computeX()
    {
        if(right == 1)
        {
            if(x > fishpanel.getWidth()-width || count_x1 == x1)
            {//判断是否到右端边框或达到最大振幅
                right=0; reset('x');
            }
            x++;
        }
        else
        {
            if(x < 0 ||count_x1 == x1)
            {//判断是否到左端边框或达到最大振幅
                right=1; reset('x');
            }
            x--;
        }
        count_x1++;
    }

    public void computeY()
    {//计算鱼的上下游动
        if(l != 0)//判断随机数l是否为0，是则水平游动，否则计算角度
            if(down == 1 && count_x1%l == 0)//x former
            {//down=1表示向下游且当x++或x--了l次后，y++
                if(y >= fishpanel.getHeight()-height || count_y1 == y1)
                {//判断是否到底或上下振幅游满否
                    down=0; reset('y');
                }
                y++;
            }
            else if(count_x1%l == 0 || count_y1 == y1)//x former
            {//当向上游且当x++或x--了l次之后,y--
                if(y <= 0)
                {
                    down=1; reset('y');
                }
                y--;
            }
        count_y1++;
    }

    public void reset(char xory)
    {//鱼游动状态改变后重新生成各项数值
        if(xory == 'x')
        {
            x1 = (int)(Math.random()*x1_mul);//重新生成新的左右振幅(判断依据：x)
            count_x1 = 0; //左右振幅计数清0
        }
        else
        {
            y1 = (int)(Math.random()*y1_mul);//重新生成新的上下振幅(判断依据：y)
            count_y1 = 0;//上下振幅计数清0
        }
        v = (int)(Math.random()*v_mul)+v_plus;//重新生成新的速度
        l = (int)(Math.random()*l_mul);//重新生成新的角度比
    }
}

 class SwimFish extends JFrame
{
    public SwimFish()
    {
        setTitle("8 swimming fishes");
        setSize(600,468);
        setResizable(false);
        FishPanel fishpanel = new FishPanel();
        Container contentpane = getContentPane();
        contentpane.add(fishpanel);
        show();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[])
    {
        new SwimFish();
    }
}

class FishPanel extends JPanel implements MouseListener
{
    private Image Background;		//背景图片
    Image imageFish[] = new Image[8];	//8张鱼图片
    int fishTotal = 8;			//鱼的总数为8条
    Fish fish[] = new Fish[fishTotal];	//8条鱼,即8个线程
    private boolean k = false;		//标志,响应鼠标事件会用到

    public FishPanel()
    {
        setSize(600, 468);
        addMouseListener(this);
        Background = Toolkit.getDefaultToolkit().getImage("./Pics/sea.jpg");
        for(int i=0; i<8; i++) imageFish[i] = Toolkit.getDefaultToolkit().getImage("./Pics/fish0"+i+".gif");

        int x,		//鱼的x坐标
                y,		//鱼的y坐标
                width=64,	//鱼图片的宽度(所有图片宽度都为64)
                height = 0;	//鱼图片的宽度(各图片高度不一致)
        for(int i=0; i<fishTotal; i++)
        {//初始化每条鱼（线程）的状态
            switch(i/2)
            {//不同的鱼对应的图片高度不一致
                case 0: height = 56; break;		//0-1两张图片高度为56
                case 1: case 2: height = 53; break;	//2-5四张图片高度为53
                case 3: height = 37; break;		//6-7两张图片高度为37
            }

            //随机产生鱼初始出现的位置
            x = (int)(Math.random()*(600 - width));
            y = (int)(Math.random()*(468 - height));

            //线程实例化
            fish[i] = new Fish(this,x,y,width,height,i);
        }
    }

    public void mouseClicked(MouseEvent e)
    {//响应鼠标点击事件
        if(k)
        {
            k = !k;
            for(int i=0; i<fishTotal; i++)
            {
                fish[i] = new Fish(this,fish[i].x,fish[i].y,fish[i].width,fish[i].height,i);
            }
        }
        else
        {
            k = !k;
            for(int i=0; i<fishTotal; i++)
                fish[i].halt();
        }
    }
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(Background,0,0,getWidth(),getHeight(),this);
        try
        {//加载背景图片后,等待30微妙
            Thread.sleep(30);
        }catch(InterruptedException e){}
        for(int i=0; i<fishTotal; i++)
        {
            //鱼向左、右游动时,显示的图片不同
            if(fish[i].right == 1)
                g.drawImage(imageFish[(i/2)*2],fish[i].x,fish[i].y,this);//标志right=1表示向右游，画鱼
            else
                g.drawImage(imageFish[(i/2)*2+1],fish[i].x,fish[i].y,this);
        }
    }
}


class Target
{
    public  void Request()
    {
        System.out.println("普通请求");
    }
}

class Adaptee
{
    public void SpecificRequest()
    {
        System.out.println("特殊请求");
    }
}

class Adapter extends Target
{
    private  Adaptee adaptee=new Adaptee();
    public void Request()
    {
        adaptee.SpecificRequest();
    }
}


class test
{
    public static void main(String  arg[])
    {
        Target ta=new Target();
        Target t2=new Adapter();

        ta.Request();
        t2.Request();//可以让t2通过适配器理解t1相同的命令
        //但是进行的是Ataptee的特殊操作。
    }
}