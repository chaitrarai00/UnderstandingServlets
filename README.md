# UnderstandingServlets
1. We will create or start with a login page which would be the starting point of the application. The user will have to enter valid email and password and try to login.
In case the user has not registered they will have to register first in the register.html page. Provide link to Register page in login page.
2.Register to the application throught this page. User provide email, password, name and country deais for registration. If any inoformation is missing user remains on the same page, 
with appropriate error triggered. once registration is successfull, user will be forwarded to login page with registration success information and they can use email and password to login.
3. use MySql to persist data, in user db User table. Application depends on Database connection so we create a Serlet Context Listener to initialize database copnnection and 
set it as attriobute for other servlets. We configure db details in deployment descriptor
4. for application logs we use Log4j and keep a different lof file: errors.log. We utilize servlet context listner to configure log4j.
5. In case of errors user should be presented with valid and useful error page. Make use of servlet exception handling.
6. on successful login the user is forwarded to home.jsp by creating a session for the user. home.jsp will protray information about the user. jsp is visible only with valid user session
7. user hom page also provides a logout page. where session is invalidated. rather than keeping session validation in all resources, we create Servlet Filter for session validation.
