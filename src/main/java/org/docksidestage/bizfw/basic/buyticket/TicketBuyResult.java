package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int change;
    private final int days;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(int days, int handedMoney, int displayPrice) {
        this.change = handedMoney - displayPrice;
        this.displayPrice = displayPrice;
        this.days = days;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getChange() { return change; }

    public TwoDayTicket getTwoDayTicket() {
        return new TwoDayTicket(displayPrice);
    }

    public FourDayTicket getFourDayTicket() {
        return new FourDayTicket(displayPrice);
    }

    public Ticket getTicket() { return new DaysTicket(this.days, this.displayPrice);}
}
