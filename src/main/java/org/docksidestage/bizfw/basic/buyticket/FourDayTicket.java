package org.docksidestage.bizfw.basic.buyticket;

public class FourDayTicket implements Ticket {

    private final int displayPrice;
    private int countPark;
    private static final int COUNT = 4;

    public FourDayTicket(int displayPrice) { this.displayPrice = displayPrice; }

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
}
