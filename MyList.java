public class MyList<E> {
    private class Node {
        public E element;
        public Node next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private int size;
    private Node head;

    public MyList(){
        this.head = null;
        this.size = 0;
    }

    private Node getNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public boolean add(E element) {
        if (head == null) {
            head = new Node(element);
            size++;
            return true;
        }
        Node node = head;
        node = getNode(size - 1);
        node.next = new Node(element);
        size++;
        return true;
    }

    public void add(int index, E element) {
        if (index == size - 1) {
            add(element);
        } else if (index != 0) {
            Node node = getNode(index - 1);
            node.next = new Node(element, node.next);
            size++;
        } else {
            Node node = getNode(0);
            head = new Node(element, node);
            size++;
        }
    }

    public E remove(int index) {
        Node removedNode = getNode(index);
        if (index != 0) {
            Node node = getNode(index - 1);
            if (node.next.next != null) node.next = node.next.next;
            else node.next = null;
            size--;
            return removedNode.element;
        } else {
            Node node = getNode(index);
            if (node.next != null) head = node.next;
            else head = null;
            size--;
            return removedNode.element;
        }
    }

    public boolean remove (Object o) {
        Node node = head;
        if (node.element.equals(o)) {
            if (node.next != null) head = node.next;
            else head = null;
            size--;
            return true;
        }
        boolean finded = false;
        while (node.next != null) {
            if (node.next.element.equals(o)){
                finded = true;
                break;
            }
            node = node.next;
        }
        if (finded) {
            if (node.next.next != null) node.next = node.next.next;
            else node.next = null;
            size--;
            return true;
        } else return false;
    }

    public E get(int index) {
        Node node = getNode(index);
        return node.element;
    }

    public int indexOf(Object o) {
        Node node = head;
        boolean finded = false;
        int index = 0;
        while (node != null){
            if (node.element.equals(o)) {
                finded = true;
                break;
            }
            node = node.next;
            index++;
        }
        if (finded) return index;
        else return -1;
    }

    @Override
    public String toString() {
        String info = "[";
        Node node = head;
        if (node == null) {
            info = info + "]";
            return info;
        }
        for(; node.next != null; node = node.next) {
            info = info + node.element + ", ";
        }
        info = info + node.element + "]";
        return info;
    }
}

