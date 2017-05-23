package FinalExam;

/**
 * Created by 李勇志 on 2016/6/16.
 */
public class SearchChar {
    public int IsCharInArray(char[] a,char b)
    {
        if(a.length==0)
        {
            throw  new IllegalArgumentException();
        }
        else
        {
            for(int i=0;i<a.length;i++)
            {
                if(a[i]==b)
                    return i;
            }
            return  -1;
        }
    }
}



class _2009_3_1 extends  Thread
{
    public int jiami(int old)
    {
        int a,b,c,d;
        a=b=c=d=0;

        a=old/1000;
        b=(old-a*1000)/100;
        c=(old-a*1000-b*100)/10;
        d=(old-a*1000-b*100-c*10);

        a=(a+5)%10;
        b=(b+5)%10;
        c=(c+5)%10;
        d=(d+5)%10;


        return 1000*d+100*c+10*b+a;

    }

}