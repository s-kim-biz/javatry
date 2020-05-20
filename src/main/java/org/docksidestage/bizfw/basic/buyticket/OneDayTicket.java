package org.docksidestage.bizfw.basic.buyticket;

public class OneDayTicket implements Ticket {

    private final int displayPrice;
    private boolean alreadyIn;

    public OneDayTicket(int displayPrice) { this.displayPrice = displayPrice; }

    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    public int getDisplayPrice() {
        return this.displayPrice;
    }

    public boolean isAlreadyIn() {
        return this.alreadyIn;
    }

    public int getDays(){ return 1; }
}
