package com.areaofthoughts.grumpycat;

/**
 *
 * @author MackSix
 */
public class MainController {
    MainViewPanel mainView;
    MainModel mainModel;
    
    public MainController(MainViewPanel mainView, MainModel mainModel) {
        this.mainView = mainView;
        this.mainModel = mainModel;
        setMainController();
    }
    
    private void setMainController() {
        mainView.setMainController(this);
        mainModel.setMainController(this);
    }
    
    public String getMainViewImagePath() {
        return mainModel.mainViewImagePath;
    }
    
    public String getMainViewImageIcon() {
        return mainModel.mainViewImageIcon;
    }
    
    public String getGrumpySaysPath() {
        return mainModel.getGrumpySaying();
    }
}
