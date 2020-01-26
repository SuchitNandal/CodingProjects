/**
 * IntegerArrayList class extends the abstract class IntegerStorageImplementation.
 */
public class IntegerArrayList extends IntegerStorageImplementation {

    /**
     * Constructor creates an array of size 10.
     */
    public IntegerArrayList(){
        arraysize = 10;
        array = new int[arraysize];
    }


    /**
     * @param o - Adds the int o to the array.
     * @return - Returns true if added.
     */
    public boolean add(int o) {
        if (SizeofList == array.length-1){
            increasesize();
        }
        array[SizeofList++] = o;
        return true;
    }

    /**
     * Helper function which is used to increase the size of the array.
     */
    public void increasesize(){
        int newsize = array.length*sizefactor;
        int[] newarray = new int[array.length];
        int i =0;
        for (int number : array){
            newarray[i++] = number;
        }

        array = new int[newsize];
        int j =0;
        for (int number: newarray){
            array[j++]=number;
        }
    }

    /**
     * @param index - index of the array.
     * @param element - The element to be added in the array.
     */
    public void add(int index, int element) {
        if (index>SizeofList){
            System.out.println("Index out of bounds.");
            return;
        }
        if (index==SizeofList){
            int temp = array[SizeofList];
            array[index] = element;
            array[index+1] = temp;
            SizeofList++;
            return;
        }
        int[] temp = new int[SizeofList-index+1];
        int j = 0;
        for(int i = index; i <=SizeofList;i++){
            temp[j++] = array[i];
        }
        array[index] = element;
        int k=0;
        for (int i = index+1;i<=SizeofList+1;i++){
            array[i] = temp[k++];
        }
        SizeofList++;
    }

    /**
     * Clears all the elements in the array.
     */
    public void clear() {
        array = new int[arraysize];
        SizeofList =0;
    }

    /**
     * @param o - The element which is to be checked if present in the array.
     * @return - Returns true if it is present.
     */
    public boolean contains(int o) {
        for (int i=0;i<SizeofList;i++){
            if (array[i]==o) return true;
        }
        return false;
    }

    /**
     * @param index - Get the element at the particular index.
     * @return - Returns the element.
     */
    public int get(int index) {
        if (index>SizeofList){
            System.out.println("Index out of bounds.");
            return -1;
        }
        int temp = array[index];
        return temp;
    }

    /**
     * @param o - The element to be checked.
     * @return - Returns the index of the element.
     */
    public int indexOf(int o) {
        int found =0;
        int temp=0;
        for(int i =0; i<array.length;i++){
            if (o ==array[i]){
                 temp = i;
                found = 1;
                break;
            }
        }
        if (found==0) return -1;
        else return temp;
    }

    /**
     * @param o - The element to be checked.
     * @return - Returns the last index of the element.
     */
    public int lastIndexOf(int o) {
        int found =0;
        int temp=0;
        for(int i =0; i<array.length;i++){
            if (o ==array[i]){
                temp = i;
                found = 1;
            }
        }
        if (found==0) return -1;
        else return temp;
    }

    /**
     * @param index - Removes the element at the particular index.
     * @return - Return the element.
     */
    public int remove(int index) {
        if (index>SizeofList){
            System.out.println("Index out of bounds.");
            return -1;
        }
        int temp = array[index];
        for (int i = index;i<SizeofList;i++){
            array[i] = array[i+1];
        }
        SizeofList--;
        return temp;
    }

    /**
     * @param o - The integer to be removed.
     * @return - The element removed.
     */
    public boolean Remove(Integer o) {
        int temp=0;
        int found=0;
        for (int i=0;i<SizeofList;i++){
            if (o == array[i]){
                 temp =i;
                 found=1;
                break;
            }
        }
        if (found==0) return false;
        for (int i = temp;i<SizeofList;i++){
            array[i] = array[i+1];
        }
        SizeofList--;
        return true;
    }


    /**
     * @param index - The particular index.
     * @param element - Replacing the element.
     * @return - Returns true if replaced.
     */
    public int set(int index, int element) {
        if (index>=SizeofList){
            System.out.println("Index out of bounds.");
            return -1;
        }
        int temp = array[index];
        array[index] = element;
        return element;
    }

    /**
     * @param os - An array which is to be added.
     * @return - Returns true if added
     */
    public boolean addAll(int index, int[] os) {
        while (SizeofList+os.length > array.length) increasesize();
        for (int j =0;j<SizeofList;j++) {
            if(j==index){
                for (int i=0;i<os.length;i++){
                    add(index++,os[i]);
                }
                break;
            }
        }
        return true;
    }

    /**
     * @return - Prints the list of all the elements in the list.
     */
    public String toString(){
        String result ="";
        for (int i=0;i<SizeofList;i++){
           result+=array[i] + " ";
        }
        return result;
    }

    /**
     * Sort the given list.
     */
    public void sort(){
        boolean swapping = true;
        int j = 0;
        int temp;
        while (swapping) {
            swapping = false;
            j++;
            for (int i = 0; i < SizeofList - j; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapping = true;
                }
            }
        }
    }

}
