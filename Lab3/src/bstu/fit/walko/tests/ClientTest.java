package bstu.fit.walko.tests;

import bstu.fit.walko.bank.Card;
import bstu.fit.walko.bank.CardType;
import bstu.fit.walko.client.Client;
import bstu.fit.walko.client.GenderType;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.testng.Assert.*;


public class ClientTest {

    private Client client;

    @BeforeSuite
    public void startTests()
    {
        System.out.println("=================== Tests start ==========================");
    }


    @BeforeTest(groups = {"util1","util2"})
    public void clientInitilization(){
        client = new Client("Sergei","Walko", GenderType.MEN, 3);
    }

    @DataProvider(name = "card1")
    public Object[][] createCard1(){
        return new Object[][]{
                {
                        111111, 24654.235,
                        new GregorianCalendar(2002,4,3),  2341233214l, 123,
                        new GregorianCalendar(2021,1,12), "sigurg", CardType.MASTER_CARD
                }
        };
    }


    @Test(dataProvider = "card1", groups = {"util1"})
    public void addCardTest(long scoreNumber, double balance, Calendar beginDate, long cardNumber, int SVV, Calendar workEndDate,
                            String signature, CardType tp){
        Card card = new Card(scoreNumber, balance,
                beginDate,  cardNumber, SVV,
                workEndDate, signature, tp);

        client.addCard(card);

        assertTrue(client.getCards()[0] == card,
                "Test 1 failed: error in add card>");
    }

    @Test(timeOut = 10000, groups = {"util1"})
    public void paymentWithEmptyCard(){
        Card card1 = new Card(111111, 24654.235,
                new GregorianCalendar(2002,4,3),  2341233214l, 123,
                new GregorianCalendar(2021,1,12), "sigurg", CardType.MASTER_CARD);
        Card card2 = new Card(222222,24654.235,
                new GregorianCalendar(2002,4,3),4537765745l,523,
                new GregorianCalendar(2021,1,12),"rower", CardType.AMERICAN_EXPRESS);
        int value = 123444;
        double balanceBeforePayment = card2.getBalance();

        client.addCard(card1);
        client.payment(card2.getCardNumber(), value);

        assertEquals(card2.getBalance(), balanceBeforePayment, 10, "Test 2 failed: error in payment>");
    }


    @Parameters({"scoreNumber","balance","cardNumber","SVV","signature"})
    //@Ignore
    @Test
    public void transferMoneyFromCardToCard(long scoreNumber, double balance,long cardNumber, int SVV, String signature){
        Card card1 = new Card(scoreNumber, balance,
                new GregorianCalendar(2002,4,3),  cardNumber, SVV,
                new GregorianCalendar(2021,1,12), signature, CardType.MASTER_CARD);
        Card card2 = new Card(222222,24654.235,
                new GregorianCalendar(2002,4,3),4537765745l,523,
                new GregorianCalendar(2021,1,12),"rower", CardType.AMERICAN_EXPRESS);
        int value = 12;
        double balanceBeforePayment = card2.getBalance();

        client.addCard(card1);
        client.transfer(card1.getCardNumber(), value, card2);

        assertEquals(card2.getBalance(), balanceBeforePayment+=value, 10, "Test 3 failed: error in money transfer>");
    }

    @Test(groups = {"util2"})
    public void blockingCardTest(){
        Card card1 = new Card(111111, 24654.235,
                new GregorianCalendar(2002,4,3),  2341233214l, 123,
                new GregorianCalendar(2021,1,12), "sigurg", CardType.MASTER_CARD);

        client.addCard(card1);
        client.blockingCard(card1.getCardNumber());

        assertFalse(card1.isBlocked(),"Test 4 failed: error in blocking card>");
    }
}