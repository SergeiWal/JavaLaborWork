package bstu.fit.walko.basejava;

import java.util.Objects;

/**
 * @author  Sergo
 * @version  JDK 15
 */
public class WrapperString  {

    private String str;


    /**
     * @throws Exception
     * @param str
     * @return null
     */
    public WrapperString(String str) {
        this.str = str;
    }

    /**
     * @throws Exception
     * @param
     * @return null
     */
    public String getStr() {
        return str;
    }

    /**
     * @throws Exception
     * @param str
     * @return null
     */
    public void setStr(String str) {
        this.str = str;
    }


    @Override
    /**
     * @throws Exception
     * @param Object
     * @return boolean
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperString that = (WrapperString) o;
        return Objects.equals(str, that.str);
    }

    @Override
    /**
     * @throws Exception
     * @param
     * @return int
     */
    public int hashCode() {
        return Objects.hash(str);
    }

    @Override
    /**
     * @throws Exception
     * @param
     * @return String
     */
    public String toString() {
        return "WrapperString{" +
                "str='" + str + '\'' +
                '}';
    }

    /**
     * @throws Exception
     * @param oldchar:char newchar:char
     * @return boolean
     */
    public void replace (char oldchar, char newchar)
    {
        str = str.replace(oldchar, newchar);
    }
}
