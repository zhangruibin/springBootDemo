package com.example.test.threadPool;

import java.util.concurrent.*;

/*
* @ClassName TestThreadPool
*@Description TODO  测试线程池的基本使用方法
*@Author zhangrui
*@Date 2018/12/5 9:48
*@Version 
*/
public class TestThreadPool {
    public static void main(String[] args) {
        //1.声明线程池执行器
        //参数，corePoolSize,maxmumPoolSize,keepAliveTime,TimeUnit,queue.
        //其中queue通常选择ArrayBlockingQueue，LinkedBlockingQueue，SynchronousQueue.
        //TimeUnit有七种取值，可以精确到NanoSeconds(纳秒级别)
        //根据日志的打印，就是先将线程加入到corePoolSize中，当corePoolSize满了之后加入到队列中，队列满了就向maxmumPoolSize-corePoolSize中加入。
        //当值达到maxmumPoolSize+queueSize之后，再有任务就会启用abort策略，abort策略有四种，可参考源码理解
        //FIXME 这种创建线程池的方法是不推荐使用的，因为当线程量超过最大存储容量的时候会抛出拒绝任务异常
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        //i=maxmumPoolSize+queueSize
        for (int i = 0;i<15;i++){
            //2.获取线程执行的任务
            Mytask mytask = new Mytask(i);
            //3.将线程放入执行器中执行，也可以使用submit方法，但是submit方法底层调用的也是execute方法，只是submit有参数返回值
            executor.execute(mytask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完别的任务数目："+executor.getCompletedTaskCount()
                    +"，线程池中存活线程数目："+executor.getActiveCount());
        }
        //4.停止执行器，shutdown是在线程执行完任务后结束，shutdownNow会尝试中断线程中所执行的任务停止执行器
        executor.shutdown();
    }


    //声明一个内部类，实现Runnable，声明线程任务
    private static class Mytask implements Runnable{
        private int taskNum;
        public Mytask(int num){
            this.taskNum = num;
        }
        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }
    }
    //在java doc中，并不提倡直接使用ThreadPoolExecutor，而是使用Executors类中提供的几个静态方法来创建线程池,三种：
    //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
    //Executors.newCachedThreadPool();
    // 创建容量为1的缓冲池
    //Executors.newSingleThreadExecutor();
    //创建固定容量大小的缓冲池
    //Executors.newFixedThreadPool(int);
