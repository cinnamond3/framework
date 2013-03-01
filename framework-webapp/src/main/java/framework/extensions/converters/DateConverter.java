package framework.extensions.converters;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.DozerConverter;

import framework.core.utilities.DateUtils;

@Named
public class DateConverter extends DozerConverter<Date, Long> {

    private DateUtils dateUtils;
    
    @Inject
    protected DateConverter(DateUtils dateUtils) {
        super(Date.class, Long.class);
        this.dateUtils = dateUtils;
    }
    
    @Override
    public Long convertTo(Date source, Long destination) {
        return dateUtils.convertToUnixTime(source);
    }

    @Override
    public Date convertFrom(Long source, Date destination) {
        return dateUtils.convertToDate(source);
    }

}
