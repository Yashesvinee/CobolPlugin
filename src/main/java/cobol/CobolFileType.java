package cobol;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CobolFileType extends LanguageFileType {
    public static final CobolFileType INSTANCE = new CobolFileType();

    private CobolFileType() {
        super(CobolLanguage.INSTANCE);
    }

    @Override
    public @NotNull String getName() {
        return "COBOL File";
    }

    @Override
    public @NotNull String getDescription() {
        return "COBOL language file";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "cbl";
    }

    @Override
    public @Nullable Icon getIcon() {
        return null;
    }
}
