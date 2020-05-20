package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author s.kim
 */
public class DaysTicket implements Ticket {
    private final int displayPrice;
    private int countPark;
    private final int days;

    public DaysTicket(int days, int displayPrice) {
        this.days = days;
        this.displayPrice = displayPrice;
    }

    public void doInPark() {
        if (this.isAlreadyIn()) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        ++countPark;
    }

    public int getDisplayPrice() {
        return this.displayPrice;
    }

    public boolean isAlreadyIn() {
        return !(countPark < days);
    }

    public int getDays() {
        return this.days;
    }
}
