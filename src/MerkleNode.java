
public class MerkleNode<T> {
    private T transaction;
    private String name;
    private String HTxn;
    private Integer childLeft;
    private Integer childRight;
    private Integer parent;

    public MerkleNode(T transaction) {
        this.transaction = transaction;
        this.name = transaction.toString();
        this.HTxn = Utils.doubleSHA256(transaction);
        this.childLeft = null;
        this.childRight = null;
        this.parent = null;
    }

    public MerkleNode() {

    }

    public Integer getChildLeft() {
        return childLeft;
    }

    public Integer getChildRight() {
        return childRight;
    }

    public Integer getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public T getTransaction() {
        return transaction;
    }

    public String getHTxn() {
        return HTxn;
    }

    public void setChildLeft(Integer childLeft) {
        this.childLeft = childLeft;
    }

    public void setChildRight(Integer childRight) {
        this.childRight = childRight;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHTxn(String HTxn) {
        this.HTxn = HTxn;
    }
}
