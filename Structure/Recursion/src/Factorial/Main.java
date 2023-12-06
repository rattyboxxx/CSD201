
package Factorial;

public class Main {
    public static long Fac(long n) {
        if (n <= 1) return 1;
        return n*Fac(n-1);
    }
    
    public static void main(String[] args) {
        int n = 10;
        int i, ans;
        for (i = 1, ans = 1; i <= n; ans *= i, i++) {}
        System.out.println(ans);
    }
}
