package com.flora.stack;

import sun.nio.cs.ext.MacHebrew;

import java.util.Scanner;

/**
 * @Author qinxiang
 * @Date 2022/8/26-上午11:37
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈(入栈)");
            System.out.println("pop:表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch(key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    System.out.println("您已退出程序");
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int pop = stack.pop();
                        System.out.printf("出栈的数据是%d\n", pop);

                    }catch(Exception e){
                        //TODO:handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出");
    }
}
class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;//top表示栈顶，初始化为-1
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈-push
    public void push(int value){
        //先判断栈是否满
        if (isFull()){
            System.out.println("栈已满，不能添加数据");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop，将栈顶的数据返回
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况 遍历栈，遍历时需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i --){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
