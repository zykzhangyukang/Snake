package beans;

import enums.Direction;

import java.awt.*;

/**
 * 小蛇
 * Created by zhangyukang on 2020/1/25 16:23
 */
public class Snake {

    private Node head=null;//蛇头
    private Node tail=null;//蛇尾
    private int size;//蛇长

    private static Color SNAKE_COLOR=Color.GREEN;//小蛇的颜色

    private Node node=new Node(15,15,Direction.LEFT);

    public Snake(Node node){
        head=node;
        tail=node;
        size=0;
    }

    public Snake(){
        head=this.node;
        tail=this.node;
        size=1;
    }

    /*向蛇尾添加一节**/
    public void addToTail(){
        Node node=null;
        switch (tail.direction){
            case UP:
                node=new Node(tail.rows-1,tail.cols,tail.direction);
                break;
            case DOWN:
                node=new Node(tail.rows-1,tail.cols,tail.direction);
                break;
            case LEFT:
                node=new Node(tail.rows,tail.cols+1,tail.direction);
                break;
            case RIGHT:
                node=new Node(tail.rows,tail.cols-1,tail.direction);
                break;
        }
        tail.next=node;
        tail=node;
        size++;
    }

    /**画出蛇*/
    public void drawSnake(Graphics g){
        Color color = g.getColor();
        for(Node h=head;h!=null;h=h.next){
            h.drawNode(g);
        }
        g.setColor(color);
    }

    /*向蛇头添加一节**/
    public void addToHead(Node node){

    }

    private class Node{
        private int rows,cols;//行列
        private int w=Ground.BOX_SIZE;//节宽
        private int h=Ground.BOX_SIZE;//节高
        private Node next;//下一个节点
        private Direction direction=Direction.LEFT;//默认方向：左

        public Node(int  rows,int cols,Direction direction){
            this.rows=rows;
            this.cols=cols;
            this.direction=direction;
        }

        /*画出小蛇的一节**/
        public void drawNode(Graphics graphics){
            Color color = graphics.getColor();
            graphics.setColor(SNAKE_COLOR);
            graphics.fillRect(cols*Ground.BOX_SIZE,rows*Ground.BOX_SIZE,w,h);
            graphics.setColor(color);
        }

    }
}
