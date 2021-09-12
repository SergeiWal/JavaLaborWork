package fit.bstu.walko.Lab13.commands.factory;

import fit.bstu.walko.Lab13.commands.*;
import org.apache.log4j.Logger;


public class CommandFactory {


    private static final Logger  LOGGER = Logger.getLogger(CommandFactory.class);

    public static Command Create(String command){

        command = command.toUpperCase();
        CommandType commandType = CommandType.valueOf(command);
        Command resultCommand;

        switch (commandType){
            case LOGIN:
                resultCommand = new LoginCommand();
                break;
            case WELCOME:
                resultCommand = new WelcomeCommand();
                break;
            case ADD_UNI:
                resultCommand = new AddUniCommand();
                break;
            case DEL_UNI:
                resultCommand = new DeleteUniCommand();
                break;
            case REGISTRATION_PAGE:
                resultCommand = new RegistrationPageCommand();
                break;
            case LOGIN_PAGE:
                resultCommand = new LoginPageCommand();
                break;
            case SIGN_OUT:
                resultCommand = null;
                break;
            case REGISTRATION:
                resultCommand = new RegistrationCommand();
                break;
            default:
                resultCommand = null;
                break;
        }

        LOGGER.info(resultCommand);
        return resultCommand;
    }
}
