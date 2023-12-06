/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    
    void addLast(String xTitle, int xPrice, int xType) {
        //You should write here appropriate statements to complete this function.
        if (xTitle.charAt(0) == 'A') return;
        Node node = new Node(new Book(xTitle, xPrice, xType));
        if (head == null) head = tail = node;
        else {
            tail.next = node;
            tail = node;
        }
    }

    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    void addAfterIndex(Node node, int index) {
        int count = 0;
        Node cur = head;
        while (cur != null && count != index) {
            count++;
            cur = cur.next;
        }
        if (cur != null) {
            node.next = cur.next;
            cur.next = node;
        }
    }
    
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Book x, y, z;
        x = new Book("X", 1, 2);
        y = new Book("Y", 2, 3);
        z = new Book("Z", 3, 4);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        addAfterIndex(new Node(x), 1-1);
        addAfterIndex(new Node(y), 2-1);
        addAfterIndex(new Node(z), 4-1);
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    void deleteIndex(int index) {
        int pos = 0;
        Node cur = head;
        while (pos < index - 1) {
            cur = cur.next;
            ++pos;
        }
        cur.next = cur.next.next;
    }
    
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        Node cur = head; int xType = 0, count = 0;
        while (cur != null) {
            ++count;
            if (count == 3) {
                xType = cur.info.type;
                break;
            }
            cur = cur.next;
        }
        
        cur = head; count = 0;
        while (cur != null) {
            if (cur.info.type > xType) {
                deleteIndex(count);
                break;
            }
            else {
                ++count;
                cur = cur.next;
            }
        }
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    int size() {
        Node cur = head;
        int k = 0;
        while (cur != null) {
            cur = cur.next;
            ++k;
        }
        return k;
    }
    
    Node pos(int k) {
        int i = 0;
        Node p = head;
        while (p != null) {
            if (i == k) {
                return (p);
            }
            i++;
            p = p.next;
        }
        return (null);
    }
    
    void sort(int k, int h) {
        if (k > h) {
            return;
        }
        if (k < 0) {
            k = 0;
        }
        int n = size();
        if (h > n) {
            h = n;
        }
        Node u = pos(k);
        Node v = pos(h);
        Node pi, pj;
        Book x;
        for (pi = u; pi != v; pi = pi.next) {
            for (pj = pi.next; pj != v; pj = pj.next) {
                if (pj.info.price < pi.info.price) {
                    x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
            }
        }
    }
    
    void addFirst(Node node) {
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }
    
    void reverse(int from, int to) {
        if (size() < 2 || from >= to) return;
        Node cur = head, nfrom = head, nto = head;
        int cfrom = from-1, cto = to+1, count;

        count = 0;
        while (count != cfrom) {
            nfrom = nfrom.next;
            ++count;
        }
        System.out.println("nfrom: " + nfrom.info);

        count = 0;
        while (count != cto) {
            nto = nto.next;
            ++count;
        }
        if (nto != null) System.out.println("nto: " + nto.info);

        count = 0;
        while (count != from) {
            cur = cur.next;
            ++count;
        }

        MyList tmp = new MyList();
        while (count != to) {
            tmp.addFirst(cur);
            cur = cur.next;
            ++count;
        }
        
        System.out.println("tmp head: " + tmp.head.info);

        nfrom.next = tmp.head;
        tmp.tail.next = nto;
    }
    
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        reverse(5, 8);
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

}
