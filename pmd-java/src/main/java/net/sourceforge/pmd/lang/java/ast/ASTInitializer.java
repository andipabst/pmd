/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
/* Generated By:JJTree: Do not edit this line. ASTInitializer.java */

package net.sourceforge.pmd.lang.java.ast;

/**
 * A class or instance initializer. Don't confuse with {@link ASTVariableInitializer}.
 *
 * <pre class="grammar">
 *
 * Initializer ::= "static"? {@link ASTBlock Block}
 *
 * </pre>
 *
 */
public class ASTInitializer extends AbstractJavaNode {

    private boolean isStatic;

    public ASTInitializer(int id) {
        super(id);
    }

    public ASTInitializer(JavaParser p, int id) {
        super(p, id);
    }

    /**
     * Accept the visitor. *
     */
    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }


    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic() {
        isStatic = true;
    }
}
