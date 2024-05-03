package strukdat;


public class Heap<K extends Comparable<? super K>, V> {
    TheArrayList<Gnode<K, V>> arrList;
    TheArrayList<Gnode<K, V>> sortedList;
    boolean priority;

    // membuat array list dan mengeset apakah heap biasa (heap max)
    // atau heap dengan priority (heap min)
    public Heap(int capacity, boolean priority) {
        arrList = new TheArrayList<Gnode<K, V>>(capacity);
        sortedList = new TheArrayList<Gnode<K, V>>(capacity);
        this.priority = priority;
    }

    // mengembalikan jumlah elemen di heap
    public int size() {
        return arrList.size();
    }

    // mengembalikan bagian value (data/informasi)
    // dari node berdasarkan index
    public V getData(int index) {
        return arrList.get(index).data;
    }

    // mengembalikan bagian value (data/informasi)
    // dari node berdasarkan valuue yang diberikan
    public V getData(Gnode<K, V> node) {
        return node.data;
    }

    // mengembalikan bagian key dari node
    // berdasarkan index
    public K getKey(int index) {
        return arrList.get(index).key;
    }

    // mengembalikan bagian key dari node
    // berdasarkan value yang diberikan
    public K getKey(Gnode<K, V> node) {
        return node.key;
    }

    // slide selanjutnya ...
    // menambahkan node <key,value> ke heap tanpa heapify
    public void add(K key, V data) {
        arrList.add(new Gnode<K, V>(key, data));
    }

    // menyisipan node <key,value> ke heap
    // dengan heapify (max atau min)
    public void insert(K key, V data) {
        arrList.add(new Gnode<K, V>(key, data));
        int size = arrList.size();
        for (int i = size / 2 - 1; i >= 0; i = (i + 1) / 2 - 1) {
            if (priority)
                heapifyMin(size, i);
            else
                heapifyMax(size, i);
        }
    }

    // membuat heap
    public void buildHeap() {
        int size = arrList.size();// build heapSort (rearrange array)
        for (int i = size / 2 - 1; i >= 0; i--) {
            if (priority)
                heapifyMin(size, i);
            else
                heapifyMax(size, i);
        }
    }

    // slide selanjutnya ...
    // to max heapify a subtree rooted at node i
    void heapifyMax(int size, int i) {
        int parent = i; // initialize parent node
        int left = 2 * i + 1; // initialize left child node
        int right = 2 * i + 2; // initialize right child node// if left child is larger than parent
        if (left < size && arrList.get(left).key.compareTo(arrList.get(parent).key) > 0)
            parent = left;// if right child is larger than parent
        if (right < size && arrList.get(right).key.compareTo(arrList.get(parent).key) > 0)
            parent = right;// if parent is not root
        if (parent != i) {
            // swap
            Gnode<K, V> temp = arrList.get(i);
            arrList.set(i, arrList.get(parent));
            arrList.set(parent, temp);// recursively heapify the affected sub-tree
            heapifyMax(size, parent);
        }
    }

    // slide selanjutnya ...
    // heapsort
    public void sort() {
        int size = arrList.size();
        // build heapSort (rearrange array)
        buildHeap();
        // one by one extract an element from heapSort
        for (int i = size - 1; i >= 0; i--) {
            // swap current root node to rightmost leaf node
            Gnode<K, V> temp = arrList.get(0);
            arrList.set(0, arrList.get(i));
            arrList.set(i, temp);
            // call max or min heapify on the reduced heap
            if (priority)
                heapifyMin(i, 0);
            else
                heapifyMax(i, 0);
        }
    }

    // to min heapify a subtree rooted at node i
    void heapifyMin(int size, int i) {
        int parent = i; // initialize parent node
        int left = 2 * i + 1; // initialize left child node
        int right = 2 * i + 2; // initialize right child node
        // if left child is smaller than parent
        if (left < size && arrList.get(left).key.compareTo(arrList.get(parent).key) < 0) {
            parent = left;
        }
        if (right < size && arrList.get(right).key.compareTo(arrList.get(parent).key) < 0) {
            parent = right;
        }
        // if parent is not root
        if (parent != i) {
            // swap
            Gnode<K, V> temp = arrList.get(i);
            arrList.set(i, arrList.get(parent));
            arrList.set(parent, temp);
            // recursively heapify the affected sub-tree
            heapifyMin(size, parent);
        }
    }

    // mengembalikan root node (tidak menghapus)
    public Gnode<K, V> first() {
        if (arrList.isEmpty()) {
            return null;
        }
        return arrList.get(0);
    }

    // mengembalikan root node dan menghapusnya dari heap
    public Gnode<K, V> removeFirst() {
        if (!arrList.isEmpty()) {
            Gnode<K, V> removeNode = arrList.get(0);
            arrList.remove(0);
            return removeNode;
        } else {
            return null; // or throw an exception indicating that the heap is empty
        }
    }

    // A utility function to print array of size n
    public void display() {
        arrList.cetakList();
    }

    public boolean isOperator(V operator) {
        String operatorValue = String.valueOf(operator);
        String[] split = operatorValue.split(":");
        return split[1].equals("+ ");
    }

    public void sortPQ() {
        while (!arrList.isEmpty()) {
            Gnode<K, V> minValue = minPQ();
            if (minValue != null) {
                sortedList.add(minValue);
            }
        }
        arrList.clear();
        for (int i = 0; i < sortedList.size(); i++) {
            arrList.add(sortedList.get(i));
        }
        sortedList.clear();
    }

    public Gnode<K, V> minPQ() {
        if (arrList.isEmpty()) {
            return null;
        }

        Gnode<K, V> minValue = arrList.get(0);
        int lastIndex = arrList.size() - 1;
        arrList.set(0, arrList.get(lastIndex));
        arrList.remove(lastIndex);
        heapifyMin(arrList.size(), 0);

        if (arrList.size() >= 1 && minValue.key.equals(arrList.get(0).key)) {
            Gnode<K,V> tempMinValue = arrList.get(0);
            if (!isOperator(minValue.data) && isOperator(arrList.get(0).data)) {
                Gnode<K, V> temp = arrList.get(0);
                arrList.set(0, minValue);
                arrList.set(lastIndex, temp);
                heapifyMin(arrList.size(), 0);
            }
            minValue = tempMinValue;
        }
        return minValue;
    }
}