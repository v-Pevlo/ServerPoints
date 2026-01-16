package v.pevlo.util

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MessagesManager(plugin: JavaPlugin) {
    private val file = File(plugin.dataFolder, "messages.yml")
    private lateinit var config: FileConfiguration

    init {
        if (!file.exists()) plugin.saveResource("messages.yml", false)
        reload()
    }

    fun reload() {
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun get(path: String) = config.getString(path) ?: ChatUtil.color("&eMissing: &c$path")
}