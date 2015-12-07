<?xml version="1.0"?>

<project default="main" basedir=".">

<echo message="pulling in property files"/>
<property file="build.properties"/>
	
<echo message="building this thing"/>
 <target name="main" >
  	 <wsgen/>
  </target>
  
</project>
