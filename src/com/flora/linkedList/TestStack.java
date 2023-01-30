package com.flora.linkedList;

import java.util.Stack;

/**
 * @Author qinxiang
 * @Date 2022/8/25-下午3:03
 */

public class TestStack {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        while(stack.size() > 0){
            System.out.println(stack.pop());//pop就是将栈顶的数据取出
        }
    }
}
