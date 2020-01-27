package org.h2.api;
import java.security.NoSuchAlgorithmException;
import java.sql.Types;
import java.util.Locale;
import org.h2.message.DbException;
import org.h2.store.DataHandler;
import org.h2.value.*;
import org.h2.value.Password;
import org.h2.util.JdbcUtils;


public class CustomPassword implements CustomDataTypesHandler{

    public final static String PasswordName = "Password";
    public final DataType PasswordDataType;

    public CustomPassword() {
        DataType input = new DataType();
        input.name = PasswordName;
        input.type = Value.Password;
        input.sqlType = Types.JAVA_OBJECT;
        PasswordDataType = input;
    }

    @Override
    public DataType getDataTypeByName(String name) {
        if (name.toUpperCase(Locale.ENGLISH).equals(PasswordName)){
            System.out.println("Getting Password datatype by name.");
            return PasswordDataType;
        }else{
            System.out.println("Did not get Password datatype by name.");
            return null;
        }
    }

    @Override
    public DataType getDataTypeById(int type) {
        if (type == Value.Password){
            System.out.println("Getting Password datatype by type.");
            return PasswordDataType;
        }else{
            System.out.println("Did not get Password datatype by type.");
            return null;
        }
    }

    @Override
    public TypeInfo getTypeInfoById(int i, long l, int i1, ExtTypeInfo extTypeInfo) {
        return null;
    }

    @Override
    public int getDataTypeOrder(int type) {
        if (type == Value.Password){
            return 53_000;
        }else{
            throw DbException.get(ErrorCode.UNKNOWN_DATA_TYPE_1, "type:" + type);
        }
    }

    @Override
    public Value convert(Value source, int targetType) {
        System.out.println("In Convert: "+source.getType()+" "+targetType);
        return null;
    }

    @Override
    public String getDataTypeClassName(int type) {
        if (type == Value.Password){
            return Password.class.getName();
        }
        else{
            throw DbException.get(ErrorCode.UNKNOWN_DATA_TYPE_1, "type:"+type);
        }
    }

    @Override
    public int getTypeIdFromClass(Class<?> cls) {
        if (cls == Password.class){
            return Value.Password;
        }
        return Value.JAVA_OBJECT;
    }

    @Override
    public Value getValue(int type, Object data, DataHandler dataHandler) {
        System.out.println("Get the Value: "+type+" "+data.getClass());
        if (type == Value.Password && data instanceof Password) {
            try {
                return PasswordValue.get((Password) data);
            }
            catch (NoSuchAlgorithmException nsae) {
                nsae.printStackTrace();
            }
        }
        return ValueJavaObject.getNoCopy(data, null, dataHandler);
    }

    @Override
    public Object getObject(Value value, Class<?> cls) {
        if (cls.equals(Password.class)) {
            if(value.getType() == TypeInfo.TYPE_PASSWORD)
                return value.getObject();
            return convert(value, Value.Password).getObject();
        }
        else
            throw DbException.get(ErrorCode.UNKNOWN_DATA_TYPE_1, "type:" + value.getType());
    }

    @Override
    public boolean supportsAdd(int type) {
        return false;
    }

    @Override
    public int getAddProofType(int type) {
        if (type == Value.Password)
            return type;
        else
            throw DbException.get(ErrorCode.UNKNOWN_DATA_TYPE_1, "type:" + type);
    }

}
