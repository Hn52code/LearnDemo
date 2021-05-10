package com.zhn.demo.spring.basic.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 注解方式：声明一个切面
 * 1.使用@Component 将改切面加入IOC容器中；
 * 2.使用@Aspect将其声明为aop切面；
 * 3.@Pointcut定义切入点方法。切入点表达式在该注解中写入；
 * 4.@Before @After @AfterReturning 等等将被注释的方法配置advice事件通知。
 * 5.@Order() 表示AOP切面的执行顺序 数字越小优先级越高。
 * 在Spring里，在使用AspectJ提供的注解声明通知之前，首先在Spring的配置类里声明@EnableAspectJAutoProxy
 * 启用AspectJ组件，之后Spring AOP将扫描和管理被AspectJ的@Aspect注解过的切面bean。
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggingAspect {

//    @Before("execution(* com.zhn.pro.demo.spring.aop.hello.MathService.*(..)))")
//    public void beforeMethod(JoinPoint point) {
//        System.out.println("开始啦。。");
//
//    }


    @Pointcut("execution(* com.zhn.pro.demo.spring.aop.hello.*.*(..))")
    public void pointMethod() {
        // 该方法用于申明切入点表达式，一般无需实现任何逻辑功能。
        // ** 切入点可以在外部引用，通过方法名调用，具体需注意外部使用路径。
    }


    /* ---------------------事件通知 ------------------*/

    /**
     * 前置事件
     * 前置通知在连接点被执行前就被执行了，除非抛出异常，否则前置通知不能够阻止方法的继续执行。
     * 请看下面的代码片段，这个切面简单地打印了方法名称，在连接点执行之前
     */
    @Before("pointMethod()")
    public void beforeMethod2(JoinPoint point) {
        System.out.println("开始啦。。");

    }

    /**
     * 使用@After注解来声明后置通知。在被匹配的连接点执行完后，执行后置通知，不管连接点执行是否有抛出异常.
     * 从另一个角度来看，后置通知跟finally语句块相似。如果你只是想在连接点方法正常返回时执行通知(异常时不执行通知)，
     * 应该使用@AfterReturning注解声明的返回型通知。如果你只是想在连接点方法抛出异常后执行通知（正常返回时不执行），
     * 应该使用@AfterThrowing注解声明的异常通知。
     * 假设当一个新的Foo的实例被创建时，我们希望触发一个通知事件，通知监控的组件，我们可以从FooDao发布一个事件，但是这会破坏对象的单一责任原则，
     * 这时面向切面遍程可以提供另一种方式，我们可以通过定义下面的这样一个切面来达到这种效果。
     */

    @After("pointMethod()")
    public void afterMethod(JoinPoint point) {

    }

    @AfterReturning("pointMethod()")
    public void afterRunningMethod(JoinPoint point) {

    }

    @AfterThrowing("pointMethod()")
    public void afterThrowingMethod(JoinPoint point) {

    }

    /**
     * ----------------环绕通知-----------------------
     * 使用 @around 绕通知包围着连接点，例如在目标方法调用前后执行切面逻辑。
     * 环绕通知是最强大的一种通知，在目标方法执行的前后可以执行用户自定义的切面逻辑，
     * 也可以在环绕通知里决定是否继续执行连接点方法或者跳过连接点直接在环绕通里返回值或者抛出异常。
     * <p>
     * 上面切面所示的通知就会被触发。这个方法接收一个ProceedingJointPoint类型的参数，我们可以在使用这个参数执行目标方法之前，
     * 执行我们的切面逻辑。在上面的例子里，我们只时简单地保存了目标方法调用的开始时间。其次，由于目标方法可以返回任意类型的对象，
     * 所以这个通知的返回值类型是Object，有一点需要注意的是，如果目标方法返回类型是void,通知的必须返回一个null。当目标方法调用完成后，
     * 我们可以计算调用时间并打印，然后把返回值返回给调用者。
     */
    @Around("pointMethod()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        return null;
    }
}
