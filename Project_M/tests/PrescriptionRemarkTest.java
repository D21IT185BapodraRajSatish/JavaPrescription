import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class PrescriptionRemarkTest {

    // Test for valid remarks
    @Test
    public void testAddRemarkValid() {
        Prescription presc = new Prescription(1, "John", "Doe", "123 Main St, Suburb, Country 12345", 1.5f, 90.0f, -1.0f, new Date(), "Dr. Andrew Smith");

        // Two valid remarks (both have at least 6 words)
        assertTrue(presc.addRemark("Client", "The patient needs a follow-up in three months."));
        assertTrue(presc.addRemark("Optometrist", "It is recommended to consider a stronger prescription next time."));
    }

    // Test for invalid remark type
    @Test
    public void testAddRemarkInvalidType() {
        Prescription presc = new Prescription(2, "Alice", "Brown", "456 Another St, Suburb, Country 67890", -1.0f, 120.0f, 2.0f, new Date(), "Dr. Samantha Green");

        // Invalid remark type (not "Client" or "Optometrist")
        assertFalse(presc.addRemark("UnknownType", "Follow-up scheduled in three months."));
        assertFalse(presc.addRemark("InvalidType", "Consider stronger prescription next time."));
    }

    // Test for too few words in remark
    @Test
    public void testAddRemarkTooFewWords() {
        Prescription presc = new Prescription(3, "John", "Doe", "123 Main St, Suburb, Country 12345", 1.5f, 90.0f, -1.0f, new Date(), "Dr. Andrew Smith");

        // Remarks with fewer than 6 words
        assertFalse(presc.addRemark("Client", "Check prescription again."));
        assertFalse(presc.addRemark("Optometrist", "Return for check."));
    }

    // Test for too many words in remark
    @Test
    public void testAddRemarkTooManyWords() {
        Prescription presc = new Prescription(4, "Alice", "Brown", "456 Another St, Suburb, Country 67890", -1.0f, 120.0f, 2.0f, new Date(), "Dr. Samantha Green");

        // Remarks with more than 20 words
        assertFalse(presc.addRemark("Client", "This is a really long remark that exceeds the maximum allowed word limit which is twenty words exactly and it keeps going on and on."));
        assertFalse(presc.addRemark("Optometrist", "This remark is also too long and exceeds the word limit of twenty words which should not be allowed by the system at all."));
    }




    // Test for first word not capitalized
    @Test
    public void testAddRemarkLowercaseFirstLetter() {
        Prescription presc = new Prescription(5, "John", "Doe", "123 Main St, Suburb, Country 12345", 1.5f, 90.0f, -1.0f, new Date(), "Dr. Andrew Smith");

        // First word does not start with a capital letter
        assertFalse(presc.addRemark("Client", "follow-up scheduled in three months."));
        assertFalse(presc.addRemark("Optometrist", "next appointment in four months."));
    }

    // Test for more than 2 remarks
    @Test
    public void testAddRemarkMaxTwoRemarks() {
        Prescription presc = new Prescription(6, "Alice", "Brown", "456 Another St, Suburb, Country 67890", -1.0f, 120.0f, 2.0f, new Date(), "Dr. Samantha Green");

        // Adding 3rd remark (should not be allowed)
        assertTrue(presc.addRemark("Client", "The patient needs a follow-up in three months."));
        assertTrue(presc.addRemark("Optometrist", "It is recommended to consider a stronger prescription next time."));
        assertFalse(presc.addRemark("Client", "Cannot add a third remark."));
    }

}
