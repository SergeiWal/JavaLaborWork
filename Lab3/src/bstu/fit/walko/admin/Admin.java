package bstu.fit.walko.admin;

import bstu.fit.walko.bank.Card;
import bstu.fit.walko.bank.CardType;
import bstu.fit.walko.client.GenderType;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Admin  implements IAdmin
{
    private static final Logger LOG =
           Logger.getLogger(Admin.class);
    private Men menInfo;
    Card[] cards;

    public Men getMenInfo() {
        return menInfo;
    }

    public void setMenInfo(Men menInfo) {
        this.menInfo = menInfo;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Admin(String name, String lastName, GenderType gender, Card[] cards) {
       LOG.info("Create amin___________________");
        this.menInfo =  new Men(name,lastName,gender);
        this.cards = cards;
    }

    class Men
    {
        private String name;
        private String lastName;
        private GenderType gender;

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

        public GenderType getgender() {
            return gender;
        }

        public void setgender(GenderType gender) {
            this.gender = gender;
        }

        public Men(String name, String lastName, GenderType gender) {
            this.name = name;
            this.lastName = lastName;
            this.gender = gender;
        }
    }


    @Override
    public void cardBlock(Card card) {
        card.blockScore();
       LOG.info("Card(" + card.getCardNumber() + ") blocked...");
    }

    @Override
    public void cardUnBlock(Card card) {
        card.setBlocked(false);
        LOG.info("Card(" + card.getCardNumber() + ") unblocked...");
    }

    @Override
    public Card FindByNumber(long number) {
        for (var c:
             cards) {
            if(c.getCardNumber() == number)
                return c;
        }
        return null;
    }

    @Override
    public Card FindByType(CardType type) {
        for (var c:
                cards) {
            if(c.getType() == type)
                return c;
        }
        return null;
    }

    public static  boolean Serializer(Admin object, String path) throws IOException {
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

    public static Admin Deserialiser(String path)
    {
        try(FileReader reader = new FileReader(path))
        {
            int c ;
            String buf ="";
            while ((c = reader.read())!=-1)
            {
                buf += (char)c;
            }
            Admin newAdmin = JSON.parseObject(buf,Admin.class);
            return newAdmin;
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
