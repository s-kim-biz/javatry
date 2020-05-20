/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

// TODO kim 不要な import 文は消しましょう by subaru (2020/04/23)

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done kim MAX_QUANTITYはoneDayPassportやtwoDayPassportを共通しているのでしょうか？現実的にどんユースケースでしょうか？ by winkichanwi
    // チケットの種類が一つでOnedayPassportかTwodayPassportかによって渡す量が変わってくるだけだと思ったので量は共通のものを使いました
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // done kim buyOneDayPassport と buyOneDayPassportTicket もう少し共通化してみましょう by subaru (2020/04/23)
    // この二つは処理としてはほとんど同じで、戻り値だけ違うということだと思うので、共通化できます。
    public void buyOneDayPassport(int handedMoney) {
        isPossibleToBuyPassport(quantity, handedMoney, ONE_DAY_PRICE);
        --quantity;
        addSalesProceeds(ONE_DAY_PRICE);
    }

    public OneDayTicket buyOneDayPassportTicket(int handedMoney) {
        buyOneDayPassport(handedMoney);
        return new OneDayTicket(ONE_DAY_PRICE);
    }

    public TwoDayTicket buyTwoDayPassportTicket(int handedMoney) {
        buyTwoDayPassport(handedMoney);
        return new TwoDayTicket(TWO_DAY_PRICE);
    }

    public Integer buyTwoDayPassport(int handedMoney) {
        isPossibleToBuyPassport(quantity, handedMoney, TWO_DAY_PRICE);
        quantity -= 2;
        addSalesProceeds(TWO_DAY_PRICE);
        return handedMoney - TWO_DAY_PRICE;
    }

    public TicketBuyResult buyTwoDayPassportResult(int handedMoney) {
        isPossibleToBuyPassport(quantity, handedMoney, TWO_DAY_PRICE);
        quantity -= 2;
        return new TicketBuyResult(handedMoney, TWO_DAY_PRICE);
    }

    // [done] kim 宿題です、こちらもTicketBuyResultを返すように修正してみましょう by winkichanwi
    public TicketBuyResult buyFourDayPassportTicket(int handedMoney) {
        isPossibleToBuyPassport(quantity, handedMoney, FOUR_DAY_PRICE);
        quantity -= 4;
        addSalesProceeds(FOUR_DAY_PRICE);
        return new TicketBuyResult(handedMoney, FOUR_DAY_PRICE);
    }

    // TODO kim isXxx()メソッドは、慣習としてbooleanを戻す意味が強いので、例外をthrowするのであれば... by jflute (2020/05/20)
    // assertPossibleToBuyPassport() とかの方が良いです。
    private void isPossibleToBuyPassport(int quantity, int handedMoney, int price) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    private void addSalesProceeds(int price) {
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }

    public static String getTicketInfo(Ticket t) {
        if (t.getDisplayPrice() == ONE_DAY_PRICE) {
            return "one-day price ticket";
        } else if (t.getDisplayPrice() == TWO_DAY_PRICE) {
            return "two-days price ticket";
        } else {
            return "uncertain ticket";
        }
    }
}
