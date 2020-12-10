package br.com.santander.core;

public class PropertyException extends Exception {

	   public PropertyException() {
	        super("Propriedade não existente ou valor da propriedade está vazio");
	    }
}
