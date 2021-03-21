package bstu.fit.walko.bank;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public abstract class BankAccount {
    protected long scoreNumber;
    protected double balance;
    protected Calendar beginDate;
    protected boolean isBlocked = false;

    public long getScoreNumber() {
        return scoreNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Calendar getBeginDate() {
        return beginDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setScoreNumber(long scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public BankAccount(long scoreNumber, double balance, Calendar beginDate) {
        this.scoreNumber = scoreNumber;
        this.balance = balance;
        this.beginDate = beginDate;
    }

    public abstract void topUpAnScore(double value);
    public abstract void payment(double value);
    public abstract void transfer(Object score, double value);
    public abstract  void blockScore();

    @Override
    public String toString()
    {
        Formatter formatter  = new Formatter();
        formatter.format("Score(Number:%d Balance:%f isBlocked:%b)",scoreNumber,balance,isBlocked);

        return formatter.toString();
    }
}
