package by.lobov.rest_homework46.config;//package by.lobov.restapihomework46.config;
//
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//public class MyInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    // Этот метод должен содержать конфигурации которые инициализируют Beans
//    // для инициализации бинов у нас использовалась аннотация @Bean
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[]{SpringConfig.class};
//    }
//
//    // Тут добавляем конфигурацию, в которой инициализируем ViewResolver (SpringConfig.class)
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[]{SpringConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//}
