package utils;

public class Validation {
    private String fieldUsername;
    private String fieldPassword;
    private String fieldConfirmPassword;
    private String fieldEmail;
    private Object objReturn;

    public Validation() {
    }

    public String getFieldUsername() {
        return fieldUsername;
    }

    public void setFieldUsername(String fieldUsername) {
        this.fieldUsername = fieldUsername;
    }

    public String getFieldPassword() {
        return fieldPassword;
    }

    public void setFieldPassword(String fieldPassword) {
        this.fieldPassword = fieldPassword;
    }

    public String getFieldEmail() {
        return fieldEmail;
    }

    public void setFieldEmail(String fieldEmail) {
        this.fieldEmail = fieldEmail;
    }

    public Object getObjReturn() {
        return objReturn;
    }

    public void setObjReturn(Object objReturn) {
        this.objReturn = objReturn;
    }

    public String getFieldConfirmPassword() {
        return fieldConfirmPassword;
    }

    public void setFieldConfirmPassword(String fieldConfirmPassword) {
        this.fieldConfirmPassword = fieldConfirmPassword;
    }
}
