package ua.yyunikov.facade.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.yyunikov.controller.ErrorCode;
import ua.yyunikov.persistence.entity.Event;

public class EventValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, Event.Meta.TYPE, ErrorCode.FIELD_EMPTY.name(), ErrorCode.FIELD_EMPTY.createMessage(Event.Meta.TYPE));
        ValidationUtils.rejectIfEmpty(errors, Event.Meta.PARAMS, ErrorCode.FIELD_EMPTY.name(), ErrorCode.FIELD_EMPTY.createMessage(Event.Meta.PARAMS));
    }
}
