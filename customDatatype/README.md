# customDatatype
Making custom datatype password using CustomDatatypeHandler class.
To make the H2 database work, you need to add the h2 jar file in your class path. I made some changes to the H2 jar file contents and added that to my classpath. However, it is not able to find the org.h2.Driver class then. Check it out.
Errorcode , Value and SysProperties are modified classes of the h2 jar. CustomPassword, Password and PasswordValue classes are totally made by me to make the new datatype Password.
