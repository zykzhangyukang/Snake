package test;

/**
 * Created by zhangyukang on 2020/1/25 16:08
 */
public class LinkNode {
    private Node head;
    private Node tail;
    private int size;


    public LinkNode(){
        Node node=new Node();
        head=node;
        tail=node;
        size=1;
    }

    /**
     * 在链表的尾部添加一个节点
     * @param node
     */
    public void addToTail(Node node){
        tail.setNext(node);
        tail=node;
        size++;
    }

    /**
     * 添加到链表的头部
     * @param node
     */
    public void addToHead(Node node){
        node.setNext(head);
        head=node;
        size++;
    }

    /**
     * 遍历链表的数据项
     * @return
     */
    public void printLinkDatas(){
        for(Node h=head;h!=null;h=h.getNext()){
            System.out.print(h.getData()+"\t");
        }
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}
