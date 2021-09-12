package fit.bstu.walko.Lab13;

public class Hasher {

    public static String getHash(String password){
        String result = "";
        for(int i=0; i<password.length(); ++i){
            result += password.charAt(i) + 13;
        }
        return result;
    }
}