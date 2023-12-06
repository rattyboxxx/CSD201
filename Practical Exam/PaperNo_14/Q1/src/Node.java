// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Node {
  Book info;
  Node next;
  Node() {
   }
  Node(Book x, Node p) {
    info=x;next=p;
   }
  Node(Book x) {
    this(x,null);
   }
 }

