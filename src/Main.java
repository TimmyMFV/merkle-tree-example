
public class Main {
    public static void main(String[] args) {
        MerkleTree<String> merkleTree = new MerkleTree<>();
        for (int i = 0; i < 16; i++) {
            MerkleNode<String> node = new MerkleNode<>("" + (char) ((int) 'A' + i));
            merkleTree.addNode(node);
        }
        merkleTree.buildTree();
        System.out.println(merkleTree.getMerkleRoot());
        System.out.println(merkleTree.getMerklePath("C"));
    }
}
