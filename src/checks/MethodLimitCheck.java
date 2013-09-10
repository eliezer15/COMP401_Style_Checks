/**
 * File: MethodLimitCheck.java
 * Created: 6/25/2013
 * Last Changed: 8/26/2013
 * @author: Eliezer Encarnacion
 * 
 * Description: Checks that the number of methods in a file
 * 				is less than max
 */

package checks;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class MethodLimitCheck extends Check {

    private int max = 30;

    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
    }

    public void setMax(int limit) {
        max = limit;
    }

    public void visitToken(DetailAST ast) {
        // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
        DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
        // count the number of direct children of the OBJBLOCK
        // that are METHOD_DEFS
        int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);
        // report error if limit is reached
        if (methodDefs > max) {
            log(ast.getLineNo(), "methodlimit", max);
        }
    }
}