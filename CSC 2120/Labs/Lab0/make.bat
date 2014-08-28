@echo off
cls

::change these to conform to your java install
::set PATH="c:\Program Files\Java\jdk1.7.0_06\bin"
::set CLASSPATH="c:\Program Files\Java\jre7";.

javac thurn/ThurnTaxisDriver.java
::javac -cp %CLASSPATH% thurn/ThurnTaxisDriver.java

jar cfm thurn.jar manifest.txt thurn/*.class pqsort/*.class gui/*.class thurn/images org

java -jar thurn.jar
::java -cp %CLASSPATH% -jar thurn.jar
