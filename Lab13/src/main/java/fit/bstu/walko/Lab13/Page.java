package fit.bstu.walko.Lab13;

public enum Page {
    LOGIN_PAGE("/views/Login.jsp"),
    REGISTER_PAGE("/views/Registration.jsp"),
    WELCOME_PAGE("/views/Welcome.jsp"),
    ERROR_PAGE("/views/Error.jsp");

    private final String value;

    Page(String value){
        this.value = value;
    }

    public String getPage(){
        return value;
    }
}
