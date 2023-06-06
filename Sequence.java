package Labs.Lab3;

/** Sequence class */
public class Sequence {
    private Type t;
    private LinkedList list;

    /** Constructors */
    Sequence(Type t, LinkedList list) {
        this.t = t;
        this.list = list;
    }
    Sequence(Type t, String s) {
        this.t = t;
        list = new LinkedList(s);
    }

    /** Getter for Sequence Type */
    Type getType() {
        return this.t;
    }

    /** Getter for Sequence LinkedList */
    LinkedList getList() {
        return this.list;
    }

    /** Setter for Sequence Type */
    void setType(Type t) {
        this.t = t;
    }

    /** Setter for Sequence LinkedList */
    void setList(LinkedList list) {
        this.list = list;
    }
}
