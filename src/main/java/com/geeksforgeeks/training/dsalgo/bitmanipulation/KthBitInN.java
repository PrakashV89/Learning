package com.geeksforgeeks.training.dsalgo.bitmanipulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KthBitInN {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(KthBitInN.class, args);

		System.out.println(new KthBitInN().getKthBitInNUsingMath(2, 2));

		Thread.sleep(60000);
	}

	public int getKthBitInN(int k, int n) {
		return (n>> k-1) & 1;
	}

	public int getKthBitInNUsingMath(int k, int n) {
		return (n & 1<<k-1) != 0 ? 1 : 0;
	}

}
