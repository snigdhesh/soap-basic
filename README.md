### step1 : Add required SOAP dependencies

Add following dependency to make spring boot project ready for SOAP services.

```
     <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web-services</artifactId>
     </dependency>
```

Add following dependency to make spring boot project ready for WSDL services.

```
     <dependency>
        <groupId>wsdl4j</groupId>
        <artifactId>wsdl4j</artifactId>
     </dependency>
```

### step2 : Add XSD file under src/main/resources (see this repo for reference)

- Add `targetNamespace="http://www.example.com/soap` under namespaces.
- Where `targetNamespace` defines package in project. `com.example.soap`
- If `targetNamespace` is `http://www.example.com/soap/models` package is like `com.example.soap.models` 
- Which means, all following request and response xmls will be converted into Java classes and put into the package mentioned above in `targetNamespace`

**Let's say we have request and response like**

CustomerRequest:
- name:**string**
- email:**string**
- Id:**int**

CustomerResponse:
- isLoanApproved:**boolean**
- eligibleAmount:**long**
- policies:**strings**


Request and response formats are same, which is
```
<xsd:element name="">
  <xsd:complexType>
    <xsd:sequence>
      <xsd:element name="" type=""/>
      <xsd:element name="" type=""/>
    </xsd:sequence>
  </xsd:complexType>
</xsd:element>
```
Example for request mentioned above looks like
```
<xsd:element name="CustomerRequest">
    <xsd:complexType>
        <xsd:sequence>
            <xsd:element name="name" type="xsd:string"/>
            <xsd:element name="email" type="xsd:string"/>
            <xsd:element name="Id" type="xsd:int"/>
        </xsd:sequence>
    </xsd:complexType>
</xsd:element>

```
Example for response mentioned above looks like

```
<xsd:element name="CustomerResponse">
    <xsd:complexType>
        <xsd:sequence>
            <xsd:element name="isLoanApproved" type="xsd:boolean"/>
            <xsd:element name="eligibleAmount" type="xsd:long"/>
            <xsd:element name="policies" type="xsd:string" maxOccurs="unbounded"/>
        </xsd:sequence>
    </xsd:complexType>
</xsd:element>
```

### step3: Add XJC plugin in pom.xml to convert XSD to Java classes

```
<plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>jaxb2-maven-plugin</artifactId>
      <version>1.6</version>
      
      <executions>
          <execution>
              <id>xjc</id>
              <goals>
                  <goal>xjc</goal>
              </goals>
          </execution>
      </executions>
      
      <configuration>
          <schemaDirectory>${project.basedir}/src/main/resources/</schemaDirectory>
          <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
          <clearOutputDir>false</clearOutputDir>
      </configuration>
</plugin>
```

### step4: Run `mvn clean install` to generate POJO classes out of xsd.