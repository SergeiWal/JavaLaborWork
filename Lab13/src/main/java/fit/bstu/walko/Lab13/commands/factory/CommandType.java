package fit.bstu.walko.Lab13.commands.factory;

public enum CommandType {

    LOGIN("login"),
    SIGN_OUT("sign_out"),
    REGISTRATION("registration"),
    LOGIN_PAGE("login_page"),
    REGISTRATION_PAGE("registration_page"),
    ADD_UNI("add_uni"),
    DEL_UNI("del_uni"),
    WELCOME("welcome");

    private  String command;

    CommandType(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
