package strukdat;

public class HuffmanCoding {
    public static void main(String[] args) {
        char[] charArray = { 'E', 'T', 'N', 'I', 'S'};
        int[] charfreq = { 29, 10, 9, 5, 4};
        // char[] charArray = {'A', 'F', 'I', 'K', 'M', 'N', 'O', 'R', 'T'};
        // int[] charfreq ={5, 9, 3, 25, 39, 20, 11, 15, 14 };
        
        // buat priority queue dengan heap min
        Heap<Integer, HuffmanNode> pq = new Heap<Integer, HuffmanNode>(charfreq.length, true);
        
        // inputkan setiap huruf dan frekuensinya ke pq
        for(int i = 0; i < charfreq.length; i++){
            pq.insert(charfreq[i], new HuffmanNode(charfreq[i],charArray[i], null, null));
        }
        
       //membuat heap minimum dari pq​
       HuffmanNode root = null;
       HuffmanNode x, y;
       int sum;
       

       while(pq.size() > 1) {
        sum = pq.getKey(pq.first());
        x = pq.getData(pq.first()); 
        // y = pq.getData(pq.removeFirst()); 
        pq.removeFirst();
        sum += pq.getKey(pq.first());
        y = pq.getData(pq.first());
        pq.removeFirst();

        root = new HuffmanNode(sum, '-', x, y);
        pq.insert(sum, root);
       }

    root = pq.getData(pq.first());
    TheArrayList<String> huffmanCodes = root.getHuffmanCodes(root, charArray.length);
       System.out.println("---------------------");
       System.out.println(" Huruf | Huffman code ");
       System.out.println("---------------------");
       for(int i = 0; i < huffmanCodes.size(); i++) {
        // split setiap string di Arraylist untuk mendapatkan huruf dan huffman codenya
        String parts = huffmanCodes.get(i);
        String[] kata = parts.split(":");

        //tampilkan huruf dan huffman codenya 
        System.out.println(kata[0] + " | " + kata[1]);
        }  
       System.out.println("---------------------");
    }
}

