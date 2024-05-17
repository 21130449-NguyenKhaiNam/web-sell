package services.mail;

import models.shoppingCart.AbstractCartProduct;
import models.DeliveryInfo;
import models.shoppingCart.ShoppingCart;
import properties.MailProperties;
import utils.FormatCurrency;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MailPlaceOrderService implements IMailServices{

    private ShoppingCart cart;

    private String dateOrder;

    private int invoiceNo;

    public MailPlaceOrderService(ShoppingCart cart, String dateOrder, int invoiceNo) {
        this.cart = cart;
        this.dateOrder = dateOrder;
        this.invoiceNo = invoiceNo;
    }

    @Override
    public void send() throws MessagingException {
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailProperties.getEmail(), MailProperties.getPassword());
            }
        };

//        DeliveryInfo deliveryInfoBuyer = cart.getDeliveryInfo();
//        String fullNameBuyer = deliveryInfoBuyer.getFullName();
//        String emailBuyer = deliveryInfoBuyer.getEmail();
//        String phoneBuyer = deliveryInfoBuyer.getPhone();
//        String addressBuyer = deliveryInfoBuyer.getAddress();
//        String temporaryPriceFormat = cart.temporaryPriceFormat();
//        String discountPriceFormat = cart.discountPriceFormat();
        double shippingFee = 0;
        String deliveryMethod = "";
//        if(cart.getDeliveryMethod() != null){
//            shippingFee = cart.getDeliveryMethod().getShippingFee();
//            deliveryMethod = cart.getDeliveryMethod().getTypeShipping();
//        }

//        String paymentMethod = cart.getPaymentMethod().getTypePayment();
        String totalPriceFormat;
//        if(cart.getDeliveryMethod() != null){
//            totalPriceFormat = cart.totalPriceFormat(true);
//        }else{
//            totalPriceFormat = cart.totalPriceFormat(false);
//        }
        int totalItems = cart.getTotalItems();

        Session session = Session.getInstance(MailProperties.getProperties(), auth);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MailProperties.getEmail()));
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailBuyer, true));
        String subjectMess = "ĐƠN HÀNG CỦA BẠN";
        message.setSubject(subjectMess);
        message.setReplyTo(null);

        List<AbstractCartProduct> listCartProduct = new ArrayList<>();
        StringBuilder itemsBoughtBuilder = new StringBuilder();
        for (Integer productId: cart.getShoppingCartMap().keySet()) {
            for (AbstractCartProduct cartProduct: cart.getShoppingCartMap().get(productId)) {
                listCartProduct.add(cartProduct);
                String trTagOpen;
                if((listCartProduct.indexOf(cartProduct) + 1) % 2 == 0){
                    trTagOpen = "<tr style='background-color: #d9eef8;'>";
                }else{
                    trTagOpen = "<tr>";
                }
                itemsBoughtBuilder.append(trTagOpen).append("<td style='border: 1px solid #d9d9d9;text-align: center;vertical-align: middle; padding: 10px;'><div class='item__info' style='display: grid; text-align: left;'><strong>").append(cartProduct.getProduct().getName()).append("</strong>")
                        .append("<span class='color' style='font-size: 14px;'>Màu sắc: ").append(cartProduct.getColor().getCodeColor()).append("</span>")
                        .append("<span class='size' style='font-size: 14px;'>Kích thước: ").append(cartProduct.sizeRequired()).append("</span></div></td>")
                        .append("<td style='border: 1px solid #d9d9d9;text-align: center;vertical-align: middle; padding: 10px;'>").append(cartProduct.sewingPriceFormat()).append("</td><td style='border: 1px solid #d9d9d9;text-align: center;vertical-align: middle; padding: 10px;'>").append(cartProduct.getQuantity())
                        .append("</td><td style='border: 1px solid #d9d9d9;text-align: center;vertical-align: middle; padding: 10px;'>").append(cartProduct.subtotalFormat()).append("</td></tr>");
            }
        }

        InputStream inputStream = MailProperties.class.getClassLoader().getResourceAsStream("templates/templateEmailPlaceOrder.html");
        StringBuilder contentBuilder  = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        while (true){
            try {
                if((line = bufferedReader.readLine()) != null){
                    contentBuilder.append(line);
                }else {
                    break;
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

        String htmlContent = contentBuilder.toString();
        htmlContent = htmlContent.replace("%%ITEMSBOUGHT%%", itemsBoughtBuilder.toString());
        htmlContent = htmlContent.replaceAll("%%INVOICENO%%", String.valueOf(invoiceNo));
        htmlContent = htmlContent.replaceAll("%%INVOICEDATE%%", dateOrder);
//        htmlContent = htmlContent.replaceAll("%%FULLNAME%%", fullNameBuyer);
//        htmlContent = htmlContent.replace("%%EMAIL%%", emailBuyer);
//        htmlContent = htmlContent.replace("%%PHONE%%", phoneBuyer);
//        htmlContent = htmlContent.replace("%%ADDRESS%%", addressBuyer);
//        htmlContent = htmlContent.replace("%%TOTALITEM%%", String.valueOf(totalItems));
//        htmlContent = htmlContent.replace("%%TOTALPRICE%%", totalPriceFormat);
//        htmlContent = htmlContent.replace("%%TEMPPRICE%%", temporaryPriceFormat);
//        htmlContent = htmlContent.replace("%%DISCOUNTPRICE%%", discountPriceFormat);
//        htmlContent = htmlContent.replace("%%SHIPPINGFEE%%", FormatCurrency.vietNamCurrency(shippingFee));
        htmlContent = htmlContent.replace("%%DELIVERYMETHOD%%", deliveryMethod);
//        htmlContent = htmlContent.replace("%%PAYMENTMETHOD%%", paymentMethod);

        message.setContent(htmlContent, "text/html; charset = UTF-8");
        Transport.send(message);
    }


//    private String htmlTemplateInvoice(InputStream inputStream){
//
//    }
}
