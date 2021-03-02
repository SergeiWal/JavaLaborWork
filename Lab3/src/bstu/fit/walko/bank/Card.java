package bstu.fit.walko.bank;

import com.alibaba.fastjson.JSON;
import com.sun.source.tree.ReturnTree;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;

public class Card extends BankAccount
{
    private long cardNumber;
    private int SVV;
    private Calendar workEndDate;
    private  String signature;
    private CardType type;

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSVV() {
        return SVV;
    }

    public void setSVV(int SVV) {
        this.SVV = SVV;
    }

    public Calendar getWorkEndDate() {
        return workEndDate;
    }

    public void setWorkEndDate(Calendar workEndDate) {
        this.workEndDate = workEndDate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Card(long scoreNumber, double balance, Calendar beginDate, long cardNumber, int SVV, Calendar workEndDate,
                String signature, CardType tp) {
        super(scoreNumber, balance, beginDate);
        this.cardNumber = cardNumber;
        this.SVV = SVV;
        this.workEndDate = workEndDate;
        this.type = tp;
        this.signature = signature;
    }

    @Override
    public void topUpAnScore(double value) {
        super.balance += value;
    }

    @Override
    public void payment(double value) {
        super.balance -= value;
    }

    @Override
    public void transfer(Object score, double value) {
        if(score instanceof Card)
        {
            ((Card)score).topUpAnScore(value);
            super.balance -=value;
        }
    }

    @Override
    public void blockScore() {
        super.isBlocked = true;
    }

    @Override
    public String toString()
    {
        Formatter f = new Formatter();
        f.format("Card number:%d SVV:%d Signature:%s ScoreInfo{%s}",cardNumber, SVV, signature, super.toString());
        return f.toString();
    }

    public static  boolean Serializer(Card object, String path) throws IOException {
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

    public static Card Deserialiser(String path)
    {
        try(FileReader reader = new FileReader(path))
        {
            int c ;
            String buf ="";
            while ((c = reader.read())!=-1)
            {
               buf += (char)c;
            }
            Card newCard = JSON.parseObject(buf,Card.class);
            return newCard;
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
