package bstu.fit.walko.basejava;

import bstu.fit.Main;
import bstu.fit.walko.TextFunction;
import static  java.lang.Math.*;
import javax.annotation.processing.SupportedSourceVersion;

/**
 * This file has start point
 * 07.02.2021
 * @author Sergo
 * @version  JDK 15
 */


public class JavaTest {

    /**
     * @param args
     * @throws  Exception
     * @return  0
     */
    public static void main(String[] args) {

        //TODO task B
        //типа char, int, short, byte, long, boolean.
        char ch = 'C';
        int number = 12;
        short snymber = 3;
        byte littleNumber = 2;
        long bigNumber = 143154534645l;
        boolean value = true;
        String str = "Hello world!!!";

        /*Strint + int
         String+ char
         String +double
         byte = byte + int
         int = double+long
         long = int + 2147483647; // при выводе значение должно быть
        положительное
         static int sint; // выведите значение без инициализации*/
        System.out.println("Task B:");
        System.out.println(str + ch);
        System.out.println(str + number);
        System.out.println(str + 123.456);

        littleNumber = (byte)(littleNumber + number);
        number = (int)(12.32 + bigNumber);
        bigNumber = number + 2147483647l;
        System.out.println(bigNumber);
        TextFunction txt = new TextFunction(123);
        System.out.println("Static default int:" + txt.sint);

        /*boolean = boolean && boolean
         boolean= boolean ^ boolean
         проверьте можно ли выполнить boolean + boolean
         подберите типы для чисел 9223372036854775807 и
        0x7fff_ffff_fff
         проинициализируйте и выведите char - 'a' ; \u0061'; 97; после
        чего сложите все char.
         Проверьте результат операции 3.45 % 2.4
         Проверьте результат операции 0.0/0.0;
         Проверьте результат операции log(-345);
         Проверьте результат Float.intBitsToFloat(0x7F80*/

        boolean flag = true && false;
        flag= true^true;
        System.out.println(flag);
        //true + false
        long bigBigNumber = 9223372036854775807l;
        long xValue = 0x7fff_ffff_fffl;
        char a='a',b='\u0061',c=97;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(a + b + c);

        System.out.println(3.45%2.4);
        System.out.println(0.0/0.0);
        System.out.println(Math.log(-345));
        System.out.println(Float.intBitsToFloat(0x7F800000));
        System.out.println(Float.intBitsToFloat(0xFF800000));

        //TODO task D
        System.out.println("Task D:");
        System.out.println(Math.PI);
        System.out.println(Math.E);
        System.out.println(Math.round(Math.PI));
        System.out.println(Math.round(Math.E));
        System.out.println(Math.min(Math.PI, Math.E));

        System.out.println(Math.random());

        //TODO task E
        /*Создать объекты разных классов оболочек (Boolean,
                Character, Integer, Byte, Short, Long, Double)
         выполните на ними арифметические, логические и битовые
        операторы (, >>>, >>, ~, &, *, -, +) – выборочно
         веведите MIN_VALUE и MAX_VALUE для Long и Double
         выполнить упарковку и распаковку для типов Integer и Byte
         вызовите для Integer методы : parseInt ; toHexString ; compare ;
        toString ; bitCount ; isNaN … */
        System.out.println("Task E");
        Boolean bl = true;
        Character smb = 'c';
        Integer integer = 14;
        Byte bt = 12;
        Long ln = 124325325215l;
        Double dbl = 3.14;

        System.out.println(bt + dbl);
        System.out.println(bt - integer);
        System.out.println(integer>>>2);
        System.out.println(bl&bl);

        System.out.println("Long min:" + Long.MIN_VALUE);
        System.out.println("Long max:" + Long.MAX_VALUE);
        System.out.println("FLoat min:" + Float.MIN_VALUE);
        System.out.println("Float min:" + Float.MAX_VALUE);

        byte vb = bt;
        int it = integer/2;
        it = integer.intValue();

        Integer integer2 =13;
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.toHexString(12));
        System.out.println(Integer.compare(12,13));
        System.out.println(Integer.toString(34));
        System.out.println(Integer.bitCount(4));
        System.out.println(Float.isNaN(12.2f));

        //TODO Task f
        System.out.println("Task F");
        /*Выполните преобразование числа типа String (String s34 = "2345";)
        к int , используя: конструктор, valueOf, parse….
         переводите строку в массив байтов и обратно из массива байтов в
                строку
         преобразуйте строку в логический тип 2-мя способами.
         определите два строки (String) с одинаковыми инициализаторами.
                Выполите ==, equals, compareTo. В чем разница, поясните
        результат. Одной из строк присвойте null. Повторите все тир
        варианта сравнения.
        19
         для произвольной строки выполните функции split, contains,
        hashCode, indexOf, length, replace.
        */

        String strNumb = "2345";
        //Integer nmb;
        Integer nmb = new Integer(strNumb);
        Integer nmb2 = Integer.valueOf("2345");
        int nmb3 = Integer.parseInt("2345");

        byte[] byteArray = strNumb.getBytes();
        System.out.println(byteArray);
        String strNmb = new String(byteArray);
        System.out.println(strNmb);

        String bool = "True";

        boolean bl1 = Boolean.parseBoolean(bool);
        boolean bl2 = Boolean.valueOf(bool);

        String str1 = "Sergei";
        String str2 = "Sergei";

        if(str1==str2)
        {
            System.out.println("true");
        }
        else
            {
                System.out.println("false");
            }

        if(str1.equals(str2))
        { System.out.println("true");}
        else{ System.out.println("false");}

        if(str1.compareTo(str2) == 0)
        { System.out.println("true");}
        else{ System.out.println("false");}

        str2 = null;
        /*if(str1==str2)
        {
            System.out.println("true");
        }
        else
        {
            System.out.println("false");
        }

        if(str1.equals(str2))
        { System.out.println("true");}
        else{ System.out.println("false");}

        if(str1.compareTo(str2) == 0)
        { System.out.println("true");}
        else{ System.out.println("false");}*/

        //для произвольной строки выполните функции split, contains,
        //        hashCode, indexOf, length, replace.

        String[] strArray = str1.split("r");
        for(String s:strArray)
            System.out.println(s);
       if(str1.contains("S")) System.out.println("true");
       System.out.println(str1.hashCode());
       System.out.println(str1.indexOf(2));
       System.out.println(str1);
       System.out.println(str1.replace('r','R'));

       //TODO Task G
        System.out.println("Task G");

        char[][] c1 ;
        char[] c2[] = new char[2][5];
        char c3[][] = new char[3][2];

        c1 = new char[3][];
        c1[0] = new char[2];
        c1[1] = new char[3];
        c1[2] = new char[4];

        //boolean comRez = с2==с3;
        //с2 = с3;

        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c1[i].length; j++) {
                c1[i][j] = 'a';
            }
        }

        for (var h:
             c1) {
            for(var g: h)
                System.out.print(g);
            System.out.println();
        }

        //TODO Task H
        System.out.println("Task H");
        WrapperString wrapperString = new WrapperString("Hello world!!!");
        wrapperString.replace('l','L');
        System.out.println(wrapperString.getStr());

        WrapperString wrapperString1 = new WrapperString("Hello world!!")
        {
            @Override
            public void replace (char oldchar, char newchar)
            {
                 System.out.println(str.replace(oldchar, newchar));

            }


            public void  delete(char newchar) {
                System.out.println(str.replace("" +newchar,""));
            }
        };
        wrapperString1.replace('l','p');

        //TODO Task I


        }
    }

