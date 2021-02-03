package com.nuosi.memento;


import java.util.ArrayList;
import java.util.List;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name CareTaker
 * @desc 备忘录管理，保存和恢复备忘录
 * @date 2021/2/3 21:16
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}
