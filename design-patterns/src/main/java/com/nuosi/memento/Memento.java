package com.nuosi.memento;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name Memento
 * @desc 包含被保存和恢复的对象状态
 * @date 2021/2/3 21:07
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
