package Labs.Lab3;

/** Link for LinkedList */
class Link {
    private char base;
    private Link next;
    private Link previous;

    /** Constructor */
    Link(char b) {
        base = b;
    }

    /** Display self */
    public void displayLink() {
        System.out.print(base);
    }

    /** Getter for Link base */
    public char getBase() {
        return this.base;
    }

    /** Getter for Link next pointer */
    public Link getNext() {
        return this.next;
    }

    /** Getter for Link previous pointer */
    public Link getPrevious() {
        return this.previous;
    }

    /** Setter for Link next pointer */
    public void setNext(Link b) {
        this.next = b;
    }

    /** Setter for Link previous pointer */
    public void setPrevious(Link b) {
        this.previous = b;
    }
}

/** Doubly LinkedList implementation for DNA/RNA sequences */
class LinkedList {
    private Link first;
    private Link last;
    private int size;

    /** Constructors */
    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }
    public LinkedList(String raw) {
        char[] arr = raw.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            this.insertLast(arr[i]);
        }
    }

    /** Checks if LinkedList is empty */
    public boolean isEmpty() {
        return first == null;
    }

    /** Getter for LinkedList size */
    public int getSize() {
        return this.size;
    }

    /** Clears all elements from LinkedList */
    public void clear() {
        first.setNext(null);
        last.setPrevious(null);
        first = last = null;
        size = 0;
    }

    /** Inserts new Link at beginning of LinkedList */
    public void insertFirst(char b) {
        Link newLink = new Link(b);

        if (isEmpty())
            last = newLink;
        else
            first.setPrevious(newLink);
        newLink.setNext(first);
        first = newLink;
        size++;
    }

    /** Inserts new Link at end of LinkedList */
    public void insertLast(char b) {
        Link newLink = new Link(b);
        if (isEmpty())
            first = newLink;
        else {
            last.setNext(newLink);
            newLink.setPrevious(last);
        }
        last = newLink;
        size++;
    }

    /** Deletes first Link in LinkedList */
    public Link deleteFirst() {
        Link temp = first;
        if (first.getNext() == null)
            last = null;
        else
            first.getNext().setPrevious(null);
        first = first.getNext();
        size--;
        return temp;
    }

    /** Deletes last Link in LinkedList */
    public Link deleteLast() {
        Link temp = last;
        if (first.getNext() == null)
            first = null;
        else
            last.getPrevious().setNext(null);
        last = last.getPrevious();
        size--;
        return temp;
    }

    /** Returns a new LinkedList that is a copy of self */
    public LinkedList copy() {
        LinkedList temp = new LinkedList();
        Link current = first;
        while (current != null) {
            temp.insertLast(current.getBase());
            current = current.getNext();
        }
        return temp;
    }

    /** Returns a new LinkedList that is a clip of self from start pos. to end pos. parameters */
    public LinkedList clip(int start, int end) {
        LinkedList temp = new LinkedList();
        Link current = first;
        for (int i = 0; i < start; i++) {
            current = current.getNext();
            if (current == null)
                return null;
        }
        while (start <= end) {
            temp.insertLast(current.getBase());
            current = current.getNext();
            start++;
        }
        return temp;
    }

    /** Transcribes LinkedList from DNA to RNA */
    public LinkedList transcribe() {
        LinkedList temp = new LinkedList();
        Link current = first;
        while (current != null) {
            char org = current.getBase();
            if (org == 'T')
                org = 'U';
            else if (org == 'A')
                org = 'U';
            else if (org == 'C')
                org = 'G';
            else if (org == 'G')
                org = 'C';
            temp.insertFirst(org);
            current = current.getNext();
        }
        return temp;
    }

    /** Overriden toString() method for LinkedList */
    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        Link current = first;
        while (current != null) {
            stb.append(current.getBase());
            current = current.getNext();
        }
        return stb.toString();
    }
}