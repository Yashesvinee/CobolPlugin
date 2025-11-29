package cobol.psi;

import com.intellij.psi.tree.IElementType;
import cobol.CobolLanguage;
import org.jetbrains.annotations.NotNull;

public class CobolElementType extends IElementType {
    public CobolElementType(@NotNull String debugName) {
        super(debugName, CobolLanguage.INSTANCE);
    }
}
