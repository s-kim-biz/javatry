package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tiger extends Animal implements Tetrapod {

    private static final Logger logger = LoggerFactory.getLogger(Tiger.class);

    protected String getBarkWord() { return "kuoooooo"; }

    @Override
    protected void downHitPoint() {
        --hitPoint;
        logger.debug("be tired...");
        if (hitPoint == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }

    public void howToLook() {
        logger.debug("so feared");
    }

    public void walkByFourLegs() {
        logger.debug("so so fast");
    }
}
