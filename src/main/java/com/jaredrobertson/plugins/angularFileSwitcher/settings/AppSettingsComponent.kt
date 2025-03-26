// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.jaredrobertson.plugins.angularFileSwitcher.settings

import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.IdeBorderFactory
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import com.jaredrobertson.plugins.angularFileSwitcher.models.CloseBehavior
import com.jaredrobertson.plugins.angularFileSwitcher.models.Grouping
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val panel: JPanel
    private val myClassFileExtensionsText = JBTextField()
    private val myTemplateFileExtensionsText = JBTextField()
    private val myStyleFileExtensionsText = JBTextField()
    private val myTestFileExtensionsText = JBTextField()
    private val mySwitcherGroupingCombo = ComboBox(Grouping.values(), 240)
    private val myCloseBehaviorCombo = ComboBox(CloseBehavior.values(), 240)
    
    // 快捷鍵輸入欄位
    private val myTsShortcutField = JBTextField()
    private val myHtmlShortcutField = JBTextField()
    private val myCssShortcutField = JBTextField()
    private val myTestShortcutField = JBTextField()
    private val myNextFileShortcutField = JBTextField()
    
    // 復原按鈕
    private val myResetShortcutsButton = JButton("還原預設快捷鍵")

    init {
        val fileExtensionTypePanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Class: ", myClassFileExtensionsText, 5, false)
            .addLabeledComponent("Template: ", myTemplateFileExtensionsText, 5, false)
            .addLabeledComponent("Style: ", myStyleFileExtensionsText, 5, false)
            .addLabeledComponent("Test: ", myTestFileExtensionsText, 5, false)
            .panel
        fileExtensionTypePanel.border = IdeBorderFactory.createTitledBorder("File Extension Types")
        
        val otherSettingsPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(
                "Open and close same component files: ",
                mySwitcherGroupingCombo,
                5,
                false
            )
            .addLabeledComponent("Close other component files: ", myCloseBehaviorCombo, 5, false)
            .panel
        otherSettingsPanel.border = IdeBorderFactory.createTitledBorder("Other Settings")
        
        // 添加快捷鍵設定面板
        val shortcutPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent("TypeScript File Shortcut: ", myTsShortcutField, 5, false)
            .addLabeledComponent("HTML File Shortcut: ", myHtmlShortcutField, 5, false)
            .addLabeledComponent("CSS/Style File Shortcut: ", myCssShortcutField, 5, false) 
            .addLabeledComponent("Test File Shortcut: ", myTestShortcutField, 5, false)
            .addLabeledComponent("Next File Shortcut: ", myNextFileShortcutField, 5, false)
            .addComponent(myResetShortcutsButton)
            .panel
        shortcutPanel.border = IdeBorderFactory.createTitledBorder("Keyboard Shortcuts")
        
        // 設置復原按鈕的點擊事件
        myResetShortcutsButton.addActionListener {
            resetShortcutsToDefault()
        }
        
        panel = FormBuilder.createFormBuilder()
            .addComponent(fileExtensionTypePanel)
            .addComponent(otherSettingsPanel)
            .addComponent(shortcutPanel)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    // 將快捷鍵重置為默認值
    private fun resetShortcutsToDefault() {
        myTsShortcutField.text = AppSettingsState.DEFAULT_TS_SHORTCUT
        myHtmlShortcutField.text = AppSettingsState.DEFAULT_HTML_SHORTCUT
        myCssShortcutField.text = AppSettingsState.DEFAULT_CSS_SHORTCUT
        myTestShortcutField.text = AppSettingsState.DEFAULT_TEST_SHORTCUT
        myNextFileShortcutField.text = AppSettingsState.DEFAULT_NEXT_FILE_SHORTCUT
    }

    val preferredFocusedComponent: JComponent
        get() = myClassFileExtensionsText
    var classFileExtensionsText: String
        get() = myClassFileExtensionsText.text
        set(newText) {
            myClassFileExtensionsText.text = newText
        }
    var templateFileExtensionsText: String
        get() = myTemplateFileExtensionsText.text
        set(newText) {
            myTemplateFileExtensionsText.text = newText
        }
    var styleFileExtensionsText: String
        get() = myStyleFileExtensionsText.text
        set(newText) {
            myStyleFileExtensionsText.text = newText
        }
    var testFileExtensionsText: String
        get() = myTestFileExtensionsText.text
        set(newText) {
            myTestFileExtensionsText.text = newText
        }
    var switcherGrouping: Grouping
        get() = mySwitcherGroupingCombo.item
        set(newGrouping) {
            mySwitcherGroupingCombo.item = newGrouping
        }
    var closeBehavior: CloseBehavior
        get() = myCloseBehaviorCombo.item
        set(newCloseBehavior) {
            myCloseBehaviorCombo.item = newCloseBehavior
        }
        
    // 快捷鍵屬性訪問器
    var tsShortcut: String
        get() = myTsShortcutField.text
        set(newShortcut) {
            myTsShortcutField.text = newShortcut
        }
        
    var htmlShortcut: String
        get() = myHtmlShortcutField.text
        set(newShortcut) {
            myHtmlShortcutField.text = newShortcut
        }
        
    var cssShortcut: String
        get() = myCssShortcutField.text
        set(newShortcut) {
            myCssShortcutField.text = newShortcut
        }
        
    var testShortcut: String
        get() = myTestShortcutField.text
        set(newShortcut) {
            myTestShortcutField.text = newShortcut
        }
        
    var nextFileShortcut: String
        get() = myNextFileShortcutField.text
        set(newShortcut) {
            myNextFileShortcutField.text = newShortcut
        }
}