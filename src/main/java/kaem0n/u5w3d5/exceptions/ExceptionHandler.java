package kaem0n.u5w3d5.exceptions;

import kaem0n.u5w3d5.payloads.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleBadRequest(BadRequestException e) {
        if (e.getErrorList() != null) {
            String msg = e.getErrorList().stream().map(err -> err.getDefaultMessage())
                    .collect(Collectors.joining(" "));
            return new ErrorResponseDTO(msg, LocalDateTime.now());
        }
        else return new ErrorResponseDTO(e.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFound(NotFoundException e) {
        return new ErrorResponseDTO(e.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleGenericError(Exception e) {
        e.printStackTrace();
        return new ErrorResponseDTO("Internal server error.", LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleMissingRequestParameter(MissingServletRequestParameterException e) {
        return new ErrorResponseDTO(e.getMessage() + ".", LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleUnauthorized(UnauthorizedException e) {
        return new ErrorResponseDTO(e.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDTO handleUnauthorized(org.springframework.security.access.AccessDeniedException e) {
        return new ErrorResponseDTO(e.getMessage(), LocalDateTime.now());
    }
}
