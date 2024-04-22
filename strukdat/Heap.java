package strukdat;
import strukdat.TheArrayList;
import strukdat.Gnode;

public class Heap<K extends Comparable<? super K>,V> {
    TheArrayList<Gnode<K,V>> arrList;
    boolean priority;

    //membuat array list dgn mengset apakah dia heap biasa (max heap) atau heap dgn priority (min heap)
    public Heap(int capacity, boolean priority) {
        arrList = new TheArrayList<Gnode<K,V>>(capacity);
        this.priority = priority;
    }

    //mengembalikan jumlah elemen di heap
    public int size() {
        return arrList.size();
    }

    //mengembalikan value dari node berdasarkan index
    public V getData(int index) {
        return arrList.get(index).data;
    }

    //mengembalikan value dari node berdasarkan value yang diberikan 
    public V getData(Gnode<K,V> node) {
        return node.data;
    }

    //mengembalikan bagian key dari node berdasarkan index 
    public K getKey(int index) {
        return arrList.get(index).key;
    }

    //mengembalikan key dari node berdasarkan value yang diberikan 
    public K getKey(Gnode<K,V> node) {
        return node.key;
    }

    //menambahkan node <key,value> ke heap tanpa heapify
    public void add(K key, V data) {
        arrList.add(new Gnode<K,V>(key, data));
    }

    //menyisipan node <key,value> ke heap dengan heapify (max atau min)​
    public void insert(K key, V data) {
        arrList.add(new Gnode<K,V>(key, data));
        int size = arrList.size();
        for (int i = size / 2 - 1; i >= 0; i = (i+1) / 2 - 1) {
            if(priority) heapifyMin(size, i);
            else heapifyMax(size, i);
        }    
    }

    //membuat heap​
    public void buildHeap() {
        int size = arrList.size();
        // build heapSort (rearrange array)​
        for (int i = size / 2 - 1; i >= 0; i--) {
            if(priority) heapifyMin(size, i);
            else heapifyMax(size, i);   
        }
    }

    //to max heapify a subtree rooted at node i
    void heapifyMax(int size, int i) {
        int parent = i; //initialize parent node
        int left = 2 * i + 1; //initialize left child node
        int right = 2 * i + 2; //initialize right child node
        //if left child is larger than parent
        if(left<size&&arrList.get(left).key.compareTo(arrList.get(parent).key)>0){
            parent = left;
        }
        if(right<size&&arrList.get(right).key.compareTo(arrList.get(parent).key)>0){
            parent = right;
        }
        //if parent is not root
        if(parent != i){
            //swap
            Gnode<K,V>temp = arrList.get(i);
            arrList.set(i, arrList.get(parent));
            arrList.set(parent, temp);
            //recursively heapify the affected sub-tree
            heapifyMax(size, parent);
        }
        
    }

    //heapsort
    public void sort(){
        int size = arrList.size();

        //build heapSort(rearrange array)
        buildHeap();

        //one by one extract an element from heapSort
        for(int i=size - 1; i>=0; i--){
            //swap current root node to rightmost leaf node
            Gnode<K, V> temp = arrList.get(0);
            arrList.set(0, arrList.get(i));
            arrList.set(i, temp);



            //call max or min heapify on the reduced heap
            if (priority){
                heapifyMin(i, 0);
            } else {
                heapifyMax(i, 0);
            }




        }
    }

    //to min heapify a subtree rooted at node i
    void heapifyMin(int size, int i){
        int parent = i; //inisialisasi parent node
        int left = 2 * i + 1; //inisialisasi left child node
        int right = 2 * i + 2; //inisialisasi right child node

        //if left child lebih kecil daripada parent
        if(left<size && arrList.get(left).key.compareTo(arrList.get(parent).key)<0){
            parent = left;
        }

        //if right child lebih kecil daripada parent
        if(right<size && arrList.get(right).key.compareTo(arrList.get(parent).key)<0){
            parent = right;
        }

        //if parent bukan root
        if(parent != i ){
            //swap
            Gnode<K,V> temp = arrList.get(i);
            arrList.set(i, arrList.get(parent));
            arrList.set(parent, temp);

            //recursively heapify the affected sub-tree
            heapifyMin(size, parent);
        }
    }
    //mengembalikan root node (tidak menghapus)
    public Gnode<K,V>first(){
        if(arrList.isEmpty()){
            throw new IllegalStateException("Heap is empty.");
        }
        return arrList.get(0);
    }

    //mengembalikan root node dan menghapusnya dari heap
    public Gnode<K,V>removeFirst(){
        if (arrList.isEmpty()){
            throw new IllegalStateException("Heap is empty.");
        }
        int size = arrList.size();
        Gnode<K,V> root = arrList.get(0);
        arrList.set(0, arrList.get(size - 1));
        arrList.remove(size - 1);

        if (priority){
            heapifyMin(arrList.size(), 0);
        }else{
            heapifyMax(arrList.size(), 0);
        }
        return root;
    }

    
    // A utility function to print array of size n
    public void display(){
        arrList.cetakList();
    }

//Main Method
public class MainProgram {
    public static void main(String[] args){
        //Test Hash Table and Heap Sort
        Integer[] kunci = {11,2,30,4,15};
        String[] data = {"Andree", "Leana", "Faviola", "Loyece", "Quincy"};
        //set priority  = false for heap max; sebaliknya untuk heap min
        Heap<Integer,String> heap = new
        Heap<Integer, String>(kunci.length, false);
        for(int i=0; i < kunci.length;i++){
            heap.add(kunci[i], data[i]);
        }
        heap.buildHeap();
        heap.display();
        System.out.println(heap.getData(heap.removeFirst()));
        heap.display();
        heap.insert(3, "Lely");
        heap.display();
        heap.sort();
        heap.display();
        
    }
}
}

    