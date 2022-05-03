#GATEWAY#

# Gateway example extract from:

 https://oril.co/blog/spring-cloud-gateway-security-with-jwt/
 git: https://github.com/igorkosandyak/spring-cloud-demo
 
 
# Tip on automatic services discovery
https://www.appsdeveloperblog.com/spring-cloud-api-gateway-automatic-mapping-of-routes/#:~:text=Enable%20Discovery%20Locator,Gateway%20project%2C%20open%20the%20application.

# Below, necessary configurations to work on application.proerties
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lowerCaseServiceId = true


# Changing Zuul with Gateway

>> References <<
 https://spring.io/guides/gs/gateway/
 https://cloud.spring.io/spring-cloud-gateway/reference/html/appendix.html
 https://www.javainuse.com/spring/cloud-gateway
 https://stackoverflow.com/questions/64675523/springcloud-api-gateway-properties-file-declaring-variables

It's possible to configure routes within application.properties or via java based configuration

# Configurations example on application.properties
spring.cloud.gateway.routes[1].id=EUREKA-MS-PAYROLL
spring.cloud.gateway.routes[1].uri=lb://MS-PAYROLL
spring.cloud.gateway.routes[1].predicates[0].name=Path
spring.cloud.gateway.routes[1].predicates[0].args[pattern]=/payments/**

# CORS

>> Reference <<
https://stackoverflow.com/questions/70561543/configure-cors-policy-for-spring-cloud-gateway