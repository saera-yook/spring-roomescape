package roomescape.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleInvalidValue(InvalidValueException e) {
        return createProblemDetail("유효하지 않은 값", HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleInvalidInput(InvalidInputException e) {
        return createProblemDetail("입력 오류", HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleNotFound(NotFoundException e) {
        return createProblemDetail("입력 오류", HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(AlreadyExistingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleAlreadyExisting(AlreadyExistingException e) {
        return createProblemDetail("입력 오류", HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(BusinessRuleViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleBusinessRuleViolation(BusinessRuleViolationException e) {
        return createProblemDetail("비즈니스 정책 위반", HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleAuthentication(AuthenticationException e) {
        return createProblemDetail("인증 오류", HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    private ProblemDetail createProblemDetail(String title, HttpStatus status, String detail) {
        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        return problemDetail;
    }
}
