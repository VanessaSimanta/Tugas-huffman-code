package strukdat;

class DataItem<T, U> {
   public T key;
   public U data;

   public DataItem(T k, U d) {
      key = k;
      data = d;
   }

   public void displayItem() {
      System.out.print("/" + key);
   }
}

class Node<T extends Comparable<T>, U> {
   private static final int ORDER = 4;
   private int numItems;
   private Node<T, U> parent;
   private Node<T, U> childArray[] = new Node[ORDER];
   private DataItem<T, U>[] itemArray = new DataItem[ORDER - 1];

   public void setItem(int index, DataItem<T, U> newItem) {
      itemArray[index] = newItem;
   }

   public void connectChild(int childNum, Node<T, U> child) {
      childArray[childNum] = child;
      if (child != null)
         child.parent = this;
   }

   public Node<T, U> disconnectChild(int childNum) {
      Node<T, U> tempNode = childArray[childNum];
      childArray[childNum] = null;
      return tempNode;
   }

   public Node<T, U> getChild(int childNum) {
      return childArray[childNum];
   }

   public Node<T, U> getParent() {
      return parent;
   }

   public boolean isLeaf() {
      return (childArray[0] == null) ? true : false;
   }

   public int getNumItems() {
      return numItems;
   }

   public DataItem<T, U> getItem(int index) {
      return itemArray[index];
   }

   public boolean isFull() {
      return (numItems == ORDER - 1) ? true : false;
   }

   public int findItem(T key) {
      for (int j = 0; j < ORDER - 1; j++) {
          if (itemArray[j] == null)
              break;
          else if (itemArray[j].key.compareTo(key) == 0)
              return j;
      }
      return -1;
  }

   public int insertItem(DataItem<T, U> newItem) {
      numItems++;
      T newKey = newItem.key;

      for (int j = ORDER - 2; j >= 0; j--) {
         if (itemArray[j] == null)
            continue;
         else {
            T itsKey = itemArray[j].key;
            if (newKey.compareTo(itsKey) < 0)
               itemArray[j + 1] = itemArray[j];
            else {
               itemArray[j + 1] = newItem;
               return j + 1;
            }
         }
      }
      itemArray[0] = newItem;
      return 0;
   }

   public DataItem<T, U> removeItem() {
      DataItem<T, U> temp = itemArray[numItems - 1];
      itemArray[numItems - 1] = null;
      numItems--;
      return temp;
   }

   public void displayNode() {
      for (int j = 0; j < numItems; j++)
         itemArray[j].displayItem();
      System.out.println("/");
   }
}

class Tree23<T extends Comparable<T>, U> {
   public Node<T, U> root = new Node<T, U>();

   public int find(T key) {
      Node<T, U> curNode = root;
      int childNumber;
      while (true) {
         if ((childNumber = curNode.findItem(key)) != -1)
            return childNumber; 
         else if (curNode.isLeaf())
            return -1; 
         else
            curNode = getNextChild(curNode, key);
      } 
   }

   public void insert(T dValue, U data) {
      Node<T, U> curNode = root;
      DataItem<T, U> tempItem = new DataItem<T, U>(dValue, data);
      
      while (true) {
         if (curNode.isLeaf())
            break;
         else
            curNode = getNextChild(curNode, dValue);
      }
      curNode.insertItem(tempItem);
      // dilanggar dahulu max items per node dari 2 menjadi 3 
      if (curNode.isFull()) 
      {
         split(curNode); // split it
         curNode = curNode.getParent();
         // untuk setiap node yang melanggar max items per node
         while(curNode.isFull()){
            split(curNode); // split it
            curNode = curNode.getParent();
         }
      }
   }

   public void split(Node<T, U> thisNode) {
      DataItem<T, U> itemB, itemC;
      Node<T, U> parent, child2, child3;
      int itemIndex;

      itemC = thisNode.removeItem();
      itemB = thisNode.removeItem();
      child2 = thisNode.disconnectChild(2);
      child3 = thisNode.disconnectChild(3);
      
      Node<T, U> newRight = new Node<T, U>();

      if (thisNode == root) {
         root = new Node<T, U>();
         parent = root;
         root.connectChild(0, thisNode);
      } else
         parent = thisNode.getParent();

      itemIndex = parent.insertItem(itemB);
      int n = parent.getNumItems();

      for (int j = n - 1; j > itemIndex; j--) {
         Node<T, U> temp = parent.disconnectChild(j);
         parent.connectChild(j + 1, temp);
      }
      parent.connectChild(itemIndex + 1, newRight);
      newRight.insertItem(itemC);
      newRight.connectChild(0, child2);
      newRight.connectChild(1, child3);
   }

