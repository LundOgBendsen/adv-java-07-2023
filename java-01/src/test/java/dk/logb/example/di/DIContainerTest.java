package dk.logb.example.di;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DIContainerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test that a string is injected if @autowired is used")
    void testContainerDIBasic() {
        DIContainer<Component> diContainer = new DIContainer(Component.class);
        Component bean = diContainer.start();
        assertTrue(bean.s != null);
    }

    @Test
    @DisplayName("Test that initialization is recursive")
    void testRecursiveDI() {
        DIContainer<C1> diContainer = new DIContainer(C1.class);
        C1 bean = diContainer.start();
        assertEquals(3, bean.next());
    }

    @Test
    @DisplayName("Test that infinite recursion crashes container")
    //expect stackoverflowError

    void testRecursiveInfinite() {
        DIContainer<RecursiveBean> diContainer = new DIContainer(RecursiveBean.class);
        StackOverflowError thrown = Assertions.assertThrows(StackOverflowError.class, () -> {
            RecursiveBean bean = diContainer.start();
        }, "StackOverflowError was expected");

    }
}

@Bean
class Component {
    @Autowired
    String s;
}

@Bean
class C1 {
    @Autowired
    C2 c2;

    public int next() {
        return 1 + c2.next();
    }
}

@Bean
class C2 {
    @Autowired
    C3 c3;
    public int next() {
        return 1 + c3.next();
    }
}


@Bean
class C3 {
    public int next() {
        return 1;
    }
}

@Bean
class RecursiveBean {
    @Autowired
    RecursiveBean recursiveBean;
}
