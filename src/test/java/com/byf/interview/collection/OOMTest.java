package com.byf.interview.collection;

import java.lang.reflect.Method;
 
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Dispatcher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
 
public class OOMTest {
 
	public static void main(String[] args) {
		System.out.println("Let us do it now.....");
		for(int i=0;i<100000;i++){
		   Enhancer enhancer = new Enhancer();
		   enhancer.setSuperclass(OOMTest.class);
		   enhancer.setCallbackTypes(new Class[] {
		            Dispatcher.class, MethodInterceptor.class });
		   enhancer.setCallbackFilter(new CallbackFilter() {
		        public int accept(Method method) {
		        	return 1;
		        }
		    });
		   
		   Class clazz = enhancer.createClass();
		   
		   System.out.println("Time:" + System.currentTimeMillis()); 
		}
	}
}