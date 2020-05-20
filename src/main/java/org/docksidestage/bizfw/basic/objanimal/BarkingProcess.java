package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author s.kim
 */
public class BarkingProcess {

    private Animal animal = null;
    // ===================================================================================
    //                                                                         Consturctor
    //
    public BarkingProcess(Animal animal){
        this.animal = animal;
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        animal.breatheIn();
        animal.prepareAbdominalMuscle();
        String barkWord = animal.getBarkWord();
        BarkedSound barkedSound = animal.doBark(barkWord);
        return barkedSound;
    }
}
