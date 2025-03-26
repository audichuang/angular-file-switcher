package com.jaredrobertson.plugins.angularFileSwitcher

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class FileSwitcherStartupActivity : StartupActivity {
    override fun runActivity(project: Project) {
        // 當項目啟動時更新所有快捷鍵
        ShortcutRegistration.updateAllShortcuts()
    }
} 