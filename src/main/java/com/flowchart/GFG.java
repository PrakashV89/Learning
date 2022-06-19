package com.flowchart;

import java.math.BigInteger;

class A
{
    int a = 12;
}
class B extends A
{
    int a = 18;

    void display()
    {
        System.out.println(super.a);
    }
}
class GFG
{

    public static void main(String[] args)
    {
        BigInteger.ZERO.isProbablePrime(90);
        B b = new B();
        b.display();
    }
} 