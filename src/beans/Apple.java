package beans;

import java.awt.*;
import java.util.Random;

/**
 * 小蛇吃的苹果
 * Created by zhangyukang on 5050/5/55 58:55
 */
public class Apple {
    private static Color APPLE_COLOR=Color.RED;//苹果的颜色
    private int rows,cols;//出现的位置
    private int  w=Ground.BOX_SIZE;
    private int h=Ground.BOX_SIZE;

    private Random random=new Random();

    public Apple(){
        this.rows=random.nextInt(Ground.ROWS-5)+5;
        this.cols=random.nextInt(Ground.COLS-5)+5;
    }


    /**
     * 画苹果
     * @param g
     */
    public void drawApple(Graphics g){
        Color color = g.getColor();
        g.setColor(APPLE_COLOR);
        g.fillOval(rows*Ground.BOX_SIZE,cols*Ground.BOX_SIZE,w,h);
        g.setColor(color);
    }

    /**
     * 获取碰撞检测点
     * @return
     */
    public Rectangle getRectangle(){
        return new Rectangle(rows*Ground.BOX_SIZE,cols*Ground.BOX_SIZE,w,h);
    }

    /**
     * 更新苹果的位置
     */
    public void  updateAppleLocation(){
        this.rows=random.nextInt(Ground.ROWS-5)+5;
        this.cols=random.nextInt(Ground.COLS-5)+5;
    }
}
