package bstu.fit.walko;

import bstu.fit.walko.admin.Admin;
import bstu.fit.walko.bank.Card;
import bstu.fit.walko.bank.CardType;
import bstu.fit.walko.client.Client;
import bstu.fit.walko.client.GenderType;
import  bstu.fit.walko.xmlParsing.StaxStreamProcessor;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class Main {
    static{
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);


    public static void main(String[] args) throws IOException, XMLStreamException {

        LOG.info("Starting program_____________________________");
        Card card1 = new Card(111111, 24654.235,
                new GregorianCalendar(2002,4,3),  2341233214l, 123,
                new GregorianCalendar(2021,1,12), "sigurg", CardType.MASTER_CARD);
        Card card2 = new Card(222222,24654.235,
                new GregorianCalendar(2002,4,3),4537765745l,523,
                new GregorianCalendar(2021,1,12),"rower", CardType.AMERICAN_EXPRESS);
        Card card3 = new Card(333333,24654.235,
                new GregorianCalendar(2002,4,3),7667686566l,623,
                new GregorianCalendar(2021,1,12),"sewer", CardType.MAESTRO);
        Card card4 = new Card(444444,24654.235,
                new GregorianCalendar(2002,4,3),4335656435l,173,
                new GregorianCalendar(2021,1,12),"sigh", CardType.MASTER_CARD);
        Card card5 = new Card(555555,24654.235,
                new GregorianCalendar(2002,4,3),5486443557l,128,
                new GregorianCalendar(2021,1,12),"knock", CardType.AMERICAN_EXPRESS);
        Card card6 = new Card(666666,24654.235,
                new GregorianCalendar(2002,4,3),6756755465l,783,
                new GregorianCalendar(2021,1,12),"http", CardType.MAESTRO);
        Card card7 = new Card(777777,0.073,
                new GregorianCalendar(2002,4,3),2674676566l,993,
                new GregorianCalendar(2021,1,12),"top,", CardType.VISA);

        Client client1 = new Client("Sergei","Walko", GenderType.MEN, 3);
        Client client2 = new Client("Kazimir", "Kantor", GenderType.MEN,2);
        Client client3 = new Client("Efim","Kopil",GenderType.GAY, 2);

        Admin admin  = new Admin("Anna","Ivanova", GenderType.WOMEN,
                new Card[]{card1, card2, card3,card4,card5,card6,card7});

        client1.addCard(card1);
        client1.addCard(card2);
        client1.addCard(card3);

        client2.addCard(card4);
        client2.addCard(card5);

        client3.addCard(card6);
        client3.addCard(card7);

        System.out.println(card1);
        System.out.println(card2);
        System.out.println(card3);
        System.out.println(card4);
        System.out.println(card5);
        System.out.println(card6);
        System.out.println(card7);

        client1.blockingCard(2341233214l);
        client2.payment(4335656435l, 123.12);
        client3.printBalance(2674676566l);

        System.out.println("------------------------------------------");
        List<Client> clients = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        AddClients(clients);
        AddCards(cards);

        for (var c:
             clients) {
            System.out.println(c.getName());
        }
        for (var c:
             cards) {
            System.out.println(c.getCardNumber());
        }

        System.out.println("---------------------------------------------------------");
        Client.Serializer(client1, "files/client.json");
        Card.Serializer(card1,"files/card.json");

        Client desClient = Client.Deserialiser("files/client.json");
        Card desCard = Card.Deserialiser("files/card.json");

        System.out.println(desClient.getName());
        System.out.println(desCard.getCardNumber());

        clients.stream().filter((n) -> n.getGender() == GenderType.MEN).
                forEach(n->System.out.println(n.getName()));
        cards.stream().filter(n->n.getBalance()<1000).map(n->n.getCardNumber()).
                forEach(c->System.out.println(c));

        LOG.info("Program ended________________________________");
    }

    static void  AddClients(List<Client> clients)
    {
        try
        {
            StaxStreamProcessor processor =
                    new StaxStreamProcessor(Files.newInputStream(Path.of("files/info.xml")));
            XMLStreamReader reader = processor.getReader();
            while (processor.startElement("Client", null)) {
                //System.out.println(processor.getAttribute("name") +":" + processor.getText());
                GenderType gender = GenderType.MEN;
                switch (processor.getAttribute("gender"))
                {
                    case "MEN":
                        gender = GenderType.MEN;
                        break;
                    case "WOMEN":
                        gender = GenderType.WOMEN;
                        break;
                    case "GAY":
                        gender = GenderType.GAY;
                        break;
                    default:
                        break;
                }
                Client newClient = new Client(processor.getAttribute("name"),processor.getAttribute("lastName"),
                        gender, Integer.parseInt(processor.getAttribute("cardCount")));
                clients.add(newClient);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    static void  AddCards(List<Card> cards)
    {
        try
        {
            StaxStreamProcessor processor =
                    new StaxStreamProcessor(Files.newInputStream(Path.of("files/info.xml")));
            XMLStreamReader reader = processor.getReader();
            while (processor.startElement("Card", null)) {

                CardType cardType = CardType.MASTER_CARD;

                switch (processor.getAttribute("tp"))
                {
                    case "MASTER_CARD":
                        cardType = CardType.MASTER_CARD;
                        break;
                    case "AMERICAN_EXPRESS":
                        cardType = CardType.AMERICAN_EXPRESS;
                        break;
                    case "MAESTRO":
                        cardType = CardType.MAESTRO;
                        break;
                    case "VISA":
                        cardType = CardType.VISA;
                        break;
                    default:
                        break;
                }


                Card newCard = new Card(Long.parseLong(processor.getAttribute("scoreNumber")),
                        Double.parseDouble(processor.getAttribute("balance")),
                        new GregorianCalendar(Integer.parseInt(processor.getAttribute("bDay")),
                                Integer.parseInt(processor.getAttribute("bMonth")),
                                Integer.parseInt(processor.getAttribute("bYear"))),
                        Long.parseLong(processor.getAttribute("cardNumber")),
                        Integer.parseInt(processor.getAttribute("SVV")),
                        new GregorianCalendar(Integer.parseInt(processor.getAttribute("eDay")),
                                Integer.parseInt(processor.getAttribute("eMonth")),
                                Integer.parseInt(processor.getAttribute("eYear"))),
                        processor.getAttribute("signature"), cardType);
                cards.add(newCard);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
