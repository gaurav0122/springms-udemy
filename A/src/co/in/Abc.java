package co.in;

import java.util.Scanner;

public class Abc {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a numbner");
		int n = sc.nextInt();
		System.out.println(n);
		int add =0;
		while(n>0) {
			add+=(n%10);
			n/=10;
		}
		System.out.println(add);
	}
}
