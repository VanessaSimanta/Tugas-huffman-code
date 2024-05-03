package strukdat;


public class HuffmanCoding {
    public static void main(String[] args) {
        char[] charArray = { 'E', 'T', 'N', 'I', 'S'};
        int[] charfreq = { 29, 10, 9, 5, 4};
        //buat priority queue dengan heap min​

        Heap<Integer, HuffmanNode> pq = new Heap<Integer, HuffmanNode>(charArray.length, false);
        //inputkan setiap huruf dan frekuensinya ke pq​
       for(int i = 0; i < charfreq.length; i++){
           pq.add(charfreq[i], new HuffmanNode(charfreq[i],charArray[i], null, null));
       }
       //membuat heap minimum dari pq​
       HuffmanNode root = null;
       HuffmanNode x, y;
       int sum = 0;

       while(pq.size() > 1) {
        sum = pq.getKey(pq.first());
        x = pq.getData(pq.first());
        y = pq.getData(pq.first());

        root = new HuffmanNode(sum,'-', x, y);
        pq.insert(sum, root);
       }
    }
}

//     TheArrayList<String> huffmanCodes = root.getHuffmanCodes(root, charArray.length);
//        System.out.println("---------------------");
//        System.out.println(" Huruf | Huffman code ");
//        System.out.println("---------------------");

//        for(int i = 0; i < huffmanCodes.size(); i++) {
//         //split setiap string di ArrayList untuk mendapatkan huruf dan Huffman codenya​
//        String[] parts = huffmanCodes.get(i).split(":");
//         char letter = parts[0].charAt(0);
//         String code = parts[1];
//         //tampilkan huruf dan Huffman codenya​
//         System.out.println("   " + letter + "   |   " + code);
//        }
       
//        System.out.println("---------------------");
// }
// }



