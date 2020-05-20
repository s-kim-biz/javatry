package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;
    private final int change;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(int handedMoney, int displayPrice) {
        this.change = handedMoney - displayPrice;
        this.displayPrice = displayPrice;
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
}
