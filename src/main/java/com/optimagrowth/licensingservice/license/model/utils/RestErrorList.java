package com.optimagrowth.licensingservice.license.model.utils;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class RestErrorList extends ArrayList<ErrorMessage> {

    private static final long serialVersionUID = -721424777198115589L;

    @Getter
    @Setter
    private HttpStatus status;

    public RestErrorList(HttpStatus status, ErrorMessage... errors) {
        this(status.value(), errors);
    }

    public RestErrorList(int status, ErrorMessage... errors) {
        super();
        this.status = HttpStatus.valueOf(status);
        addAll(asList(errors));
    }
}
