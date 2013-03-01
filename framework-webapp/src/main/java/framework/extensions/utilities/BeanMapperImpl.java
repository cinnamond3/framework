package framework.extensions.utilities;

import javax.inject.Inject;
import javax.inject.Named;

import org.dozer.Mapper;

import framework.core.entity.AbstractEntity;
import framework.ui.databeans.AbstractDTO;
import framework.ui.utilities.BeanMapper;

@Named
public class BeanMapperImpl implements BeanMapper {

    private final Mapper dozerBeanMapper;

    @Inject
    protected BeanMapperImpl(Mapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public <T extends AbstractEntity, I extends AbstractDTO> I convert(T source, Class<I> destinationClass) {
        return this.dozerBeanMapper.map(source, destinationClass);
    }

    @Override
    public <T extends AbstractDTO, I extends AbstractEntity> I convert(T source, Class<I> destinationClass) {
        return this.dozerBeanMapper.map(source, destinationClass);
    }
}
