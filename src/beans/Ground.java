package beans;

import java.awt.*;
import java.awt.event.*;

/**
 * 草地类
 * Created by zhangyukang on 2020/1/24 08:37
 */
public class Ground extends Frame{

    public static final int ROWS=50;//草地行数
    public static final int COLS=50;//草地列数
    public static final int BOX_SIZE=15;//每个小格的大小
    private static final Color GROUND_COLOR=Color.BLACK;//草地的颜色
    private int score=0;//初始分数

    private boolean isOver=false;

    private PaintThread paintThread=new PaintThread();

    private Snake snake=new Snake(this);

    private Apple apple=new Apple();


    /**
     * 运行草地
     */
    public void execute(){
        setSize(COLS*BOX_SIZE,ROWS*BOX_SIZE);
        setLocation(200,200);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
        setVisible(true);
        new Thread(paintThread).start();
    }

    /**
     * 画出草地
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(GROUND_COLOR);
        g.fillRect(0,0,COLS*BOX_SIZE,ROWS*BOX_SIZE);
        //画出横线
        g.setColor(Color.black);
        for(int i=1;i<ROWS;i++){
            g.drawLine(0,i*BOX_SIZE,COLS*BOX_SIZE,i*BOX_SIZE);
        }
        for(int i=1;i<COLS;i++){
            g.drawLine(i*BOX_SIZE,0,BOX_SIZE*i,ROWS*BOX_SIZE);
        }
        //画蛇
        snake.drawSnake(g);
        //画苹果
        apple.drawApple(g);
        //吃苹果
        snake.eatApple(apple);
        //分值
        g.setColor(Color.white);
        g.drawString("当前分数:"+score+" 分",30,60);
        if(isOver){
           g.setFont(new Font("宋体",Font.BOLD,30));
           g.setColor(Color.YELLOW);
           g.drawString("游戏结束 ! \n 按F2重玩",200,300);
        }
        g.setColor(color);


    }

    /**游戏结束*/
    public void stop(){
        isOver=true;
        paintThread.setOff();
    }

    /**
     * 重画草地的线程
     */
    private class PaintThread implements Runnable{

        private boolean flag=true;

        @Override
        public void run() {
            try {
                while (flag){
                    Thread.sleep(100);
                    repaint();
                }
            }catch (Exception e){
                System.out.println("【重画线程异常】");
                e.printStackTrace();
            }
        }

        public void setOff(){
            this.flag=false;
        }

        public void setOn(){
            this.flag=true;
        }

    }

    /**
     * 键盘事件
     */
    private class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            snake.changeSnake(e);
            if(isOver){
                if(KeyEvent.VK_F2==e.getKeyCode())
                restart();
            }
        }
    }

    /**
     * 重新开始
     */
    private void restart() {
        isOver=false;
        snake=new Snake(this);
        paintThread.setOn();
        new Thread(paintThread).start();
    }
    /**
     * 双缓冲技术
     */
    Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(COLS*BOX_SIZE,ROWS*BOX_SIZE);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.GREEN);
        gOffScreen.fillRect(0, 0,COLS*BOX_SIZE,ROWS*BOX_SIZE);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score=score;
    }
    public static void main(String[] args){
        new Ground().execute();
    }
}
