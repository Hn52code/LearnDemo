package com.zhn.demo.mode.builder;

public class Client {

    public static void main(String[] args) {

        ComputerDirector director = new ComputerDirector(new DiyComputerBuilder());

        Computer computer = director.buildComputer();

        System.out.println(computer);

    }

}
