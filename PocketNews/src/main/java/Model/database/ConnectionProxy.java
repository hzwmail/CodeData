package Model.database;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class ConnectionProxy implements InvocationHandler {
	private Connection conn;
	
	public ConnectionProxy(Connection conn){
		this.conn=conn;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		this.showsql(args);
		Object result=method.invoke(conn, args);
		return result;
	}
	
	private void showsql(Object[] args){
		if(args!=null){
			System.out.println("[showsql:"+args[0]+"]");
		}
	}
	
	public Connection newInstance(){
		ClassLoader classLoder=conn.getClass().getClassLoader();
		Class<?>[] interfaces=new Class<?>[]{Connection.class};
		return (Connection) Proxy.newProxyInstance(classLoder, interfaces, this);
	}

}
