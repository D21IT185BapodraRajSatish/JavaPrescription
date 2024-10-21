import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    // Constructor to initialize prescription details
    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float axis, float cylinder, Date examinationDate, String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }


    // Method to add prescription
    public boolean addPrescription() {
         //Condition 1: First name and last name should each have 4-15 characters
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }

        // Condition 2: Address must have a minimum of 20 characters
        if (address.length() < 20) {
            return false;
        }

        // Condition 3: Sphere should be between -20.00 and +20.00, Cylinder between -4.00 and +4.00, Axis between 0 and 180
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false;
        }

        // Condition 4: Optometrist name must be between 8 and 25 characters
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // If all conditions are met, write prescription details to a TXT file
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Name: " + firstName + " " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Examination Date: " + examinationDate + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("---------------------------------\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add a remark to the prescription
    public boolean addRemark(String remarkType, String remark) {
        // Condition 1: Check if the remark type is valid
        boolean isValidRemarkType = false;
        for (String type : remarkTypes) {
            if (type.equalsIgnoreCase(remarkType)) {
                isValidRemarkType = true;
                break;
            }
        }
        if (!isValidRemarkType) {
            return false;
        }

        // Condition 2: Check if there are already 2 remarks for the prescription
        if (postRemarks.size() >= 2) {
            return false;
        }

        // Condition 3: Check if the remark has between 6 and 20 words
        String[] words = remark.trim().split("\\s+");
        if (words.length < 6 || words.length > 20) {
            return false;
        }

        // Condition 4: Check if the first letter of the first word is uppercase
        if (!Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // If all conditions are met, write the remark to the file and store in the list
        try (FileWriter writer = new FileWriter("remark.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Remark Type: " + remarkType + "\n");
            writer.write("Remark: " + remark + "\n");
            writer.write("---------------------------------\n");

            postRemarks.add(remark); // Add remark to in-memory list
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
