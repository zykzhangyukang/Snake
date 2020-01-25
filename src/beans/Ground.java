package beans;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 草地类
 * Created by zhangyukang on 2020/1/24 08:37
 */
public class Ground extends Frame{

    public static final int ROWS=30;//草地行数
    public static final int COLS=30;//草地列数
    public static final int BOX_SIZE=20;//每个小格的大小
    private static final Color GROUND_COLOR=Color.GRAY;//草地的颜色

    private Snake snake=new Snake();


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
        setVisible(true);
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
        g.setColor(Color.BLACK);
        for(int i=1;i<ROWS;i++){
            g.drawLine(0,i*BOX_SIZE,COLS*BOX_SIZE,i*BOX_SIZE);
        }
        for(int i=1;i<COLS;i++){
            g.drawLine(i*BOX_SIZE,0,BOX_SIZE*i,ROWS*BOX_SIZE);
        }
        g.setColor(color);
        snake.drawSnake(g);
    }


    public static void main(String[] args){
        new Ground().execute();
    }
}
