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

import java.util.HashMap;
import java.util.Map;

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
    private static Map<Integer, Integer> dayAndPrice = new HashMap<Integer,Integer>(){{
        put(1 ,ONE_DAY_PRICE);
        put(2, TWO_DAY_PRICE);
        put(4, FOUR_DAY_PRICE);
    }};

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
    public void buyCertainDayPassport(int day, int handedMoney) {
        assertPossibleToBuyPassport(quantity, handedMoney, day);
        quantity -= day;
        addSalesProceeds(dayAndPrice.get(day));
    }

    public OneDayTicket buyOneDayPassportTicket(int handedMoney) {
        buyCertainDayPassport(1, handedMoney);
        return new OneDayTicket(dayAndPrice.get(1));
    }

    public Integer buyTwoDayPassport(int handedMoney) {
        buyCertainDayPassport(2, handedMoney);
        return handedMoney - dayAndPrice.get(2);
    }

    public TwoDayTicket buyTwoDayPassportTicket(int handedMoney) {
        buyCertainDayPassport(2, handedMoney);
        return new TwoDayTicket(dayAndPrice.get(2));
    }

    public TicketBuyResult buyTwoDayPassportResult(int handedMoney) {
        buyCertainDayPassport(2, handedMoney);
        return new TicketBuyResult(2, handedMoney, dayAndPrice.get(2));
    }

    // [done] kim 宿題です、こちらもTicketBuyResultを返すように修正してみましょう by winkichanwi
    public TicketBuyResult buyCertainDayPassportTicket(int days, int handedMoney) {
        buyCertainDayPassport(days, handedMoney);
        return new TicketBuyResult(days, handedMoney, dayAndPrice.get(days));
    }

    // TODO done kim isXxx()メソッドは、慣習としてbooleanを戻す意味が強いので、例外をthrowするのであれば... by jflute (2020/05/20)
    // assertPossibleToBuyPassport() とかの方が良いです。
    private void assertPossibleToBuyPassport(int quantity, int handedMoney, int day) {
        if (!dayAndPrice.containsKey(day)) {
            throw new TicketTypeNotExistedException("Ticket type of '" + day + "-day' does not exist");
        }
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < dayAndPrice.get(day)) {
            throw new TicketShortMoneyException("Short money: '" + handedMoney + "'");
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

    public static class TicketTypeNotExistedException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketTypeNotExistedException(String msg) {
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

}
