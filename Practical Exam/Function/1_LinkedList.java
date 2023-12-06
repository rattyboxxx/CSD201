// Linked List

class LinkedList {

    // FIRST NODE IS INDEXED 0 - ZERO!
	
    // Get ll size
    int size() {
        Node cur = head;
        int k = 0;
        while (cur != null) {
            cur = cur.next;
            ++k;
        }
        return k;
    }

    // Add last node
    void addLast(Node node) {
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    // Add first node
    void addFirst(Node node) {
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    // Add node after a specific node
    void addAfterIndex(Node node, int index) {
        if (index < 0) {
            addFirst(node);
            return;
        }
        if (index >= size() - 1) {
            addLast(node);
            return;
        }
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

    // Delete last node
    void deleteLast() {
        if (isEmpty()) {
            return;
        }
        if (head.next == null) {
            head = null;
        }
        Node cur = head;
        while (cur.next.next != null) {
            cur = cur.next;
        }
        cur.next = null;
        tail = cur;
    }

    // Delete first node
    void deleteFirst() {
        if (isEmpty()) {
            return;
        }
        if (head.next == null) {
            head = null;
        }
        Node cur = head;
        head = cur.next;

    }

    // Delete an node with index
    void deleteIndex(int index) {
        if (index <= 0) {
            deleteFirst();
            return;
        }
        if (index >= size()) {
            deleteLast();
            return;
        }
        int pos = 0;
        Node cur = head;
        while (pos < index - 1) {
            cur = cur.next;
            ++pos;
        }
        cur.next = cur.next.next;
    }

    // Get node by index
    Node pos(int k) {
        int i = 0;
        Node p = head;
        while (p != null) {
            if (i == k) {
                return p;
            }
            i++;
            p = p.next;
        }
        return null;
    }

    // Delete a node
    void delete(Node q) {
        if (isEmpty() || q == null) {
            return;
        }
        if (q == head) {
            removeFirst();
            return;
        }
        Node f = head;
        while (f != null && f.next != q) {
            f = f.next;
        }
        if (f == null) {
            return;
        }
        Node q1 = q.next;
        f.next = q1;
        if (f.next == null) {
            tail = f;
        }
    }
	
	// Reverse from index from to index to
	void reverse(int from, int to) {
		if (size() < 2) return;
		Node cur = head, nfrom = head, nto = head;
		int cfrom = from-1, cto = to+1, count;
		
		count = 0;
		while (count != cfrom) {
			nfrom = nfrom.next;
			++count;
		}
		
		count = 0;
		while (count != cto) {
			nto = nto.next;
			++count;
		}
		
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
		
		nfrom.next = tmp.head;
		tmp.tail.next = nto;
	}

    // Reverse all the linked list
    void reverseAll() {
        MyList t = new MyList();
        Node p = head;
        while (p != null) {
            t.addFirst(p);
            p = p.next;
        }
        head = t.head;
        tail = t.tail;
    }
	
	// Sort from index k to index h
    // TODO: Change value if necessary
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
        Node v = pos(h + 1);
        Node pi, pj;
        Bat x;
        for (pi = u; pi != v; pi = pi.next) {
            for (pj = pi.next; pj != v; pj = pj.next) {
                if (pj.value < pi.value) {
                    x = pi.value;
                    pi.value = pj.value;
                    pj.value = x;
                }
            }
        }
    }

    // Sort all linked list
    // TODO: Change value if necessary
    void sortAll() {
        Node pi, pj;
        Object x;
        for (pi = head; pi != null; pi = pi.next) {
            for (pj = pi.next; pj != null; pj = pj.next) {
                if (pj.value < pi.value) {
                    x = pi.value;
                    pi.value = pj.value;
                    pj.value = x;
                }
            }
        }
    }
}