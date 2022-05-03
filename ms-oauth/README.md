#OAUTH 2#

OAUTH example extracted from: 

https://developpaper.com/ultimate-solution-for-microservice-permissions-spring-cloud-gateway-oauth2/
git: github.com/it-wwh/spring-cloud-gateway-oauth2

####
 
The Gateway example on this location, although a good example with reactive web,
didn´t work properly for this study case, because of complexity using reactive 
spring features

####

Creation of JWT Keystore

-> Explains keystore creation
https://www.baeldung.com/keytool-intro

keytool -genkey -alias <alias> -keyalg <key alg> -validity <validity> -keystore <name of file>.jks

*** The keystore created for this ms-oauth and other services ***
keytool -genkey -alias msdev -keyalg RSA -validity 720 -keystore dev.jks

alias – the name for our certificate
validity – the time (in days) of the validity of our certificate
key alg - algorithm to be used (ex: RSA)

*** parameters after # where used to create dev.jks

After command, will prompt for:
Enter keystore password:  # dev123
Re-enter new password:  # dev123

What is your first and last name?
  [Unknown]:  Name # devsuperior
What is the name of your organizational unit?
  [Unknown]:  Unit # devsuperior
What is the name of your organization?
  [Unknown]:  Company # devsuperior
What is the name of your City or Locality?
  [Unknown]:  City # city
What is the name of your State or Province?
  [Unknown]:  State # state
What is the two-letter country code for this unit?
  [Unknown]:  US # BR
Is CN=Name, OU=Unit, O=Company, L=City, ST=State, C=US correct?
  [no]:  yes

-> Explains how to extract public key from jks store

https://www.baeldung.com/spring-security-oauth-jwt-legacy  Item 7.2

>>> For commands presented, you have to have openssl available!!! <<<

keytool -list -rfc --keystore <name of file>.jks | openssl x509 -inform pem -pubkey -noout  -out <name of file>.pem

Which creates a <name of file>.pem with a content like:

-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgIK2Wt4x2EtDl41C7vfp
OsMquZMyOyteO2RsVeMLF/hXIeYvicKr0SQzVkodHEBCMiGXQDz5prijTq3RHPy2
/5WJBCYq7yHgTLvspMy6sivXN7NdYE7I5pXo/KHk4nz+Fa6P3L8+L90E/3qwf6j3
DKWnAgJFRY8AbSYXt1d5ELiIG1/gEqzC0fZmNhhfrBtxwWXrlpUDT0Kfvf0QVmPR
xxCLXT+tEe1seWGEqeOLL5vXRLqmzZcBe1RZ9kQQm43+a9Qn5icSRnDfTAesQ3Cr
lAWJKl2kcWU1HwJqw+dZRSZ1X4kEXNMyzPdPBbGmU6MHdhpywI7SKZT7mX4BDnUK
eQIDAQAB
-----END PUBLIC KEY-----

*** The publickey.pem created for this ms-oauth and other services with below command ***
keytool -list -rfc --keystore dev.jks | openssl x509 -inform pem -pubkey -noout  -out devpublic.pem