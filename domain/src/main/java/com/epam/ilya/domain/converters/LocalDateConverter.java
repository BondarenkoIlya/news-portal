package com.epam.ilya.domain.converters;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * {@inheritDoc}
 *
 * @author Ilya_Bondarenko
 */
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    /**
     * {@inheritDoc}
     **/
    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        return Date.valueOf(date);
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        return dbData.toLocalDate();
    }
}
