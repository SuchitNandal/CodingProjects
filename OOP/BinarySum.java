
public class BinarySum {
    public static int Calculation(String first, String second) {
        int sum1=0;int sum2=0;

        for (int i = first.length(); i > 0; i--) {
            if (Character.toString(first.charAt(i-1)).equals("1")){
                sum1 +=(int) Math.pow(2, first.length()-i);
            }
        }

        for (int i = second.length(); i > 0; i--) {
            if (Character.toString(second.charAt(i-1)).equals("1")){
                sum2 +=(int) Math.pow(2, second.length()-i);
            }
        }

        int sum = sum1+sum2;
        System.out.println("Adding " +sum1+" + "+sum2);
        System.out.println("Sum in Decimal = "+sum);
        return sum;
    }

    public static int Binary(int number){
        int quotient=number;
        int value = number;
        String binary="";
        while (quotient > 0){
            int remainder = 0;
            remainder = quotient%2;
            binary = binary +remainder;
            quotient = quotient/2;
        }
        String reverse = "";
        for(int i = binary.length() - 1; i >= 0; i--)
        {
            reverse = reverse + binary.charAt(i);
        }
        System.out.println("Sum in Binary: b'"+reverse);
        return value;
    }

    public static void HexaDecimal(int number){
        int quotient= number;
        String Hexa="";
        while (quotient > 0){
            int remainder = 0;
            remainder = quotient%16;
            if (remainder <10) Hexa = Hexa + remainder;
            if (remainder==10) Hexa = Hexa + "A";
            if (remainder==11) Hexa = Hexa + "B";
            if (remainder==12) Hexa = Hexa + "C";
            if (remainder==13) Hexa = Hexa + "D";
            if (remainder==14) Hexa = Hexa + "E";
            if (remainder==15) Hexa = Hexa + "F";
            quotient = quotient/16;
        }
        String reverse = "";
        for(int i = Hexa.length() - 1; i >= 0; i--)
        {
            reverse = reverse + Hexa.charAt(i);
        }
        System.out.println("Sum in Hexadecimal: 0x"+reverse);
    }

        public static void main (String args[]){
            if (args.length == 2) {
                HexaDecimal(Binary(Calculation(args[0],args[1])));

            } else
                System.out.println("Correct Format: BinaryNumber1 BinaryNumber2");
        }
    }

