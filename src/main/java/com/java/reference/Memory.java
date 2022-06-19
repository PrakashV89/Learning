package com.java.reference;

public class Memory {
    private long bytes;
    private long kb;
    private long mb;
    private long gb;

    private Memory(long bytes){
        this.bytes = bytes;
        this.kb = bytes/1024;
        this.mb = kb/1024;
        this.gb = mb/1024;
    }

    public static Memory ofBytes(long bytes){
        return new Memory(bytes);
    }

    @Override
    public String toString() {
        return "Memory{" +
                "bytes=" + bytes +
                ", kb=" + kb +
                ", mb=" + mb +
                ", gb=" + gb +
                '}';
    }
}
