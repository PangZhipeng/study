package com.company.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zhipeng.pang
 * @date 2022/10/20 10:55 ����
 */
public class ProxyTest {

    public static void main(String[] args) {
        // ���Դ���ģʽ
        User user = new User();
        user.setName("zhangsan");
//        // ��̬����  �����齻���������ȥ��
//        UserProxy userProxy = new UserProxy(user);
//        userProxy.doSomething();
        // ��̬����

        //����InvocationHandler����
        InvocationHandler userHandle = new DynamicsInvocationHandle<UserInterface>(user);

        //�����������,��������ÿ��ִ�з��������滻ִ��Invocation�е�invoke����
        UserInterface userProxy = (UserInterface) Proxy.newProxyInstance(UserInterface.class.getClassLoader(),new Class<?>[]{UserInterface.class}, userHandle);
        userProxy.doSomething();

        //Ҳ����ʹ������ķ�ʽ�������������Proxy.newProxyInstance��ʵ���Ƕ��������ķ�װ
		/*try {
			//ʹ��Proxy���getProxyClass��̬��������һ����̬������renterProxy
			Class<?> renterProxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class<?>[]{Person.class});
			//��ȡ������renterProxy�Ĺ�����������ΪInvocationHandler
			Constructor<?> constructor = renterProxyClass.getConstructor(InvocationHandler.class);
			//ʹ�ù���������һ��������ʵ������
			Person renterProxy = (Person)constructor.newInstance(renterHandler);
			renterProxy.rentHouse();
			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
}
