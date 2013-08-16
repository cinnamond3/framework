package framework.core.utilities;

import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import framework.core.constants.ParameterCode;
import framework.core.entity.SystemParameter;
import framework.core.service.SystemParameterService;

/**
 * Provides Transactional for {@link DataInitializationStartup}.
 * 
 * @author Frederick Yap
 */
@Named
public class DataInitializerServiceImpl {

    private List<DataGenerator> dataGenerators;
    private SystemParameterService systemParameterService;
    private XMLEncoder xmlEncoder;

    /**
     * Default constructor.
     */
    protected DataInitializerServiceImpl() {
    }

    /**
     * Calls the saveOrUpdate method of all {@link DataGenerator} instances.
     */
    public void update() {
        this.sort();
        for (final DataGenerator dataGenerator : this.dataGenerators) {
            SystemParameter systemParameter = this.systemParameterService.findByCode(ParameterCode.DB_VERSION);
            if (systemParameter != null) {
                final Integer currentDBVersion = Integer.valueOf(EncryptionUtil.decrypt(systemParameter.getValue()));
                if (dataGenerator.getDBVersion() > currentDBVersion) {
                    dataGenerator.performDataOperation();
                    systemParameter.setValue(EncryptionUtil.encrypt(String.valueOf(dataGenerator.getDBVersion())));
                    this.systemParameterService.saveOrUpdate(systemParameter);
                }
            } else {
                final ClassLoader classLoader = this.getClass().getClassLoader();
                final InputStream resourceAsStream = classLoader.getResourceAsStream("Default.data");
                systemParameter = this.xmlEncoder.convert(resourceAsStream, SystemParameter.class);
                systemParameter.setValue(EncryptionUtil.encrypt(String.valueOf(dataGenerator.getDBVersion())));
                dataGenerator.performDataOperation();
                this.systemParameterService.saveOrUpdate(systemParameter);
            }
        }

    }

    /**
     * Sets the list of {@link DataGenerator} implementation to be used by this class.
     * 
     * @param cryptography
     *            list {@link DataGenerator} implementation injected by the IOC container.
     */
    @Inject
    protected void setDataGenerators(List<DataGenerator> dataGenerators) {
        this.dataGenerators = dataGenerators;
    }

    /**
     * Sets the instance of {@link SystemParameterService} implementation to be used by this class.
     * 
     * @param cryptography
     *            {@link SystemParameterService} implementation injected by the IOC container.
     */
    @Inject
    protected void setSystemParameterService(SystemParameterService systemParameterService) {
        this.systemParameterService = systemParameterService;
    }

    /**
     * Sets the instance of {@link XMLEncoder} implementation to be used by this class.
     * 
     * @param cryptography
     *            {@link XMLEncoder} implementation injected by the IOC container.
     */
    @Inject
    protected void setXmlEncoder(XMLEncoder xmlEncoder) {
        this.xmlEncoder = xmlEncoder;
    }

    /**
     * Sorts all {@link DataGenerator} based on version.
     */
    protected void sort() {
        Collections.sort(this.dataGenerators, new Comparator<DataGenerator>() {
            @Override
            public int compare(DataGenerator o1, DataGenerator o2) {
                return o1.getDBVersion() - o2.getDBVersion();
            }
        });
    }

}