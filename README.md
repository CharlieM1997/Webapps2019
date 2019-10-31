# Webapps2019
A J2EE Web Application built to work with Payara.

## Setup Instructions

1. Install NetBeans IDE 8.2 or similar to open the Web App.

2. Open the webapps2019 folder as a project in NetBeans.

3. On NetBeans, go to Tools > Plugins > Installed and check if Payara Common and Payara Server are installed. If not, go to Available Updates, search for Payara and install them.

4. Go to Servers > Servers and right-click, then select Add Server. Select Payara Server, and direct to the payara5 folder. Set the username to admin and don't set a password. Set the name to 'Webapps' if prompted.

5. Follow the first three steps on this link [here.](https://netbeans.org/kb/docs/ide/java-db.html) The Java DB folder should be called 'db' in JDK 8 after installation, if not already installed. When it asks for the Database Location, select the 'Derby' folder.

6. Right-click on and start the Derby Server.

7. Right-click on and start the Webapps Server.

8. Right-click on webapps2019 in Projects, and Run.

9. When prompted, the username and password to log into the web app are both 'admin1'.

If there are difficulties setting the web app up or it is not possible to set it up on your end, I am happy to arrange a date and time to remotely showcase the web app.
