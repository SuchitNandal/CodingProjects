package org.h2.value;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.h2.api.ErrorCode;
import org.h2.message.DbException;

public class Password implements Serializable{

    public static final long serialVersionUID = 1L;
    public String Password;

    public Password(String password) throws NoSuchAlgorithmException{
        System.out.println("Verifying Password correctness..");
        verifyPassword(password);
    }

    public void verifyPassword(String input) throws NoSuchAlgorithmException{
        String Regex = "((?=.*[a-z])(?=.*\\d)(?=.*[@#$%])(?=.*[A-Z]).{5,20})";
        Pattern PasswordPattern = Pattern.compile(Regex);
        Matcher PasswordMatcher = PasswordPattern.matcher(input);
        System.out.println("Match: "+PasswordMatcher.matches());
        if (PasswordMatcher.matches()){
            System.out.println("Setting Password datatype with value: " + input);
            this.Password = input;
        } else {
            System.out.println("Exception thrown!!");
            throw DbException.get(ErrorCode.Invalid_Password);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Password other = (Password) obj;
        if (Password == null) {
            System.out.println("Password is null");
            if (other.Password != null)
                return false;
        } else if (!Password.equals(other.Password))
            return false;
        return true;
    }


    @Override
    public String toString(){
        return Password;
    }

    @Override
    public int hashCode() {
        // randomly generate hash code.
        final int number = 67;
        return 4 * ((Password == null) ? 0 : Password.hashCode()) + number;
    }
}