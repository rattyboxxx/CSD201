import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

class Graph {

    // All search function
    void breadthFirst(char x) {
        int k = (int) (x - 65);
        Queue my = new Queue();
        boolean[] b = new boolean[n];
        Arrays.fill(b, true);
        my.enqueue(k);
        b[k] = false;
        while (!my.isEmpty()) {
            int p = my.dequeue();
            for (int i = 0; i < n; i++) {
                if (b[i] && a[i][p] != 0) {
                    b[i] = false;
                    my.enqueue(i);
                }
            }
            visit(p);
        }
    }

    void breadthFirst(int k) {
        breadthFirst((char) (k + 65));
    }

    void depthFirst(boolean[] b, char x) {
        int k = (int) (x - 65);
        visit(k);
        b[k] = false;
        for (int i = 0; i < n; i++) {
            if (b[i] && a[i][k] != 0) {
                depthFirst(b, (char) (i + 65));
            }
        }
    }

    void depthFirst(char x) {
        int k = (int) (x - 65);
        boolean[] b = new boolean[n];
        Arrays.fill(b, true);
        b[k] = false;
        depthFirst(b, x);
    }

    void depthFirst(int x) {
        depthFirst((char) (x + 65));
    }

    // Dijkstra algorithm
    int degree(int i) { // Bac cua 1 dinh nao do
        int s, j;
        s = 0;
        for (j = 0; j < n; j++) {
            s += a[i][j];
        }
        s += a[i][i];
        return s;
    }

    void dijkstra(int fro, int to, RandomAccessFile f) throws Exception {
        int INF = 99; // infinity value
        boolean[] S = new boolean[n]; // kiem tra xem da duyet dinh chua
        int[] d = new int[n]; // luu gia tri duong di ngan nhat tai dinh do
        int[] p = new int[n]; // luu gia tri dinh gan no nhat

        for (int i = 0; i < n; i++) {
            S[i] = false;
            d[i] = a[fro][i];
            p[i] = fro;
        }

        S[fro] = true; // da duyet dinh fro

        // Duyet cac dinh, tim min quang duong, them vao tap ss
        int k, t;
        while (true) {
            k = -1;
            t = INF;
            for (int i = 0; i < n; i++) {
                if (S[i] == true) {
                    continue;
                }
                if (d[i] < t) {
                    k = i;
                    t = d[i];
                }
            }
            if (k == -1) {
                return; // no solution
            }
            S[k] = true; // Da duyet dinh k
            if (k == to) {
                break;
            }
            // Recalculate d[i]
            for (int i = 0; i < n; i++) {
                if (S[i] == true) {
                    continue;
                }
                if (d[i] > d[k] + a[k][i]) {
                    d[i] = d[k] + a[k][i];
                    p[i] = k;
                }
            }
        }

        // truy nguoc lai cac dinh tu to ve fro
        Stack s = new Stack();
        int x = to;
        while (true) {
            s.push(x);
            if (x == fro) {
                break;
            }
            x = p[x]; // truy nguoc ve dinh lien truoc no
        }

        ArrayList<Integer> pp = new ArrayList<>(); // Luu tru cac dinh tu fro den to
        while (!s.isEmpty()) { // loi tu stack s sang pp
            x = s.pop();
            pp.add(x);
        }

        // In ra cac dinh tu fro den to
        f.writeBytes("" + v[pp.get(0)]);
        for (int i = 1; i < pp.size(); i++) {
            f.writeBytes("   " + v[pp.get(i)]);
        }
        f.writeBytes("\r\n");
        
        // In ra quang duong nho nhat tu fro den to
        f.writeBytes("" + d[pp.get(0)]);
        for (int i = 1; i < pp.size(); i++) {
            f.writeBytes("   " + d[pp.get(i)]);
        }
        f.writeBytes("\r\n");
        
        // In ra cac dinh duoc chon theo Dijkstra
        f.writeBytes("" + v[ss.get(0)]);
        for (int i = 1; i < ss.size(); i++) {
            f.writeBytes("   " + v[ss.get(i)]);
        }
        f.writeBytes("\r\n");
    }

