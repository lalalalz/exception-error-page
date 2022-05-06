package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class ErrorPageController {

    @GetMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        String uuid = (String) request.getAttribute("logId");
        log.info("error page 404 [{}]", uuid);
        return "error-page/404";
    }

    @GetMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        log.info("error page 500");
        return "error-page/500";
    }

    private void printErrorInfo(HttpServletRequest request) {
        log.info("ERROR_EXEPTION: {}", request.getAttribute("javax.servlet.error.exception"));
        log.info("ERROR_EXEPTION_TYPE: {}", request.getAttribute("javax.servlet.error.exception_type"));
        log.info("ERROR_MESSAGE: {}", request.getAttribute("javax.servlet.error.message"));
        log.info("ERROR_REQUEST_URI: {}", request.getAttribute("javax.servlet.error.request_uri"));
        log.info("ERROR_SERVLET_NAME: {}", request.getAttribute("javax.servlet.error.servlet_name"));
        log.info("ERROR_STATUS_CODE: {}", request.getAttribute("javax.servlet.error.status_code"));
    }
}
