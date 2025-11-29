// This is a generated file. Not intended for manual editing.
package cobol.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import cobol.psi.*;

public class CobolStatementImpl extends ASTWrapperPsiElement implements CobolStatement {

  public CobolStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull Visitor visitor) {
    visitor.visitCobolStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof Visitor) accept((Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DataDivision getDataDivision() {
    return findChildByClass(DataDivision.class);
  }

  @Override
  @Nullable
  public DisplayStatement getDisplayStatement() {
    return findChildByClass(DisplayStatement.class);
  }

  @Override
  @Nullable
  public IdentificationDivision getIdentificationDivision() {
    return findChildByClass(IdentificationDivision.class);
  }

  @Override
  @Nullable
  public Paragraph getParagraph() {
    return findChildByClass(Paragraph.class);
  }

  @Override
  @Nullable
  public PerformStatement getPerformStatement() {
    return findChildByClass(PerformStatement.class);
  }

  @Override
  @Nullable
  public ProcedureDivision getProcedureDivision() {
    return findChildByClass(ProcedureDivision.class);
  }

}
