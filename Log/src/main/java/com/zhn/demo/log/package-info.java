/**
 * 日志框架
 * JAVA日志框架概述
 *    日志用来记录应用的运行状态以及一些关键业务信息，其重要性不言而喻，通常我们借助于现有的日志框架完成日志输出。目前开源的日志框架很多，
 * 常见的有log4j、logback等，有时候我们还会碰到诸如common-logging、slf4j这些名词，这些框架有什么作用？它们之间有什么联系？在搭建应用时
 * 该如何选择合适的日志框架？对于这些问题，将会在本文中做出解释。
 * 一、日志门面与日志组件
 *    在上面提到的log4j、logback、common-logging、slf4j中，包含了2类框架，其中一类是具体用来输出日志的框架，log4j和logback属于此类，
 * 我们将他们称为“日志组件”；另两个common-logging、slf4j被归为“日志门面”，或叫做“日志接口”。
 *    简单来说，可以按照面向接口编程的思想来理解这2种组件，“日志门面”提供了标准的日志输出API，其底层如何实现，或者说采用什么日志组件，
 * 由开发者进行选择。当然，选择的日志组件必须实现了对应“日志门面”的接口。
 * 1.1.log4j与logback
 *    log4j隶属于apache，其包含一个升级产品（log4j2），是目前流行的日志组件之一。logback则是近些年来才开始流行，由于其在性能上PK掉了log4j，
 * 因此越来越多的项目开始使用logback。另外JAVA的官方类库也提供了日志组件，即JAVA UTIL LOGGING（下文简称JUL），这里我们不做重点讨论。
 *    下面直接引用一段话来表述log4j和logback的性能差距：
 *    1.某些关键操作，比如判定是否记录一条日志语句的操作，其性能得到了显著的提高。这个操作在LogBack中需要3纳秒，而在Log4J中则需要30纳秒。
 *    LogBack创建记录器（logger）的速度也更快：13微秒，而在Log4J中需要23微秒。更重要的是，它获取已存在的记录器只需94纳秒，而 Log4J需要2234纳秒，
 *    时间减少到了1/23。跟JUL相比的性能提高也是显著的。 <br>另外，LOGBack的所有文档是全面免费提供的，不象Log4J那样只提供部分免费文档而需要用户
 *    去购买付费文档。
 * 1.2.common-logging与slf4j
 *    前文提到，这2个框架均为日志门面，但common-logging（下文简称JCL）隶属于apache阵营，而slf4j与logback为同一作者。并且这两个框架与底层日志
 * 组件的绑定机制也不相同：
 *    common-logging通过动态查找的机制，在程序运行时自动找出真正使用的日志库。由于它使用了ClassLoader寻找和载入底层的日志库，导致了像OSGI这样的
 * 框架无法正常工作，因为OSGI不同的插件使用自己ClassLoader。
 * slf4j在编译时静态绑定真正的Log库，因此可以在OSGI中使用。
 * 二、如何集成slf4j
 *    在第一节中，我们解释了日志组件和日志门面的概念，同时提到了slf4j的优越性，接下来我们将讨论如何将slf4j与log4j、logback进行集成，以及如何在
 * 一个已经使用了common-logging、jul的遗留系统上无缝集成slf4j。
 * 2.1.基本集成
 *    如果是一个新系统，或者你想对遗留系统的日志框架做一个完整的替换，可以引入如下依赖快速集成：
 * 复制代码
 * <!-- 日志组件log4j与slf4j的集成依赖 -->
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>slf4j-log4j12</artifactId>
 *     <version>1.7.25</version>
 * </dependency>
 * ​
 * <!-- 日志组件logback与slf4j的集成依赖 -->
 * <dependency>
 *     <groupId>ch.qos.logback</groupId>
 *     <artifactId>logback-classic</artifactId>
 *     <version>1.7.25</version>
 * </dependency>
 * ​
 * <!-- 日志组件jul与slf4j的集成依赖 -->
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>slf4j-jdk14</artifactId>
 *     <version>1.7.25</version>
 * </dependency>
 * ​
 * <!-- 日志门面jcl与slf4j的集成依赖，将所有slf4j日志委派给jcl -->
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>slf4j-jcl</artifactId>
 *     <version>1.7.25</version>
 * </dependency>
 * 复制代码
 * 2.2.使用桥接器
 *    在实际的应用中，我们会碰到不同的组件使用不同日志框架的情况，
 * 例如Spring Framework使用的是通过日志门面commons-logging输出日志，XSocket依赖的则JUL。
 *    我们想统一它们的日志输出方式以便于管理。
 *    slf4j提供了现成的解决方案，那便是“桥接器”，它的功能是将这些不同形式的日志打印输出重定向到slf4j，然后slf4j又会根据
 * 绑定器把日志交给具体的日志实现工具。下面给出一个常用的应用桥接配置方案：
 * 复制代码
 * <!-- JUL桥接器，将jul的日志输出重定向到slf4j -->
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>jul-to-slf4j</artifactId>
 * </dependency>
 * <!-- JCL桥接器，将commos-logging的日志输出重定向到slf4j -->
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>jcl-over-slf4j</artifactId>
 * </dependency>
 * <!--log4j桥接器，将log4j的日志输出重定向到slf4j  -->
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>log4j-over-slf4j</artifactId>
 * </dependency>
 * <!-- 日志组件logback与slf4j的集成依赖，各桥接器的重定向最终流向logback -->
 * <dependency>
 *     <groupId>ch.qos.logback</groupId>
 *     <artifactId>logback-classic</artifactId>
 * </dependency>
 * 另外，使用桥接器的时候，我们需要注意避免与2.1中的jar包混淆使用，导致日志流死循环：
 * 条件	原因
 * log4j-over-slf4j.jar和slf4j-log4j12.jar同时存在
 * slf4j-log4j12.jar会将所有日志调用委托给log4j，而log4j-over-slf4j.jar又将所有对log4j的调用委托给相应的slf4j，两者形成了一个死循环.
 * jul-to-slf4j.jar和slf4j-jdk14.jar同时存在
 * slf4j-jdk14.jar会将所有日志调用委托给jdk的log，而jul-to-slf4j.jar又将所有对jul-api的调用委托给相应的slf4j，两者形成了一个死循环。
 */
package com.zhn.demo.log;