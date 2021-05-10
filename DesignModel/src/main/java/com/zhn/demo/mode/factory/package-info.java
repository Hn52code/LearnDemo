/**
 * 设计模式：
 * 工厂模式：简单工厂模式（静态工厂模式），方法工厂模式，抽象工厂模式
 * 一个例子：
 * 订购披萨，披萨有很多种类(apple披萨，banana披萨)，制作流程，完成订购。
 * example包：是一个传统的方式编码
 * simple包：简单工厂方式
 * method包：方法工厂方式  新增了需求，披萨分为伦敦（apple披萨，banana披萨）和北京披萨（apple披萨，banana披萨）；
 *           可以使用简单工厂模式，但是随业务增加，会造成过多的工厂
 * abs包：抽象工厂方式，对method包写法改进
 */
package com.zhn.demo.mode.factory;