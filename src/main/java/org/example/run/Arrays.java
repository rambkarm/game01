package org.example.run;

public class Arrays {
    public static void main(String[] args) {
        int[] aa = {10, 5, 7};
        System.out.println(aa[0] + " " + aa[1] + " " + aa[2]);
        for (int i = 0; i < aa.length; i++) {
            System.out.print(aa[i] + " ");
        }
        int[] arr = new int[5];
        int[] arrInit = {1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 2 + arrInit[i];
        }
        for (int a : arr) {
//            System.out.println(a);
        }
    }
}

