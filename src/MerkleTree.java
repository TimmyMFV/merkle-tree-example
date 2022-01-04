import java.util.ArrayList;
import java.util.Comparator;

public class MerkleTree<T> {
    private final ArrayList<MerkleNode<T>> storage;
    private boolean isBuilt;
    private int originalSize;

    public MerkleTree() {
        this.storage = new ArrayList<>();
        this.isBuilt = false;
        this.originalSize = 0;
    }

    public void addNode(MerkleNode<T> node) {
        storage.add(node);
    }

    public void buildTree() {
        originalSize = storage.size();
        storage.sort(Comparator.comparing(o -> o.getTransaction().toString()));

        //duplicate the last element if total element is odd number
        if (originalSize % 2 != 0) {
            MerkleNode<T> lastNode = storage.get(originalSize-1);
            MerkleNode<T> duplicatedNode = new MerkleNode<>();
            duplicatedNode.setName(lastNode.getName());
            duplicatedNode.setHTxn(lastNode.getHTxn());
            storage.add(duplicatedNode);
        }

        int index = 0;
        while (index+1 < storage.size()) {
            MerkleNode<T> node = new MerkleNode<>();

            node.setName((storage.get(index).getName() + storage.get(index+1).getName()).trim());

            //hashed by pair
            node.setHTxn(Utils.doubleSHA256(storage.get(index).getHTxn() + storage.get(index+1).getHTxn()));

            node.setChildLeft(index);
            node.setChildRight(index+1);

            storage.get(index).setParent(storage.size());
            storage.get(index+1).setParent(storage.size());

            storage.add(node);
            index += 2;
        }
        isBuilt = true;
    }

    public String getMerkleRoot() {
        if (isBuilt) return storage.get(storage.size()-1).getHTxn();
        return "Merkle tree has not built!";
    }

    public String getMerklePath(T transaction) {
        //should be binary search here
        MerkleNode<T> foundNode = null;
        for (int i = 0; i < originalSize; i++) {
            if (transaction.toString().equals(storage.get(i).getTransaction().toString())) {
                foundNode = storage.get(i);
                break;
            }
        }
        if (foundNode == null) {
            return "Transaction has not found!";
        }
        StringBuilder path = new StringBuilder();

        //tracing util get root
        while (!foundNode.getHTxn().equals(getMerkleRoot())) {
            MerkleNode<T> parentNode = this.storage.get(foundNode.getParent());
            if (storage.get(parentNode.getChildLeft()).getHTxn().equals(foundNode.getHTxn())) {
                path.append(storage.get(parentNode.getChildRight()).getName()).append(" ");
            }
            else if (storage.get(parentNode.getChildRight()).getHTxn().equals(foundNode.getHTxn())) {
                path.append(storage.get(parentNode.getChildLeft()).getName()).append(" ");
            }
            else return "Transaction is invalid!";
            foundNode = parentNode;
        }
        return path.toString();
    }

}
