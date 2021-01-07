package br.com.brainweb.interview.core.features.hero;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnit4.class)
public class HeroControllerTest {

    @InjectMocks
    HeroController heroController;

    @Test
    public void testFindAll_Success() {
    }
}
