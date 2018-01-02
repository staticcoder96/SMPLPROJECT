# Commands to run
cd smpl/syntax
jflex smplLexer.jflex
cup -parser SMPLParser SMPLParser.cup
cd ..
cd ..
export CLASSPATH=".:/home/sanjay/SMPLPROJECT/:/usr/share/java/cup.jar"
javac smpl/values/*.java
javac smpl/sys/SmplRepl.java
java smpl.sys.SmplRepl ../smpl-tests-2017.smpl