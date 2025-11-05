package udistrital.uitestswing;

import static org.junit.Assume.assumeFalse;

import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.BeforeClass;
import org.junit.Test;

public class UITestSwingTest extends AssertJSwingJUnitTestCase {

    private FrameFixture window;

    @BeforeClass
    public static void verifyEnvironment() {
        assumeFalse("Skipping Swing UI tests because the environment is headless", GraphicsEnvironment.isHeadless());
    }

    @Override
    protected void onSetUp() {
        JFrame frame = GuiActionRunner.execute(UITestSwing::createLoginFrame);
        window = new FrameFixture(robot(), frame);
        window.show();
    }

    @Override
    protected void onTearDown() {
        if (window != null) {
            window.cleanUp();
        }
    }

    @Test
    public void shouldShowSuccessMessageWhenCredentialsAreValid() {
        window.textBox("usernameField").enterText("admin");
        window.textBox("passwordField").enterText("password");
        window.button("loginButton").click();

        window.label("messageLabel").requireText("Login successful!");
    }

    @Test
    public void shouldShowErrorMessageWhenCredentialsAreInvalid() {
        window.textBox("usernameField").enterText("user");
        window.textBox("passwordField").enterText("secret");
        window.button("loginButton").click();

        window.label("messageLabel").requireText("Invalid username or password.");
    }
}
