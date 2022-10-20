package com.company.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 10:55 周四
 */
public class ProxyTest {

    public static void main(String[] args) {
        // 测试代理模式
        User user = new User();
        user.setName("zhangsan");
//        // 静态代理  把事情交给代理对象去做
//        UserProxy userProxy = new UserProxy(user);
//        userProxy.doSomething();
        // 动态代理

        //创建InvocationHandler对象
        InvocationHandler userHandle = new DynamicsInvocationHandle<UserInterface>(user);

        //创建代理对象,代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        UserInterface userProxy = (UserInterface) Proxy.newProxyInstance(UserInterface.class.getClassLoader(),new Class<?>[]{UserInterface.class}, userHandle);
        userProxy.doSomething();

        //也可以使用下面的方式创建代理类对象，Proxy.newProxyInstance其实就是对下面代码的封装
		/*try {
			//使用Proxy类的getProxyClass静态方法生成一个动态代理类renterProxy
			Class<?> renterProxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class<?>[]{Person.class});
			//获取代理类renterProxy的构造器，参数为InvocationHandler
			Constructor<?> constructor = renterProxyClass.getConstructor(InvocationHandler.class);
			//使用构造器创建一个代理类实例对象
			Person renterProxy = (Person)constructor.newInstance(renterHandler);
			renterProxy.rentHouse();
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
}
