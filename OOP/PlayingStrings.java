public class StringThing
{   //This method returns true or false depending on the fact
    // if both the strings point to the same location in momory or no.
    private static boolean equalityTest1(String a, String b)
    {
        return a == b;
    }
    //This method returns true or false depending on the fact if
    //both the strings have the exact same content or no.
    //(Same location in memory is not necessary)
    private static boolean equalityTest2(String a, String b)
    {
        return a.equals(b);
    }
    //This method returns true or false depending on the fact if
    //both the string objects have the same hashcodes or no.
    private static boolean equalityTest3(String a, String b)
    {
        return System.identityHashCode(a) == System.identityHashCode(b);
    }
    //If both the strings are empty then it returns True else if
    //any 1 the string is empty then it will return false. If that is also not true then
    //checks if the character at index 0 is equal for both strings or no. If yes then
    //recursively call the function for substrings at index 1. Else just return false.
    private static boolean equalityTest4(String a, String b)
    {
        if(a.length() == 0 && b.length() == 0)
        {
            return true;
        }
        else
        {
            if(a.length() == 0 || b.length() == 0)
            {
                return false;
            }
            if(a.charAt(0) == b.charAt(0))
            {
                return equalityTest4(a.substring(1), b.substring(1));
            }
            else
            {
                return false;
            }
        }
    }

    //If both the strings have same content then it will return true because
    //.hashcode() returns same integer hashcode value for same content objects.
    private static boolean equalityTest5(String a, String b)
    {
        return a.hashCode() == b.hashCode();
    }

    public static void main(String[] args)
    {
        String abcV1 = "abc";
        String abcV2 = "a" + "b" + "c";
        String abcV3 = "abcd".substring(0, abcV1.length());
        String abcV4 = "" + abcV2;
        String abcV5 = "a" + (char)98 + 99;
        String abcV6 = new String("abc");
        String abcV7 = abcV3.intern();
        String abcv8 = abcV6;


        // using abcV1 as first parameter ...
        // all possible trues for equalityTest1
        System.out.println(equalityTest1(abcV1,abcV2));
        System.out.println(equalityTest1(abcV1,abcV7));

        // all possible trues for equalityTest2
        System.out.println(equalityTest2(abcV1,abcV2));
        System.out.println(equalityTest2(abcV1,abcV3));
        System.out.println(equalityTest2(abcV1,abcV4));
        System.out.println(equalityTest2(abcV1,abcV6));
        System.out.println(equalityTest2(abcV1,abcV7));
        System.out.println(equalityTest2(abcV1,abcv8));

        // all possible trues for equalityTest3
        System.out.println(equalityTest1(abcV1,abcV2));
        System.out.println(equalityTest1(abcV1,abcV7));

        // all possible trues for equalityTest4
        System.out.println(equalityTest4(abcV1,abcV2));
        System.out.println(equalityTest4(abcV1,abcV3));
        System.out.println(equalityTest4(abcV1,abcV4));
        System.out.println(equalityTest4(abcV1,abcV6));
        System.out.println(equalityTest4(abcV1,abcV7));
        System.out.println(equalityTest4(abcV1,abcv8));

        // all possible trues for equalityTest5
        System.out.println(equalityTest5(abcV1,abcV2));
        System.out.println(equalityTest5(abcV1,abcV3));
        System.out.println(equalityTest5(abcV1,abcV4));
        System.out.println(equalityTest5(abcV1,abcV6));
        System.out.println(equalityTest5(abcV1,abcV7));
        System.out.println(equalityTest5(abcV1,abcv8));

    }
}