package strukdat;

public class BinarySearchTree<K extends Comparable<? super K>,V> extends BinaryTree<K,V> implements Tree<K,V> {
    private Gnode<K,V> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(K key, V data) {
        root = insertNode(root, key, data);
    }
    
    public void delete(K key) {
        root = deleteNode (root, key);
    }

    public V search(K key) {
        V info = null;
        info = getData(find(root, key));
        return info;
    }

    public K max() {
        K kunci = null;
        kunci = getKey(findMax(root));
        return kunci;
    }

    public K min() {
        K kunci = null;
        kunci = getKey(findMin(root));
        return kunci;
    }

    private Gnode<K,V> insertNode(Gnode<K,V> node, K k, V data) {
        //Jika tree masih kosong (belum ada node sama sekali), atau​
        //T sebelumnnya adalah child node. Buat node baru yang akan ​
        //di-link-kan ke child node sebelumnya​
        if (node == null) {
        Gnode<K,V> newNode = new Gnode<K,V>(k, data);
        return newNode;
        }
        //key dari node baru lebih kecil dari key child node sebelumnya​
        //go to the left node (subtree) node baru akan di-link ke left link
        else if (k.compareTo(node.key) < 0) {
            node.llink= insertNode(node.llink, k, data);
            return node;
        }
        //key dari node baru lebih besar dari atau sama dengan ​
        //key child node sebelumnya. Goto the right node (subtree)​
        //node baru akan di-link ke right link​
        else {
            node.rlink = insertNode(node.rlink, k, data);
            return node;
        }
    }
    //CARI !!!
    private Gnode<K,V> deleteNode(Gnode<K,V> node, K k){
        //base case
        if (node == null){
            return node;
        }
        //kalau key lebih kecil ke subtree kiri
        if (k.compareTo(node.key) < 0){
            node.llink = deleteNode(node.llink, k);
        }
        //kalau key lebih besar ke subtree kanan
        else if (k.compareTo(node.key) > 0){
            node.rlink = deleteNode(node.rlink, k);
        }
        //saat key ditemuin
        else{
            //punya 2 anak
            if (node.llink != null && node.rlink != null){
                Gnode<K,V> temp = node;
                Gnode<K,V> minRight = findMax(temp.rlink);
                node.key = minRight.key;
                node.rlink = deleteNode(node.rlink, minRight.key);
            }
            //punya 1 left child
            else if (node.llink != null){
                node = node.llink;  
            }
            //punya 1 right child
            else if (node.rlink != null){
                node = node.rlink;
            }
            //ga punya anak
            else {
                node = null;
            }
        }
        return node;
    }

    private Gnode<K,V> find(Gnode<K,V> node, K k){
        //node adalah subtree (root dari subtree)​
        if (node == null || node.key == k) {
            return node;
        }
        else if (node.key.compareTo(k) < 0){
            return find(node.rlink, k);
        } 
        else {
            return find(node.llink, k);
        }
     }
    //CARI !!!
    private Gnode<K,V> findMax(Gnode<K,V> node){
        Gnode<K,V> max = root;

        if(root == null){
            return max;
        }

        if (root.rlink == null){
            return max;
        }

        while (max.rlink != null) {
            max = max.rlink; 
        }
        return max;
    }
    //CARI !!!
    private Gnode<K,V> findMin (Gnode<K,V> node){
        Gnode<K,V> min = root;

        if(root == null){
            return min;
        }

        if (root.llink == null){
            return min;
        }

        while (min.llink != null) {
            min = min.llink; 
        }
        return min;

    }

    public void inOrder(){
        printInOrder(root);
    }

    public void levelOrder(){
        printLevelOrder(root);
    }
    
    public void preOrder(){
        printPreOrder(root);

        System.out.println();
    }
    
    public void postOrder(){
        printPostOrder(root);
        System.out.println();
    }

    public K getKey(Gnode<K,V> node){
        return (node.key);
    }

    public V getData(Gnode<K,V> node){
        return(node.data);
    }
}
