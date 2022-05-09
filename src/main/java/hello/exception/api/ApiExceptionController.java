package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {

        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello" + id);
    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatusEx1() {
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStatusEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @GetMapping("/api/default-handler-ex")
    public String defaultException(@RequestParam Integer data) {
        return "ok";
    }

//    @ExceptionHandler({IllegalArgumentException.class, BadRequestException.class})
//    public ErrorResult multiEx(Exception e) {
//        log.info("exception e", e);
//        return new ErrorResult("BAD", "Multi Ex : " + e.getClass());
//    }
//
//    @ExceptionHandler
//    public ErrorResult userExHandle(UserException e) {
//        log.info("exception e", e);
//        return new ErrorResult("BAD", e.getMessage());
//    }

//    @ExceptionHandler(UserException.class)
//    public ModelAndView userExViewHandle(UserException e) {
//        return new ModelAndView("error/500");
//    }

//    @ExceptionHandler(UserException.class)
//    public ResponseEntity<ErrorResult> userExHandle(UserException e) {
//        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
//        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public String userExHandle(UserException e) {
        return "ok";
    }


//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalExHandler(IllegalArgumentException e) {
//        log.error("[exceptionHandle] ex", e);
//        return new ErrorResult("BAD", e.getMessage());
//    }

    @Data
    @AllArgsConstructor

    static class ErrorResult {
        private String code;
        private String message;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}

