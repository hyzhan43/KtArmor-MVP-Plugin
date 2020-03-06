package com.zhan.hy.ui;

import com.intellij.openapi.wm.WindowManager;
import com.zhan.hy.MvpCreateAction;
import com.zhan.hy.bean.InputInfo;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MvpTemplateDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField moduleNameField;
    private JRadioButton activityRadioButton;
    private JRadioButton fragmentRadioButton;
    private JCheckBox contractCheckBox;
    private JCheckBox presenterCheckBox;
    private JCheckBox layoutCheckBox;
    private JCheckBox modelCheckBox;

    private DialogCallback callback;

    public MvpTemplateDialog(DialogCallback callback) {
        this.callback = callback;
        setTitle("KtArmor-MVP");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        // 设置大小
        setSize(400, 250);
        // 设置居中显示
        setLocationRelativeTo(null);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(activityRadioButton);
        buttonGroup.add(fragmentRadioButton);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if (callback != null) {

            InputInfo inputInfo = new InputInfo();
            inputInfo.setModuleName(moduleNameField.getText().trim());

            inputInfo.setSelectFragment(fragmentRadioButton.isSelected());
            inputInfo.setSelectActivity(activityRadioButton.isSelected());
            inputInfo.setSelectContract(contractCheckBox.isSelected());
            inputInfo.setSelectModel(modelCheckBox.isSelected());
            inputInfo.setSelectPresenter(presenterCheckBox.isSelected());
            inputInfo.setSelectLayout(layoutCheckBox.isSelected());

            callback.onOk(inputInfo);
        }
        // dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {


        MvpTemplateDialog dialog = new MvpTemplateDialog((inputInfo) -> {

        });
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);


//        MvpTemplateDialog dialog = new MvpTemplateDialog((AUTHOR, name) -> {
//            String fileName = "ActivityTemplate.txt";
//            String content = ReadTemplateFile(fileName);
//            System.out.println("before : " + content);
//            //content = dealTemplateContent(content);
//            System.out.println("after: " + content);
//        });

//        FileUtils.writeToManifest("E:/Tools/Java/WorkSpace/KtArmor-MVP-Plugin/src/com/zhan/hy" + "/AndroidManifest.xml"
//        ,"Hello World");

        // FileUtils.readTemplateFile("E:/Tools/Java/WorkSpace/KtArmor-MVP-Plugin/src/com/zhan/hy/template/ActivityTemplate.txt");


        //FileUtils.readTemplateFile("template/ActivityTemplate.txt");

//        MvpCreateAction action = new MvpCreateAction();
//        action.actionPerformed();
//        System.exit(0);


    }

    public interface DialogCallback {
        void onOk(InputInfo inputInfo);
    }
}
