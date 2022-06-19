package com.flowchart;

class Base

{

    private final void flipper()

    {

        System.out.println("Base");

    }

}



public class Inheritance extends Base

{

    public final void flipper()

    {
        System.out.println("Derived");

    }

    public static void main(String[] args)

    {

        new Inheritance().flipper();

    }

}
