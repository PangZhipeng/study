package com.company.mySpring.mySpring;

import com.company.mySpring.mySpring.annotation.*;
import com.spring.annotation.ComponentScan;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手写模拟spring流程
 */
public class MyApplicationContext {

    private Class configClass;

    public static final String SCOPE_SINGLETON = "singleton";

    public static final String SCOPE_PROTOTYPE = "prototype";

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
    // 单例池
    private Map<String,Object> singletonMap = new HashMap<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public MyApplicationContext(Class configClass) throws Exception{
        this.configClass = configClass;
        // 扫描配置的包
        doScan(configClass);
        //    生成需要的单例bean
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (SCOPE_SINGLETON.equals(beanDefinition.getScope())){
                // 创建bean
                Object singletonBean = createBean(beanName, beanDefinition);
                singletonMap.put(beanName,singletonBean);
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        try {
            // spring的推断构造方法
            Object instance = clazz.getConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Autowired.class)){
                    field.set(instance,getBean(field.getName()));
                }
            }
            // 回调事件
            if (instance instanceof BeanNameAware){
                ((BeanNameAware)instance).setName(beanName);
            }

            // 初始化前
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(PostConstruct.class)){
                    method.invoke(instance);
                }
            }



            // 初始化事件
            if (instance instanceof InitializingBean){
                ((InitializingBean)instance).afterPropertiesSet();
            }
            // 初始化后  aop
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                instance = beanPostProcessor.postProcessorBeforeInitialization(instance,beanName);
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                instance = beanPostProcessor.postProcessorAfterInitialization(instance,beanName);
            }
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void doScan(Class configClass) throws Exception {
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScan = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            // 这里扫描的不是源码路径  是编译后的target文件夹中的
            String scanPath = componentScan.value();
            // 把. 替换位目录分隔符
            scanPath = scanPath.replace(".", "/");
            ClassLoader appClassLoader = MyApplicationContext.class.getClassLoader();
            URL resource = appClassLoader.getResource(scanPath);
            File file = new File(resource.getFile());
            // 遍历目录下的所有文件
            if (file.isDirectory()){
                File[] files = file.listFiles();
                for (File f : files) {
                    String absolutePath = f.getAbsolutePath();
                    String classPath = absolutePath.substring(absolutePath.indexOf("com"),absolutePath.indexOf(".class"));
                    classPath = classPath.replace("\\",".");
                    Class<?> aClass = appClassLoader.loadClass(classPath);
                    if (aClass.isAnnotationPresent(Component.class)){
                        // 自定义的类名字
                        Component componentAnnotate = aClass.getAnnotation(Component.class);
                        // 如果没有自定义 使用类名作为key
                        String beanName = StringUtils.isBlank(componentAnnotate.value()) ?
                                Introspector.decapitalize(aClass.getSimpleName()) : componentAnnotate.value();
                        // beanPostProcessor
                        if (BeanPostProcessor.class.isAssignableFrom(aClass)){
                            BeanPostProcessor beanPostProcessor = (BeanPostProcessor)aClass.getConstructor().newInstance();
                            beanPostProcessors.add(beanPostProcessor);
                        }
                        // 扫描出来的类信息
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setType(aClass);
                        beanDefinition.setLazy(false);

                        // 判断是不是 单例bean   scope的value  singleton: 单例  prototype是多例
                        if (aClass.isAnnotationPresent(Scope.class)) {
                            Scope scopeAnnotate = aClass.getAnnotation(Scope.class);
                            String scopeValue = scopeAnnotate.value();
                            if (SCOPE_PROTOTYPE.equals(scopeValue)){
                                beanDefinition.setScope(SCOPE_PROTOTYPE);
                            }else {
                                beanDefinition.setScope(SCOPE_SINGLETON);
                            }
                        }else{
                            // 没有scope 注解默认是单例的
                            beanDefinition.setScope(SCOPE_SINGLETON);
                        }
                        beanDefinitionMap.put(beanName,beanDefinition);
                    }
                }
            }
        }
    }

    public Object getBean(String beanName){
        if (beanDefinitionMap.containsKey(beanName)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            // 原型bean  每次get都要重新创建一个新的
            if (SCOPE_PROTOTYPE.equals(beanDefinition.getScope())){
                return createBean(beanName, beanDefinition);
            }else {
                // 单例bean 直接返回
                Object bean = singletonMap.get(beanName);
                if (bean == null){
                    bean = createBean(beanName,beanDefinition);
                    singletonMap.put(beanName,bean);
                }
                return bean;
            }
        }else {
            throw new NullPointerException(String.format("no such bean: %s ", beanName));
        }
    }
}
