package test;

/**
 * Created by zhangyukang on 2020/1/25 16:12
 */
public class TestCreateLinkNode {

    /**
     * 创建链表
     * @param args
     */
    public static void main(String[] args){
        LinkNode linkNode = new LinkNode();
        Node node=new Node();
        Node node1=new Node();
        Node node2=new Node();
        Node node3=new Node();
        node.setData(100);
        node1.setData(45);
        node2.setData(78);
        node3.setData(23);
        linkNode.addToTail(node);
        linkNode.addToTail(node1);
        linkNode.addToTail(node2);
        linkNode.addToHead(node3);
        linkNode.printLinkDatas();
    }
}
