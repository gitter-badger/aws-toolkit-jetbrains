package software.aws.toolkits.jetbrains.services.lambda.upload;

import com.intellij.openapi.ui.ComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jetbrains.annotations.NotNull;
import software.amazon.awssdk.services.lambda.model.Runtime;

@SuppressWarnings("NullableProblems")
public final class CreateLambdaPanel {
    @NotNull JTextField name;
    @NotNull JTextField description;
    @NotNull JTextField handler;
    @NotNull JButton createRole;
    @NotNull JButton createBucket;
    @NotNull JPanel content;
    @NotNull ComboBox<IamRole> iamRole;
    @NotNull ComboBox<Runtime> runtime;
    @NotNull ComboBox<String> sourceBucket;
}