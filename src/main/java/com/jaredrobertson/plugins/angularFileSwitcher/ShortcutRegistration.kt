package com.jaredrobertson.plugins.angularFileSwitcher

import com.intellij.openapi.actionSystem.KeyboardShortcut
import com.intellij.openapi.components.Service
import com.intellij.openapi.keymap.Keymap
import com.intellij.openapi.keymap.KeymapManager
import com.jaredrobertson.plugins.angularFileSwitcher.settings.AppSettingsState
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

@Service
class ShortcutRegistration {
    
    companion object {
        private const val TS_ACTION_ID = "angular.SwitchToTypeScript"
        private const val HTML_ACTION_ID = "angular.SwitchToHtml"
        private const val CSS_ACTION_ID = "angular.SwitchToStyle"
        private const val TEST_ACTION_ID = "angular.SwitchToTest"
        private const val NEXT_FILE_ACTION_ID = "angular.QuickSwitch"
        
        // 更新所有快捷鍵
        fun updateAllShortcuts() {
            updateTsShortcut()
            updateHtmlShortcut()
            updateCssShortcut()
            updateTestShortcut()
            updateNextFileShortcut()
        }
        
        // 更新TypeScript文件的快捷鍵
        fun updateTsShortcut() {
            updateShortcut(TS_ACTION_ID, AppSettingsState.instance.tsShortcut)
        }
        
        // 更新HTML文件的快捷鍵
        fun updateHtmlShortcut() {
            updateShortcut(HTML_ACTION_ID, AppSettingsState.instance.htmlShortcut)
        }
        
        // 更新CSS/樣式文件的快捷鍵
        fun updateCssShortcut() {
            updateShortcut(CSS_ACTION_ID, AppSettingsState.instance.cssShortcut)
        }
        
        // 更新測試文件的快捷鍵
        fun updateTestShortcut() {
            updateShortcut(TEST_ACTION_ID, AppSettingsState.instance.testShortcut)
        }
        
        // 更新下一個文件的快捷鍵
        fun updateNextFileShortcut() {
            updateShortcut(NEXT_FILE_ACTION_ID, AppSettingsState.instance.nextFileShortcut)
        }
        
        // 更新指定動作的快捷鍵
        private fun updateShortcut(actionId: String, shortcutText: String) {
            try {
                val keymap = KeymapManager.getInstance().activeKeymap
                
                // 首先移除現有的快捷鍵
                keymap.removeAllActionShortcuts(actionId)
                
                // 解析快捷鍵文本並設置新的快捷鍵
                val parts = shortcutText.trim().split(" ")
                if (parts.size == 2) {
                    val modifier = getModifier(parts[0].toLowerCase())
                    val key = getKey(parts[1].toUpperCase())
                    
                    if (modifier != -1 && key != -1) {
                        val firstKeyStroke = KeyStroke.getKeyStroke(key, modifier)
                        val shortcut = KeyboardShortcut(firstKeyStroke, null)
                        keymap.addShortcut(actionId, shortcut)
                    }
                }
            } catch (e: Exception) {
                // 忽略無效的快捷鍵
            }
        }
        
        // 獲取修飾鍵代碼
        private fun getModifier(modifier: String): Int {
            return when (modifier) {
                "alt" -> KeyEvent.ALT_MASK
                "ctrl" -> KeyEvent.CTRL_MASK
                "shift" -> KeyEvent.SHIFT_MASK
                "meta", "cmd" -> KeyEvent.META_MASK
                else -> -1
            }
        }
        
        // 獲取鍵代碼
        private fun getKey(key: String): Int {
            if (key.length == 1) {
                val char = key[0]
                return KeyEvent.getExtendedKeyCodeForChar(char.toInt())
            }
            
            return when (key) {
                "A" -> KeyEvent.VK_A
                "B" -> KeyEvent.VK_B
                "C" -> KeyEvent.VK_C
                "D" -> KeyEvent.VK_D
                "E" -> KeyEvent.VK_E
                "F" -> KeyEvent.VK_F
                "G" -> KeyEvent.VK_G
                "H" -> KeyEvent.VK_H
                "I" -> KeyEvent.VK_I
                "J" -> KeyEvent.VK_J
                "K" -> KeyEvent.VK_K
                "L" -> KeyEvent.VK_L
                "M" -> KeyEvent.VK_M
                "N" -> KeyEvent.VK_N
                "O" -> KeyEvent.VK_O
                "P" -> KeyEvent.VK_P
                "Q" -> KeyEvent.VK_Q
                "R" -> KeyEvent.VK_R
                "S" -> KeyEvent.VK_S
                "T" -> KeyEvent.VK_T
                "U" -> KeyEvent.VK_U
                "V" -> KeyEvent.VK_V
                "W" -> KeyEvent.VK_W
                "X" -> KeyEvent.VK_X
                "Y" -> KeyEvent.VK_Y
                "Z" -> KeyEvent.VK_Z
                "0" -> KeyEvent.VK_0
                "1" -> KeyEvent.VK_1
                "2" -> KeyEvent.VK_2
                "3" -> KeyEvent.VK_3
                "4" -> KeyEvent.VK_4
                "5" -> KeyEvent.VK_5
                "6" -> KeyEvent.VK_6
                "7" -> KeyEvent.VK_7
                "8" -> KeyEvent.VK_8
                "9" -> KeyEvent.VK_9
                else -> -1
            }
        }
    }
} 