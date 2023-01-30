package com.flora.hashtab;

import java.util.Scanner;

/**
 * @Author qinxiang
 * @Date 2022/8/31-下午2:32
 */
public class HashtabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch(key){
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    System.out.println("您已退出系统");
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }

        }
    }

}
//创建HashTab
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示有多少条链表
    public HashTab(int size){
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];//初始化
        //这时不要初始化每个链表
        for(int i = 0; i < size; i ++){
            empLinkedListArray[i] = new EmpLinkedList();
        }

    }
    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    //遍历所有的链表,遍历hashtab
    public void list(){
        for(int i = 0; i < size; i ++){
            empLinkedListArray[i].list(i);

        }
    }
    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp empById = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(empById != null){
            System.out.printf("在第%d条链表中找到雇员，id=%d\n", empLinkedListNo, id);

        }else{
            System.out.println("在哈希表中没有找到该雇员~");
        }
    }
    //编写散列函数，使用一个简单取模法
    public int hashFun(int id){
        return id % size;
    }
}
//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }
}
//创建EmpLinkList 表示链表
class EmpLinkedList{
    //头指针，执行第一个Emp，因此我们这个链表的head，是直接指向第一个Emp
    private Emp head;
    public void add(Emp emp){
        //如果是添加第一个雇员
        if(head == null){
            head = emp;
            return;
        }
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时直接将emp加入链表
        curEmp.next = emp;
    }
    //遍历链表的雇员信息
    public void list(int no){
        if(head == null){
            System.out.println("当前的第"+no+"个链表为空");
            return;
        }
        System.out.print("当前第"+no+"个链表的信息为");
        Emp curEmp = head;
        while(true){
            System.out.printf("id=%d name=%s\t", curEmp.id, curEmp.name);
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
    }
    //根据id查找雇员
    //如果查找到就返回Emp,如果没有找到就返回null
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){
                break;
            }
            if(curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
