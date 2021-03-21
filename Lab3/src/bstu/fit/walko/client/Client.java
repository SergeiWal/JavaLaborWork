package bstu.fit.walko.client;

import bstu.fit.walko.bank.BankAccount;
import bstu.fit.walko.bank.Card;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Client {

    public static  final Logger Log = Logger.getLogger(Client.class);
    private String name;
    private String lastName;
    private GenderType gender;
    private BankAccount[] cards;
    private int currentCardCount = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public BankAccount[] getCards() {
        return cards;
    }

    public void setCards(BankAccount[] cards) {
        this.cards = cards;
    }

    public Client(String name, String lastName, GenderType gender, int cardCount) {
        Log.info("Create client("+ name + ")");
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        cards = new BankAccount[cardCount];
    }

    public void addCard(Card newCard)
    {
        try
        {
            if(currentCardCount < cards.length)
            {
                cards[currentCardCount] = newCard;
                currentCardCount++;
            }
            else throw new Exception("Cards array overflow...");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void printBalance(long cardNumber)
    {
        try
        {
            for (var i:
                 cards) {
                if(i != null && ((Card)i).getCardNumber()==cardNumber)
                {
                    System.out.println(((Card)i).getBalance());
                    Log.info(this.name + " balance: " + ((Card)i).getBalance());
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            Log.info("This card didn't found ...");
        }
    }

    public void payment(long cardNumber, double value)
    {
        try
        {
            for (var i:
                    cards) {
                if(i != null && ((Card)i).getCardNumber()==cardNumber)
                {
                    ((Card)i).payment(value);
                    Log.info("Payment good");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public void transfer(long cardNumber, double value, Card dest)
    {
        try
        {
            for (var i:
                    cards) {
                if(i != null && ((Card)i).getCardNumber()==cardNumber)
                {
                    ((Card)i).transfer(dest,value);
                    Log.info("Transfer from " + cardNumber + " to " + dest.getCardNumber() + "...");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void topUpAnScore(long cardNumber, double value)
    {
        try
        {
            for (var i:
                    cards) {
                if(i != null && ((Card)i).getCardNumber()==cardNumber)
                {
                    ((Card)i).topUpAnScore(value);
                   Log.info("An " + cardNumber + "send many...");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public  void blockingCard(long cardNumber)
    {
        try
        {
            for (var i:
                    cards) {
                if(i != null && ((Card)i).getCardNumber()==cardNumber)
                {
                    ((Card)i).blockScore();
                    Log.info(cardNumber + " blocked...");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static  boolean Serializer(Client object, String path) throws IOException {
        try(FileWriter writer = new FileWriter(path, false))
        {
            String buf= JSON.toJSONString(object);
            writer.write(buf);
            return  true;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    public static Client Deserialiser(String path)
    {
        try(FileReader reader = new FileReader(path))
        {
            int c ;
            String buf ="";
            while ((c = reader.read())!=-1)
            {
                buf += (char)c;
            }
            Client newClient = JSON.parseObject(buf,Client.class);
            return newClient;
        }
        catch (IOException e)
        {
            return null;
        }
    }
}


