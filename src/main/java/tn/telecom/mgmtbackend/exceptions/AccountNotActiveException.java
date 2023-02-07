package tn.telecom.mgmtbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccountNotActiveException extends Exception{
    public AccountNotActiveException(){
        super("Account not active");
    }
}
