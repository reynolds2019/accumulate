package com.nuosi.lowcode.turing;

import org.junit.Test;

import java.io.*;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name BrainFuck
 * @desc BrainFuck解释器，验证图灵完备
 * @date 2021/2/4 11:29
 */
public class BrainFuck {
    /**
     * BrainFuck语法
     */
    private static class Syntax {
        public final static char NEXT = '>';
        public final static char PREVIOUS = '<';
        public final static char PLUS = '+';
        public final static char MINUS = '-';
        public final static char OUTPUT = '.';
        public final static char INPUT = ',';
        public final static char BRACKET_LEFT = '[';
        public final static char BRACKET_RIGHT = ']';
    }

    /**
     * BrainFuck使用内存
     */
    private byte[] data;
    /**
     * 指向数据的数据指针
     */
    private int dataPointer = 0;

    /**
     * 指向字符串值的字符指针
     */
    private int charPointer = 0;

    /**
     * 当前行位置
     */
    private int lineCount = 0;

    /**
     * 当前列位置
     */
    private int columnCount = 0;

    /**
     * 打印输出
     */
    private OutputStream outWriter;

    /**
     * BrainFuck构造器
     */
    public BrainFuck(int cells) throws IOException {
        initate(cells);
    }

    /**
     * 初始化
     */
    private void initate(int cells) throws IOException {
        data = new byte[cells];
        dataPointer = 0;
        charPointer = 0;
        if(outWriter!=null){
            outWriter.close();
        }
        outWriter = new PrintStream(System.out);
    }

    /**
     * 编译器解释字符串
     */
    public void interpret(String str) throws Exception {
        for (; charPointer < str.length(); charPointer++){
            interpret(str.charAt(charPointer), str.toCharArray());
        }
        initate(data.length);
    }

    /**
     * 编译器解释字符
     */
    protected void interpret(char c, char[] chars) throws Exception {
        switch (c) {
            case Syntax.NEXT:
                // 数据指针右移加一
                if ((dataPointer + 1) > data.length) {
                    throw new Exception("Error on line " + lineCount + ", column " + columnCount + ":"
                            + "data pointer (" + dataPointer
                            + ") on postion " + charPointer + "" + " out of range.");
                }
                dataPointer++;
                break;
            case Syntax.PREVIOUS:
                // 数据指针左移减一
                if ((dataPointer - 1) < 0) {
                    throw new Exception("Error on line " + lineCount + ", column " + columnCount + ":"
                            + "data pointer (" + dataPointer
                            + ") on postion " + charPointer + " " + "negative.");
                }
                dataPointer--;
                break;
            case Syntax.PLUS:
                // 数据指针处的字节增加一
                data[dataPointer]++;
                break;
            case Syntax.MINUS:
                // 数据指针处的字节减一
                data[dataPointer]--;
                break;
            case Syntax.OUTPUT:
                // 当前索引处输出字符
                outWriter.write(data[dataPointer]);
                break;
            case Syntax.INPUT:
                // 接受一个字节的输入，并将其值存储
                data[dataPointer] = (byte)System.in.read();
                break;
            case Syntax.BRACKET_LEFT:
                // 如果指针指向的单元值为零，向后跳转到对应的]指令的次一指令处
                // 开始重复「始……终」之间的指令，直到你读到「始」之前盯着的那个格子里的数字变成 0 为止
                if (data[dataPointer] == 0) {
                    int i = 1;
                    while (i > 0) {
                        char c2 = chars[++charPointer];
                        if (c2 == Syntax.BRACKET_LEFT)
                            i++;
                        else if (c2 == Syntax.BRACKET_RIGHT)
                            i--;
                    }
                }
                break;
            case Syntax.BRACKET_RIGHT:
                // 如果指针指向的单元值不为零，向前跳转到对应的[指令的次一指令处
                // 如果当前格子里的数字为 0，就跳过，否则回头到「始」那里
                int i = 1;
                while (i > 0) {
                    char c2 = chars[--charPointer];
                    if (c2 == Syntax.BRACKET_LEFT)
                        i--;
                    else if (c2 == Syntax.BRACKET_RIGHT)
                        i++;
                }
                charPointer--;
                break;
        }
        columnCount++;
    }

    public static void main(String[] args) {
        String str = "++++++++++[>+>+++>+++++++>++++++++++<<<<-]>>>++.>+.+++++++..+++.<<++.>+++++++++++++++.>.+++.------.--------.<<+.<.";
        try {
            BrainFuck brainFuck = new BrainFuck(5); //最小值5
            brainFuck.interpret(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
