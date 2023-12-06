
import java.util.EmptyStackException;


public class ArrayStack {
    protected Object[] a;
    int top, max;
    
    public ArrayStack() {
        this(50);
    }
    
    public ArrayStack(int max1) {
        max = max1;
        a = new Object[max];
        top = -1;
    }
    
    protected boolean grow() {
        int max1 = max + max/2;
        Object[] a1 = new Object[max1];
        if (a1 == null) return false;
        for (int i = 0; i <= top; i++) {
            a1[i] = a[i];
        }
        a = a1;
        return true;
    }
    
    protected boolean isEmpty() {
        return top == -1;
    }
    
    public boolean isFull() {
        return top == max - 1;
    }
    
    public void clear() {
        top = -1;
    }
    
    public void push(Object x) {
        if (isFull() && !grow()) return;
        a[++top] = x;
    }
    
    Object top() {
        if (isEmpty()) throw new EmptyStackException();
        return a[top];
    }
    
    public Object pop() {
        if (isEmpty()) throw new EmptyStackException();
        Object x = a[top];
        top--;
        return x;
    }
}
