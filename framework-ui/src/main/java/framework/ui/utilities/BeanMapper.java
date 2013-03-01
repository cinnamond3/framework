package framework.ui.utilities;

import framework.core.entity.AbstractEntity;
import framework.ui.databeans.AbstractDTO;

public interface BeanMapper {

    <T extends AbstractEntity, I extends AbstractDTO> I convert(T source, Class<I> destinationClass);

    <T extends AbstractDTO, I extends AbstractEntity> I convert(T source, Class<I> destinationClass);
}
