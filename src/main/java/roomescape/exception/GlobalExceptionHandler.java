package roomescape.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
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

    private ProblemDetail createProblemDetail(String title, HttpStatus status, String detail) {
        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        return problemDetail;
    }
}
