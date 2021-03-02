package bstu.fit.walko.admin;

import bstu.fit.walko.bank.Card;
import bstu.fit.walko.bank.CardType;

public interface IAdmin {
    void cardBlock(Card card);
    void cardUnBlock(Card card);
    Card FindByNumber(long number);
    Card FindByType(CardType type);
}
