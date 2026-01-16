package v.pevlo.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import v.pevlo.util.ChatUtil.message
import v.pevlo.home.HomesManager
import v.pevlo.Main

private val plugin = Main.instance
private val msgs = plugin.msgs

object HomeTP: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String?>): Boolean {
        if (sender is Player) {
            val home = HomesManager(plugin).loadHome(sender.uniqueId.toString())
            val loc = home?.loc?.toBukkit()

            if (loc != null) {
                sender.teleport(home.loc.toBukkit()!!)
                sender.message(
                    msgs.get("home-teleport"),
                    Pair("%player%", sender.name),
                    Pair("%x%", "%.2f".format(home.loc.x)),
                    Pair("%y%", "%.2f".format(home.loc.y)),
                    Pair("%z%","%.2f".format(home.loc.z))
                )
            } else {
                sender.message(msgs.get("no-home-message"), Pair("%player%", sender.name))
            }
        }

        return true
    }
}