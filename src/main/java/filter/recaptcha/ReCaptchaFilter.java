package filter.recaptcha;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/signIn", "/signUp", "/public/auth/signIn.jsp"})
public class ReCaptchaFilter implements Filter {
    private static final String SECRET_KEY = "6LdSQu4pAAAAAP2wrQoDMwNVcga07MxOl9VN4D29";
    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final double SCORE_PASSED = 0.5;
    private static final int MAX_COUNT_ERROR = 3;
    private static Map<String, Integer> managerIp;

    static {
        managerIp = new HashMap<>();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String gRecaptchaResponse = servletRequest.getParameter("g-recaptcha-response");
        String remoteIp = servletRequest.getRemoteAddr();
        String errorReCaptcha = servletRequest.getParameter("errorReCaptcha");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (gRecaptchaResponse != null) {
            boolean isVerify = verifyCaptcha(gRecaptchaResponse, remoteIp);
            if (!managerIp.containsKey(remoteIp)) {
                managerIp.put(remoteIp, 0);
            }
            if (!isVerify) {
                if (managerIp.get(remoteIp) > MAX_COUNT_ERROR) {
                    servletRequest.setAttribute("errorReCaptcha", "true");
                }
                servletRequest.setAttribute("managerIp", managerIp);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean verifyCaptcha(String gRecaptchaResponse, String remoteIp) throws IOException {
        try {
            String response = Request.Post(RECAPTCHA_VERIFY_URL)
                    .bodyForm(Form.form()
                            .add("secret", SECRET_KEY)
                            .add("response", gRecaptchaResponse)
                            .add("remoteip", remoteIp)
                            .build())
                    .execute().returnContent().asString();
            JSONObject json = new JSONObject(response);
            ReCaptcha reCaptcha = ReCaptcha.builder()
                    .action(json.getString("action"))
                    .score(json.getDouble("score"))
                    .success(json.getBoolean("success"))
                    .challengeTs(json.getString("challenge_ts"))
                    .hostname(json.getString("hostname"))
                    .build();
//            JSONArray errorCodes = json.getJSONArray("error-codes");
//            String[] errorCodesArray = new String[errorCodes.length()];
//            for (int i = 0; i < errorCodes.length(); i++) {
//                errorCodesArray[i] = errorCodes.getString(i);
//            }
//            reCaptcha.setErrorCodes(errorCodesArray);
            if (!reCaptcha.isSuccess() || reCaptcha.getScore() < SCORE_PASSED) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}