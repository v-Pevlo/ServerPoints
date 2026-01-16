package v.pevlo.events

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import v.pevlo.Main
import v.pevlo.util.ChatUtil.message

private val plugin = Main.Companion.instance
private val msgs = plugin.msgs
val config = plugin.config

class EventsManager(plugin: Plugin): Listener {
    companion object : Listener {
        lateinit var eventManager: EventsManager
    }

    @EventHandler
    fun join(e: PlayerJoinEvent) {
        val xyz = locGet("spawn-location")
        val loc = Location(xyz[0] as World?,
                            xyz[1] as Double,
                            xyz[2] as Double,
                            xyz[3] as Double,
                            xyz[4] as Float,
                            xyz[5] as Float
        )
        e.player.teleport(loc)
        e.player.message(msgs.get("join-message"), Pair("%player%", e.player.name)) /**/
    }
}

fun locGet(path: String): List<Any?> {
    val world = config.getString("$path.world")?.let { Bukkit.getWorld(it) }
    val x = config.getDouble("$path.x")
    val y = config.getDouble("$path.y")
    val z = config.getDouble("$path.z")
    val yaw = config.getDouble("$path.yaw").toFloat()
    val pitch = config.getDouble("$path.pitch").toFloat()

    return listOf(world, x, y, z, yaw, pitch)
}