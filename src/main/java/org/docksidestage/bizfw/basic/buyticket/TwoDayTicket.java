package org.docksidestage.bizfw.basic.buyticket;

public class TwoDayTicket implements Ticket {
    private final int displayPrice;
    private int countPark;

    public TwoDayTicket(int displayPrice) { this.displayPrice = displayPrice; }

    public void doInPark() {
        if (!(countPark < 2)) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        ++countPark;
    }

    public int getDisplayPrice() {
        return this.displayPrice;
    }

    public boolean isAlreadyIn() {
        return !(countPark < 2);
    }
}
