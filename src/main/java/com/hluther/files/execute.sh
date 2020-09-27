cd "/home/helmuth/Documentos/Programas Desarrollados/NetBeansProjects/Java/Organizacion de Lenguajes y Compiladores 2/VCompiler/src/main/java/com/hluther/files"
java -jar java-cup-11b.jar -symbols VSym -parser VParser Parser.cup
#java -jar jflex-full-1.7.0.jar Lexer.flex
#mv VLexer.java "/home/helmuth/Documentos/Programas Desarrollados/NetBeansProjects/Java/Organizacion de Lenguajes y Compiladores 2/VCompiler/src/main/java/com/hluther/compiler/lexer/VLexer.java" 
mv VParser.java "/home/helmuth/Documentos/Programas Desarrollados/NetBeansProjects/Java/Organizacion de Lenguajes y Compiladores 2/VCompiler/src/main/java/com/hluther/compiler/parser/VParser.java"
mv VSym.java "/home/helmuth/Documentos/Programas Desarrollados/NetBeansProjects/Java/Organizacion de Lenguajes y Compiladores 2/VCompiler/src/main/java/com/hluther/compiler/parser/VSym.java"
