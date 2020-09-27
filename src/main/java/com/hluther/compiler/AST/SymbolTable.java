package com.hluther.compiler.AST;

import java.util.LinkedList;
/**
 *
 * @author helmuth
 */
public class SymbolTable extends LinkedList<Symbol>{

    public SymbolTable() {
        super();
    }
    
    /**
     * Método que busca una variable en la tabla de símbolos y devuelve su valor.
     * @param id Identificador de la variable que quiere buscarse
     * @return Valor de la variable que se buscaba, si no existe se devuelve nulo.
     */
    public Object getValue(String id) {
        if (contains(id)){
            for(Symbol s:this){
                if(s.getId().equals(id)){
                    return s.getValue();
                }
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito.");
        return null;
    }
    
    /**
     * Método que asigna un value a una variable específica, si no la encuentra
     * no realiza la asignación y despliega un mensaje de error.
     * @param id Identificador de la variable que quiere buscarse
     * @param value Valor que quiere asignársele a la variable buscada
     */
    void setValue(String id, Object value) {
        for(Symbol s:this){
            if(s.getId().equals(id)){
                s.setValue(value);
                return;
            }
        }
        System.out.println("La variable "+id+" no existe en este ámbito, por lo "
                + "que no puede asignársele un valor.");
    }

    @Override
    public boolean add(Symbol e) {
        if (!contains(e.getId())) {
            super.add(e);
            return true;
        }
        System.out.println("La variable "+e.getId()+" no puede declararse porque ya existe en este ámbito");
        return false;
    }
    
    @Override
    public void addLast(Symbol e) {
        if (!contains(e.getId())) {
            super.add(e);
        }
        System.out.println("La variable "+e.getId()+" no puede declararse porque ya existe en este ámbito");
    }
    
    /**
     * Metodo que busca una variable especifica.
     * @param id
     * @return True si existe la variable. De lo contrario false.
     */
    public boolean contains(String id){
        return this.stream().anyMatch((Symbol s) -> s.getId().equals(id));
    }
    
}
   
