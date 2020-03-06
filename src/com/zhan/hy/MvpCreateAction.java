package com.zhan.hy;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.PlatformIcons;
import com.zhan.hy.bean.FileInfo;
import com.zhan.hy.bean.InputInfo;
import com.zhan.hy.constant.MvpConst;
import com.zhan.hy.creator.*;
import com.zhan.hy.format.WrapperFactory;
import com.zhan.hy.ui.MvpTemplateDialog;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * author：  HyZhan
 * create：  2019/7/4
 * desc：    TODO
 */
public class MvpCreateAction extends AnAction {

    private Project project;
    private String manifestPath;
    private MvpTemplateDialog dialog;
    private FileInfo fileInfo = new FileInfo();

    private String packagePath;
    private String saveLocation;

    public MvpCreateAction() {
        super(PlatformIcons.CLASS_ICON);
    }

    /**
     * 事件监听器
     * 例如点击按钮、文本框里按下回车，就会调用这个里面的方法。
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        IdeView ideView = e.getRequiredData(LangDataKeys.IDE_VIEW);

        // 获取鼠标右击选中的目录
        PsiDirectory directory = ideView.getOrChooseDirectory();
        if (directory == null) {
            return;
        }

        project = directory.getProject();
        saveLocation = directory.getVirtualFile().getPath();

        fileInfo.setBasePath(project.getBasePath());

        manifestPath = project.getBasePath() + "/app/src/main/AndroidManifest.xml";
        fileInfo.setPackageName(FileUtils.getPackageName(manifestPath));
        fileInfo.setAppPath(getAppPath());
        packagePath = getPackagePath();

        initDialog();
        // 刷新项目
        project.getBaseDir().refresh(false, true);
    }

    /**
     * 初始化Dialog
     */
    private void initDialog() {
        dialog = new MvpTemplateDialog(this::createMvpTemplate);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createMvpTemplate(InputInfo inputInfo) {
        String module = inputInfo.getModuleName();
        if (module.isEmpty()) {
            Messages.showErrorDialog("module name is Empty", MvpConst.TITLE);
            return;
        }

        fileInfo.setModuleName(module);
        fileInfo.setSaveLocation(saveLocation + "/" + module.toLowerCase());
        fileInfo.setCustomPath(getCustomPath(module.toLowerCase()));
        fileInfo.setActivity(inputInfo.isSelectActivity());

        // 创建mvp代码模板文件
        createFiles(inputInfo);
        Messages.showInfoMessage(MvpConst.SUCCESS_MSG, MvpConst.TITLE);
        closeDialog();
    }

    private void closeDialog() {
        dialog.dispose();
    }

    /**
     * 生成类文件
     */
    private void createFiles(InputInfo inputInfo) {

        if (inputInfo.isSelectActivity()) {
            registerManifest();
        }

        createClassFile(inputInfo.isSelectActivity(), ActivityCreator.class);
        createClassFile(inputInfo.isSelectFragment(), FragmentCreator.class);
        createClassFile(inputInfo.isSelectContract(), ContractCreator.class);
        createClassFile(inputInfo.isSelectModel(), ModelCreator.class);
        createClassFile(inputInfo.isSelectPresenter(), PresenterCreator.class);
        createClassFile(inputInfo.isSelectLayout(), LayoutCreator.class);
    }

    /**
     * 生成mvp框架代码
     */
    private <T extends Creator> void createClassFile(Boolean isCreate, Class<T> creatorClz) {
        if (!isCreate) {
            return;
        }

        T creator = FileFactory.createCreator(creatorClz);
        if (creator != null) {
            creator.mkdirFile(fileInfo);
        }
    }

    private void registerManifest() {
        String fileName = "ActivityNodeTemplate.txt";
        String content = FileUtils.readTemplateFile("template/" + fileName);
        content = new WrapperFactory().wrap(content, fileInfo);

        FileUtils.writeToManifest(manifestPath, content);
    }

    /**
     * 获取包名文件路径
     */
    private String getAppPath() {
        String packagePath = getPackagePath();
        return project.getBasePath() + "/app/src/main/java/" + packagePath + "/";
    }

    private String getPackagePath() {
        return fileInfo.getPackageName().replace(".", "/");
    }

    private String getCustomPath(String module) {
        int index = saveLocation.indexOf(packagePath);
        String customPath = saveLocation.substring(index + packagePath.length());
        if (StringUtils.isEmpty(customPath)) {
            return "." + module;
        }

        return customPath.replace("/", ".") + "." + module;
    }
}
