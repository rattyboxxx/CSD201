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
    void addLast(String xForest, int xFood, int xRate) {
        //You should write here appropriate statements to complete this function.
        if (xForest.charAt(0) == 'A') return;
        
        Node node = new Node(new Rabbit(xForest, xFood, xRate));
        
        if (isEmpty()) head = tail = node;
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
    void addFirst(Rabbit ele) {
        Node node = new Node(ele);
        if (isEmpty()) head = tail = node;
        else {
            node.next = head;
            head = node;
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
        Rabbit x, y;
        x = new Rabbit("X", 1, 2);
        y = new Rabbit("Y", 3, 4);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
        Your task is to insert statements here, just after this comment,
        to complete the question in the exam paper.*/
        addFirst(x);
        addFirst(y);
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================    
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
        
        int max_rate = 0;
        int max_rate_pos = 0;
        
        Node cur = head, tmp = null;
        int count = 0;
        
        // Find first max rate node
        while (cur != null) {
            if (cur.info.rate > max_rate) {
                max_rate = cur.info.rate;
                max_rate_pos = count;
                tmp = cur;
            }
            cur = cur.next;
            ++count;
        }
        
        // Remove
        if (max_rate_pos == 0) {
            head = head.next;
        }
        
        else {
            cur = head;
            for (int i = 1; i < max_rate_pos; ++i) {
                cur = cur.next;
            }
            
            cur.next = cur.next.next;
        }
        
        // Insert
        cur = head;
        for (int i = 1; i < 2; i++) {
            cur = cur.next;
        }
        
        assert(tmp != null);
        tmp.next = cur.next;
        cur.next=  tmp;
        
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
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
        
        Node cur, tmp = new Node(new Rabbit());
        for (int i = 0; i < 4; i++) {
            cur = head;
            for (int j = 0; j < 3; j++) {
                if (cur.info.rate > cur.next.info.rate) {
                    tmp.info.food = cur.info.food;
                    tmp.info.forest = cur.info.forest;
                    tmp.info.rate = cur.info.rate;
                    
                    cur.info.food = cur.next.info.food;
                    cur.info.forest = cur.next.info.forest;
                    cur.info.rate = cur.next.info.rate;
                    
                    cur.next.info.food = tmp.info.food;
                    cur.next.info.forest = tmp.info.forest;
                    cur.next.info.rate = tmp.info.rate;
                }
                cur = cur.next;
            }
        }
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

}
