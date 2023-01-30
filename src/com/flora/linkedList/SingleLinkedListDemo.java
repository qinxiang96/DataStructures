package com.flora.linkedList;

import java.util.Stack;

/**
 * @Author qinxiang
 * @Date 2022/8/24-下午5:46
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero2);
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero2);
        //测试修改节点的代码
        singleLinkedList.list();
        HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改之后的链表的情况");
        singleLinkedList.list();

        singleLinkedList.delete(5);
        System.out.println("删除后的链表的情况");
        singleLinkedList.list();

        int length = getLength(singleLinkedList.getHead());
        System.out.println("链表的节点个数为"+length);

        HeroNode lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 1);
        System.out.println("倒数第1个节点为"+lastIndexNode);
        singleLinkedList.list();

        reverseList(singleLinkedList.getHead());
        System.out.println("反转后~~");
        singleLinkedList.list();

        System.out.println("逆序打印结果");
        reversePrint(singleLinkedList.getHead());
        System.out.println("链表的结构");
        singleLinkedList.list();
    }
    //逆序打印
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
    //将单链表进行反转
    public static void reverseList(HeroNode head){
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助节点
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点cur的下一个节点
        HeroNode reverseHead = new HeroNode(0, "","");
        while(cur!= null){
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next,实现链表反转
        head.next = reverseHead.next;
    }

    //查找单链表中的倒数第K个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        if (head.next == null){
            return null;
        }
        //第一个遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历size-index的位置
        if (index <=0 || index > size){
            return null;
        }
        HeroNode current = head.next;
        for (int i = 0; i < size-index; i ++){
            current = current.next;
        }
        return current;
    }
    //方法：获取到单链表的节点的个数，如果是带头节点的链表，需求不统计头节点
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        //定义一个辅助节点
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }
}
//定义一个SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while(true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }
    //第二种方式 在添加英雄时，根据排名将英雄插入到指定位置
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认不存在 FALSE
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            System.out.printf("准备插入的英雄的编号%d已经存在，不能添加", heroNode.no);
        }else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
    //修改节点的信息，根据no编号来修改，即no编号不能改
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("；链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if(temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号%d的节点", newHeroNode.no);
        }
    }
    //删除节点
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点的
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，实现遍历
        }
        //判断flag
        if (flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("没有找到待删除的编号为%d的节点\n",no);
        }

    }
    //显示链表
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }
}
//定义一个HeroNode,每一个对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    public HeroNode(int hNo, String hName, String hNickname){
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}