package lh.cn.edu.henu.upto.designMode;

/**
 * Created by LiHao on 2018/7/15.
 */

public class Singleton {

        //在 new UpTo()时禁止指令重排，避免 instance没有初始化成员变量，形成实例就被其他进程调用
    private static volatile Singleton instance = null;
    //私有化构造函数，避免外界通过该构造方法创建多个实例
    private Singleton(){
    };
    public static Singleton getInstance(){
        //只有instance为null，才会进入synchronized
        if(instance == null){
            //锁住UpTo类，一次只能有一个调用
            synchronized (Singleton.class){
                //判断instance是否为空，避免创建多个实例
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
