package com.nuosi.memento;

/**
 * @author nuosi fsofs@163.com
 * @version 0.1.0
 * @name Main
 * @desc 备忘录模式：内部状态、保存、恢复
 * @date 2021/2/3 21:12
 */
public class Main {

    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        careTaker.add(originator.getMemento());
        originator.setState("State #2");
        careTaker.add(originator.getMemento());

        System.out.println("Current State: " + originator.getState());
        originator.restoreMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
    }
}
