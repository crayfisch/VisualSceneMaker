package de.dfki.vsm.util.stickman;

import de.dfki.common.CommonAnimation;
import de.dfki.common.CommonStickman;
import de.dfki.common.StageStickmanController;
import de.dfki.stickmanfx.animationlogic.AnimationLoaderFX;
import de.dfki.stickmanfx.stagecontroller.StageStickmanControllerFX;
import de.dfki.stickmanfx.stagecontroller.decorators.StageStickmanFullScreenControllerFXDecorator;
import de.dfki.stickmanfx.stagecontroller.decorators.StageStickmanNetworkControllerDecoratorFX;
import de.dfki.vsm.model.project.PluginConfig;

/**
 * Created by alvaro on 9/19/16.
 */
public class StickmanFxFactory extends StickmanAbstractFactory {

    public StickmanFxFactory(PluginConfig config) {
        super(config);
    }

    @Override
    protected StageStickmanController getStickman() {
        final String host = config.getProperty("smhost");
        final String port = config.getProperty("smport");

        createInitialStageController();

        if (config.containsKey("fullscreen") && config.getProperty("fullscreen").equalsIgnoreCase(Boolean.TRUE.toString())) {
            stickmanStageC = new StageStickmanFullScreenControllerFXDecorator(stickmanStageC);
        }
        if(host !=null && port !=null && !host.equals("") && !port.equals("")){
            stickmanStageC =  new StageStickmanNetworkControllerDecoratorFX(stickmanStageC, host, Integer.parseInt(port)) ;
        }
        return stickmanStageC;
    }

    private void createInitialStageController() {
        final String xPos = config.getProperty("xStage");
        final String yPos = config.getProperty("yStage");

        if (xPos !=null && yPos !=null) {
            try {
                stickmanStageC = new StageStickmanControllerFX(Integer.parseInt(xPos), Integer.parseInt(yPos));
            }catch (Exception e){
                stickmanStageC = new StageStickmanControllerFX();
            }

        }else{
            stickmanStageC = new StageStickmanControllerFX();
        }
    }

    @Override
    public CommonAnimation getAnimation(String actor) {
        return (CommonAnimation) AnimationLoaderFX.getInstance();
    }

    @Override
    public CommonAnimation loadEventAnimation(CommonStickman sm, String name, int duration, boolean block) {
        return AnimationLoaderFX.getInstance().loadEventAnimation(sm, name, duration, false);
    }

    @Override
    public CommonAnimation loadAnimation(CommonStickman sm, String name, int duration, boolean block) {
        return AnimationLoaderFX.getInstance().loadAnimation(sm, name, duration, false); // TODO: with regard to get a "good" timing, consult the gesticon
    }
}