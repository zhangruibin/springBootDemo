package com.example.test.baseTest;

/**
 * @ClassName Base
 * @Description TODO
 * @Author Administrator
 * @Date 2019/7/23 14:04
 * @Version
 */
public class Base {
    private String baseName = "base";
    public Base() {
        callName();
    }
    public void callName() {
        System. out. println(baseName);
    }
    static class Sub extends Base {
        private String baseName = "sub";
        public void callName() {
            System. out. println (baseName) ;
        }
    }
    public static void main(String[] args) {
        Base b = new Sub();
    }

}
