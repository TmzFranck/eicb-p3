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
package mavlc.syntax.statement;

import mavlc.syntax.AstNodeVisitor;
import mavlc.syntax.SourceLocation;
import mavlc.syntax.expression.Expression;
import mavlc.syntax.type.TypeSpecifier;

import java.util.Objects;

/**
 * AST node representing the definition of a constant value.
 */
public class ValueDefinition extends Declaration {
	
	public final Expression value;
	
	/**
	 * @param sourceLocation Location of the node within the containing source file.
	 * @param typeSpecifier The type of the defined value.
	 * @param valueName The name of the defined value.
	 * @param value The constant value.
	 */
	public ValueDefinition(SourceLocation sourceLocation, TypeSpecifier<?> typeSpecifier, String valueName, Expression value) {
		super(sourceLocation, typeSpecifier, valueName);
		this.value = value;
	}
	
	@Override
	public boolean isVariable() {
		return false;
	}
	
	@Override
	public <RetTy, ArgTy> RetTy accept(AstNodeVisitor<? extends RetTy, ArgTy> visitor, ArgTy obj) {
		return visitor.visitValueDefinition(this, obj);
	}
	
	@Override public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		if(!super.equals(o)) return false;
		ValueDefinition that = (ValueDefinition) o;
		return Objects.equals(value, that.value);
	}
	
	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), value);
	}
}
