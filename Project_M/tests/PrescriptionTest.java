import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class PrescriptionTest {

    // Test for valid prescriptions
    @Test
    public void testAddPrescriptionValid() {
        // Two valid prescriptions
        Prescription presc1 = new Prescription(1, "John", "Smith", "123 Main St, Suburb, Country 12345", 1.5f, 90.0f, -1.0f, new Date(), "Dr. Andrew Smith");
        Prescription presc2 = new Prescription(2, "Alice", "Brown", "456 Another St, Suburb, Some Country 67890", -2.0f, 100.0f, 3.0f, new Date(), "Dr. Samantha Brown");

        assertTrue(presc1.addPrescription());
        assertTrue(presc2.addPrescription());
    }

    // Test for invalid names (too short)
    @Test
    public void testAddPrescriptionInvalidName() {
        // Invalid first name (less than 4 characters)
        Prescription presc1 = new Prescription(3, "Jo", "Doe", "123 Main St, Suburb, Country 12345", 1.5f, 90.0f, -1.0f, new Date(), "Dr. Andrew Smith");
        // Invalid last name (less than 4 characters)
        Prescription presc2 = new Prescription(4, "John", "Li", "456 Another St, Suburb, Country 67890", -2.0f, 100.0f, 0.0f, new Date(), "Dr. Samantha Green");

        assertFalse(presc1.addPrescription());
        assertFalse(presc2.addPrescription());
    }

    // Test for invalid address (too short)
    @Test
    public void testAddPrescriptionInvalidAddress() {
        // Invalid address (less than 20 characters)
        Prescription presc1 = new Prescription(5, "John", "Doe", "Short address", 1.5f, 90.0f, -1.0f, new Date(), "Dr. Andrew Smith");
        // Invalid address (missing country/postcode)
        Prescription presc2 = new Prescription(6, "Alice", "Brown", "123 Main", 1.5f, 80.0f, -2.0f, new Date(), "Dr. Samantha Green");

        assertFalse(presc1.addPrescription());
        assertFalse(presc2.addPrescription());
    }

    // Test for invalid sphere value (out of range)
    @Test
    public void testAddPrescriptionInvalidSphere() {
        // Sphere below -20.00
        Prescription presc1 = new Prescription(7, "John", "Doe", "123 Main St, Suburb, Country 12345", -21.0f, 90.0f, -1.0f, new Date(), "Dr. Andrew Smith");
        // Sphere above +20.00
        Prescription presc2 = new Prescription(8, "Alice", "Brown", "456 Another St, Suburb, Country 67890", 21.0f, 100.0f, 0.0f, new Date(), "Dr. Samantha Green");

        assertFalse(presc1.addPrescription());
        assertFalse(presc2.addPrescription());
    }

    // Test for invalid cylinder value (out of range)
    @Test
    public void testAddPrescriptionInvalidCylinder() {
        // Cylinder below -4.00
        Prescription presc1 = new Prescription(9, "John", "Doe", "123 Main St, Suburb, Country 12345", -1.0f, 90.0f, -5.0f, new Date(), "Dr. Andrew Smith");
        // Cylinder above +4.00
        Prescription presc2 = new Prescription(10, "Alice", "Brown", "456 Another St, Suburb, Country 67890", 1.0f, 80.0f, 5.0f, new Date(), "Dr. Samantha Green");

        assertFalse(presc1.addPrescription());
        assertFalse(presc2.addPrescription());
    }

    // Test for invalid optometrist name (too short or too long)
    @Test
    public void testAddPrescriptionInvalidOptometrist() {
        // Optometrist name shorter than 8 characters
        Prescription presc1 = new Prescription(11, "John", "Doe", "123 Main St, Suburb, Country 12345", -1.0f, 90.0f, 0.0f, new Date(), "Dr. Li");
        // Optometrist name longer than 25 characters
        Prescription presc2 = new Prescription(12, "Alice", "Brown", "456 Another St, Suburb, Country 67890", -2.0f, 100.0f, 1.0f, new Date(), "Dr. Elizabeth Alexandra Henderson");

        assertFalse(presc1.addPrescription());
        assertFalse(presc2.addPrescription());
    }
}
