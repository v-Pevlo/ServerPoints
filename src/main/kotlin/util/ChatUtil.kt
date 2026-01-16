package v.pevlo.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

object ChatUtil {
    fun color(msg: String, vararg args: Pair<String, String>): String {
        return ChatColor.translateAlternateColorCodes('&', applyArgs(msg, *args))
    }

    fun applyArgs(text: String, vararg args: Pair<String, String>): String {
        var result = text

        for (arg in args) {
            result = result.replace(arg.first, arg.second)
        }

        return result
    }

    fun log(msg: String, vararg args: Pair<String, String>) {
        Bukkit.getConsoleSender().sendMessage(color(msg, *args))
    }

    fun CommandSender.message(msg: String, vararg args: Pair<String, String>) {
        sendMessage(color(msg, *args))
    }
}