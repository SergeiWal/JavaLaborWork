package fit.bstu.walko.Lab13.repository.dbConstants;

public enum UniwerConstants {
    ID("id"),
    NAME("uni_name"),
    CITY("city"),
    COUNTRY("country");

    private String fieldName;

    private UniwerConstants(String fieldName){
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}