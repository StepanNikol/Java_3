package org.example;


public class TestClass {
    @BeforeSuit
    public void init(){
        System.out.println("Starting execution");
    }
    @Test(priority = 7)
    public void test1(){
        System.out.println("Test #1");
    }
    @Test(priority = 1)
    public void test2(){
        System.out.println("Test #2");
    }
    @Test(priority = 10)
    public void test3(){
        System.out.println("Test #3");
    }
    @Test(priority = 6)
    public void test4(){
        System.out.println("Test #4");
    }
    @Test(priority = 2)
    public void test5(){
        System.out.println("Test #5");
    }
    @AfterSuit
    public void ending(){
        System.out.println("Execution has ended");
    }
}
