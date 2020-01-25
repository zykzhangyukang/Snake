package beans;

import enums.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 小蛇
 * Created by zhangyukang on 2020/1/25 16:23
 */
public class Snake {

    private Node head=null;//蛇头
    private Node tail=null;//蛇尾
    private int size;//蛇长
    private Ground ground;

    private static Color SNAKE_COLOR=Color.GREEN;//小蛇的颜色

    /*初始为一节节点**/
    private Node node=new Node(15,5,Direction.UP);

    public Snake(Node node){
        head=node;
        tail=node;
        size=0;
    }

    public Snake(Ground ground){
        head=this.node;
        tail=this.node;
        size=1;
        this.ground=ground;
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
        node.pre=tail;
        tail=node;
        size++;
    }

    /*向蛇头添加一节**/
    public void addToHead(){
        Node node=null;
        switch (head.direction){
            case UP:
               node=new Node(head.rows-1,head.cols,head.direction);
                break;
            case DOWN:
                node=new Node(head.rows+1,head.cols,head.direction);
                break;
            case LEFT:
                node=new Node(head.rows,head.cols-1,head.direction);
                break;
            case RIGHT:
                node=new Node(head.rows,head.cols+1,head.direction);
                break;
        }
        node.next=head;
        head.pre=node;
        head=node;
        size++;
    }

    /*画蛇*/
    public void drawSnake(Graphics g){
        Color color = g.getColor();
        for(Node h=head;h!=null;h=h.next){
            h.drawNode(g);
        }
        g.setColor(color);
        move();
        checkIsDeath();
    }
    /*判断蛇是否死亡*/
    private void checkIsDeath() {
        if(head.rows<0||head.rows>Ground.ROWS||head.cols<0||head.cols>Ground.COLS){
            ground.stop();
        }
        for(Node h=head.next;h!=null;h=h.next){
            if(head.rows==h.rows&&head.cols==h.cols){
                ground.stop();
            }
        }
    }

    /**蛇移动:掐头去尾*/
    private void move() {
        addToHead();
        cutTail();
    }

    /**剪出尾巴*/
    private void cutTail() {
       if(size==0)return ;
       tail=tail.pre;
       tail.next=null;
    }

    /*蛇改变方向**/
    public void changeSnake(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_UP:
                if(head.direction!=Direction.DOWN)
                head.direction=Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                if(head.direction!=Direction.UP)
                head.direction=Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                if(head.direction!=Direction.RIGHT)
                head.direction=Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                if(head.direction!=Direction.LEFT)
                head.direction=Direction.RIGHT;
                break;
        }
    }

    /*蛇吃苹果**/
    public void eatApple(Apple apple) {
        if(this.getRectangle().intersects(apple.getRectangle())){
            addToHead();
            apple.updateAppleLocation();
            ground.setScore(ground.getScore()+5);
        }
    }
    /*获取蛇的碰撞点**/
    private Rectangle getRectangle(){
        return new Rectangle(head.cols*Ground.BOX_SIZE,head.rows*Ground.BOX_SIZE,head.w,head.h);
    }

    private class Node{
        private int rows,cols;//行列
        private int w=Ground.BOX_SIZE;//节宽
        private int h=Ground.BOX_SIZE;//节高
        private Node next;//下一个节点
        private Node pre;//上一个节点
        private Direction direction;//默认方向：左


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
