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
import mavlc.syntax.expression.CallExpression;

import java.util.Objects;

/**
 * AST node representing calls to void-functions.
 */
public class CallStatement extends Statement {
	
	public final CallExpression callExpression;
	
	/**
	 * @param sourceLocation Location of the node within the containing source file.
	 * @param callExpression The actual call expression.
	 */
	public CallStatement(SourceLocation sourceLocation, CallExpression callExpression) {
		super(sourceLocation);
		this.callExpression = callExpression;
	}
	
	@Override
	public <RetTy, ArgTy> RetTy accept(AstNodeVisitor<? extends RetTy, ArgTy> visitor, ArgTy obj) {
		return visitor.visitCallStatement(this, obj);
	}
	
	@Override public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		CallStatement that = (CallStatement) o;
		return Objects.equals(callExpression, that.callExpression);
	}
	
	@Override public int hashCode() {
		return Objects.hash(callExpression);
	}
}
