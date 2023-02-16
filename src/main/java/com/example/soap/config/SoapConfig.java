package com.example.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class SoapConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> initializeMessageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
    }

    @Bean(name = "loan")
    public DefaultWsdl11Definition initializeDefaultWSDL11Definition(XsdSchema schema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("LoadDetector");//we can give any name for port type, but give a meaningful one
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setTargetNamespace("http://www.example.com/soap/models");
        defaultWsdl11Definition.setSchema(schema);
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema schema() {
        return new SimpleXsdSchema(new ClassPathResource("loan.xsd"));
    }
}
