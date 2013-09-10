/**
 * File: MethodMinCheck.java
 * Created: 6/25/2013
 * Last Changed: 8/26/2013
 * @author Eliezer Encarnacion
 * 
 * Description: Checks that the number of methods in a file
 * 				is greater than or equal to min
 */

package checks;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class MethodMinCheck extends Check {

    private int min = 2; //default

    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
    }

    public void setMin(int limit) {
        min = limit;
    }

    public void visitToken(DetailAST ast) {
        DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
        int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);
        if (methodDefs < min) {
            log(ast.getLineNo(), "methodmin", min);
        }
    }
}