
public class MerkleNode<T> {
    private T transaction;
    private String name;
    private String HTxn;
    private Integer childLLeft;
    private Integer childRight;
    private Integer parent;

    public MerkleNode(T transaction) {
        this.transaction = transaction;
        this.name = transaction.toString();
        this.HTxn = Utils.doubleSHA256(transaction);
        this.childLLeft = null;
        this.childRight = null;
        this.parent = null;
    }

    public MerkleNode() {

    }

    public Integer getChildLeft() {
        return childLLeft;
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

    public void setChildLeft(Integer childLLeft) {
        this.childLLeft = childLLeft;
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
