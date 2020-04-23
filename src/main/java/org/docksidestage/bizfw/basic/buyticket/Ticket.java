package org.docksidestage.bizfw.basic.buyticket;

public interface Ticket {

    void doInPark();

    int getDisplayPrice();

    boolean isAlreadyIn();
}
