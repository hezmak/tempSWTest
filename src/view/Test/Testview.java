package view.Test;

import view.MainController;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import view.Main;
import com.google.common.util.concurrent.SettableFuture;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;

import java.util.concurrent.TimeUnit;

import static org.junit.Assume.assumeTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;


//import sample.ClickApplication;

//import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class Testview extends GuiTest {
    private static final SettableFuture<Stage> stageFuture = SettableFuture.create();

    /**
     * The type Test program info window.
     */
    protected static class TestProgramInfoWindow extends Main {
        /**
         * Instantiates a new Test program info window.
         */
        public TestProgramInfoWindow() {
            super();
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            super.start(primaryStage);
            stageFuture.set(primaryStage);
        }
    }

    @Before
    @Override
    public void setupStage() throws Throwable {
        assumeTrue(!UserInputDetector.instance.hasDetectedUserInput());

        FXTestUtils.launchApp(Testview.TestProgramInfoWindow.class); // You can add start parameters here
        try {
            stage = targetWindow(stageFuture.get(25, TimeUnit.SECONDS));
            FXTestUtils.bringToFront(stage);
        } catch (Exception e) {
            throw new RuntimeException("Unable to show stage", e);
        }
    }

    @Override
    protected Parent getRootNode() {
        return stage.getScene().getRoot();
    }

    @Test
    //TODO
    public void testOpenTestFile(){
        String file1 = "firstfile.txt";
        String file2 = "secondfile.txt";

        System.out.println("왼쪽 파일을 선택한다");
        assertTrue(GuiTest.find("#btnLeftFileEdit").isDisable());
        assertTrue(GuiTest.find("#btnLeftFileSave").isDisable());
        assertTrue(GuiTest.find("#btnRightFileEdit").isDisable());
        assertTrue(GuiTest.find("#btnRightFileSave").isDisable());

        click("#btnLeftFileOpen");


        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
        type(file1).type(KeyCode.ENTER);
        //assertTrue(((TextField)GuiTest.find("#fieldLeftFile")).getText().equals("filename1"));

        System.out.println("오른쪽 파일을 선택한다");


        //click("#cancel_button");



    }
}
