package com.flora.linkedList;

/**
 * @Author qinxiang
 * @Date 2022/8/25-下午3:54
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();

        HeroNode2 newHeroNode = new HeroNode2(1,"张飞","关公");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表");
        doubleLinkedList.list();

        doubleLinkedList.delete(3);
        System.out.println("删除后的链表");
        doubleLinkedList.list();

        doubleLinkedList.addByOrder(new HeroNode2(5,"大鹏","演员"));
        doubleLinkedList.addByOrder(new HeroNode2(3,"大鹏","演员"));
        System.out.println("顺序添加的链表");
        doubleLinkedList.list();
    }
}
//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "","");
    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }
    //显示链表
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
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
    //添加节点到双向链表
    public void add(HeroNode2 heroNode){
        HeroNode2 temp = head;
        while(true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }
    //第二种方式 在添加英雄时，根据排名将英雄插入到指定位置
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
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
    public void update(HeroNode2 newHeroNode){
        if(head.next == null){
            System.out.println("；链表为空");
            return;
        }
        HeroNode2 temp = head.next;
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
        if(head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标志是否找到待删除节点的
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，实现遍历
        }
        //判断flag
        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("没有找到待删除的编号为%d的节点\n",no);
        }

    }
}
//定义一个HeroNode2,每一个对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;
    public HeroNode2(int hNo, String hName, String hNickname){
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
