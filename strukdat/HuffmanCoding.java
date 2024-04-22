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
}
