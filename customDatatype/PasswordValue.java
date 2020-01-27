package org.h2.value;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import org.h2.api.ErrorCode;
import org.h2.engine.Mode;
import org.h2.message.DbException;
import org.h2.util.JdbcUtils;


public class PasswordValue extends Value{

    public Password password;

    PasswordValue(String password) throws NoSuchAlgorithmException{
        this.password = new Password(password);
    }

    public PasswordValue(Password Value){
        this.password = Value;
    }

    public static Value get(Password password)  throws NoSuchAlgorithmException {
        PasswordValue value = new PasswordValue(password);
        System.out.println("Get Password Value" + value.getString());
        return value;
    }

    @Override
    public String getSQL() {
        System.out.println("Get SQL");
        return password.toString();
    }

    @Override
    public StringBuilder getSQL(StringBuilder builder) {
        return null;
    }

    @Override
    public TypeInfo getType() {
        System.out.println("Get Type "+ Value.Password);
        return TypeInfo.TYPE_PASSWORD;
    }

    public long getPrecision() {
        return 0;
    }


    @Override
    public int getValueType() {
        return 0;
    }

    @Override
    public String getString() {
        System.out.println("Get Password String" + password.toString());
        return password.toString();
    }

    @Override
    public Object getObject() {
        System.out.println("Getting password object.");
        return password;
    }

    @Override
    public void set(PreparedStatement prep, int parameterIndex) throws SQLException {
        System.out.println("Setting password value.");
        Object obj = JdbcUtils.deserialize(getBytesNoCopy(), getDataHandler());
        prep.setObject(parameterIndex, obj, Types.JAVA_OBJECT);
    }

    @Override
    public int compareTypeSafe(Value v, CompareMode mode) {
        System.out.println("Comparison");
        PasswordValue pass = (PasswordValue) v.getObject();
        System.out.println(mode.compareString(this.password.toString(), pass.password.toString(), false));
        return mode.compareString(this.password.toString(), pass.password.toString(), false);
    }

    @Override
    public int hashCode() {
        System.out.println("Hashcode: " + password.hashCode());
        return password.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        System.out.println(password.toString() + "<-Match->" + ((PasswordValue) other).getString());
        return other instanceof PasswordValue
                && password.equals(((PasswordValue) other).password);
    }

    public Value convertTo(int targetType, int precision, Mode mode, Object column, String[] enumerators) {
        if (41 == targetType) {
            return this;
        }
        switch (targetType) {
            case Value.BYTES: {
                return ValueBytes.getNoCopy(JdbcUtils.serialize(password, null));
            }
            case Value.STRING: {
                return ValueString.get(password.toString());
            }
            case Value.JAVA_OBJECT: {
                return ValueJavaObject.getNoCopy(JdbcUtils.serialize(password, null));
            }
        }
        throw DbException.get(
                ErrorCode.DATA_CONVERSION_ERROR_1, getString());
    }
}
