package org.docksidestage.bizfw.basic.buyticket;

public class TwoDayTicket implements Ticket {
    private final int displayPrice;
    private int countPark;
    private static final int COUNT = 2;

    public TwoDayTicket(int displayPrice) { this.displayPrice = displayPrice; }

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
        return !(countPark < COUNT);
    }

    public int getDays() { return 2; }
}
