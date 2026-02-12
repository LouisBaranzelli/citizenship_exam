package org.example.examen_citoyennete.service;

import org.example.examen_citoyennete.model.Theme;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceSupport;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class InfoThemeSupplier {

    MessageSource messageSource;

    public InfoThemeSupplier(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getLabel(Theme theme, Locale local){
        return messageSource.getMessage("theme."+ theme.name() + ".label", null,"test", local);
    }

    public String getDescription(Theme theme, Locale local){
        return messageSource.getMessage("theme."+ theme.name() + ".description", null,"test", local);
    }

}
