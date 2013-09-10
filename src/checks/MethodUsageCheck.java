/**
 * File: MethodUsageCheck.java
 * Created: 6/25/2013
 * Last Changed: 8/26/2013
 * @author Eliezer Encarnacion
 * 
 * Description: Checks that the method defined by methodName is not used
 */

package checks;

import javax.swing.JOptionPane;
import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import logging.Log;

public class MethodUsageCheck extends Check {
	
	private String methodName = "split"; //default
	private Log logger = new Log();
	
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.DOT }; //Return all the method calls;
	}

	public void visitToken(DetailAST ast) {
		DetailAST ident = ast.findFirstToken(TokenTypes.IDENT);
	
		while (ident != null) {
			if (ident.getText().equals(methodName)) {
				log(ast.getLineNo(), "methodusage", methodName);
				
				JOptionPane.showConfirmDialog(null, getClassName(ast));
				
				//logger.log("methodusage", Integer.toString(ast.getLineNo()));
				logger.closeStream();
			}
			
			ident = ident.getNextSibling();
		}
	
	}
	
	private String getClassName(DetailAST ast) {
		DetailAST className = ast;
		while (className.getType() != TokenTypes.CLASS_DEF)
			className = className.getParent();
		
		return className.getText();
	}
}
