/*******************************************************************************
 * Copyright (c) 2016-2019 Embedded Systems and Applications Group
 * Department of Computer Science, Technische Universitaet Darmstadt,
 * Hochschulstr. 10, 64289 Darmstadt, Germany.
 * <p>
 * All rights reserved.
 * <p>
 * This software is provided free for educational use only.
 * It may not be used for commercial purposes without the
 * prior written permission of the authors.
 ******************************************************************************/
package mavlc.context_analysis;

import mavlc.errors.OverwritingDeclarationError;
import mavlc.errors.UndeclaredReferenceError;
import mavlc.syntax.function.Function;
import mavlc.syntax.record.RecordTypeDeclaration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Module environment containing all functions and record types accessible in this
 * module, i.e. all functions and record types defined in the module and all
 * functions from the standard runtime environment.
 */
public class ModuleEnvironment {
	
	protected final Map<String, Function> functions = new HashMap<>();
	
	protected final Map<String, RecordTypeDeclaration> records = new HashMap<>();
	
	public ModuleEnvironment() {
		functions.putAll(RuntimeFunctions.getRuntimeFunctions());
	}
	
	/**
	 * Add a function to the set of functions defined in this module.
	 *
	 * @param function Function defined in this function.
	 */
	public void addFunction(Function function) {
		String name = function.name;
		if(functions.containsKey(name)) {
			throw new OverwritingDeclarationError(name, functions.get(name), function);
		}
		functions.put(name, function);
	}
	
	
	/**
	 * Get the function declaration (AST node) for the given function name.
	 * Throws an {@link UndeclaredReferenceError} if no function with the given
	 * name was defined in this module or the standard runtime environment.
	 *
	 * @param name Function name.
	 * @return The function declaration if a function with the given name is defined.
	 */
	public Function getFunctionDeclaration(String name) {
		if(!functions.containsKey(name)) {
			throw new UndeclaredReferenceError(name);
		}
		return functions.get(name);
	}
	
	public Collection<Function> getFunctions() { return functions.values(); }
	
	/**
	 * Add a record type to the set of record types defined in this module.
	 *
	 * @param recordType recordType defined in this record type.
	 */
	public void addRecordTypeDeclaration(RecordTypeDeclaration recordType) {
		String name = recordType.name;
		if(records.containsKey(name)) {
			throw new OverwritingDeclarationError(name, records.get(name), recordType);
		}
		records.put(name, recordType);
	}
	
	
	/**
	 * Get the record type declaration (AST node) for the given record type name.
	 * Throws an {@link UndeclaredReferenceError} if no record type with the given
	 * name was defined in this module or the standard runtime environment.
	 *
	 * @param name recordType name.
	 * @return The record type declaration if a record type with the given name is defined.
	 */
	public RecordTypeDeclaration getRecordTypeDeclaration(String name) {
		if(!records.containsKey(name)) {
			throw new UndeclaredReferenceError(name);
		}
		return records.get(name);
	}
	
	public Collection<RecordTypeDeclaration> getRecordTypeDeclarations() { return records.values(); }
}