    // All Euler function
    boolean isUndirected() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != a[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isEvenDegree() {
        int bac;
        for (int i = 0; i < n; i++) {
            bac = 0;
            for (int j = 0; j < n; j++) {
                bac += a[i][j];
            }
            if (bac % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    boolean isConnected() {
        boolean[] pushed = new boolean[20];

        for (int i = 0; i < n; i++) {
            pushed[i] = false;
        }
        Stack s = new Stack();
        s.push(0);
        pushed[0] = true;

        int r;
        while (!s.isEmpty()) {
            r = s.pop();
            for (int i = 0; i < n; i++) {
                if (i == r) {
                    continue;
                }
                if (!pushed[i] && a[r][i] > 0) {
                    s.push(i);
                    pushed[i] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!pushed[i]) {
                return false;
            }
        }
        return true;
    }

    void checkEulerCycle(RandomAccessFile f) throws Exception {
        // Check directed
        if (isUndirected()) {
            f.writeBytes(
                    "The graph is undirected.\r\n"
            );
        } else {
            f.writeBytes("The graph is directed.\r\n");
        }
        // Check connected
        if (isConnected()) {
            f.writeBytes(
                    "The graph is connected.\r\n"
            );
        } else {
            f.writeBytes("The graph is not connected.\r\n");
        }
        // Check even degree
        if (isEvenDegree()) {
            f.writeBytes(
                    "All vertices have even degree.\r\n"
            );
        } else {
            f.writeBytes("The graph has a vertex with odd degree\r\n");
        }
        // Check Euler's cycle
        if (isUndirected() && isConnected() && isEvenDegree()) {
            f.writeBytes(
                    "Conditions for Euler's cycle are satisfied.\r\n"
            );
        } else {
            f.writeBytes("Conditions for Euler's cycle are not satisfied.\r\n");
        }
    }

    boolean hasEulerCycle() {
        boolean ok = true;
        if (!isUndirected()) {
            System.out.println("The graph is directed.\r\n");
            ok = false;
        }
        if (!isConnected()) {
            System.out.println("The graph is not connected.\r\n");
            ok = false;
        }
        if (!isEvenDegree()) {
            System.out.println("The graph has a vertex with odd degree\r\n");
            ok = false;
        }
        if (!ok) {
            System.out.println("Conditions for Euler's cycle are not satisfied.\r\n");
            return false;
        }
        return true;
    }

    // Chua phan tich ti nao
    void EulerCycle(int k, RandomAccessFile f) throws Exception {
        if (k >= n) {
            return;
        }
        if (!hasEulerCycle()) {
            return;
        }
        Stack s = new Stack();
        int[][] b = new int[20][20];
        int[] eu = new int[20];
        int m;
        int i, j, r, t;
        int[] x = new int[50];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                b[i][j] = a[i][j];
            }
        }
        s.push(k); // Dua dinh k vao Stack
        m = 0; // Ban dau chu trinh chua co phan tu nao
        t = 0;
        x[0] = k;
        while (!s.isEmpty()) {
            r = s.top();
            i = 0;
            while (i < n && b[r][i] == 0) {
                i++; // Tim i dau tien de b[r][i] != 0
            }
            if (i == n) { // r da la dinh co lap, dua r vao chu trinh Euler
                eu[m++] = r;
                s.pop(); // Lay dinh co lap ra khoi Stack
            } else {
                x[++t] = i;
                s.push(i);
                b[r][i]--; // Loai canh (i,r) khoi do thi
                b[i][r]--; // Loai canh (i,r) khoi do thi
            }
        }
        // In chu trinh Euler
        for (i = 0; i < m; i++) {
            f.writeBytes(v[eu[i]] + "  ");
        }
        f.writeBytes("\r\n");
    }
}
