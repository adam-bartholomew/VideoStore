/*
 * SimpleDateTest.java
 * JUnit based test
 *
 * Created on September 21, 2007, 3:39 PM
 */

package videostore.model;

import junit.framework.*;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

/**
 *
 * @author bjork
 */
public class SimpleDateTest extends TestCase {
    
    public SimpleDateTest(String testName) {
        super(testName);
        formatter = DateFormat.getDateInstance(DateFormat.SHORT);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getToday method, of class videostore.util.SimpleDate.
     */
    public void testGetToday() throws Exception {
        SimpleDate.setToday(formatter.parse(TEST_DATE));
        assertEquals(TEST_DATE, SimpleDate.getToday().toString());
    }

    /**
     * Test of changeTodayBy method, of class videostore.util.SimpleDate.
     */
    public void testChangeTodayBy() throws Exception {
        SimpleDate.setToday(formatter.parse(TEST_DATE));
        SimpleDate.changeTodayBy(7);
        assertEquals(NEXT_WEEK, SimpleDate.getToday().toString());
    }

    // Since toString is tested by the above, no additional tests are provided

    /**
     * Test of toStringMMDDYY method, of class videostore.util.SimpleDate.
     */
    public void testToStringYYMMDD() throws Exception {
         SimpleDate.setToday(formatter.parse(TEST_DATE));
         assertEquals(TEST_DATE_YYMMDD, SimpleDate.getToday().toStringYYMMDD());
    }

    /** Test of toDate method, of class videostore.util.SimpleDate
     *
     */
    public void testToDate() throws Exception {
        SimpleDate.setToday(formatter.parse(TEST_DATE));
        assertEquals(new Date(TEST_DATE), SimpleDate.getToday().toDate());
    }
    
    /** Test of constructor from a java.util.Date object
     *
     */
    public void testSimpleDate() throws Exception {
        Date testDate = new Date(TEST_DATE);
        assertEquals(formatter.format(testDate),
                     new SimpleDate(testDate).toString());
    }
    
    /**
     * Test of equals method, of class videostore.util.SimpleDate.
     */
    public void testEquals() throws Exception {
        SimpleDate today = SimpleDate.getToday();
        assertTrue(today.equals(SimpleDate.getToday()));
        SimpleDate.changeTodayBy(1);
        assertFalse(today.equals(SimpleDate.getToday()));
    }

    /**
     * Test of isAfter method, of class videostore.util.SimpleDate.
     */
    public void testIsAfter() throws Exception {
        SimpleDate today = SimpleDate.getToday();
        assertFalse(today.isAfter(SimpleDate.getToday()));
        SimpleDate.changeTodayBy(1);
        assertFalse(today.isAfter(SimpleDate.getToday()));
        assertTrue(SimpleDate.getToday().isAfter(today));
    }

    /**
     * Test of daysLater method, of class videostore.util.SimpleDate.
     */
    public void testDaysLater() {
        SimpleDate threeDaysFromNow = SimpleDate.getToday().daysLater(3);
        SimpleDate.changeTodayBy(3);
        assertEquals(SimpleDate.getToday(), threeDaysFromNow);
    }

    /**
     * Test of daysAfter method, of class videostore.util.SimpleDate.
     */
    public void testDaysAfter() {
        SimpleDate today = SimpleDate.getToday();
        SimpleDate.changeTodayBy(3);
        assertEquals(0, today.daysAfter(today));
        assertEquals(3, SimpleDate.getToday().daysAfter(today));
        assertEquals(-3, today.daysAfter(SimpleDate.getToday()));
    }

    
    private static final String TEST_DATE = "9/1/07";
    private static final String TEST_DATE_YYMMDD = "07/09/01";
    private static final String NEXT_WEEK = "9/8/07";
    private DateFormat formatter;
}
