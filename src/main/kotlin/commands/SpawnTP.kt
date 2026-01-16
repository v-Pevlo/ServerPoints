package v.pevlo.commands

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import v.pevlo.util.ChatUtil.message
import v.pevlo.Main
import v.pevlo.events.locGet

private val plugin = Main.instance
private val msgs = plugin.msgs

object SpawnTP: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String?>): Boolean {
        if (sender is Player) {
            val xyz = locGet("spawn-location")
            val loc = Location(
                xyz[0] as World?,
                xyz[1] as Double,
                xyz[2] as Double,
                xyz[3] as Double,
                xyz[4] as Float,
                xyz[5] as Float
            )
            sender.teleport(loc)
            sender.message(msgs.get("spawn-teleport"), Pair("%player%", sender.name))
        }

        return true
    }
}