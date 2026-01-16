package v.pevlo.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import v.pevlo.util.ChatUtil.message
import v.pevlo.home.Home
import v.pevlo.home.HomesManager
import v.pevlo.Main
import v.pevlo.home.SerializableLocation

private val plugin = Main.instance
private val msgs = plugin.msgs

object SetHome: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String?>): Boolean {
        if (sender is Player) {
            val home = Home(
                sender.uniqueId.toString(),
                SerializableLocation.fromBukkit(sender.location)
            )

            HomesManager(plugin).saveHome(home)
            sender.message(
                msgs.get("home-set"),
                Pair("%player%", sender.name),
                Pair("%x%", "%.2f".format(home.loc.x)),
                Pair("%y%", "%.2f".format(home.loc.y)),
                Pair("%z%","%.2f".format(home.loc.z))
            )
        }

        return true
    }
}