/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
/* Generated By:JJTree: Do not edit this line. ASTAllocationExpression.java */

package net.sourceforge.pmd.lang.java.ast;

import org.checkerframework.checker.nullness.qual.Nullable;
/**
 * A class instance creation expression. Represents both {@linkplain #isQualifiedInstanceCreation() qualified}
 * and unqualified instance creation. May declare an anonymous class body.
 *
 *
 * <pre class="grammar">
 *
 * ConstructorCall   ::= UnqualifiedAlloc
 *                     | {@link ASTPrimaryExpression PrimaryExpression} "." UnqualifiedAlloc
 *
 * UnqualifiedAlloc                  ::=
 *      "new" {@link ASTTypeArguments TypeArguments}? ClassOrInterfaceTypeToInstantiate {@link ASTArgumentList ArgumentList} {@link ASTAnonymousClassDeclaration AnonymousClassDeclaration}?
 *
 * ClassOrInterfaceTypeToInstantiate ::=
 *      {@link ASTAnnotation TypeAnnotation}* {@link ASTClassOrInterfaceType ClassOrInterfaceType}
 *
 * </pre>
 */
public final class ASTConstructorCall extends AbstractJavaTypeNode implements ASTPrimaryExpression, ASTQualifiableExpression, LeftRecursiveNode {

    ASTConstructorCall(int id) {
        super(id);
    }


    ASTConstructorCall(JavaParser p, int id) {
        super(p, id);
    }


    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }

    @Override
    public void jjtClose() {
        super.jjtClose();

        /* JLS:
         *  A name is syntactically classified as an ExpressionName in these contexts:
         *       ...
         *     - As the qualifying expression in a qualified class instance creation expression (§15.9)*
         */
        AbstractJavaNode firstChild = (AbstractJavaNode) jjtGetChild(0);

        if (firstChild instanceof ASTAmbiguousName) {
            replaceChildAt(0, (AbstractJavaNode) ((ASTAmbiguousName) firstChild).forceExprContext());
        }
    }

    /**
     * Returns true if this expression begins with a primary expression.
     * Such an expression creates an instance of inner member classes and
     * their anonymous subclasses. For example, {@code new Outer().new Inner()}
     * evaluates to an instance of the Inner class, which is nested inside
     * the new instance of Outer.
     */
    public boolean isQualifiedInstanceCreation() {
        return jjtGetChild(0) instanceof ASTPrimaryExpression;
    }


    @Nullable
    public ASTTypeArguments getExplicitTypeArguments() {
        return getFirstChildOfType(ASTTypeArguments.class);
    }


    public ASTArgumentList getArguments() {
        int idx = jjtGetNumChildren() - (isAnonymousClass() ? 2 : 1);
        return (ASTArgumentList) jjtGetChild(idx);
    }


    /**
     * Returns the type node.
     */
    public ASTClassOrInterfaceType getTypeNode() {
        return getFirstChildOfType(ASTClassOrInterfaceType.class);
    }


    /**
     * Returns true if this expression defines a body,
     * which is compiled to an anonymous class. If this
     * method returns false.
     */
    public boolean isAnonymousClass() {
        return jjtGetChild(jjtGetNumChildren() - 1) instanceof ASTAnonymousClassDeclaration;
    }


    @Nullable
    public ASTAnonymousClassDeclaration getAnonymousClassDeclaration() {
        return isAnonymousClass()
               ? (ASTAnonymousClassDeclaration) jjtGetChild(jjtGetNumChildren() - 1)
               : null;
    }
}
