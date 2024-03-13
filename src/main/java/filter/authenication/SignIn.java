package filter.authenication;

import config.ConfigPage;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebFilter(filterName = "SignIn", servletNames = {"signIn"}, urlPatterns = {"/signIn"})
public class SignIn implements Filter {
    private Map<String, Integer> signInAttempts;
    private int limit = 5; // Max allowed sign-in attempts
    private int duration = 60 * 5; // Time window in seconds
    private ScheduledExecutorService executor;
    private final String MESSAGE_SPAM ="Vui lòng thử lại sau 5 phút!";

    public void init(FilterConfig config) throws ServletException {
        signInAttempts = new ConcurrentHashMap<>();
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::clearSignInAttempts, duration, duration, TimeUnit.SECONDS);
    }

    public void destroy() {
        executor.shutdown();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String ipAddress = httpRequest.getRemoteAddr();
        int attempts = signInAttempts.getOrDefault(ipAddress, 0);
        if (attempts >= limit) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpRequest.setAttribute("spam", MESSAGE_SPAM);
            httpRequest.getRequestDispatcher(ConfigPage.SIGN_IN).forward(request, response);
            return;
        }
        signInAttempts.put(ipAddress, attempts + 1);
        chain.doFilter(request, response);
    }

    private void clearSignInAttempts() {
        signInAttempts.clear();
    }
}