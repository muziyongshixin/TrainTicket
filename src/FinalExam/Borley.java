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
    int x,		  //�����x����
            y,		  //�����y����
            width,	  //����ʹ�õ�ͼƬ�Ŀ��
            height;	  //����ʹ�õ�ͼƬ�ĸ߶�

    int v_mul  = 40,  //����vֵ��һ������
            v_plus = 10,  //����vֵ��һ������
            l_mul  = 10,  //����lֵ��һ������
            x1_mul = 1800,//����x1ֵ��һ������
            y1_mul = 800; //����y1ֵ��һ������

    //����������ʼ�εķ���
    int down = (int)(Math.random()*10)%2;	//down=1 : ������
    int right= (int)(Math.random()*10)%2;	//right=1: ������
    int v = (int)(Math.random()*v_mul)+v_plus;//v��������ˢ������,vԽС,����ˢ��֮���ʱ������ԽС,�Ӷ��ٶȾ�Խ��
    int l = (int)(Math.random()*l_mul);	//�������б�νǶȱ�x:y=l,����x�������ζ�l��֮�����y�������ƶ�һ��

    //������������������ζ���������ޣ������
    int x1 = (int)(Math.random()*x1_mul);
    int y1 = (int)(Math.random()*y1_mul);

    int i,	//��ı�־����i����(�������й���8����)
            count_x1 = 0,//x�������������
            count_y1 = 0;//y�������������


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
            {//�ж��Ƿ��Ҷ˱߿��ﵽ������
                right=0; reset('x');
            }
            x++;
        }
        else
        {
            if(x < 0 ||count_x1 == x1)
            {//�ж��Ƿ���˱߿��ﵽ������
                right=1; reset('x');
            }
            x--;
        }
        count_x1++;
    }

    public void computeY()
    {//������������ζ�
        if(l != 0)//�ж������l�Ƿ�Ϊ0������ˮƽ�ζ����������Ƕ�
            if(down == 1 && count_x1%l == 0)//x former
            {//down=1��ʾ�������ҵ�x++��x--��l�κ�y++
                if(y >= fishpanel.getHeight()-height || count_y1 == y1)
                {//�ж��Ƿ񵽵׻��������������
                    down=0; reset('y');
                }
                y++;
            }
            else if(count_x1%l == 0 || count_y1 == y1)//x former
            {//���������ҵ�x++��x--��l��֮��,y--
                if(y <= 0)
                {
                    down=1; reset('y');
                }
                y--;
            }
        count_y1++;
    }

    public void reset(char xory)
    {//���ζ�״̬�ı���������ɸ�����ֵ
        if(xory == 'x')
        {
            x1 = (int)(Math.random()*x1_mul);//���������µ��������(�ж����ݣ�x)
            count_x1 = 0; //�������������0
        }
        else
        {
            y1 = (int)(Math.random()*y1_mul);//���������µ��������(�ж����ݣ�y)
            count_y1 = 0;//�������������0
        }
        v = (int)(Math.random()*v_mul)+v_plus;//���������µ��ٶ�
        l = (int)(Math.random()*l_mul);//���������µĽǶȱ�
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
    private Image Background;		//����ͼƬ
    Image imageFish[] = new Image[8];	//8����ͼƬ
    int fishTotal = 8;			//�������Ϊ8��
    Fish fish[] = new Fish[fishTotal];	//8����,��8���߳�
    private boolean k = false;		//��־,��Ӧ����¼����õ�

    public FishPanel()
    {
        setSize(600, 468);
        addMouseListener(this);
        Background = Toolkit.getDefaultToolkit().getImage("./Pics/sea.jpg");
        for(int i=0; i<8; i++) imageFish[i] = Toolkit.getDefaultToolkit().getImage("./Pics/fish0"+i+".gif");

        int x,		//���x����
                y,		//���y����
                width=64,	//��ͼƬ�Ŀ��(����ͼƬ��ȶ�Ϊ64)
                height = 0;	//��ͼƬ�Ŀ��(��ͼƬ�߶Ȳ�һ��)
        for(int i=0; i<fishTotal; i++)
        {//��ʼ��ÿ���㣨�̣߳���״̬
            switch(i/2)
            {//��ͬ�����Ӧ��ͼƬ�߶Ȳ�һ��
                case 0: height = 56; break;		//0-1����ͼƬ�߶�Ϊ56
                case 1: case 2: height = 53; break;	//2-5����ͼƬ�߶�Ϊ53
                case 3: height = 37; break;		//6-7����ͼƬ�߶�Ϊ37
            }

            //����������ʼ���ֵ�λ��
            x = (int)(Math.random()*(600 - width));
            y = (int)(Math.random()*(468 - height));

            //�߳�ʵ����
            fish[i] = new Fish(this,x,y,width,height,i);
        }
    }

    public void mouseClicked(MouseEvent e)
    {//��Ӧ������¼�
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
        {//���ر���ͼƬ��,�ȴ�30΢��
            Thread.sleep(30);
        }catch(InterruptedException e){}
        for(int i=0; i<fishTotal; i++)
        {
            //���������ζ�ʱ,��ʾ��ͼƬ��ͬ
            if(fish[i].right == 1)
                g.drawImage(imageFish[(i/2)*2],fish[i].x,fish[i].y,this);//��־right=1��ʾ�����Σ�����
            else
                g.drawImage(imageFish[(i/2)*2+1],fish[i].x,fish[i].y,this);
        }
    }
}


class Target
{
    public  void Request()
    {
        System.out.println("��ͨ����");
    }
}

class Adaptee
{
    public void SpecificRequest()
    {
        System.out.println("��������");
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
        t2.Request();//������t2ͨ�����������t1��ͬ������
        //���ǽ��е���Ataptee�����������
    }
}