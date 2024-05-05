package strukdat;

public class HuffmanNode extends Gnode<Integer, Character> {
    TheArrayList<String> codeList;
    public HuffmanNode(int freq, char letter, HuffmanNode node1, HuffmanNode node2) {
        super(freq, letter);
        llink = node1;
        rlink = node2;
    }

    public TheArrayList<String> getHuffmanCodes(HuffmanNode root, int n) {
        String s = " ";
        codeList = new TheArrayList<String>(n);
        printCode(root, s);
        return codeList;
    }

    //print code (deretan bit)​
    public void printCode(HuffmanNode node, String s) {
        if (node == null) // Base case
            return;
        
        // If it's a leaf node, add its character and code to the list
        if (node.llink == null && node.rlink == null) {
            codeList.add(node.data + ":" + s);
            return;
        }
        printCode((HuffmanNode)node.llink, s + "1");
        printCode((HuffmanNode)node.rlink, s + "0");//traverse ke subtree kanan​
    }
}


