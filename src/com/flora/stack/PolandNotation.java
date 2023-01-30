package com.flora.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author qinxiang
 * @Date 2022/8/26-下午6:33
 */
public class PolandNotation {
    public static void main(String[] args) {
        //(3+4)*5-6
        /*String suffixExpression = "3 4 + 5 * 6 -";
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);
        int calculate = calculate(rpnList);
        System.out.println("计算的结果=" + calculate);

         */
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + parseSuffixExpressionList);
        int calculate = calculate(parseSuffixExpressionList);
        System.out.println("后缀表达式的运算结果是"+ calculate);


    }
    //将得到的中缀表达式对应的list->后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<String>();
        //遍历ls
        for(String item: ls){
            //如果是一个数，入s2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号)，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                //当item的优先级小于等于s1栈顶运算符,将s1栈顶的运算符弹出并加入到s2中，再次转到(4,1)与s1中新的栈顶运算符相比较
                //比较优先级的方法
                while(s1.size()!=0&& Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并压入s2
        while(s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//注意因为是存放到list，因此按顺序输出就是对应的后缀表达式对应的list
    }
    //方法：将中缀表达式转换成对应的List
    public static List<String> toInfixExpressionList(String s){
        //定义一个List,存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历到一个字符，就放入到c
        do{
            //如果是非数字，加入到ls
            if((c=s.charAt(i))< 48 || (c=s.charAt(i)) > 57){
                ls.add(""+ c);
                i++;
            }else{
                str="";//先将str置成空；'0'[48]->'9'[57] ASCII码
                while(i < s.length()&&(c=s.charAt(i))>= 48 && (c=s.charAt(i)) <= 57){
                    str+=c;
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;
    }
    //将一逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele: split){
            list.add(ele);
        }
        return list;
    }
    //运算
    public static int calculate(List<String> ls){
        //创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        for(String item: ls){
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")){
                stack.push(item);
            }else{
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1/num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}
//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getValue(String operation){
        int result = 0;
        switch(operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在改运算符");
                break;
        }
        return result;
    }
}