   public Node<T, U> getNextChild(Node<T, U> theNode, T theValue) {
      int j;

      int numItems = theNode.getNumItems();
      for (j = 0; j < numItems; j++) {
         if (theValue.compareTo(theNode.getItem(j).key) < 0)
            return theNode.getChild(j);
      }
      return theNode.getChild(j);
   }

   public void displayTree() {
      recDisplayTree(root, 0, 0);
   }

   private void recDisplayTree(Node<T, U> thisNode, int level, int childNumber) {
      System.out.print("level=" + level + " child=" + childNumber + " ");
      thisNode.displayNode(); 

      int numItems = thisNode.getNumItems();
      for (int j = 0; j < numItems + 1; j++) {
         Node<T, U> nextNode = thisNode.getChild(j);
         if (nextNode != null)
            recDisplayTree(nextNode, level + 1, j);
         else
            return;
      }
   }

   //function untuk delete
   Node<T, U> delete(Node<T, U> root, T key) {
     Node<T, U> current = root;
     Node<T, U> previous = null;
     
    //cari node kalo ketemu maka break
     while (current != null) {
        int index = current.findItem(key);

        if (index != -1) {
           break;
        }

        previous = current;
        boolean foundChild = false;

        for (int i = 0; i < current.getNumItems(); i++) {
           if (key.compareTo(current.getItem(i).key) < 0) {
              current = current.getChild(i);
              foundChild = true;
              break;
           }
        }

        if (!foundChild) {
           current = current.getChild(current.getNumItems()); 
        }
     }

     // kalo node ga ketemu maka keluarin output
     if (current == null) {
         System.out.println("Key " + key + " tidak ditemukan dalam BST yang diberikan.");
         return root;
     }

     if (current.isLeaf() && current.findItem(key) != -1) {
         if (current == root) {
             return null;
         }
         current.removeItem(); 

         
        int index = previous.findItem(key);
        if (index != -1) {
           previous.removeItem();
        }

         return root;
     }

     Node<T, U> temp = current;
     Node<T, U> prevTemp = null;
     while (!temp.isLeaf()) {
         prevTemp = temp;
         temp = temp.getChild(temp.getNumItems());
     }

     DataItem<T, U> lastItem = temp.removeItem();
     current.setItem(0, lastItem);

     if (temp.getNumItems() == 0 && prevTemp != null) {
         prevTemp.disconnectChild(prevTemp.getNumItems());
     }

     return root;
 }
}

public class Tree23Main {
   public static void main(String[] args) {
      Tree23<Integer, String> theTree = new Tree23<Integer, String>();

      theTree.insert(24, "Twenty-Four");
      theTree.insert(41, "Forty-one");
      theTree.insert(28, "Twenty-Eight");
      theTree.insert(53, "Fifty-Three");
      theTree.insert(85, "Eighty-five");

      theTree.insert(57, "Fifty-seven");
      theTree.insert(13, "Thirteen");
      theTree.insert(31, "Thirty-one");
      theTree.insert(74, "Seventy-four");
      theTree.insert(27, "Twenty-seven");
      theTree.insert(72, "Seventy-two");
      theTree.insert(50, "Fifty");
      theTree.insert(48, "Forty-Eight");
      theTree.insert(5, "Five");
      theTree.insert(19, "Nineteen");
      theTree.insert(97, "Ninety-seven");

      theTree.insert(59, "Fifty-nine");
      theTree.insert(16, "Sixteen");
      theTree.insert(90, "Ninety");
      theTree.insert(75, "Seventy-five");
      theTree.insert(18, "Eighteen");

      theTree.displayTree();

     theTree.delete(theTree.root, 19);
      
     int found = theTree.find(19);
     if (found != -1)
        System.out.println("19 ditemukan di tree");
     else
        System.out.println("19 tidak ditemukan di tree ");
        
     System.out.println("Tree setelah operasi delete:");
     theTree.displayTree();
   }
}
