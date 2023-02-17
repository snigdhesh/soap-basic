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

    /**
     * This method is nothing but the WSDL definition. WSDL is for SOAP service, whereas SWAGGER for REST service.
     * @param schema
     * @return
     */
    @Bean(name = "loan") //This name value you put here will be your WSDL name. Ex: localhost:8080/ws/loan.wsdl
    public DefaultWsdl11Definition initializeDefaultWSDL11Definition(XsdSchema schema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        /**
         * PortType is nothing but a name given for group of operations. Like users, pet etc,
         * we can give any name for port type, but give a meaningful one
         */
        defaultWsdl11Definition.setPortTypeName("LoadDetector");
        defaultWsdl11Definition.setLocationUri("/ws");
        //This is nothing but the package name, which resolves to com.example.soap.models
        defaultWsdl11Definition.setTargetNamespace("http://www.example.com/soap/models");
        defaultWsdl11Definition.setSchema(schema);
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema schema() {
        return new SimpleXsdSchema(new ClassPathResource("loan.xsd"));
    }
}
