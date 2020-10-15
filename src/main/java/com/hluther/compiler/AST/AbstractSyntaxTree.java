package com.hluther.compiler.AST;

import com.hluther.controlClasses.ThreeAddressCodeDriver;
import com.hluther.gui.VCompilerFrame;
import java.util.LinkedList;
/**
 * Clase que contiene todas las funciones, procedimientos, clases y variables declaradas.
 * @author helmuth
 */
public class AbstractSyntaxTree implements Instruction{
    
    private final LinkedList<Instruction> visualBasicInstructions;
    private final LinkedList<Instruction> javaInstructions;
    private final LinkedList<Instruction> phytonInstructions;
    private final LinkedList<Instruction> cInstructions;
    private final char T_VALUE = 194;
    private final char L_VALUE = 192;
    
    /**
     * Constructor de la clase. 
     * @param visualBasicInstructions Lista de intrucciones de la seccion de Visual Basic del archivo de entrada.
     * @param javaInstructions Lista de intrucciones de la seccion de Java del archivo de entrada.
     * @param phyInstructions Lista de intrucciones de la seccion de Phyton del archivo de entrada.
     * @param cInstructions Lista de intrucciones de la seccion de C del archivo de entrada. Esta lista contiene el main.
     */
    public AbstractSyntaxTree(LinkedList<Instruction> visualBasicInstructions, LinkedList<Instruction> javaInstructions, LinkedList<Instruction> phyInstructions, LinkedList<Instruction> cInstructions){
        this.visualBasicInstructions = visualBasicInstructions;
        this.javaInstructions = javaInstructions;
        this.phytonInstructions = phyInstructions;
        this.cInstructions = cInstructions;
    }
    
    public LinkedList<Instruction> getVisualBasicInstructions() {
        return visualBasicInstructions;
    }

    public LinkedList<Instruction> getJavaInstructions() {
        return javaInstructions;
    }

    public LinkedList<Instruction> getPhytonInstructions() {
        return phytonInstructions;
    }

    /**
     * Método que retorna el sub-arbol de una función que es buscada por su id.
     * @param threeAddressCodeDriver
     * @param symbolTable
     * @param id Identificador de la función buscada
     * @return Sub-arbol de la función si esta existe o null en caso de que 
     * no fuera encontrada.
     */
    /*
    public Method getMethod(String id){
        for(var instruction : instructions){
            if(instruction instanceof Method){
                Method method = (Method)instruction;
                if(method.getId().equals(id)){
                    return method;
                }
            }
        }
        return null;
    }*/
    
    
    @Override
    public String generateOnlyReadThreeAddressCode(ThreeAddressCodeDriver threeAddressCodeDriver) {
        String code = "";
        code = visualBasicInstructions.stream().map(method -> method.generateOnlyReadThreeAddressCode(threeAddressCodeDriver) + "\n").reduce(code, String::concat);        
        code = javaInstructions.stream().map(instruction -> instruction.generateOnlyReadThreeAddressCode(threeAddressCodeDriver) + "\n").reduce(code, String::concat);
        System.out.println(code.replaceAll(String.valueOf(T_VALUE), "t").replaceAll(String.valueOf(L_VALUE), "L"));
        return code;
    }
    
    /**
     * Método que ejecuta las instrucciones del AST, es una sobreescritura del 
 método analyze que existe por la implementación de la interfaz Instruction
     * @param symbolTable  tabla de símbolos del ámbito padre de la sentencia
     * @param ast
     * @param vCompilerFrame
     * @return Esta instrucción retorna el valor producido por la operación que se ejecutó
     */  
    @Override
    public Object analyze(SymbolTable symbolTable, AbstractSyntaxTree ast, VCompilerFrame vCompilerFrame){        
        //Instanciacion de una tabla de simbolos global
        SymbolTable globalSymbolTable = new SymbolTable(null);
        
        //Validar y agregar cada uno de los metodos a la tabla de simbolos. Analizar las instrucciones de cada metodo.
        for(var instruction : visualBasicInstructions){
            Method currentMethod = (Method)instruction;
            if(!globalSymbolTable.addMethod(currentMethod)){
                vCompilerFrame.printMessage("Fila: " +currentMethod.getRow()+ " Columna: " +currentMethod.getColumn()+ " Error Semantico " + "Ya existe un metodo declarado con el identidicador [" +currentMethod.getName()+ "].");
            }
            currentMethod.analyze(globalSymbolTable, ast, vCompilerFrame);
        }   
        
           
        
        
        
 /*       
        
        
        
        
        //Analizar las instrucciones de codigo Visual Basic
        visualBasicInstructions.forEach(instruction -> {
            instruction.analyze(new SymbolTable(null), ast, vCompilerFrame);
        });
        
        //Analizar las instrucciones de codigo Java
        javaInstructions.forEach(instruction -> {
            instruction.analyze(new SymbolTable(null), ast, vCompilerFrame);
        });
        
        
     
        /*
        
        globalSymbolTable = symbolTable;    
        
        //Imports para el main
        cInstructions.stream().filter(instruction -> (instruction instanceof Import)).forEachOrdered(instruction -> {
            instruction.analyze(symbolTable, ast, vCompilerFrame);
        });
        
        //Declaraciones y asignaciones globlales del main
        cInstructions.stream().filter(instruction -> (instruction instanceof Declaration || instruction instanceof Assignment)).forEachOrdered(instruction -> {
            instruction.analyze(symbolTable, ast, vCompilerFrame);
        });
        
        //Instrucciones dentro del main
        for(var instruction : cInstructions){
            if(instruction instanceof Method){
                Method method = (Method)instruction;
                if("_main()".equals(method.getId())){
                    method.setParamsValue(new LinkedList<>());
                    method.setImported(true);
                    method.analyze(symbolTable, ast, vCompilerFrame);
                    break;
                }
            }
        }
        */
        return null;
    }
  
}