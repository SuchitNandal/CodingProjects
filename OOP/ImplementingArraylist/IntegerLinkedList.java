/**
 * IntegerLinkedList.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 *
 * @author Suchit Nandal sn1566@rit.edu
 * @author Ninad Godambe ng9810@rit.edu
 *
 * This class is used to implement a Linked List that works on integers.
 * It has been designed from a basic level, rather than using any existing data
 * structure
 */


public class IntegerLinkedList extends IntegerStorageImplementation {
    Node FirstNode;

    /**
     * Adds an element at the first place of the list
     */
    public boolean add(int data) {
        Node Current = new Node();
        Current.data = data;
        Node prev = new Node();
        Node temp = new Node();
        temp = FirstNode;
        if (FirstNode == null) FirstNode = Current;
        else {
            while (temp.nextnode != null) temp = temp.nextnode;
            temp.nextnode = Current;
        }

        SizeofList += 1;
        return true;
    }


    /**
     * Adds an element at the given index of the list
     */
    public void add(int index, int data) {
        Node myNode = new Node();
        myNode.data = data;
        myNode.nextnode = FirstNode;
        Node counter = FirstNode;

        if (index == 0) {
            myNode.nextnode = FirstNode;
            FirstNode = myNode;

        } else {
            for (int i = 0; i < index - 1; i++) {
                counter = counter.nextnode;
                SizeofList++;
                myindex += 1;
            }
            myNode.nextnode = counter.nextnode;
            counter.nextnode = myNode;
        }
        SizeofList += 1;

    }

    /**
     * Adds a list of elements at the given index of the list
     */
    public boolean addAll(int index, int[] os) {
        if (SizeofList >= 1) {
            for (int i = 0; i < os.length; i++) {

                add(index, os[i]);
                index++;
            }
        } else System.out.println("No Elements in the lists");
        return true;
    }

    /**
     * Clears the entire list
     */
    public void clear() {
        Node counterA = FirstNode;
        Node counterB = null;
        if (FirstNode == null) System.out.println("List is empty");
        else {

            while (counterA != null) {
                counterB = counterA.nextnode;
                counterA = null;
                counterA = counterB;
            }
            FirstNode = null;
        }
        SizeofList = 0;
    }

    /**
     * Gives the element at given index
     */
    public int get(int index) {
        Node counterA = FirstNode;
        if (index > SizeofList) System.out.println("Index out of bounds !");
        else if (FirstNode == null) System.out.println("List is empty");
        else if (index == 0) return FirstNode.data;
        else {
            for (int i = 0; i < index; i++) {
                counterA = counterA.nextnode;

            }
        }
        return counterA.data;
    }

    /**
     * Removes the element at the specified position in this list (optional operation).
     */
    public int remove(int index) {
        Node counterA = FirstNode;
        Node counterB;
        if (FirstNode == null) System.out.println("List is empty");
        else if (index == 0) FirstNode = FirstNode.nextnode;
        else {
            for (int i = 0; i < index - 1; i++) {
                counterA = counterA.nextnode;
            }
            counterB = counterA.nextnode;
            counterA.nextnode = counterB.nextnode;
            counterB.nextnode = null;
        }
        SizeofList -= 1;
        myindex -= 1;
        return index;
    }

    /**
     * Removes the specified integer.
     */
    @Override
    public boolean Remove(Integer o) {
        Node ePresent;
        Node temp;
        if (!contains(o)) System.out.println("Element Not Found");
        if (FirstNode == null) System.out.println("Empty List !!");
        while (FirstNode.data == o) {
            FirstNode = FirstNode.nextnode;
        }
        temp = FirstNode;
        ePresent = FirstNode.nextnode;
        while (ePresent != null) if (ePresent.data == o) {
            Node newN = ePresent.nextnode;
            temp.nextnode = newN;
            ePresent = newN;
            SizeofList--;
            break;
        } else {
            temp = ePresent;
            ePresent = ePresent.nextnode;
        }

        return true;
    }


    /**
     * Replaces the element at the index with the specified index
     */
    @Override
    public int set(int index, int element) {
        Node Current = new Node();
        Current.data = element;
        Current.nextnode = FirstNode;
        Node counter = FirstNode;

        if (index == 0) FirstNode.data = element;
        else {
            for (int i = 0; i < index; i++) {
                counter = counter.nextnode;
            }
            counter.data = element;
        }
        return counter.data;
    }

    /**
     * Returns the size of the list
     */
    public int size() {
        Node counter = new Node();
        int FinalCount = 0;
        if (FirstNode == null) {
            System.out.println("List is empty");
        } else {
            counter.nextnode = FirstNode;
            int count = 0;
            while (counter.nextnode != null) {
                count++;
                counter = counter.nextnode;
            }
            FinalCount = count;
        }
        return FinalCount;
    }

    /**
     * Checks of the list contains the specified element
     */
    public boolean contains(int e) {
        Node ePresent;
        for (ePresent = FirstNode; ePresent != null;
             ePresent = ePresent.nextnode) {
            if (ePresent.data == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the first occurence of the element
     */
    public int indexOf(int e) {
        Node current = FirstNode;
        int result = -1;
        for (int i = 0; i < SizeofList; i++) {
            if (e == (current.data)) {
                result = i;
                break;

            }
            current = current.nextnode;
        }
        return result;
    }

    /**
     * Returns the index of the first occurence of the element
     */
    public int lastIndexOf(int e) {
        Node current = FirstNode;
        int result = -1;
        for (int i = 0; i < SizeofList - 1; i++) {
            if (e == (current.data)) {
                result = i;

            }
            current = current.nextnode;
        }
        return result;
    }

    /**
     * Converts the list into string format
     */
    public String toString() {
        Object obj;
        String result = "";

        Node currentNode = FirstNode;

        while (currentNode != null) {
            obj = currentNode.data;
            result = result + obj.toString() + " ";
            currentNode = currentNode.nextnode;
        }
        return result;
    }

    /**
     * Sorts the list into ascending form
     */

    public void sort() {
        Node temp1 = FirstNode;
        Node temp2 = FirstNode;
        while (temp1 != null) {
            while (temp2.nextnode != null) {
                if (temp2.data > temp2.nextnode.data) {
                    int temp = temp2.data;
                    temp2.data = temp2.nextnode.data;
                    temp2.nextnode.data = temp;
                }
                temp2 = temp2.nextnode;
            }
            temp2 = FirstNode;
            temp1 = temp1.nextnode;
        }
    }
}