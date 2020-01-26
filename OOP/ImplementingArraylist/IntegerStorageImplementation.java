/**
 * IntegerStorageImplementation implements the interface IntegerStorage.
 */
public abstract class IntegerStorageImplementation implements IntegerStorage {

    public int arraysize;
    public int[] array;
    public int sizefactor = 2;
    public int SizeofList=0;

    Node FirstElement;
    int myindex = -1;

    /**
     * @param os - An array which is to be added.
     * @return - Returns true if added
     */
    public boolean	addAll(int[] os){
        for(int i=0;i<os.length;i++){
            add(os[i]);
        }
        return true;
    }
    /**
     * @param os - An array which is to be added at a particular index in a list.
     * @return - Returns true if added
     */
    public abstract boolean addAll(int index, int[] os);

    /**
     * @param os - Checks if all the elements passed in the array are present.
     * @return - Returns true if all are present.
     */
    public boolean containsAll(int[] os){
        for (int i=0;i<os.length;i++){
           if (!contains(os[i])) return false;
        }
        return true;
    }

    /**
     * @param os - Removes all the elements passed in the array.
     * @return - True if all the elements are removed.
     */
    public  boolean	removeAll(int[] os){
        for (int i=0;i<os.length;i++){
            if (contains(os[i])){
                Remove(os[i]);
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * @return - Returns the size of the list.
     */
    public int	size(){
        return SizeofList;
    }

    /**
     * @return - Returns true if the list is empty.
     */
    public  boolean	isEmpty(){
        if (SizeofList==0) return true;
        else return false;
    }

    /**
     * @return - Returns the sum of all the elements in the list.
     */
    public int hashCode(){
        String result;
        int sum = 0;
        int numbervalue=0;
        result = toString();
        if (result.equals("")) return 0;
        String[] numbers = result.split(" ");
        for (String number : numbers){
            numbervalue = Integer.parseInt(number);
            sum += numbervalue;
        }
        return sum;
    }

    /**
     * @return - Prints the list of all the elements in the list.
     */
    public abstract String toString();

    /**
     * @param o - Checks if both the storage have the same elements in the same order.
     * @return - True if they do.
     */
    public  boolean	contentEquals(IntegerStorage o){
        if (this.SizeofList!=o.size()) return false;
        for (int i=0;i<SizeofList;i++){
            if (array[i]==o.get(i)){
                continue;
            }
            else return false;
        }
        return true;
    }

    /**
     * @param o - Checks if both the storage have the same elements.
     * @return - True if they do.
     */
    public  boolean	equals(Object o){
        IntegerStorage is = (IntegerStorage)o;
        int found=0;
        if (this.SizeofList!=is.size()) return false;
        for (int i=0;i<SizeofList;i++){
            for (int j=0;j<SizeofList;j++){
                if (array[i]==is.get(j)){
                    found=1;
                    break;
                }
            }
            if (found==0) return false;
        }
       return true;
    }

    /**
     * Sort the given list.
     */
    public abstract void sort();
    }
