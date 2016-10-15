package app.bunchoffools.codenicely.bunchoffools.contact_us.model.data;

/**
 * Created by meghal on 15/10/16.
 */

public class ContactUsData {


    private boolean success;
    private String message;
    private String email;
    private String mobile;
    private String address;
    private String fb;
    private String website;
    private String image;

    public ContactUsData(boolean success, String message, String email, String mobile, String address, String fb, String website, String image) {
        this.success = success;
        this.message = message;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.fb = fb;
        this.website = website;
        this.image = image;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getFb() {
        return fb;
    }

    public String getWebsite() {
        return website;
    }

    public String getImage() {
        return image;
    }
}
