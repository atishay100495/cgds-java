# cgds-java
Java based API for accessing the MSKCC Cancer Genomics Data Server (CGDS) <br> 
Author: Atishay Jain, BITS Pilani Goa Campus, India <br>
Email: atishay100495@gmail.com <br>

Steps: <br>
1. mvn initialize <br>
2. ant compile <br>
3. Optional: "ant jar" will compile and build a jar file of the framework  <br>

<br>

Steps to run tests:  <br>
Running tests from Linux: <br>
1. cd cgds-java <br>
2. mvn initialize <br>
3. ant compile <br>
4. java -cp "build:\`echo lib/*.jar | tr ' ' ':'\`" -Dproject.home=. org.testng.TestNG testng.xml  <br>

Running tests from Eclipse: <br>
1. Install TestNG plugin in Eclipse  <br>
2. Right-click test file "cgds-java/src/test/java/org/cbioportal/cgds_java/tests/CGDSJavaAPITest.java". Select Run-As -> Run-Configurations  <br>
3. Double click TestNG in left navigation bar to create a TestNG profile.  <br>
4. Select project 'cgds-java'  <br>
5. Select class 'org.cbioportal.cgds_java.tests.CGDSJavaAPITest'  <br>
6. Set VM Argument for 'project.home'. Eg. -Dproject.home=/home/atishay/gsoc2016/cbioportal/workspace/cgds-java  <br>
7. Click Run  <br>
<br>
The latest test results can be downloaded here: <br>
https://docs.google.com/uc?authuser=0&id=0B15Tsm1HLWRJTGtKYkF0VlJXTG8&export=download 
<br>

