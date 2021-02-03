package com.nuosi.memento;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name Originator
 * @desc 创建并在Memento对象中存储状态
 * @date 2021/2/3 21:14
 */
public class Originator {
    private Memento memento;

    public void setState(String state){
        this.memento = new Memento(state);
    }

    public String getState(){
        return memento.getState();
    }

    public Memento getMemento(){
        return memento;
    }

    public Memento restoreMemento(Memento memento){
        this.memento = memento;
        return memento;
    }
}
