package FinalExam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 李勇志 on 2016/6/15.
 */
public class Server {

    public static void main(String arg[]) throws  Exception {
        MemoController memoController = new MemoController();

            ServerSocket ss = new ServerSocket(1999);
            while (true) {
                Socket s = ss.accept();

                    UserThread t = new UserThread(s, memoController);
                    t.start();
                }
            }








}


class MemoController {
    FileOutputStream fs_out;
    DataOutputStream ds_out;

    public MemoController() {
        try {

            fs_out = new FileOutputStream("Memo.txt");
            ds_out = new DataOutputStream(fs_out);
        } catch (Exception e) {

        }
    }

    public void write(String s) {
        try {
            String wt = s;
            ds_out.writeBytes(wt);

        } catch (Exception e) {

        }

    }

    public void close() {
        try {

            this.fs_out.close();
            this.ds_out.close();
        } catch (Exception e) {

        }
    }

}

class UserThread extends Thread {
    boolean running = false;
    DataInputStream in;
    DataOutputStream out;
    Socket connection;
    MemoController me;

    public UserThread(Socket s, MemoController memo) {
        this.connection = s;
        this.me = memo;
        try {

            in = new DataInputStream(connection.getInputStream());
            out = new DataOutputStream(connection.getOutputStream());


        } catch (Exception e) {

        }


    }


    public void start() {
        running = true;
        super.start();
    }

    @Override
    public void run() {

        try {
            String get = in.readLine();
            if (get.equals("plain")) {
                String send = "hello";
                out.writeBytes(send);
            } else {
                String send = "OK";
                out.writeBytes(send);
                me.write(get);
            }
            in.close();
            out.close();

        } catch (Exception e) {

        }

    }


}