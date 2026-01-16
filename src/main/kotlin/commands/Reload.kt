package v.pevlo.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import v.pevlo.util.ChatUtil.message
import v.pevlo.Main

private val plugin = Main.instance
private val msgs = plugin.msgs

object Reload: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String?>): Boolean {
        if (!sender.isOp) return false

        plugin.reloadConfig()
        msgs.reload()
        sender.message(msgs.get("reload-message"))

        return true
    }
}