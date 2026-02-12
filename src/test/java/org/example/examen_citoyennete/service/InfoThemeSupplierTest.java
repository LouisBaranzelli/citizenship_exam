package org.example.examen_citoyennete.service;

import org.example.examen_citoyennete.model.Theme;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
class InfoThemeSupplierTest {

    private static Logger logger = LoggerFactory.getLogger(InfoThemeSupplierTest.class);

    @Autowired
    InfoThemeSupplier infoThemeSupplier;

    @Test
    public void test(){
        logger.info("{}", infoThemeSupplier.getDescription(Theme.T1, Locale.FRANCE));
    }
}