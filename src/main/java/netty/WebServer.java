package netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebServer {
	
	private static Logger logger = LoggerFactory.getLogger(WebServer.class);

	public static void main(String[] args) throws Exception {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		Integer port = 6001;
//		DispatcherServlet servlet = getDispatcherServlet();
//		NettyHttpServer server = new NettyHttpServer(port,servlet);
//		server.start();
		
		Integer port = 6001;
		DispatcherServlet servlet = getDispatcherServlet();
		NettyHttpServer server = new NettyHttpServer(port,servlet);
		server.start();
		
	}
	
	public static DispatcherServlet getDispatcherServlet() throws Exception{

		MockServletContext servletContext = new MockServletContext();
		MockServletConfig servletConfig = new MockServletConfig(servletContext);

		XmlWebApplicationContext mvcContext = new XmlWebApplicationContext();
		mvcContext.setServletContext(servletContext);
		mvcContext.setServletConfig(servletConfig);
		mvcContext.setConfigLocation("classpath:applicationContext.xml");
		mvcContext.refresh();

		DispatcherServlet dispatcherServlet = new DispatcherServlet(mvcContext);
		dispatcherServlet.init(servletConfig);
		return dispatcherServlet;
		
	}
	
}
