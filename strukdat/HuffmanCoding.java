package strukdat;
import strukdat.HuffmanNode;
import strukdat.Heap;
import strukdat.TheArrayList;

public class HuffmanCoding {
    public static void main(String[] args) {
        char[] charArray = { 'E', 'T', 'N', 'I', 'S'};
        int[] charfreq = { 29, 10, 9, 5, 4};
        //buat priority queue dengan heap min​
        //Heap<Integer, HuffmanNode> pq = ...
        //inputkan setiap huruf dan frekuensinya ke pq​
       // for(int i=0; i < ...; i++)​

       //membuat heap minimum dari pq​

       HuffmanNode root = null;
       HuffmanNode x, y;
       int sum;
       while(pq.size() > 1) {
        sum = pq.getKey(pq.first());
        pq.removeFirst();
        //...
        //root = new HuffmanNode(...,’...', x, y);
        //pq.insert(..., ...);
       }

       //TheArrayList<String> ...​
       //... = .... getHuffmanCodes(..., ...);​
       System.out.println("---------------------");
       System.out.println(" Huruf | Huffman code ");
       System.out.println("---------------------");
       for(int i=0; i < ...; i++) {​

        // //split setiap string di ArrayList untuk mendapatkan huruf dan Huffman codenya​
        
        // ...
        
        // //tampilkan huruf dan Huffman codenya​
        
        // ...
        
       }
       System.out.println("---------------------");
}

