/*
package restful.service;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class PortCustom implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{
	private static final Logger logger = LoggerFactory.getLogger(PortCustom.class);
	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		((TomcatServletWebServerFactory)factory).addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
				int port;
				try {
				port = 9595;
					logger.error("访问端口{}", port);
				}catch (Exception e) {
					logger.error("端口获取异常，采用默认端口8080");
					port = 8080;
				}
				protocol.setPort(port);
			}
		});
	}
}
*/