/*从它们的具体实现来看，它们实际上也是调用了ThreadPoolExecutor，只不过参数都已配置好了。

  newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，它使用的LinkedBlockingQueue；

  newSingleThreadExecutor将corePoolSize和maximumPoolSize都设置为1，也使用的LinkedBlockingQueue；

  newCachedThreadPool将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，使用的SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程。

  实际中，如果Executors提供的三个静态方法能满足要求，就尽量使用它提供的三个方法，因为自己去手动配置ThreadPoolExecutor的参数有点麻烦，要根据实际任务的类型和数量来进行配置。

  另外，如果ThreadPoolExecutor达不到要求，可以自己继承ThreadPoolExecutor类进行重写。
  */
    //1.8的之后加入了另一种创建方式newWorkStealingPool(int parallelism)/newWorkStealingPool()
    //方法注解的大概意思是：
    // 创建一个线程池，该线程池维护足够的线程以支持给定的并行级别，并且可以使用多个队列来减少争用。并行性级别对应于积极参与或可用于任务处理的线程的最大数量。实际的线程数可以动态地增长和收缩。工作窃取池不能保证提交任务的执行顺序。
    //
    // TODO 1.ThreadPoolExecutor的继承实现关系
    // Executor是一个顶层接口，在它里面只声明了一个方法execute(Runnable)，返回值为void，参数为Runnable类型，从字面意思可以理解，就是用来执行传进去的任务的；
    // 然后ExecutorService接口继承了Executor接口，并声明了一些方法：submit、invokeAll、invokeAny以及shutDown等；
    // 抽象类AbstractExecutorService实现了ExecutorService接口，基本实现了ExecutorService中声明的所有方法；
    // 然后ThreadPoolExecutor继承了类AbstractExecutorService。
    // 在ThreadPoolExecutor类中有几个非常重要的方法：
    // 1.execute():execute()方法实际上是Executor中声明的方法，在ThreadPoolExecutor进行了具体的实现，这个方法是ThreadPoolExecutor的核心方法，通过这个方法可以向线程池提交一个任务，交由线程池去执行;
    // 2.submit():submit()方法是在ExecutorService中声明的方法，在AbstractExecutorService就已经有了具体的实现，在ThreadPoolExecutor中并没有对其进行重写，这个方法也是用来向线程池提交任务的，但是它和execute()方法不同，它能够返回任务执行的结果，去看submit()方法的实现，会发现它实际上还是调用的execute()方法，只不过它利用了Future来获取任务执行结果（Future相关内容将在下一篇讲述）;
    // 3.shutdown():在线程执行完任务之后，关闭线程池;
    // 4.shutdownNow()：如果当前线程没有执行完毕的话就尝试中断当前线程所执行的任务，然后关闭线程池。


    //TODO 2.ThreadPoolExecutor中的几个重要参数解释：
    //1.corePoolSize：核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
    //2.maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
    //3.keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0；
    //4.unit：参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
    //TimeUnit.DAYS;               //天
    //TimeUnit.HOURS;             //小时
    //TimeUnit.MINUTES;           //分钟
    //TimeUnit.SECONDS;           //秒
    //TimeUnit.MILLISECONDS;      //毫秒
    //TimeUnit.MICROSECONDS;      //微妙
    //TimeUnit.NANOSECONDS;       //纳秒
    //5.workQueue：一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，一般来说，这里的阻塞队列有以下三种选择：
    //5.1.ArrayBlockingQueue;
    //5.2.LinkedBlockingQueue;
    //5.3.SynchronousQueue;
    //6.threadFactory：线程工厂，主要用来创建线程；
    //7.handler：表示当拒绝处理任务时的策略，有以下四种取值：
    //7.1.ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
    //7.2.ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
    //7.3.ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
    //7.4.ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
    //TODO 3.ThreadPoolExecutor中的几个重要的成员变量及解释
    //1.任务缓存队列，用来存放等待执行的任务
    // private final BlockingQueue<Runnable> workQueue;
    //2.线程池的主要状态锁，对线程池状态（比如线程池大小、runState等）的改变都要使用这个锁
    //private final ReentrantLock mainLock = new ReentrantLock();
    //3.用来存放工作集
    //private final HashSet<Worker> workers = new HashSet<Worker>();
    //4.线程存活时间
    //private volatile long  keepAliveTime;
    //5.是否允许为核心线程设置存活时间
    //private volatile boolean allowCoreThreadTimeOut;
    //6.核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
    //private volatile int   corePoolSize;
    //7.线程池最大能容忍的线程数
    //private volatile int   maximumPoolSize;
    //8.线程池中当前的线程数
    //private volatile int   poolSize;
    //9.任务拒绝策略
    //private volatile RejectedExecutionHandler handler;
    //10.线程工厂，用来创建线程
    //private volatile ThreadFactory threadFactory;
    //11.用来记录线程池中曾经出现过的最大线程数
    //private int largestPoolSize;
    //12.用来记录已经执行完毕的任务个数
    //private long completedTaskCount;
    //TODO 借用网上的例子进行解释：
    //假如有一个工厂，工厂里面有10个工人，每个工人同时只能做一件任务。
    //因此只要当10个工人中有工人是空闲的，来了任务就分配给空闲的工人做；
    //当10个工人都有任务在做时，如果还来了任务，就把任务进行排队等待；
    //如果说新任务数目增长的速度远远大于工人做任务的速度，那么此时工厂主管可能会想补救措施，比如重新招4个临时工人进来；
    //然后就将任务也分配给这4个临时工人做；
    //如果说着14个工人做任务的速度还是不够，此时工厂主管可能就要考虑不再接收新的任务或者抛弃前面的一些任务了。
    //当这14个工人当中有人空闲时，而新任务增长的速度又比较缓慢，工厂主管可能就考虑辞掉4个临时工了，只保持原来的10个工人，毕竟请额外的工人是要花钱的。
    //这个例子中的corePoolSize就是10，而maximumPoolSize就是14（10+4）。
    //也就是说corePoolSize就是线程池大小，maximumPoolSize在我看来是线程池的一种补救措施，即任务量突然过大时的一种补救措施。
    //不过为了方便理解，在本文后面还是将corePoolSize翻译成核心池大小。
    // FIXME 文章写的挺好的，但是是针对JDK1.6进行解析的，有的地方在JDK1.8中有所改动，比如创建线程池的方式，以及ThreadPoolExecutor内部方法的实现方式
    //TODO 接下来是线程池的基础使用：
    //基础使用代码：
    //延伸--线程池的正确打开方式
    // FIXME  testCygwin
    // XXX
}
