package com.areaofthoughts.grumpycat;

import java.util.Random;

/**
 *
 * @author MackSix
 */
public class MainModel {

    MainController mainController;
    public final String mainViewImagePath = "/images/grumpy cat.png";
    public final String mainViewImageIcon = "/images/grumpy cat 32x32.png";
    private final String quietLike = "/images/quiet i like.png";
    private final String stillHere = "/images/you still here.png";
    private final String goAway = "/images/go away.png";
    private final String iLikeNo = "/images/i like no.png";
    private final String niceNowLeave = "/images/nice now leave.png";
    private final String niceMakeup = "/images/nice makeup.png";
    private final String dontLikeYou = "/images/i don't like you.png";
    private final int MAX_SAYINGS = 7;
    private Random randomObj;
    
    {
        randomObj = new Random();
    }
    
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public String getGrumpySaying() {
        int temp = randomObj.nextInt(MAX_SAYINGS);
        String grumpySays;
        switch(temp) {
            case 1:  grumpySays = quietLike;
                     break;
            case 2:  grumpySays = stillHere;
                     break;
            case 3:  grumpySays = goAway;
                     break;
            case 4:  grumpySays = iLikeNo;
                     break;
            case 5:  grumpySays = niceNowLeave;
                     break;
            case 6:  grumpySays = niceMakeup;
                     break;
            case 7:  grumpySays = dontLikeYou;
                     break;
            default: grumpySays = goAway;
         }
        
        return grumpySays;
    }
}
