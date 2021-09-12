package fit.bstu.walko.Lab13.repository.dbConstants;

public enum UserConstant {
    ID("id"),
    NAME("user_name"),
    LOGIN("login"),
    PASS("user_password"),
    ROLE("user_role");

    private String fieldName;

    private UserConstant(String fieldName){
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
