package com.hluther.compiler.AST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author helmuth
 */
public class SymbolTable{

    private Map<String, Symbol> table;
    private SymbolTable previousTable;
    private ArrayList<Method> methods;
    private ArrayList<Class> classes;
    
    public SymbolTable(SymbolTable previousTable) {
        this.table = new HashMap<>();
        this.previousTable = previousTable;
        this.methods = new ArrayList<>();
        this.classes = new ArrayList<>();
    }
    
    public boolean addSymbol(Symbol symbol){
        if(table.get(symbol.getId()) == null){
            table.put(symbol.getId(), symbol);
            return true;
        } else{
            return false;
        }
    }
    
    public Symbol getSymbol(String id) {
        for (SymbolTable currentSymbolTable = this; currentSymbolTable != null; currentSymbolTable = currentSymbolTable.getPreviousTable()) {
            Symbol symbol = (Symbol)(currentSymbolTable.getTable().get(id));
            if (symbol != null) {
                return symbol;
            }
        }
        return null;
    }
    
    public boolean addMethod(Method method){
        if (!methods.stream().noneMatch(currentMethod -> (currentMethod.getId().equals(method.getId())))) {
            return false;
        }
        methods.add(method);
        return true;
    }
    
    public SymbolTable getPreviousTable(){
        return previousTable;
    }
    
    public Map<String, Symbol> getTable(){
        return table;
    }
    
    
        
    
    /*

    public String setVariable(Simbolo simbolo) {
        for (Tabla currentSymbolTable = this; currentSymbolTable != null; currentSymbolTable = currentSymbolTable.getAnterior()) {
            Simbolo encontro = (Simbolo) (currentSymbolTable.getTable().get(simbolo.getIdentificador()));
            if (encontro != null) {
                return "La variable con el identificador"
                        + simbolo.getIdentificador() + " ya existe.";
            }
        }
        this.table.put(simbolo.getIdentificador(), simbolo);
        return null;
    }

    

    public String setFuncion(Funcion f) {
        for (Funcion i : methods) {
            if (f.getNombre().equalsIgnoreCase(i.getNombre())) {
                return "La funcion con el identificador"
                        + f.getNombre() + " ya existe.";
            }
        }
        this.methods.add(f);
        return null;
    }

    public Funcion getFuncion(String nombre) {
        for (Tabla currentSymbolTable = this; currentSymbolTable != null; currentSymbolTable = currentSymbolTable.getAnterior()) {
            for (Funcion f : currentSymbolTable.getFunciones()) {
                if (f.getNombre().equalsIgnoreCase(nombre)) {
                    return f;
                }
            }
        }
        return null;
    }


    public void setTable(Map<String, Simbolo> Table) {
        this.table = Table;
    }

    public void setAnterior(Tabla Anterior) {
        this.previousTable = Anterior;
    }

    public ArrayList<Funcion> getFunciones() {
        return methods;
    }*/
}    
