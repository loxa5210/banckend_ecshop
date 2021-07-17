package com.idv.lili.ecshop;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

//@SpringBootTest
class EcShopApplicationTests {

	@Test
	void contextLoads() {

		LinkedList<Integer> i =  new LinkedList();
//
//
//		for (int i = 1; i <= 100; i++) {
//
//			if (i % 3 == 0 && i % 5 == 0) {
//				System.out.print(i + " = # ;");
//			} else if ((i % 3 == 0)) {
//				System.out.print(i + " = ! ;");
//
//			} else if (i % 5 == 0) {
//				System.out.print(i + " = @ ;");
//			} else {
//				System.out.print(i + " = nil ;");
//
//			}
//		}
	}

	int getFirstNode(List<Integer> list, int target){

		//target 10
		boolean run = true;

		LinkedList<Integer> newList = new LinkedList<>();

		newList.add(2);
		newList.add(8);


		for (int i = 0; i < list.size(); i++) {

			int temp = list.get(i);

			if (target <= temp) {
				if (run) {
					newList.add(target);
					run = false;
				}
			}
			newList.add(temp);
		}

		return newList.get(0);

	}

	public int  test3(int x){
		int A[] = {0,2,4,6,8,10,12,14};

		int high = 7;
		int low = 0;
		while (high >low){
			int mid = (high + low) / 2 ;
			if(A[mid] <=x){//=-1
				low = mid +1;
			}
			else {
				high = mid;
			}
		}
		return A[high];
	}

	public void test2(){

		int a [] = { 1 ,3 ,5 ,7 ,9 ,8 ,6 ,4, 2};

		int n =9 ,temp;


		for (int i=0 ; i<n ; i=i+1){

			temp = a[i];

			a[i] = a [n-i-1];
			a [n-i-1] = temp;

		}

		for( int i=0 ; i<=n/2 ; i = i+1)
			System.out.printf("%d %d" , a[i] , a[n-i-1]);


	}

	public int  k (int a[] , int n){
		if(n>=0){
			return k(a , n-1 + a[n]);
		}else {
			return 0;
		}
	}


	public int  ttt (int n){
		int a[] = {5,4,3,2,1};

		return k(a,n);
	}

}
