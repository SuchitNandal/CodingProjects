public class test {

    public static void testmethod(IntegerStorage is){
        System.out.println("adding 10 elemennts to the storage: ");
        for (int i=0;i<10;i++){
            is.add(i);
        }
        System.out.println(is);

        System.out.println("adding 1 entire array ");
        int[] array = {1000,10101,212,23243,100,101};
        is.addAll(3,array);
        System.out.println("\n"+is);

        System.out.println("removing at index 0 \n" + is.remove(0) +"\n"+ is);
        System.out.println("what is the size of the storage right now \n"+is.size()+"\n");
        System.out.println("Checking if it contains 11 in the list\n "+ is.contains(11) +"\n"+ is);
        System.out.println("removing 3,4,5 from the list \n"+ is.removeAll(new int[]{3,4,5}) +"\n"+ is);
        System.out.println("removing the element \n" + is.Remove(1) +"\n"+ is);
        System.out.println("\n\n");
    }

    public static void main(String[] args){
        IntegerStorage linked = new IntegerLinkedList();
        testmethod(linked);

        IntegerStorage integer = new IntegerArrayList();
        testmethod(integer);

        System.out.println(integer.contentEquals(linked ));
    }
}
