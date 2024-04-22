package strukdat;

public class BinaryTree<K,V> {
    //rekursif in order transversal
    public void printInOrder(Gnode<K,V> node) {
        if (node == null) {
            return;
        }
        else {
            //ke left node secara rekursif
            printInOrder(node.llink);

            System.out.println(node + " ");
            
            printInOrder(node.rlink);
        }
    }

    //post order transversal
    public void printPostOrder(Gnode<K,V> node) {
        if (node == null) {
            return;
        } 
        else {
            printPostOrder(node.llink);

            printPostOrder(node.rlink);

            System.out.println(node + " ");
        }
    }

    //pre order transversal
    public void printPreOrder(Gnode<K,V> node) {
        if (node == null) {
            return;
        }
        else {
            System.out.println(node + " ");

            printPreOrder(node.llink);
            
            printPreOrder(node.rlink);
        }
    }

    private void printLevelOrderRec (SingleList<Gnode<K,V>> q) {
        if (q.isEmpty()) {
            return;
        }

        Gnode<K,V> node = q.remove();
        //cetak key dari node
        System.out.println(node + " ");

        if (node.llink != null) {
            q.pushQ(node.llink);
        }

        if (node.rlink != null) {
            q.pushQ(node.rlink);
        }

        printLevelOrderRec(q);
    }

    public void printLevelOrder(Gnode<K,V> node) {
        //buat queue untuk menampung node disetiap level​
        SingleList<Gnode<K,V>> q = new SingleList<Gnode<K,V>>();
        q.pushQ(node);
        
        //memanggil fungsi rekursif untuk mencetak key ​dari node di setiap leve​l
        printLevelOrderRec(q);        
    }

}
