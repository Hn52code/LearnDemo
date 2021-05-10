package com.zhn.demo.mode.builder;

public class DiyComputerBuilder extends ComputerBuilder {

    protected Computer computer = new Computer();

    @Override
    void cpu() {
        System.out.println(" div 自选cpu");
        this.computer.setCPU("amd");
    }

    @Override
    void mainboard() {
        System.out.println(" div 自选主板");
        this.computer.setMainboard("华硕");
    }

    @Override
    void memory() {
        System.out.println(" div 自选内存");
        this.computer.setCPU("海盗船");
    }

    @Override
    Computer create() {
        return this.computer;
    }
}
