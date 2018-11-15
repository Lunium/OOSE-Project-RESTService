package nl.han.oose.dynamicroombackend.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BeanDefinitionsTest {

    @InjectMocks
    BeanDefinitions definitions;

    @Test
    public void modelMapper() throws Exception {
        ModelMapper mapper = definitions.modelMapper();
        assertNotNull(mapper);
    }

}