package dk.logb.example.di;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 *  A simple Dependency Injection (DI) container
 *  Components are called beans. The container can have one root bean.
 *  The root bean is the bean type is passed to the constructor of the container:
 *
 *  new DIContainer<AComponent>(AComponent.class)
 *
 *  The container is bootstrapped by calling start() which returns an instance of the root bean class.
 *
 *  Fields annotated with @Autowired in beans will be injected with instances of the field type.
 * @Bean can be used to signal that a class is a bean. This is not necessary for the container to work.
 *
 *  An instance of the field type is created by calling the default constructor of the field type.
 *
 * @param <T>
 *     The type of the root bean
 */
public class DIContainer<T> {

    private static final Logger logger = Logger.getLogger(DIContainer.class.getName());

    Class<T> clazz = null;
    T rootBean = null;

    public DIContainer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T start() {
        this.rootBean = (T) this.initialize(clazz);
        return this.rootBean;
    }

    /**
     * Recursively initialize beans. New instances are created by calling the default constructor.
     * @param clazz, the class to be initialized i.e. inject all dependencies into @Autowired fields
     * @return an initialized instance of class clazz
     */
    private Object initialize(Class clazz) {
        try {

            //create a new instance of the class to be initialized
            Object bean =  clazz.newInstance();
            //iterate fields of the class
            for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                //if field is annotated with @Autowired
                if (field.isAnnotationPresent(Autowired.class)) {
                    validateField(field);
                    field.setAccessible(true); //make private fields accessible
                    //create new instance of @Autowired field type and inject it into the field
                    field.set(bean, initialize(field.getType()));
                }
            }
            return bean;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateField(Field field) {
        //is field type an interface, i.e. it cannot be instantiated? If so throw exception
        if (field.getType().isInterface()) {
            throw new RuntimeException("Cannot autowire interface " + field.getType().getName());
        }
        //is type annotated with @Bean? If not log warning
        if (!field.getType().isAnnotationPresent(Bean.class)) {
            logger.warning("Injecting type  " + field.getType().getName() + " which is not annotated with @Bean. Location: " + field.getDeclaringClass().getSimpleName() + "." + field.getName());
        }
    }

    public static void main(String[] args) {
        logger.info("Booting container");
        ABean aBean = new DIContainer<ABean>(ABean.class).start();
        //are all these words palindromes?
        logger.info( "" + aBean.isAllPalindrome("otto", "anna", "hannah", "kayak", "level", "racecar", "radar", "rotator", "rotor", "tenet"));

    }


}


/**
 * Optional Annotation for marking a class as a bean, i.e. a component that can be injected into other beans.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Bean {
    String value() default "";
}

/**
 *  Mandatory Annotation for marking a field as a field that should be injected with an instance of the field type.
 */
@Target(value=ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Autowired {
    String value() default "";
}


/**
 * Example of a bean
 */

@Bean
class ABean {

    @Autowired
    private BBean bBean = null;


    public boolean isAllPalindrome(String... strings) {
        for (String s : strings) {
            if (!bBean.isPalindrome(s)) {
                return false;
            }
        }
        return true;
    }

    public ABean() {
        System.out.println("ABean created");
    }

    public void setBComponent(BBean bComponent) {
        this.bBean = bComponent;
    }

    public BBean getBComponent() {
        return this.bBean;
    }
}

/**
 * Example of a bean
 */
@Bean
class BBean {

    @Autowired
    private String s = null;


    public boolean isPalindrome(String s) {
        System.out.println("BBean.s = " + this.s); //this should not print '...null' since the field should be injected
        return s.equals(new StringBuilder(s).reverse().toString());
    }

}




