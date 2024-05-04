package strukdat;

public class HuffmanCoding {
    public static void main(String[] args) {
        char[] charArray = { 'E', 'T', 'N', 'I', 'S'};
        int[] charfreq = { 29, 10, 9, 5, 4};
        //char[] charArray = {'A', 'F', 'I', 'K', 'M', 'N', 'O', 'R', 'T'};
        //int[] charfreq ={5, 9, 3, 25, 39, 20, 11, 15, 14 };
        
        // Create a priority queue with a min-heap
        Heap<Integer, HuffmanNode> pq = new Heap<Integer, HuffmanNode>(charArray.length, true);
        
        // Input each character and its frequency into the priority queue
        for(int i = 0; i < charArray.length; i++){
            pq.insert(charfreq[i], new HuffmanNode(charfreq[i],charArray[i], null, null));
        }
        
       //membuat heap minimum dari pq​
       HuffmanNode root = null;
       HuffmanNode x, y;
       int sum;
       

       while(pq.size() > 1) {
        sum = pq.getKey(pq.first());
        x = pq.getData(pq.removeFirst()); // Remove the first minimum node
        y = pq.getData(pq.removeFirst()); // Remove the second minimum node
    
        root = new HuffmanNode(sum, '-', x, y);
        pq.insert(sum, root);
       }

    TheArrayList<String> huffmanCodes = root.getHuffmanCodes(root, charArray.length);
       System.out.println("---------------------");
       System.out.println(" Huruf | Huffman code ");
       System.out.println("---------------------");
       for(int i = 0; i < huffmanCodes.size(); i++) {
        // split each string in ArrayList to get the character and Huffman code
        String parts = huffmanCodes.get(i);
        String[] kata = parts.split(" ");

        // display the character and Huffman code
        System.out.println(kata[0] + " | " + kata[1]);
    }  
       System.out.println("---------------------");
    }
}



