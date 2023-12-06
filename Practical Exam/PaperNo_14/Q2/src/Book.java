// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============
class Book {
  String title;
  int price,type;
  Book() {
   }
  Book(String xTitle, int xPrice, int xType){
    title=xTitle;price=xPrice; type=xType;
   }
  public String toString(){
    return("(" +title+","+price + "," + type + ")");
   }
 }
