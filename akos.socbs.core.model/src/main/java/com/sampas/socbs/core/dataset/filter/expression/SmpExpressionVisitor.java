package com.sampas.socbs.core.dataset.filter.expression;

import org.opengis.filter.expression.Add;
import org.opengis.filter.expression.Divide;
import org.opengis.filter.expression.ExpressionVisitor;
import org.opengis.filter.expression.Function;
import org.opengis.filter.expression.Literal;
import org.opengis.filter.expression.Multiply;
import org.opengis.filter.expression.PropertyName;
import org.opengis.filter.expression.Subtract;

import com.sampas.socbs.core.dataset.filter.impl.SmpPropertyName;

public class SmpExpressionVisitor {
	
	private ExpressionVisitor geoToolsExpressionVisitor = null;

	public SmpExpressionVisitor() {

	}

	public SmpExpressionVisitor(ExpressionVisitor geoToolsExpressionVisitor) {

		this.geoToolsExpressionVisitor = geoToolsExpressionVisitor;
	}

	public ExpressionVisitor getGeotoolsExpressionVisitor() {
		
		return (this.geoToolsExpressionVisitor);
	}
	
    Object visit(SmpAdd expression, Object extraData) {
    	
    	Add geoToolsAddExpression = expression.getGeotoolsAdd();
    	
    	return (this.geoToolsExpressionVisitor.visit(geoToolsAddExpression, extraData));
    }
    
    Object visit(SmpDivide expression, Object extraData)  {
    	
    	Divide geoToolsDivideExpression = expression.getGeotoolsDivide();
    	
    	return (this.geoToolsExpressionVisitor.visit(geoToolsDivideExpression, extraData));
    }
    
    Object visit(SmpFunction expression, Object extraData) {
    	
    	Function geoToolsFunctionExpression = expression.getGeotoolsFunction();
    	
    	return (this.geoToolsExpressionVisitor.visit(geoToolsFunctionExpression, extraData));
    }
    
    Object visit(SmpLiteral expression, Object extraData){
    	
    	Literal geoToolsLiteralExpression = expression.getGeotoolsLiteral();
    	
    	return (this.geoToolsExpressionVisitor.visit(geoToolsLiteralExpression, extraData));
    }
    
    Object visit(SmpMultiply expression, Object extraData) {
    	
    	Multiply geoToolsMultiplyExpression = expression.getGeotoolsMultiply();
    	
    	return (this.geoToolsExpressionVisitor.visit(geoToolsMultiplyExpression, extraData));
    }
    
    Object visit(SmpPropertyName expression, Object extraData) {
    	
    	PropertyName geoToolsPropertyNameExpression = expression.getGeotoolsPropertyName();
    	
    	return (this.geoToolsExpressionVisitor.visit(geoToolsPropertyNameExpression, extraData));
    }
    
    Object visit(SmpSubtract expression, Object extraData) {
    	
    	Subtract geoToolsSubtractExpression = expression.getGeotoolsSubtract();
    	
    	return (this.geoToolsExpressionVisitor.visit(geoToolsSubtractExpression, extraData));
    }
	

}
