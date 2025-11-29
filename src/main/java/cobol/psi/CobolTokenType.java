package cobol.psi;

import com.intellij.psi.tree.IElementType;
import cobol.CobolLanguage;
import org.jetbrains.annotations.NotNull;

public class CobolTokenType extends IElementType {
    public CobolTokenType(@NotNull String debugName) {
        super(debugName, CobolLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CobolTokenType." + super.toString();
    }
}
