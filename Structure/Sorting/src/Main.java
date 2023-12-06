
public class Main {
    
    static void quickSort(int[] a, int start, int end, int check) {
        int ktra = 0;
        for (int i = start; i < end; i++) {
            if (a[i] > a[i+1]){
                ktra = 1;
                break;
            }
        }
        if (ktra == 0) return;
        int pivot = start;
        int up = end, down = start, t;
        while (up > down) {
            while (up >= down && a[down] <= a[pivot]) down++;
            while (up >= down && a[up] > a[pivot]) up--;
            if (up > down) {
                t = a[up];
                a[up] = a[down];
                a[down] = t;
            }
        }
        t = a[start];
        a[start] = a[up];
        a[up] = t;
        display(a);
        if (up+1 <= end) quickSort(a, up+1, end, 1);
    }
    
    static void display(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) System.out.println(a[i]);
            else System.out.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] a = {6, 1, 10, 2, 9, 3, 8, 4, 7, 5};
        display(a);
        quickSort(a, 0, a.length-1, 1);
    }
    
}
