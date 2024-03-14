package models;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeliveryInfoStorage {
    private HashMap<String, DeliveryInfo> deliveryInfoMap = new HashMap<>();

    public HashMap<String, DeliveryInfo> getDeliveryInfoMap() {
        return deliveryInfoMap;
    }

    public void setDeliveryInfoMap(HashMap<String, DeliveryInfo> deliveryInfoMap) {
        this.deliveryInfoMap = deliveryInfoMap;
    }

    public boolean add(String deliveryInfoKey, DeliveryInfo deliveryInfo){
        if(!deliveryInfoMap.containsKey(deliveryInfoKey)){
            if(!deliveryInfoMap.containsValue(deliveryInfo)){
                deliveryInfoMap.put(deliveryInfoKey, deliveryInfo);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean remove(String deliveryInfoKey){
        return deliveryInfoMap.remove(deliveryInfoKey) != null;
    }

    public boolean updateDeliveryInfo(String fullName, String email, String phone, String address , String deliveryInfoKey){
        if(deliveryInfoMap.containsKey(deliveryInfoKey)){
            DeliveryInfo deliveryInfoTemp = new DeliveryInfo(fullName, email, phone, address);
            DeliveryInfo deliveryInfoTarget = getDeliveryInfoByKey(deliveryInfoKey);
            if(!deliveryInfoMap.containsValue(deliveryInfoTemp) || deliveryInfoTarget.equals(deliveryInfoTemp)){
                deliveryInfoTarget.setFullName(fullName);
                deliveryInfoTarget.setEmail(email);
                deliveryInfoTarget.setPhone(phone);
                deliveryInfoTarget.setAddress(address);
                return true;
            }
        }
        return false;
    }

    public boolean checkAllValidationDeliveryInfo(JSONObject errorFields, String fullName, String email, String phone, String address){
        boolean isAllValid = true;
        if (fullName.isEmpty() || address.isEmpty()) {
            isAllValid = false;
            if (fullName.isEmpty()) {
                errorFields.put("fullNameError", "Vui lòng bạn nhập họ và tên");
            }

            if (address.isEmpty()) {
                errorFields.put("addressError", "Vui lòng bạn nhập địa chỉ giao hàng");
            }
        }

        if (email.isEmpty() || !isValidEmail(email)) {
            isAllValid = false;
            if (email.isEmpty()) {
                errorFields.put("emailError", "Vui lòng bạn nhập email");
            } else if (!isValidEmail(email)) {
                errorFields.put("emailError", "Vui lòng bạn nhập email hơp lệ (Cấu trúc email: tên_email@tên_miền)\nVí dụ: yourname@example.com");
            }
        }

        if (phone.isEmpty() || !isValidPhone(phone)) {
            isAllValid = false;
            if (phone.isEmpty()) {
                errorFields.put("phoneError", "Vui lòng bạn nhập số điện thoại");
            } else if (!isValidPhone(phone)) {
                errorFields.put("phoneError", "Vui lòng bạn nhập số điện thoại hợp lệ (Số điện thoại gồm 10 bắt đầu bằng số 0)");
            }
        }
        return isAllValid;
    }

    private boolean isValidEmail(String email) {
        Pattern patternEmail = Pattern.compile("^\\w+@\\w+\\.[A-Za-z]+$");
        Matcher matcherEmail = patternEmail.matcher(email);
        return matcherEmail.matches();
    }

    private boolean isValidPhone(String phone) {
        Pattern patternPhone = Pattern.compile("^\\+?(?:\\d\\s?){9,13}$");
        Matcher matcherPhone = patternPhone.matcher(phone);
        return matcherPhone.matches();
    }

    public DeliveryInfo getDeliveryInfoByKey(String key){
        return deliveryInfoMap.get(key);
    }


    public static void main(String[] args) {
        DeliveryInfoStorage deliveryInfoStorage = new DeliveryInfoStorage();
        DeliveryInfo deliveryInfo = new DeliveryInfo("A", "A", "A", "A");
        DeliveryInfo deliveryInfo1 = new DeliveryInfo("A", "A", "A", "B");
        DeliveryInfo deliveryInfo2 = new DeliveryInfo("A", "A", "A", "A");
        System.out.println(deliveryInfoStorage.add("1", deliveryInfo));
        System.out.println(deliveryInfoStorage.add("2", deliveryInfo1));
        System.out.println(deliveryInfoStorage.add("3", deliveryInfo2));
        System.out.println(deliveryInfoStorage.getDeliveryInfoMap());
    }
}
