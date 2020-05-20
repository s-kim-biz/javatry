package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author s.kim
 */
public abstract class BarkingProcess {
    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected abstract void breatheIn();

    protected abstract String getBarkWord();

    protected abstract void prepareAbdominalMuscle();

    protected abstract BarkedSound doBark(String barkWord);
}
