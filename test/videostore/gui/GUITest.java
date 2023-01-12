/*
 * GUITest.java
 * JUnit based test
 *
 * Created on October 8, 2007, 6:09 PM
 */

package videostore.gui;

import junit.framework.*;
import org.junit.*;
import videostore.controller.Main;

/**
 *
 * @author bjork
 */
public class GUITest extends TestCase {
    
    public GUITest(String testName) {
        super(testName);
    }
    
    // getInstance() is tested by the other tests below
    
    // There are no good ways to unit test gotoCard(), and goBack();
    
    /**
     * Test of showMessage method, of class videostore.gui.GUI.
     */
    public void testShowMessage() {
        GUI.getInstance().showMessage("Click OK if this is OK");
    }

    /**
     * Test of askQuestion method, of class videostore.gui.GUI.
     */
    public void testAskQuestion() {
        String answer = GUI.getInstance().askQuestion("Please answer FOO");
        assertTrue(answer.equals("FOO"));
        answer = GUI.getInstance().askQuestion("Please Cancel without answering");
        assertTrue(answer == null);
        answer = GUI.getInstance().askQuestion("Please close without answering");
        assertTrue(answer == null);
    }

    /**
     * Test of askYesNoQuestion method, of class videostore.gui.GUI.
     */
    public void testAskYesNoQuestion() {
        boolean answer = GUI.getInstance().askYesNoQuestion("Please answer YES");
        assertTrue(answer);
        answer = GUI.getInstance().askYesNoQuestion("Please answer NO");
        assertFalse(answer);
        answer = GUI.getInstance().askYesNoQuestion("Please close without answering");
        assertFalse(answer);
    }
    
}
